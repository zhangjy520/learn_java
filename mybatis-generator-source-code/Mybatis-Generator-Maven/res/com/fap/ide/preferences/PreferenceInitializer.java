// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PreferenceInitializer.java

package com.fap.ide.preferences;

import com.fap.ide.Activator;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class PreferenceInitializer extends AbstractPreferenceInitializer
{

    public PreferenceInitializer()
    {
    }

    public void initializeDefaultPreferences()
    {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        store.setDefault("url", "jdbc:oracle:thin:@svnserver:1521:ebank");
        store.setDefault("user", "xpaymentv3");
        store.setDefault("password", "xpaymentv3");
        store.setDefault("", true);
        store.setDefault("project", "payment");
        store.setDefault("packageAPIPath", "${workspace_loc:payment/src/main/java/com/rrtx/payment/db/procedure/mapper}");
        store.setDefault("packageXMLPath", "${workspace_loc:payment/src/main/resources/com/rrtx/payment/db/procedure/mapper}");
        store.setDefault("tableAPIPath", "${workspace_loc:payment/src/main/java/com/rrtx/payment/db/table/mapper}");
        store.setDefault("tableModelPath", "${workspace_loc:payment/src/main/java/com/rrtx/payment/db/table/model}");
        store.setDefault("tableXMLPath", "${workspace_loc:payment/src/main/resources/com/rrtx/payment/db/table/mapper}");
        store.setDefault("jdbcPath", "C:\\Users\\.m2\\repository\\ojdbc\\ojdbc\\14\\ojdbc-14.jar");
    }
}
