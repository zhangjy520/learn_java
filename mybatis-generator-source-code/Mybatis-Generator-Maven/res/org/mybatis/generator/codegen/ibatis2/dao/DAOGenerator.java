// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DAOGenerator.java

package org.mybatis.generator.codegen.ibatis2.dao;

import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.elements.AbstractDAOElementGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.elements.CountByExampleMethodGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.elements.DeleteByExampleMethodGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.elements.DeleteByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.elements.InsertMethodGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.elements.InsertSelectiveMethodGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.elements.SelectByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.elements.SelectByExampleWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.elements.SelectByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.elements.UpdateByExampleParmsInnerclassGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.elements.UpdateByExampleSelectiveMethodGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.elements.UpdateByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.elements.UpdateByExampleWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.elements.UpdateByPrimaryKeySelectiveMethodGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.elements.UpdateByPrimaryKeyWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.elements.UpdateByPrimaryKeyWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.templates.AbstractDAOTemplate;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

public class DAOGenerator extends AbstractJavaGenerator
{

    public DAOGenerator(AbstractDAOTemplate daoTemplate, boolean generateForJava5)
    {
        this.daoTemplate = daoTemplate;
        this.generateForJava5 = generateForJava5;
    }

    public List getCompilationUnits()
    {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(Messages.getString("Progress.14", table.toString()));
        TopLevelClass topLevelClass = getTopLevelClassShell();
        Interface interfaze = getInterfaceShell();
        addCountByExampleMethod(topLevelClass, interfaze);
        addDeleteByExampleMethod(topLevelClass, interfaze);
        addDeleteByPrimaryKeyMethod(topLevelClass, interfaze);
        addInsertMethod(topLevelClass, interfaze);
        addInsertSelectiveMethod(topLevelClass, interfaze);
        addSelectByExampleWithBLOBsMethod(topLevelClass, interfaze);
        addSelectByExampleWithoutBLOBsMethod(topLevelClass, interfaze);
        addSelectByPrimaryKeyMethod(topLevelClass, interfaze);
        addUpdateByExampleParmsInnerclass(topLevelClass, interfaze);
        addUpdateByExampleSelectiveMethod(topLevelClass, interfaze);
        addUpdateByExampleWithBLOBsMethod(topLevelClass, interfaze);
        addUpdateByExampleWithoutBLOBsMethod(topLevelClass, interfaze);
        addUpdateByPrimaryKeySelectiveMethod(topLevelClass, interfaze);
        addUpdateByPrimaryKeyWithBLOBsMethod(topLevelClass, interfaze);
        addUpdateByPrimaryKeyWithoutBLOBsMethod(topLevelClass, interfaze);
        List answer = new ArrayList();
        if(context.getPlugins().clientGenerated(interfaze, topLevelClass, introspectedTable))
        {
            answer.add(topLevelClass);
            answer.add(interfaze);
        }
        return answer;
    }

    protected TopLevelClass getTopLevelClassShell()
    {
        FullyQualifiedJavaType interfaceType = new FullyQualifiedJavaType(introspectedTable.getDAOInterfaceType());
        FullyQualifiedJavaType implementationType = new FullyQualifiedJavaType(introspectedTable.getDAOImplementationType());
        CommentGenerator commentGenerator = context.getCommentGenerator();
        TopLevelClass answer = new TopLevelClass(implementationType);
        answer.setVisibility(JavaVisibility.PUBLIC);
        answer.setSuperClass(daoTemplate.getSuperClass());
        answer.addImportedType(daoTemplate.getSuperClass());
        answer.addSuperInterface(interfaceType);
        answer.addImportedType(interfaceType);
        FullyQualifiedJavaType fqjt;
        for(Iterator iterator = daoTemplate.getImplementationImports().iterator(); iterator.hasNext(); answer.addImportedType(fqjt))
            fqjt = (FullyQualifiedJavaType)iterator.next();

        commentGenerator.addJavaFileComment(answer);
        answer.addMethod(daoTemplate.getConstructorClone(commentGenerator, implementationType, introspectedTable));
        Field field;
        for(Iterator iterator1 = daoTemplate.getFieldClones(commentGenerator, introspectedTable).iterator(); iterator1.hasNext(); answer.addField(field))
            field = (Field)iterator1.next();

        Method method;
        for(Iterator iterator2 = daoTemplate.getMethodClones(commentGenerator, introspectedTable).iterator(); iterator2.hasNext(); answer.addMethod(method))
            method = (Method)iterator2.next();

        return answer;
    }

    protected Interface getInterfaceShell()
    {
        Interface answer = new Interface(new FullyQualifiedJavaType(introspectedTable.getDAOInterfaceType()));
        answer.setVisibility(JavaVisibility.PUBLIC);
        String rootInterface = introspectedTable.getTableConfigurationProperty("rootInterface");
        if(rootInterface == null)
            rootInterface = context.getJavaClientGeneratorConfiguration().getProperty("rootInterface");
        if(StringUtility.stringHasValue(rootInterface))
        {
            FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(rootInterface);
            answer.addSuperInterface(fqjt);
            answer.addImportedType(fqjt);
        }
        FullyQualifiedJavaType fqjt;
        for(Iterator iterator = daoTemplate.getInterfaceImports().iterator(); iterator.hasNext(); answer.addImportedType(fqjt))
            fqjt = (FullyQualifiedJavaType)iterator.next();

        context.getCommentGenerator().addJavaFileComment(answer);
        return answer;
    }

    protected void addCountByExampleMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if(introspectedTable.getRules().generateCountByExample())
        {
            AbstractDAOElementGenerator methodGenerator = new CountByExampleMethodGenerator(generateForJava5);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }

    protected void addDeleteByExampleMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if(introspectedTable.getRules().generateDeleteByExample())
        {
            AbstractDAOElementGenerator methodGenerator = new DeleteByExampleMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }

    protected void addDeleteByPrimaryKeyMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if(introspectedTable.getRules().generateDeleteByPrimaryKey())
        {
            AbstractDAOElementGenerator methodGenerator = new DeleteByPrimaryKeyMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }

    protected void addInsertMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if(introspectedTable.getRules().generateInsert())
        {
            AbstractDAOElementGenerator methodGenerator = new InsertMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }

    protected void addInsertSelectiveMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if(introspectedTable.getRules().generateInsertSelective())
        {
            AbstractDAOElementGenerator methodGenerator = new InsertSelectiveMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }

    protected void addSelectByExampleWithBLOBsMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if(introspectedTable.getRules().generateSelectByExampleWithBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new SelectByExampleWithBLOBsMethodGenerator(generateForJava5);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }

    protected void addSelectByExampleWithoutBLOBsMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if(introspectedTable.getRules().generateSelectByExampleWithoutBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new SelectByExampleWithoutBLOBsMethodGenerator(generateForJava5);
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }

    protected void addSelectByPrimaryKeyMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if(introspectedTable.getRules().generateSelectByPrimaryKey())
        {
            AbstractDAOElementGenerator methodGenerator = new SelectByPrimaryKeyMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }

    protected void addUpdateByExampleParmsInnerclass(TopLevelClass topLevelClass, Interface interfaze)
    {
        Rules rules = introspectedTable.getRules();
        if(rules.generateUpdateByExampleSelective() || rules.generateUpdateByExampleWithBLOBs() || rules.generateUpdateByExampleWithoutBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByExampleParmsInnerclassGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }

    protected void addUpdateByExampleSelectiveMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if(introspectedTable.getRules().generateUpdateByExampleSelective())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByExampleSelectiveMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }

    protected void addUpdateByExampleWithBLOBsMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if(introspectedTable.getRules().generateUpdateByExampleWithBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByExampleWithBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }

    protected void addUpdateByExampleWithoutBLOBsMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if(introspectedTable.getRules().generateUpdateByExampleWithoutBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByExampleWithoutBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }

    protected void addUpdateByPrimaryKeySelectiveMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if(introspectedTable.getRules().generateUpdateByPrimaryKeySelective())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByPrimaryKeySelectiveMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }

    protected void addUpdateByPrimaryKeyWithBLOBsMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if(introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByPrimaryKeyWithBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }

    protected void addUpdateByPrimaryKeyWithoutBLOBsMethod(TopLevelClass topLevelClass, Interface interfaze)
    {
        if(introspectedTable.getRules().generateUpdateByPrimaryKeyWithoutBLOBs())
        {
            AbstractDAOElementGenerator methodGenerator = new UpdateByPrimaryKeyWithoutBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass, interfaze);
        }
    }

    protected void initializeAndExecuteGenerator(AbstractDAOElementGenerator methodGenerator, TopLevelClass topLevelClass, Interface interfaze)
    {
        methodGenerator.setDAOTemplate(daoTemplate);
        methodGenerator.setContext(context);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.setProgressCallback(progressCallback);
        methodGenerator.setWarnings(warnings);
        methodGenerator.addImplementationElements(topLevelClass);
        methodGenerator.addInterfaceElements(interfaze);
    }

    private AbstractDAOTemplate daoTemplate;
    private boolean generateForJava5;
}
