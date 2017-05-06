package com.github.elucash.openexternal.actions;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import com.github.elucash.openexternal.OpenExternalPlugin;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OpenTerminalAction extends AbstractOpenAction
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
                    File scriptFile = writeScript(path, openInNewTab());
                    Process appleScriptProcess = new ProcessBuilder(
                            new String[] { "/usr/bin/osascript", scriptFile.getAbsolutePath() }).start();
                    int exitCode = appleScriptProcess.waitFor();

                    if (exitCode != 0) {
                        throw new RuntimeException();
                    }
                    scriptFile.delete();

                    opendPathSet.add(path);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                ErrorDialog.openError(getShell(), null, null,
                        new Status(4, OpenExternalPlugin.ID, 4,
                                "Cannot open the Terminal.", exception));
            }
        }
    }

    private File writeScript(String terminalPath, boolean useTab) throws IOException
    {
        File file = new File(System.getProperty("java.io.tmpdir"), "eclipse.applescript");
        file.deleteOnExit();
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(new FileWriter(file));

            String script = "\tdo script \"cd '" + terminalPath + "'\"";// ;clear;pwd

            writer.println("tell application \"System Events\"");
            writer.println("\tset windowCount to count(processes whose name is \"Terminal\")");
            writer.println("end tell");

            writer.println("tell application \"Terminal\"");
            writer.println("\tactivate");

            if (useTab) {
                writer.println("    if windowCount is greater than 0 then");
                writer
                        .println("        tell application \"System Events\" to tell process \"Terminal\" to keystroke \"t\" using command down");
                writer.println("    end if");
                writer.println(script + " in window 1");
            } else {
                writer.println("    if windowCount is equal to 0 then");
                writer.println(script + " in window 1");
                writer.println("    else");
                writer.println(script);
                writer.println("    end if");
            }
            writer.println("end tell");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return file;
    }

    private boolean openInNewTab() {
        String policy = OpenExternalPlugin.getDefaultPreferenceStore().getString("mac_terminal_policy");
        return policy.equals("mac_terminal_policy_new_tab");
    }
}
