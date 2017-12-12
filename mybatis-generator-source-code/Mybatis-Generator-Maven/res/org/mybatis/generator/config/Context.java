// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Context.java

package org.mybatis.generator.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.PluginAggregator;
import org.mybatis.generator.internal.db.ConnectionFactory;
import org.mybatis.generator.internal.db.DatabaseIntrospector;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

// Referenced classes of package org.mybatis.generator.config:
//            PropertyHolder, ModelType, JDBCConnectionConfiguration, JavaModelGeneratorConfiguration, 
//            SqlMapGeneratorConfiguration, JavaClientGeneratorConfiguration, TableConfiguration, PluginConfiguration, 
//            CommentGeneratorConfiguration, JavaTypeResolverConfiguration

public class Context extends PropertyHolder
{

    public Context(ModelType defaultModelType)
    {
        beginningDelimiter = "\"";
        endingDelimiter = "\"";
        if(defaultModelType == null)
            this.defaultModelType = ModelType.CONDITIONAL;
        else
            this.defaultModelType = defaultModelType;
        tableConfigurations = new ArrayList();
        pluginConfigurations = new ArrayList();
    }

    public void addTableConfiguration(TableConfiguration tc)
    {
        tableConfigurations.add(tc);
    }

    public JDBCConnectionConfiguration getJdbcConnectionConfiguration()
    {
        return jdbcConnectionConfiguration;
    }

    public JavaClientGeneratorConfiguration getJavaClientGeneratorConfiguration()
    {
        return javaClientGeneratorConfiguration;
    }

    public JavaModelGeneratorConfiguration getJavaModelGeneratorConfiguration()
    {
        return javaModelGeneratorConfiguration;
    }

    public JavaTypeResolverConfiguration getJavaTypeResolverConfiguration()
    {
        return javaTypeResolverConfiguration;
    }

    public SqlMapGeneratorConfiguration getSqlMapGeneratorConfiguration()
    {
        return sqlMapGeneratorConfiguration;
    }

    public void addPluginConfiguration(PluginConfiguration pluginConfiguration)
    {
        pluginConfigurations.add(pluginConfiguration);
    }

    public void validate(List errors)
    {
        if(!StringUtility.stringHasValue(id))
            errors.add(Messages.getString("ValidationError.16"));
        if(jdbcConnectionConfiguration == null)
            errors.add(Messages.getString("ValidationError.10", id));
        else
            jdbcConnectionConfiguration.validate(errors);
        if(javaModelGeneratorConfiguration == null)
            errors.add(Messages.getString("ValidationError.8", id));
        else
            javaModelGeneratorConfiguration.validate(errors, id);
        if(sqlMapGeneratorConfiguration == null)
            errors.add(Messages.getString("ValidationError.9", id));
        else
            sqlMapGeneratorConfiguration.validate(errors, id);
        if(javaClientGeneratorConfiguration != null)
            javaClientGeneratorConfiguration.validate(errors, id);
        if(tableConfigurations.size() == 0)
        {
            errors.add(Messages.getString("ValidationError.3", id));
        } else
        {
            for(int i = 0; i < tableConfigurations.size(); i++)
            {
                TableConfiguration tc = (TableConfiguration)tableConfigurations.get(i);
                tc.validate(errors, i);
            }

        }
        PluginConfiguration pluginConfiguration;
        for(Iterator iterator = pluginConfigurations.iterator(); iterator.hasNext(); pluginConfiguration.validate(errors, id))
            pluginConfiguration = (PluginConfiguration)iterator.next();

    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setJavaClientGeneratorConfiguration(JavaClientGeneratorConfiguration javaClientGeneratorConfiguration)
    {
        this.javaClientGeneratorConfiguration = javaClientGeneratorConfiguration;
    }

    public void setJavaModelGeneratorConfiguration(JavaModelGeneratorConfiguration javaModelGeneratorConfiguration)
    {
        this.javaModelGeneratorConfiguration = javaModelGeneratorConfiguration;
    }

    public void setJavaTypeResolverConfiguration(JavaTypeResolverConfiguration javaTypeResolverConfiguration)
    {
        this.javaTypeResolverConfiguration = javaTypeResolverConfiguration;
    }

    public void setJdbcConnectionConfiguration(JDBCConnectionConfiguration jdbcConnectionConfiguration)
    {
        this.jdbcConnectionConfiguration = jdbcConnectionConfiguration;
    }

    public void setSqlMapGeneratorConfiguration(SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration)
    {
        this.sqlMapGeneratorConfiguration = sqlMapGeneratorConfiguration;
    }

    public ModelType getDefaultModelType()
    {
        return defaultModelType;
    }

    public XmlElement toXmlElement()
    {
        XmlElement xmlElement = new XmlElement("context");
        if(defaultModelType != ModelType.CONDITIONAL)
            xmlElement.addAttribute(new Attribute("defaultModelType", defaultModelType.getModelType()));
        if(StringUtility.stringHasValue(introspectedColumnImpl))
            xmlElement.addAttribute(new Attribute("introspectedColumnImpl", introspectedColumnImpl));
        if(StringUtility.stringHasValue(targetRuntime))
            xmlElement.addAttribute(new Attribute("targetRuntime", targetRuntime));
        if(StringUtility.stringHasValue(id))
            xmlElement.addAttribute(new Attribute("id", id));
        addPropertyXmlElements(xmlElement);
        if(commentGeneratorConfiguration != null)
            xmlElement.addElement(commentGeneratorConfiguration.toXmlElement());
        if(jdbcConnectionConfiguration != null)
            xmlElement.addElement(jdbcConnectionConfiguration.toXmlElement());
        if(javaTypeResolverConfiguration != null)
            xmlElement.addElement(javaTypeResolverConfiguration.toXmlElement());
        if(javaModelGeneratorConfiguration != null)
            xmlElement.addElement(javaModelGeneratorConfiguration.toXmlElement());
        if(sqlMapGeneratorConfiguration != null)
            xmlElement.addElement(sqlMapGeneratorConfiguration.toXmlElement());
        if(javaClientGeneratorConfiguration != null)
            xmlElement.addElement(javaClientGeneratorConfiguration.toXmlElement());
        TableConfiguration tableConfiguration;
        for(Iterator iterator = tableConfigurations.iterator(); iterator.hasNext(); xmlElement.addElement(tableConfiguration.toXmlElement()))
            tableConfiguration = (TableConfiguration)iterator.next();

        return xmlElement;
    }

    public List getTableConfigurations()
    {
        return tableConfigurations;
    }

    public String getBeginningDelimiter()
    {
        return beginningDelimiter;
    }

    public String getEndingDelimiter()
    {
        return endingDelimiter;
    }

    public void addProperty(String name, String value)
    {
        super.addProperty(name, value);
        if("beginningDelimiter".equals(name))
            beginningDelimiter = value;
        else
        if("endingDelimiter".equals(name))
            endingDelimiter = value;
        else
        if("autoDelimitKeywords".equals(name) && StringUtility.stringHasValue(value))
            autoDelimitKeywords = new Boolean(StringUtility.isTrue(value));
    }

    public CommentGenerator getCommentGenerator()
    {
        if(commentGenerator == null)
            commentGenerator = ObjectFactory.createCommentGenerator(this);
        return commentGenerator;
    }

    public CommentGeneratorConfiguration getCommentGeneratorConfiguration()
    {
        return commentGeneratorConfiguration;
    }

    public void setCommentGeneratorConfiguration(CommentGeneratorConfiguration commentGeneratorConfiguration)
    {
        this.commentGeneratorConfiguration = commentGeneratorConfiguration;
    }

    public Plugin getPlugins()
    {
        return pluginAggregator;
    }

    public String getTargetRuntime()
    {
        return targetRuntime;
    }

    public void setTargetRuntime(String targetRuntime)
    {
        this.targetRuntime = targetRuntime;
    }

    public String getIntrospectedColumnImpl()
    {
        return introspectedColumnImpl;
    }

    public void setIntrospectedColumnImpl(String introspectedColumnImpl)
    {
        this.introspectedColumnImpl = introspectedColumnImpl;
    }

    public int getIntrospectionSteps()
    {
        int steps = 0;
        steps = ++steps + tableConfigurations.size() * 1;
        return steps;
    }

    public void introspectTables(ProgressCallback callback, List warnings, Set fullyQualifiedTableNames)
        throws SQLException, InterruptedException
    {
        JavaTypeResolver javaTypeResolver;
        Connection connection;
        introspectedTables = new ArrayList();
        javaTypeResolver = ObjectFactory.createJavaTypeResolver(this, warnings);
        connection = null;
        callback.startTask(Messages.getString("Progress.0"));
        connection = getConnection();
        DatabaseIntrospector databaseIntrospector = new DatabaseIntrospector(this, connection.getMetaData(), javaTypeResolver, warnings);
        for(Iterator iterator = tableConfigurations.iterator(); iterator.hasNext();)
        {
            TableConfiguration tc = (TableConfiguration)iterator.next();
            String tableName = StringUtility.composeFullyQualifiedTableName(tc.getCatalog(), tc.getSchema(), tc.getTableName(), '.');
            if(fullyQualifiedTableNames == null || fullyQualifiedTableNames.size() <= 0 || fullyQualifiedTableNames.contains(tableName))
                if(!tc.areAnyStatementsEnabled())
                {
                    warnings.add(Messages.getString("Warning.0", tableName));
                } else
                {
                    callback.startTask(Messages.getString("Progress.1", tableName));
                    List tables = databaseIntrospector.introspectTables(tc);
                    if(tables != null)
                        introspectedTables.addAll(tables);
                    callback.checkCancel();
                }
        }

        break MISSING_BLOCK_LABEL_228;
        Exception exception;
        exception;
        closeConnection(connection);
        throw exception;
        closeConnection(connection);
        return;
    }

    public int getGenerationSteps()
    {
        int steps = 0;
        if(introspectedTables != null)
        {
            for(Iterator iterator = introspectedTables.iterator(); iterator.hasNext();)
            {
                IntrospectedTable introspectedTable = (IntrospectedTable)iterator.next();
                steps += introspectedTable.getGenerationSteps();
            }

        }
        return steps;
    }

    public void generateFiles(ProgressCallback callback, List generatedJavaFiles, List generatedXmlFiles, List warnings)
        throws InterruptedException
    {
        pluginAggregator = new PluginAggregator();
        for(Iterator iterator = pluginConfigurations.iterator(); iterator.hasNext();)
        {
            PluginConfiguration pluginConfiguration = (PluginConfiguration)iterator.next();
            Plugin plugin = ObjectFactory.createPlugin(this, pluginConfiguration);
            if(plugin.validate(warnings))
                pluginAggregator.addPlugin(plugin);
            else
                warnings.add(Messages.getString("Warning.24", pluginConfiguration.getConfigurationType(), id));
        }

        if(introspectedTables != null)
        {
            IntrospectedTable introspectedTable;
            for(Iterator iterator1 = introspectedTables.iterator(); iterator1.hasNext(); generatedXmlFiles.addAll(pluginAggregator.contextGenerateAdditionalXmlFiles(introspectedTable)))
            {
                introspectedTable = (IntrospectedTable)iterator1.next();
                callback.checkCancel();
                introspectedTable.initialize();
                introspectedTable.calculateGenerators(warnings, callback);
                generatedJavaFiles.addAll(introspectedTable.getGeneratedJavaFiles());
                generatedXmlFiles.addAll(introspectedTable.getGeneratedXmlFiles());
                generatedJavaFiles.addAll(pluginAggregator.contextGenerateAdditionalJavaFiles(introspectedTable));
            }

        }
        generatedJavaFiles.addAll(pluginAggregator.contextGenerateAdditionalJavaFiles());
        generatedXmlFiles.addAll(pluginAggregator.contextGenerateAdditionalXmlFiles());
    }

    private Connection getConnection()
        throws SQLException
    {
        Connection connection = ConnectionFactory.getInstance().getConnection(jdbcConnectionConfiguration);
        return connection;
    }

    private void closeConnection(Connection connection)
    {
        if(connection != null)
            try
            {
                connection.close();
            }
            catch(SQLException sqlexception) { }
    }

    public boolean autoDelimitKeywords()
    {
        return autoDelimitKeywords != null && autoDelimitKeywords.booleanValue();
    }

    public void cleanTableConfig()
    {
        tableConfigurations = new ArrayList();
    }

    private String id;
    private JDBCConnectionConfiguration jdbcConnectionConfiguration;
    private SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration;
    private JavaTypeResolverConfiguration javaTypeResolverConfiguration;
    private JavaModelGeneratorConfiguration javaModelGeneratorConfiguration;
    private JavaClientGeneratorConfiguration javaClientGeneratorConfiguration;
    private ArrayList tableConfigurations;
    private ModelType defaultModelType;
    private String beginningDelimiter;
    private String endingDelimiter;
    private CommentGeneratorConfiguration commentGeneratorConfiguration;
    private CommentGenerator commentGenerator;
    private PluginAggregator pluginAggregator;
    private List pluginConfigurations;
    private String targetRuntime;
    private String introspectedColumnImpl;
    private Boolean autoDelimitKeywords;
    private List introspectedTables;
}
