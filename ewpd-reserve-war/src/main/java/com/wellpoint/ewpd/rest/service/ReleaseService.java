package com.wellpoint.ewpd.rest.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wellpoint.contractcodepool.ContractCodeManager;
import com.wellpoint.contractcodepool.exception.InvalidInputParameterException;
import com.wellpoint.ewpd.rest.Requests.ReleaseRequest;
import com.wellpoint.ewpd.rest.Responses.ContractCodes;
import com.wellpoint.ewpd.rest.Responses.ReleaseResponse;
import com.wellpoint.ewpd.rest.Responses.Status;
import com.wellpoint.ewpd.rest.dao.DatasourceBean;
import com.wellpoint.ewpd.rest.dao.ReleaseRequestDao;

/**
 * @author AF37766
 *
 */
@Service
public class ReleaseService {
	
	
	@Autowired
	private DatasourceBean datasourceBean;
	
	@Autowired
	private ReleaseRequestDao releaseRequestDao;
	
	@Autowired
	private PropertyGetter propertyGetter;
	
	private String callingSystem;
	
	public void getContractCodesStatus(ReleaseRequest relsRequest, ReleaseResponse relResp) throws Exception {
		Status s = new Status(); 
		relResp.setStatus(s);
		if(relsRequest.getCallingSystem() != null && validateCallingSystem(relsRequest.getCallingSystem())){
			if(relsRequest.getTxnId() == null || relsRequest.getTxnId().isEmpty()){
				throw new InvalidInputParameterException(propertyGetter.getValue("INVALID_TXNID"));
			}
		relResp.setTxnId(relsRequest.getTxnId());
		
		
		if(relsRequest.getTxnTimestamp()!=null && !relsRequest.getTxnTimestamp().isEmpty())
			checkTxnTimestamp(relsRequest.getTxnTimestamp());
			
		relResp.setTxnTimestamp((relsRequest.getTxnTimestamp()==null || relsRequest.getTxnTimestamp().isEmpty())
					?new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date()):relsRequest.getTxnTimestamp());
			
		long insertedSeqId =releaseRequestDao.insertRequest(relsRequest);
		
	
		 ContractCodeManager contractCodeManager = new ContractCodeManager(); 
		 contractCodeManager.setDataSource(datasourceBean.getDataSource());
		//System.out.println(System.getProperty("EWPD_OWNERSCHEMA"));
		 
		 if(relsRequest.getContractCodes() != null ){
			 Iterator cntIter = relsRequest.getContractCodes().iterator();
			 while(cntIter.hasNext()){
				 String cntrct = (String) cntIter.next();
				 if(cntrct.trim().length()<=0){
					 cntIter.remove();
				 }
			 }
		 }
		 
		if(relsRequest.getContractCodes() != null && !relsRequest.getContractCodes().isEmpty() && relsRequest.getContractCodes().size()>0){
			List<Map <String,String> > mapList =  contractCodeManager.releaseContracts(relsRequest.getContractCodes(),System.getProperty("EWPD_OWNERSCHEMA"));
		
		
		 
		int sucCount = 0;
		int failCount = 0;
		
		List<ContractCodes> contractCodes = new ArrayList<ContractCodes>();
		for(Map<String, String> map1:mapList){
			for(Map.Entry<String, String> entry:map1.entrySet()){
				
				ContractCodes e = new ContractCodes();
				e.setContractCode(entry.getKey());
				e.setStatusCode(Long.valueOf(entry.getValue()));
				if(e.getStatusCode() == 100){e.setStatusDesc(propertyGetter.getValue("RELEASE_STATUS_SUCCESS")); sucCount++;}
				if(e.getStatusCode() == 400){e.setStatusDesc(propertyGetter.getValue("RELEASE_STATUS_FAILURE")); failCount++;}
				contractCodes.add(e);
			}
		}
		if(sucCount != mapList.size() && failCount != mapList.size())relResp.setTxnStatus(propertyGetter.getValue("PARTIAL_SUCCESS"));
		else if(sucCount == mapList.size())relResp.setTxnStatus("Success");
		else if(failCount == mapList.size())relResp.setTxnStatus("Failure");
		
		s.setContractCodes(contractCodes);
		relResp.setStatus(s);
		
		
		
		
		boolean updated;
		if(insertedSeqId >0)
			updated= releaseRequestDao.updateTheResponse(relResp,insertedSeqId);
		
		}else{
			throw new InvalidInputParameterException(propertyGetter.getValue("INVALID_CNTRCTS"));
		}
		}else{
			throw new InvalidInputParameterException(propertyGetter.getValue("INVALID_CALLSYS_REL"));
		}
		
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
	
	
	
}
