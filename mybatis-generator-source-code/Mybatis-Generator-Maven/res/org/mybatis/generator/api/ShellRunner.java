// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ShellRunner.java

package org.mybatis.generator.api;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.util.messages.Messages;
import org.mybatis.generator.logging.LogFactory;

// Referenced classes of package org.mybatis.generator.api:
//            MyBatisGenerator, VerboseProgressCallback

public class ShellRunner
{

    public ShellRunner()
    {
    }

    public static void main(String args[])
    {
        if(args.length == 0)
        {
            usage();
            System.exit(0);
            return;
        }
        Map arguments = parseCommandLine(args);
        if(arguments.containsKey("-?"))
        {
            usage();
            System.exit(0);
            return;
        }
        if(!arguments.containsKey("-configfile"))
        {
            writeLine(Messages.getString("RuntimeError.0"));
            return;
        }
        List warnings = new ArrayList();
        String configfile = (String)arguments.get("-configfile");
        File configurationFile = new File(configfile);
        if(!configurationFile.exists())
        {
            writeLine(Messages.getString("RuntimeError.1", configfile));
            return;
        }
        Set fullyqualifiedTables = new HashSet();
        if(arguments.containsKey("-tables"))
        {
            for(StringTokenizer st = new StringTokenizer((String)arguments.get("-tables"), ","); st.hasMoreTokens();)
            {
                String s = st.nextToken().trim();
                if(s.length() > 0)
                    fullyqualifiedTables.add(s);
            }

        }
        Set contexts = new HashSet();
        if(arguments.containsKey("-contextids"))
        {
            for(StringTokenizer st = new StringTokenizer((String)arguments.get("-contextids"), ","); st.hasMoreTokens();)
            {
                String s = st.nextToken().trim();
                if(s.length() > 0)
                    contexts.add(s);
            }

        }
        try
        {
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configurationFile);
            DefaultShellCallback shellCallback = new DefaultShellCallback(arguments.containsKey("-overwrite"));
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);
            ProgressCallback progressCallback = arguments.containsKey("-verbose") ? ((ProgressCallback) (new VerboseProgressCallback())) : null;
            myBatisGenerator.generate(progressCallback, contexts, fullyqualifiedTables);
        }
        catch(XMLParserException e)
        {
            writeLine(Messages.getString("Progress.3"));
            writeLine();
            String error;
            for(Iterator iterator1 = e.getErrors().iterator(); iterator1.hasNext(); writeLine(error))
                error = (String)iterator1.next();

            return;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return;
        }
        catch(InvalidConfigurationException e)
        {
            writeLine(Messages.getString("Progress.16"));
            String error;
            for(Iterator iterator2 = e.getErrors().iterator(); iterator2.hasNext(); writeLine(error))
                error = (String)iterator2.next();

            return;
        }
        catch(InterruptedException interruptedexception) { }
        String warning;
        for(Iterator iterator = warnings.iterator(); iterator.hasNext(); writeLine(warning))
            warning = (String)iterator.next();

        if(warnings.size() == 0)
        {
            writeLine(Messages.getString("Progress.4"));
        } else
        {
            writeLine();
            writeLine(Messages.getString("Progress.5"));
        }
    }

    private static void usage()
    {
        String lines = Messages.getString("Usage.Lines");
        int iLines = Integer.parseInt(lines);
        for(int i = 0; i < iLines; i++)
        {
            String key = (new StringBuilder("Usage.")).append(i).toString();
            writeLine(Messages.getString(key));
        }

    }

    private static void writeLine(String message)
    {
        System.out.println(message);
    }

    private static void writeLine()
    {
        System.out.println();
    }

    private static Map parseCommandLine(String args[])
    {
        List errors = new ArrayList();
        Map arguments = new HashMap();
        for(int i = 0; i < args.length; i++)
            if("-configfile".equalsIgnoreCase(args[i]))
            {
                if(i + 1 < args.length)
                    arguments.put("-configfile", args[i + 1]);
                else
                    errors.add(Messages.getString("RuntimeError.19", "-configfile"));
                i++;
            } else
            if("-overwrite".equalsIgnoreCase(args[i]))
                arguments.put("-overwrite", "Y");
            else
            if("-verbose".equalsIgnoreCase(args[i]))
                arguments.put("-verbose", "Y");
            else
            if("-?".equalsIgnoreCase(args[i]))
                arguments.put("-?", "Y");
            else
            if("-h".equalsIgnoreCase(args[i]))
                arguments.put("-?", "Y");
            else
            if("-forceJavaLogging".equalsIgnoreCase(args[i]))
                LogFactory.forceJavaLogging();
            else
            if("-contextids".equalsIgnoreCase(args[i]))
            {
                if(i + 1 < args.length)
                    arguments.put("-contextids", args[i + 1]);
                else
                    errors.add(Messages.getString("RuntimeError.19", "-contextids"));
                i++;
            } else
            if("-tables".equalsIgnoreCase(args[i]))
            {
                if(i + 1 < args.length)
                    arguments.put("-tables", args[i + 1]);
                else
                    errors.add(Messages.getString("RuntimeError.19", "-tables"));
                i++;
            } else
            {
                errors.add(Messages.getString("RuntimeError.20", args[i]));
            }

        if(!errors.isEmpty())
        {
            String error;
            for(Iterator iterator = errors.iterator(); iterator.hasNext(); writeLine(error))
                error = (String)iterator.next();

            System.exit(-1);
        }
        return arguments;
    }

    private static final String CONFIG_FILE = "-configfile";
    private static final String OVERWRITE = "-overwrite";
    private static final String CONTEXT_IDS = "-contextids";
    private static final String TABLES = "-tables";
    private static final String VERBOSE = "-verbose";
    private static final String FORCE_JAVA_LOGGING = "-forceJavaLogging";
    private static final String HELP_1 = "-?";
    private static final String HELP_2 = "-h";
}
