/*
 * ProductSessionObject.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.product;

import com.wellpoint.wpd.common.product.vo.ProductKeyObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.myfaces.custom.tree2.TreeModelBase;
//import java.util.Map;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductSessionObject{
	
    public static final int VIEW_MODE = 1;
    public static final int EDIT_MODE = 2;
    public static final int COPY_MODE = 3;
    
	private ProductKeyObject productKeyObject;
	private int mode = -1;
	private List searchResultList;
	private int benefitId = -1;
	private int benefitComponentId = -1;
	private int benefitAdminId = -1;
	private List allVersionSearchResults;
	private int adminOptionId;
	private int productKeyForVersionPage;
	private int adminLevelOptionAssnId;
	private int productStructKey;
	List questionareList;
	private String productFamily;
	
	private int beneiftDfnId;
	List tierQuestionnaireList;
	//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
	List orgQuestionnaireList;
	List orgTierQuestionnaireList;
	HashMap allPossibleAnswerMap;
	
	private List benefitTierDefinitionList;
	
	private List benefitTierLevelList;
	
	private List benefitTierPsvlList;
	private List tierDefinitionsList; //CARS:AM2
	private List tierCriteriaDefinitionList; //CARS:AM2
	private List tieredAdminMethodList ;//CARS:AM2
	
	private Map benefitHideShowFlagMap; // eWPD Stabilization 2011 for benefit hide/unhide
	
	 //Code change for product tree rendering optimization
	private boolean treeStructureUpdated = false;
	private TreeModelBase productTree = null;
	
	
	//CARS:AM2:START
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
     * Returns the productKeyObject
     * @return ProductKeyObject productKeyObject.
     */
    public ProductKeyObject getProductKeyObject() {
        return productKeyObject;
    }
    
    /**
     * Sets the productKeyObject
     * @param productKeyObject.
     */
    public void setProductKeyObject(ProductKeyObject productKeyObject) {
        this.productKeyObject = productKeyObject;
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
     * Returns the searchResultList
     * @return List searchResultList.
     */
    public List getSearchResultList() {
        return searchResultList;
    }
    /**
     * Sets the searchResultList
     * @param searchResultList.
     */
    public void setSearchResultList(List searchResultList) {
        this.searchResultList = searchResultList;
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
     * Returns the allVersionSearchResults
     * @return List allVersionSearchResults.
     */
    public List getAllVersionSearchResults() {
        return allVersionSearchResults;
    }
    /**
     * Sets the allVersionSearchResults
     * @param allVersionSearchResults.
     */
    public void setAllVersionSearchResults(List allVersionSearchResults) {
        this.allVersionSearchResults = allVersionSearchResults;
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
     * Returns the productKeyForVersionPage
     * @return int productKeyForVersionPage.
     */
    public int getProductKeyForVersionPage() {
        return productKeyForVersionPage;
    }
    /**
     * Sets the productKeyForVersionPage
     * @param productKeyForVersionPage.
     */
    public void setProductKeyForVersionPage(int productKeyForVersionPage) {
        this.productKeyForVersionPage = productKeyForVersionPage;
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
	 * @return Returns the productStructKey.
	 */
	public int getProductStructKey() {
		return productStructKey;
	}
	/**
	 * @param productStructKey The productStructKey to set.
	 */
	public void setProductStructKey(int productStructKey) {
		this.productStructKey = productStructKey;
	}
	/**
	 * @return Returns the beneiftDfnId.
	 */
	public int getBeneiftDfnId() {
		return beneiftDfnId;
	}
	/**
	 * @param beneiftDfnId The beneiftDfnId to set.
	 */
	public void setBeneiftDfnId(int beneiftDfnId) {
		this.beneiftDfnId = beneiftDfnId;
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
		
	/**Getter Setter method for Benefit hide unhide flag Map :: eWPD Stabilization 2011**/
	public Map getBenefitHideShowFlagMap() {
		return benefitHideShowFlagMap;
	}
	public void setBenefitHideShowFlagMap(Map benefitHideShowFlagMap) {
		this.benefitHideShowFlagMap = benefitHideShowFlagMap;
	}
	/** end :: by eWPD Stabilization 2011**/
	
	/**
	 */
	public void setOrgQuestionnaireList() {
		return;
	}
	/**
	 */
	public void setOrgTierQuestionnaireList() {
		return;
	}
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
	 * @return Returns the productTree.
	 */
	public TreeModelBase getProductTree() {
		return productTree;
	}
	/**
	 * @param productTree The productTree to set.
	 */
	public void setProductTree(TreeModelBase productTree) {
		this.productTree = productTree;
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
	 * ----------------------------------------------------------
	 */
	
	private Map dataHiddenValTerm;
	private Map dataHiddenValQualifier;
	private Map lineFreqValueMap;
	private Map hiddenLineFreqValueMap;
	private Map hiddenLowerLevelFreqValueMap;
	private Map hiddenLevelCheckMap;
	private Map dataHiddenLowerLevelValDesc;
	private Map customizedSysIdMap;
	private Map hiddenLineCheckMap;
	private Map lineIdMap;
	
	
	/**
	 * @return Returns the customizedSysIdMap.
	 */
	public Map getCustomizedSysIdMap() {
		return customizedSysIdMap;
	}
	/**
	 * @param customizedSysIdMap The customizedSysIdMap to set.
	 */
	public void setCustomizedSysIdMap(Map customizedSysIdMap) {
		this.customizedSysIdMap = customizedSysIdMap;
	}
	/**
	 * @return Returns the dataHiddenLowerLevelValDesc.
	 */
	public Map getDataHiddenLowerLevelValDesc() {
		return dataHiddenLowerLevelValDesc;
	}
	/**
	 * @param dataHiddenLowerLevelValDesc The dataHiddenLowerLevelValDesc to set.
	 */
	public void setDataHiddenLowerLevelValDesc(Map dataHiddenLowerLevelValDesc) {
		this.dataHiddenLowerLevelValDesc = dataHiddenLowerLevelValDesc;
	}
	/**
	 * @return Returns the dataHiddenValQualifier.
	 */
	public Map getDataHiddenValQualifier() {
		return dataHiddenValQualifier;
	}
	/**
	 * @param dataHiddenValQualifier The dataHiddenValQualifier to set.
	 */
	public void setDataHiddenValQualifier(Map dataHiddenValQualifier) {
		this.dataHiddenValQualifier = dataHiddenValQualifier;
	}
	/**
	 * @return Returns the dataHiddenValTerm.
	 */
	public Map getDataHiddenValTerm() {
		return dataHiddenValTerm;
	}
	/**
	 * @param dataHiddenValTerm The dataHiddenValTerm to set.
	 */
	public void setDataHiddenValTerm(Map dataHiddenValTerm) {
		this.dataHiddenValTerm = dataHiddenValTerm;
	}
	/**
	 * @return Returns the hiddenLevelCheckMap.
	 */
	public Map getHiddenLevelCheckMap() {
		return hiddenLevelCheckMap;
	}
	/**
	 * @param hiddenLevelCheckMap The hiddenLevelCheckMap to set.
	 */
	public void setHiddenLevelCheckMap(Map hiddenLevelCheckMap) {
		this.hiddenLevelCheckMap = hiddenLevelCheckMap;
	}
	/**
	 * @return Returns the hiddenLineCheckMap.
	 */
	public Map getHiddenLineCheckMap() {
		return hiddenLineCheckMap;
	}
	/**
	 * @param hiddenLineCheckMap The hiddenLineCheckMap to set.
	 */
	public void setHiddenLineCheckMap(Map hiddenLineCheckMap) {
		this.hiddenLineCheckMap = hiddenLineCheckMap;
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
	 * @return Returns the lineFreqValueMap.
	 */
	public Map getLineFreqValueMap() {
		return lineFreqValueMap;
	}
	/**
	 * @param lineFreqValueMap The lineFreqValueMap to set.
	 */
	public void setLineFreqValueMap(Map lineFreqValueMap) {
		this.lineFreqValueMap = lineFreqValueMap;
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
}

