// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JavaBeansUtil.java

package org.mybatis.generator.internal.util;

import java.util.Locale;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

public class JavaBeansUtil
{

    private JavaBeansUtil()
    {
    }

    public static String getGetterMethodName(String property, FullyQualifiedJavaType fullyQualifiedJavaType)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(property);
        if(Character.isLowerCase(sb.charAt(0)) && (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))))
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        if(fullyQualifiedJavaType.equals(FullyQualifiedJavaType.getBooleanPrimitiveInstance()))
            sb.insert(0, "is");
        else
            sb.insert(0, "get");
        return sb.toString();
    }

    public static String getSetterMethodName(String property)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(property);
        if(Character.isLowerCase(sb.charAt(0)) && (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))))
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        sb.insert(0, "set");
        return sb.toString();
    }

    public static String getCamelCaseString(String inputString, boolean firstCharacterUppercase)
    {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for(int i = 0; i < inputString.length(); i++)
        {
            char c = inputString.charAt(i);
            switch(c)
            {
            case 32: // ' '
            case 35: // '#'
            case 36: // '$'
            case 38: // '&'
            case 45: // '-'
            case 47: // '/'
            case 64: // '@'
            case 95: // '_'
                if(sb.length() > 0)
                    nextUpperCase = true;
                break;

            default:
                if(nextUpperCase)
                {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else
                {
                    sb.append(Character.toLowerCase(c));
                }
                break;
            }
        }

        if(firstCharacterUppercase)
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    public static String getValidPropertyName(String inputString)
    {
        String answer;
        if(inputString == null)
            answer = null;
        else
        if(inputString.length() < 2)
            answer = inputString.toLowerCase(Locale.US);
        else
        if(Character.isUpperCase(inputString.charAt(0)) && !Character.isUpperCase(inputString.charAt(1)))
            answer = (new StringBuilder(String.valueOf(inputString.substring(0, 1).toLowerCase(Locale.US)))).append(inputString.substring(1)).toString();
        else
            answer = inputString;
        return answer;
    }
}
