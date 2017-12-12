// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractDAOElementGenerator.java

package org.mybatis.generator.codegen.ibatis2.dao.elements;

import java.util.List;
import org.mybatis.generator.api.DAOMethodNameCalculator;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractGenerator;
import org.mybatis.generator.codegen.ibatis2.dao.templates.AbstractDAOTemplate;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.internal.*;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

public abstract class AbstractDAOElementGenerator extends AbstractGenerator
{

    public abstract void addInterfaceElements(Interface interface1);

    public abstract void addImplementationElements(TopLevelClass toplevelclass);

    public AbstractDAOElementGenerator()
    {
    }

    public void setDAOTemplate(AbstractDAOTemplate abstractDAOTemplate)
    {
        daoTemplate = abstractDAOTemplate;
    }

    public DAOMethodNameCalculator getDAOMethodNameCalculator()
    {
        if(dAOMethodNameCalculator == null)
        {
            String type = context.getJavaClientGeneratorConfiguration().getProperty("methodNameCalculator");
            if(StringUtility.stringHasValue(type))
            {
                if("extended".equalsIgnoreCase(type))
                    type = org/mybatis/generator/internal/ExtendedDAOMethodNameCalculator.getName();
                else
                if("default".equalsIgnoreCase(type))
                    type = org/mybatis/generator/internal/DefaultDAOMethodNameCalculator.getName();
            } else
            {
                type = org/mybatis/generator/internal/DefaultDAOMethodNameCalculator.getName();
            }
            try
            {
                dAOMethodNameCalculator = (DAOMethodNameCalculator)ObjectFactory.createInternalObject(type);
            }
            catch(Exception e)
            {
                dAOMethodNameCalculator = new DefaultDAOMethodNameCalculator();
                warnings.add(Messages.getString("Warning.17", type, e.getMessage()));
            }
        }
        return dAOMethodNameCalculator;
    }

    public JavaVisibility getExampleMethodVisibility()
    {
        if(exampleMethodVisibility == null)
        {
            String type = context.getJavaClientGeneratorConfiguration().getProperty("exampleMethodVisibility");
            if(StringUtility.stringHasValue(type))
            {
                if("public".equalsIgnoreCase(type))
                    exampleMethodVisibility = JavaVisibility.PUBLIC;
                else
                if("private".equalsIgnoreCase(type))
                    exampleMethodVisibility = JavaVisibility.PRIVATE;
                else
                if("protected".equalsIgnoreCase(type))
                    exampleMethodVisibility = JavaVisibility.PROTECTED;
                else
                if("default".equalsIgnoreCase(type))
                {
                    exampleMethodVisibility = JavaVisibility.DEFAULT;
                } else
                {
                    exampleMethodVisibility = JavaVisibility.PUBLIC;
                    warnings.add(Messages.getString("Warning.16", type));
                }
            } else
            {
                exampleMethodVisibility = JavaVisibility.PUBLIC;
            }
        }
        return exampleMethodVisibility;
    }

    protected AbstractDAOTemplate daoTemplate;
    private DAOMethodNameCalculator dAOMethodNameCalculator;
    private JavaVisibility exampleMethodVisibility;
}
