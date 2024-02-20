package com.wellpoint.wpd.common.migration.request;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveMigGeneralInfoRequest extends MigrationContractRequest{
    
    private int migDateSegmentSysId;
    
    private int migContractSysId;
    
    

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
}
