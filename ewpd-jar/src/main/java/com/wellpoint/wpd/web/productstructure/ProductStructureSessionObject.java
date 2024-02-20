/*
 * ProductStructureSessionObject.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the express 
 * written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.productstructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.myfaces.custom.tree2.TreeModelBase;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureSessionObject {

    private int id;

    private List businessDomains;

    private String name;

    private int version;

    private String status;

    private int parentId;

    private String state;

    private String action;

    private String structureType;

    private String mandateType;

    private String stateId;
    
    private int benefitComponentId;
    
    private int benefitId;
    
    private String productFamily;
    
    //enhancement
    private boolean questionHiddenFlag;

    //  Questionnare---start
	List questionareList;
	
	List orgQuestionnaireList;
	
	int adminLevelOptionSysId;
	
	HashMap allPossibleAnswerMap;
	
	// Questionnare ---End 
	
	//Code change for product structure tree rendering optimization
	private boolean treeStructureUpdated = false;
	private TreeModelBase productStructureTree = null;
	
//	code change for performance improvement
	private Map dataHiddenLowerLevelValDescMap;
	private Map dataHiddenValTermMap ;
    private Map dataHiddenValQualifierMap;
    private Map hiddenLineFreqValueMap;
    private Map hiddenLowerLevelFreqValueMap;
    private Map lineIdMap;
    private Map hiddenLevelFlagMap;
    private Map hiddenLineFlagMap;
    private Map hiddenBenefitValueMap;
    private Map oldDescOutputTxtMap;
	
    /**
     * Returns the id
     * 
     * @return int id.
     */

    public int getId() {
        return id;
    }


    /**
     * Sets the id
     * 
     * @param id.
     */

    public void setId(int id) {
        this.id = id;
    }


    /**
     * Returns the name
     * 
     * @return String name.
     */

    public String getName() {
        return name;
    }


    /**
     * Sets the name
     * 
     * @param name.
     */

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Returns the parentId
     * 
     * @return int parentId.
     */

    public int getParentId() {
        return parentId;
    }


    /**
     * Sets the parentId
     * 
     * @param parentId.
     */

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }


    /**
     * Returns the state
     * 
     * @return String state.
     */

    public String getState() {
        return state;
    }


    /**
     * Sets the state
     * 
     * @param state.
     */

    public void setState(String state) {
        this.state = state;
    }


    /**
     * Returns the status
     * 
     * @return String status.
     */

    public String getStatus() {
        return status;
    }


    /**
     * Sets the status
     * 
     * @param status.
     */

    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * Returns the version
     * 
     * @return int version.
     */

    public int getVersion() {
        return version;
    }


    /**
     * Sets the version
     * 
     * @param version.
     */

    public void setVersion(int version) {
        this.version = version;
    }


    /**
     * Returns the businessDomains
     * 
     * @return List businessDomains.
     */

    public List getBusinessDomains() {
        return businessDomains;
    }


    /**
     * Sets the businessDomains
     * 
     * @param businessDomains.
     */

    public void setBusinessDomains(List businessDomains) {
        this.businessDomains = businessDomains;
    }


    /**
     * Returns the action
     * 
     * @return String action.
     */

    public String getAction() {
        return action;
    }


    /**
     * Sets the action
     * 
     * @param action.
     */

    public void setAction(String action) {
        this.action = action;
    }


    /**
     * @return mandateType
     * 
     * Returns the mandateType.
     */
    public String getMandateType() {
        return mandateType;
    }


    /**
     * @param mandateType
     * 
     * Sets the mandateType.
     */
    public void setMandateType(String mandateType) {
        this.mandateType = mandateType;
    }


    /**
     * @return stateId
     * 
     * Returns the stateId.
     */
    public String getStateId() {
        return stateId;
    }


    /**
     * @param stateId
     * 
     * Sets the stateId.
     */
    public void setStateId(String stateId) {
        this.stateId = stateId;
    }


    /**
     * @return structureType
     * 
     * Returns the structureType.
     */
    public String getStructureType() {
        return structureType;
    }


    /**
     * @param structureType
     * 
     * Sets the structureType.
     */
    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }

	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * @return Returns the benefitId.
	 */
	public int getBenefitId() {
		return benefitId;
	}
	/**
	 * @param benefitId The benefitId to set.
	 */
	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}
	/**
	 * @return Returns the questionHiddenFlag.
	 */
	public boolean isQuestionHiddenFlag() {
		return questionHiddenFlag;
	}
	/**
	 * @param questionHiddenFlag The questionHiddenFlag to set.
	 */
	public void setQuestionHiddenFlag(boolean questionHiddenFlag) {
		this.questionHiddenFlag = questionHiddenFlag;
	}
	/**
	 * @return Returns the adminLevelOptionSysId.
	 */
	public int getAdminLevelOptionSysId() {
		return adminLevelOptionSysId;
	}
	/**
	 * @param adminLevelOptionSysId The adminLevelOptionSysId to set.
	 */
	public void setAdminLevelOptionSysId(int adminLevelOptionSysId) {
		this.adminLevelOptionSysId = adminLevelOptionSysId;
	}
	/**
	 * @return Returns the questionareList.
	 */
	public List getQuestionareList() {
		return questionareList;
	}
	/**
	 * @param questionareList The questionareList to set.
	 */
	public void setQuestionareList(List questionareList) {
		this.questionareList = questionareList;
	}
	/**
	 * @return Returns the productFamily.
	 */
	public String getProductFamily() {
		return productFamily;
	}
	/**
	 * @param productFamily The productFamily to set.
	 */
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	/**
	 */
	public void setOrgQuestionnaireList(List orgQuestionnaireList) {
		this.orgQuestionnaireList = orgQuestionnaireList;
	}
	/**
	 * @return Returns the orgQuestionareList.
	 */
	public List getOrgQuestionnaireList() {
		return orgQuestionnaireList;
	}
	
	/**
	 * @return Returns the allPossibleAnswerMap.
	 */
	public HashMap getAllPossibleAnswerMap() {
		return allPossibleAnswerMap;
	}
	/**
	 * @param allPossibleAnswerMap The allPossibleAnswerMap to set.
	 */
	public void setAllPossibleAnswerMap(HashMap allPossibleAnswerMap) {
		this.allPossibleAnswerMap = allPossibleAnswerMap;
	}
	/**
	 * @return Returns the treeStructureUpdated.
	 */
	public boolean isTreeStructureUpdated() {
		return treeStructureUpdated;
	}
	/**
	 * @param treeStructureUpdated The treeStructureUpdated to set.
	 */
	public void setTreeStructureUpdated(boolean treeStructureUpdated) {
		this.treeStructureUpdated = treeStructureUpdated;
	}
	/**
	 * @return Returns the productTree.
	 */
	public TreeModelBase getProductStructureTree() {
		return productStructureTree;
	}
	/**
	 * @param productTree The productTree to set.
	 */
	public void setProductStructureTree(TreeModelBase productStructureTree) {
		this.productStructureTree = productStructureTree;
	}
/**
 * @return Returns the dataHiddenLowerLevelValDescMap.
 */
public Map getDataHiddenLowerLevelValDescMap() {
	return dataHiddenLowerLevelValDescMap;
}
/**
 * @param dataHiddenLowerLevelValDescMap The dataHiddenLowerLevelValDescMap to set.
 */
public void setDataHiddenLowerLevelValDescMap(Map dataHiddenLowerLevelValDescMap) {
	this.dataHiddenLowerLevelValDescMap = dataHiddenLowerLevelValDescMap;
}
	/**
	 * @return Returns the dataHiddenValQualifierMap.
	 */
	public Map getDataHiddenValQualifierMap() {
		return dataHiddenValQualifierMap;
	}
	/**
	 * @param dataHiddenValQualifierMap The dataHiddenValQualifierMap to set.
	 */
	public void setDataHiddenValQualifierMap(Map dataHiddenValQualifierMap) {
		this.dataHiddenValQualifierMap = dataHiddenValQualifierMap;
	}
	/**
	 * @return Returns the dataHiddenValTermMap.
	 */
	public Map getDataHiddenValTermMap() {
		return dataHiddenValTermMap;
	}
	/**
	 * @param dataHiddenValTermMap The dataHiddenValTermMap to set.
	 */
	public void setDataHiddenValTermMap(Map dataHiddenValTermMap) {
		this.dataHiddenValTermMap = dataHiddenValTermMap;
	}
	/**
	 * @return Returns the hiddenLineFreqValueMap.
	 */
	public Map getHiddenLineFreqValueMap() {
		return hiddenLineFreqValueMap;
	}
	/**
	 * @param hiddenLineFreqValueMap The hiddenLineFreqValueMap to set.
	 */
	public void setHiddenLineFreqValueMap(Map hiddenLineFreqValueMap) {
		this.hiddenLineFreqValueMap = hiddenLineFreqValueMap;
	}
	/**
	 * @return Returns the hiddenLowerLevelFreqValueMap.
	 */
	public Map getHiddenLowerLevelFreqValueMap() {
		return hiddenLowerLevelFreqValueMap;
	}
	/**
	 * @param hiddenLowerLevelFreqValueMap The hiddenLowerLevelFreqValueMap to set.
	 */
	public void setHiddenLowerLevelFreqValueMap(Map hiddenLowerLevelFreqValueMap) {
		this.hiddenLowerLevelFreqValueMap = hiddenLowerLevelFreqValueMap;
	}
	/**
	 * @return Returns the lineIdMap.
	 */
	public Map getLineIdMap() {
		return lineIdMap;
	}
	/**
	 * @param lineIdMap The lineIdMap to set.
	 */
	public void setLineIdMap(Map lineIdMap) {
		this.lineIdMap = lineIdMap;
	}
	/**
	 * @return Returns the hiddenLevelFlagMap.
	 */
	public Map getHiddenLevelFlagMap() {
		return hiddenLevelFlagMap;
	}
	/**
	 * @param hiddenLevelFlagMap The hiddenLevelFlagMap to set.
	 */
	public void setHiddenLevelFlagMap(Map hiddenLevelFlagMap) {
		this.hiddenLevelFlagMap = hiddenLevelFlagMap;
	}
	/**
	 * @return Returns the hiddenLineFlagMap.
	 */
	public Map getHiddenLineFlagMap() {
		return hiddenLineFlagMap;
	}
	/**
	 * @param hiddenLineFlagMap The hiddenLineFlagMap to set.
	 */
	public void setHiddenLineFlagMap(Map hiddenLineFlagMap) {
		this.hiddenLineFlagMap = hiddenLineFlagMap;
	}
	/**
	 * @return Returns the hiddenBenefitValueMap.
	 */
	public Map getHiddenBenefitValueMap() {
		return hiddenBenefitValueMap;
	}
	/**
	 * @param hiddenBenefitValueMap The hiddenBenefitValueMap to set.
	 */
	public void setHiddenBenefitValueMap(Map hiddenBenefitValueMap) {
		this.hiddenBenefitValueMap = hiddenBenefitValueMap;
	}
	/**
	 * @return Returns the oldDescOutputTxtMap.
	 */
	public Map getOldDescOutputTxtMap() {
		return oldDescOutputTxtMap;
	}
	/**
	 * @param oldDescOutputTxtMap The oldDescOutputTxtMap to set.
	 */
	public void setOldDescOutputTxtMap(Map oldDescOutputTxtMap) {
		this.oldDescOutputTxtMap = oldDescOutputTxtMap;
	}
}