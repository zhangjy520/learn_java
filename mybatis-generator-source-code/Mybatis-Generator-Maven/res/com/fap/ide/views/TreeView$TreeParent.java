// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TreeView.java

package com.fap.ide.views;

import java.util.ArrayList;

// Referenced classes of package com.fap.ide.views:
//            TreeView

class TreeView$TreeParent extends TreeView$TreeObject
{

    public void addChild(TreeView$TreeObject child)
    {
        children.add(child);
        child.setParent(this);
    }

    public void removeChild(TreeView$TreeObject child)
    {
        children.remove(child);
        child.setParent(null);
    }

    public TreeView$TreeObject[] getChildren()
    {
        return (TreeView$TreeObject[])children.toArray(new TreeView$TreeObject[children.size()]);
    }

    public boolean hasChildren()
    {
        return children.size() > 0;
    }

    private ArrayList children;
    final TreeView this$0;

    public TreeView$TreeParent(String name)
    {
        this$0 = TreeView.this;
        super(TreeView.this, name);
        children = new ArrayList();
    }
}
