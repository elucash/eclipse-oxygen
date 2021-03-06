/*******************************************************************************
 * Copyright (c) 2016 Google, Inc. and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Stefan Xenos <sxenos@gmail.com> (Google) - initial API and implementation
 ******************************************************************************/
package org.eclipse.jdt.internal.ui.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;

import org.eclipse.core.runtime.jobs.Job;

import org.eclipse.jdt.core.JavaCore;

/**
 * Handler for the Rebuild Java Index command
 *
 * @since 3.13
 */
public class RebuildIndexHandler extends AbstractHandler {
	private final Job rebuildJob= Job.create(CommandsMessages.RebuildIndexHandler_jobName, monitor -> {
		JavaCore.rebuildIndex(monitor);
	});

	@Override
	public Object execute(ExecutionEvent event) {
		rebuildJob.cancel();
		rebuildJob.schedule();
		return null;
	}
}
