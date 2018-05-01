/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.internal.ui.wizards.buildpaths;

import org.eclipse.swt.widgets.Shell;

import org.eclipse.jface.resource.ImageDescriptor;

import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import org.eclipse.jdt.internal.corext.util.JavaModelUtil;

import org.eclipse.jdt.ui.wizards.ClasspathAttributeConfiguration;

import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.internal.ui.wizards.NewWizardMessages;

public class ModuleAttributeConfiguration extends ClasspathAttributeConfiguration {

	public static final String TRUE= "true"; //$NON-NLS-1$

	@Override
	public ImageDescriptor getImageDescriptor(ClasspathAttributeAccess attribute) {
		return JavaPluginImages.DESC_OBJS_MODULE_ATTRIB;
	}

	@Override
	public String getNameLabel(ClasspathAttributeAccess attribute) {
		return NewWizardMessages.CPListLabelProvider_module_label;
	}

	@Override
	public String getValueLabel(ClasspathAttributeAccess access) {
		String value= access.getClasspathAttribute().getValue();
		return TRUE.equals(value) ? NewWizardMessages.CPListLabelProvider_module_yes : NewWizardMessages.CPListLabelProvider_module_no;
	}

	@Override
	public boolean canEdit(ClasspathAttributeAccess attribute) {
		IJavaProject project= attribute.getJavaProject();
		return project != null && JavaModelUtil.is9OrHigher(project);
	}

	@Override
	public boolean canRemove(ClasspathAttributeAccess attribute) {
		return false;
	}

	@Override
	public IClasspathAttribute performEdit(Shell shell, ClasspathAttributeAccess attribute) {
		throw new UnsupportedOperationException("should be handled specifically");
//
//		String initialValue= attribute.getClasspathAttribute().getValue();
//		String newValue= TRUE.equals(initialValue) ? null : TRUE;
//		return JavaCore.newClasspathAttribute(CPListElement.MODULE, newValue);
	}

	@Override
	public IClasspathAttribute performRemove(ClasspathAttributeAccess attribute) {
		return JavaCore.newClasspathAttribute(CPListElement.MODULE, null);
	}
}
