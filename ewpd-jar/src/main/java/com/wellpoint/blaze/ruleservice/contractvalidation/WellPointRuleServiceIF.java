/**
 * WellPointRuleServiceIF.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.blaze.ruleservice.contractvalidation;

public interface WellPointRuleServiceIF extends java.rmi.Remote {
    public java.util.Vector invokeRuleCategoryEntryPoint() throws java.rmi.RemoteException;
    public com.wellpoint.wpd.common.contract.ws.model.Messages invokeRuleExecutionEntryPoint(com.wellpoint.wpd.common.contract.ws.model.Contract arg_0_1, java.util.Vector arg_1_1) throws java.rmi.RemoteException;
}
