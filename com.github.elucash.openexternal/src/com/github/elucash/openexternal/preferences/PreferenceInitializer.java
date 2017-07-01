package com.github.elucash.openexternal.preferences;

import com.github.elucash.openexternal.OpenExternalPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class PreferenceInitializer extends AbstractPreferenceInitializer
{
    @Override
    public void initializeDefaultPreferences()
    {
        IPreferenceStore store = OpenExternalPlugin.getDefaultPreferenceStore();
        store.setDefault("windows_explorer_policy", "windows_explorer_policy_folders");
        store.setDefault("mac_terminal_policy", "mac_terminal_policy_new_shell");
    }
}
