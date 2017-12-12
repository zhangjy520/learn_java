// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TreeView.java

package com.fap.ide.views;

import com.fap.generartor.api.PlugInAPI;
import java.util.Iterator;
import java.util.List;
import org.eclipse.jface.viewers.*;

// Referenced classes of package com.fap.ide.views:
//            TreeView

class TreeView$ViewContentProvider
    implements IStructuredContentProvider, ITreeContentProvider
{

    public void inputChanged(Viewer viewer, Object obj, Object obj1)
    {
    }

    public void dispose()
    {
    }

    public Object[] getElements(Object parent)
    {
        if(parent.equals(getViewSite()))
        {
            if(invisibleRoot == null)
                initialize();
            return getChildren(invisibleRoot);
        } else
        {
            return getChildren(parent);
        }
    }

    public Object getParent(Object child)
    {
        if(child instanceof TreeView$TreeObject)
            return ((TreeView$TreeObject)child).();
        else
            return null;
    }

    public Object[] getChildren(Object parent)
    {
        if(parent instanceof TreeView$TreeParent)
            return ((TreeView$TreeParent)parent).en();
        else
            return new Object[0];
    }

    public boolean hasChildren(Object parent)
    {
        if(parent instanceof TreeView$TreeParent)
            return ((TreeView$TreeParent)parent).en();
        else
            return false;
    }

    private void initialize()
    {
        PlugInAPI plugInApi = TreeView.access$0(TreeView.this);
        TreeView$TreeParent p1 = new TreeView$TreeParent(TreeView.this, "Tables");
        for(Iterator tables = plugInApi.getTableNames().iterator(); tables.hasNext(); p1.addChild(new TreeView$TreeObject(TreeView.this, (String)tables.next())));
        TreeView$TreeParent root = new this._cls0(TreeView.this, "FAP IDE");
        root.addChild(p1);
        invisibleRoot = new invisibleRoot(TreeView.this, "");
        invisibleRoot.addChild(root);
    }

    private invisibleRoot invisibleRoot;
    final TreeView this$0;

    TreeView$ViewContentProvider()
    {
        this$0 = TreeView.this;
        super();
    }
}
