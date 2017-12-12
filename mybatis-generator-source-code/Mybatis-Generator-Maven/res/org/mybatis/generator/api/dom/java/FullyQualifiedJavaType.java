// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FullyQualifiedJavaType.java

package org.mybatis.generator.api.dom.java;

import java.util.*;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

// Referenced classes of package org.mybatis.generator.api.dom.java:
//            PrimitiveTypeWrapper

public class FullyQualifiedJavaType
    implements Comparable
{

    public FullyQualifiedJavaType(String fullTypeSpecification)
    {
        typeArguments = new ArrayList();
        parse(fullTypeSpecification);
    }

    public boolean isExplicitlyImported()
    {
        return explicitlyImported;
    }

    public String getFullyQualifiedName()
    {
        StringBuilder sb = new StringBuilder();
        if(wildcardType)
        {
            sb.append('?');
            if(boundedWildcard)
            {
                if(extendsBoundedWildcard)
                    sb.append(" extends ");
                else
                    sb.append(" super ");
                sb.append(baseQualifiedName);
            }
        } else
        {
            sb.append(baseQualifiedName);
        }
        if(typeArguments.size() > 0)
        {
            boolean first = true;
            sb.append('<');
            FullyQualifiedJavaType fqjt;
            for(Iterator iterator = typeArguments.iterator(); iterator.hasNext(); sb.append(fqjt.getFullyQualifiedName()))
            {
                fqjt = (FullyQualifiedJavaType)iterator.next();
                if(first)
                    first = false;
                else
                    sb.append(", ");
            }

            sb.append('>');
        }
        return sb.toString();
    }

    public List getImportList()
    {
        List answer = new ArrayList();
        if(isExplicitlyImported())
        {
            int index = baseShortName.indexOf('.');
            if(index == -1)
            {
                answer.add(baseQualifiedName);
            } else
            {
                StringBuilder sb = new StringBuilder();
                sb.append(packageName);
                sb.append('.');
                sb.append(baseShortName.substring(0, index));
                answer.add(sb.toString());
            }
        }
        FullyQualifiedJavaType fqjt;
        for(Iterator iterator = typeArguments.iterator(); iterator.hasNext(); answer.addAll(fqjt.getImportList()))
            fqjt = (FullyQualifiedJavaType)iterator.next();

        return answer;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public String getShortName()
    {
        StringBuilder sb = new StringBuilder();
        if(wildcardType)
        {
            sb.append('?');
            if(boundedWildcard)
            {
                if(extendsBoundedWildcard)
                    sb.append(" extends ");
                else
                    sb.append(" super ");
                sb.append(baseShortName);
            }
        } else
        {
            sb.append(baseShortName);
        }
        if(typeArguments.size() > 0)
        {
            boolean first = true;
            sb.append('<');
            FullyQualifiedJavaType fqjt;
            for(Iterator iterator = typeArguments.iterator(); iterator.hasNext(); sb.append(fqjt.getShortName()))
            {
                fqjt = (FullyQualifiedJavaType)iterator.next();
                if(first)
                    first = false;
                else
                    sb.append(", ");
            }

            sb.append('>');
        }
        return sb.toString();
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(!(obj instanceof FullyQualifiedJavaType))
        {
            return false;
        } else
        {
            FullyQualifiedJavaType other = (FullyQualifiedJavaType)obj;
            return getFullyQualifiedName().equals(other.getFullyQualifiedName());
        }
    }

    public int hashCode()
    {
        return getFullyQualifiedName().hashCode();
    }

    public String toString()
    {
        return getFullyQualifiedName();
    }

    public boolean isPrimitive()
    {
        return primitive;
    }

    public PrimitiveTypeWrapper getPrimitiveTypeWrapper()
    {
        return primitiveTypeWrapper;
    }

    public static final FullyQualifiedJavaType getIntInstance()
    {
        if(intInstance == null)
            intInstance = new FullyQualifiedJavaType("int");
        return intInstance;
    }

    public static final FullyQualifiedJavaType getNewMapInstance()
    {
        return new FullyQualifiedJavaType("java.util.Map");
    }

    public static final FullyQualifiedJavaType getNewListInstance()
    {
        return new FullyQualifiedJavaType("java.util.List");
    }

    public static final FullyQualifiedJavaType getNewHashMapInstance()
    {
        return new FullyQualifiedJavaType("java.util.HashMap");
    }

    public static final FullyQualifiedJavaType getNewArrayListInstance()
    {
        return new FullyQualifiedJavaType("java.util.ArrayList");
    }

    public static final FullyQualifiedJavaType getNewIteratorInstance()
    {
        return new FullyQualifiedJavaType("java.util.Iterator");
    }

    public static final FullyQualifiedJavaType getStringInstance()
    {
        if(stringInstance == null)
            stringInstance = new FullyQualifiedJavaType("java.lang.String");
        return stringInstance;
    }

    public static final FullyQualifiedJavaType getBooleanPrimitiveInstance()
    {
        if(booleanPrimitiveInstance == null)
            booleanPrimitiveInstance = new FullyQualifiedJavaType("boolean");
        return booleanPrimitiveInstance;
    }

    public static final FullyQualifiedJavaType getObjectInstance()
    {
        if(objectInstance == null)
            objectInstance = new FullyQualifiedJavaType("java.lang.Object");
        return objectInstance;
    }

    public static final FullyQualifiedJavaType getDateInstance()
    {
        if(dateInstance == null)
            dateInstance = new FullyQualifiedJavaType("java.util.Date");
        return dateInstance;
    }

    public static final FullyQualifiedJavaType getCriteriaInstance()
    {
        if(criteriaInstance == null)
            criteriaInstance = new FullyQualifiedJavaType("Criteria");
        return criteriaInstance;
    }

    public static final FullyQualifiedJavaType getGeneratedCriteriaInstance()
    {
        if(generatedCriteriaInstance == null)
            generatedCriteriaInstance = new FullyQualifiedJavaType("GeneratedCriteria");
        return generatedCriteriaInstance;
    }

    public int compareTo(FullyQualifiedJavaType other)
    {
        return getFullyQualifiedName().compareTo(other.getFullyQualifiedName());
    }

    public void addTypeArgument(FullyQualifiedJavaType type)
    {
        typeArguments.add(type);
    }

    private void parse(String fullTypeSpecification)
    {
        String spec = fullTypeSpecification.trim();
        if(spec.startsWith("?"))
        {
            wildcardType = true;
            spec = spec.substring(1).trim();
            if(spec.startsWith("extends "))
            {
                boundedWildcard = true;
                extendsBoundedWildcard = true;
                spec = spec.substring(8);
            } else
            if(spec.startsWith("super "))
            {
                boundedWildcard = true;
                extendsBoundedWildcard = false;
                spec = spec.substring(6);
            } else
            {
                boundedWildcard = false;
            }
            parse(spec);
        } else
        {
            int index = fullTypeSpecification.indexOf('<');
            if(index == -1)
            {
                simpleParse(fullTypeSpecification);
            } else
            {
                simpleParse(fullTypeSpecification.substring(0, index));
                genericParse(fullTypeSpecification.substring(index));
            }
        }
    }

    private void simpleParse(String typeSpecification)
    {
        baseQualifiedName = typeSpecification.trim();
        if(baseQualifiedName.contains("."))
        {
            packageName = getPackage(baseQualifiedName);
            baseShortName = baseQualifiedName.substring(packageName.length() + 1);
            if("java.lang".equals(packageName))
                explicitlyImported = false;
            else
                explicitlyImported = true;
        } else
        {
            baseShortName = baseQualifiedName;
            explicitlyImported = false;
            packageName = "";
            if("byte".equals(baseQualifiedName))
            {
                primitive = true;
                primitiveTypeWrapper = PrimitiveTypeWrapper.getByteInstance();
            } else
            if("short".equals(baseQualifiedName))
            {
                primitive = true;
                primitiveTypeWrapper = PrimitiveTypeWrapper.getShortInstance();
            } else
            if("int".equals(baseQualifiedName))
            {
                primitive = true;
                primitiveTypeWrapper = PrimitiveTypeWrapper.getIntegerInstance();
            } else
            if("long".equals(baseQualifiedName))
            {
                primitive = true;
                primitiveTypeWrapper = PrimitiveTypeWrapper.getLongInstance();
            } else
            if("char".equals(baseQualifiedName))
            {
                primitive = true;
                primitiveTypeWrapper = PrimitiveTypeWrapper.getCharacterInstance();
            } else
            if("float".equals(baseQualifiedName))
            {
                primitive = true;
                primitiveTypeWrapper = PrimitiveTypeWrapper.getFloatInstance();
            } else
            if("double".equals(baseQualifiedName))
            {
                primitive = true;
                primitiveTypeWrapper = PrimitiveTypeWrapper.getDoubleInstance();
            } else
            if("boolean".equals(baseQualifiedName))
            {
                primitive = true;
                primitiveTypeWrapper = PrimitiveTypeWrapper.getBooleanInstance();
            } else
            {
                primitive = false;
                primitiveTypeWrapper = null;
            }
        }
    }

    private void genericParse(String genericSpecification)
    {
        int lastIndex = genericSpecification.lastIndexOf('>');
        if(lastIndex == -1)
            throw new RuntimeException(Messages.getString("RuntimeError.22", genericSpecification));
        String argumentString = genericSpecification.substring(1, lastIndex);
        StringTokenizer st = new StringTokenizer(argumentString, ",<>", true);
        int openCount = 0;
        StringBuilder sb = new StringBuilder();
        while(st.hasMoreTokens()) 
        {
            String token = st.nextToken();
            if("<".equals(token))
            {
                sb.append(token);
                openCount++;
            } else
            if(">".equals(token))
            {
                sb.append(token);
                openCount--;
            } else
            if(",".equals(token))
            {
                if(openCount == 0)
                {
                    typeArguments.add(new FullyQualifiedJavaType(sb.toString()));
                    sb.setLength(0);
                } else
                {
                    sb.append(token);
                }
            } else
            {
                sb.append(token);
            }
        }
        if(openCount != 0)
            throw new RuntimeException(Messages.getString("RuntimeError.22", genericSpecification));
        String finalType = sb.toString();
        if(StringUtility.stringHasValue(finalType))
            typeArguments.add(new FullyQualifiedJavaType(finalType));
    }

    private static String getPackage(String baseQualifiedName)
    {
        StringBuilder sb = new StringBuilder();
        String s;
        for(StringTokenizer st = new StringTokenizer(baseQualifiedName, "."); st.hasMoreTokens(); sb.append(s))
        {
            s = st.nextToken();
            if(Character.isUpperCase(s.charAt(0)))
                break;
            if(sb.length() > 0)
                sb.append('.');
        }

        return sb.toString();
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((FullyQualifiedJavaType)obj);
    }

    private static FullyQualifiedJavaType intInstance = null;
    private static FullyQualifiedJavaType stringInstance = null;
    private static FullyQualifiedJavaType booleanPrimitiveInstance = null;
    private static FullyQualifiedJavaType objectInstance = null;
    private static FullyQualifiedJavaType dateInstance = null;
    private static FullyQualifiedJavaType criteriaInstance = null;
    private static FullyQualifiedJavaType generatedCriteriaInstance = null;
    private String baseShortName;
    private String baseQualifiedName;
    private boolean explicitlyImported;
    private String packageName;
    private boolean primitive;
    private PrimitiveTypeWrapper primitiveTypeWrapper;
    private List typeArguments;
    private boolean wildcardType;
    private boolean boundedWildcard;
    private boolean extendsBoundedWildcard;

}
