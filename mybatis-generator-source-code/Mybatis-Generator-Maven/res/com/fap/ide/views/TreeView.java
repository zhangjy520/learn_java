// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TreeView.java

package com.fap.ide.views;

import com.fap.generartor.api.PlugInAPI;
import com.fap.ide.Activator;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.*;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.*;
import org.eclipse.ui.console.*;
import org.eclipse.ui.help.IWorkbenchHelpSystem;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;

public class TreeView extends ViewPart
{
    class NameSorter extends ViewerSorter
    {

        final TreeView this$0;

        NameSorter()
        {
            this$0 = TreeView.this;
            super();
        }
    }

    class TreeObject
        implements IAdaptable
    {

        public String getName()
        {
            return name;
        }

        public void setParent(TreeParent parent)
        {
            this.parent = parent;
        }

        public TreeParent getParent()
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
        private TreeParent parent;
        final TreeView this$0;


        public TreeObject(String name)
        {
            this$0 = TreeView.this;
            super();
            this.name = name;
        }
    }

    class TreeParent extends TreeObject
    {

        public void addChild(TreeObject child)
        {
            children.add(child);
            child.setParent(this);
        }

        public void removeChild(TreeObject child)
        {
            children.remove(child);
            child.setParent(null);
        }

        public TreeObject[] getChildren()
        {
            return (TreeObject[])children.toArray(new TreeObject[children.size()]);
        }

        public boolean hasChildren()
        {
            return children.size() > 0;
        }

        private ArrayList children;
        final TreeView this$0;

        public TreeParent(String name)
        {
            this$0 = TreeView.this;
            super(name);
            children = new ArrayList();
        }
    }

    class ViewContentProvider
        implements IStructuredContentProvider, ITreeContentProvider
    {

        public void inputChanged(Viewer viewer1, Object obj, Object obj1)
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
            if(child instanceof TreeObject)
                return ((TreeObject)child).getParent();
            else
                return null;
        }

        public Object[] getChildren(Object parent)
        {
            if(parent instanceof TreeParent)
                return ((TreeParent)parent).getChildren();
            else
                return new Object[0];
        }

        public boolean hasChildren(Object parent)
        {
            if(parent instanceof TreeParent)
                return ((TreeParent)parent).hasChildren();
            else
                return false;
        }

        private void initialize()
        {
            PlugInAPI plugInApi = getPlugInAPIInstant();
            TreeParent p1 = new TreeParent("Tables");
            for(Iterator tables = plugInApi.getTableNames().iterator(); tables.hasNext(); p1.addChild(new TreeObject((String)tables.next())));
            TreeParent root = new TreeParent("FAP IDE");
            root.addChild(p1);
            invisibleRoot = new TreeParent("");
            invisibleRoot.addChild(root);
        }

        private TreeParent invisibleRoot;
        final TreeView this$0;

        ViewContentProvider()
        {
            this$0 = TreeView.this;
            super();
        }
    }

    class ViewLabelProvider extends LabelProvider
    {

        public String getText(Object obj)
        {
            return obj.toString();
        }

        public Image getImage(Object obj)
        {
            String imgFile = "";
            if(obj instanceof TreeParent)
            {
                TreeParent treeParent = (TreeParent)obj;
                if(treeParent.getName().toLowerCase().equals("fap ide"))
                    imgFile = dbImg;
                else
                    imgFile = folderImg;
            }
            if(obj instanceof TreeObject)
            {
                TreeObject treeobj = (TreeObject)obj;
                if(treeobj.parent.getName().toLowerCase().equals("packages"))
                    imgFile = packageImg;
                else
                if(treeobj.parent.getName().toLowerCase().equals("tables"))
                    imgFile = tableImg;
            }
            ImageLoader imgl = new ImageLoader();
            org.eclipse.swt.graphics.ImageData imgdata[] = imgl.load((new StringBuilder(String.valueOf(getPlugInPath()))).append(imgFile).toString());
            if(imgdata.length > 0)
                return new Image(null, imgdata[0]);
            else
                return PlatformUI.getWorkbench().getSharedImages().getImage("IMG_OBJ_ELEMENTS");
        }

        final TreeView this$0;

        ViewLabelProvider()
        {
            this$0 = TreeView.this;
            super();
        }
    }


    public TreeView()
    {
        tableImg = "/icons/table.png";
        folderImg = "/icons/group.png";
        packageImg = "/icons/package.png";
        dbImg = "/icons/db.png";
        printer = createConsole("FAPConsole");
    }

    public void createPartControl(Composite parent)
    {
        viewer = new TreeViewer(parent, 770);
        drillDownAdapter = new DrillDownAdapter(viewer);
        viewer.setContentProvider(new ViewContentProvider());
        viewer.setLabelProvider(new ViewLabelProvider());
        viewer.setSorter(new NameSorter());
        viewer.setInput(getViewSite());
        PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "com.fap.ide.test.viewer");
        makeActions();
        hookContextMenu();
        hookDoubleClickAction();
    }

    private void hookContextMenu()
    {
        MenuManager menuMgr = new MenuManager("#PopupMenu");
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {

            public void menuAboutToShow(IMenuManager manager)
            {
                fillContextMenu(manager);
            }

            final TreeView this$0;

            
            {
                this$0 = TreeView.this;
                super();
            }
        });
        org.eclipse.swt.widgets.Menu menu = menuMgr.createContextMenu(viewer.getControl());
        viewer.getControl().setMenu(menu);
        getSite().registerContextMenu(menuMgr, viewer);
    }

    private void contributeToActionBars()
    {
        IActionBars bars = getViewSite().getActionBars();
        fillLocalPullDown(bars.getMenuManager());
        fillLocalToolBar(bars.getToolBarManager());
    }

    private void fillLocalPullDown(IMenuManager manager)
    {
        manager.add(action1);
        manager.add(new Separator());
        manager.add(action2);
    }

    private void fillContextMenu(IMenuManager manager)
    {
        TreeSelection select = (TreeSelection)viewer.getSelection();
        Object obj = select.getFirstElement();
        if(obj instanceof TreeParent)
            return;
        if(obj instanceof TreeObject)
        {
            TreeObject treeobj = (TreeObject)obj;
            if(treeobj.parent.getName().toLowerCase().equals("packages"))
                manager.add(action1);
            else
            if(treeobj.parent.getName().toLowerCase().equals("tables"))
                manager.add(action2);
        }
    }

    private void fillLocalToolBar(IToolBarManager manager)
    {
        manager.add(action1);
        manager.add(action2);
    }

    private void makeActions()
    {
        action1 = new Action() {

            public void run()
            {
                printMsg("Generate [Package] Begin ...");
                ISelection selection = viewer.getSelection();
                Iterator selectList = ((IStructuredSelection)selection).toList().iterator();
                PlugInAPI plugInApi = getPlugInAPIInstant();
                ArrayList selectPackages = new ArrayList();
                while(selectList.hasNext()) 
                {
                    Object obj = selectList.next();
                    if(obj instanceof TreeObject)
                    {
                        TreeObject treeobj = (TreeObject)obj;
                        if(treeobj.parent.getName().toLowerCase().equals("packages"))
                            selectPackages.add(treeobj.getName());
                    }
                }
            }

            final TreeView this$0;

            
            {
                this$0 = TreeView.this;
                super();
            }
        };
        action1.setText("Generate [Package]");
        action1.setToolTipText("");
        action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor("IMG_OBJS_INFO_TSK"));
        action2 = new Action() {

            public void run()
            {
                printMsg("Generate [Table] Begin ...");
                ISelection selection = viewer.getSelection();
                Iterator selectList = ((IStructuredSelection)selection).toList().iterator();
                PlugInAPI plugInApi = getPlugInAPIInstant();
                ArrayList selectTables = new ArrayList();
                while(selectList.hasNext()) 
                {
                    Object obj = selectList.next();
                    if(obj instanceof TreeObject)
                    {
                        TreeObject treeobj = (TreeObject)obj;
                        if(treeobj.parent.getName().toLowerCase().equals("tables"))
                            selectTables.add(treeobj.getName());
                    }
                }
                plugInApi.genTableFilesAllByOne(selectTables);
                printMsg("Generate [Table] End");
            }

            final TreeView this$0;

            {
                this$0 = TreeView.this;
                super();
            }
        };
        action2.setText("Generate [Table]");
        action2.setToolTipText("");
        action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor("IMG_OBJS_INFO_TSK"));
        doubleClickAction = new Action() {

            public void run()
            {
            }

            final TreeView this$0;

            
            {
                this$0 = TreeView.this;
                super();
            }
        };
    }

    private void hookDoubleClickAction()
    {
        viewer.addDoubleClickListener(new IDoubleClickListener() {

            public void doubleClick(DoubleClickEvent event)
            {
                doubleClickAction.run();
            }

            final TreeView this$0;

            
            {
                this$0 = TreeView.this;
                super();
            }
        });
    }

    private void showMessage(String message)
    {
        MessageDialog.openInformation(viewer.getControl().getShell(), "Sample View", message);
    }

    public void setFocus()
    {
        viewer.getControl().setFocus();
    }

    private String getPlugInPath()
    {
        String path = "";
        try
        {
            path = FileLocator.toFileURL(Platform.getBundle("com.fap.ide").getEntry("")).getPath();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        path = path.substring(1, path.lastIndexOf("/"));
        return path;
    }

    private String getProjectPath(String projectName)
    {
        String projectPath = "";
        try
        {
            projectPath = (new StringBuilder(String.valueOf(getWSPath()))).append("/").append(projectName).toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return projectPath;
    }

    private String getWSPath()
    {
        String path = null;
        try
        {
            path = FileLocator.toFileURL(Platform.getBundle("com.fap.ide").getEntry("")).getPath();
            path = path.substring(1, path.lastIndexOf("/"));
            path = path.substring(0, path.lastIndexOf("/"));
            if(path.indexOf("bundles") > 0)
                path = Platform.getLocation().toString();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return path;
    }

    private PlugInAPI getPlugInAPIInstant()
    {
        printMsg("<<< \u7487\u950B\u7273\u7035\u5F52\u53A4\u7F03\uE1BC\u5F2C\u93C1\uFFFD >>>");
        printMsg((new StringBuilder("!!JDBC PATH:")).append(Activator.getDefault().getPreferenceStore().getString("jdbcPath")).toString());
        printMsg((new StringBuilder("Database Connection:")).append(Activator.getDefault().getPreferenceStore().getString("url")).toString());
        printMsg((new StringBuilder("User:")).append(Activator.getDefault().getPreferenceStore().getString("user")).toString());
        printMsg((new StringBuilder("Password:")).append(Activator.getDefault().getPreferenceStore().getString("password")).toString());
        printMsg((new StringBuilder("Package Mapper Directory:")).append(getRealPath(Activator.getDefault().getPreferenceStore().getString("packageAPIPath"))).toString());
        printMsg((new StringBuilder("Package Mapper XML Directory:")).append(getRealPath(Activator.getDefault().getPreferenceStore().getString("packageXMLPath"))).toString());
        printMsg((new StringBuilder("Table Mapper Directory:")).append(getRealPath(Activator.getDefault().getPreferenceStore().getString("tableAPIPath"))).toString());
        printMsg((new StringBuilder("Table Model Directory:")).append(getRealPath(Activator.getDefault().getPreferenceStore().getString("tableModelPath"))).toString());
        printMsg((new StringBuilder("Table Mapper XML Directory:")).append(getRealPath(Activator.getDefault().getPreferenceStore().getString("tableXMLPath"))).toString());
        printMsg("=======================");
        return new PlugInAPI(Activator.getDefault().getPreferenceStore().getString("jdbcPath"), Activator.getDefault().getPreferenceStore().getString("url"), Activator.getDefault().getPreferenceStore().getString("user"), Activator.getDefault().getPreferenceStore().getString("password"), Activator.getDefault().getPreferenceStore().getBoolean(""), Activator.getDefault().getPreferenceStore().getString("project"), getRealPath(Activator.getDefault().getPreferenceStore().getString("packageAPIPath")), getRealPath(Activator.getDefault().getPreferenceStore().getString("packageXMLPath")), getRealPath(Activator.getDefault().getPreferenceStore().getString("tableAPIPath")), getRealPath(Activator.getDefault().getPreferenceStore().getString("tableModelPath")), getRealPath(Activator.getDefault().getPreferenceStore().getString("tableXMLPath")));
    }

    public String getRealPath(String path)
    {
        return path.replace("${workspace_loc:", (new StringBuilder(String.valueOf(getWSPath()))).append("/").toString()).replace("}", "");
    }

    private void printMsg(String msg)
    {
        printer.println(msg);
        try
        {
            printer.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private MessageConsoleStream createConsole(String consoleName)
    {
        MessageConsole console = new MessageConsole(consoleName, null);
        console.setWaterMarks(1024, 0x500000);
        IConsoleManager manager = ConsolePlugin.getDefault().getConsoleManager();
        manager.addConsoles(new IConsole[] {
            console
        });
        manager.showConsoleView(console);
        MessageConsoleStream cs = console.newMessageStream();
        cs.setColor(Display.getDefault().getSystemColor(2));
        PrintStream printStream = new PrintStream(cs);
        System.setOut(printStream);
        System.setErr(printStream);
        return cs;
    }

    public static final String ID = "com.fap.ide.test.views.SampleView";
    private TreeViewer viewer;
    private DrillDownAdapter drillDownAdapter;
    private Action action1;
    private Action action2;
    private Action doubleClickAction;
    String tableImg;
    String folderImg;
    String packageImg;
    String dbImg;
    MessageConsoleStream printer;






}
