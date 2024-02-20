/*
 * RetrieveSubCatalogResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.subcatalog.response;

import java.util.List;

import com.wellpoint.wpd.common.catalog.bo.CatalogBO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveSubCatalogResponse extends WPDResponse {
    
    private CatalogBO catalogBO;
    
    private DomainDetail domainDetail;
    
    private List itemList;

    /**
     * @return domainDetail
     * Returns the domainDetail.
     */
    public DomainDetail getDomainDetail() {
        return domainDetail;
    }
    /**
     * @param domainDetail
     * Sets the domainDetail.
     */
    public void setDomainDetail(DomainDetail domainDetail) {
        this.domainDetail = domainDetail;
    }
    /**
     * @return catalogBO
     * Returns the catalogBO.
     */
    public CatalogBO getCatalogBO() {
        return catalogBO;
    }
    /**
     * @param catalogBO
     * Sets the catalogBO.
     */
    public void setCatalogBO(CatalogBO catalogBO) {
        this.catalogBO = catalogBO;
    }
    
    /**
     * @return Returns the itemList.
     * @return List itemList
     */
    public List getItemList() {
        return itemList;
    }
    /**
     * Sets the itemList
     * @param itemList
     */
    public void setItemList(List itemList) {
        this.itemList = itemList;
    }
}
