// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TreeView.java

package com.fap.ide.views;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.ui.*;

// Referenced classes of package com.fap.ide.views:
//            TreeView

class TreeView$ViewLabelProvider extends LabelProvider
{

    public String getText(Object obj)
    {
        return obj.toString();
    }

    public Image getImage(Object obj)
    {
        String imgFile = "";
        if(obj instanceof TreeView$TreeParent)
        {
            TreeView$TreeParent treeParent = (>)obj;
            if(treeParent.().toLowerCase().equals("fap ide"))
                imgFile = dbImg;
            else
                imgFile = folderImg;
        }
        if(obj instanceof )
        {
            TreeView$TreeObject treeobj = ()obj;
            if(_mth0(treeobj).().toLowerCase().equals("packages"))
                imgFile = packageImg;
            else
            if(_mth0(treeobj).().toLowerCase().equals("tables"))
                imgFile = tableImg;
        }
        ImageLoader imgl = new ImageLoader();
        org.eclipse.swt.graphics.ImageData imgdata[] = imgl.load((new StringBuilder(String.valueOf(TreeView.access$1(TreeView.this)))).append(imgFile).toString());
        if(imgdata.length > 0)
            return new Image(null, imgdata[0]);
        else
            return PlatformUI.getWorkbench().getSharedImages().getImage("IMG_OBJ_ELEMENTS");
    }

    final TreeView this$0;

    TreeView$ViewLabelProvider()
    {
        this$0 = TreeView.this;
        super();
    }
}
