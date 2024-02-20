/*
 * SaveContractBasicInfoResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wellpoint.ets.ebx.simulation.webservices.client.ContractWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerDisplayVO;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveContractBasicInfoResponse extends ContractResponse {
    private Contract contract;
    private DomainDetail domainDetail;
    private int ifLatestProductExist;
    private boolean ifLatestNoteExist;
    private boolean testDateSegments;
    private int condition;
    private boolean ifUncodedLineNotesExist;

    public static final int CHECKIN_SUCCESS = 1;
    public static final int VALIDATION_WAIT = 2;
    public static final int VALIDATION_RESULTS = 3;
    public static final int SPS_VALIDATION_ERROR = 4;
    
    private List deletedRulesList = null;
    private List unCodedRulesList = null;
    //sscr 17571
    private List VendorInfoListForDateSeg;
    private Map<String,List> carvedoutMap = null;
    
    public List getVendorInfoListForDateSeg() {
		return VendorInfoListForDateSeg;
	}
	public void setVendorInfoListForDateSeg(List vendorInfoListForDateSeg) {
		VendorInfoListForDateSeg = vendorInfoListForDateSeg;
	}
	public Map<String, List> getCarvedoutMap() {
		return carvedoutMap;
	}
	public void setCarvedoutMap(Map<String, List> carvedoutMap) {
		this.carvedoutMap = carvedoutMap;
	}
    //SSCR 17571 - Tab impl
	private int carvedOutCondition;
	
	public int getCarvedOutCondition() {
		return carvedOutCondition;
	}
	public void setCarvedOutCondition(int carvedOutCondition) {
		this.carvedOutCondition = carvedOutCondition;
	}
    // SSCR 16332
    
	private List<ContractWebServiceVO> contractWSErrorList = null;
    private List<EbxWebSerDisplayVO> wSErrorDisplayList =null;
    private String wsProcessError;
    
    
    public List<ContractWebServiceVO> getContractWSErrorList() {
		return contractWSErrorList;
	}
	public void setContractWSErrorList(
			List<ContractWebServiceVO> contractWSErrorList) {
		this.contractWSErrorList = contractWSErrorList;
	}
	
	public List<EbxWebSerDisplayVO> getWSErrorDisplayList() {
		return wSErrorDisplayList;
	}
	public void setWSErrorDisplayList(List<EbxWebSerDisplayVO> errorDisplayList) {
		wSErrorDisplayList = errorDisplayList;
	}
	
	public String getWsProcessError() {
		return wsProcessError;
	}
	public void setWsProcessError(String wsProcessError) {
		this.wsProcessError = wsProcessError;
	}
	/**
     * Returns the contract
     * @return Contract contract.
     */
    public Contract getContract() {
        return contract;
    }
    /**
     * Sets the contract
     * @param contract.
     */
    public void setContract(Contract contract) {
        this.contract = contract;
    }
    /**
     * Returns the domainDetail
     * @return DomainDetail domainDetail.
     */
    public DomainDetail getDomainDetail() {
        return domainDetail;
    }
    /**
     * Sets the domainDetail
     * @param domainDetail.
     */
    public void setDomainDetail(DomainDetail domainDetail) {
        this.domainDetail = domainDetail;
    }

	/**
	 * Returns the ifLatestProductExist
	 * @return int ifLatestProductExist.
	 */
	public int getIfLatestProductExist() {
		return ifLatestProductExist;
	}
	/**
	 * Sets the ifLatestProductExist
	 * @param ifLatestProductExist.
	 */
	public void setIfLatestProductExist(int ifLatestProductExist) {
		this.ifLatestProductExist = ifLatestProductExist;
	}
	
	/**
	 * Returns the testDateSegments
	 * @return boolean testDateSegments.
	 */
	public boolean isTestDateSegments() {
		return testDateSegments;
	}
	/**
	 * Sets the testDateSegments
	 * @param testDateSegments.
	 */
	public void setTestDateSegments(boolean testDateSegments) {
		this.testDateSegments = testDateSegments;
	}
    /**
     * @return ifLatestNoteExist
     * 
     * Returns the ifLatestNoteExist.
     */
    public boolean isIfLatestNoteExist() {
        return ifLatestNoteExist;
    }
    /**
     * @param ifLatestNoteExist
     * 
     * Sets the ifLatestNoteExist.
     */
    public void setIfLatestNoteExist(boolean ifLatestNoteExist) {
        this.ifLatestNoteExist = ifLatestNoteExist;
    }
	/**
	 * @return Returns the condition.
	 */
	public int getCondition() {
		return condition;
	}
	/**
	 * @param condition The condition to set.
	 */
	public void setCondition(int condition) {
		this.condition = condition;
	}
	/**
	 * @return Returns the deletedRulesList.
	 */
	public List getDeletedRulesList() {
		return deletedRulesList;
	}
	/**
	 * @param deletedRulesList The deletedRulesList to set.
	 */
	public void setDeletedRulesList(List deletedRulesList) {
		this.deletedRulesList = deletedRulesList;
	}
	/**
	 * @return Returns the unCodedRulesList.
	 */
	public List getUnCodedRulesList() {
		return unCodedRulesList;
	}
	/**
	 * @param unCodedRulesList The unCodedRulesList to set.
	 */
	public void setUnCodedRulesList(List unCodedRulesList) {
		this.unCodedRulesList = unCodedRulesList;
	}
	/**
	 * @return Returns the ifUncodedLineNotesExist.
	 */
	public boolean isIfUncodedLineNotesExist() {
		return ifUncodedLineNotesExist;
	}
	/**
	 * @param ifUncodedLineNotesExist The ifUncodedLineNotesExist to set.
	 */
	public void setIfUncodedLineNotesExist(boolean ifUncodedLineNotesExist) {
		this.ifUncodedLineNotesExist = ifUncodedLineNotesExist;
	}
}
