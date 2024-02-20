/*
 * Created on Jan 23, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.bo;

/**
 * @author u13680
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RemoveProduct {
    private int migDateSegmentSysId;
    
    private int migContractSysId;
    
    private int stepNumberCompleted;
        
    /**
     * Returns the migContractSysId
     * @return int migContractSysId.
     */
    public int getMigContractSysId() {
        return migContractSysId;
    }
    /**
     * Sets the migContractSysId
     * @param migContractSysId.
     */
    public void setMigContractSysId(int migContractSysId) {
        this.migContractSysId = migContractSysId;
    }
    /**
     * Returns the migDateSegmentSysId
     * @return int migDateSegmentSysId.
     */
    public int getMigDateSegmentSysId() {
        return migDateSegmentSysId;
    }
    /**
     * Sets the migDateSegmentSysId
     * @param migDateSegmentSysId.
     */
    public void setMigDateSegmentSysId(int migDateSegmentSysId) {
        this.migDateSegmentSysId = migDateSegmentSysId;
    }


	/**
	 * @return Returns the stepNumberCompleted.
	 */
	public int getStepNumberCompleted() {
		return stepNumberCompleted;
	}
	/**
	 * @param stepNumberCompleted The stepNumberCompleted to set.
	 */
	public void setStepNumberCompleted(int stepNumberCompleted) {
		this.stepNumberCompleted = stepNumberCompleted;
	}
}
