/*
 * ProductStructureBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public interface ProductStructureBO {
    
    public int getProductStructureId();
    
    public String getProductStructureName();
    
    public int getVersion();
    
    public int getProductStructureCode();
    
    public void setProductStructureCode(int productStructureCode);
    
    public String getProductStructureDescription();
    
    public void setProductStructureDescription(String productStructureDescription);

}
