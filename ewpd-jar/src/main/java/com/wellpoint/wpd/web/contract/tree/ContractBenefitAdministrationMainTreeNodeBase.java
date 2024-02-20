/*
 * ContractBenefitAdministrationTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract.tree;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.web.product.ProductSessionObject;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractBenefitAdministrationMainTreeNodeBase extends BaseTreeNode {

	protected static String PRODUCT_SESSION_KEY = "product";
	
    /**
     *
     */
    public ContractBenefitAdministrationMainTreeNodeBase(){
        super();
    }
    
    /**
     * 
     * @param treeModel
     * @param parent
     * @param type
     * @param identifier
     * @param name
     * @param leaf
     */
    public ContractBenefitAdministrationMainTreeNodeBase(TreeModel treeModel,
            BaseTreeNode parent, String type, String identifier,
            String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);

    }
  
    /**
	 * This method is used to load the Benefit Admin Option Headers 
	 * 
	 * @return List
	 */
	 public List loadChildren() {
	    
	    Logger.logInfo("entered method loadChildren of ContractBenefitAdministrationMainTreeNodeBase");
	     
        children = new ArrayList();
        String adminId = this.getIdentifier();
        getProductSessionObject().setBenefitAdminId(Integer.parseInt(adminId));
        
        //adds the Benefit Node
        children.add(new ContractBenefitLevelMainTreeNodeBase(getModel(), this,
                    "Benefit-Level", "Benefit", "Benefit", false));	
       
        //adds the Benefit Level Node
        children.add(new ContractBenefitLevelMainTreeNodeBase(getModel(), this,
                "Benefit-Level", "Benefit Level", "Benefit Level", false));	
        
        return children;
    }
	 
	 
 	/**
	 * Retrieves the ProductSessionObject from session.
	 * @return ProductSessionObject
	 */
	protected ProductSessionObject getProductSessionObject(){
		ProductSessionObject productSessionObject = (ProductSessionObject)getSession().getAttribute(PRODUCT_SESSION_KEY);
		if(productSessionObject == null) {
			productSessionObject = new ProductSessionObject();
			getSession().setAttribute(PRODUCT_SESSION_KEY,productSessionObject);
		}
		return productSessionObject;
	}
	
	protected HttpSession getSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        return session;
    }
}
