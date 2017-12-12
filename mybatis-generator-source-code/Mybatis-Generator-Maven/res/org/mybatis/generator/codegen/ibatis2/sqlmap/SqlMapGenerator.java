// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SqlMapGenerator.java

package org.mybatis.generator.codegen.ibatis2.sqlmap;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.BaseColumnListElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.BlobColumnListElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.CountByExampleElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.DeleteByExampleElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.DeleteByPrimaryKeyElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.ExampleWhereClauseElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.InsertElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.InsertSelectiveElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.ResultMapWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.ResultMapWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.SelectByExampleWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.SelectByExampleWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.SelectByPrimaryKeyElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.UpdateByExampleSelectiveElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.UpdateByExampleWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.UpdateByExampleWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.UpdateByPrimaryKeySelectiveElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.UpdateByPrimaryKeyWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.UpdateByPrimaryKeyWithoutBLOBsElementGenerator;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.messages.Messages;

public class SqlMapGenerator extends AbstractXmlGenerator
{

    public SqlMapGenerator()
    {
    }

    protected XmlElement getSqlMapElement()
    {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(Messages.getString("Progress.12", table.toString()));
        XmlElement answer = new XmlElement("sqlMap");
        answer.addAttribute(new Attribute("namespace", introspectedTable.getIbatis2SqlMapNamespace()));
        context.getCommentGenerator().addRootComment(answer);
        addResultMapWithoutBLOBsElement(answer);
        addResultMapWithBLOBsElement(answer);
        addExampleWhereClauseElement(answer);
        addBaseColumnListElement(answer);
        addBlobColumnListElement(answer);
        addSelectByExampleWithBLOBsElement(answer);
        addSelectByExampleWithoutBLOBsElement(answer);
        addSelectByPrimaryKeyElement(answer);
        addDeleteByPrimaryKeyElement(answer);
        addDeleteByExampleElement(answer);
        addInsertElement(answer);
        addInsertSelectiveElement(answer);
        addCountByExampleElement(answer);
        addUpdateByExampleSelectiveElement(answer);
        addUpdateByExampleWithBLOBsElement(answer);
        addUpdateByExampleWithoutBLOBsElement(answer);
        addUpdateByPrimaryKeySelectiveElement(answer);
        addUpdateByPrimaryKeyWithBLOBsElement(answer);
        addUpdateByPrimaryKeyWithoutBLOBsElement(answer);
        return answer;
    }

    protected void addResultMapWithoutBLOBsElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateBaseResultMap())
        {
            AbstractXmlElementGenerator elementGenerator = new ResultMapWithoutBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addResultMapWithBLOBsElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateResultMapWithBLOBs())
        {
            AbstractXmlElementGenerator elementGenerator = new ResultMapWithBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addExampleWhereClauseElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateSQLExampleWhereClause())
        {
            AbstractXmlElementGenerator elementGenerator = new ExampleWhereClauseElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addBaseColumnListElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateBaseColumnList())
        {
            AbstractXmlElementGenerator elementGenerator = new BaseColumnListElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addBlobColumnListElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateBlobColumnList())
        {
            AbstractXmlElementGenerator elementGenerator = new BlobColumnListElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addSelectByExampleWithoutBLOBsElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateSelectByExampleWithoutBLOBs())
        {
            AbstractXmlElementGenerator elementGenerator = new SelectByExampleWithoutBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addSelectByExampleWithBLOBsElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateSelectByExampleWithBLOBs())
        {
            AbstractXmlElementGenerator elementGenerator = new SelectByExampleWithBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addSelectByPrimaryKeyElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateSelectByPrimaryKey())
        {
            AbstractXmlElementGenerator elementGenerator = new SelectByPrimaryKeyElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addDeleteByExampleElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateDeleteByExample())
        {
            AbstractXmlElementGenerator elementGenerator = new DeleteByExampleElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addDeleteByPrimaryKeyElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateDeleteByPrimaryKey())
        {
            AbstractXmlElementGenerator elementGenerator = new DeleteByPrimaryKeyElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addInsertElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateInsert())
        {
            AbstractXmlElementGenerator elementGenerator = new InsertElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addInsertSelectiveElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateInsertSelective())
        {
            AbstractXmlElementGenerator elementGenerator = new InsertSelectiveElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addCountByExampleElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateCountByExample())
        {
            AbstractXmlElementGenerator elementGenerator = new CountByExampleElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByExampleSelectiveElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateUpdateByExampleSelective())
        {
            AbstractXmlElementGenerator elementGenerator = new UpdateByExampleSelectiveElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByExampleWithBLOBsElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateUpdateByExampleWithBLOBs())
        {
            AbstractXmlElementGenerator elementGenerator = new UpdateByExampleWithBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByExampleWithoutBLOBsElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateUpdateByExampleWithoutBLOBs())
        {
            AbstractXmlElementGenerator elementGenerator = new UpdateByExampleWithoutBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByPrimaryKeySelectiveElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateUpdateByPrimaryKeySelective())
        {
            AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeySelectiveElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByPrimaryKeyWithBLOBsElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs())
        {
            AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeyWithBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByPrimaryKeyWithoutBLOBsElement(XmlElement parentElement)
    {
        if(introspectedTable.getRules().generateUpdateByPrimaryKeyWithoutBLOBs())
        {
            AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeyWithoutBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void initializeAndExecuteGenerator(AbstractXmlElementGenerator elementGenerator, XmlElement parentElement)
    {
        elementGenerator.setContext(context);
        elementGenerator.setIntrospectedTable(introspectedTable);
        elementGenerator.setProgressCallback(progressCallback);
        elementGenerator.setWarnings(warnings);
        elementGenerator.addElements(parentElement);
    }

    public Document getDocument()
    {
        Document document = new Document("-//ibatis.apache.org//DTD SQL Map 2.0//EN", "http://ibatis.apache.org/dtd/sql-map-2.dtd");
        document.setRootElement(getSqlMapElement());
        if(!context.getPlugins().sqlMapDocumentGenerated(document, introspectedTable))
            document = null;
        return document;
    }
}
