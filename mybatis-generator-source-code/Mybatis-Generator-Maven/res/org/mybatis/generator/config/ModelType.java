// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModelType.java

package org.mybatis.generator.config;

import org.mybatis.generator.internal.util.messages.Messages;

public final class ModelType extends Enum
{

    private ModelType(String s, int i, String modelType)
    {
        super(s, i);
        this.modelType = modelType;
    }

    public String getModelType()
    {
        return modelType;
    }

    public static ModelType getModelType(String type)
    {
        if(HIERARCHICAL.getModelType().equalsIgnoreCase(type))
            return HIERARCHICAL;
        if(FLAT.getModelType().equalsIgnoreCase(type))
            return FLAT;
        if(CONDITIONAL.getModelType().equalsIgnoreCase(type))
            return CONDITIONAL;
        else
            throw new RuntimeException(Messages.getString("RuntimeError.13", type));
    }

    public static ModelType[] values()
    {
        ModelType amodeltype[];
        int i;
        ModelType amodeltype1[];
        System.arraycopy(amodeltype = ENUM$VALUES, 0, amodeltype1 = new ModelType[i = amodeltype.length], 0, i);
        return amodeltype1;
    }

    public static ModelType valueOf(String s)
    {
        return (ModelType)Enum.valueOf(org/mybatis/generator/config/ModelType, s);
    }

    public static final ModelType HIERARCHICAL;
    public static final ModelType FLAT;
    public static final ModelType CONDITIONAL;
    private final String modelType;
    private static final ModelType ENUM$VALUES[];

    static 
    {
        HIERARCHICAL = new ModelType("HIERARCHICAL", 0, "hierarchical");
        FLAT = new ModelType("FLAT", 1, "flat");
        CONDITIONAL = new ModelType("CONDITIONAL", 2, "conditional");
        ENUM$VALUES = (new ModelType[] {
            HIERARCHICAL, FLAT, CONDITIONAL
        });
    }
}
