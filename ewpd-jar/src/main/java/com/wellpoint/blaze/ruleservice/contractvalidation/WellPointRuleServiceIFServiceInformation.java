/**
 * WellPointRuleServiceIFServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.blaze.ruleservice.contractvalidation;

public class WellPointRuleServiceIFServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

    private static java.util.Map operationDescriptions;
    private static java.util.Map typeMappings;

    static {
         initOperationDescriptions();
         initTypeMappings();
    }

    private static void initOperationDescriptions() { 
        operationDescriptions = new java.util.HashMap();

        java.util.Map inner0 = new java.util.HashMap();

        java.util.List list0 = new java.util.ArrayList();
        inner0.put("invokeRuleCategoryEntryPoint", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc invokeRuleCategoryEntryPoint0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "invokeRuleCategoryEntryPointReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://xml.apache.org/xml-soap", "Vector"), java.util.Vector.class, true, false, false, false, true, false); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        invokeRuleCategoryEntryPoint0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("invokeRuleCategoryEntryPoint", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "invokeRuleCategoryEntryPoint"), _params0, _returnDesc0, _faults0, null);
        if (invokeRuleCategoryEntryPoint0Op instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeRuleCategoryEntryPoint0Op).setOption("targetNamespace","http://contractvalidation.ruleservice.blaze.wellpoint.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeRuleCategoryEntryPoint0Op).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "WellPointRuleServiceIF"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeRuleCategoryEntryPoint0Op).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "invokeRuleCategoryEntryPointResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeRuleCategoryEntryPoint0Op).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "invokeRuleCategoryEntryPointRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeRuleCategoryEntryPoint0Op).setOption("outputName","invokeRuleCategoryEntryPointResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeRuleCategoryEntryPoint0Op).setOption("inputName","invokeRuleCategoryEntryPointRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeRuleCategoryEntryPoint0Op).setOption("buildNum","o0444.10");
        }
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        invokeRuleCategoryEntryPoint0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        list0.add(invokeRuleCategoryEntryPoint0Op);

        java.util.List list1 = new java.util.ArrayList();
        inner0.put("invokeRuleExecutionEntryPoint", list1);

        com.ibm.ws.webservices.engine.description.OperationDesc invokeRuleExecutionEntryPoint1Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params1 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "arg_0_1"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Contract"), com.wellpoint.wpd.common.contract.ws.model.Contract.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "arg_1_1"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://xml.apache.org/xml-soap", "Vector"), java.util.Vector.class, false, false, false, false, true, false), 
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc1 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "invokeRuleExecutionEntryPointReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Messages"), com.wellpoint.wpd.common.contract.ws.model.Messages.class, true, false, false, false, true, false); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults1 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        invokeRuleExecutionEntryPoint1Op = new com.ibm.ws.webservices.engine.description.OperationDesc("invokeRuleExecutionEntryPoint", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "invokeRuleExecutionEntryPoint"), _params1, _returnDesc1, _faults1, null);
        if (invokeRuleExecutionEntryPoint1Op instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeRuleExecutionEntryPoint1Op).setOption("targetNamespace","http://contractvalidation.ruleservice.blaze.wellpoint.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeRuleExecutionEntryPoint1Op).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "WellPointRuleServiceIF"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeRuleExecutionEntryPoint1Op).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "invokeRuleExecutionEntryPointResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeRuleExecutionEntryPoint1Op).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "invokeRuleExecutionEntryPointRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeRuleExecutionEntryPoint1Op).setOption("outputName","invokeRuleExecutionEntryPointResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeRuleExecutionEntryPoint1Op).setOption("inputName","invokeRuleExecutionEntryPointRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeRuleExecutionEntryPoint1Op).setOption("buildNum","o0444.10");
        }
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        invokeRuleExecutionEntryPoint1Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        list1.add(invokeRuleExecutionEntryPoint1Op);

        operationDescriptions.put("WellPointRuleServiceService",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "BenefitLines"),
                         com.wellpoint.wpd.common.contract.ws.model.BenefitLines.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Rule"),
                         com.wellpoint.wpd.common.contract.ws.model.Rule.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "RuleCategory"),
                         com.wellpoint.wpd.common.contract.ws.model.RuleCategory.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"),
                         java.lang.Object[].class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "AdminOption"),
                         com.wellpoint.wpd.common.contract.ws.model.AdminOption.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Note"),
                         com.wellpoint.wpd.common.contract.ws.model.Note.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "PricingInformation"),
                         com.wellpoint.wpd.common.contract.ws.model.PricingInformation.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "MembershipInformation"),
                         com.wellpoint.wpd.common.contract.ws.model.MembershipInformation.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Message"),
                         com.wellpoint.wpd.common.contract.ws.model.Message.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Contract"),
                         com.wellpoint.wpd.common.contract.ws.model.Contract.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Benefit"),
                         com.wellpoint.wpd.common.contract.ws.model.Benefit.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Messages"),
                         com.wellpoint.wpd.common.contract.ws.model.Messages.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "BenefitComponent"),
                         com.wellpoint.wpd.common.contract.ws.model.BenefitComponent.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Question"),
                         com.wellpoint.wpd.common.contract.ws.model.Question.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Product"),
                         com.wellpoint.wpd.common.contract.ws.model.Product.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "SuperProcessSteps"),
                         com.wellpoint.wpd.common.contract.ws.model.SuperProcessSteps.class);

        typeMappings = java.util.Collections.unmodifiableMap(typeMappings);
    }

    public java.util.Map getTypeMappings() {
        return typeMappings;
    }

    public Class getJavaType(javax.xml.namespace.QName xmlName) {
        return (Class) typeMappings.get(xmlName);
    }

    public java.util.Map getOperationDescriptions(String portName) {
        return (java.util.Map) operationDescriptions.get(portName);
    }

    public java.util.List getOperationDescriptions(String portName, String operationName) {
        java.util.Map map = (java.util.Map) operationDescriptions.get(portName);
        if (map != null) {
            return (java.util.List) map.get(operationName);
        }
        return null;
    }

}
