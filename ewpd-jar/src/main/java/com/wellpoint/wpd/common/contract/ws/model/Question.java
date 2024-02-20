/**
 * Question.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class Question  {
    private java.lang.String question;
    private java.lang.String answer;
    private java.lang.String reference;
    private java.lang.String pva;
    private com.wellpoint.wpd.common.contract.ws.model.Note note;

    public Question() {
    }

    public java.lang.String getQuestion() {
        return question;
    }

    public void setQuestion(java.lang.String question) {
        this.question = question;
    }

    public java.lang.String getAnswer() {
        return answer;
    }

    public void setAnswer(java.lang.String answer) {
        this.answer = answer;
    }

    public java.lang.String getReference() {
        return reference;
    }

    public void setReference(java.lang.String reference) {
        this.reference = reference;
    }

    public java.lang.String getPva() {
        return pva;
    }

    public void setPva(java.lang.String pva) {
        this.pva = pva;
    }

    public com.wellpoint.wpd.common.contract.ws.model.Note getNote() {
        return note;
    }

    public void setNote(com.wellpoint.wpd.common.contract.ws.model.Note note) {
        this.note = note;
    }

}
