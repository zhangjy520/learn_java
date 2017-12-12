// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExampleGenerator.java

package org.mybatis.generator.codegen.ibatis2.model;

import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.ibatis2.Ibatis2FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.rules.Rules;
import org.mybatis.generator.internal.util.JavaBeansUtil;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

public class ExampleGenerator extends AbstractJavaGenerator
{

    public ExampleGenerator(boolean generateForJava5)
    {
        this.generateForJava5 = generateForJava5;
    }

    public List getCompilationUnits()
    {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(Messages.getString("Progress.6", table.toString()));
        CommentGenerator commentGenerator = context.getCommentGenerator();
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getExampleType());
        TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setConstructor(true);
        method.setName(type.getShortName());
        if(generateForJava5)
            method.addBodyLine("oredCriteria = new ArrayList<Criteria>();");
        else
            method.addBodyLine("oredCriteria = new ArrayList();");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        Rules rules = introspectedTable.getRules();
        if(rules.generateUpdateByExampleSelective() || rules.generateUpdateByExampleWithBLOBs() || rules.generateUpdateByExampleWithoutBLOBs())
        {
            method = new Method();
            method.setVisibility(JavaVisibility.PROTECTED);
            method.setConstructor(true);
            method.setName(type.getShortName());
            method.addParameter(new Parameter(type, "example"));
            method.addBodyLine("this.orderByClause = example.orderByClause;");
            method.addBodyLine("this.oredCriteria = example.oredCriteria;");
            method.addBodyLine("this.distinct = example.distinct;");
            commentGenerator.addGeneralMethodComment(method, introspectedTable);
            topLevelClass.addMethod(method);
        }
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(FullyQualifiedJavaType.getStringInstance());
        field.setName("orderByClause");
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("setOrderByClause");
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "orderByClause"));
        method.addBodyLine("this.orderByClause = orderByClause;");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getStringInstance());
        method.setName("getOrderByClause");
        method.addBodyLine("return orderByClause;");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        field.setName("distinct");
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("setDistinct");
        method.addParameter(new Parameter(FullyQualifiedJavaType.getBooleanPrimitiveInstance(), "distinct"));
        method.addBodyLine("this.distinct = distinct;");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        method.setName("isDistinct");
        method.addBodyLine("return distinct;");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        FullyQualifiedJavaType fqjt;
        if(generateForJava5)
            fqjt = new FullyQualifiedJavaType("java.util.List<Criteria>");
        else
            fqjt = new FullyQualifiedJavaType("java.util.List");
        field.setType(fqjt);
        field.setName("oredCriteria");
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(fqjt);
        method.setName("getOredCriteria");
        method.addBodyLine("return oredCriteria;");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("or");
        method.addParameter(new Parameter(FullyQualifiedJavaType.getCriteriaInstance(), "criteria"));
        method.addBodyLine("oredCriteria.add(criteria);");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("or");
        method.setReturnType(FullyQualifiedJavaType.getCriteriaInstance());
        method.addBodyLine("Criteria criteria = createCriteriaInternal();");
        method.addBodyLine("oredCriteria.add(criteria);");
        method.addBodyLine("return criteria;");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("createCriteria");
        method.setReturnType(FullyQualifiedJavaType.getCriteriaInstance());
        method.addBodyLine("Criteria criteria = createCriteriaInternal();");
        method.addBodyLine("if (oredCriteria.size() == 0) {");
        method.addBodyLine("oredCriteria.add(criteria);");
        method.addBodyLine("}");
        method.addBodyLine("return criteria;");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PROTECTED);
        method.setName("createCriteriaInternal");
        method.setReturnType(FullyQualifiedJavaType.getCriteriaInstance());
        method.addBodyLine("Criteria criteria = new Criteria();");
        method.addBodyLine("return criteria;");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("clear");
        method.addBodyLine("oredCriteria.clear();");
        method.addBodyLine("orderByClause = null;");
        method.addBodyLine("distinct = false;");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        topLevelClass.addInnerClass(getGeneratedCriteriaInnerClass(topLevelClass));
        topLevelClass.addInnerClass(getCriteriaInnerClass(topLevelClass));
        List answer = new ArrayList();
        if(context.getPlugins().modelExampleClassGenerated(topLevelClass, introspectedTable))
            answer.add(topLevelClass);
        return answer;
    }

    private InnerClass getCriteriaInnerClass(TopLevelClass topLevelClass)
    {
        InnerClass answer = new InnerClass(FullyQualifiedJavaType.getCriteriaInstance());
        answer.setVisibility(JavaVisibility.PUBLIC);
        answer.setStatic(true);
        answer.setSuperClass(FullyQualifiedJavaType.getGeneratedCriteriaInstance());
        context.getCommentGenerator().addClassComment(answer, introspectedTable, true);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PROTECTED);
        method.setName("Criteria");
        method.setConstructor(true);
        method.addBodyLine("super();");
        answer.addMethod(method);
        return answer;
    }

    private InnerClass getGeneratedCriteriaInnerClass(TopLevelClass topLevelClass)
    {
        InnerClass answer = new InnerClass(FullyQualifiedJavaType.getGeneratedCriteriaInstance());
        answer.setVisibility(JavaVisibility.PROTECTED);
        answer.setStatic(true);
        answer.setAbstract(true);
        context.getCommentGenerator().addClassComment(answer, introspectedTable);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PROTECTED);
        method.setName("GeneratedCriteria");
        method.setConstructor(true);
        method.addBodyLine("super();");
        if(generateForJava5)
        {
            method.addBodyLine("criteriaWithoutValue = new ArrayList<String>();");
            method.addBodyLine("criteriaWithSingleValue = new ArrayList<Map<String, Object>>();");
            method.addBodyLine("criteriaWithListValue = new ArrayList<Map<String, Object>>();");
            method.addBodyLine("criteriaWithBetweenValue = new ArrayList<Map<String, Object>>();");
        } else
        {
            method.addBodyLine("criteriaWithoutValue = new ArrayList();");
            method.addBodyLine("criteriaWithSingleValue = new ArrayList();");
            method.addBodyLine("criteriaWithListValue = new ArrayList();");
            method.addBodyLine("criteriaWithBetweenValue = new ArrayList();");
        }
        answer.addMethod(method);
        List criteriaLists = new ArrayList();
        criteriaLists.add("criteriaWithoutValue");
        criteriaLists.add("criteriaWithSingleValue");
        criteriaLists.add("criteriaWithListValue");
        criteriaLists.add("criteriaWithBetweenValue");
        for(Iterator iterator = introspectedTable.getNonBLOBColumns().iterator(); iterator.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
            if(StringUtility.stringHasValue(introspectedColumn.getTypeHandler()))
                criteriaLists.addAll(addtypeHandledObjectsAndMethods(introspectedColumn, method, answer));
        }

        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("isValid");
        method.setReturnType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        StringBuilder sb = new StringBuilder();
        Iterator strIter = criteriaLists.iterator();
        sb.append("return ");
        sb.append((String)strIter.next());
        sb.append(".size() > 0");
        method.addBodyLine(sb.toString());
        for(; strIter.hasNext(); method.addBodyLine(sb.toString()))
        {
            sb.setLength(0);
            OutputUtilities.javaIndent(sb, 1);
            sb.append("|| ");
            sb.append((String)strIter.next());
            sb.append(".size() > 0");
            if(!strIter.hasNext())
                sb.append(';');
        }

        answer.addMethod(method);
        topLevelClass.addImportedType(FullyQualifiedJavaType.getNewMapInstance());
        topLevelClass.addImportedType(FullyQualifiedJavaType.getNewListInstance());
        topLevelClass.addImportedType(FullyQualifiedJavaType.getNewHashMapInstance());
        topLevelClass.addImportedType(FullyQualifiedJavaType.getNewArrayListInstance());
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        FullyQualifiedJavaType listOfStrings;
        if(generateForJava5)
            listOfStrings = new FullyQualifiedJavaType("java.util.List<java.lang.String>");
        else
            listOfStrings = new FullyQualifiedJavaType("java.util.List");
        field.setType(listOfStrings);
        field.setName("criteriaWithoutValue");
        answer.addField(field);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(field.getType());
        method.setName(JavaBeansUtil.getGetterMethodName(field.getName(), field.getType()));
        method.addBodyLine("return criteriaWithoutValue;");
        answer.addMethod(method);
        FullyQualifiedJavaType listOfMaps;
        if(generateForJava5)
            listOfMaps = new FullyQualifiedJavaType("java.util.List<java.util.Map<java.lang.String, java.lang.Object>>");
        else
            listOfMaps = new FullyQualifiedJavaType("java.util.List");
        field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(listOfMaps);
        field.setName("criteriaWithSingleValue");
        answer.addField(field);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(field.getType());
        method.setName(JavaBeansUtil.getGetterMethodName(field.getName(), field.getType()));
        method.addBodyLine("return criteriaWithSingleValue;");
        answer.addMethod(method);
        field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(listOfMaps);
        field.setName("criteriaWithListValue");
        answer.addField(field);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(field.getType());
        method.setName(JavaBeansUtil.getGetterMethodName(field.getName(), field.getType()));
        method.addBodyLine("return criteriaWithListValue;");
        answer.addMethod(method);
        field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(listOfMaps);
        field.setName("criteriaWithBetweenValue");
        answer.addField(field);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(field.getType());
        method.setName(JavaBeansUtil.getGetterMethodName(field.getName(), field.getType()));
        method.addBodyLine("return criteriaWithBetweenValue;");
        answer.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PROTECTED);
        method.setName("addCriterion");
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
        method.addBodyLine("if (condition == null) {");
        method.addBodyLine("throw new RuntimeException(\"Value for condition cannot be null\");");
        method.addBodyLine("}");
        method.addBodyLine("criteriaWithoutValue.add(condition);");
        answer.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PROTECTED);
        method.setName("addCriterion");
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getObjectInstance(), "value"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "property"));
        method.addBodyLine("if (value == null) {");
        method.addBodyLine("throw new RuntimeException(\"Value for \" + property + \" cannot be null\");");
        method.addBodyLine("}");
        if(generateForJava5)
            method.addBodyLine("Map<String, Object> map = new HashMap<String, Object>();");
        else
            method.addBodyLine("Map map = new HashMap();");
        method.addBodyLine("map.put(\"condition\", condition);");
        method.addBodyLine("map.put(\"value\", value);");
        method.addBodyLine("criteriaWithSingleValue.add(map);");
        answer.addMethod(method);
        FullyQualifiedJavaType listOfObjects;
        if(generateForJava5)
            listOfObjects = new FullyQualifiedJavaType("java.util.List<? extends java.lang.Object>");
        else
            listOfObjects = new FullyQualifiedJavaType("java.util.List");
        method = new Method();
        method.setVisibility(JavaVisibility.PROTECTED);
        method.setName("addCriterion");
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
        method.addParameter(new Parameter(listOfObjects, "values"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "property"));
        method.addBodyLine("if (values == null || values.size() == 0) {");
        method.addBodyLine("throw new RuntimeException(\"Value list for \" + property + \" cannot be null or empty\");");
        method.addBodyLine("}");
        if(generateForJava5)
            method.addBodyLine("Map<String, Object> map = new HashMap<String, Object>();");
        else
            method.addBodyLine("Map map = new HashMap();");
        method.addBodyLine("map.put(\"condition\", condition);");
        method.addBodyLine("map.put(\"values\", values);");
        method.addBodyLine("criteriaWithListValue.add(map);");
        answer.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PROTECTED);
        method.setName("addCriterion");
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getObjectInstance(), "value1"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getObjectInstance(), "value2"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "property"));
        method.addBodyLine("if (value1 == null || value2 == null) {");
        method.addBodyLine("throw new RuntimeException(\"Between values for \" + property + \" cannot be null\");");
        method.addBodyLine("}");
        if(generateForJava5)
            method.addBodyLine("List<Object> list = new ArrayList<Object>();");
        else
            method.addBodyLine("List list = new ArrayList();");
        method.addBodyLine("list.add(value1);");
        method.addBodyLine("list.add(value2);");
        if(generateForJava5)
            method.addBodyLine("Map<String, Object> map = new HashMap<String, Object>();");
        else
            method.addBodyLine("Map map = new HashMap();");
        method.addBodyLine("map.put(\"condition\", condition);");
        method.addBodyLine("map.put(\"values\", list);");
        method.addBodyLine("criteriaWithBetweenValue.add(map);");
        answer.addMethod(method);
        FullyQualifiedJavaType listOfDates;
        if(generateForJava5)
            listOfDates = new FullyQualifiedJavaType("java.util.List<java.util.Date>");
        else
            listOfDates = new FullyQualifiedJavaType("java.util.List");
        if(introspectedTable.hasJDBCDateColumns())
        {
            topLevelClass.addImportedType(FullyQualifiedJavaType.getDateInstance());
            topLevelClass.addImportedType(FullyQualifiedJavaType.getNewIteratorInstance());
            method = new Method();
            method.setVisibility(JavaVisibility.PROTECTED);
            method.setName("addCriterionForJDBCDate");
            method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
            method.addParameter(new Parameter(FullyQualifiedJavaType.getDateInstance(), "value"));
            method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "property"));
            method.addBodyLine("if (value == null) {");
            method.addBodyLine("throw new RuntimeException(\"Value for \" + property + \" cannot be null\");");
            method.addBodyLine("}");
            method.addBodyLine("addCriterion(condition, new java.sql.Date(value.getTime()), property);");
            answer.addMethod(method);
            method = new Method();
            method.setVisibility(JavaVisibility.PROTECTED);
            method.setName("addCriterionForJDBCDate");
            method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
            method.addParameter(new Parameter(listOfDates, "values"));
            method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "property"));
            method.addBodyLine("if (values == null || values.size() == 0) {");
            method.addBodyLine("throw new RuntimeException(\"Value list for \" + property + \" cannot be null or empty\");");
            method.addBodyLine("}");
            if(generateForJava5)
            {
                method.addBodyLine("List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();");
                method.addBodyLine("Iterator<Date> iter = values.iterator();");
                method.addBodyLine("while (iter.hasNext()) {");
                method.addBodyLine("dateList.add(new java.sql.Date(iter.next().getTime()));");
                method.addBodyLine("}");
            } else
            {
                method.addBodyLine("List dateList = new ArrayList();");
                method.addBodyLine("Iterator iter = values.iterator();");
                method.addBodyLine("while (iter.hasNext()) {");
                method.addBodyLine("dateList.add(new java.sql.Date(((Date)iter.next()).getTime()));");
                method.addBodyLine("}");
            }
            method.addBodyLine("addCriterion(condition, dateList, property);");
            answer.addMethod(method);
            method = new Method();
            method.setVisibility(JavaVisibility.PROTECTED);
            method.setName("addCriterionForJDBCDate");
            method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
            method.addParameter(new Parameter(FullyQualifiedJavaType.getDateInstance(), "value1"));
            method.addParameter(new Parameter(FullyQualifiedJavaType.getDateInstance(), "value2"));
            method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "property"));
            method.addBodyLine("if (value1 == null || value2 == null) {");
            method.addBodyLine("throw new RuntimeException(\"Between values for \" + property + \" cannot be null\");");
            method.addBodyLine("}");
            method.addBodyLine("addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);");
            answer.addMethod(method);
        }
        if(introspectedTable.hasJDBCTimeColumns())
        {
            topLevelClass.addImportedType(FullyQualifiedJavaType.getDateInstance());
            topLevelClass.addImportedType(FullyQualifiedJavaType.getNewIteratorInstance());
            method = new Method();
            method.setVisibility(JavaVisibility.PROTECTED);
            method.setName("addCriterionForJDBCTime");
            method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
            method.addParameter(new Parameter(FullyQualifiedJavaType.getDateInstance(), "value"));
            method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "property"));
            method.addBodyLine("if (value == null) {");
            method.addBodyLine("throw new RuntimeException(\"Value for \" + property + \" cannot be null\");");
            method.addBodyLine("}");
            method.addBodyLine("addCriterion(condition, new java.sql.Time(value.getTime()), property);");
            answer.addMethod(method);
            method = new Method();
            method.setVisibility(JavaVisibility.PROTECTED);
            method.setName("addCriterionForJDBCTime");
            method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
            method.addParameter(new Parameter(listOfDates, "values"));
            method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "property"));
            method.addBodyLine("if (values == null || values.size() == 0) {");
            method.addBodyLine("throw new RuntimeException(\"Value list for \" + property + \" cannot be null or empty\");");
            method.addBodyLine("}");
            if(generateForJava5)
            {
                method.addBodyLine("List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();");
                method.addBodyLine("Iterator<Date> iter = values.iterator();");
                method.addBodyLine("while (iter.hasNext()) {");
                method.addBodyLine("timeList.add(new java.sql.Time(iter.next().getTime()));");
                method.addBodyLine("}");
            } else
            {
                method.addBodyLine("List timeList = new ArrayList();");
                method.addBodyLine("Iterator iter = values.iterator();");
                method.addBodyLine("while (iter.hasNext()) {");
                method.addBodyLine("timeList.add(new java.sql.Time(((Date)iter.next()).getTime()));");
                method.addBodyLine("}");
            }
            method.addBodyLine("addCriterion(condition, timeList, property);");
            answer.addMethod(method);
            method = new Method();
            method.setVisibility(JavaVisibility.PROTECTED);
            method.setName("addCriterionForJDBCTime");
            method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
            method.addParameter(new Parameter(FullyQualifiedJavaType.getDateInstance(), "value1"));
            method.addParameter(new Parameter(FullyQualifiedJavaType.getDateInstance(), "value2"));
            method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "property"));
            method.addBodyLine("if (value1 == null || value2 == null) {");
            method.addBodyLine("throw new RuntimeException(\"Between values for \" + property + \" cannot be null\");");
            method.addBodyLine("}");
            method.addBodyLine("addCriterion(condition, new java.sql.Time(value1.getTime()), new java.sql.Time(value2.getTime()), property);");
            answer.addMethod(method);
        }
        IntrospectedColumn introspectedColumn;
        for(Iterator iterator1 = introspectedTable.getNonBLOBColumns().iterator(); iterator1.hasNext(); answer.addMethod(getSetBetweenOrNotBetweenMethod(introspectedColumn, false)))
        {
            introspectedColumn = (IntrospectedColumn)iterator1.next();
            topLevelClass.addImportedType(introspectedColumn.getFullyQualifiedJavaType());
            answer.addMethod(getSetNullMethod(introspectedColumn));
            answer.addMethod(getSetNotNullMethod(introspectedColumn));
            answer.addMethod(getSetEqualMethod(introspectedColumn));
            answer.addMethod(getSetNotEqualMethod(introspectedColumn));
            answer.addMethod(getSetGreaterThanMethod(introspectedColumn));
            answer.addMethod(getSetGreaterThenOrEqualMethod(introspectedColumn));
            answer.addMethod(getSetLessThanMethod(introspectedColumn));
            answer.addMethod(getSetLessThanOrEqualMethod(introspectedColumn));
            if(introspectedColumn.isJdbcCharacterColumn())
            {
                answer.addMethod(getSetLikeMethod(introspectedColumn));
                answer.addMethod(getSetNotLikeMethod(introspectedColumn));
            }
            answer.addMethod(getSetInOrNotInMethod(introspectedColumn, true));
            answer.addMethod(getSetInOrNotInMethod(introspectedColumn, false));
            answer.addMethod(getSetBetweenOrNotBetweenMethod(introspectedColumn, true));
        }

        return answer;
    }

    private List addtypeHandledObjectsAndMethods(IntrospectedColumn introspectedColumn, Method constructor, InnerClass innerClass)
    {
        List answer = new ArrayList();
        StringBuilder sb = new StringBuilder();
        FullyQualifiedJavaType listOfMaps;
        if(generateForJava5)
            listOfMaps = new FullyQualifiedJavaType("java.util.List<java.util.Map<java.lang.String, java.lang.Object>>");
        else
            listOfMaps = new FullyQualifiedJavaType("java.util.List");
        sb.setLength(0);
        sb.append(introspectedColumn.getJavaProperty());
        sb.append("CriteriaWithSingleValue");
        answer.add(sb.toString());
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(listOfMaps);
        field.setName(sb.toString());
        innerClass.addField(field);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(field.getType());
        method.setName(JavaBeansUtil.getGetterMethodName(field.getName(), field.getType()));
        sb.insert(0, "return ");
        sb.append(';');
        method.addBodyLine(sb.toString());
        innerClass.addMethod(method);
        sb.setLength(0);
        sb.append(introspectedColumn.getJavaProperty());
        sb.append("CriteriaWithListValue");
        answer.add(sb.toString());
        field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(listOfMaps);
        field.setName(sb.toString());
        innerClass.addField(field);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(field.getType());
        method.setName(JavaBeansUtil.getGetterMethodName(field.getName(), field.getType()));
        sb.insert(0, "return ");
        sb.append(';');
        method.addBodyLine(sb.toString());
        innerClass.addMethod(method);
        sb.setLength(0);
        sb.append(introspectedColumn.getJavaProperty());
        sb.append("CriteriaWithBetweenValue");
        answer.add(sb.toString());
        field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(listOfMaps);
        field.setName(sb.toString());
        innerClass.addField(field);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(field.getType());
        method.setName(JavaBeansUtil.getGetterMethodName(field.getName(), field.getType()));
        sb.insert(0, "return ");
        sb.append(';');
        method.addBodyLine(sb.toString());
        innerClass.addMethod(method);
        sb.setLength(0);
        sb.append(introspectedColumn.getJavaProperty());
        if(generateForJava5)
            sb.append("CriteriaWithSingleValue = new ArrayList<Map<String, Object>>();");
        else
            sb.append("CriteriaWithSingleValue = new ArrayList();");
        constructor.addBodyLine(sb.toString());
        sb.setLength(0);
        sb.append(introspectedColumn.getJavaProperty());
        if(generateForJava5)
            sb.append("CriteriaWithListValue = new ArrayList<Map<String, Object>>();");
        else
            sb.append("CriteriaWithListValue = new ArrayList();");
        constructor.addBodyLine(sb.toString());
        sb.setLength(0);
        sb.append(introspectedColumn.getJavaProperty());
        if(generateForJava5)
            sb.append("CriteriaWithBetweenValue = new ArrayList<Map<String, Object>>();");
        else
            sb.append("CriteriaWithBetweenValue = new ArrayList();");
        constructor.addBodyLine(sb.toString());
        method = new Method();
        method.setVisibility(JavaVisibility.PROTECTED);
        sb.setLength(0);
        sb.append("add");
        sb.append(introspectedColumn.getJavaProperty());
        sb.setCharAt(3, Character.toUpperCase(sb.charAt(3)));
        sb.append("Criterion");
        method.setName(sb.toString());
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
        method.addParameter(new Parameter(introspectedColumn.getFullyQualifiedJavaType(), "value"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "property"));
        method.addBodyLine("if (value == null) {");
        method.addBodyLine("throw new RuntimeException(\"Value for \" + property + \" cannot be null\");");
        method.addBodyLine("}");
        if(generateForJava5)
            method.addBodyLine("Map<String, Object> map = new HashMap<String, Object>();");
        else
            method.addBodyLine("Map map = new HashMap();");
        method.addBodyLine("map.put(\"condition\", condition);");
        method.addBodyLine("map.put(\"value\", value);");
        sb.setLength(0);
        sb.append(introspectedColumn.getJavaProperty());
        sb.append("CriteriaWithSingleValue.add(map);");
        method.addBodyLine(sb.toString());
        innerClass.addMethod(method);
        FullyQualifiedJavaType listOfObjects = FullyQualifiedJavaType.getNewListInstance();
        if(generateForJava5)
            listOfObjects.addTypeArgument(introspectedColumn.getFullyQualifiedJavaType());
        sb.setLength(0);
        sb.append("add");
        sb.append(introspectedColumn.getJavaProperty());
        sb.setCharAt(3, Character.toUpperCase(sb.charAt(3)));
        sb.append("Criterion");
        method = new Method();
        method.setVisibility(JavaVisibility.PROTECTED);
        method.setName(sb.toString());
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
        method.addParameter(new Parameter(listOfObjects, "values"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "property"));
        method.addBodyLine("if (values == null || values.size() == 0) {");
        method.addBodyLine("throw new RuntimeException(\"Value list for \" + property + \" cannot be null or empty\");");
        method.addBodyLine("}");
        if(generateForJava5)
            method.addBodyLine("Map<String, Object> map = new HashMap<String, Object>();");
        else
            method.addBodyLine("Map map = new HashMap();");
        method.addBodyLine("map.put(\"condition\", condition);");
        method.addBodyLine("map.put(\"values\", values);");
        sb.setLength(0);
        sb.append(introspectedColumn.getJavaProperty());
        sb.append("CriteriaWithListValue.add(map);");
        method.addBodyLine(sb.toString());
        innerClass.addMethod(method);
        sb.setLength(0);
        sb.append("add");
        sb.append(introspectedColumn.getJavaProperty());
        sb.setCharAt(3, Character.toUpperCase(sb.charAt(3)));
        sb.append("Criterion");
        method = new Method();
        method.setVisibility(JavaVisibility.PROTECTED);
        method.setName(sb.toString());
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
        method.addParameter(new Parameter(introspectedColumn.getFullyQualifiedJavaType(), "value1"));
        method.addParameter(new Parameter(introspectedColumn.getFullyQualifiedJavaType(), "value2"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "property"));
        method.addBodyLine("if (value1 == null || value2 == null) {");
        method.addBodyLine("throw new RuntimeException(\"Between values for \" + property + \" cannot be null\");");
        method.addBodyLine("}");
        if(generateForJava5)
        {
            sb.setLength(0);
            sb.append("List<");
            sb.append(introspectedColumn.getFullyQualifiedJavaType().getShortName());
            sb.append("> list = new ArrayList<");
            sb.append(introspectedColumn.getFullyQualifiedJavaType().getShortName());
            sb.append(">();");
            method.addBodyLine(sb.toString());
        } else
        {
            method.addBodyLine("List list = new ArrayList();");
        }
        method.addBodyLine("list.add(value1);");
        method.addBodyLine("list.add(value2);");
        if(generateForJava5)
            method.addBodyLine("Map<String, Object> map = new HashMap<String, Object>();");
        else
            method.addBodyLine("Map map = new HashMap();");
        method.addBodyLine("map.put(\"condition\", condition);");
        method.addBodyLine("map.put(\"values\", list);");
        sb.setLength(0);
        sb.append(introspectedColumn.getJavaProperty());
        sb.append("CriteriaWithBetweenValue.add(map);");
        method.addBodyLine(sb.toString());
        innerClass.addMethod(method);
        return answer;
    }

    private Method getSetNullMethod(IntrospectedColumn introspectedColumn)
    {
        return getNoValueMethod(introspectedColumn, "IsNull", "is null");
    }

    private Method getSetNotNullMethod(IntrospectedColumn introspectedColumn)
    {
        return getNoValueMethod(introspectedColumn, "IsNotNull", "is not null");
    }

    private Method getSetEqualMethod(IntrospectedColumn introspectedColumn)
    {
        return getSingleValueMethod(introspectedColumn, "EqualTo", "=");
    }

    private Method getSetNotEqualMethod(IntrospectedColumn introspectedColumn)
    {
        return getSingleValueMethod(introspectedColumn, "NotEqualTo", "<>");
    }

    private Method getSetGreaterThanMethod(IntrospectedColumn introspectedColumn)
    {
        return getSingleValueMethod(introspectedColumn, "GreaterThan", ">");
    }

    private Method getSetGreaterThenOrEqualMethod(IntrospectedColumn introspectedColumn)
    {
        return getSingleValueMethod(introspectedColumn, "GreaterThanOrEqualTo", ">=");
    }

    private Method getSetLessThanMethod(IntrospectedColumn introspectedColumn)
    {
        return getSingleValueMethod(introspectedColumn, "LessThan", "<");
    }

    private Method getSetLessThanOrEqualMethod(IntrospectedColumn introspectedColumn)
    {
        return getSingleValueMethod(introspectedColumn, "LessThanOrEqualTo", "<=");
    }

    private Method getSetLikeMethod(IntrospectedColumn introspectedColumn)
    {
        return getSingleValueMethod(introspectedColumn, "Like", "like");
    }

    private Method getSetNotLikeMethod(IntrospectedColumn introspectedColumn)
    {
        return getSingleValueMethod(introspectedColumn, "NotLike", "not like");
    }

    private Method getSingleValueMethod(IntrospectedColumn introspectedColumn, String nameFragment, String operator)
    {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addParameter(new Parameter(introspectedColumn.getFullyQualifiedJavaType(), "value"));
        StringBuilder sb = new StringBuilder();
        sb.append(introspectedColumn.getJavaProperty());
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        sb.insert(0, "and");
        sb.append(nameFragment);
        method.setName(sb.toString());
        method.setReturnType(FullyQualifiedJavaType.getCriteriaInstance());
        sb.setLength(0);
        if(introspectedColumn.isJDBCDateColumn())
            sb.append("addCriterionForJDBCDate(\"");
        else
        if(introspectedColumn.isJDBCTimeColumn())
            sb.append("addCriterionForJDBCTime(\"");
        else
        if(StringUtility.stringHasValue(introspectedColumn.getTypeHandler()))
        {
            sb.append("add");
            sb.append(introspectedColumn.getJavaProperty());
            sb.setCharAt(3, Character.toUpperCase(sb.charAt(3)));
            sb.append("Criterion(\"");
        } else
        {
            sb.append("addCriterion(\"");
        }
        sb.append(Ibatis2FormattingUtilities.getAliasedActualColumnName(introspectedColumn));
        sb.append(' ');
        sb.append(operator);
        sb.append("\", ");
        if(introspectedColumn.getFullyQualifiedJavaType().isPrimitive())
        {
            sb.append("new ");
            sb.append(introspectedColumn.getFullyQualifiedJavaType().getPrimitiveTypeWrapper().getShortName());
            sb.append("(value)");
        } else
        {
            sb.append("value");
        }
        sb.append(", \"");
        sb.append(introspectedColumn.getJavaProperty());
        sb.append("\");");
        method.addBodyLine(sb.toString());
        method.addBodyLine("return (Criteria) this;");
        return method;
    }

    private Method getSetBetweenOrNotBetweenMethod(IntrospectedColumn introspectedColumn, boolean betweenMethod)
    {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();
        method.addParameter(new Parameter(type, "value1"));
        method.addParameter(new Parameter(type, "value2"));
        StringBuilder sb = new StringBuilder();
        sb.append(introspectedColumn.getJavaProperty());
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        sb.insert(0, "and");
        if(betweenMethod)
            sb.append("Between");
        else
            sb.append("NotBetween");
        method.setName(sb.toString());
        method.setReturnType(FullyQualifiedJavaType.getCriteriaInstance());
        sb.setLength(0);
        if(introspectedColumn.isJDBCDateColumn())
            sb.append("addCriterionForJDBCDate(\"");
        else
        if(introspectedColumn.isJDBCTimeColumn())
            sb.append("addCriterionForJDBCTime(\"");
        else
        if(StringUtility.stringHasValue(introspectedColumn.getTypeHandler()))
        {
            sb.append("add");
            sb.append(introspectedColumn.getJavaProperty());
            sb.setCharAt(3, Character.toUpperCase(sb.charAt(3)));
            sb.append("Criterion(\"");
        } else
        {
            sb.append("addCriterion(\"");
        }
        sb.append(Ibatis2FormattingUtilities.getAliasedActualColumnName(introspectedColumn));
        if(betweenMethod)
            sb.append(" between");
        else
            sb.append(" not between");
        sb.append("\", ");
        if(introspectedColumn.getFullyQualifiedJavaType().isPrimitive())
        {
            sb.append("new ");
            sb.append(introspectedColumn.getFullyQualifiedJavaType().getPrimitiveTypeWrapper().getShortName());
            sb.append("(value1), ");
            sb.append("new ");
            sb.append(introspectedColumn.getFullyQualifiedJavaType().getPrimitiveTypeWrapper().getShortName());
            sb.append("(value2)");
        } else
        {
            sb.append("value1, value2");
        }
        sb.append(", \"");
        sb.append(introspectedColumn.getJavaProperty());
        sb.append("\");");
        method.addBodyLine(sb.toString());
        method.addBodyLine("return (Criteria) this;");
        return method;
    }

    private Method getSetInOrNotInMethod(IntrospectedColumn introspectedColumn, boolean inMethod)
    {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType type = FullyQualifiedJavaType.getNewListInstance();
        if(generateForJava5)
            if(introspectedColumn.getFullyQualifiedJavaType().isPrimitive())
                type.addTypeArgument(introspectedColumn.getFullyQualifiedJavaType().getPrimitiveTypeWrapper());
            else
                type.addTypeArgument(introspectedColumn.getFullyQualifiedJavaType());
        method.addParameter(new Parameter(type, "values"));
        StringBuilder sb = new StringBuilder();
        sb.append(introspectedColumn.getJavaProperty());
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        sb.insert(0, "and");
        if(inMethod)
            sb.append("In");
        else
            sb.append("NotIn");
        method.setName(sb.toString());
        method.setReturnType(FullyQualifiedJavaType.getCriteriaInstance());
        sb.setLength(0);
        if(introspectedColumn.isJDBCDateColumn())
            sb.append("addCriterionForJDBCDate(\"");
        else
        if(introspectedColumn.isJDBCTimeColumn())
            sb.append("addCriterionForJDBCTime(\"");
        else
        if(StringUtility.stringHasValue(introspectedColumn.getTypeHandler()))
        {
            sb.append("add");
            sb.append(introspectedColumn.getJavaProperty());
            sb.setCharAt(3, Character.toUpperCase(sb.charAt(3)));
            sb.append("Criterion(\"");
        } else
        {
            sb.append("addCriterion(\"");
        }
        sb.append(Ibatis2FormattingUtilities.getAliasedActualColumnName(introspectedColumn));
        if(inMethod)
            sb.append(" in");
        else
            sb.append(" not in");
        sb.append("\", values, \"");
        sb.append(introspectedColumn.getJavaProperty());
        sb.append("\");");
        method.addBodyLine(sb.toString());
        method.addBodyLine("return (Criteria) this;");
        return method;
    }

    private Method getNoValueMethod(IntrospectedColumn introspectedColumn, String nameFragment, String operator)
    {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        StringBuilder sb = new StringBuilder();
        sb.append(introspectedColumn.getJavaProperty());
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        sb.insert(0, "and");
        sb.append(nameFragment);
        method.setName(sb.toString());
        method.setReturnType(FullyQualifiedJavaType.getCriteriaInstance());
        sb.setLength(0);
        sb.append("addCriterion(\"");
        sb.append(Ibatis2FormattingUtilities.getAliasedActualColumnName(introspectedColumn));
        sb.append(' ');
        sb.append(operator);
        sb.append("\");");
        method.addBodyLine(sb.toString());
        method.addBodyLine("return (Criteria) this;");
        return method;
    }

    private boolean generateForJava5;
}
