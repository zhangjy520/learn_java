// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntrospectedTable.java

package org.mybatis.generator.api;


// Referenced classes of package org.mybatis.generator.api:
//            IntrospectedTable

protected static final class IntrospectedTable$InternalAttribute extends Enum
{

    public static IntrospectedTable$InternalAttribute[] values()
    {
        IntrospectedTable$InternalAttribute aintrospectedtable$internalattribute[];
        int i;
        IntrospectedTable$InternalAttribute aintrospectedtable$internalattribute1[];
        System.arraycopy(aintrospectedtable$internalattribute = ENUM$VALUES, 0, aintrospectedtable$internalattribute1 = new IntrospectedTable$InternalAttribute[i = aintrospectedtable$internalattribute.length], 0, i);
        return aintrospectedtable$internalattribute1;
    }

    public static IntrospectedTable$InternalAttribute valueOf(String s)
    {
        return (IntrospectedTable$InternalAttribute)Enum.valueOf(org/mybatis/generator/api/IntrospectedTable$InternalAttribute, s);
    }

    public static final IntrospectedTable$InternalAttribute ATTR_DAO_IMPLEMENTATION_TYPE;
    public static final IntrospectedTable$InternalAttribute ATTR_DAO_INTERFACE_TYPE;
    public static final IntrospectedTable$InternalAttribute ATTR_PRIMARY_KEY_TYPE;
    public static final IntrospectedTable$InternalAttribute ATTR_BASE_RECORD_TYPE;
    public static final IntrospectedTable$InternalAttribute ATTR_RECORD_WITH_BLOBS_TYPE;
    public static final IntrospectedTable$InternalAttribute ATTR_EXAMPLE_TYPE;
    public static final IntrospectedTable$InternalAttribute ATTR_IBATIS2_SQL_MAP_PACKAGE;
    public static final IntrospectedTable$InternalAttribute ATTR_IBATIS2_SQL_MAP_FILE_NAME;
    public static final IntrospectedTable$InternalAttribute ATTR_IBATIS2_SQL_MAP_NAMESPACE;
    public static final IntrospectedTable$InternalAttribute ATTR_MYBATIS3_XML_MAPPER_PACKAGE;
    public static final IntrospectedTable$InternalAttribute ATTR_MYBATIS3_XML_MAPPER_FILE_NAME;
    public static final IntrospectedTable$InternalAttribute ATTR_MYBATIS3_JAVA_MAPPER_TYPE;
    public static final IntrospectedTable$InternalAttribute ATTR_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME;
    public static final IntrospectedTable$InternalAttribute ATTR_ALIASED_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME;
    public static final IntrospectedTable$InternalAttribute ATTR_COUNT_BY_EXAMPLE_STATEMENT_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_DELETE_BY_EXAMPLE_STATEMENT_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_DELETE_BY_PRIMARY_KEY_STATEMENT_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_INSERT_STATEMENT_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_INSERT_SELECTIVE_STATEMENT_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_SELECT_BY_EXAMPLE_STATEMENT_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_SELECT_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_SELECT_BY_PRIMARY_KEY_STATEMENT_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_UPDATE_BY_EXAMPLE_STATEMENT_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_UPDATE_BY_EXAMPLE_SELECTIVE_STATEMENT_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_UPDATE_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_UPDATE_BY_PRIMARY_KEY_STATEMENT_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_UPDATE_BY_PRIMARY_KEY_SELECTIVE_STATEMENT_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_UPDATE_BY_PRIMARY_KEY_WITH_BLOBS_STATEMENT_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_BASE_RESULT_MAP_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_RESULT_MAP_WITH_BLOBS_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_EXAMPLE_WHERE_CLAUSE_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_BASE_COLUMN_LIST_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_BLOB_COLUMN_LIST_ID;
    public static final IntrospectedTable$InternalAttribute ATTR_MYBATIS3_UPDATE_BY_EXAMPLE_WHERE_CLAUSE_ID;
    private static final IntrospectedTable$InternalAttribute ENUM$VALUES[];

    static 
    {
        ATTR_DAO_IMPLEMENTATION_TYPE = new IntrospectedTable$InternalAttribute("ATTR_DAO_IMPLEMENTATION_TYPE", 0);
        ATTR_DAO_INTERFACE_TYPE = new IntrospectedTable$InternalAttribute("ATTR_DAO_INTERFACE_TYPE", 1);
        ATTR_PRIMARY_KEY_TYPE = new IntrospectedTable$InternalAttribute("ATTR_PRIMARY_KEY_TYPE", 2);
        ATTR_BASE_RECORD_TYPE = new IntrospectedTable$InternalAttribute("ATTR_BASE_RECORD_TYPE", 3);
        ATTR_RECORD_WITH_BLOBS_TYPE = new IntrospectedTable$InternalAttribute("ATTR_RECORD_WITH_BLOBS_TYPE", 4);
        ATTR_EXAMPLE_TYPE = new IntrospectedTable$InternalAttribute("ATTR_EXAMPLE_TYPE", 5);
        ATTR_IBATIS2_SQL_MAP_PACKAGE = new IntrospectedTable$InternalAttribute("ATTR_IBATIS2_SQL_MAP_PACKAGE", 6);
        ATTR_IBATIS2_SQL_MAP_FILE_NAME = new IntrospectedTable$InternalAttribute("ATTR_IBATIS2_SQL_MAP_FILE_NAME", 7);
        ATTR_IBATIS2_SQL_MAP_NAMESPACE = new IntrospectedTable$InternalAttribute("ATTR_IBATIS2_SQL_MAP_NAMESPACE", 8);
        ATTR_MYBATIS3_XML_MAPPER_PACKAGE = new IntrospectedTable$InternalAttribute("ATTR_MYBATIS3_XML_MAPPER_PACKAGE", 9);
        ATTR_MYBATIS3_XML_MAPPER_FILE_NAME = new IntrospectedTable$InternalAttribute("ATTR_MYBATIS3_XML_MAPPER_FILE_NAME", 10);
        ATTR_MYBATIS3_JAVA_MAPPER_TYPE = new IntrospectedTable$InternalAttribute("ATTR_MYBATIS3_JAVA_MAPPER_TYPE", 11);
        ATTR_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME = new IntrospectedTable$InternalAttribute("ATTR_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME", 12);
        ATTR_ALIASED_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME = new IntrospectedTable$InternalAttribute("ATTR_ALIASED_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME", 13);
        ATTR_COUNT_BY_EXAMPLE_STATEMENT_ID = new IntrospectedTable$InternalAttribute("ATTR_COUNT_BY_EXAMPLE_STATEMENT_ID", 14);
        ATTR_DELETE_BY_EXAMPLE_STATEMENT_ID = new IntrospectedTable$InternalAttribute("ATTR_DELETE_BY_EXAMPLE_STATEMENT_ID", 15);
        ATTR_DELETE_BY_PRIMARY_KEY_STATEMENT_ID = new IntrospectedTable$InternalAttribute("ATTR_DELETE_BY_PRIMARY_KEY_STATEMENT_ID", 16);
        ATTR_INSERT_STATEMENT_ID = new IntrospectedTable$InternalAttribute("ATTR_INSERT_STATEMENT_ID", 17);
        ATTR_INSERT_SELECTIVE_STATEMENT_ID = new IntrospectedTable$InternalAttribute("ATTR_INSERT_SELECTIVE_STATEMENT_ID", 18);
        ATTR_SELECT_BY_EXAMPLE_STATEMENT_ID = new IntrospectedTable$InternalAttribute("ATTR_SELECT_BY_EXAMPLE_STATEMENT_ID", 19);
        ATTR_SELECT_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID = new IntrospectedTable$InternalAttribute("ATTR_SELECT_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID", 20);
        ATTR_SELECT_BY_PRIMARY_KEY_STATEMENT_ID = new IntrospectedTable$InternalAttribute("ATTR_SELECT_BY_PRIMARY_KEY_STATEMENT_ID", 21);
        ATTR_UPDATE_BY_EXAMPLE_STATEMENT_ID = new IntrospectedTable$InternalAttribute("ATTR_UPDATE_BY_EXAMPLE_STATEMENT_ID", 22);
        ATTR_UPDATE_BY_EXAMPLE_SELECTIVE_STATEMENT_ID = new IntrospectedTable$InternalAttribute("ATTR_UPDATE_BY_EXAMPLE_SELECTIVE_STATEMENT_ID", 23);
        ATTR_UPDATE_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID = new IntrospectedTable$InternalAttribute("ATTR_UPDATE_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID", 24);
        ATTR_UPDATE_BY_PRIMARY_KEY_STATEMENT_ID = new IntrospectedTable$InternalAttribute("ATTR_UPDATE_BY_PRIMARY_KEY_STATEMENT_ID", 25);
        ATTR_UPDATE_BY_PRIMARY_KEY_SELECTIVE_STATEMENT_ID = new IntrospectedTable$InternalAttribute("ATTR_UPDATE_BY_PRIMARY_KEY_SELECTIVE_STATEMENT_ID", 26);
        ATTR_UPDATE_BY_PRIMARY_KEY_WITH_BLOBS_STATEMENT_ID = new IntrospectedTable$InternalAttribute("ATTR_UPDATE_BY_PRIMARY_KEY_WITH_BLOBS_STATEMENT_ID", 27);
        ATTR_BASE_RESULT_MAP_ID = new IntrospectedTable$InternalAttribute("ATTR_BASE_RESULT_MAP_ID", 28);
        ATTR_RESULT_MAP_WITH_BLOBS_ID = new IntrospectedTable$InternalAttribute("ATTR_RESULT_MAP_WITH_BLOBS_ID", 29);
        ATTR_EXAMPLE_WHERE_CLAUSE_ID = new IntrospectedTable$InternalAttribute("ATTR_EXAMPLE_WHERE_CLAUSE_ID", 30);
        ATTR_BASE_COLUMN_LIST_ID = new IntrospectedTable$InternalAttribute("ATTR_BASE_COLUMN_LIST_ID", 31);
        ATTR_BLOB_COLUMN_LIST_ID = new IntrospectedTable$InternalAttribute("ATTR_BLOB_COLUMN_LIST_ID", 32);
        ATTR_MYBATIS3_UPDATE_BY_EXAMPLE_WHERE_CLAUSE_ID = new IntrospectedTable$InternalAttribute("ATTR_MYBATIS3_UPDATE_BY_EXAMPLE_WHERE_CLAUSE_ID", 33);
        ENUM$VALUES = (new IntrospectedTable$InternalAttribute[] {
            ATTR_DAO_IMPLEMENTATION_TYPE, ATTR_DAO_INTERFACE_TYPE, ATTR_PRIMARY_KEY_TYPE, ATTR_BASE_RECORD_TYPE, ATTR_RECORD_WITH_BLOBS_TYPE, ATTR_EXAMPLE_TYPE, ATTR_IBATIS2_SQL_MAP_PACKAGE, ATTR_IBATIS2_SQL_MAP_FILE_NAME, ATTR_IBATIS2_SQL_MAP_NAMESPACE, ATTR_MYBATIS3_XML_MAPPER_PACKAGE, 
            ATTR_MYBATIS3_XML_MAPPER_FILE_NAME, ATTR_MYBATIS3_JAVA_MAPPER_TYPE, ATTR_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME, ATTR_ALIASED_FULLY_QUALIFIED_TABLE_NAME_AT_RUNTIME, ATTR_COUNT_BY_EXAMPLE_STATEMENT_ID, ATTR_DELETE_BY_EXAMPLE_STATEMENT_ID, ATTR_DELETE_BY_PRIMARY_KEY_STATEMENT_ID, ATTR_INSERT_STATEMENT_ID, ATTR_INSERT_SELECTIVE_STATEMENT_ID, ATTR_SELECT_BY_EXAMPLE_STATEMENT_ID, 
            ATTR_SELECT_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID, ATTR_SELECT_BY_PRIMARY_KEY_STATEMENT_ID, ATTR_UPDATE_BY_EXAMPLE_STATEMENT_ID, ATTR_UPDATE_BY_EXAMPLE_SELECTIVE_STATEMENT_ID, ATTR_UPDATE_BY_EXAMPLE_WITH_BLOBS_STATEMENT_ID, ATTR_UPDATE_BY_PRIMARY_KEY_STATEMENT_ID, ATTR_UPDATE_BY_PRIMARY_KEY_SELECTIVE_STATEMENT_ID, ATTR_UPDATE_BY_PRIMARY_KEY_WITH_BLOBS_STATEMENT_ID, ATTR_BASE_RESULT_MAP_ID, ATTR_RESULT_MAP_WITH_BLOBS_ID, 
            ATTR_EXAMPLE_WHERE_CLAUSE_ID, ATTR_BASE_COLUMN_LIST_ID, ATTR_BLOB_COLUMN_LIST_ID, ATTR_MYBATIS3_UPDATE_BY_EXAMPLE_WHERE_CLAUSE_ID
        });
    }

    private IntrospectedTable$InternalAttribute(String s, int i)
    {
        super(s, i);
    }
}
