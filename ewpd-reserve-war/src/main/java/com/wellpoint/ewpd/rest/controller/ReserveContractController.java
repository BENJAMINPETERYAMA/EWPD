package com.wellpoint.ewpd.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.wellpoint.contractcodepool.exception.InvalidInputParameterException;
import com.wellpoint.ewpd.rest.Requests.ReleaseRequest;
import com.wellpoint.ewpd.rest.Requests.ReserveRequest;
import com.wellpoint.ewpd.rest.Requests.ReserveRequestForSales;
import com.wellpoint.ewpd.rest.Responses.Input;
import com.wellpoint.ewpd.rest.Responses.ReleaseResponse;
import com.wellpoint.ewpd.rest.Responses.ReserveContractResponse;
import com.wellpoint.ewpd.rest.model.EnquiryResponse;
import com.wellpoint.ewpd.rest.Responses.ReserveForSalesResponse;
import com.wellpoint.ewpd.rest.model.Status;
import com.wellpoint.ewpd.rest.service.EnquireService;
import com.wellpoint.ewpd.rest.service.PropertyGetter;
import com.wellpoint.ewpd.rest.service.ReleaseService;
import com.wellpoint.ewpd.rest.service.ReserveForSalesService;
import com.wellpoint.ewpd.rest.service.ReserveService;
import com.wellpoint.ewpd.rest.util.ServiceUtil;

/**
 * @author AF37766
 *
 */

@RestController
@RequestMapping("/reserveContract")
public class ReserveContractController {
	
	private static final Logger logger = Logger.getLogger(ReserveContractController.class); 
	
	@Autowired
	private ReserveService resService;
	
	@Autowired
	private ReleaseService relService;
	
	@Autowired
	private PropertyGetter propertyGetter; 
	
	@Autowired
	private EnquireService enquireService; 

	@Autowired
	private ReserveForSalesService reserveForSalesService;
	
	
	@RequestMapping(value = "/reserve", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReserveContractResponse> reserveContractDetails(@RequestBody ReserveRequest resRequest) throws InvalidInputParameterException {
		//ReserveContractResponse response = new ReserveContractResponse();
		ReserveContractResponse response = new ReserveContractResponse();
		String InvalidNumeric_value  = propertyGetter.getValue("InvalidNumeric_Value");
		Status status = new Status();
		try {
			resService.setInputToResponse(response,resRequest);
			response =resService.validateandInsert(resRequest);
			//response.setHello("hello World");
			String validateResRequest = new Gson().toJson(resRequest);
			String reserveRequest = StringEscapeUtils.escapeHtml4(validateResRequest);
			System.out.println(reserveRequest); 
			logger.debug("Reserve Request   "+reserveRequest);
			return new ResponseEntity<ReserveContractResponse>(response, HttpStatus.OK);
		}
		
		catch (NumberFormatException e) {
			
			e.printStackTrace();
			status.setMessage(InvalidNumeric_value);
			status.setCode(HttpStatus.BAD_REQUEST.value());
			response.setStatus(status);
			return new ResponseEntity<ReserveContractResponse>(response, HttpStatus.BAD_REQUEST);
		}
		catch (InvalidInputParameterException e) {
			
			e.printStackTrace();
			status.setMessage(e.getMessage());
			status.setCode(HttpStatus.BAD_REQUEST.value());
			response.setStatus(status);
			return new ResponseEntity<ReserveContractResponse>(response, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			e.printStackTrace();
			status.setMessage(e.getMessage());
			status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setStatus(status);
			return new ResponseEntity<ReserveContractResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	@RequestMapping(value = "/release", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReleaseResponse> releaseContractDetails(@RequestBody ReleaseRequest relsRequest) throws InvalidInputParameterException {
		ReleaseResponse relResp = new ReleaseResponse();
		Input input =  new Input();
		String Cntrct_Release_Success = propertyGetter.getValue("Cntrct_Release_Success");
		String Cntrct_Release_Failure = propertyGetter.getValue("Cntrct_Release_Failure"); 
		String Cntrct_Release_Partial = propertyGetter.getValue("Cntrct_Release_Partial");
		com.wellpoint.ewpd.rest.Responses.Status status = new com.wellpoint.ewpd.rest.Responses.Status();
		try{
			
			input.setContractCodes(relsRequest.getContractCodes());
			relResp.setTxnId(relsRequest.getTxnId());
			relResp.setTxnTimestamp(relsRequest.getTxnTimestamp());
			relResp.setInput(input);
			relService.getContractCodesStatus(relsRequest , relResp);
			
			
			status.setCode(String.valueOf(HttpStatus.OK.value()));
			if(relResp.getTxnStatus().equalsIgnoreCase("success"))status.setMessage(Cntrct_Release_Success);
			else if(relResp.getTxnStatus().equalsIgnoreCase("failure"))status.setMessage(Cntrct_Release_Failure);
			else {status.setMessage(Cntrct_Release_Partial);}
			relResp.getStatus().setCode(status.getCode());
			relResp.getStatus().setMessage(status.getMessage());
			return new ResponseEntity<ReleaseResponse>(relResp, HttpStatus.OK);
		

		}catch (InvalidInputParameterException e) {
			
			//e.printStackTrace();
			//status.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			//status.setMessage(e.getMessage());
			
			relResp.setTxnStatus("failure");
			relResp.getStatus().setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			relResp.getStatus().setMessage(e.getMessage());
			return new ResponseEntity<ReleaseResponse>(relResp, HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			//e.printStackTrace();
			/*status.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			status.setMessage(e.getMessage());*/
			relResp.setTxnStatus("failure");
			relResp.getStatus().setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			relResp.getStatus().setMessage(e.getMessage());
			return new ResponseEntity<ReleaseResponse>(relResp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//11.0
	/*@RequestMapping(value="/enquireService/{contractCode}", method=RequestMethod.GET , produces={MediaType.APPLICATION_JSON_VALUE}, consumes={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<EnquiryResponse>> enquiryService(@PathVariable String contractCode){
		
		EnquiryResponse enquiryResponse = new EnquiryResponse();
		List<EnquiryResponse> enquiryResponseList =new ArrayList<EnquiryResponse>();
		try{
			
			enquiryResponseList= enquireService.getContractDetails(contractCode);
			
			return new ResponseEntity<List<EnquiryResponse>>(enquiryResponseList,HttpStatus.OK);

		}catch(Exception e){
			enquiryResponse.setMessage(e.getMessage());
			return new ResponseEntity<List<EnquiryResponse>>(enquiryResponseList,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
	
	@RequestMapping(value="/enquireService", method=RequestMethod.POST , produces={MediaType.APPLICATION_JSON_VALUE}, consumes={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<EnquiryResponse>> enquiryService(@RequestBody List<String> contrctList){
		
		EnquiryResponse enquiryResponse = new EnquiryResponse();
		List<EnquiryResponse> enquiryResponseList =new ArrayList<EnquiryResponse>();
		try{
			
			enquiryResponseList= enquireService.getContractDetails(contrctList);
			
			return new ResponseEntity<List<EnquiryResponse>>(enquiryResponseList,HttpStatus.OK);

		}catch(Exception e){
			e.printStackTrace();
			enquiryResponse.setMessage(e.getMessage());
			return new ResponseEntity<List<EnquiryResponse>>(enquiryResponseList,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
@RequestMapping(value = "/reserveForSales", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReserveForSalesResponse> reserveContractForSalesDetails(@RequestBody ReserveRequestForSales resRequest) throws Exception {
		
		String requestTimeStamp=reserveForSalesService.currentDate();
		logger.debug("Request Timestamp "+ resRequest.getTransactionTimestamp()+ "Current SystemTime"+ requestTimeStamp);
		logger.debug( "Time in milliseconds "+System.currentTimeMillis());
		ReserveForSalesResponse response = new ReserveForSalesResponse();
		logger.info("reserve contract for sales details started : ReserveContractController.reserveContractForSalesDetails");
		Status status = new Status();
		String cntrct = null;
		boolean quoteLineFlagExists = false;
		try {
			long time = System.currentTimeMillis();
			
			
			reserveForSalesService.validateRequest(resRequest);
			logger.info("validation of request for reserve contract for sales completed");
			
			cntrct = reserveForSalesService.quoteLineCheckForRequestContract(resRequest);
			
			if(cntrct != null && !cntrct.isEmpty()){
				  quoteLineFlagExists = true;
				  if(cntrct.equalsIgnoreCase(resRequest.getBaseContract())){
				      response.setContractCode(resRequest.getBaseContract());
				      status.setCode(HttpStatus.CREATED.value());
				      status.setMessage(propertyGetter.getValue(ServiceUtil.RET_BASE_CNTCT_MSG));
				      response.setStatus(status);
					  
				  }else{
				      response.setContractCode(cntrct);
				      status.setCode(HttpStatus.OK.value());
				      status.setMessage(propertyGetter.getValue(ServiceUtil.SUCCESS_RES_MSG));
				      response.setStatus(status);
				  }
			} else {
				if ((System.getProperty("simulation_mode") != null)
						&& (System.getProperty("simulation_mode")
								.equals("false"))) {
					logger.info("simulation mode : false");
					response = reserveForSalesService
							.processContractReserveService(resRequest);
					
			} else {
					logger.info("simultion mode : true");
					String filePath = System
							.getProperty("simulation_response_directory");
					
                    File file = new File(filePath);
                    filePath = file.getCanonicalPath();

					Gson gson = new Gson();
					response = (ReserveForSalesResponse) gson
							.fromJson(
									new FileReader(
											filePath
													+ "Sample_reserveWithHpcc_Response_success.json"),
									ReserveForSalesResponse.class);
				}
			}

		      logger.info("Total Response time for Pre-validation: " + (System.currentTimeMillis() - time));
			  
          	  response.setCallingSystem(resRequest.getCallingSystem());
			  response.setTransactionId(resRequest.getTransactionId());
			  response.setTransactionTimestamp(reserveForSalesService.currentDate());
			  logger.debug("Response Timestamp"+ response.getTransactionTimestamp()+"Time in Milliseconds" + System.currentTimeMillis());
          
		      reserveForSalesService.insertReqRes(resRequest, response, requestTimeStamp);
		      
		      if (!quoteLineFlagExists){
		    	  	
		    	  reserveForSalesService.processRequest(resRequest,response);
		    	  
			      }
		     
		  
		      
			return new ResponseEntity<ReserveForSalesResponse>(response, HttpStatus.OK);
		}
		
		catch (InvalidInputParameterException ex) {
			logger.error("Invalid input parameter exception"+ex);
			status.setMessage(ex.getMessage());
			status.setCode(HttpStatus.CONFLICT.value());
			response.setCallingSystem(resRequest.getCallingSystem());
			response.setStatus(status);
			response.setTransactionId(resRequest.getTransactionId());
			response.setTransactionTimestamp(reserveForSalesService.currentDate());
			reserveForSalesService.insertReqRes(resRequest, response, requestTimeStamp);
		    reserveForSalesService.processRequest(resRequest,response);
			return new ResponseEntity<ReserveForSalesResponse>(response, HttpStatus.BAD_REQUEST);
		}
		catch (Exception ex) {
			logger.error("Internal server issue exception"+ex);
			status.setMessage(propertyGetter.getValue(ServiceUtil.INTERNAL_SER_ERR));
			status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setCallingSystem(resRequest.getCallingSystem());
			response.setStatus(status);
			response.setTransactionId(resRequest.getTransactionId());
			response.setTransactionTimestamp(reserveForSalesService.currentDate());
			reserveForSalesService.insertReqRes(resRequest, response, requestTimeStamp);
		    reserveForSalesService.processRequest(resRequest,response);
			return new ResponseEntity<ReserveForSalesResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

}

