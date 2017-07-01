package com.github.elucash.openexternal.adapters;

import com.github.elucash.openexternal.models.IntermediateResource;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterFactory;

public class ResourceAdapterFactory
        implements IAdapterFactory
{
    private static Class[] TYPES = { IntermediateResource.class };

    @Override
    public Object getAdapter(Object adaptableObject, Class adapterType)
    {
        IResource resource = null;

        if ((adaptableObject instanceof IAdaptable)) {
            resource = (IResource)
                    ((IAdaptable) adaptableObject).getAdapter(IResource.class);
        }

        if ((IntermediateResource.class.equals(adapterType)) && (resource != null)) {
            return new IntermediateResource(resource);
        }

        return null;
    }

    @Override
    public Class[] getAdapterList()
    {
        return TYPES;
    }
}