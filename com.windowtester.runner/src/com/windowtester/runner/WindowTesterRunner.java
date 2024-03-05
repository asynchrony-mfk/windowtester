/*******************************************************************************
 *  Copyright (c) 2012 Google, Inc.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *  Contributors:
 *  Google, Inc. - initial API and implementation
 *******************************************************************************/
package com.windowtester.runner;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import com.windowtester.runner.util.Logger;

/**
 * The main entry point for WindowTester JUnit execution. If the org.eclipse.test plugin
 * is resolved and ready then this class delegates test execution to
 * org.eclipse.test.UITestApplication, otherwise the tests are executed by our own
 * {@link LocalTestRunner}.
 */
public class WindowTesterRunner
	implements IApplication
{
	private static final String ECLIPSE_TEST_ID = "org.eclipse.test";
	private static final String ECLIPSE_TEST_CLASSNAME = "org.eclipse.test.UITestApplication";

	private IApplication runner;
	
	/**
	 * Main entry point.
	 * 
	 * @see org.eclipse.equinox.app.IApplication#start(IApplicationContext)
	 */
	public Object start(IApplicationContext context) throws Exception {
		WrapperedTestRunner eclipseTestRunner = new WrapperedTestRunner(ECLIPSE_TEST_ID, ECLIPSE_TEST_CLASSNAME);
		try {
			if (eclipseTestRunner.canStart(context))
				runner = eclipseTestRunner;
			else
				runner = new LocalTestRunner();
			return runner.start(context);
		}
		catch (Exception e) {
			e.printStackTrace();
			Logger.log("Unhandled exception", e);
			throw e;
		}
	}
	
	public void stop() {
		// no-op		
	}

//	/**
//	 * Main entry point.
//	 * Runs this runnable with the given args and returns a result.
//	 * @see org.eclipse.core.runtime.IPlatformRunnable#run(java.lang.Object)
//	 */
//	public Object run(Object object) throws Exception {
//		String[] args = (String[]) object;
//		WrapperedTestRunner eclipseTestRunner = new WrapperedTestRunner(ECLIPSE_TEST_ID, ECLIPSE_TEST_CLASSNAME);
//		try {
//			if (eclipseTestRunner.canStart(args))
//				runner = eclipseTestRunner;
//			else
//				runner = new LocalTestRunner();
//			return runner.run(args);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			Logger.log("Unhandled exception", e);
//			throw e;
//		}
//	}
}
