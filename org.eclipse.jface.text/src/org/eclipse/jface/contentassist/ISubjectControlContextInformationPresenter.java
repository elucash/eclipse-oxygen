/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jface.contentassist;

import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationPresenter;


/**
 * Extends {@link org.eclipse.jface.text.contentassist.IContextInformationPresenter} to
 * allow to install a content assistant on the given
 * {@linkplain org.eclipse.jface.contentassist.IContentAssistSubjectControl content assist subject control}.
 *
 * @since 3.0
 * @deprecated As of 3.2, replaced by Platform UI's field assist support
 */
@Deprecated
public interface ISubjectControlContextInformationPresenter extends IContextInformationPresenter {

	/**
	 * Installs this presenter for the given context information.
	 *
	 * @param info the context information which this presenter should style
	 * @param contentAssistSubjectControl the content assist subject control
	 * @param offset the document offset for which the information has been computed
	 */
	void install(IContextInformation info, IContentAssistSubjectControl contentAssistSubjectControl, int offset);
}
