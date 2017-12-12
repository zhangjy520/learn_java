// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TreeView.java

package com.fap.ide.views;

import com.fap.generartor.api.PlugInAPI;
import java.util.*;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.*;

// Referenced classes of package com.fap.ide.views:
//            TreeView

class TreeView$2 extends Action
{

    public void run()
    {
        TreeView.access$3(TreeView.this, "Generate [Package] Begin ...");
        ISelection selection = TreeView.access$4(TreeView.this).getSelection();
        Iterator selectList = ((IStructuredSelection)selection).toList().iterator();
        PlugInAPI plugInApi = TreeView.access$0(TreeView.this);
        ArrayList selectPackages = new ArrayList();
        while(selectList.hasNext()) 
        {
            Object obj = selectList.next();
            if(obj instanceof eeObject)
            {
                eeObject treeobj = (eeObject)obj;
                if(eeObject.access._mth0(treeobj).getName().toLowerCase().equals("packages"))
                    selectPackages.add(treeobj.getName());
            }
        }
    }

    final TreeView this$0;

    TreeView$2()
    {
        this$0 = TreeView.this;
        super();
    }
}
