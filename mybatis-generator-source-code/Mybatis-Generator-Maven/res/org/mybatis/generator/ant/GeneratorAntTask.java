// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneratorAntTask.java

package org.mybatis.generator.ant;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.PropertySet;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

// Referenced classes of package org.mybatis.generator.ant:
//            AntProgressCallback

public class GeneratorAntTask extends Task
{

    public GeneratorAntTask()
    {
    }

    public void execute()
        throws BuildException
    {
        if(!StringUtility.stringHasValue(configfile))
            throw new BuildException(Messages.getString("RuntimeError.0"));
        List warnings = new ArrayList();
        File configurationFile = new File(configfile);
        if(!configurationFile.exists())
            throw new BuildException(Messages.getString("RuntimeError.1", configfile));
        Set fullyqualifiedTables = new HashSet();
        if(StringUtility.stringHasValue(fullyQualifiedTableNames))
        {
            for(StringTokenizer st = new StringTokenizer(fullyQualifiedTableNames, ","); st.hasMoreTokens();)
            {
                String s = st.nextToken().trim();
                if(s.length() > 0)
                    fullyqualifiedTables.add(s);
            }

        }
        Set contexts = new HashSet();
        if(StringUtility.stringHasValue(contextIds))
        {
            for(StringTokenizer st = new StringTokenizer(contextIds, ","); st.hasMoreTokens();)
            {
                String s = st.nextToken().trim();
                if(s.length() > 0)
                    contexts.add(s);
            }

        }
        try
        {
            Properties p = propertyset != null ? propertyset.getProperties() : null;
            ConfigurationParser cp = new ConfigurationParser(p, warnings);
            org.mybatis.generator.config.Configuration config = cp.parseConfiguration(configurationFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(new AntProgressCallback(this, verbose), contexts, fullyqualifiedTables);
        }
        catch(XMLParserException e)
        {
            String error;
            for(Iterator iterator1 = e.getErrors().iterator(); iterator1.hasNext(); log(error, 0))
                error = (String)iterator1.next();

            throw new BuildException(e.getMessage());
        }
        catch(SQLException e)
        {
            throw new BuildException(e.getMessage());
        }
        catch(IOException e)
        {
            throw new BuildException(e.getMessage());
        }
        catch(InvalidConfigurationException e)
        {
            String error;
            for(Iterator iterator2 = e.getErrors().iterator(); iterator2.hasNext(); log(error, 0))
                error = (String)iterator2.next();

            throw new BuildException(e.getMessage());
        }
        catch(InterruptedException interruptedexception) { }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new BuildException(e.getMessage());
        }
        String error;
        for(Iterator iterator = warnings.iterator(); iterator.hasNext(); log(error, 1))
            error = (String)iterator.next();

    }

    public String getConfigfile()
    {
        return configfile;
    }

    public void setConfigfile(String configfile)
    {
        this.configfile = configfile;
    }

    public boolean isOverwrite()
    {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite)
    {
        this.overwrite = overwrite;
    }

    public PropertySet createPropertyset()
    {
        if(propertyset == null)
            propertyset = new PropertySet();
        return propertyset;
    }

    public boolean isVerbose()
    {
        return verbose;
    }

    public void setVerbose(boolean verbose)
    {
        this.verbose = verbose;
    }

    public String getContextIds()
    {
        return contextIds;
    }

    public void setContextIds(String contextIds)
    {
        this.contextIds = contextIds;
    }

    public String getFullyQualifiedTableNames()
    {
        return fullyQualifiedTableNames;
    }

    public void setFullyQualifiedTableNames(String fullyQualifiedTableNames)
    {
        this.fullyQualifiedTableNames = fullyQualifiedTableNames;
    }

    private String configfile;
    private boolean overwrite;
    private PropertySet propertyset;
    private boolean verbose;
    private String contextIds;
    private String fullyQualifiedTableNames;
}
