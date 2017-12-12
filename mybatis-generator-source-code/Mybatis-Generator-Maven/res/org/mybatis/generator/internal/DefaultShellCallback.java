// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultShellCallback.java

package org.mybatis.generator.internal;

import java.io.File;
import java.util.StringTokenizer;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.util.messages.Messages;

public class DefaultShellCallback
    implements ShellCallback
{

    public DefaultShellCallback(boolean overwrite)
    {
        this.overwrite = overwrite;
    }

    public File getDirectory(String targetProject, String targetPackage)
        throws ShellException
    {
        File project = new File(targetProject);
        if(!project.isDirectory())
            throw new ShellException(Messages.getString("Warning.9", targetProject));
        StringBuilder sb = new StringBuilder();
        for(StringTokenizer st = new StringTokenizer(targetPackage, "."); st.hasMoreTokens(); sb.append(File.separatorChar))
            sb.append(st.nextToken());

        File directory = new File(project, sb.toString());
        if(!directory.isDirectory())
        {
            boolean rc = directory.mkdirs();
            if(!rc)
                throw new ShellException(Messages.getString("Warning.10", directory.getAbsolutePath()));
        }
        return directory;
    }

    public void refreshProject(String s)
    {
    }

    public boolean isMergeSupported()
    {
        return false;
    }

    public boolean isOverwriteEnabled()
    {
        return overwrite;
    }

    public String mergeJavaFile(String newFileSource, String existingFileFullPath, String javadocTags[])
        throws ShellException
    {
        throw new UnsupportedOperationException();
    }

    private boolean overwrite;
}
