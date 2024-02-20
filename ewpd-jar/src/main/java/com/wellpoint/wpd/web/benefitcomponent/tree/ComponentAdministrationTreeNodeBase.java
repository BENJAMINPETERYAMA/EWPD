/*
 * ComponentAdministrationTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.benefitcomponent.tree;

import com.wellpoint.wpd.web.tree.BaseTreeNode;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ComponentAdministrationTreeNodeBase extends BaseTreeNode {
	/**
	 * Constructor
	 */	
	 public ComponentAdministrationTreeNodeBase() {
       super();
	 }

	 /**
	  * Constructor
	  * @param treeModel,parent,type,identifier,name,leaf
	  */
	 public ComponentAdministrationTreeNodeBase(TreeModel treeModel,
	  		BaseTreeNode parent, String type, String identifier,
             String name, boolean leaf) {
       
	 		super(treeModel, parent, type, identifier, name, leaf);

	  }
	 public  List loadChildren() {
	 	 children = new ArrayList();
	 	 
	 	 children.add(new ComponentLevelTreeNodeBase(getModel(), this,
                "Benefit-Level", "Benefit", "Benefit", false));	
   
	 	 children.add(new ComponentLevelTreeNodeBase(getModel(), this,
            "Benefit-Level", "Benefit Level", "Benefit Level", false));	
    return children;
	 }

}
