// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntrospectedTableIbatis2Java2Impl.java

package org.mybatis.generator.codegen.ibatis2;

import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.codegen.*;
import org.mybatis.generator.codegen.ibatis2.dao.DAOGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.templates.GenericCIDAOTemplate;
import org.mybatis.generator.codegen.ibatis2.dao.templates.GenericSIDAOTemplate;
import org.mybatis.generator.codegen.ibatis2.dao.templates.IbatisDAOTemplate;
import org.mybatis.generator.codegen.ibatis2.dao.templates.SpringDAOTemplate;
import org.mybatis.generator.codegen.ibatis2.model.BaseRecordGenerator;
import org.mybatis.generator.codegen.ibatis2.model.ExampleGenerator;
import org.mybatis.generator.codegen.ibatis2.model.PrimaryKeyGenerator;
import org.mybatis.generator.codegen.ibatis2.model.RecordWithBLOBsGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.SqlMapGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.rules.Rules;

public class IntrospectedTableIbatis2Java2Impl extends IntrospectedTable
{

    public IntrospectedTableIbatis2Java2Impl()
    {
        super(org.mybatis.generator.api.IntrospectedTable.TargetRuntime.IBATIS2);
        javaModelGenerators = new ArrayList();
        daoGenerators = new ArrayList();
    }

    public void calculateGenerators(List warnings, ProgressCallback progressCallback)
    {
        calculateJavaModelGenerators(warnings, progressCallback);
        calculateDAOGenerators(warnings, progressCallback);
        calculateSqlMapGenerator(warnings, progressCallback);
    }

    protected void calculateSqlMapGenerator(List warnings, ProgressCallback progressCallback)
    {
        sqlMapGenerator = new SqlMapGenerator();
        initializeAbstractGenerator(sqlMapGenerator, warnings, progressCallback);
    }

    protected void calculateDAOGenerators(List warnings, ProgressCallback progressCallback)
    {
        if(context.getJavaClientGeneratorConfiguration() == null)
            return;
        String type = context.getJavaClientGeneratorConfiguration().getConfigurationType();
        AbstractJavaGenerator javaGenerator;
        if("IBATIS".equalsIgnoreCase(type))
            javaGenerator = new DAOGenerator(new IbatisDAOTemplate(), isJava5Targeted());
        else
        if("SPRING".equalsIgnoreCase(type))
            javaGenerator = new DAOGenerator(new SpringDAOTemplate(), isJava5Targeted());
        else
        if("GENERIC-CI".equalsIgnoreCase(type))
            javaGenerator = new DAOGenerator(new GenericCIDAOTemplate(), isJava5Targeted());
        else
        if("GENERIC-SI".equalsIgnoreCase(type))
            javaGenerator = new DAOGenerator(new GenericSIDAOTemplate(), isJava5Targeted());
        else
            javaGenerator = (AbstractJavaGenerator)ObjectFactory.createInternalObject(type);
        initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
        daoGenerators.add(javaGenerator);
    }

    protected void calculateJavaModelGenerators(List warnings, ProgressCallback progressCallback)
    {
        if(getRules().generateExampleClass())
        {
            AbstractJavaGenerator javaGenerator = new ExampleGenerator(isJava5Targeted());
            initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
            javaModelGenerators.add(javaGenerator);
        }
        if(getRules().generatePrimaryKeyClass())
        {
            AbstractJavaGenerator javaGenerator = new PrimaryKeyGenerator();
            initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
            javaModelGenerators.add(javaGenerator);
        }
        if(getRules().generateBaseRecordClass())
        {
            AbstractJavaGenerator javaGenerator = new BaseRecordGenerator();
            initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
            javaModelGenerators.add(javaGenerator);
        }
        if(getRules().generateRecordWithBLOBsClass())
        {
            AbstractJavaGenerator javaGenerator = new RecordWithBLOBsGenerator();
            initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
            javaModelGenerators.add(javaGenerator);
        }
    }

    protected void initializeAbstractGenerator(AbstractGenerator abstractGenerator, List warnings, ProgressCallback progressCallback)
    {
        abstractGenerator.setContext(context);
        abstractGenerator.setIntrospectedTable(this);
        abstractGenerator.setProgressCallback(progressCallback);
        abstractGenerator.setWarnings(warnings);
    }

    public List getGeneratedJavaFiles()
    {
        List answer = new ArrayList();
        for(Iterator iterator = javaModelGenerators.iterator(); iterator.hasNext();)
        {
            AbstractJavaGenerator javaGenerator = (AbstractJavaGenerator)iterator.next();
            List compilationUnits = javaGenerator.getCompilationUnits();
            GeneratedJavaFile gjf;
            for(Iterator iterator2 = compilationUnits.iterator(); iterator2.hasNext(); answer.add(gjf))
            {
                CompilationUnit compilationUnit = (CompilationUnit)iterator2.next();
                gjf = new GeneratedJavaFile(compilationUnit, context.getJavaModelGeneratorConfiguration().getTargetProject());
            }

        }

        for(Iterator iterator1 = daoGenerators.iterator(); iterator1.hasNext();)
        {
            AbstractJavaGenerator javaGenerator = (AbstractJavaGenerator)iterator1.next();
            List compilationUnits = javaGenerator.getCompilationUnits();
            GeneratedJavaFile gjf;
            for(Iterator iterator3 = compilationUnits.iterator(); iterator3.hasNext(); answer.add(gjf))
            {
                CompilationUnit compilationUnit = (CompilationUnit)iterator3.next();
                gjf = new GeneratedJavaFile(compilationUnit, context.getJavaClientGeneratorConfiguration().getTargetProject());
            }

        }

        return answer;
    }

    public List getGeneratedXmlFiles()
    {
        List answer = new ArrayList();
        Document document = sqlMapGenerator.getDocument();
        GeneratedXmlFile gxf = new GeneratedXmlFile(document, getIbatis2SqlMapFileName(), getIbatis2SqlMapPackage(), context.getSqlMapGeneratorConfiguration().getTargetProject(), true);
        if(context.getPlugins().sqlMapGenerated(gxf, this))
            answer.add(gxf);
        return answer;
    }

    public boolean isJava5Targeted()
    {
        return false;
    }

    public int getGenerationSteps()
    {
        return javaModelGenerators.size() + daoGenerators.size() + 1;
    }

    protected List javaModelGenerators;
    protected List daoGenerators;
    protected AbstractXmlGenerator sqlMapGenerator;
}
