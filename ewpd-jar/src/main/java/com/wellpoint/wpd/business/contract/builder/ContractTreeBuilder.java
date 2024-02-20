/*
 * ProductTreeBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.builder;

import java.util.List;

import com.wellpoint.wpd.business.contract.adapter.ContractTreeAdapterManager;
import com.wellpoint.wpd.business.product.adapter.ProductTreeAdapterManager;
import com.wellpoint.wpd.common.contract.bo.TestData;
import com.wellpoint.wpd.common.contract.tree.bo.ContractTreeProducts;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeAdminOptions;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitAdministration;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitComponents;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeStandardBenefits;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractTreeBuilder {
    
    /**
     * This method to retrieve the Product Details for the corresponding Contract.
     * @param businessObject
     * @return
     * @throws WPDException
     */
    public Object retrieveProducts(ContractTreeProducts businessObject)throws WPDException {
       ContractTreeAdapterManager adapterManager = new ContractTreeAdapterManager();
       return adapterManager.retrieveProducts(businessObject);
    }
    /**
     * This method retrieves the benefit component details for the corresponding product in Contract t
     * @param details
     * @return
     * @throws WPDException
     */
    public List getBenefitComponents(ProductTreeBenefitComponents details)throws WPDException {
        ContractTreeAdapterManager  adapterManager = new ContractTreeAdapterManager();
      return adapterManager.getContractTreeBenefitComponents(details);
    }
    /**
     * This method retrieves the standard benfit details for the corresponding benefit component in Contract
     * @param standardBenefitsDetails
     * @return
     * @throws WPDException
     */
    public List getStandardBenefits(ProductTreeStandardBenefits standardBenefitsDetails) throws WPDException{
        ContractTreeAdapterManager adapterManager = new ContractTreeAdapterManager();
        return adapterManager.getContractTreeStandardBenefits(standardBenefitsDetails);
    }
    /**
     * This method retrieves the benefit administration details for the corresponding standard benefits in Contract 
     * @param details
     * @return
     */
    public List getBenefitAdminstrations(ProductTreeBenefitAdministration details)throws WPDException {
        ProductTreeAdapterManager adapterManager = new ProductTreeAdapterManager();
        return adapterManager.getProductTreeBenefitAdministrations(details);
        
    }
    /**
     * @param adminOptionsDetails
     * @return
     */
    public List getAdminOptions(ProductTreeAdminOptions adminOptionsDetails)throws WPDException {
        ProductTreeAdapterManager adapterManager = new ProductTreeAdapterManager();
        return adapterManager.getProductTreeAdminOptions(adminOptionsDetails);
    }
    /**
     * @param testData
     * @param user
     */
    public void deleteTestData(TestData testData) throws WPDException {
        // TODO Auto-generated method stub
        
    }
	/**
	 * @param benefitComponentDetails
	 * @return
	 */
	public List getBenefitComponentsForMigration(ProductTreeBenefitComponents benefitComponentDetails) throws WPDException{
		ContractTreeAdapterManager  adapterManager = new ContractTreeAdapterManager();
	      return adapterManager.getContractTreeBenefitComponentsForMigration(benefitComponentDetails);
	}
	
	/**
	 * @param benefitComponentDetails
	 * @return
	 */
	public List getBenefitComponentsForMigration(ProductTreeBenefitComponents benefitComponentDetails, int baseDateSegId) throws WPDException{
		ContractTreeAdapterManager  adapterManager = new ContractTreeAdapterManager();
	      return adapterManager.getContractTreeBenefitComponentsForMigration(benefitComponentDetails, baseDateSegId);
	}
}
