/*
 * ProductTreeBuilder.java
 *
 * © 2006 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Wellpoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.product.builder;

import com.wellpoint.wpd.business.product.adapter.ProductTreeAdapterManager;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeAdminOptions;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitAdministration;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitComponents;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeStandardBenefits;
import com.wellpoint.wpd.util.TimeHandler;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductTreeBuilder
{

  /**
   * 
   * @param standardBenefitsDetails
   * @return
   * @throws WPDException
   */
  public List searchBenefitsForMigrationTree(ProductTreeStandardBenefits standardBenefitsDetails) throws WPDException
  {
    ProductTreeAdapterManager adapterManager = new ProductTreeAdapterManager();
    return adapterManager.searchBenefitsForMigrationTree(standardBenefitsDetails);
  }
  
  /**
   * 
   * @param standardBenefitsDetails
   * @return
   * @throws WPDException
   */
  public List searchBenefitsForMigrationTree(ProductTreeStandardBenefits standardBenefitsDetails, int baseDateSegId) throws WPDException
  {
    ProductTreeAdapterManager adapterManager = new ProductTreeAdapterManager();
    return adapterManager.searchBenefitsForMigrationTree(standardBenefitsDetails, baseDateSegId);
  }

  /**
   * Method to get the major headings i.e the benefit components list
   * @param productTreeBenefitComponents
   * @return
   * @throws WPDException
   */
  public List getBenefitComponents(ProductTreeBenefitComponents productTreeBenefitComponents) throws WPDException
  {
    ProductTreeAdapterManager adapterManager = new ProductTreeAdapterManager();
    return adapterManager.getProductTreeBenefitComponents(productTreeBenefitComponents);
  }

  /**
   * 
   * @param standardBenefitsDetails
   * @return
   * @throws WPDException
   */
  public List getStandardBenefits(ProductTreeStandardBenefits standardBenefitsDetails) throws WPDException
  {
    ProductTreeAdapterManager adapterManager = new ProductTreeAdapterManager();
    return adapterManager.getProductTreeStandardBenefits(standardBenefitsDetails);
  }

  /**
   * 
   * @param productTreeBenefitAdministration
   * @return
   * @throws WPDException
   */
  public List getBenefitAdminstrations(ProductTreeBenefitAdministration productTreeBenefitAdministration) throws WPDException
  {
    ProductTreeAdapterManager adapterManager = new ProductTreeAdapterManager();
    return adapterManager.getProductTreeBenefitAdministrations(productTreeBenefitAdministration);
  }

  /**
   * 
   * @param adminOptionsDetails
   * @return
   */
  public List getAdminOptions(ProductTreeAdminOptions adminOptionsDetails) throws WPDException
  {
    ProductTreeAdapterManager adapterManager = new ProductTreeAdapterManager();
    return adapterManager.getProductTreeAdminOptions(adminOptionsDetails);
  }
}
