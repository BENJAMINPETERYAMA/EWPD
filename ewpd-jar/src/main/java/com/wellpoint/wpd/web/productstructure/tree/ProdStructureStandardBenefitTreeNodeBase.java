/*
 * ProdStructureStandardBenefitTreeNodeBase.java
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
import com.wellpoint.wpd.common.productstructure.tree.bo.ProdStructureTreeBenefitDate;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProdStructureStandardBenefitTreeNodeBase extends BaseTreeNode {

    public ProdStructureStandardBenefitTreeNodeBase() {
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
    public ProdStructureStandardBenefitTreeNodeBase(TreeModel treeModel,
            BaseTreeNode parent, String type, String identifier, String name,
            boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }


    /**
     * Loads administration
     */
    public List loadChildren() {
    	children = new ArrayList(0);
        List benefitDateList = new ArrayList(0);
        leafNodeId = this.getIdentifier();
        String benefitComponentId = this.getParent().getIdentifier();
        ProdStructureTreeBenefitDate treeBenefitDate = new ProdStructureTreeBenefitDate();
        ProductStructureBusinessObjectBuilder productStructureBusinessObjectBuilder = new ProductStructureBusinessObjectBuilder();
        String[] idArray = leafNodeId.split("~");
        treeBenefitDate.setStandardBenefitId(Integer.parseInt(idArray[0]));
        treeBenefitDate.setBnftCmpntId(Integer.parseInt(benefitComponentId));

        try {
            benefitDateList = productStructureBusinessObjectBuilder
                    .getBenefitDate(treeBenefitDate);
        } catch (WPDException e) {
            //TODO Auto-generated catch block
        	Logger.logError(e);
        }
        if (benefitDateList.size() == 0) {
            return null;
        } else {
            Iterator benfitDateIter = benefitDateList.iterator();
        	children = new ArrayList(benefitDateList == null ? 0: benefitDateList.size());
            while (benfitDateIter.hasNext()) {
                treeBenefitDate = (ProdStructureTreeBenefitDate) benfitDateIter
                        .next();
                String effectiveDate = treeBenefitDate.getEffectiveDate();
                String expiryDate = treeBenefitDate.getExpiryeDate();
                leafNodeId = Integer.toString(treeBenefitDate.getAdminId());
                String dateString = effectiveDate + "-" + expiryDate;
                children.add(new ProdStructureBenefitDateTreeNodeBase(
                        getModel(), this, "BenefitDate", leafNodeId,
                        "Administration", false));
            }
        }
        return children;
    }

}