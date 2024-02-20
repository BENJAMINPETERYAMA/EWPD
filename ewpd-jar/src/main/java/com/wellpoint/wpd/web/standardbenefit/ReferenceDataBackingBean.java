/*
 * Created on Feb 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.standardbenefit;

import com.wellpoint.wpd.common.standardbenefit.bo.MandateDefinition;
import com.wellpoint.wpd.common.standardbenefit.bo.MandateDefinitionImpl;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.standardbenefit.MandateBOImpl;

import java.util.ArrayList;
import java.util.List;


/**
 * Backing bean for reference data.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class ReferenceDataBackingBean extends WPDBackingBean {

    private List mandateList;

    private List jurisdictionList;

    private List providerList;

    private List dataTypeList;

    private MandateDefinition mandateDefinition1 = null;

    private MandateDefinition mandateDefinition2 = null;

    private MandateBO mandateBO1 = null;

    public ReferenceDataBackingBean() {
        mandateDefinition1 = new MandateDefinitionImpl();
        mandateDefinition2 = new MandateDefinitionImpl();

    }

    /**
     * @return Returns the mandateList.
     */
    public List getMandateList() {
        mandateList = new ArrayList(2);

        mandateDefinition1.setMandate("State-California");
        mandateList.add(mandateDefinition1);
        mandateDefinition2.setMandate("State-Nevada");
        mandateList.add(mandateDefinition2);
        return mandateList;

    }

    /**
     * @param mandateList The mandateList to set.
     */
    public void setMandateList(List mandateList) {
        this.mandateList = mandateList;
    }

    /**
     * @return Returns the jurisdictionList.
     */
    public List getJurisdictionList() {
        jurisdictionList = new ArrayList(4);
        mandateBO1 = new MandateBOImpl();
        mandateBO1.setJurisdictionCode("CA");
        mandateBO1.setJurisdictionName("California");
        jurisdictionList.add(mandateBO1);
        mandateBO1 = new MandateBOImpl();
        mandateBO1.setJurisdictionCode("TX");
        mandateBO1.setJurisdictionName("Texas");
        jurisdictionList.add(mandateBO1);
        mandateBO1 = new MandateBOImpl();
        mandateBO1.setJurisdictionCode("NV");
        mandateBO1.setJurisdictionName("Nevada");
        jurisdictionList.add(mandateBO1);
        mandateBO1 = new MandateBOImpl();
        mandateBO1.setJurisdictionCode("NY");
        mandateBO1.setJurisdictionName("New York");
        jurisdictionList.add(mandateBO1);
        return jurisdictionList;
    }

    /**
     * @param jurisdictionList The jurisdictionList to set.
     */
    public void setJurisdictionList(List jurisdictionList) {
        this.jurisdictionList = jurisdictionList;
    }

    /**
     * @return Returns the dataTypeList.
     */
    public List getDataTypeList() {
        return dataTypeList;
    }

    /**
     * @param dataTypeList The dataTypeList to set.
     */
    public void setDataTypeList(List dataTypeList) {
        this.dataTypeList = dataTypeList;
    }

    /**
     * @return Returns the providerList.
     */
    public List getProviderList() {
        return providerList;
    }

    /**
     * @param providerList The providerList to set.
     */
    public void setProviderList(List providerList) {
        this.providerList = providerList;
    }
}
