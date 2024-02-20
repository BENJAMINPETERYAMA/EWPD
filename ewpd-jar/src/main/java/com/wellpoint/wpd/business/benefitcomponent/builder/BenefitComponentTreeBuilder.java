/*
 * BenefitComponentTreeBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.benefitcomponent.builder;

import com.wellpoint.wpd.business.benefitcomponent.adapter.BenefitComponentTreeAdapterManager;
import com.wellpoint.wpd.common.benefitcomponent.tree.bo.TreeAdminLevels;
import com.wellpoint.wpd.common.benefitcomponent.tree.bo.TreeBenefitAdministration;
import com.wellpoint.wpd.common.benefitcomponent.tree.bo.TreeBenefitComponent;
import com.wellpoint.wpd.common.framework.exception.ServiceException;

import java.util.List;

/**
 * Builder for Benefit componet tree
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentTreeBuilder {

    /**
     * Method to get Benefit Component
     * 
     * @param treeDataObject
     * @return List
     * @throws ServiceException
     */
    public List getBenefitData(TreeBenefitComponent treeDataObject)
            throws ServiceException {
        BenefitComponentTreeAdapterManager benefitComponentTreeAdapterManager = new BenefitComponentTreeAdapterManager();
        return benefitComponentTreeAdapterManager
                .getBenefitData(treeDataObject);
    }


    /**
     * Method to get Benefit Administration
     * 
     * @param treeDataObject
     * @return List
     * @throws ServiceException
     */
    public List getAdminData(TreeBenefitAdministration treeDataObject)
            throws ServiceException {
        BenefitComponentTreeAdapterManager benefitComponentTreeAdapterManager = new BenefitComponentTreeAdapterManager();
        return benefitComponentTreeAdapterManager.getAdminData(treeDataObject);
    }


    /**
     * Method to get Admin Option
     * 
     * @param treeDataObject
     * @return List
     * @throws ServiceException
     */
    public List getAdminOptionData(TreeAdminLevels treeDataObject)
            throws ServiceException {
        BenefitComponentTreeAdapterManager benefitComponentTreeAdapterManager = new BenefitComponentTreeAdapterManager();
        return benefitComponentTreeAdapterManager
                .getAdminOptionData(treeDataObject);
    }
}