/*
 * Created on Aug 30, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.response;

import com.wellpoint.wpd.common.framework.messages.InformationalMessage;

/**
 * @author U11085
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConfirmMigrationContractResponse extends MigrationContractResponse{
	private InformationalMessage benefitYearIndConflictMessage;
	/**
	 * @return Returns the benefitYearIndConflictMessage.
	 */
	public InformationalMessage getBenefitYearIndConflictMessage() {
		return benefitYearIndConflictMessage;
	}
	/**
	 * @param benefitYearIndConflictMessage The benefitYearIndConflictMessage to set.
	 */
	public void setBenefitYearIndConflictMessage(
			InformationalMessage benefitYearIndConflictMessage) {
		this.benefitYearIndConflictMessage = benefitYearIndConflictMessage;
	}
}
