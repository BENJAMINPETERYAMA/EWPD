/*
 * ProductTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.product.tree;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import com.wellpoint.wpd.business.product.builder.ProductTreeBuilder;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitComponents;


import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductTreeNodeBase extends ProductTreeNode {

	
	//private ProductTreeNodeBase parent;
	
	public ProductTreeNodeBase(){
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
	 public ProductTreeNodeBase(TreeModel treeModel,
            ProductTreeNode parent, String type, String identifier,
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
    public ProductTreeNodeBase(String type, String identifier, String name,
            boolean leaf) {
        super(type, identifier, name, leaf);
    } 
	
    /**
     * Loads the benefit Components for the product when the product node is clicked
     * 
     * 
     * @return List
     */
    protected List loadChildren(){
        
        Logger.logInfo("entered method loadChildren of ProductTreeNodeBase");
        
        children = new ArrayList();
        productId=this.getIdentifier();
        List benefitComponents = null;
        
        ProductTreeBuilder productTreeBuilder=new ProductTreeBuilder();
        
        //an instance of benefitComponent BO is created
        ProductTreeBenefitComponents benefitComponentDetails=new ProductTreeBenefitComponents();
        
        //checks if the identifier i.e(productId) is null
        if(null==productId ||"0".equals(productId)){
            return benefitComponents;
        }
        
       
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
        .getExternalContext().getSession(true);
        String producttype = (String)session.getAttribute("ptoductType");
       
        benefitComponentDetails.setProductId(Integer.parseInt(productId));
        if(!"MANDATE".equals(producttype)){
        children.add(new ProductAdministrationHeadTreeNodeBase(getModel(),
                this, "Administration-Head",Integer.toString(benefitComponentDetails.getProductId()),"Administration", false));
        }
        try {
            
       
            //fetches the benefit component details
            benefitComponents = productTreeBuilder.getBenefitComponents( benefitComponentDetails);
            
        } catch (WPDException e) {
            
            // TODO Auto-generated catch block
			Logger.logError(e);
        }
        
        
        //the benefitComponent List is iterated to get the benefit Components      
        if(null != benefitComponents){
	        for(int i=0;i<benefitComponents.size();i++)
	        {
	            benefitComponentDetails=(ProductTreeBenefitComponents)benefitComponents.get(i);
	            
	            
	            //benefit Components added as the children of Product
	            children.add(new ProductBenefitComponentTreeNodeBase(getModel(),
	                    this, "Benefit-Component",Integer.toString(benefitComponentDetails.getBenefitComponentId()),benefitComponentDetails.getBenefitComponentDesc(), false));
	            
	            
	        }
        }
        
 
        
    	return children;
    }
}

