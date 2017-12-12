// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TreeView.java

package com.fap.ide.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;

// Referenced classes of package com.fap.ide.views:
//            TreeView

class TreeView$5
    implements IDoubleClickListener
{

    public void doubleClick(DoubleClickEvent event)
    {
        TreeView.access$5(TreeView.this).run();
    }

    final TreeView this$0;

    TreeView$5()
    {
        this$0 = TreeView.this;
        super();
    }
}
