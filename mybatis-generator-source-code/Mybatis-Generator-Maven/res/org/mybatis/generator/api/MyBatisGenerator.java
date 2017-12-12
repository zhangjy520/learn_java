// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MyBatisGenerator.java

package org.mybatis.generator.api;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import org.mybatis.generator.config.*;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.*;
import org.mybatis.generator.internal.util.ClassloaderUtility;
import org.mybatis.generator.internal.util.messages.Messages;

// Referenced classes of package org.mybatis.generator.api:
//            ShellCallback, ProgressCallback, GeneratedXmlFile, GeneratedJavaFile

public class MyBatisGenerator
{

    public MyBatisGenerator(Configuration configuration, ShellCallback shellCallback, List warnings)
        throws InvalidConfigurationException
    {
        if(configuration == null)
            throw new IllegalArgumentException(Messages.getString("RuntimeError.2"));
        this.configuration = configuration;
        if(shellCallback == null)
            this.shellCallback = new DefaultShellCallback(false);
        else
            this.shellCallback = shellCallback;
        if(warnings == null)
            this.warnings = new ArrayList();
        else
            this.warnings = warnings;
        generatedJavaFiles = new ArrayList();
        generatedXmlFiles = new ArrayList();
        projects = new HashSet();
        this.configuration.validate();
    }

    public void generate(ProgressCallback callback)
        throws SQLException, IOException, InterruptedException
    {
        generate(callback, null, null);
    }

    public void generate(ProgressCallback callback, Set contextIds)
        throws SQLException, IOException, InterruptedException
    {
        generate(callback, contextIds, null);
    }

    public void generate(ProgressCallback callback, Set contextIds, Set fullyQualifiedTableNames)
        throws SQLException, IOException, InterruptedException
    {
        if(callback == null)
            callback = new NullProgressCallback();
        generatedJavaFiles.clear();
        generatedXmlFiles.clear();
        List contextsToRun;
        if(contextIds == null || contextIds.size() == 0)
        {
            contextsToRun = configuration.getContexts();
        } else
        {
            contextsToRun = new ArrayList();
            for(Iterator iterator = configuration.getContexts().iterator(); iterator.hasNext();)
            {
                Context context = (Context)iterator.next();
                if(contextIds.contains(context.getId()))
                    contextsToRun.add(context);
            }

        }
        if(configuration.getClassPathEntries().size() > 0)
        {
            ClassLoader classLoader = ClassloaderUtility.getCustomClassloader(configuration.getClassPathEntries());
            ObjectFactory.setExternalClassLoader(classLoader);
        }
        int totalSteps = 0;
        for(Iterator iterator1 = contextsToRun.iterator(); iterator1.hasNext();)
        {
            Context context = (Context)iterator1.next();
            totalSteps += context.getIntrospectionSteps();
        }

        callback.introspectionStarted(totalSteps);
        Context context;
        for(Iterator iterator2 = contextsToRun.iterator(); iterator2.hasNext(); context.introspectTables(callback, warnings, fullyQualifiedTableNames))
            context = (Context)iterator2.next();

        totalSteps = 0;
        for(Iterator iterator3 = contextsToRun.iterator(); iterator3.hasNext();)
        {
            Context context = (Context)iterator3.next();
            totalSteps += context.getGenerationSteps();
        }

        callback.generationStarted(totalSteps);
        Context context;
        for(Iterator iterator4 = contextsToRun.iterator(); iterator4.hasNext(); context.generateFiles(callback, generatedJavaFiles, generatedXmlFiles, warnings))
            context = (Context)iterator4.next();

        callback.saveStarted(generatedXmlFiles.size() + generatedJavaFiles.size());
        Iterator iterator5 = generatedXmlFiles.iterator();
        while(iterator5.hasNext()) 
        {
            GeneratedXmlFile gxf = (GeneratedXmlFile)iterator5.next();
            projects.add(gxf.getTargetProject());
            File targetFile;
            String source;
            try
            {
                File directory = shellCallback.getDirectory(gxf.getTargetProject(), gxf.getTargetPackage());
                targetFile = new File(directory, gxf.getFileName());
                if(targetFile.exists())
                {
                    if(gxf.isMergeable())
                        source = XmlFileMergerJaxp.getMergedSource(gxf, targetFile);
                    else
                    if(shellCallback.isOverwriteEnabled())
                    {
                        source = gxf.getFormattedContent();
                        warnings.add(Messages.getString("Warning.11", targetFile.getAbsolutePath()));
                    } else
                    {
                        source = gxf.getFormattedContent();
                        targetFile = getUniqueFileName(directory, gxf.getFileName());
                        warnings.add(Messages.getString("Warning.2", targetFile.getAbsolutePath()));
                    }
                } else
                {
                    source = gxf.getFormattedContent();
                }
            }
            catch(ShellException e)
            {
                warnings.add(e.getMessage());
                continue;
            }
            callback.checkCancel();
            callback.startTask(Messages.getString("Progress.15", targetFile.getName()));
            writeFile(targetFile, source);
        }
        for(Iterator iterator6 = generatedJavaFiles.iterator(); iterator6.hasNext();)
        {
            GeneratedJavaFile gjf = (GeneratedJavaFile)iterator6.next();
            projects.add(gjf.getTargetProject());
            try
            {
                File directory = shellCallback.getDirectory(gjf.getTargetProject(), gjf.getTargetPackage());
                File targetFile = new File(directory, gjf.getFileName());
                String source;
                if(targetFile.exists())
                {
                    if(shellCallback.isMergeSupported())
                        source = shellCallback.mergeJavaFile(gjf.getFormattedContent(), targetFile.getAbsolutePath(), MergeConstants.OLD_ELEMENT_TAGS);
                    else
                    if(shellCallback.isOverwriteEnabled())
                    {
                        source = gjf.getFormattedContent();
                        warnings.add(Messages.getString("Warning.11", targetFile.getAbsolutePath()));
                    } else
                    {
                        source = gjf.getFormattedContent();
                        targetFile = getUniqueFileName(directory, gjf.getFileName());
                        warnings.add(Messages.getString("Warning.2", targetFile.getAbsolutePath()));
                    }
                } else
                {
                    source = gjf.getFormattedContent();
                }
                callback.checkCancel();
                callback.startTask(Messages.getString("Progress.15", targetFile.getName()));
                writeFile(targetFile, source);
            }
            catch(ShellException e)
            {
                warnings.add(e.getMessage());
            }
        }

        String project;
        for(Iterator iterator7 = projects.iterator(); iterator7.hasNext(); shellCallback.refreshProject(project))
            project = (String)iterator7.next();

        callback.done();
    }

    private void writeFile(File file, String content)
        throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
        bw.write(content);
        bw.close();
    }

    private File getUniqueFileName(File directory, String fileName)
    {
        File answer = null;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < 1000; i++)
        {
            sb.setLength(0);
            sb.append(fileName);
            sb.append('.');
            sb.append(i);
            File testFile = new File(directory, sb.toString());
            if(testFile.exists())
                continue;
            answer = testFile;
            break;
        }

        if(answer == null)
            throw new RuntimeException(Messages.getString("RuntimeError.3", directory.getAbsolutePath()));
        else
            return answer;
    }

    private Configuration configuration;
    private ShellCallback shellCallback;
    private List generatedJavaFiles;
    private List generatedXmlFiles;
    private List warnings;
    private Set projects;
}
