/*
 * ContractTreeNodeBase.java
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

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.wpd.business.contract.builder.ContractTreeBuilder;
import com.wellpoint.wpd.common.contract.tree.bo.ContractTreeProducts;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.web.tree.BaseTreeNode;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractMainTreeNodeBase extends BaseTreeNode {
	
	private String dateSegmentId;
    public ContractMainTreeNodeBase() {
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
	 public ContractMainTreeNodeBase(TreeModel treeModel,
	         BaseTreeNode parent, String type, String identifier,
            String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }
	 
	 
	 /**
	  * 
	  * @param type
	  * @param identifier
	  * @param name
	  * @param leaf
	  */
    public ContractMainTreeNodeBase(String type, String identifier, String name,String dateSegmentId,
            boolean leaf) {
        super(type, identifier, name, leaf);
        this.dateSegmentId = dateSegmentId;
    }
    
    /**
     * Loads the products for the contract when the contract node is clicked
     * @return List
     */
    public List loadChildren(){
        
        Logger.logInfo("entered method loadChildren of ContractMainTreeNodeBase");
        children = null;
        String contractId = this.getIdentifier();
        
        
        ContractTreeBuilder contractTreeBuilder = new ContractTreeBuilder();
        
        //an instance of product BO is created
        ContractTreeProducts productDetails = new ContractTreeProducts();
        
        //checks if the identifier i.e(contractId) is null
        if(null == contractId ||"0".equals(contractId))
            return children;
         
        if(null == this.dateSegmentId || "0".equals(this.dateSegmentId))
            return children;
        
        children = new ArrayList();
        productDetails.setContractId(Integer.parseInt(contractId));
        productDetails.setDateSegmentId(Integer.parseInt(this.dateSegmentId));
        children.add(new ContractAdministrationHeadTreeNodeBase(getModel(),this,"Administration-Head",dateSegmentId,"Administration",false));
        try {
            //fetches the product details
            productDetails = (ContractTreeProducts) 
            	contractTreeBuilder.retrieveProducts(productDetails);
        } catch (WPDException e) {
            // TODO Auto-generated catch block
            return null;
//        	Logger.logError(e);
        }
        
        if(null==productDetails)
            return null;
        // add the product as a children node
        children.add(new ContractProductMainTreeNodeBase(getModel(),
                this, "Product", Integer.toString(productDetails.getProductId()),
                productDetails.getProductName(), false,dateSegmentId));
        
    	return children;
    }
	/**
	 * @return Returns the dateSegmentId.
	 */
	public String getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(String dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
}
