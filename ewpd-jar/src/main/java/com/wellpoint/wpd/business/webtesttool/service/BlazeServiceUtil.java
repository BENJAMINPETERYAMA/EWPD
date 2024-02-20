/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.webtesttool.service;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.xmlbeans.XmlException;

import com.blazesoft.oes.WCSCDERRCDESDocument;
import com.blazesoft.oes.WCSCDERRCDETBLDocument;
import com.blazesoft.oes.WCSCLAIMAREAINPUTDocument;
import com.blazesoft.oes.WCSDTLRVNCDEFILLERDocument;
import com.blazesoft.oes.WCSDTLTABLEDocument;
import com.blazesoft.oes.WCSINDEXESDocument;
import com.blazesoft.oes.WCSINPUTPARMLISTDocument;
import com.blazesoft.oes.WCSRLOPDDocument;
import com.blazesoft.oes.WCSRLOPDOUTPUTAREADocument;
import com.blazesoft.oes.WCSRULMDDocument;
import com.wellpoint.blaze.XML_RSIF;
import com.wellpoint.blaze.XML_RSIFServiceLocator;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.webtesttool.bo.BenefitComponentRuleIDBO;
import com.wellpoint.wpd.common.webtesttool.bo.BenefitRuleIDBO;
import com.wellpoint.wpd.common.webtesttool.bo.ClaimHeaderBO;
import com.wellpoint.wpd.common.webtesttool.bo.ClaimLineBO;
import com.wellpoint.wpd.common.webtesttool.bo.TestCaseBO;


public class BlazeServiceUtil extends Thread{

	private TestCaseBO testCaseBO;
	private List benftCompRuleIdList;
	private Map benefitIdsMap;
	private List accidentBenefitRuleIdList;
	private boolean status = true;
	private Exception exception;
	private TestSuiteBusinessService testSuiteBusinessService;
	private List diagnosisList = null;
	public BlazeServiceUtil(TestCaseBO testCaseBO, 
							List benftCompRuleIdList,
							List accidentBenefitRuleIdList,
							Map benefitIdsMap){
		this.testCaseBO = testCaseBO;
		this.benftCompRuleIdList = benftCompRuleIdList;
		this.accidentBenefitRuleIdList = accidentBenefitRuleIdList;
		this.benefitIdsMap = benefitIdsMap;
	}

    public void run() {
        this.process();
    }
    public void process(){
        List benftRuleIdList;
    	List basicBenftRuleIdList = Collections.synchronizedList( new ArrayList() );
    	List riderBenftRuleIdList = Collections.synchronizedList( new ArrayList() );
    	List majMedicalBenftRuleIdList = Collections.synchronizedList( new ArrayList() );
    	
        ClaimLineBO claimLineBO = null;
        //Populate the ClaimHeader and ClaimLine array into XML
        ClaimHeaderBO claimHeaderBO = this.testCaseBO.getClaimHeaderBO();
        String xmlStrInput = populateHeaderAndLine(claimHeaderBO, testCaseBO.getClaimLineDetailList());
        
        //for each claim Line, set items for Blaze
        //	claimheader data
		//	clamline data arrray ( need to specify the line index)
		//	benefit comp rule ids
        int claimLineIndex = 1;
        int claimLineSize = this.testCaseBO.getClaimLineDetailList().size();
        for(Iterator ii = this.testCaseBO.getClaimLineDetailList().iterator(); ii.hasNext(); claimLineIndex++){
            claimLineBO = (ClaimLineBO) ii.next();
            
            XML_RSIF service;
            String resultXML;
            String inputXML="";
            try {
	                service = connect();
	                inputXML = getXMLForBeneftComp(claimLineBO, xmlStrInput, benftCompRuleIdList, claimLineIndex, claimLineSize);
	                Logger.logInfo(inputXML);
	                resultXML = service.invokeselectComponents(inputXML);
	                Logger.logInfo(resultXML);
	                //If Id is not expected.test case fail for th line. go to the next line
	                String actualBenftCompRuleSYSId = populateBenefitCompResult(claimLineBO, getBenftCompRuleIds(resultXML), benftCompRuleIdList);
	                if(!claimLineBO.isStatus()){
	                    continue;
	                }
	                benftRuleIdList = (List)benefitIdsMap.get(actualBenftCompRuleSYSId);
	                populateBenefitIdLists(benftRuleIdList, basicBenftRuleIdList, riderBenftRuleIdList, majMedicalBenftRuleIdList);
	                
	                //Call Blaze for Basic Benefit
	                if(basicBenftRuleIdList.size()>0){
		                inputXML = getXMLForBeneft(claimLineBO, xmlStrInput, basicBenftRuleIdList, claimLineIndex, claimLineSize);
		                resultXML = service.invokeselectBenefits(inputXML);
		                populateBenefitResult(claimLineBO, getBenftCompRuleIds(resultXML), basicBenftRuleIdList, "BASIC");
	                }
	                //Call Blaze for  Rider
	                if(riderBenftRuleIdList.size()>0){
		                inputXML = getXMLForBeneft(claimLineBO, xmlStrInput, riderBenftRuleIdList, claimLineIndex, claimLineSize);
		                resultXML = service.invokeselectBenefits(inputXML);
		                populateBenefitResult(claimLineBO, getBenftCompRuleIds(resultXML), riderBenftRuleIdList, "RIDER");
	                }
	                //Call Blaze for MajMedical
	                if(majMedicalBenftRuleIdList.size()>0){
		                inputXML = getXMLForBeneft(claimLineBO, xmlStrInput, majMedicalBenftRuleIdList, claimLineIndex, claimLineSize);
		                resultXML = service.invokeselectBenefits(inputXML);
		                populateBenefitResult(claimLineBO, getBenftCompRuleIds(resultXML), majMedicalBenftRuleIdList, "MAJMEDICAL");
	                }
	                //Call Blaze for Accident Rider
	                if(accidentBenefitRuleIdList.size()>0){
		                inputXML = getXMLForBeneft(claimLineBO, xmlStrInput, accidentBenefitRuleIdList, claimLineIndex, claimLineSize);
		                resultXML = service.invokeselectAccidentRiderBenefits(inputXML);
		                populateBenefitResult(claimLineBO, getBenftCompRuleIds(resultXML), accidentBenefitRuleIdList);
	                }
					
            } catch (RemoteException e1) {
                Logger.logError(inputXML);
                Logger.logError(e1);
                this.setStatus(false);
                this.setException(e1);
            } catch(Exception ee){
                Logger.logError(ee);
                this.setStatus(false);
                this.setException(ee);
            }
        }
    }
	private XML_RSIF connect()throws Exception{
	    XML_RSIFServiceLocator locator = new XML_RSIFServiceLocator();
	    return locator.getXML_RSService();
	}
	private void populateBenefitIdLists(List benftRuleIdList, List basicBenftRuleIdList, List riderBenftRuleIdList, List majMedicalBenftRuleIdList){
	    for(Iterator i = benftRuleIdList.iterator(); i.hasNext();){
        	BenefitRuleIDBO benefitRuleID = (BenefitRuleIDBO)i.next();
        	if(benefitRuleID.getBenefitCategory().equals("BASIC")){
        	    basicBenftRuleIdList.add(benefitRuleID);
        	}else if(benefitRuleID.getBenefitCategory().equals("RIDER")){
        	    riderBenftRuleIdList.add(benefitRuleID);
        	}else if(benefitRuleID.getBenefitCategory().equals("MAJMEDICAL")){
        	    majMedicalBenftRuleIdList.add(benefitRuleID);
        	}
	    }
	}
	// populate benefit component results
	private String populateBenefitCompResult(ClaimLineBO claimLineBO, List actualeBenftCompRuleIdList, List benftCompRuleIdList){
        String expectedBenftCompRuleId="";
        String actualBenftCompRuleSYSId="";
        //if expectedBenefitCompName is null, return
        String expectedBenefitCompName = claimLineBO.getExpectedBenefitComponent();
        if((expectedBenefitCompName != null) && (expectedBenefitCompName.length()>0))
        	expectedBenefitCompName = expectedBenefitCompName.split("~")[1];
        else
        	return actualBenftCompRuleSYSId;
        
        String actualBenefitCompName="";
        boolean resultFlag = false;
        BenefitComponentRuleIDBO benefitCompRuleID;
        //get expected benefit component Rule id
        synchronized(benftCompRuleIdList) {
	        for(Iterator i = benftCompRuleIdList.iterator(); i.hasNext();){
	        	benefitCompRuleID = (BenefitComponentRuleIDBO)i.next();
	        	if(benefitCompRuleID.getBeneftCompName().equals(expectedBenefitCompName))
	        		expectedBenftCompRuleId = benefitCompRuleID.getBeneftCompRuleId();
	        }
        }
        //compare returned rule ids with expected rule id
        if( (actualeBenftCompRuleIdList.size() == 1) && (actualeBenftCompRuleIdList.contains(expectedBenftCompRuleId)) ){
    		resultFlag = true;
        }
        //get Actual benefit component name
        if(resultFlag){
        	synchronized(benftCompRuleIdList) {
		        for(Iterator i = benftCompRuleIdList.iterator(); i.hasNext();){
		        	benefitCompRuleID = (BenefitComponentRuleIDBO)i.next();
		        	if(benefitCompRuleID.getBeneftCompRuleId().equals(expectedBenftCompRuleId)){
		        		actualBenftCompRuleSYSId = benefitCompRuleID.getBeneftCompId();
		        		actualBenefitCompName = benefitCompRuleID.getBeneftCompName();
		        		break;
		        	}
		        }
        	}
        }else{
        	synchronized(benftCompRuleIdList) {
	        	for(Iterator i = benftCompRuleIdList.iterator(); i.hasNext();){
	        		benefitCompRuleID = (BenefitComponentRuleIDBO)i.next();
	        		if(actualeBenftCompRuleIdList.contains(benefitCompRuleID.getBeneftCompRuleId()) ){
	        			if( actualBenefitCompName.equals("") )
	        				actualBenefitCompName = benefitCompRuleID.getBeneftCompName();
	        			else
	        				actualBenefitCompName = actualBenefitCompName + ", " + benefitCompRuleID.getBeneftCompName();
	        		}
	        	}
	        	
        	}
        }
        claimLineBO.setActualBenefitComponent(actualBenefitCompName);
        claimLineBO.setStatus(resultFlag);
       return actualBenftCompRuleSYSId;
	}
	private void populateBenefitResult(ClaimLineBO claimLineBO, List actualeBenftRuleIdList, List benftRuleIdList){
        String expectedBenftRuleId="";
        
        boolean resultFlag = false;
        BenefitRuleIDBO benefitRuleID;
        for(Iterator i = benftRuleIdList.iterator(); i.hasNext();){
        	benefitRuleID = (BenefitRuleIDBO)i.next();
            //compare returned rule ids with expected rule id
            if(actualeBenftRuleIdList.contains(benefitRuleID.getBenefitRuleId())){
            	claimLineBO.setAccidentRider(true);
            	break;
            }
        }
	}
	private void populateBenefitResult(ClaimLineBO claimLineBO, List actualeBenftRuleIdList, List benftRuleIdList, String type){
		
        String expectedBenftRuleId="";
        String expectedBenefitName="";
        String actiualBenefitName="";
        
        if(type.equals("BASIC")){
        	expectedBenefitName = claimLineBO.getExpectedBasicBenefit();
        }else if(type.equals("RIDER")){
        	expectedBenefitName = claimLineBO.getExpectedRiderBenefit();
        }else if(type.equals("MAJMEDICAL")){
        	expectedBenefitName = claimLineBO.getExpectedBenefit();
        }
        if((expectedBenefitName != null) && (expectedBenefitName.length()>0))
        	expectedBenefitName = expectedBenefitName.split("~")[1];
        else
        	return;
        
        boolean resultFlag = false;
        BenefitRuleIDBO benefitRuleID;
        //get expected benefit Rule id
        for(Iterator i = benftRuleIdList.iterator(); i.hasNext();){
        	benefitRuleID = (BenefitRuleIDBO)i.next();
        	if(benefitRuleID.getBenefitName().equals(expectedBenefitName)){
        		expectedBenftRuleId = benefitRuleID.getBenefitRuleId();
        		break;
        	}
        }
        //compare returned rule ids with expected rule id
        if(actualeBenftRuleIdList.contains(expectedBenftRuleId)){
    		resultFlag = true;
        }
        
        //get Actual benefit component name
        if(resultFlag){
	        for(Iterator i = benftRuleIdList.iterator(); i.hasNext();){
	        	benefitRuleID = (BenefitRuleIDBO)i.next();
	        	if(benefitRuleID.getBenefitRuleId().equals(expectedBenftRuleId)){
	        		actiualBenefitName = benefitRuleID.getBenefitName();
	        		break;
	        	}
	        }
        }else{
        	for(Iterator i = benftRuleIdList.iterator(); i.hasNext();){
        		benefitRuleID = (BenefitRuleIDBO)i.next();
        		if(actualeBenftRuleIdList.contains(benefitRuleID.getBenefitRuleId()) ){
        			actiualBenefitName = actiualBenefitName + benefitRuleID.getBenefitName() + ", ";
        		}
        	}
        }
        
        if(type.equals("BASIC")){
            claimLineBO.setActualBasicBenefit(actiualBenefitName);
        }else if(type.equals("RIDER")){
            claimLineBO.setActualRiderBenefit(actiualBenefitName);
        }else if(type.equals("MAJMEDICAL")){
            claimLineBO.setActualBenefit(actiualBenefitName);
        }
        claimLineBO.setStatus(resultFlag);
	}
	// get results for service response
	private List getBenftCompRuleIds(String resultXML){
	    String[] benftCompRuleIdArray = new String[0];
        try {
            WCSRLOPDDocument docu = WCSRLOPDDocument.Factory.parse(resultXML);
            WCSRLOPDDocument.WCSRLOPD t1 =  docu.getWCSRLOPD();
    	    WCSRLOPDOUTPUTAREADocument.WCSRLOPDOUTPUTAREA outPut = t1.getWCSRLOPDOUTPUTAREA();
    	    if(outPut != null)
    	    	benftCompRuleIdArray = outPut.getWCSOPCOMPBNFTIDArray();
        } catch (XmlException e) {
            Logger.logError(e);
        }
        List ruleIdList= new ArrayList();
        if(benftCompRuleIdArray != null)
        	ruleIdList = Arrays.asList(benftCompRuleIdArray);
	    return ruleIdList;
	}
	//	 Populate the benefit ids
	private String getXMLForBeneft(ClaimLineBO claimLineBO, String xmlStrInput, List benftRuleIdList, int claimLineIndex, int claimLineSize)
	throws XmlException{
	        //get Root Element
        WCSRULMDDocument rootDoc = WCSRULMDDocument.Factory.parse(xmlStrInput);
        WCSRULMDDocument.WCSRULMD rootElement =  rootDoc.getWCSRULMD();
        
        //Add input param list
        WCSINPUTPARMLISTDocument.WCSINPUTPARMLIST inputParamList = rootElement.addNewWCSINPUTPARMLIST();
        //Add benefit rule id
        for(Iterator i = benftRuleIdList.iterator(); i.hasNext();){
			inputParamList.addWCSIPCOMPBNFTID( ((BenefitRuleIDBO)i.next()).getBenefitRuleId());
		}      
        getXML(claimLineBO, rootElement, claimLineIndex, claimLineSize);
        return rootDoc.xmlText();
	}
	// Populate the benefit component ids
    private String getXMLForBeneftComp(ClaimLineBO claimLineBO, String xmlStrInput, List benftCompRuleIdList, int claimLineIndex, int claimLineSize) throws XmlException{
        //get Root Element
        WCSRULMDDocument rootDoc = WCSRULMDDocument.Factory.parse(xmlStrInput);
        WCSRULMDDocument.WCSRULMD rootElement =  rootDoc.getWCSRULMD();        
        //Add input param list
        WCSINPUTPARMLISTDocument.WCSINPUTPARMLIST inputParamList = rootElement.addNewWCSINPUTPARMLIST();
        //Add benefit component rule id
        synchronized(benftCompRuleIdList) {
	        for(Iterator i = benftCompRuleIdList.iterator(); i.hasNext();){
				inputParamList.addWCSIPCOMPBNFTID( ((BenefitComponentRuleIDBO)i.next()).getBeneftCompRuleId());
			}
        }
        getXML(claimLineBO, rootElement, claimLineIndex, claimLineSize);
        return rootDoc.xmlText();
    }
    
    // Populate the claim line index claim line size
    private void getXML(ClaimLineBO claimLineBO, WCSRULMDDocument.WCSRULMD rootElement, int claimLineIndex, int claimLineSize){       
        //Add Claim Deatials
        WCSCLAIMAREAINPUTDocument.WCSCLAIMAREAINPUT claimDetails = rootElement.getWCSCLAIMAREAINPUT();
        //set indexes
        WCSINDEXESDocument.WCSINDEXES indexes =  claimDetails.addNewWCSINDEXES();
        indexes.setWCSDTLNDX(new BigInteger(""+claimLineIndex));
        indexes.setWCSHMLNDX(new BigInteger(""+claimLineIndex));
        // Number of claim lines
        claimDetails.setWCSCDDTLCNTR(new BigInteger(""+claimLineSize));
        //set Type of Bill
        claimDetails.setWCSHOSPTYPEOFBILL(claimLineBO.getTypeOfBillCode());
    }
    
    //  Populate the ClaimHeader and ClaimLine array
    private String populateHeaderAndLine(ClaimHeaderBO claimHeaderBO, List claimLineList){
    	
        //get Root Element
        WCSRULMDDocument rootDoc = WCSRULMDDocument.Factory.newInstance();
        WCSRULMDDocument.WCSRULMD rootElement =  rootDoc.addNewWCSRULMD();
        //Add Claim Deatials
        WCSCLAIMAREAINPUTDocument.WCSCLAIMAREAINPUT claimDetails= rootElement.addNewWCSCLAIMAREAINPUT();
        //set Claim Type
        claimDetails.setWCSCDCLMTYPE(claimHeaderBO.getClaimType());
        //set Provider Id
        claimDetails.setWCSCDPRVDRID(claimHeaderBO.getProviderId());
        //set Med Assign Indicator
        claimDetails.setWCSMEDASSIGNIND(claimHeaderBO.getMedAssignIndicator());
        //set Age
        if((claimHeaderBO.getAge() != null) && ( claimHeaderBO.getAge().length()>0))
        	claimDetails.setWCSCDPATAGE(getBigInteger(claimHeaderBO.getAge()));
        //set ITS/Prov Spec
        if((claimHeaderBO.getItsClaim() != null) && ("N".equalsIgnoreCase(claimHeaderBO.getItsClaim())) )
        	claimDetails.setWCSCDPRVDRSPCLTYCDE(claimHeaderBO.getItsProvSpec());
        else if( (claimHeaderBO.getItsClaim() != null) && ("Y".equalsIgnoreCase(claimHeaderBO.getItsClaim())) )
        	claimDetails.setWCSCDITSPROVSPECIALTYCDE(claimHeaderBO.getItsProvSpec());
        //set Hospital Facility Code
        claimDetails.setWCSHOSPFCLTYTYPECDE(claimHeaderBO.getHospitalFacilityCode());
        //set Gender
        claimDetails.setWCSCDPATSEX(claimHeaderBO.getGender());
        //set Group State
        claimDetails.setWCSCDGRPSTATE(claimHeaderBO.getGroupState());
        //set Days From Injury
        if((claimHeaderBO.getDaysFromInjury() != null) && ( claimHeaderBO.getDaysFromInjury().length()>0))
        	claimDetails.setWCSDAYSFROMINJRY(getBigInteger(claimHeaderBO.getDaysFromInjury()));
        else
        	claimDetails.setWCSDAYSFROMINJRY(getBigInteger("9999"));
        //set Member Relationship Code
        claimDetails.setWCSMBRRLTNSHIPCD(claimHeaderBO.getMemberRelationshipCode());
        //set ITS
        claimDetails.setWCSITSCLAIMIND(claimHeaderBO.getItsProvSpec());
        //set Edit
        claimDetails.setWCSCDERRCNTR(getBigInteger("1")); //number of edit values (possible values for WTT is 0 or 1)
        WCSCDERRCDESDocument.WCSCDERRCDES errorCodes = claimDetails.addNewWCSCDERRCDES();
        WCSCDERRCDETBLDocument.WCSCDERRCDETBL errorTBL = errorCodes.addNewWCSCDERRCDETBL();
        errorTBL.setWCSCDERRCDE(claimHeaderBO.getEdit());
        
        WCSDTLTABLEDocument.WCSDTLTABLE dtlTable;
        WCSDTLRVNCDEFILLERDocument.WCSDTLRVNCDEFILLER revenueCodeFiller;
        
        //set Diagnosis Code
        Set diagnosisSet = new HashSet();
        for(Iterator i = claimLineList.iterator(); i.hasNext();){
            ClaimLineBO claimLineBO = (ClaimLineBO) i.next();
            if(claimLineBO.getDiagnosisCode() != null && claimLineBO.getDiagnosisCode().length()>0)
            	diagnosisSet.add(claimLineBO.getDiagnosisCode());
        }
        diagnosisList = new ArrayList(diagnosisSet);
        for(Iterator i = diagnosisList.iterator(); i.hasNext(); ){
	        claimDetails.addNewWCSCDICDACD().setWCSCDICDACDE((String)i.next());
        }
        int diagnosisPNTR;
        for(Iterator i = claimLineList.iterator(); i.hasNext();){
            ClaimLineBO claimLineBO = (ClaimLineBO) i.next();
	        dtlTable = claimDetails.addNewWCSDTLTABLE();
	        //set HCPC Code
	        dtlTable.setWCSDTLPRCDRCDE(claimLineBO.getHcpcCode());
	        //set Modifier Code
	        dtlTable.setWCSDTLPRCDRMODFRCDE(claimLineBO.getModifierCode());
	        //set  TT Code
	        dtlTable.setWCSDTLTTCDE(claimLineBO.getTtCode());
	        //set Place of Service
	        dtlTable.setWCSDTLPTCDE(claimLineBO.getPlaceOfService());
	        
	        dtlTable.setWCSDTLSVCFROMDTE(new BigInteger( new Long(new Date().getTime()).toString() ));
	        dtlTable.setWCSDTLSVCTHRUDTE(new BigInteger( new Long(new Date().getTime()).toString() ));
	                
	        //set Revenue Code
	        revenueCodeFiller = dtlTable.addNewWCSDTLRVNCDEFILLER();
	        revenueCodeFiller.setWCSDTLRVNCDE(claimLineBO.getRevenueCode());
	        diagnosisPNTR = diagnosisList.indexOf(claimLineBO.getDiagnosisCode()) + 1;
	        if(diagnosisPNTR>0)
	        	dtlTable.addNewWCSDTLICDACDEPNTR().setWCSDTLICDAPNTR( getBigInteger(""+diagnosisPNTR) );
        }
        return rootDoc.xmlText();
    }
    private String getString(String str){
    	String returnStr;
    	if(str == null)
    		returnStr = "";
    	else
    		returnStr = str;
    	return returnStr;    		
    }
    private BigInteger getBigInteger(String str){
    	BigInteger temp;
    	if(str == null){
    		temp = new BigInteger("0");
    	}else{
    		try{
	    		temp = BigInteger.valueOf(Long.parseLong(str));
    		}catch(NumberFormatException e){
    			e.printStackTrace();
    			setException(e);
    			setStatus(false);
    			temp = new BigInteger("0");
    		}
    	}
    	return temp;
    }
	/**
	 * @return Returns the status.
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public synchronized void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return Returns the exception.
	 */
	public Exception getException() {
		return exception;
	}
	/**
	 * @param exception The exception to set.
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}
}
