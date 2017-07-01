package com.github.elucash.openexternal.models;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IActionFilter;

public class IntermediateResource
        implements IAdaptable, IActionFilter
{
    private IResource resource;

    public IntermediateResource(IResource resource)
    {
        this.resource = resource;
    }

    public IResource getResource() {
        return resource;
    }

    @Override
    public boolean testAttribute(Object target, String name, String value) {
        if (name.equals("platform")) {
            return Platform.getOS().equals(value);
        }

        return true;
    }

    @Override
    public Object getAdapter(Class adapter)
    {
        return getResource().getAdapter(adapter);
    }
}