/**
 * XML_RSIFServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.blaze;

public class XML_RSIFServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

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
        inner0.put("invokegetRuleInfo", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc invokegetRuleInfo0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "invokegetRuleInfoReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        invokegetRuleInfo0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("invokegetRuleInfo", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokegetRuleInfo"), _params0, _returnDesc0, _faults0, null);
        if (invokegetRuleInfo0Op instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokegetRuleInfo0Op).setOption("targetNamespace","http://blaze.wellpoint.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokegetRuleInfo0Op).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "XML_RSIF"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokegetRuleInfo0Op).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokegetRuleInfoResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokegetRuleInfo0Op).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokegetRuleInfoRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokegetRuleInfo0Op).setOption("outputName","invokegetRuleInfoResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokegetRuleInfo0Op).setOption("inputName","invokegetRuleInfoRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokegetRuleInfo0Op).setOption("buildNum","o0444.10");
        }
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        invokegetRuleInfo0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        list0.add(invokegetRuleInfo0Op);

        java.util.List list1 = new java.util.ArrayList();
        inner0.put("invokeselectAccidentRiderBenefits", list1);

        com.ibm.ws.webservices.engine.description.OperationDesc invokeselectAccidentRiderBenefits1Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params1 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "arg_0_2"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc1 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "invokeselectAccidentRiderBenefitsReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults1 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        invokeselectAccidentRiderBenefits1Op = new com.ibm.ws.webservices.engine.description.OperationDesc("invokeselectAccidentRiderBenefits", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectAccidentRiderBenefits"), _params1, _returnDesc1, _faults1, null);
        if (invokeselectAccidentRiderBenefits1Op instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectAccidentRiderBenefits1Op).setOption("targetNamespace","http://blaze.wellpoint.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectAccidentRiderBenefits1Op).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "XML_RSIF"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectAccidentRiderBenefits1Op).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectAccidentRiderBenefitsResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectAccidentRiderBenefits1Op).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectAccidentRiderBenefitsRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectAccidentRiderBenefits1Op).setOption("outputName","invokeselectAccidentRiderBenefitsResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectAccidentRiderBenefits1Op).setOption("inputName","invokeselectAccidentRiderBenefitsRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectAccidentRiderBenefits1Op).setOption("buildNum","o0444.10");
        }
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        invokeselectAccidentRiderBenefits1Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        list1.add(invokeselectAccidentRiderBenefits1Op);

        java.util.List list2 = new java.util.ArrayList();
        inner0.put("invokeselectBenefits", list2);

        com.ibm.ws.webservices.engine.description.OperationDesc invokeselectBenefits2Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params2 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "arg_0_1"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc2 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "invokeselectBenefitsReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults2 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        invokeselectBenefits2Op = new com.ibm.ws.webservices.engine.description.OperationDesc("invokeselectBenefits", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectBenefits"), _params2, _returnDesc2, _faults2, null);
        if (invokeselectBenefits2Op instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectBenefits2Op).setOption("targetNamespace","http://blaze.wellpoint.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectBenefits2Op).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "XML_RSIF"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectBenefits2Op).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectBenefitsResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectBenefits2Op).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectBenefitsRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectBenefits2Op).setOption("outputName","invokeselectBenefitsResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectBenefits2Op).setOption("inputName","invokeselectBenefitsRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectBenefits2Op).setOption("buildNum","o0444.10");
        }
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        invokeselectBenefits2Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        list2.add(invokeselectBenefits2Op);

        java.util.List list3 = new java.util.ArrayList();
        inner0.put("invokeselectComponents", list3);

        com.ibm.ws.webservices.engine.description.OperationDesc invokeselectComponents3Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params3 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "arg_0_0"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc3 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "invokeselectComponentsReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults3 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        invokeselectComponents3Op = new com.ibm.ws.webservices.engine.description.OperationDesc("invokeselectComponents", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectComponents"), _params3, _returnDesc3, _faults3, null);
        if (invokeselectComponents3Op instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectComponents3Op).setOption("targetNamespace","http://blaze.wellpoint.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectComponents3Op).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "XML_RSIF"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectComponents3Op).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectComponentsResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectComponents3Op).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectComponentsRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectComponents3Op).setOption("outputName","invokeselectComponentsResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectComponents3Op).setOption("inputName","invokeselectComponentsRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)invokeselectComponents3Op).setOption("buildNum","o0444.10");
        }
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        invokeselectComponents3Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        list3.add(invokeselectComponents3Op);

        operationDescriptions.put("XML_RSService",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
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
