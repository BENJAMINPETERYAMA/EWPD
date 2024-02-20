/*
 * StandardBenefitTreeBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.standardbenefit.builder;

import com.wellpoint.wpd.business.standardbenefit.adapter.StandardBenefitTreeAdapterManager;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.standardbenefit.tree.bo.TreeAdminOptions;
import com.wellpoint.wpd.common.standardbenefit.tree.bo.TreeBenefitAdministration;
import com.wellpoint.wpd.common.standardbenefit.tree.bo.TreeBenefitDefinition;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitTreeBuilder {
	
    /**
     * Builder methode to return Benefit Defenition List
     * @param object
     * @return List
     * @throws ServiceException
     */
	public List getStandardDefinitionData(TreeBenefitDefinition treeDataObject) throws ServiceException{
		StandardBenefitTreeAdapterManager standardBenefitTreeAdapterManager =new StandardBenefitTreeAdapterManager();
		
		return standardBenefitTreeAdapterManager.getStandardDefinitionData(treeDataObject);		
	}
	
	/**
     * Builder methode to return Benefit Administration List
     * @param object
     * @return List
     * @throws ServiceException
     */
	public List getBenefitAdminData(TreeBenefitAdministration treeDataObject)throws ServiceException{
		StandardBenefitTreeAdapterManager standardBenefitTreeAdapterManager =new StandardBenefitTreeAdapterManager();
		return standardBenefitTreeAdapterManager.getBenefitAdminData(treeDataObject);
	}
	
	/**
     *  Builder methode to return Admin Option List
     * @param object
     * @return List
     * @throws ServiceException
     */
	public List getAdminOptionData(TreeAdminOptions treeDataObject)throws ServiceException{
		StandardBenefitTreeAdapterManager standardBenefitTreeAdapterManager =new StandardBenefitTreeAdapterManager();
		return standardBenefitTreeAdapterManager.getAdminOptionData(treeDataObject);
	}
	
	

}
