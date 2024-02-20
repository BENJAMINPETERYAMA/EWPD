/*
 * ContractBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.contract.bo.DateSegment;
import com.wellpoint.wpd.common.contract.request.ContractRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractBackingBean extends WPDBackingBean {
    public static String CONTRACT_SESSION_KEY = "contract";
    protected static String CONTRACT_SEARCH_SESSION_KEY = "contractSearch";
    private String ifUncodedLineNotesExist;
    
    protected int getBenefitComponentIdFromSession(){
	    return getContractSession().getBenefitComponentId();
	}

    public WPDResponse executeService(ContractRequest request) {
        ContractKeyObject contractKeyObject = getContractSession().getContractKeyObject();
        request.setContractKeyObject(contractKeyObject);
        return super.executeService(request);
    }
    
    protected void setValuesForAdminMethodValidation(){
		String entityId = getContractSession().getContractKeyObject().getDateSegmentId() + "";
		String entityName = getContractSession().getContractKeyObject().getContractId();
		String entityType = "contract";
		
		getSession().setAttribute("AM_ENTITY_ID",entityId);
 		getSession().setAttribute("AM_ENTITY_TYPE",entityType);
 		getSession().setAttribute("AM_ENTITY_NAME",entityName);
 		getSession().setAttribute("AM_CONTRACT_ID",getContractSession().getContractKeyObject().getContractSysId() + "");
    }
    
    public static void setContractKeyToRequest(ContractRequest request) {
        HttpSession session =  (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        ContractSession contractSessionObject = (ContractSession)session.getAttribute(CONTRACT_SESSION_KEY);
        request.setContractKeyObject(contractSessionObject.getContractKeyObject());
    }
    
    protected ContractKeyObject getContractKeyObject(){
        return getContractSession().getContractKeyObject();
    }
    
    protected void setTierDefinitionListToSession(List tierDefinitionList){
    	getContractSession().setBenefitTierDefinitionList(tierDefinitionList);
    }
    
    protected void setTierLevalListToSession(List tierLevelList){
    	getContractSession().setBenefitTierLevelList(tierLevelList);
    }
    
    protected void setTierDefWithPsvlListToSession(List tierDefPsvlList){
    	getContractSession().setBenefitTierPsvlList(tierDefPsvlList);
    }
    
    protected void removeTierDefWithPsvlListFromSession(){
    	getContractSession().setBenefitTierPsvlList(null);
    }
    
    protected void removeTierDefinitionListFromSession(){
    	getContractSession().setBenefitTierDefinitionList(null);
    }
    
    protected void removeTierLevalListFromSession(){
    	getContractSession().setBenefitTierLevelList(null);
    }
    protected void setContractToSession(Contract contract) {
        ContractKeyObject contractKeyObject = new ContractKeyObject();
        contractKeyObject.setContractId(contract.getContractId());
        contractKeyObject.setContractSysId(contract.getContractSysId());
        contractKeyObject.setContractParentSysId(contract.getParentSysId());
        contractKeyObject.setContractType(contract.getContractType());
        DateSegment dateSegment =(DateSegment)contract.getDateSegmentList().get(0);
        contractKeyObject.setDateSegmentId(dateSegment.getDateSegmentSysId());
        contractKeyObject.setDsType(dateSegment.getDateSegmentType());
        getContractSession().setProductId(dateSegment.getProductId());
        contractKeyObject.setProductId(dateSegment.getProductId());
        contractKeyObject.setEffectiveDate(WPDStringUtil.getStringDate(dateSegment.getEffectiveDate()));
        contractKeyObject.setExpiryDate(WPDStringUtil.getStringDate(dateSegment.getExpiryDate()));
        contractKeyObject.setDateSegmentStatus(dateSegment.getStatus());
        contractKeyObject.setDateSegmentVersion(dateSegment.getVersion());
//        contractKeyObject.setStartDate(contract.getStartDate());
        contractKeyObject.setStatus(contract.getStatus());
        if (contract.getState() != null)
            contractKeyObject.setState(contract.getState().getState());
        contractKeyObject.setVersion(contract.getVersion());
        getContractSession().setContractKeyObject(contractKeyObject);
        this.setBreadCrumbText();
    }
    
    public void setBreadCrumbText() {
	    String contractId = null;
	    if(isViewMode()) {
	        contractId = getContractKeyObject().getContractId();
	        super.setBreadCrumbText("Contract Development >>  Contract ("+contractId+") >> View");	
	    } else {
		    if(getContractKeyObject() != null ) {
		        contractId = getContractKeyObject().getContractId();
		        if(isEditMode()) {
			        super.setBreadCrumbText("Contract Development >> Contract ("+contractId+") >> Edit");		            
		        } else if(isCopyMode()) {
		            super.setBreadCrumbText("Contract Development >>  Contract ("+contractId+") >> Copy");
		        }
		    } else {
		        //super.setBreadCrumbText("Contract Development >> Contract >> Create");
                super.setBreadCrumbText("Contract Development >> Contract Maintain>> Locate Criteria");
		    }
	    }
    }
    
	protected ContractSession getContractSession(){
	    ContractSession contractSessionObject = (ContractSession)getSession().getAttribute(CONTRACT_SESSION_KEY);
		if(contractSessionObject == null) {
		    contractSessionObject = new ContractSession();
			getSession().setAttribute(CONTRACT_SESSION_KEY,contractSessionObject);
		}
		return contractSessionObject;
	}
	
	public void clearSession(){
		getSession().removeAttribute(CONTRACT_SESSION_KEY);
		// Rule Validation
		getSession().removeAttribute(WebConstants.
				SESSION_DELETED_RULES_LIST);
		getSession().removeAttribute(WebConstants.
						SESSION_UNCODED_RULES_LIST);
	}
	
	protected ContractSearchSession getContractSearchSession(){
	    ContractSearchSession contractSearchSessionObject = (ContractSearchSession)getSession().getAttribute(CONTRACT_SEARCH_SESSION_KEY);
		if(contractSearchSessionObject == null) {
		    contractSearchSessionObject = new ContractSearchSession();
			getSession().setAttribute(CONTRACT_SEARCH_SESSION_KEY,contractSearchSessionObject);
		}
		return contractSearchSessionObject;
	}
	
    protected void setMode(int mode){
        if(mode != ContractSession.EDIT_MODE && mode != ContractSession.VIEW_MODE && mode != ContractSession.COPY_MODE)
            throw new IllegalArgumentException("The give mode is not valid");
        getContractSession().setMode(mode);
    }
    
	public boolean isTreeStructureUpdated() {
		return getContractSession().isTreeStructureUpdated();
	}
	
	public void updateTreeStructure() {
		setTreeStructureUpdated(true);
	}
	
	public void setTreeStructureUpdated(boolean treeStructureUpdated) {
		getContractSession().setTreeStructureUpdated(treeStructureUpdated);
	}
    
	public boolean isViewMode(){
	    if(getContractSession().getMode() == ContractSession.VIEW_MODE)
	        return true;
	    else 
	        return false;
	}
	
	public boolean isEditMode(){
	    if(getContractSession().getMode() == ContractSession.EDIT_MODE)
	        return true;
	    else 
	        return false;
	}
	
	public boolean isCopyMode(){
	    if(getContractSession().getMode() == ContractSession.COPY_MODE)
	        return true;
	    else 
	        return false;
	}
	
	public boolean isCopyHeadingsMode(){
	    if(getContractSession().getMode() == ContractSession.COPY_HEADINGS_MODE)
	        return true;
	    else 
	        return false;
	}
	
	public void setEditMode(){
	    getContractSession().setMode(ContractSession.EDIT_MODE);
	}
	
	public void setViewMode(){
	    getContractSession().setMode(ContractSession.VIEW_MODE);
	}
	
	public void setCopyMode(){
	    getContractSession().setMode(ContractSession.COPY_MODE);
	}
	
	protected int getContractKeyFromSession(){
	    return getContractKeyObject().getContractSysId();
	}
	
	protected String getContractNameFromSession(){
	    return getContractKeyObject().getContractId();
	}
	
	public void setCopyHeadingsMode(){
		getContractSession().setMode(ContractSession.COPY_HEADINGS_MODE);
	}
	/**
	 * @return Returns the ifUncodedLineNotesExist.
	 */
	public String getIfUncodedLineNotesExist() {
		return ifUncodedLineNotesExist;
	}
	/**
	 * @param ifUncodedLineNotesExist The ifUncodedLineNotesExist to set.
	 */
	public void setIfUncodedLineNotesExist(String ifUncodedLineNotesExist) {
		this.ifUncodedLineNotesExist = ifUncodedLineNotesExist;
	}
}
