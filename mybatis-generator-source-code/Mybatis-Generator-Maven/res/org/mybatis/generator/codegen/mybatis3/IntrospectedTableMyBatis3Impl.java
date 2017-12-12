// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntrospectedTableMyBatis3Impl.java

package org.mybatis.generator.codegen.mybatis3;

import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.codegen.*;
import org.mybatis.generator.codegen.mybatis3.javamapper.JavaMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.model.BaseRecordGenerator;
import org.mybatis.generator.codegen.mybatis3.model.ExampleGenerator;
import org.mybatis.generator.codegen.mybatis3.model.PrimaryKeyGenerator;
import org.mybatis.generator.codegen.mybatis3.model.RecordWithBLOBsGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.rules.Rules;

public class IntrospectedTableMyBatis3Impl extends IntrospectedTable
{

    public IntrospectedTableMyBatis3Impl()
    {
        super(org.mybatis.generator.api.IntrospectedTable.TargetRuntime.MYBATIS3);
        javaModelGenerators = new ArrayList();
        daoGenerators = new ArrayList();
    }

    public void calculateGenerators(List warnings, ProgressCallback progressCallback)
    {
        calculateJavaModelGenerators(warnings, progressCallback);
        calculateDAOGenerators(warnings, progressCallback);
        calculateXmlMapperGenerator(warnings, progressCallback);
    }

    protected void calculateXmlMapperGenerator(List warnings, ProgressCallback progressCallback)
    {
        xmlMapperGenerator = new XMLMapperGenerator();
        initializeAbstractGenerator(xmlMapperGenerator, warnings, progressCallback);
    }

    protected void calculateDAOGenerators(List warnings, ProgressCallback progressCallback)
    {
        if(context.getJavaClientGeneratorConfiguration() == null)
            return;
        String type = context.getJavaClientGeneratorConfiguration().getConfigurationType();
        AbstractJavaGenerator javaGenerator;
        if("XMLMAPPER".equalsIgnoreCase(type))
            javaGenerator = new JavaMapperGenerator();
        else
        if("MAPPER".equalsIgnoreCase(type))
            javaGenerator = new JavaMapperGenerator();
        else
            javaGenerator = (AbstractJavaGenerator)ObjectFactory.createInternalObject(type);
        initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
        daoGenerators.add(javaGenerator);
    }

    protected void calculateJavaModelGenerators(List warnings, ProgressCallback progressCallback)
    {
        if(getRules().generateExampleClass())
        {
            AbstractJavaGenerator javaGenerator = new ExampleGenerator();
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
        Document document = xmlMapperGenerator.getDocument();
        GeneratedXmlFile gxf = new GeneratedXmlFile(document, getMyBatis3XmlMapperFileName(), getMyBatis3XmlMapperPackage(), context.getSqlMapGeneratorConfiguration().getTargetProject(), true);
        if(context.getPlugins().sqlMapGenerated(gxf, this))
            answer.add(gxf);
        return answer;
    }

    public int getGenerationSteps()
    {
        return javaModelGenerators.size() + daoGenerators.size() + 1;
    }

    public boolean isJava5Targeted()
    {
        return true;
    }

    protected List javaModelGenerators;
    protected List daoGenerators;
    protected AbstractXmlGenerator xmlMapperGenerator;
}
