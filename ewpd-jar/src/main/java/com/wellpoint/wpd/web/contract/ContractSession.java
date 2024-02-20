/*
 * ContractSession.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.myfaces.custom.tree2.TreeModelBase;

import com.wellpoint.wpd.web.contract.tree.ContractTreeNodeBase;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractSession implements Cloneable{
    public static final int VIEW_MODE = 1;
    public static final int EDIT_MODE = 2;
    public static final int COPY_MODE = 3;
    public static final int COPY_HEADINGS_MODE = 4;
    public static final int PRINT_MODE = 5; //CARS
    
    ContractKeyObject contractKeyObject = null;
    private int mode = -1;
    private int productId;
    private int benefitId;
	private int benefitComponentId;
	private String benefitComponentDesc;
	private int benefitAdminId;
	private int adminOptionId;
	private int productAdmin;
    
    private String contractId;
    private int contractSysId;
    private int version;
    private String status;
    private List attachNotesList;
    private List dateSegmentList;
    
    private String initialTest;
    
    private String testContractState;
    
    private int adminLevelOptionAssnId;
    
	private List benefitTierDefinitionList;
	
	private List benefitTierLevelList;
	
	private List benefitTierPsvlList;
	
	private Map benefitHideShowFlagMap;

     List questionnaireList;
     List tierQuestionnaireList;
     // Code change by minu : 28-12-2010 : eWPD System Stabilization 
     List orgQuestionnaireList;
     List orgTierQuestionnaireList;
     
     HashMap allPossibleAnswerMap;
     
	int adminLevelOptionSysId;
	private List tierDefinitionsList; //CARS:AM2
	private List tierCriteriaDefinitionList; //CARS:AM2
	private List tieredAdminMethodList ;//CARS:AM2
	
	private boolean treeStructureUpdated = false;
	private TreeModelBase contractTree = null;
	
	//CARS:AM2:START
    private boolean isTierPOS = false; //CARS:AM2|POS
    //POS{
    
    //Stabilization release
    private Map dataHiddenLowerLevelValDescMap;
    private Map dataHiddenValTermMap ;
    private Map dataHiddenValQualifierMap;
    private Map hiddenLineFreqValueMap;
    private Map hiddenLowerLevelFreqValueMap;
    private Map lineIdMap;
    private Map levelIdsMapFromSession;
    
    public boolean isTierPOS() {
		return isTierPOS;
	}
	public void setTierPOS(boolean isTierPOS) {
		this.isTierPOS = isTierPOS;
	}
	//POS}

	/**
	 * @return Returns the tieredAdminMethodList.
	 */
	public List getTieredAdminMethodList() {
		return tieredAdminMethodList;
	}
	/**
	 * @param tieredAdminMethodList The tieredAdminMethodList to set.
	 */
	public void setTieredAdminMethodList(List tieredAdminMethodList) {
		this.tieredAdminMethodList = tieredAdminMethodList;
	}
	/**
	 * @return Returns the tierCriteriaDefinitionList.
	 */
	public List getTierCriteriaDefinitionList() {
		return tierCriteriaDefinitionList;
	}
	/**
	 * @param tierCriteriaDefinitionList The tierCriteriaDefinitionList to set.
	 */
	public void setTierCriteriaDefinitionList(List tierCriteriaDefinitionList) {
		this.tierCriteriaDefinitionList = tierCriteriaDefinitionList;
	}
	/**
	 * @return Returns the tierDefinitionsList.
	 */
	public List getTierDefinitionsList() {
		return tierDefinitionsList;
	}
	/**
	 * @param tierDefinitionsList The tierDefinitionsList to set.
	 */
	public void setTierDefinitionsList(List tierDefinitionsList) {
		this.tierDefinitionsList = tierDefinitionsList;
	}
	//CARS:AM2:END
	/**
	 * @return Returns the benefitTierPsvlList.
	 */
	public List getBenefitTierPsvlList() {
		return benefitTierPsvlList;
	}
	/**
	 * @param benefitTierPsvlList The benefitTierPsvlList to set.
	 */
	public void setBenefitTierPsvlList(List benefitTierPsvlList) {
		this.benefitTierPsvlList = benefitTierPsvlList;
	}
    /**
     * Returns the productAdmin
     * @return int productAdmin.
     */
    public int getProductAdmin() {
        return productAdmin;
    }
    /**
     * Sets the productAdmin
     * @param productAdmin.
     */
    public void setProductAdmin(int productAdmin) {
        this.productAdmin = productAdmin;
    }
    /**
     * Returns the contractKeyObject
     * @return ContractKeyObject contractKeyObject.
     */
    public ContractKeyObject getContractKeyObject() {
        return contractKeyObject;
    }
    /**
     * Sets the contractKeyObject
     * @param contractKeyObject.
     */
    public void setContractKeyObject(ContractKeyObject contractKeyObject) {
        this.contractKeyObject = contractKeyObject;
    }
    /**
     * Returns the mode
     * @return int mode.
     */
    public int getMode() {
        return mode;
    }
    /**
     * Sets the mode
     * @param mode.
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return Returns the contractSysId.
	 */
	public int getContractSysId() {
		return contractSysId;
	}
	/**
	 * @param contractSysId The contractSysId to set.
	 */
	public void setContractSysId(int contractSysId) {
		this.contractSysId = contractSysId;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}

    /**
     * Returns the productId
     * @return int productId.
     */
    public int getProductId() {
        return productId;
    }
    /**
     * Sets the productId
     * @param productId.
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }
    /**
     * Returns the benefitAdminId
     * @return int benefitAdminId.
     */
    public int getBenefitAdminId() {
        return benefitAdminId;
    }
    /**
     * Sets the benefitAdminId
     * @param benefitAdminId.
     */
    public void setBenefitAdminId(int benefitAdminId) {
        this.benefitAdminId = benefitAdminId;
    }
    /**
     * Returns the benefitComponentId
     * @return int benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }
    /**
     * Sets the benefitComponentId
     * @param benefitComponentId.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }
    /**
     * Returns the benefitId
     * @return int benefitId.
     */
    public int getBenefitId() {
        return benefitId;
    }
    /**
     * Sets the benefitId
     * @param benefitId.
     */
    public void setBenefitId(int benefitId) {
        this.benefitId = benefitId;
    }
    /**
     * Returns the adminOptionId
     * @return int adminOptionId.
     */
    public int getAdminOptionId() {
        return adminOptionId;
    }
    /**
     * Sets the adminOptionId
     * @param adminOptionId.
     */
    public void setAdminOptionId(int adminOptionId) {
        this.adminOptionId = adminOptionId;
    }

	/**
	 * @return Returns the attachNotesList.
	 */
	public List getAttachNotesList() {
		return attachNotesList;
	}
	/**
	 * @param attachNotesList The attachNotesList to set.
	 */
	public void setAttachNotesList(List attachNotesList) {
		this.attachNotesList = attachNotesList;
	}
	/**
	 * @return Returns the dateSegmentList.
	 */
	public List getDateSegmentList() {
		return dateSegmentList;
	}
	/**
	 * @param dateSegmentList The dateSegmentList to set.
	 */
	public void setDateSegmentList(List dateSegmentList) {
		this.dateSegmentList = dateSegmentList;
	}
	/**
	 * @return Returns the initialTest.
	 */
	public String getInitialTest() {
		return initialTest;
	}
	/**
	 * @param initialTest The initialTest to set.
	 */
	public void setInitialTest(String initialTest) {
		this.initialTest = initialTest;
	}
	/**
	 * @return Returns the testContractState.
	 */
	public String getTestContractState() {
		return testContractState;
	}
	/**
	 * @param testContractState The testContractState to set.
	 */
	public void setTestContractState(String testContractState) {
		this.testContractState = testContractState;
	}
    /**
     * Returns the adminLevelOptionAssnId
     * @return int adminLevelOptionAssnId.
     */
    public int getAdminLevelOptionAssnId() {
        return adminLevelOptionAssnId;
    }
    /**
     * Sets the adminLevelOptionAssnId
     * @param adminLevelOptionAssnId.
     */
    public void setAdminLevelOptionAssnId(int adminLevelOptionAssnId) {
        this.adminLevelOptionAssnId = adminLevelOptionAssnId;
    }
	/**
	 * @return Returns the benefitComponentDesc.
	 */
	public String getBenefitComponentDesc() {
		return benefitComponentDesc;
	}
	/**
	 * @param benefitComponentDesc The benefitComponentDesc to set.
	 */
	public void setBenefitComponentDesc(String benefitComponentDesc) {
		this.benefitComponentDesc = benefitComponentDesc;
	}
	/**
	 * @return Returns the questionnaireList.
	 */
	public List getQuestionnaireList() {
		return questionnaireList;
	}
	/**
	 * @param questionnaireList The questionnaireList to set.
	 */
	public void setQuestionnaireList(List questionnaireList) {
		this.questionnaireList = questionnaireList;
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
	public List getBenefitTierDefinitionList() {
		return benefitTierDefinitionList;
	}
	public void setBenefitTierDefinitionList(List benefitTierDefinitionList) {
		this.benefitTierDefinitionList = benefitTierDefinitionList;
	}
	public List getBenefitTierLevelList() {
		return benefitTierLevelList;
	}
	public void setBenefitTierLevelList(List benefitTierLevelList) {
		this.benefitTierLevelList = benefitTierLevelList;
	}
	
	/**
	 * @return Returns the tierQuestionnaireList.
	 */
	public List getTierQuestionnaireList() {
		return tierQuestionnaireList;
	}
	/**
	 * @param tierQuestionnaireList The tierQuestionnaireList to set.
	 */
	public void setTierQuestionnaireList(List tierQuestionnaireList) {
		this.tierQuestionnaireList = tierQuestionnaireList;
	}
	
	public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
           return null;
        }
    }
	
	 // Code change by minu : 28-12-2010 : eWPD System Stabilization 
	/**
	 * @return Returns the orgQuestionnaireList.
	 */
	public List getOrgQuestionnaireList() {
		return orgQuestionnaireList;
	}
	/**
	 * @param orgQuestionnaireList The orgQuestionnaireList to set.
	 */
	public void setOrgQuestionnaireList(List orgQuestionnaireList) {
		this.orgQuestionnaireList = orgQuestionnaireList;
	}
	/**
	 * @return Returns the orgTierQuestionnaireList.
	 */
	public List getOrgTierQuestionnaireList() {
		return orgTierQuestionnaireList;
	}
	/**
	 * @param orgTierQuestionnaireList The orgTierQuestionnaireList to set.
	 */
	public void setOrgTierQuestionnaireList(List orgTierQuestionnaireList) {
		this.orgTierQuestionnaireList = orgTierQuestionnaireList;
	}
	
	/**Getter Setter method for Benefitcomp hide unhide flag Map :: by KK**/
	public Map getBenefitHideShowFlagMap() {
		return benefitHideShowFlagMap;
	}
	public void setBenefitHideShowFlagMap(Map benefitHideShowFlagMap) {
		this.benefitHideShowFlagMap = benefitHideShowFlagMap;
	}
	/** end :: by KK**/
	
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
	
	public boolean isTreeStructureUpdated() {
		return treeStructureUpdated;
	}
	
	public void setTreeStructureUpdated(boolean treeStructureUpdated) {
		this.treeStructureUpdated = treeStructureUpdated;
	}
	public TreeModelBase getContractTree() {
		return contractTree;
	}
	public void setContractTree(TreeModelBase contractTree) {
		this.contractTree = contractTree;
	}
	/**
	 * @return Returns the dataHiddenLowerLevelValDesc.
	 */
	public Map getDataHiddenLowerLevelValDescMap() {
		return dataHiddenLowerLevelValDescMap;
	}
	/**
	 * @param dataHiddenLowerLevelValDesc The dataHiddenLowerLevelValDesc to set.
	 */
	public void setDataHiddenLowerLevelValDescMap(Map dataHiddenLowerLevelValDescMap) {
		this.dataHiddenLowerLevelValDescMap = dataHiddenLowerLevelValDescMap;
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
	 * @return Returns the levelIdsMapFromSession.
	 */
	public Map getLevelIdsMapFromSession() {
		return levelIdsMapFromSession;
	}
	/**
	 * @param levelIdsMapFromSession The levelIdsMapFromSession to set.
	 */
	public void setLevelIdsMapFromSession(Map levelIdsMapFromSession) {
		this.levelIdsMapFromSession = levelIdsMapFromSession;
	}
}
