/*
 * AdminOptionsTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.standardbenefit.tree;

import com.wellpoint.wpd.web.tree.BaseTreeNode;

import org.apache.myfaces.custom.tree2.TreeModel;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionsTreeNodeBase  extends BaseTreeNode{
	
	private int  assnId;
	/**
	 * Constructor
	 */	
	 public AdminOptionsTreeNodeBase() {
       super();
	 }

	 /**
	  * Method for admin option tree node.
	  * 
	  * @param treeModel,parent,type,identifier,name,leaf
	  */
	 public AdminOptionsTreeNodeBase(TreeModel treeModel,
	  		BaseTreeNode parent, String type, String identifier,
           String name, boolean leaf) {
       super(treeModel, parent, type, identifier, name, leaf);

	  }
	 /**
	  * Method for admin option tree node.
	  * 
	  * @param treeModel,parent,type,identifier,name,leaf,assnid
	  */
	 public AdminOptionsTreeNodeBase(TreeModel treeModel,
	  		BaseTreeNode parent, String type, String identifier,
           String name, boolean leaf,int assnId) {
       super(treeModel, parent, type, identifier, name, leaf);
       this.assnId = assnId; 
	 }

	/**
	 * Returns the assnId
	 * @return int assnId.
	 */
	public int getAssnId() {
		return assnId;
	}
	/**
	 * Sets the assnId
	 * @param assnId.
	 */
	public void setAssnId(int assnId) {
		this.assnId = assnId;
	}
}

