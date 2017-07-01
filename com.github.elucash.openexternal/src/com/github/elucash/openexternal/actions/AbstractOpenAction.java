package com.github.elucash.openexternal.actions;

import com.github.elucash.openexternal.OpenExternalPlugin;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionDelegate;

public abstract class AbstractOpenAction extends ActionDelegate
        implements IObjectActionDelegate {

    private IStructuredSelection selection;
    private IWorkbenchPart targetPart;

    @Override
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        this.targetPart = targetPart;
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        if ((selection instanceof IStructuredSelection)) {
            this.selection = ((IStructuredSelection) selection);
        }
    }

    public IStructuredSelection getSelection() {
        return selection;
    }

    protected Shell getShell() {
        if (targetPart != null) {
            return targetPart.getSite().getShell();
        }
        return OpenExternalPlugin.getActivePage().getActivePart().getSite().getShell();
    }

    protected String getPath(Object object, boolean directory)
    {
        if ((object instanceof IAdaptable)) {
            IResource resource = (IResource) ((IAdaptable) object).getAdapter(IResource.class);
            if (resource == null) {
                return null;
            }
            if ((directory) &&
                    (resource.getType() == 1)) {
                return resource.getParent().getLocation().toOSString();
            }

            return resource.getLocation().toOSString();
        }

        return null;
    }

    protected String getPath(Object object) {
        return getPath(object, false);
    }
}