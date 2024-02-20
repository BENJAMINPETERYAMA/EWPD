package com.wellpoint.ewpd.rest.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.everit.json.schema.ValidationException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.wellpoint.contractcodepool.exception.InvalidInputParameterException;
import com.wellpoint.ewpd.rest.Requests.BenefitAPIRequest;
import com.wellpoint.ewpd.rest.Requests.ReserveRequestForSales;
import com.wellpoint.ewpd.rest.Responses.BenefitAPIResponse;
import com.wellpoint.ewpd.rest.Responses.HpccAssociations;
import com.wellpoint.ewpd.rest.Responses.ReserveForSalesResponse;
import com.wellpoint.ewpd.rest.dao.ReserveForSalesDao;
import com.wellpoint.ewpd.rest.model.Status;
import com.wellpoint.ewpd.rest.util.ServiceUtil;
import com.wellpoint.messageengine.KafkaProducer;

@Service
public class ReserveForSalesService  {

	private static final Logger logger = Logger.getLogger(ReserveForSalesService.class);

	@Autowired
	private PropertyGetter propertyGetter;
	
	@Autowired
	private ReserveForSalesDao reserveForSalesDao;
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	private boolean invHPCCFlag= false;
	
	private boolean noHPCCAss= false;
	
	private boolean retrvHPCCFail = false;
	
	private boolean hpccAssExists = false;
	
	public ReserveForSalesResponse processContractReserveService(
			ReserveRequestForSales resRequest) throws InvalidInputParameterException {
		
		logger.info("validating request for contract as manual : ReserveForSalesService.validateRequestForContractAsManual");

		ReserveForSalesResponse response = new ReserveForSalesResponse(); 
		Status status = new Status();
		String VA_ASO_IND ="N";
		
		
		if(resRequest.getStateCode().trim().equalsIgnoreCase(propertyGetter.getValue(ServiceUtil.REQ_STATE_CD_CA.trim()))){
			  logger.info("stateCode value is passed CA in the request");
		      response.setContractCode(null);
		      status.setCode(Integer.parseInt(propertyGetter.getValue(ServiceUtil.REQ_STATE_CD_CA_INV_STTS_CD)));
		      status.setMessage(propertyGetter.getValue(ServiceUtil.ERR_MSG_STATE_CD_CA));
		      response.setStatus(status);
		}else if(checkHPCCRetrieveWithRequest(resRequest)){
			  if(invHPCCFlag){
				  logger.info("invalid hpcc passed in the request");
			      response.setContractCode(null);
			      status.setCode(Integer.parseInt(propertyGetter.getValue(ServiceUtil.MAN_MSG_INV_HPCC_STTS_CD)));
			      status.setMessage(propertyGetter.getValue(ServiceUtil.MAN_MSG_INV_HPCC));
			      response.setStatus(status);
				  }
			  else{
				  logger.info("request hpcc failed");
				  response.setContractCode(null);
				  status.setCode(Integer.parseInt(propertyGetter.getValue(ServiceUtil.RETRV_HPCC_FAIL_STTS_CD)));
				  status.setMessage(propertyGetter.getValue(ServiceUtil.RETRV_HPCC_FAIL_STTS_MSG));
				  response.setStatus(status);
			  }
			  
		}else if(!resRequest.getBaseContractType().trim().equalsIgnoreCase(propertyGetter.getValue(ServiceUtil.REQ_BASE_CNTRCT_TYP_STND).trim())){
			  logger.info("basecontract type value is passed other than standard in the request");
		      response.setContractCode(null);
		      status.setCode(Integer.parseInt(propertyGetter.getValue(ServiceUtil.BASE_CNTRCT_TYPE_INV_STTS_CD)));
		      status.setMessage(propertyGetter.getValue(ServiceUtil.ERR_MSG_BASE_CNTRCT_TYPE));
		      response.setStatus(status);
		}else if((resRequest.getStateCode().trim().equalsIgnoreCase(propertyGetter.getValue(ServiceUtil.STATE_CD_VA).trim()) && 
			  resRequest.getFundingTypeCode().trim().equalsIgnoreCase(propertyGetter.getValue(ServiceUtil.FUND_TYP_CD_ASO).trim()))){
			  logger.info("state code : VA& fund type code : ASO");
			  VA_ASO_IND ="Y";
			  response = reserveForSalesDao.fetchReserveContractForSalestData(resRequest,VA_ASO_IND);
			  if(response.getContractCode() != null){
				  try{	
						createBenefitAPI(resRequest,response);
					}catch(Exception ex){
						logger.error("handling exception in calling create benefit api"+ex);
					}
				  response.setContractCode(response.getContractCode());
				  status.setCode(Integer.parseInt(propertyGetter.getValue(ServiceUtil.MAN_MSG_VA_ASO_STTS_CD)));
				  status.setMessage(propertyGetter.getValue(ServiceUtil.MAN_MSG_VA_ASO));
				  response.setStatus(status);
			  }
		}else if(resRequest.getCepIndicator().trim().equalsIgnoreCase(propertyGetter.getValue(ServiceUtil.CEP_IND_Y).trim())){
			  logger.info("cep indicator: Y");
			  response = reserveForSalesDao.fetchReserveContractForSalestData(resRequest,VA_ASO_IND);
			  if(response.getContractCode() != null){
				  try{	
						createBenefitAPI(resRequest,response);
					}catch(Exception ex){
						logger.error("handling exception in calling create benefit api"+ex);
					}
			      response.setContractCode(response.getContractCode());
				  status.setCode(Integer.parseInt(propertyGetter.getValue(ServiceUtil.MAN_MSG_CEP_IND_STTS_CD)));
				  status.setMessage(propertyGetter.getValue(ServiceUtil.MAN_MSG_CEP_IND));
				  response.setStatus(status);
			  }
		}else if(resRequest.getPlanModifiedInd().trim().equalsIgnoreCase(propertyGetter.getValue(ServiceUtil.PLAN_MOD_IND_N).trim()) && hpccAssExists){
			  logger.info("planmodifyind value : N & request contract already associated with the request hpcc");
			  response.setContractCode(resRequest.getBaseContract());
		      status.setCode(HttpStatus.CREATED.value());
		      status.setMessage(propertyGetter.getValue(ServiceUtil.RET_BASE_CNTCT_MSG));
		      response.setStatus(status);
		}
		else{
			  logger.info("calling reserveForSalesDao.fetchReserveContractForSalestData to execute stored proc call");
			  response = reserveForSalesDao.fetchReserveContractForSalestData(resRequest,VA_ASO_IND);
			if (response.getContractCode() != null
					&& !response.getContractCode().contains(
							propertyGetter
									.getValue(ServiceUtil.INVALID_CNTRCT_ERR))
					&& !response.getContractCode().contains(
							propertyGetter
									.getValue(ServiceUtil.CNTRCT_CD_MANUAL))) {
				try{
					createBenefitAPI(resRequest,response);
				}catch(Exception ex){
					logger.error("handling exception in calling create benefit api"+ex);
					
				}
			}
		}

		return response;
	}
	
	public boolean validateRequest(ReserveRequestForSales resRequest) throws InvalidInputParameterException, ParseException {
			
			logger.info("validating the request fields for reserve request for sales : ReserveForSalesService.validateRequest");

			StringBuilder invalidParms = new StringBuilder();
			StringBuilder exceptionMsg = new StringBuilder(propertyGetter.getValue(ServiceUtil.INV_IP_MSG));
			boolean flag = true;			
			
				if (resRequest.getBaseContract() == null
						|| resRequest.getBaseContract().isEmpty()) {
					invalidParms.append(propertyGetter.getValue(ServiceUtil.INVALID_CNTRCTCODE)+",");
				}else{
					resRequest.setBaseContract(resRequest.getBaseContract().toUpperCase());
				}
				if (resRequest.getBaseContractType() == null
						|| resRequest.getBaseContractType().isEmpty()) {
					invalidParms.append(propertyGetter.getValue(ServiceUtil.INVALID_CNTRCTCODE_TYPE)+",");
				}else{
					resRequest.setBaseContractType(converttoTitleCase(resRequest.getBaseContractType()));
				}
				if (resRequest.getPlanModifiedInd() == null
						|| resRequest.getPlanModifiedInd().isEmpty()) {
					invalidParms.append(propertyGetter.getValue(ServiceUtil.INVALID_PLAN_MODIFY_IND)+",");
				}else{
					resRequest.setPlanModifiedInd(resRequest.getPlanModifiedInd().toUpperCase());
				}
				if (resRequest.getHpcc() == null
						|| resRequest.getHpcc().isEmpty()) {
					invalidParms.append(propertyGetter.getValue(ServiceUtil.INVALID_HPCC)+",");
				}else{
					resRequest.setHpcc(resRequest.getHpcc().toUpperCase());
				}
				if (resRequest.getQuoteLineItem() == null
						|| resRequest.getQuoteLineItem().isEmpty()) {
					invalidParms.append(propertyGetter.getValue(ServiceUtil.INVALID_QUOTE_LIN_ITEM)+",");
				}
				if (resRequest.getFundingTypeCode() == null
						|| resRequest.getFundingTypeCode().isEmpty()) {
					invalidParms.append(propertyGetter.getValue(ServiceUtil.INVALID_FUNDING_TYPE)+",");
				}else{
					resRequest.setFundingTypeCode(resRequest.getFundingTypeCode().toUpperCase());
				}
				if (resRequest.getStateCode() == null
						|| resRequest.getStateCode().isEmpty()) {
					invalidParms.append(propertyGetter.getValue(ServiceUtil.INVALID_STATE)+",");
				}else{
					resRequest.setStateCode(resRequest.getStateCode().toUpperCase());
				}
				if (resRequest.getCepIndicator() == null
						|| resRequest.getCepIndicator().isEmpty()) {
					invalidParms.append(propertyGetter.getValue(ServiceUtil.INVALID_CEP_INDICATOR)+",");
				}else{
					resRequest.setCepIndicator(resRequest.getCepIndicator().toUpperCase());
				}
				if (resRequest.getTransactionId() == null
						|| resRequest.getTransactionId().isEmpty()) {
					invalidParms.append(propertyGetter.getValue(ServiceUtil.INVALID_SALESTXID)+",");
				}
				if (resRequest.getTransactionTimestamp() == null
						|| resRequest.getTransactionTimestamp().isEmpty() || validateReqTimestampValue(resRequest.getTransactionTimestamp())) {
					//resRequest.setTransactionTimestamp(null);
					invalidParms.append(propertyGetter.getValue(ServiceUtil.INVALID_TX_TIMSTMP)+",");	
				}
				if (resRequest.getEffectiveDate() == null
						|| resRequest.getEffectiveDate().isEmpty()) {
					invalidParms.append(propertyGetter.getValue(ServiceUtil.INVALID_EFFECTDATE)+",");
				}
				
				if (invalidParms.length()>0) {
					flag = false;
					int lenght = invalidParms.length()-1;
					invalidParms.deleteCharAt(lenght);
					exceptionMsg.append(invalidParms);
					throw new InvalidInputParameterException(exceptionMsg.toString());
				}else{
					
					SimpleDateFormat sdfTxn =new  SimpleDateFormat(propertyGetter.getValue(ServiceUtil.TXN_TIMSTMP_FORMAT));
					
					String currentDatetxn = sdfTxn.format(new Date());
					Date currentDatetxnstmp = null;
					try {
						currentDatetxnstmp = sdfTxn.parse(currentDatetxn);
					} catch (ParseException ex) {
						logger.error("converting  current date to transaction date"+ex);
					}
					
					Date txnDate = checkTxnTimestamp(resRequest.getTransactionTimestamp(), sdfTxn);

					if (txnDate.compareTo(currentDatetxnstmp) > 0) {
						invalidParms.append(propertyGetter.getValue(ServiceUtil.INVALID_TX_TIMSTMP) + ",");
					}
					if(invalidParms.length()>0){
						flag = false;
						int lenght = invalidParms.length()-1;
						invalidParms.deleteCharAt(lenght);
						exceptionMsg.append(invalidParms+propertyGetter.getValue(ServiceUtil.LESS_EQUAL_CURR_DT_MSG));
						throw new InvalidInputParameterException(exceptionMsg.toString());
					}
				}
							
		return flag;

	}
	
	
		public String quoteLineCheckForRequestContract(ReserveRequestForSales resRequest) throws InvalidInputParameterException{
			String cntrct = reserveForSalesDao.getQuoteLineResponse(resRequest);
			return cntrct;
		}
		
		
		private Date checkTxnTimestamp(String txnTimestamp,SimpleDateFormat sdf) throws InvalidInputParameterException {
			
			try {
				return sdf.parse(txnTimestamp);
			} catch (ParseException e) {
				throw new InvalidInputParameterException(propertyGetter.getValue(ServiceUtil.INVALID_TXNTMSTMP_FORMAT));
			}
			
		}
		
		public String currentDate() throws InvalidInputParameterException {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			String currentDateString = sdf.format(new Date());
			return currentDateString;
		}
		public boolean checkHPCCRetrieveWithRequest(ReserveRequestForSales req) throws InvalidInputParameterException{
			BenefitAPIResponse benResponse = new BenefitAPIResponse();
          	hpccAssExists = false;
			try{
				logger.info("calling retrieveBenefitAPI method to call retrieve benefit API : ReserveForSalesService.checkHPCCRetrieveWithRequest");
				benResponse = retrieveBenefitAPI(req);
			}catch(NullPointerException ex){
				logger.error("getting null response from retrieveBenefitAPI method "+ex);
				if(invHPCCFlag || retrvHPCCFail){
					return true;
				}
			}
			/*if(invHPCCFlag || retrvHPCCFail || benResponse.getStatus().getCode().equals(propertyGetter.getValue(ServiceUtil.RETRV_HPCC_FAIL_STTS_CD))){
				return true;
			}*/
			logger.info("Recieved response from retrieve benefit api");
			if(!noHPCCAss){
			if (benResponse.getHpccAssociations() != null
					&& !benResponse.getHpccAssociations().isEmpty()) {
				for (HpccAssociations hpccAss : benResponse.getHpccAssociations()) {
					if (hpccAss.getHpcc().trim().equalsIgnoreCase(req.getHpcc().trim())) {
						hpccAssExists = true;
						//return true;
					}
	
				}
	
			}
			}
			return false;
		}
		
		public BenefitAPIResponse retrieveBenefitAPI(ReserveRequestForSales req) throws InvalidInputParameterException {
			
			
			String url = propertyGetter.getValue(ServiceUtil.BEN_API_URL);
			String[] excMsg;
			String[] resMsg = null;
			invHPCCFlag= false;
			noHPCCAss = false;
			retrvHPCCFail = false;
			HttpHeaders header = new HttpHeaders();
			
			logger.info("setting request fields to call retrieve benefit api");
			header.setContentType(MediaType.APPLICATION_JSON);
			header.set(propertyGetter.getValue(ServiceUtil.AUTHORIZATION), propertyGetter.getValue(ServiceUtil.AUTHORIZATION_VALUE));
			
			BenefitAPIRequest benefitReq = new BenefitAPIRequest();
			benefitReq.setAction(propertyGetter.getValue(ServiceUtil.BEN_API_RETRIEVE));
			benefitReq.setMedicalContract(req.getBaseContract());
			benefitReq.setHpcc(req.getHpcc());
			benefitReq.setAssnEffectiveDate(req.getEffectiveDate());
			benefitReq.setTxnId(req.getTransactionId());
			benefitReq.setTxnTimestamp(req.getTransactionTimestamp());
			HttpEntity<BenefitAPIRequest> benfitAPIRequest = new HttpEntity<BenefitAPIRequest>(benefitReq, header);
			
			RestTemplate rest = new RestTemplate();			
			
			ResponseEntity<BenefitAPIResponse> benResponse = null;
			try{
				logger.info("calling retrieve benefit api : ReserveForSalesService.retrieveBenefitAPI");

				benResponse=rest.exchange(url, HttpMethod.POST, benfitAPIRequest, BenefitAPIResponse.class);
			}catch(HttpClientErrorException ex){
				logger.error("calling  benefit api"+ex);
				excMsg = ex.getResponseBodyAsString().split(",");
				for(String msg : excMsg){
					if (msg.contains("message")){
						resMsg = msg.replace("}","").split(":");
					}
				}
				if(excMsg[0] !=null && excMsg[0].contains(propertyGetter.getValue(ServiceUtil.RETRV_HPCC_FAIL_VAL))){
					retrvHPCCFail = true;
				}else if(resMsg[1] != null && resMsg[1].contains(propertyGetter.getValue(ServiceUtil.RETRV_HPCC_FAIL_STTS_MSG))){
					retrvHPCCFail = true;
				}else if(resMsg[1] != null && resMsg[1].contains(propertyGetter.getValue(ServiceUtil.INVALID_HPCC_MSG))){
					invHPCCFlag = true;
					logger.info("Invalid hpcc passed in the request");
				}else if(resMsg[1] != null && resMsg[1].contains(propertyGetter.getValue(ServiceUtil.NO_HPCC_ASSN))){
					noHPCCAss = true;
					logger.info("No hpcc associations for the request contract");
				}else{
					retrvHPCCFail = true;
				}
			}catch(HttpServerErrorException ex){
				retrvHPCCFail = true;
			}
					
			return benResponse.getBody();
			
		}
		
		public BenefitAPIResponse createBenefitAPI(ReserveRequestForSales req, ReserveForSalesResponse resp) throws InvalidInputParameterException {
			
			String url = propertyGetter.getValue(ServiceUtil.BEN_API_URL);

			HttpHeaders header = new HttpHeaders();
			logger.info("setting request parameters to call create benefit api : ReserveForSalesService.createBenefitAPI");
			header.setContentType(MediaType.APPLICATION_JSON);
			header.set(propertyGetter.getValue(ServiceUtil.AUTHORIZATION), propertyGetter.getValue(ServiceUtil.AUTHORIZATION_VALUE));
			
			BenefitAPIRequest benefitReq = new BenefitAPIRequest();
			benefitReq.setAction(propertyGetter.getValue(ServiceUtil.BEN_API_CREATE));
			benefitReq.setMedicalContract(resp.getContractCode());
			benefitReq.setHpcc(req.getHpcc());
			benefitReq.setAssnEffectiveDate(req.getEffectiveDate());
			benefitReq.setTxnId(req.getTransactionId());
			benefitReq.setTxnTimestamp(req.getTransactionTimestamp());
			HttpEntity<BenefitAPIRequest> benfitAPIRequest = new HttpEntity<BenefitAPIRequest>(benefitReq, header);
			
			RestTemplate rest = new RestTemplate();
			
			ResponseEntity<BenefitAPIResponse> benResponse = null;
			
			try{
				logger.info("calling create benefit api using url");
				benResponse=rest.exchange(url, HttpMethod.POST, benfitAPIRequest, BenefitAPIResponse.class);
			}catch(HttpClientErrorException ex){
				logger.error("bad request issue : ReserveForSalesService.createBenefitAPI"+ex);
			}
				
			return benResponse.getBody();
			
		}
		
		public void insertReqRes(ReserveRequestForSales resReq, ReserveForSalesResponse resRes,String requestTimeStamp) throws Exception{
			
			logger.info("inserting data into the audit table : ReserveForSalesService.insertReqRes");
			
			long insertedSeqId = reserveForSalesDao.insertRequest(resReq,requestTimeStamp);
			
			if(insertedSeqId > 0){
				reserveForSalesDao.updateTheResponse(resRes, insertedSeqId);
			}
			
			
		}
		
		public long processRequest(ReserveRequestForSales resRequest,ReserveForSalesResponse response){
            
			logger.info("inserting data into the spider table : ReserveForSalesService.processRequest");
			
			long insert = 0;
			
            
            if(resRequest.getTransactionId()!=null){
            	   JsonObject contractReserveBDFPayLoadObj = new JsonObject();
                   JsonObject contractReserveBDFPayLoad = new JsonObject();
                   JsonObject key = new JsonObject();
                   JsonObject reserveContractRequest = new JsonObject();
                   JsonObject reserveContractResponse = new JsonObject();
                   JsonObject status = new JsonObject();
                   status.addProperty(ServiceUtil.CODE ,String.valueOf(response.getStatus().getCode()));
                   status.addProperty(ServiceUtil.MESSAGE, response.getStatus().getMessage());
                   reserveContractResponse.addProperty(ServiceUtil.CALLING_SYSTEM, response.getCallingSystem());
                   reserveContractResponse.addProperty(ServiceUtil.TRANSACTION_ID, response.getTransactionId());
                   reserveContractResponse.addProperty(ServiceUtil.TRANSACTION_TIMESTAMP, response.getTransactionTimestamp());
                   reserveContractResponse.add(ServiceUtil.STATUS, status);
                   reserveContractResponse.addProperty(ServiceUtil.CONTRACT_CODE, response.getContractCode());
                   
                   reserveContractRequest.addProperty(ServiceUtil.BASE_CONTRACT, resRequest.getBaseContract());
                   reserveContractRequest.addProperty(ServiceUtil.PLAN_MODIFIED, resRequest.getPlanModifiedInd());
                   reserveContractRequest.addProperty(ServiceUtil.CEP_IND, resRequest.getCepIndicator());
                   reserveContractRequest.addProperty(ServiceUtil.HPCC, resRequest.getHpcc());
                   reserveContractRequest.addProperty(ServiceUtil.FUNDING_CODE, resRequest.getFundingTypeCode());
                   reserveContractRequest.addProperty(ServiceUtil.STATE_CODE, resRequest.getStateCode());
                   reserveContractRequest.addProperty(ServiceUtil.EFF_DATE, resRequest.getEffectiveDate());
                   reserveContractRequest.addProperty(ServiceUtil.TRANS_ID, resRequest.getTransactionId());
                   reserveContractRequest.addProperty(ServiceUtil.TRANS_TIMESTAMP, resRequest.getTransactionTimestamp());
                  
                   if(resRequest.getActwiseIndicator() != null){
                	   reserveContractRequest.addProperty(ServiceUtil.ACTWISE, resRequest.getActwiseIndicator());
                   }
                   //commented engage indicators for BDF payload
                   /*if(resRequest.getEngageIndicators() != null){
                	   reserveContractRequest.addProperty(ServiceUtil.ENGAGE_IND, resRequest.getEngageIndicators());
                   }*/
                   if(resRequest.getQuoteLineItem() != null){
                	   reserveContractRequest.addProperty(ServiceUtil.QUOTE_ITEM, resRequest.getQuoteLineItem());
                   }
                   if(resRequest.getGroupSize() != null){
                	   reserveContractRequest.addProperty(ServiceUtil.GROUP_SIZE, resRequest.getGroupSize());
                   }
                   if(resRequest.getBaseContractType() != null){
                	   reserveContractRequest.addProperty(ServiceUtil.BASECONTRACTYPE, resRequest.getBaseContractType());
                   }
                   if(resRequest.getPerformanceGuarantee() != null){
                	   reserveContractRequest.addProperty(ServiceUtil.PERFORMACE_GUART, resRequest.getPerformanceGuarantee());
                   }
                   if(resRequest.getWgsCaseId() != null){
                	   reserveContractRequest.addProperty(ServiceUtil.CASE_ID, resRequest.getWgsCaseId());
                   }
                   if(resRequest.getWgsGroupId() != null){
                	   reserveContractRequest.addProperty(ServiceUtil.GROUP_ID, resRequest.getWgsGroupId());
                   }
                   if(resRequest.getPoolingStatus() != null){
                	   reserveContractRequest.addProperty(ServiceUtil.POOL_STATUS, resRequest.getPoolingStatus());
                   }
                   if(resRequest.getCallingSystem() != null){
                	   reserveContractRequest.addProperty(ServiceUtil.CALL_SYSTEM, resRequest.getCallingSystem());
                   }
                   
                   key.addProperty(ServiceUtil.REC_ID, getRecordId().trim());
                   key.addProperty(ServiceUtil.REC_TIMESTAMP, resRequest.getTransactionTimestamp());
                   contractReserveBDFPayLoad.add(ServiceUtil.KEY, key);
                   contractReserveBDFPayLoad.add(ServiceUtil.RESV_CONT_REQ,reserveContractRequest);
                   contractReserveBDFPayLoad.add(ServiceUtil.RES_RESPONSE,reserveContractResponse);
                   contractReserveBDFPayLoadObj.add(ServiceUtil.CNTRCT_RSRV_BDF_PL,contractReserveBDFPayLoad);
                   boolean schemaValid=true;
                  try {
                	  			if(ServiceUtil.BDFValidationFlag.equalsIgnoreCase(propertyGetter.getValue("BDFSchemaValidation"))){
                	  				
                	  				schemaValid=validateSchema(contractReserveBDFPayLoadObj);
                	  			}
                	  			if(schemaValid){
                                insert = reserveForSalesDao.insertDFJson(contractReserveBDFPayLoadObj,resRequest,response);
                                kafkaProducer.sendTransformPayload(contractReserveBDFPayLoadObj.toString());
                	  			}
                         } catch (Exception ex) {
                                logger.error("Getting exception while inserting or publishing message to topic"+ex);
                         }
                   }
            return insert;
         }
		
		public  Date dateConversion(String date) throws InvalidInputParameterException {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsed = null;
			
			if(date == null || date.isEmpty()){
				return null;
			}
			try {
				format.setLenient(false);
				parsed = format.parse(date);
				return parsed;
			} catch (ParseException e) {
				throw new InvalidInputParameterException(propertyGetter.getValue("INVALID_DT_FMT"));
			}
		}
		
		
		public  Date dateConversionRespTimestamp(String date) throws InvalidInputParameterException {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date parsed = null;
			
			if(date == null || date.isEmpty()){
				return null;
			}
			try {
				format.setLenient(false);
				parsed = format.parse(date);
				return parsed;
			} catch (ParseException e) {
				throw new InvalidInputParameterException(propertyGetter.getValue("INVALID_DT_FMT"));
			}
		}
		
		public  boolean validateReqTimestampValue(String date) throws InvalidInputParameterException, ParseException {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			boolean invReqDate = false;
		    try {
					sdf1.setLenient(false);
					sdf1.parse(date);
			} catch (ParseException e) {
				invReqDate =true;
				return invReqDate;
				//throw new InvalidInputParameterException(propertyGetter.getValue("INVALID_DT_FMT"));
			}
		    if (!invReqDate) {
		    	String[] dat = date.split("-");
		    	String[] dat1 = dat[2].split("T");
		    	String[] dat2 = dat1[1].split(":");
		    	String[] dat3 = dat2[2].replace(".", "-").split("-");
		    	String[] dat4 = dat3[1].split("Z");
		    	if (dat[0].length() < 4 || dat[1].length() < 2
		    			|| dat1[0].length() < 2 || dat2[0].length() < 2
		    			|| dat2[1].length() < 2 || dat3[0].length() < 2
		    			|| dat4[0].length() < 3) {
		    		invReqDate = true;
		    	}
		    }
				return invReqDate;
		}
		
		public  Date dateConversiontoReqTimestamp(String date) throws InvalidInputParameterException, ParseException {
			//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsed = null;
			
			if(date == null || date.isEmpty()|| validateReqTimestampValue(date)){
				return null;
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = sdf1.parse(date);
			String formattedTime = output.format(d);
			
			
			try {
				output.setLenient(false);
				parsed = output.parse(formattedTime);
				return parsed;
			} catch (ParseException e) {
				throw new InvalidInputParameterException(propertyGetter.getValue("INVALID_DT_FMT"));
			}
		}
		
		public  Date dateConversiontoResTimestamp(String date) throws InvalidInputParameterException, ParseException {
			//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsed = null;
			
			if(date == null || date.isEmpty()){
				return null;
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = sdf1.parse(date);
			String formattedTime = output.format(d);
			
			
			try {
				output.setLenient(false);
				parsed = output.parse(formattedTime);
				return parsed;
			} catch (ParseException e) {
				throw new InvalidInputParameterException(propertyGetter.getValue("INVALID_DT_FMT"));
			}
		}
		
		public String getRecordId(){
			
			logger.info("getting record sequence : ReserveForSalesService.getRecordId()");
			String rec = ServiceUtil.RECRD_ID;
				
			long recSeq = reserveForSalesDao.getSeqForRecId();
			
			String result = rec.substring(0, rec.length()-String.valueOf(recSeq).length())+recSeq;
			return result;
		}
		
		public String converttoTitleCase(String val){
			return val.trim().substring(0,1).toUpperCase()+val.trim().substring(1).toLowerCase();
		}
		

		public boolean validateSchema(JsonObject contractReserveBDFPayLoadObj)  {
			try{
				JSONObject jsonSchema = new JSONObject(
						new JSONTokener(ReserveForSalesService.class.getResourceAsStream("/ContractReserveSchema_V2.0.json")));
				Schema schema = SchemaLoader.load(jsonSchema);
				schema.validate(contractReserveBDFPayLoadObj);
			}
			catch(ValidationException ve){
				logger.error("Exception" +ve);
				return false;
			}
			return true;
		}

}

