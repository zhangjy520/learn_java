// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TreeView.java

package com.fap.ide.views;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;

// Referenced classes of package com.fap.ide.views:
//            TreeView

class TreeView$1
    implements IMenuListener
{

    public void menuAboutToShow(IMenuManager manager)
    {
        TreeView.access$2(TreeView.this, manager);
    }

    final TreeView this$0;

    TreeView$1()
    {
        this$0 = TreeView.this;
        super();
    }
}
