/*
 * ContractAdminOptionsTreeNodeBase.java
 * 
 * � 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract.tree;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.wpd.web.tree.BaseTreeNode;



/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractAdminOptionsMainTreeNodeBase extends BaseTreeNode {

    private int adminLevelOptionAssnId;
    
    public ContractAdminOptionsMainTreeNodeBase(){
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
    public ContractAdminOptionsMainTreeNodeBase(TreeModel treeModel,
            BaseTreeNode parent, String type, String identifier,
            String name, boolean leaf,int adminLevelOptionAssnId) {
        super(treeModel, parent, type, identifier, name, leaf);
        
        //assigning the adminLevelOptionAssnId so as to be available when clicking on the tree
        this.adminLevelOptionAssnId=adminLevelOptionAssnId;
    }

    /**
     * Returns the adminLevelOptionAssnId
     * @return int adminLevelOptionAssnId.
     */
    public int getAdminLevelOptionAssnId() {
        return adminLevelOptionAssnId;
    }
    /**
     * Sets the adminLevelOptionAssnId
     * @param adminLevelOptionAssnId.
     */
    public void setAdminLevelOptionAssnId(int adminLevelOptionAssnId) {
        this.adminLevelOptionAssnId = adminLevelOptionAssnId;
    }
}
