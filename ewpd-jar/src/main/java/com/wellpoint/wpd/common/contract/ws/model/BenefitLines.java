/**
 * BenefitLines.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class BenefitLines  {
    private java.lang.String term;
    private java.lang.String qualifier;
    private java.lang.String pva;
    private java.lang.String format;
    private java.lang.String reference;
    private java.lang.String benefitValue;
    private java.lang.String benefitLevelDescription;
    private com.wellpoint.wpd.common.contract.ws.model.Note note;
    private java.lang.Integer levelId;

    public BenefitLines() {
    }

    public java.lang.String getTerm() {
        return term;
    }

    public void setTerm(java.lang.String term) {
        this.term = term;
    }

    public java.lang.String getQualifier() {
        return qualifier;
    }

    public void setQualifier(java.lang.String qualifier) {
        this.qualifier = qualifier;
    }

    public java.lang.String getPva() {
        return pva;
    }

    public void setPva(java.lang.String pva) {
        this.pva = pva;
    }

    public java.lang.String getFormat() {
        return format;
    }

    public void setFormat(java.lang.String format) {
        this.format = format;
    }

    public java.lang.String getReference() {
        return reference;
    }

    public void setReference(java.lang.String reference) {
        this.reference = reference;
    }

    public java.lang.String getBenefitValue() {
        return benefitValue;
    }

    public void setBenefitValue(java.lang.String benefitValue) {
        this.benefitValue = benefitValue;
    }

    public java.lang.String getBenefitLevelDescription() {
        return benefitLevelDescription;
    }

    public void setBenefitLevelDescription(java.lang.String benefitLevelDescription) {
        this.benefitLevelDescription = benefitLevelDescription;
    }

    public com.wellpoint.wpd.common.contract.ws.model.Note getNote() {
        return note;
    }

    public void setNote(com.wellpoint.wpd.common.contract.ws.model.Note note) {
        this.note = note;
    }

    public java.lang.Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(java.lang.Integer levelId) {
        this.levelId = levelId;
    }

}
