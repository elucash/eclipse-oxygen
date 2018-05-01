package com.github.elucash.openexternal;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class OpenExternalPlugin extends AbstractUIPlugin
{
    public static final String ID = "com.github.elucash.openexternal";
    private static OpenExternalPlugin plugin;

    @Override
    public void start(BundleContext context)
            throws Exception
    {
        super.start(context);
        plugin = this;
    }

    @Override
    public void stop(BundleContext context)
            throws Exception
    {
        plugin = null;
        super.stop(context);
    }

    public static OpenExternalPlugin getDefault()
    {
        return plugin;
    }

    public static IWorkbenchPage getActivePage() {
        IWorkbenchWindow window = getDefault().getWorkbench().getActiveWorkbenchWindow();
        if (window == null) {
            return null;
        }
        return window.getActivePage();
    }

    public static IPreferenceStore getDefaultPreferenceStore() {
        return getDefault().getPreferenceStore();
    }
}
