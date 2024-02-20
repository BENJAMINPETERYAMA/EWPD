/*
 * LauchPageBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.workInventory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.common.bo.CheckedOutObject;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LaunchPageBackingBean.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class LaunchPageBackingBean extends WPDBackingBean {

    private List businessObjectsList;

    private List standardBenefitList;

    private List productList;

    private List productStructureList;

    private String userId;

    private final String PROD = "com.wellpoint.wpd.common.product.bo.ProductBO";

    private final String PROD_STRUCT = "com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO";

    private final String BENEFIT = "com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO";
    
    private boolean authorizedProductSearch;
    
    private boolean authorizedContractSearch;
    
    private boolean authorizedMigrationWizard;
    
    private boolean authorizedNotesSearch;
    
    private boolean authorizedSearch;

   

    /**
     * @return Returns the businessObjectsList.
     */
    public List getBusinessObjectsList() {
        return businessObjectsList;
    }

    /**
     * @param businessObjectsList
     *            The businessObjectsList to set.
     */
    public void setBusinessObjectsList(List businessObjectsList) {
        this.businessObjectsList = businessObjectsList;
    }

    /**
     * @return Returns the productList.
     */
    private void createProductList(){
    	if (null != businessObjectsList) {
            Iterator businessObjectsListIterator = businessObjectsList
                    .iterator();
            CheckedOutObject chkOutObj;
            while (businessObjectsListIterator.hasNext()) {
                chkOutObj = (CheckedOutObject) businessObjectsListIterator
                        .next();
                if (PROD.equals(chkOutObj.getName())) {
                    if (productList == null) {
                        productList = new ArrayList();
                    }
                    productList.add(chkOutObj);
                }
            }
        }
    }
    public List getProductList() {
        return productList;
    }


    private void createProductStructureList(){
    	if (null != businessObjectsList) {
            CheckedOutObject chkOutObj;
            Iterator businessObjectsListIterator = businessObjectsList
                    .iterator();
            while (businessObjectsListIterator.hasNext()) {
                chkOutObj = (CheckedOutObject) businessObjectsListIterator
                        .next();
                if (PROD_STRUCT.equals(chkOutObj.getName())) {
                    if (productStructureList == null) {
                        productStructureList = new ArrayList();
                    }
                    productStructureList.add(chkOutObj);
                }
            }
        }
    }
    /**
     * @return Returns the productStructureList.
     */    
    public List getProductStructureList() {
    	return productStructureList;
    }
    
    private void createStandardBenefitList(){
        if (null != businessObjectsList) {
            CheckedOutObject chkOutObj;
            Iterator businessObjectsListIterator = businessObjectsList
                    .iterator();
            while (businessObjectsListIterator.hasNext()) {
                chkOutObj = (CheckedOutObject) businessObjectsListIterator
                        .next();
                if (BENEFIT.equals(chkOutObj.getName())) {
                    if (standardBenefitList == null) {
                        standardBenefitList = new ArrayList();
                    }
                    standardBenefitList.add(chkOutObj);
                }
            }
        }
    }

    /**
     * @return Returns the standardBenefitList.
     */

    public List getStandardBenefitList() {
    	return standardBenefitList ;
    }

    /**
     * @return Returns the userId.
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @return Returns the userId.
     */
    public boolean getAuthorizedProductSearch() 
    {
    	try {
    		return getUser().isAuthorized(WebConstants.PRODUCT_MODULE, WebConstants.MAINTAIN_TASK);    		
		} catch (SevereException e) {
			
			return false;
		}
    }
    
	/**
	 * @param authorizedProductSearch The authorizedProductSearch to set.
	 */
	public void setAuthorizedProductSearch(boolean authorizedProductSearch) {
		this.authorizedProductSearch = authorizedProductSearch;
	}
	
	/**
	 * @return Returns the authorizedContractSearch.
	 */
	public boolean getAuthorizedContractSearch() {
		try {
			return getUser().isAuthorized(WebConstants.CONTRACT_DEVELOPMENT_MODULE,WebConstants.MAINTAIN_TASK);
		} catch (SevereException e) {
			
			return false;
		}
	}
	/**
	 * @param authorizedContractSearch The authorizedContractSearch to set.
	 */
	public void setAuthorizedContractSearch(boolean authorizedContractSearch) {
		this.authorizedContractSearch = authorizedContractSearch;
	}
	/**
	 * @return Returns the authorizedNotesSearch.
	 */
	public boolean getAuthorizedNotesSearch() {
		try {
			return getUser().isAuthorized(WebConstants.NOTES_MODULE, WebConstants.MAINTAIN_TASK);			
		} catch (SevereException e) {
			
			return false;
		}
	}
	/**
	 * @param authorizedNotesSearch The authorizedNotesSearch to set.
	 */
	public void setAuthorizedNotesSearch(boolean authorizedNotesSearch) {
		this.authorizedNotesSearch = authorizedNotesSearch;
	}
	/**
	 * @return Returns the authorizedSearch.
	 */
	public boolean getAuthorizedSearch() {
		try {
			return getUser().isAuthorized(WebConstants.SEARCH_ENGINE_MODULE,WebConstants.ADVANCED_SEARCH_TASK);			
		} catch (SevereException e) {
			
			return false;
		}
	}
	/**
	 * @param authorizedSearch The authorizedSearch to set.
	 */
	public void setAuthorizedSearch(boolean authorizedSearch) {
		this.authorizedSearch = authorizedSearch;
	}
	/**
	 * @return Returns the authorizedMigrationWizard.
	 */
	public boolean getAuthorizedMigrationWizard() {
		try {
			return getUser().isAuthorized(WebConstants.MIGRATION_WIZARD_MODULE,null);			
		} catch (SevereException e) {
			
			return false;
		}
	}
	/**
	 * @param authorizedMigrationWizard The authorizedMigrationWizard to set.
	 */
	public void setAuthorizedMigrationWizard(boolean authorizedMigrationWizard) {
		this.authorizedMigrationWizard = authorizedMigrationWizard;
	}
}