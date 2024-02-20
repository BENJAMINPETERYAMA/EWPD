/**
 * 
 */
package com.wellpoint.ewpd.rest.service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wellpoint.contractcodepool.ContractCodeManager;
import com.wellpoint.contractcodepool.bo.ReserveContractRequest;
import com.wellpoint.contractcodepool.exception.InvalidInputParameterException;
import com.wellpoint.contractcodepool.exception.NoContractCodeAvailableException;
import com.wellpoint.ewpd.rest.Requests.ReserveRequest;
import com.wellpoint.ewpd.rest.Responses.ReserveContractResponse;
import com.wellpoint.ewpd.rest.dao.DatasourceBean;
import com.wellpoint.ewpd.rest.dao.ReserveRequestDao;
import com.wellpoint.ewpd.rest.model.Input;
import com.wellpoint.ewpd.rest.model.Status;
/**
 * @author AF37766
 *
 */
@Service
//@PropertySource({"classpath:WEB-INF/properties/reserveService.properties"})
public class ReserveService {	
	
	//private static final Date Date = new Date();
	
	private static final Logger logger = Logger.getLogger(ReserveService.class); 
	
	@Autowired
	private ReserveRequestDao reserveRequestDao;
	
	@Autowired
	private DatasourceBean datasourceBean; 
	
	@Autowired
	private PropertyGetter propertyGetter; 
	
	/*@Value("${callingSystem}")
	private String callingSystem;*/
	private String callingSystem;
	public ReserveContractResponse validateandInsert(ReserveRequest resRequest) throws InvalidInputParameterException,NumberFormatException, Exception{
		
		/*PropertyGetter pg = new PropertyGetter();
		System.out.println("callingSystem : "+pg.getPath("callingSystem"));*/
		final String INVALID_EFFECTDATE = propertyGetter.getValue("INVALID_EFFECTDATE");
		final String INVALID_EXPDATE = propertyGetter.getValue("INVALID_EXPDATE");
		final String INVALID_DATERANGE = propertyGetter.getValue("INVALID_DATERANGE");
		final String DEFAULT_COMMENT = propertyGetter.getValue("DEFAULT_COMMENT");
		final String INVALID_TXNID = propertyGetter.getValue("INVALID_TXNID");
		final String INVALID_CNTRCTCODECOUNT = propertyGetter.getValue("INVALID_CNTRCTCODECOUNT");
		final String INVALID_DOMAINVALUE = propertyGetter.getValue("INVALID_DOMAINVALUE");
		final String INVALID_CALLINGSYSTEM = propertyGetter.getValue("INVALID_CALLINGSYSTEM");
		
		
		
		if(validateCallingSystem(resRequest.getCallingSystem())){
		
		if(resRequest != null && resRequest.getDomain() != null 
			  && resRequest.getDomain().getLineOfBusiness() != null && !resRequest.getDomain().getLineOfBusiness().isEmpty()
			  && resRequest.getDomain().getBusinessEntity() !=null && resRequest.getDomain().getBusinessUnit() !=null
			  && resRequest.getDomain().getMarketSegment() != null && !resRequest.getDomain().getBusinessEntity().isEmpty()
			  && !resRequest.getDomain().getBusinessUnit().isEmpty() && !resRequest.getDomain().getMarketSegment().isEmpty()
				&& Integer.valueOf(resRequest.getContractCodeCount())>0 && resRequest.getTxnId() != null && !resRequest.getTxnId().isEmpty()){
			SimpleDateFormat sdfActual = new SimpleDateFormat("yyyy-MM-dd");
			if((resRequest.getEffectiveDate() == null|| resRequest.getEffectiveDate().isEmpty()) && (resRequest.getExpiryDate() == null || resRequest.getExpiryDate().isEmpty())){
				Calendar cl = Calendar.getInstance();
				cl.setTime(new Date());
				resRequest.setEffectiveDate(sdfActual.format(cl.getTime()));
				
				cl.add(Calendar.YEAR,1);
				
				resRequest.setExpiryDate(sdfActual.format(cl.getTime()));
			}else if((resRequest.getEffectiveDate() == null || resRequest.getEffectiveDate().isEmpty()) && (resRequest.getExpiryDate() != null ||  !resRequest.getExpiryDate().isEmpty())){
					Calendar cl = Calendar.getInstance();
					cl.setTime(new Date());
					resRequest.setEffectiveDate(sdfActual.format(cl.getTime()));
					
			}else if((resRequest.getEffectiveDate() != null || !resRequest.getEffectiveDate().isEmpty()) && (resRequest.getExpiryDate() == null || resRequest.getExpiryDate().isEmpty())){
				Calendar cl = Calendar.getInstance();
				cl.setTime(dateConversion(resRequest.getEffectiveDate()));
				
				cl.add(Calendar.YEAR,1);
				
				resRequest.setExpiryDate(sdfActual.format(cl.getTime()));
			}
			
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//String requestDateString = "05/21/2018";
			//sdfActual.setTimeZone(TimeZone.getTimeZone("UTC"));
			//String requestDateString = sdfActual.format(dateConversion(resRequest.getEffectiveDate()));
			try{
            Date requestDate = sdfActual.parse(resRequest.getEffectiveDate());
            sdfActual.setLenient(false);
            String currentDateString = sdfActual.format(new Date());
            Date currentDate = sdfActual.parse(currentDateString);
            
            if(requestDate.compareTo(currentDate) < 0)
            	throw new InvalidInputParameterException(INVALID_EFFECTDATE);

			
			
            String expiryDateString = sdfActual.format(dateConversion(resRequest.getExpiryDate()));
            Date expiryDate = sdfActual.parse(expiryDateString);
			
			if(requestDate.compareTo(expiryDate) >= 0 ){
				throw new InvalidInputParameterException(INVALID_EXPDATE);
				
			}
			}catch (ParseException e) {
				//logger.fatal("Exception occured in convertDate", e);effectiveDate;
				
				throw new InvalidInputParameterException(propertyGetter.getValue("INVALID_DT_FMT"));
			}
			Calendar clndr = Calendar.getInstance();
			clndr.setTime(dateConversion(resRequest.getEffectiveDate()));
			clndr.add(Calendar.YEAR,3);
			if(dateConversion(resRequest.getExpiryDate()).after(clndr.getTime()) || dateConversion(resRequest.getExpiryDate()).equals(clndr.getTime())){
				throw new InvalidInputParameterException(INVALID_DATERANGE);
			}
			
			if(resRequest.getComment() == null || resRequest.getComment().isEmpty())
			resRequest.setComment(DEFAULT_COMMENT);
			
			logger.info("Request is validated successfully");
			
			long insertedSeqId =reserveRequestDao.insertRequest(resRequest);
			//System.out.println("Insertion happened with ID " +insertedSeqId);
			
			ReserveContractResponse reserveContractResponse = setUpTheResponse(resRequest);
			boolean updated = reserveRequestDao.updateTheResponse(reserveContractResponse,insertedSeqId);
			return reserveContractResponse;
		}else{
			if(resRequest.getTxnId() == null || resRequest.getTxnId().isEmpty()){
				throw new InvalidInputParameterException(INVALID_TXNID);
			}
			if(Integer.valueOf(resRequest.getContractCodeCount())<=0){
				throw new InvalidInputParameterException(INVALID_CNTRCTCODECOUNT);
			}
			throw new InvalidInputParameterException(INVALID_DOMAINVALUE);
		}
		
		}else{
			throw new InvalidInputParameterException(INVALID_CALLINGSYSTEM);
		}
	}

	private boolean validateCallingSystem(String callingSystemRequest) throws InvalidInputParameterException {
		callingSystem = propertyGetter.getValue("callingSystem");
		System.out.println("callingSystem : "+callingSystem);
		String[] s = callingSystem.split(",");
		for(String s1:s){
			if(s1.equalsIgnoreCase(callingSystemRequest))
				return true;
		}
		return false;
	}

	private ReserveContractResponse setUpTheResponse(ReserveRequest resRequest) throws NumberFormatException, DataAccessException, Exception {
		 ContractCodeManager contractCodeManager = new ContractCodeManager(); 
		 //reserveRequestDao.
		 contractCodeManager.setDataSource(datasourceBean.getDataSource());
		 ReserveContractRequest singleRepoReq =  new ReserveContractRequest();
		 
		 singleRepoReq.setBusinessEntity(resRequest.getDomain().getBusinessEntity());
		 singleRepoReq.setBusinessGroup(resRequest.getDomain().getMarketSegment());
		 singleRepoReq.setCallingSystem(resRequest.getCallingSystem());
		 singleRepoReq.setComments(resRequest.getComment());
		 singleRepoReq.setContractCount(Integer.valueOf(resRequest.getContractCodeCount()));
		 singleRepoReq.setEffectiveDate(dateConversion(resRequest.getEffectiveDate()));
		 singleRepoReq.setExpirtyDate(dateConversion(resRequest.getExpiryDate()));
		 singleRepoReq.setLineOfBusiness(resRequest.getDomain().getLineOfBusiness());
		 singleRepoReq.setMarketBusinessUnit(resRequest.getDomain().getBusinessUnit());
		 
		
		ReserveContractResponse reserveContractResponse= new ReserveContractResponse();
		setInputToResponse(reserveContractResponse,resRequest);
		
		
		List<String> contractCodes = new ArrayList<>();
		//System.out.println(System.getProperty("EWPD_OWNERSCHEMA"));
		contractCodes.add(contractCodeManager.reserveContracts(singleRepoReq, System.getProperty("EWPD_OWNERSCHEMA")));
		reserveContractResponse.setContractCodes(contractCodes); 
		
		Status s = new Status();
		s.setMessage(propertyGetter.getValue("CNTRCT_RESERVED"));
		s.setCode(HttpStatus.OK.value());
		reserveContractResponse.setStatus(s);
		return reserveContractResponse;
		
	}

	public void setInputToResponse(ReserveContractResponse response,ReserveRequest resRequest) throws InvalidInputParameterException{
		
		response.setTxnId(resRequest.getTxnId());
		
		if(resRequest.getTxnTimestamp()!=null && !resRequest.getTxnTimestamp().isEmpty())
		checkTxnTimestamp(resRequest.getTxnTimestamp());
		
		response.setTxnTimestamp((resRequest.getTxnTimestamp()==null || resRequest.getTxnTimestamp().isEmpty())
				?new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date()):resRequest.getTxnTimestamp());
		Input input =  new Input();
		input.setDomain(resRequest.getDomain());
		input.setComment(resRequest.getComment());
		try{
			input.setContractCodeCount(Integer.valueOf(resRequest.getContractCodeCount()));
		}catch(NumberFormatException ne){
			throw new InvalidInputParameterException(propertyGetter.getValue("InvalidNumeric_Value"));
		}
		
		
		
		input.setEffectiveDate(resRequest.getEffectiveDate());
		input.setExpiryDate(resRequest.getExpiryDate());
		response.setInput(input);
		
	}
	
	private void checkTxnTimestamp(String txnTimestamp) throws InvalidInputParameterException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
		try {
			sdf.parse(txnTimestamp);
		} catch (ParseException e) {
			throw new InvalidInputParameterException(propertyGetter.getValue("INVALID_TXNTMSTMP_FORMAT"));
		}
		
	}

	public  Date dateConversion(String date) throws InvalidInputParameterException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = null;
		//java.sql.Date sql = null;
		try {
			format.setLenient(false);
			parsed = format.parse(date);
			//sql = new java.sql.Date(parsed.getTime());
			return parsed;
		} catch (ParseException e) {
			//logger.fatal("Exception occured in convertDate", e);effectiveDate;
			
			throw new InvalidInputParameterException(propertyGetter.getValue("INVALID_DT_FMT"));
		}
	}
	

}
