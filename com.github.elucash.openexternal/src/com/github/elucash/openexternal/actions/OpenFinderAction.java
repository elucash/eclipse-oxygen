package com.github.elucash.openexternal.actions;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import com.github.elucash.openexternal.OpenExternalPlugin;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OpenFinderAction extends AbstractOpenAction
{
    @Override
    public void run(IAction action)
    {
        List list = getSelection().toList();
        Set<String> opendPathSet = new HashSet<String>();

        for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
            Object resource = localIterator.next();
            String path = getPath(resource, true);

            if (path == null) {
                continue;
            }
            try
            {
                if (!opendPathSet.contains(path)) {
                    new ProcessBuilder(new String[] { "open", path }).start();
                    opendPathSet.add(path);
                }
            } catch (IOException exception) {
                ErrorDialog.openError(getShell(), null, null,
                        new Status(4,
                                OpenExternalPlugin.ID,
                                4, "Cannot open the Finder.", exception));
            }
        }
    }
}