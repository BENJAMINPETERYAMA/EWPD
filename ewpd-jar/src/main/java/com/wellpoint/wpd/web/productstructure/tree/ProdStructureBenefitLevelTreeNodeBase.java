/*
 * ProdStructureBenefitLevelTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.productstructure.tree;

import com.wellpoint.wpd.business.productstructure.builder.ProductStructureBusinessObjectBuilder;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.productstructure.tree.bo.ProdStructureTreeAdminOptions;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import org.apache.myfaces.custom.tree2.TreeModel;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProdStructureBenefitLevelTreeNodeBase extends BaseTreeNode {

    public ProdStructureBenefitLevelTreeNodeBase() {
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
    public ProdStructureBenefitLevelTreeNodeBase(TreeModel treeModel,
            BaseTreeNode parent, String type, String identifier, String name,
            boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }


    /**
     * Loads admin options for product structure return adminoption list
     */
    public List loadChildren() {
        children = new ArrayList(0);
        List adminOptionList = new ArrayList(0);
        leafNodeId = this.getParent().getIdentifier();
        ProdStructureTreeAdminOptions treeAdminOptions = new ProdStructureTreeAdminOptions();
        ProductStructureBusinessObjectBuilder productStructureBusinessObjectBuilder = new ProductStructureBusinessObjectBuilder();
        treeAdminOptions.setAdminId(Integer.parseInt(leafNodeId));

        // Get the benefitComponentId from the tree
        String benefitComponentId = this.getParent().getParent()
                .getParent().getIdentifier();
        
        String productStructureId = this.getParent().getParent()
        .getParent().getParent().getIdentifier();

        treeAdminOptions.setBenefitComponentId(Integer.parseInt(benefitComponentId));
        treeAdminOptions.setEntitySysId(Integer.parseInt(productStructureId));
        int type = (this.getIdentifier().equals("Benefit")) ? 2 : 1;
        treeAdminOptions.setLvlId(type);
        try {
            adminOptionList = productStructureBusinessObjectBuilder
                    .getAdminOptions(treeAdminOptions);
        } catch (WPDException e) {
            //TODO Auto-generated catch block
        	Logger.logError(e);
        }
        if (adminOptionList.size() == 0) {
            return null;
        } else {
        	children = new ArrayList(adminOptionList==null?0:adminOptionList.size());
            Iterator adminIter = adminOptionList.iterator();
            while (adminIter.hasNext()) {
                treeAdminOptions = (ProdStructureTreeAdminOptions) adminIter
                        .next();
                String optionDesc = treeAdminOptions.getOptionDesc();
                int optionId = treeAdminOptions.getOptionIdentifier();
                String id = new Integer(optionId).toString();
                String optionIdAndAdminId = id + "~" + leafNodeId;

                // Create an object to concatenate the ids
                StringBuffer adminOptionAndBenefitComponentIds = new StringBuffer(
                        optionIdAndAdminId);

                // Append the benefitComponentId to the above object
                adminOptionAndBenefitComponentIds.append("~").append(
                        benefitComponentId);
                
                int adminLevelOptionAssnId = treeAdminOptions.getAdminLevelOptionAssnId();

                String desc = this.getDescription();
                if (type == 1 && desc.equals("Benefit Level")) {
                    children.add(new ProdStructureAdminOptionsTreeNodeBase(
                            getModel(), this, "AdminOptionsBenefitLevel",
                            adminOptionAndBenefitComponentIds.toString(),
                            optionDesc, true,adminLevelOptionAssnId));
                }
                if (type == 2 && desc.equals("Benefit")) {
                    children.add(new ProdStructureAdminOptionsTreeNodeBase(
                            getModel(), this, "AdminOptionsBenefitLevel",
                            adminOptionAndBenefitComponentIds.toString(),
                            optionDesc, true,adminLevelOptionAssnId));
                }
            }
        }

        return children;
    }


    /**
     * 
     * @return HttpSession
     */
    private HttpSession getSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        return session;
    }

}

