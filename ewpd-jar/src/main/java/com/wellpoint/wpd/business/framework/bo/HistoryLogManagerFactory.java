/*
 * Created on Sep 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.framework.bo;

import com.wellpoint.wpd.business.framework.bo.manager.HistoryLogManager;

/**
 * @author U14659
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 * 
 * Used to instantiate the history log manager impl class
 */
public class HistoryLogManagerFactory {

	/*
	 * Instantiate the history log manager impl class and returns it's object
	 */
	public static HistoryLogManager getHistoryLogManger() {

		HistoryLogManagerImpl historyLogManagerImpl = new HistoryLogManagerImpl();
		historyLogManagerImpl
				.setProcessType(HistoryLogManager.SYNCHRONOUS_PROCESS);

		return historyLogManagerImpl;
	}

}