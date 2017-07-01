package com.github.elucash.openexternal.preferences;

import com.github.elucash.openexternal.OpenExternalPlugin;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class OpenExternalPreferencePage extends FieldEditorPreferencePage
        implements IWorkbenchPreferencePage
{
    public OpenExternalPreferencePage()
    {
        super(1);
        setPreferenceStore(OpenExternalPlugin.getDefaultPreferenceStore());
    }

    @Override
    protected void createFieldEditors()
    {

        if (Platform.getOS().equals("macosx")) {
            addField(new RadioGroupFieldEditor("mac_terminal_policy",
                    "Mac OS X Shell Policy:", 1, new String[][] {
                            { "Open in a new shell", "mac_terminal_policy_new_shell" },
                            { "Open in a new tab", "mac_terminal_policy_new_tab" } },
                    getFieldEditorParent(), true));

        }
    }

    @Override
    public void init(IWorkbench workbench)
    {}
}