/*
 * BenefitComponentDeleteRequest.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

import java.util.List;

/**
 * Request for Benefit Component Delete
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentDeleteRequest extends WPDRequest {

    private int benefitComponentKey;

    private String benefitComponentName;

    private int benefitComponentVersion;

    List domainList;


    /**
     * @return Returns the benefitComponentKey.
     */
    public int getBenefitComponentKey() {
        return benefitComponentKey;
    }


    /**
     * @param benefitComponentKey
     *            The benefitComponentKey to set.
     */
    public void setBenefitComponentKey(int benefitComponentKey) {
        this.benefitComponentKey = benefitComponentKey;
    }


    /**
     * @return Returns the benefitComponentName.
     */
    public String getBenefitComponentName() {
        return benefitComponentName;
    }


    /**
     * @param benefitComponentName
     *            The benefitComponentName to set.
     */
    public void setBenefitComponentName(String benefitComponentName) {
        this.benefitComponentName = benefitComponentName;
    }


    /**
     * @return Returns the benefitComponentVersion.
     */
    public int getBenefitComponentVersion() {
        return benefitComponentVersion;
    }


    /**
     * @param benefitComponentVersion
     *            The benefitComponentVersion to set.
     */
    public void setBenefitComponentVersion(int benefitComponentVersion) {
        this.benefitComponentVersion = benefitComponentVersion;
    }


    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {

    }


    /**
     * @return Returns the domainList.
     */
    public List getDomainList() {
        return domainList;
    }


    /**
     * @param domainList
     *            The domainList to set.
     */
    public void setDomainList(List domainList) {
        this.domainList = domainList;
    }
}