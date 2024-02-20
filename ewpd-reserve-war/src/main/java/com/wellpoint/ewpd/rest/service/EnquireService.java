/**
 * 
 */
package com.wellpoint.ewpd.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellpoint.ewpd.rest.dao.EnquireDAO;
import com.wellpoint.ewpd.rest.dao.ReserveRequestDao;
import com.wellpoint.ewpd.rest.model.EnquiryResponse;

/**
 * @author AF37766
 *
 */
@Service
public class EnquireService {
	
	@Autowired
	private ReserveRequestDao reserveRequestDao;
	
	@Autowired
	private EnquireDAO enquireDAO;

	public List<EnquiryResponse> getContractDetails(String contractCode) {
		
		List<EnquiryResponse> enquiryResponseLst = new ArrayList();
		
		enquiryResponseLst= enquireDAO.fetchDetails(contractCode);
		
		return enquiryResponseLst;
		
		
		
	}
	public List<EnquiryResponse> getContractDetails(List<String> contractList) {
		
		List<EnquiryResponse> enquiryResponseLst = new ArrayList();
		
		//System.out.println(contractList.toString());
		
		enquiryResponseLst= enquireDAO.fetchDetails(contractList);
		
		return enquiryResponseLst;
		
		
		
	}
	
	
	
}
