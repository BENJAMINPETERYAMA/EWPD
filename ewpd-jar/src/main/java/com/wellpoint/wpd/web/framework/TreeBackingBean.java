/*
 * TreeBackingBean.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.custom.tree2.HtmlTree;
import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeModelBase;

import org.apache.myfaces.custom.tree2.TreeState;
import org.apache.myfaces.custom.tree2.TreeStateBase;

import com.wellpoint.wpd.web.tree.BaseTreeNode;
import com.wellpoint.wpd.web.tree.RootTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TreeBackingBean extends WPDBackingBean{
	TreeState treeState;
	String structureId ;
	TreeModelBase treeDataModel;
	public TreeBackingBean(){
		structureId =(String) getSession().getAttribute("structureId");
		if(structureId == null){
			structureId = "1";
		}
		if(getSession().getAttribute("StructureTreeState")!= null)
			treeState = (TreeState)getSession().getAttribute("StructureTreeState");
		else{
			treeState = new TreeStateBase();
	        treeState.setTransient(true);
	        getSession().setAttribute("StructureTreeState",treeState);
		}
	}
    public TreeModel getTreeDataModel() {
    	if(treeDataModel == null){
    		BaseTreeNode root = new RootTreeNode("root",structureId,"ROOT"+structureId,false);
    		treeDataModel = new TreeModelBase(root);
    		root.setModel(treeDataModel);
    	}
    	treeDataModel.setTreeState(treeState);
        return treeDataModel;
    }
    
    public void processAction(ActionEvent event){
    	 UIComponent component = (UIComponent) event.getSource();
    	 while (!(component != null && component instanceof HtmlTree)) {
            component = component.getParent();
         }
    	 HtmlTree tree = (HtmlTree) component;
//    	 TreeNode node = tree.getNode();

    }

}
