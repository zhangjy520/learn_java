// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractDAOTemplate.java

package org.mybatis.generator.codegen.ibatis2.dao.templates;

import java.text.MessageFormat;
import java.util.*;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;

public abstract class AbstractDAOTemplate
{

    public AbstractDAOTemplate()
    {
        interfaceImports = new ArrayList();
        implementationImports = new ArrayList();
        fields = new ArrayList();
        methods = new ArrayList();
        checkedExceptions = new ArrayList();
        configured = false;
    }

    public final Method getConstructorClone(CommentGenerator commentGenerator, FullyQualifiedJavaType type, IntrospectedTable introspectedTable)
    {
        configure();
        Method answer = new Method();
        answer.setConstructor(true);
        answer.setName(type.getShortName());
        answer.setVisibility(constructorTemplate.getVisibility());
        Parameter parm;
        for(Iterator iterator = constructorTemplate.getParameters().iterator(); iterator.hasNext(); answer.addParameter(parm))
            parm = (Parameter)iterator.next();

        String bodyLine;
        for(Iterator iterator1 = constructorTemplate.getBodyLines().iterator(); iterator1.hasNext(); answer.addBodyLine(bodyLine))
            bodyLine = (String)iterator1.next();

        FullyQualifiedJavaType fqjt;
        for(Iterator iterator2 = constructorTemplate.getExceptions().iterator(); iterator2.hasNext(); answer.addException(fqjt))
            fqjt = (FullyQualifiedJavaType)iterator2.next();

        commentGenerator.addGeneralMethodComment(answer, introspectedTable);
        return answer;
    }

    public final String getDeleteMethod(String sqlMapNamespace, String statementId, String parameter)
    {
        configure();
        String answer = MessageFormat.format(deleteMethodTemplate, new Object[] {
            sqlMapNamespace, statementId, parameter
        });
        return answer;
    }

    public final List getInterfaceImports()
    {
        configure();
        return interfaceImports;
    }

    public final List getImplementationImports()
    {
        configure();
        return implementationImports;
    }

    public final String getInsertMethod(String sqlMapNamespace, String statementId, String parameter)
    {
        configure();
        String answer = MessageFormat.format(insertMethodTemplate, new Object[] {
            sqlMapNamespace, statementId, parameter
        });
        return answer;
    }

    public final String getQueryForListMethod(String sqlMapNamespace, String statementId, String parameter)
    {
        configure();
        String answer = MessageFormat.format(queryForListMethodTemplate, new Object[] {
            sqlMapNamespace, statementId, parameter
        });
        return answer;
    }

    public final String getQueryForObjectMethod(String sqlMapNamespace, String statementId, String parameter)
    {
        configure();
        String answer = MessageFormat.format(queryForObjectMethodTemplate, new Object[] {
            sqlMapNamespace, statementId, parameter
        });
        return answer;
    }

    public final FullyQualifiedJavaType getSuperClass()
    {
        configure();
        return superClass;
    }

    public final String getUpdateMethod(String sqlMapNamespace, String statementId, String parameter)
    {
        configure();
        String answer = MessageFormat.format(updateMethodTemplate, new Object[] {
            sqlMapNamespace, statementId, parameter
        });
        return answer;
    }

    public final List getCheckedExceptions()
    {
        configure();
        return checkedExceptions;
    }

    public final List getFieldClones(CommentGenerator commentGenerator, IntrospectedTable introspectedTable)
    {
        configure();
        List answer = new ArrayList();
        Field field;
        for(Iterator iterator = fields.iterator(); iterator.hasNext(); answer.add(field))
        {
            Field oldField = (Field)iterator.next();
            field = new Field();
            field.setInitializationString(oldField.getInitializationString());
            field.setFinal(oldField.isFinal());
            field.setStatic(oldField.isStatic());
            field.setName(oldField.getName());
            field.setType(oldField.getType());
            field.setVisibility(oldField.getVisibility());
            commentGenerator.addFieldComment(field, introspectedTable);
        }

        return answer;
    }

    public final List getMethodClones(CommentGenerator commentGenerator, IntrospectedTable introspectedTable)
    {
        configure();
        List answer = new ArrayList();
        Method method;
        for(Iterator iterator = methods.iterator(); iterator.hasNext(); answer.add(method))
        {
            Method oldMethod = (Method)iterator.next();
            method = new Method();
            String bodyLine;
            for(Iterator iterator1 = oldMethod.getBodyLines().iterator(); iterator1.hasNext(); method.addBodyLine(bodyLine))
                bodyLine = (String)iterator1.next();

            FullyQualifiedJavaType fqjt;
            for(Iterator iterator2 = oldMethod.getExceptions().iterator(); iterator2.hasNext(); method.addException(fqjt))
                fqjt = (FullyQualifiedJavaType)iterator2.next();

            Parameter parm;
            for(Iterator iterator3 = oldMethod.getParameters().iterator(); iterator3.hasNext(); method.addParameter(parm))
                parm = (Parameter)iterator3.next();

            method.setConstructor(oldMethod.isConstructor());
            method.setFinal(oldMethod.isFinal());
            method.setStatic(oldMethod.isStatic());
            method.setName(oldMethod.getName());
            method.setReturnType(oldMethod.getReturnType());
            method.setVisibility(oldMethod.getVisibility());
            commentGenerator.addGeneralMethodComment(method, introspectedTable);
        }

        return answer;
    }

    protected void setConstructorTemplate(Method constructorTemplate)
    {
        this.constructorTemplate = constructorTemplate;
    }

    protected void setDeleteMethodTemplate(String deleteMethodTemplate)
    {
        this.deleteMethodTemplate = deleteMethodTemplate;
    }

    protected void addField(Field field)
    {
        fields.add(field);
    }

    protected void setInsertMethodTemplate(String insertMethodTemplate)
    {
        this.insertMethodTemplate = insertMethodTemplate;
    }

    protected void addMethod(Method method)
    {
        methods.add(method);
    }

    protected void setQueryForListMethodTemplate(String queryForListMethodTemplate)
    {
        this.queryForListMethodTemplate = queryForListMethodTemplate;
    }

    protected void setQueryForObjectMethodTemplate(String queryForObjectMethodTemplate)
    {
        this.queryForObjectMethodTemplate = queryForObjectMethodTemplate;
    }

    protected void setSuperClass(FullyQualifiedJavaType superClass)
    {
        this.superClass = superClass;
    }

    protected void setUpdateMethodTemplate(String updateMethodTemplate)
    {
        this.updateMethodTemplate = updateMethodTemplate;
    }

    protected void addInterfaceImport(FullyQualifiedJavaType type)
    {
        interfaceImports.add(type);
    }

    protected void addImplementationImport(FullyQualifiedJavaType type)
    {
        implementationImports.add(type);
    }

    protected void addCheckedException(FullyQualifiedJavaType type)
    {
        checkedExceptions.add(type);
    }

    private void configure()
    {
        if(!configured)
        {
            configureCheckedExceptions();
            configureConstructorTemplate();
            configureDeleteMethodTemplate();
            configureFields();
            configureImplementationImports();
            configureInsertMethodTemplate();
            configureInterfaceImports();
            configureMethods();
            configureQueryForListMethodTemplate();
            configureQueryForObjectMethodTemplate();
            configureSuperClass();
            configureUpdateMethodTemplate();
            configured = true;
        }
    }

    protected void configureCheckedExceptions()
    {
    }

    protected void configureFields()
    {
    }

    protected void configureImplementationImports()
    {
    }

    protected void configureInterfaceImports()
    {
    }

    protected void configureMethods()
    {
    }

    protected void configureSuperClass()
    {
    }

    protected abstract void configureConstructorTemplate();

    protected abstract void configureInsertMethodTemplate();

    protected abstract void configureQueryForListMethodTemplate();

    protected abstract void configureQueryForObjectMethodTemplate();

    protected abstract void configureUpdateMethodTemplate();

    protected abstract void configureDeleteMethodTemplate();

    private List interfaceImports;
    private List implementationImports;
    private FullyQualifiedJavaType superClass;
    private List checkedExceptions;
    private List fields;
    private List methods;
    private Method constructorTemplate;
    private String deleteMethodTemplate;
    private String insertMethodTemplate;
    private String updateMethodTemplate;
    private String queryForObjectMethodTemplate;
    private String queryForListMethodTemplate;
    private boolean configured;
}
