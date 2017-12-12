// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NameRuler.java

package com.fap.generartor.api;

import java.io.PrintStream;

public class NameRuler
{

    public NameRuler()
    {
    }

    public static String classNameRuler(String name)
    {
        String _name = name.toLowerCase();
        if(_name.contains("_"))
        {
            String name_[] = _name.split("_");
            _name = "";
            for(int i = 0; i < name_.length; i++)
            {
                name_[i] = (new StringBuilder(String.valueOf(name_[i].substring(0, 1).toUpperCase()))).append(name_[i].substring(1)).toString();
                _name = (new StringBuilder(String.valueOf(_name))).append(name_[i]).toString();
            }

        } else
        {
            _name = (new StringBuilder(String.valueOf(_name.substring(0, 1).toUpperCase()))).append(_name.substring(1)).toString();
        }
        return _name;
    }

    public static String fieldNameRuler(String name)
    {
        System.out.println(name);
        String _name = name.toLowerCase();
        if(_name.contains("_"))
        {
            String name_[] = _name.split("_");
            _name = name_[0];
            for(int i = 1; i < name_.length; i++)
            {
                name_[i] = (new StringBuilder(String.valueOf(name_[i].substring(0, 1).toUpperCase()))).append(name_[i].substring(1)).toString();
                _name = (new StringBuilder(String.valueOf(_name))).append(name_[i]).toString();
            }

        }
        return _name;
    }

    public static String columnNameRuler(String name)
    {
        String _name = name.toLowerCase();
        _name = _name.substring(_name.indexOf("_") + 1);
        if(_name.contains("_"))
        {
            String name_[] = _name.split("_");
            _name = name_[0];
            for(int i = 1; i < name_.length; i++)
            {
                name_[i] = (new StringBuilder(String.valueOf(name_[i].substring(0, 1).toUpperCase()))).append(name_[i].substring(1)).toString();
                _name = (new StringBuilder(String.valueOf(_name))).append(name_[i]).toString();
            }

        }
        String reservedWords[] = {
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", 
            "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", 
            "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", 
            "new", "package", "private", "protected", "public", "return", "strictfp", "short", "static", "super", 
            "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while"
        };
        String changeWords[] = {
            "abstractz", "assertz", "booleanz", "breaked", "bytes", "cases", "cateched", "chars", "clazz", "consts", 
            "continued", "defaultz", "doz", "doublez", "elses", "enums", "extendz", "finals", "finallyz", "floatz", 
            "fors", "gotoed", "ifz", "implementz", "imports", "instanceofed", "ints", "interfaces", "longs", "natives", 
            "news", "packages", "privates", "protecteds", "publics", "returns", "strictfps", "shorts", "statics", "supers", 
            "switchz", "synchronizedz", "thiz", "throwes", "throwz", "transients", "tryed", "voids", "volatiles", "whiles"
        };
        for(int i = 0; i < reservedWords.length; i++)
        {
            if(!reservedWords[i].equalsIgnoreCase(_name))
                continue;
            _name = changeWords[i];
            break;
        }

        return _name;
    }

    public static String firstCaseToUpper(String str)
    {
        String _str = str.substring(0, 1);
        return (new StringBuilder(String.valueOf(_str))).append(str.substring(1)).toString();
    }
}
