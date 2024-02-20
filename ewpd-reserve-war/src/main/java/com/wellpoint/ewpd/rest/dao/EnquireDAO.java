/**
 * 
 */
package com.wellpoint.ewpd.rest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wellpoint.ewpd.rest.model.Domain;
import com.wellpoint.ewpd.rest.model.EnquiryResponse;

/**
 * @author AF37766
 *
 */
@Repository
public class EnquireDAO {
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	private Logger logger;
    
	
	
	/**
	 * @return the jdbcTemplate
	 */
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}



	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<String> cntrctCodeListAvail = new ArrayList();
	
	public List<EnquiryResponse> fetchDetails(String contractCode) {
		EnquiryResponse enquiryResponse = new EnquiryResponse();
		
		List<EnquiryResponse> enquireList = new ArrayList();
		if(contractCode==null || !(contractCode.length()>0)){
			return null;
		}
		try{
			
		MapSqlParameterSource inputParameter = new MapSqlParameterSource();
		
		inputParameter.addValue("CNTRCT_ID", contractCode);
		
		String fetchSql= "SELECT * FROM RSRV_CNTRCT_ID_PL A, ENTY_DOMN_INFO C, AVLBL_DOMN D WHERE A.CNTRCT_ID  IN :CNTRCT_ID AND C.enty_sys_id =A.rsrv_cntrct_mstr_ref AND C.DOMN_SYS_ID = D.DOMN_SYS_ID";
		
		System.out.println("contractCode: "+contractCode);
		System.out.println("fetchSql :"+fetchSql);
		
		enquireList = jdbcTemplate.query(fetchSql, inputParameter, new fetchDetailsMapper());
		
		
		/*Domain dm = new Domain();
		dm.setBusinessEntity("businessEntity");
		dm.setBusinessUnit("businessUnit");
		List<String> lineOfBusiness =new ArrayList();
		lineOfBusiness.add("MEDICAL");
		lineOfBusiness.add("PHARMACY");
		lineOfBusiness.add("DENTAL");
		lineOfBusiness.add("VISION");
		
		dm.setLineOfBusiness(lineOfBusiness );
		dm.setMarketSegment("SG");
		enquiryResponse.setDomain(dm);*/
		
		
		
		return (enquireList!=null&& !enquireList.isEmpty())?enquireList:null;
		}catch(Exception e){
			e.printStackTrace();
			enquiryResponse.setMessage("ContractCode not available in the DB");
			enquireList.add(enquiryResponse);
			return enquireList;
		}
	}
	public List<EnquiryResponse> fetchDetails(List<String> contractCode) {
		EnquiryResponse enquiryResponse = new EnquiryResponse();
		
		List<EnquiryResponse> enquireList = new ArrayList();
		if(contractCode==null || !(contractCode.size()>0)){
			return null;
		}
		try{
			
		MapSqlParameterSource inputParameter = new MapSqlParameterSource();
		cntrctCodeListAvail = new ArrayList();
		cntrctCodeListAvail.addAll(contractCode);
		inputParameter.addValue("CNTRCT_ID", contractCode);
		//order changes added
		String fetchSql= "SELECT * FROM RSRV_CNTRCT_ID_PL A, ENTY_DOMN_INFO C, AVLBL_DOMN D WHERE A.CNTRCT_ID  IN (:CNTRCT_ID) AND C.enty_sys_id =A.rsrv_cntrct_mstr_ref AND C.DOMN_SYS_ID = D.DOMN_SYS_ID order by A.CNTRCT_ID ";
		
		logger.info("contractCode: "+contractCode);
		logger.info("fetchSql :"+fetchSql);
		enquireList = jdbcTemplate.query(fetchSql, inputParameter, new fetchDetailsMapper());
		
		
		/*Domain dm = new Domain();
		dm.setBusinessEntity("businessEntity");
		dm.setBusinessUnit("businessUnit");
		List<String> lineOfBusiness =new ArrayList();
		lineOfBusiness.add("MEDICAL");
		lineOfBusiness.add("PHARMACY");
		lineOfBusiness.add("DENTAL");
		lineOfBusiness.add("VISION");
		
		dm.setLineOfBusiness(lineOfBusiness );
		dm.setMarketSegment("SG");
		enquiryResponse.setDomain(dm);*/
		if(cntrctCodeListAvail!=null && !cntrctCodeListAvail.isEmpty()){
			for(String cntrct:cntrctCodeListAvail){
				EnquiryResponse enquiryResponse1 = new EnquiryResponse();
				enquiryResponse1.setContractCode(cntrct);
				enquiryResponse1.setMessage("ContractCode not available in the DB");
				if(enquireList!=null)
				enquireList.add(enquiryResponse1);
			}
		}
		
		
		return (enquireList!=null&& !enquireList.isEmpty())?enquireList:null;
		}catch(Exception e){
			e.printStackTrace();
			enquiryResponse.setMessage("ContractCode not available in the DB");
			enquireList.add(enquiryResponse);
			return enquireList;
		}
	}
	
	private final class fetchDetailsMapper implements RowMapper{
		
		@Override
		public EnquiryResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			EnquiryResponse enquiryResponse = new EnquiryResponse();
			
			Domain dm = new Domain();
			dm.setBusinessEntity(rs.getString("BUS_ENTY_NM"));
			dm.setBusinessUnit(rs.getString("MRKT_BUS_UNT"));
			List<String> lineOfBusiness =new ArrayList();
			lineOfBusiness.add(rs.getString("LOB_NM"));
			enquiryResponse.setContractCode(rs.getString("CNTRCT_ID"));
			
			dm.setLineOfBusiness(lineOfBusiness );
			dm.setMarketSegment(rs.getString("BUS_GRP_NM"));
			
			enquiryResponse.setDomain(dm);
			enquiryResponse.setMessage("SUCCESS");
			if(cntrctCodeListAvail !=null && !cntrctCodeListAvail.isEmpty()){
				if(cntrctCodeListAvail.contains(enquiryResponse.getContractCode())){
					cntrctCodeListAvail.remove(enquiryResponse.getContractCode());
				}
			}
			
			
			return enquiryResponse;
		}
		
	}
	

}
