/*
 * Created on Sep 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.vo;

import java.util.List;

/**
 * @author u13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodsPopupVO {

    private String termCode;
    
    private String qualifierCode;
    
    private String providerCode;
    
    private int dataTypeId;
    
    private List questionAnswers;
    
    private int adminMethodId;
    
    private String adminMethodDesc;
    
    private int adminMethodNumber;
    

    /**
     * @return Returns the adminMethodDesc.
     */
    public String getAdminMethodDesc() {
        return adminMethodDesc;
    }
    /**
     * @param adminMethodDesc The adminMethodDesc to set.
     */
    public void setAdminMethodDesc(String adminMethodDesc) {
        this.adminMethodDesc = adminMethodDesc;
    }
    /**
     * @return Returns the adminMethodId.
     */
    public int getAdminMethodId() {
        return adminMethodId;
    }
    /**
     * @param adminMethodId The adminMethodId to set.
     */
    public void setAdminMethodId(int adminMethodId) {
        this.adminMethodId = adminMethodId;
    }
    /**
     * @return Returns the dataTypeId.
     */
    public int getDataTypeId() {
        return dataTypeId;
    }
    /**
     * @param dataTypeId The dataTypeId to set.
     */
    public void setDataTypeId(int dataTypeId) {
        this.dataTypeId = dataTypeId;
    }
    /**
     * @return Returns the providerCode.
     */
    public String getProviderCode() {
        return providerCode;
    }
    /**
     * @param providerCode The providerCode to set.
     */
    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }
    /**
     * @return Returns the qualifierCode.
     */
    public String getQualifierCode() {
        return qualifierCode;
    }
    /**
     * @param qualifierCode The qualifierCode to set.
     */
    public void setQualifierCode(String qualifierCode) {
        this.qualifierCode = qualifierCode;
    }
    /**
     * @return Returns the questionAnswers.
     */
    public List getQuestionAnswers() {
        return questionAnswers;
    }
    /**
     * @param questionAnswers The questionAnswers to set.
     */
    public void setQuestionAnswers(List questionAnswers) {
        this.questionAnswers = questionAnswers;
    }
    /**
     * @return Returns the termCode.
     */
    public String getTermCode() {
        return termCode;
    }
    /**
     * @param termCode The termCode to set.
     */
    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }
    /**
     * @return Returns the adminMethodNumber.
     */
    public int getAdminMethodNumber() {
        return adminMethodNumber;
    }
    /**
     * @param adminMethodNumber The adminMethodNumber to set.
     */
    public void setAdminMethodNumber(int adminMethodNumber) {
        this.adminMethodNumber = adminMethodNumber;
    }
}
