// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PreferencePage.java

package com.fap.ide.preferences;

import com.fap.ide.Activator;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class PreferencePage extends FieldEditorPreferencePage
    implements IWorkbenchPreferencePage
{

    public PreferencePage()
    {
        super(1);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("DataBase Configuration");
    }

    public void createFieldEditors()
    {
        GridLayout layout = (GridLayout)getFieldEditorParent().getLayout();
        layout.numColumns = 1;
        addField(new StringFieldEditor("jdbcPath", "DriverClass PATH:", getFieldEditorParent()));
        addField(new StringFieldEditor("url", "Database Connection:", getFieldEditorParent()));
        addField(new StringFieldEditor("user", "User:", getFieldEditorParent()));
        addField(new StringFieldEditor("password", "Password:", getFieldEditorParent()));
        addField(new StringFieldEditor("packageAPIPath", "Package Mapper Directory:", getFieldEditorParent()));
        addField(new StringFieldEditor("packageXMLPath", "Package Mapper XML Directory:", getFieldEditorParent()));
        addField(new StringFieldEditor("tableAPIPath", "Table Mapper Directory:", getFieldEditorParent()));
        addField(new StringFieldEditor("tableModelPath", "Table Model Directory:", getFieldEditorParent()));
        addField(new StringFieldEditor("tableXMLPath", "Table Mapper XML Directory:", getFieldEditorParent()));
    }

    public void init(IWorkbench iworkbench)
    {
    }
}
