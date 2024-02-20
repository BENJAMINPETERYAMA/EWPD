/*
 * ContractAdministrationHeadTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.wpd.business.contract.adapter.ContractAdapterManager;
import com.wellpoint.wpd.common.contract.bo.ContractProductAdminBO;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractAdministrationHeadTreeNodeBase extends BaseTreeNode{
	  public ContractAdministrationHeadTreeNodeBase() {
        super();
    }

    /**
     * 
     * 
     * @param treeModel
     * @param parent
     * @param type
     * @param identifier
     * @param name
     * @param leaf
     */
    public ContractAdministrationHeadTreeNodeBase(TreeModel treeModel,
            BaseTreeNode parent, String type, String identifier,
            String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }

    /**
     * Loads the benefit Components for the product when the product node is clicked
     * 
     * 
     * @return List
     */
    public List loadChildren(){
        
        Logger.logInfo("entered method loadChildren of ContractProductMainTreeNodeBase");
        children= new ArrayList();
        int dateSegmentId= Integer.parseInt(this.getIdentifier());
        ContractAdapterManager adapterManager = new ContractAdapterManager();
        List adminList = null;
        try{
         adminList = adapterManager.getAdminList(dateSegmentId);
          
        }catch(WPDException ex){
        	Logger.logError(ex);
        }
        Iterator itr = adminList.iterator();
        while(itr.hasNext()){
        ContractProductAdminBO  AdminBO = (ContractProductAdminBO)itr.next();
        String AdminId = Integer.toString(AdminBO.getAdminKey());
        String AdminDesc =AdminBO.getAdminDesc();
        children.add(new  ContractAdministrationTreeNodeBase(getModel(),this,"Administration",AdminId,AdminDesc,true));
        }
        
        
    	return children;
    }

}
