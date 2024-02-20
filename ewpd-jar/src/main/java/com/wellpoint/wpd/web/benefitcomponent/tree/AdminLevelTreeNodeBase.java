/*
 * AdminLevelTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.benefitcomponent.tree;

import com.wellpoint.wpd.web.tree.BaseTreeNode;

import org.apache.myfaces.custom.tree2.TreeModel;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminLevelTreeNodeBase extends BaseTreeNode{
    
    
    private int adminOptionAssnId;
	/**
	 * Constructor
	 */	
	 public AdminLevelTreeNodeBase() {
       super();
	 }

	 /**
	  * Constructor
	  * @param treeModel,parent,type,identifier,name,leaf
	  */
	 public AdminLevelTreeNodeBase(TreeModel treeModel,
	  		BaseTreeNode parent, String type, String identifier,
             String name, boolean leaf,int adminOptionAssnId) {
       
	 		super(treeModel, parent, type, identifier, name, leaf);
	 		this.adminOptionAssnId=adminOptionAssnId;

	  }
	 	
    /**
     * Returns the adminOptionAssnId
     * @return int adminOptionAssnId.
     */
    public int getAdminOptionAssnId() {
        return adminOptionAssnId;
    }
    /**
     * Sets the adminOptionAssnId
     * @param adminOptionAssnId.
     */
    public void setAdminOptionAssnId(int adminOptionAssnId) {
        this.adminOptionAssnId = adminOptionAssnId;
    }
}
