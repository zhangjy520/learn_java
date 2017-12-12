// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExampleGenerator.java

package org.mybatis.generator.codegen.mybatis3.model;

import java.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.JavaBeansUtil;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

public class ExampleGenerator extends AbstractJavaGenerator
{

    public ExampleGenerator()
    {
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
        method.addBodyLine("oredCriteria = new ArrayList<Criteria>();");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
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
        FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("java.util.List<Criteria>");
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
        topLevelClass.addInnerClass(getCriterionInnerClass(topLevelClass));
        List answer = new ArrayList();
        if(context.getPlugins().modelExampleClassGenerated(topLevelClass, introspectedTable))
            answer.add(topLevelClass);
        return answer;
    }

    private InnerClass getCriterionInnerClass(TopLevelClass topLevelClass)
    {
        InnerClass answer = new InnerClass(new FullyQualifiedJavaType("Criterion"));
        answer.setVisibility(JavaVisibility.PUBLIC);
        answer.setStatic(true);
        context.getCommentGenerator().addClassComment(answer, introspectedTable);
        Field field = new Field();
        field.setName("condition");
        field.setType(FullyQualifiedJavaType.getStringInstance());
        field.setVisibility(JavaVisibility.PRIVATE);
        answer.addField(field);
        answer.addMethod(getGetter(field));
        field = new Field();
        field.setName("value");
        field.setType(FullyQualifiedJavaType.getObjectInstance());
        field.setVisibility(JavaVisibility.PRIVATE);
        answer.addField(field);
        answer.addMethod(getGetter(field));
        field = new Field();
        field.setName("secondValue");
        field.setType(FullyQualifiedJavaType.getObjectInstance());
        field.setVisibility(JavaVisibility.PRIVATE);
        answer.addField(field);
        answer.addMethod(getGetter(field));
        field = new Field();
        field.setName("noValue");
        field.setType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        field.setVisibility(JavaVisibility.PRIVATE);
        answer.addField(field);
        answer.addMethod(getGetter(field));
        field = new Field();
        field.setName("singleValue");
        field.setType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        field.setVisibility(JavaVisibility.PRIVATE);
        answer.addField(field);
        answer.addMethod(getGetter(field));
        field = new Field();
        field.setName("betweenValue");
        field.setType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        field.setVisibility(JavaVisibility.PRIVATE);
        answer.addField(field);
        answer.addMethod(getGetter(field));
        field = new Field();
        field.setName("listValue");
        field.setType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        field.setVisibility(JavaVisibility.PRIVATE);
        answer.addField(field);
        answer.addMethod(getGetter(field));
        Method method = new Method();
        method.setVisibility(JavaVisibility.PROTECTED);
        method.setName("Criterion");
        method.setConstructor(true);
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
        method.addBodyLine("super();");
        method.addBodyLine("this.condition = condition;");
        method.addBodyLine("this.noValue = true;");
        answer.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PROTECTED);
        method.setName("Criterion");
        method.setConstructor(true);
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getObjectInstance(), "value"));
        method.addBodyLine("super();");
        method.addBodyLine("this.condition = condition;");
        method.addBodyLine("this.value = value;");
        method.addBodyLine("if (value instanceof List<?>) {");
        method.addBodyLine("this.listValue = true;");
        method.addBodyLine("} else {");
        method.addBodyLine("this.singleValue = true;");
        method.addBodyLine("}");
        answer.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PROTECTED);
        method.setName("Criterion");
        method.setConstructor(true);
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getObjectInstance(), "value"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getObjectInstance(), "secondValue"));
        method.addBodyLine("super();");
        method.addBodyLine("this.condition = condition;");
        method.addBodyLine("this.value = value;");
        method.addBodyLine("this.secondValue = secondValue;");
        method.addBodyLine("this.betweenValue = true;");
        answer.addMethod(method);
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
        method.addBodyLine("criteria = new ArrayList<Criterion>();");
        answer.addMethod(method);
        List criteriaLists = new ArrayList();
        criteriaLists.add("criteria");
        for(Iterator iterator = introspectedTable.getNonBLOBColumns().iterator(); iterator.hasNext();)
        {
            IntrospectedColumn introspectedColumn = (IntrospectedColumn)iterator.next();
            if(StringUtility.stringHasValue(introspectedColumn.getTypeHandler()))
            {
                String name = addtypeHandledObjectsAndMethods(introspectedColumn, method, answer);
                criteriaLists.add(name);
            }
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
        if(!strIter.hasNext())
            sb.append(';');
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
        topLevelClass.addImportedType(FullyQualifiedJavaType.getNewListInstance());
        topLevelClass.addImportedType(FullyQualifiedJavaType.getNewArrayListInstance());
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        FullyQualifiedJavaType listOfCriterion = new FullyQualifiedJavaType("java.util.List<Criterion>");
        field.setType(listOfCriterion);
        field.setName("criteria");
        answer.addField(field);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(field.getType());
        method.setName(JavaBeansUtil.getGetterMethodName(field.getName(), field.getType()));
        method.addBodyLine("return criteria;");
        answer.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PROTECTED);
        method.setName("addCriterion");
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "condition"));
        method.addBodyLine("if (condition == null) {");
        method.addBodyLine("throw new RuntimeException(\"Value for condition cannot be null\");");
        method.addBodyLine("}");
        method.addBodyLine("criteria.add(new Criterion(condition));");
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
        method.addBodyLine("criteria.add(new Criterion(condition, value));");
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
        method.addBodyLine("criteria.add(new Criterion(condition, value1, value2));");
        answer.addMethod(method);
        FullyQualifiedJavaType listOfDates = new FullyQualifiedJavaType("java.util.List<java.util.Date>");
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
            method.addBodyLine("List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();");
            method.addBodyLine("Iterator<Date> iter = values.iterator();");
            method.addBodyLine("while (iter.hasNext()) {");
            method.addBodyLine("dateList.add(new java.sql.Date(iter.next().getTime()));");
            method.addBodyLine("}");
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
            method.addBodyLine("List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();");
            method.addBodyLine("Iterator<Date> iter = values.iterator();");
            method.addBodyLine("while (iter.hasNext()) {");
            method.addBodyLine("timeList.add(new java.sql.Time(iter.next().getTime()));");
            method.addBodyLine("}");
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
        if(nameFragment.equals("EqualTo"))
        {
            StringBuilder isNullMethodName = new StringBuilder();
            isNullMethodName.append(introspectedColumn.getJavaProperty());
            isNullMethodName.setCharAt(0, Character.toUpperCase(isNullMethodName.charAt(0)));
            isNullMethodName.insert(0, "and");
            isNullMethodName.append("IsNull");
            isNullMethodName.append("();");
            method.addBodyLine("if(value == null ){");
            method.addBodyLine(isNullMethodName.toString());
            method.addBodyLine("} else {");
        } else
        if(nameFragment.equals("NotEqualTo"))
        {
            StringBuilder isNotNullMethodName = new StringBuilder();
            isNotNullMethodName.append(introspectedColumn.getJavaProperty());
            isNotNullMethodName.setCharAt(0, Character.toUpperCase(isNotNullMethodName.charAt(0)));
            isNotNullMethodName.insert(0, "and");
            isNotNullMethodName.append("IsNotNull");
            isNotNullMethodName.append("();");
            method.addBodyLine("if(value == null ){");
            method.addBodyLine(isNotNullMethodName.toString());
            method.addBodyLine("} else {");
        }
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
        sb.append(MyBatis3FormattingUtilities.getAliasedActualColumnName(introspectedColumn));
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
        if(nameFragment.equals("EqualTo") || nameFragment.equals("NotEqualTo"))
            method.addBodyLine("}");
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
        sb.append(MyBatis3FormattingUtilities.getAliasedActualColumnName(introspectedColumn));
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
        sb.append(MyBatis3FormattingUtilities.getAliasedActualColumnName(introspectedColumn));
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
        sb.append(MyBatis3FormattingUtilities.getAliasedActualColumnName(introspectedColumn));
        sb.append(' ');
        sb.append(operator);
        sb.append("\");");
        method.addBodyLine(sb.toString());
        method.addBodyLine("return (Criteria) this;");
        return method;
    }

    private String addtypeHandledObjectsAndMethods(IntrospectedColumn introspectedColumn, Method constructor, InnerClass innerClass)
    {
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        sb.append(introspectedColumn.getJavaProperty());
        sb.append("Criteria");
        String answer = sb.toString();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(new FullyQualifiedJavaType("java.util.List<Criterion>"));
        field.setName(answer);
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
        sb.append(field.getName());
        sb.append(" = new ArrayList<Criterion>();");
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
        method.addParameter(new Parameter(FullyQualifiedJavaType.getObjectInstance(), "value"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "property"));
        method.addBodyLine("if (value == null) {");
        method.addBodyLine("throw new RuntimeException(\"Value for \" + property + \" cannot be null\");");
        method.addBodyLine("}");
        sb.setLength(0);
        sb.append(field.getName());
        sb.append(".add(new Criterion(condition, value));");
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
        sb.setLength(0);
        sb.append(field.getName());
        sb.append(".add(new Criterion(condition, value1, value2));");
        method.addBodyLine(sb.toString());
        innerClass.addMethod(method);
        return answer;
    }
}
