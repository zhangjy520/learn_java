// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TreeView.java

package com.fap.ide.views;

import org.eclipse.core.runtime.IAdaptable;

// Referenced classes of package com.fap.ide.views:
//            TreeView

class TreeView$TreeObject
    implements IAdaptable
{

    public String getName()
    {
        return name;
    }

    public void setParent(TreeView$TreeParent parent)
    {
        this.parent = parent;
    }

    public TreeView$TreeParent getParent()
    {
        return parent;
    }

    public String toString()
    {
        return getName();
    }

    public Object getAdapter(Class key)
    {
        return null;
    }

    private String name;
    private TreeView$TreeParent parent;
    final TreeView this$0;


    public TreeView$TreeObject(String name)
    {
        this$0 = TreeView.this;
        super();
        this.name = name;
    }
}
