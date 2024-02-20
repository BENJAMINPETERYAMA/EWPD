/*
 * Created on May 12, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.util.StringUtils;

import com.wellpoint.ets.bx.mapping.domain.entity.Accumulator;
import com.wellpoint.ets.bx.mapping.domain.entity.ClaimCodeSequence;
import com.wellpoint.ets.bx.mapping.domain.entity.ClaimLevelSequence;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.UMRule;
import com.wellpoint.ets.bx.mapping.domain.entity.UMRuleCodeSequence;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.util.BxUtil;


/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HippaSegmentRepositoryImpl implements HippaSegmentRepository {

	private DataSource dataSource;

	private static Logger log = Logger
			.getLogger(HippaSegmentRepositoryImpl.class);
	
	private final String eb01CodeValues = "select b.data_element_val as \"Value\", " 
				+ "c.val_desc_txt as \"Description\" , " 
						+" CASE " 
						+" WHEN " 
						+" INSTR( b.data_element_val, '.' ,1, '2' ) > 0 OR " 
						+" INSTR( b.data_element_val, '+' ,1, '2' ) > 0 OR " 
						+" INSTR( b.data_element_val, '-' ,1, '2' ) > 0 OR " 
						+" LENGTH(TRIM(TRANSLATE(b.data_element_val, ' +-.0123456789', ' '))) IS NOT NULL " 
						+" THEN b.data_element_val " 
						+" END            alpha , " 
						+" CASE " 
						+" WHEN " 
						+" INSTR( b.data_element_val, '.' ,1, '2' ) <= 0 AND "
						+" INSTR( b.data_element_val, '+' ,1, '2' ) <= 0 AND " 
						+" INSTR( b.data_element_val, '-' ,1, '2' ) <= 0 AND " 
						+" LENGTH(TRIM(TRANSLATE(b.data_element_val, ' +-.0123456789', ' '))) IS NULL " 
						+" THEN TO_NUMBER(b.data_element_val) " 
						+" END            numeric  "
				+" from bx_cntrct_var_valdn_mapg b " 
				+" left outer join bx_hippa_segment_val c " 
				+" on b.data_element_id = c.data_element_id " 
				+" and b.data_element_val = c.data_element_val ";
	
	private final String hippaCodeValues = "select b.data_element_val as \"Value\", "
				+ "b.val_desc_txt as \"Description\" , "
					+" CASE " 
					+" WHEN " 
					+" INSTR( b.data_element_val, '.' ,1, '2' ) > 0 OR " 
					+" INSTR( b.data_element_val, '+' ,1, '2' ) > 0 OR " 
					+" INSTR( b.data_element_val, '-' ,1, '2' ) > 0 OR " 
					+" LENGTH(TRIM(TRANSLATE(b.data_element_val, ' +-.0123456789', ' '))) IS NOT NULL " 
					+" THEN b.data_element_val " 
					+" END            alpha , " 
					+" CASE " 
					+" WHEN " 
					+" INSTR( b.data_element_val, '.' ,1, '2' ) <= 0 AND "
					+" INSTR( b.data_element_val, '+' ,1, '2' ) <= 0 AND " 
					+" INSTR( b.data_element_val, '-' ,1, '2' ) <= 0 AND " 
					+" LENGTH(TRIM(TRANSLATE(b.data_element_val, ' +-.0123456789', ' '))) IS NULL " 
					+" THEN TO_NUMBER(b.data_element_val) " 
					+" END            numeric  "
				+ "from bx_hippa_segment_val b ";
	
	private final String umRuleValues = "SELECT b.RULE_ID as \"Value\", "
		+" b.RULE_SHRT_DESC as \"Description\" , "
		+" CASE "  
		+" WHEN " 
		+" INSTR( b.RULE_ID, '.' ,1, '2' ) > 0 OR "
		+" INSTR( b.RULE_ID, '+' ,1, '2' ) > 0 OR " 
		+" INSTR( b.RULE_ID, '-' ,1, '2' ) > 0 OR "  
		+" LENGTH(TRIM(TRANSLATE(b.RULE_ID, ' +-.0123456789', ' '))) IS NOT NULL " 
		+" THEN b.RULE_ID "  
		+" END            alpha , " 
		+" CASE "  
		+" WHEN " 
		+" INSTR( RULE_ID, '.' ,1, '2' ) <= 0 AND " 
		+" INSTR( RULE_ID, '+' ,1, '2' ) <= 0 AND "  
		+" INSTR( RULE_ID, '-' ,1, '2' ) <= 0 AND " 
		+" LENGTH(TRIM(TRANSLATE(RULE_ID, ' +-.0123456789', ' '))) IS NULL "  
		+" THEN TO_NUMBER(RULE_ID) "  
		+" END            numeric "
		+" FROM RULE b";

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepository#getHippaSegmentsForAutocomplete(java.lang.String, java.lang.String, java.lang.String, int)
	 */
	public List getHippaSegmentsForAutocomplete(String hippaSegmentName,
			String searchString, String variableFormat, int noOfRecords) {
		List matchingHippaCodeValues = null;
		if (null == hippaSegmentName || ("".equals(hippaSegmentName.trim()))) {
			log.info("HippaSegment name cannot be null.");
			return null;
		}

		if (null == variableFormat || ("".equals(variableFormat.trim()))) {
			log.info("Variable format name cannot be null.");
			return null;
		}

		if (null == searchString) {
			searchString = "";
		}

		String query = "select b.data_element_val as \"Value\", c.val_desc_txt as \"Description\" "
				+ "from bx_cntrct_var_valdn_mapg b " 
				+"left outer join bx_hippa_segment_val c " 
				+"on b.data_element_id = c.data_element_id " 
				+"and b.data_element_val = c.data_element_val "
				+ "where b.var_frmt = '"
				+ variableFormat.trim()
				+ "' "
				+ "and b.data_element_id = '"
				+ hippaSegmentName.trim()
				+ "' "
				+ "and b.data_element_val like '" + searchString + "%'"
				+ " order by \"Value\" ASC ";
		if (noOfRecords > 0) {
			noOfRecords = noOfRecords + 1;
			query ="select * from ("+query+") where rownum <" + noOfRecords;
		}
		log.trace("Query in HRepo (search eb01): " + query);
		HippaCodeLocateQuery hippaCodeLocateQuery = new HippaCodeLocateQuery(
				dataSource, query);//List<HippaCodeValue>
		matchingHippaCodeValues = hippaCodeLocateQuery.execute();
		if (null != matchingHippaCodeValues
				&& !matchingHippaCodeValues.isEmpty()) {
			log.trace("No. of  matching HippaCodeValues for "
					+ hippaSegmentName.trim() + " is "
					+ matchingHippaCodeValues.size());
			return matchingHippaCodeValues;
		}
		return new ArrayList();
	}
	
	/**
	 * This function is added for mass update EB01 auto populate to get all EB01 values , 
	 * since there are multiple formats.
	 * @param searchString
	 * @param noOfRecords
	 * @return
	 */
	public List getAllEB01ForAutocomplete(String searchString, int noOfRecords) {
		List matchingHippaCodeValues = null;
		
		if (null == searchString) {
			searchString = "";
		}

		String query = "select b.data_element_val as \"Value\", c.val_desc_txt as \"Description\" "
				+ "from bx_cntrct_var_valdn_mapg b " 
				+"left outer join bx_hippa_segment_val c " 
				+"on b.data_element_id = c.data_element_id " 
				+"and b.data_element_val = c.data_element_val "
				+ "where b.data_element_id = 'EB01' and upper(b.data_element_val) like upper('" + searchString + "%')"
				+" GROUP BY b.data_element_val, c.val_desc_txt"
				+ " order by \"Value\" ASC ";
		if (noOfRecords > 0) {
			noOfRecords = noOfRecords + 1;
			query ="select * from ("+query+") where rownum <" + noOfRecords;
		}
		log.trace("Query in HRepo (search eb01): " + query);
		HippaCodeLocateQuery hippaCodeLocateQuery = new HippaCodeLocateQuery(
				dataSource, query);//List<HippaCodeValue>
		matchingHippaCodeValues = hippaCodeLocateQuery.execute();
		if (null != matchingHippaCodeValues
				&& !matchingHippaCodeValues.isEmpty()) {
			log.trace("No. of  matching HippaCodeValues for EB01 is "
					+ matchingHippaCodeValues.size());
			return matchingHippaCodeValues;
		}
		return new ArrayList();
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepository#getMatchingHippaCodeValuesFromDataDictionaryForAutoComplete(java.lang.String, java.lang.String, int)
	 */
	public List getMatchingHippaCodeValuesFromDataDictionaryForAutoComplete(
			String hippaSegmentName, String searchString,
			int noOfRecordsForPopUp) {
		if (null == hippaSegmentName || ("".equals(hippaSegmentName.trim()))) {
			log.info("HippaSegment name cannot be null.");
			return null;
		}
		if (null == searchString) {
			searchString = "";
		}
		
		if(null != hippaSegmentName && hippaSegmentName.startsWith("III02")){
			hippaSegmentName = DomainConstants.III02_NAME;
		}
		
		
		String query;
		if (DomainConstants.UMRULE_NAME.equals(hippaSegmentName)){
			 query = "SELECT b.rule_id as \"Value\", "
					+ "b.rule_shrt_desc as \"Description\" "
					+ "FROM rule b " + "WHERE b.rule_typ_cd = '"
					+ hippaSegmentName + "' "
					+ "and b.wpd_del_flag = 'N' "
					+ "and b.rule_id  like '" + searchString + "%' "
					+ " order by \"Value\" ASC ";
		}else{
			query = "select b.data_element_val as \"Value\", "
				+ "b.val_desc_txt as \"Description\" "
				+ "from bx_hippa_segment_val b " + "where b.data_element_id= '"
				+ hippaSegmentName.trim() + "' "
				+ "and upper(b.data_element_val) like upper('" + searchString.trim() + "%') "
				+ " order by \"Value\" ASC ";
		}
		
		if (noOfRecordsForPopUp > 0) {
			noOfRecordsForPopUp = noOfRecordsForPopUp + 1;
			query ="select * from ("+query+") where rownum <" + noOfRecordsForPopUp;
		}
		log.trace("Query in HRepo (find xcept eb01): " + query);
		HippaCodeLocateQuery hippaCodeLocateQuery = new HippaCodeLocateQuery(
				dataSource, query);//List<HippaCodeValue>
		List matchingHippaCodeValues = hippaCodeLocateQuery.execute();
		if (null != matchingHippaCodeValues
				&& !matchingHippaCodeValues.isEmpty()) {
			log.trace("No. of  matching HippaCodeValues for "
					+ hippaSegmentName.trim() + " is "
					+ matchingHippaCodeValues.size());
			return matchingHippaCodeValues;
		}
		return new ArrayList();
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepository#findMatchingHippaCodeValuesForEB01(java.lang.String, java.lang.String, java.lang.int)
	 */
	public List findMatchingHippaCodeValuesForEB01(String hippaSegmentName,
			String searchString, String variableFormat, int noOfRecords) {
		List matchingHippaCodeValues = new ArrayList();
		if (null == hippaSegmentName || ("".equals(hippaSegmentName.trim()))) {
			log.info("HippaSegment name cannot be null.");
			return matchingHippaCodeValues;
		}

		if (null == variableFormat || ("".equals(variableFormat.trim()))) {
			log.info("Variable format name cannot be null.");
			return matchingHippaCodeValues;
		}

		if (null == searchString) {
			searchString = "";
		}

		String query = eb01CodeValues
				+ " where b.var_frmt = '"
				+ variableFormat.trim().toUpperCase()
				+ "' "
				+ " and b.data_element_id = '"
				+ hippaSegmentName.trim().toUpperCase()
				+ "' "
				+ " and upper(c.val_desc_txt) like upper('%" + searchString + "%') "
				+ " order by numeric , alpha ASC ";
		if (noOfRecords > 0) {
			noOfRecords = noOfRecords + 1;
			query ="select * from ("+query+") where rownum <" + noOfRecords;
		}
		log.trace("Query in HRepo (search eb01): " + query);
		HippaCodeLocateQuery hippaCodeLocateQuery = new HippaCodeLocateQuery(
				dataSource, query);//List<HippaCodeValue>
		matchingHippaCodeValues = hippaCodeLocateQuery.execute();
		if (null != matchingHippaCodeValues
				&& !matchingHippaCodeValues.isEmpty()) {
			log.trace("No. of  matching HippaCodeValues for "
					+ hippaSegmentName.trim() + " is "
					+ matchingHippaCodeValues.size());
		}
		return matchingHippaCodeValues;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepository#findMatchingHippaCodeValuesFromDataDictionary(java.lang.String, java.lang.String,java.lang.int )
	 */
	public List findMatchingHippaCodeValuesFromDataDictionary(
			String hippaSegmentName, String searchString, int noOfRecords) {
		List matchingHippaCodeValues = new ArrayList();
		String query;
		if (null == hippaSegmentName || ("".equals(hippaSegmentName.trim()))) {
			log.info("HippaSegment name cannot be null.");
			return matchingHippaCodeValues;
		}
		if (hippaSegmentName.startsWith(DomainConstants.III02_NAME)) {
			hippaSegmentName = DomainConstants.III02_NAME;
		}
		if (hippaSegmentName.startsWith(DomainConstants.NOTE_TYPE_CODE)) {
			hippaSegmentName = DomainConstants.NOTE_TYPE_CODE;
		}
		
		if (null == searchString) {
			searchString = "";
		}
		if (DomainConstants.UMRULE_NAME.equals(hippaSegmentName.trim())){
			 query = umRuleValues
					 + " where b.rule_typ_cd= '"
					 + hippaSegmentName.trim() + "' "
					 + "and b.rule_shrt_desc like '%"
					 + searchString.trim() + "%'"
					 + "and b.wpd_del_flag = 'N' "
					 + " order by numeric , alpha ASC ";
		}else{ 
			query = hippaCodeValues 
				+ " where b.data_element_id= '"
				+ hippaSegmentName.trim() + "' " 
				+ " and upper(b.val_desc_txt) like upper('%"
				+ searchString.trim() + "%') "
				+" order by numeric , alpha ASC ";
		}
		if (noOfRecords > 0) {
			noOfRecords = noOfRecords + 1;
			query ="select * from ("+query+") where rownum <" + noOfRecords;
		}
		log.trace("Query in HRepo (find xcept eb01): " + query);
		HippaCodeLocateQuery hippaCodeLocateQuery = new HippaCodeLocateQuery(
				dataSource, query);//List<HippaCodeValue>
		matchingHippaCodeValues = hippaCodeLocateQuery.execute();
		if (null != matchingHippaCodeValues
				&& !matchingHippaCodeValues.isEmpty()) {
			log.trace("No. of  matching HippaCodeValues for "
					+ hippaSegmentName.trim() + " is "
					+ matchingHippaCodeValues.size());
		}
		return matchingHippaCodeValues;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepository#getAvailableHippaSegmentValuesFromDataDictionary(java.lang.String, java.lang.int)
	 */
	public List getAvailableHippaSegmentValuesFromDataDictionary(
			String hippaSegmentName, int noOfRecords) {
		List possibleHippaCodeValues = new ArrayList();
		if (null == hippaSegmentName || ("".equals(hippaSegmentName.trim()))) {
			log.info("HippaSegment name cannot be null.");
			return possibleHippaCodeValues;
		}
		// SSCR19537 - April - Start
		if (hippaSegmentName.startsWith(DomainConstants.III02_NAME)) {
			hippaSegmentName = DomainConstants.III02_NAME;
		}
		if (hippaSegmentName.startsWith(DomainConstants.NOTE_TYPE_CODE)) {
			hippaSegmentName = DomainConstants.NOTE_TYPE_CODE;
		}
		// SSCR19537 - April - End
		
		 //Added for NM1 Message Segment
        if(hippaSegmentName.equalsIgnoreCase(DomainConstants.LOOP2120NM1MESSAGESEGMENT)){
        	hippaSegmentName = DomainConstants.NM1_MSG_SGMNT;
        }
        
		String query = hippaCodeValues
				+ " where b.data_element_id= '"
				+ hippaSegmentName.trim() + "' "
				+ " order by numeric , alpha ASC ";
		if (noOfRecords > 0) {
			noOfRecords = noOfRecords + 1;
			query ="select * from ("+query+") where rownum <" + noOfRecords;
		}
		HippaCodeLocateQuery hippaCodeLocateQuery = new HippaCodeLocateQuery(
				dataSource, query);//List<HippaCodeValue>
		possibleHippaCodeValues = hippaCodeLocateQuery.execute();
		if (null != possibleHippaCodeValues
				&& !possibleHippaCodeValues.isEmpty()) {
			log.trace("No. of possible hippa code values for "
					+ hippaSegmentName.trim() + " is "
					+ possibleHippaCodeValues.size());
		}
		return possibleHippaCodeValues;
	}

	private class HippaCodeLocateQuery extends MappingSqlQuery {

		private HippaCodeLocateQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		/* (non-Javadoc)
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet, int)
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			HippaCodeValue hippaCodeValue = new HippaCodeValue();
			hippaCodeValue.setValue(rs.getString("Value"));
			hippaCodeValue.setDescription(rs.getString("Description"));
			log.trace("Desc, Value " + hippaCodeValue.getDescription() + ", "
					+ hippaCodeValue.getValue());
			return hippaCodeValue;
		}
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepository#getAvailableHippaSegmentValuesForEB01(java.lang.String,java.lang.String,java.lang.int)
	 */
	public List getAvailableHippaSegmentValuesForEB01(String hippaSegmentName,
			String variableFormat, String pva, int noOfRecords) {
		List possibleHippaCodeValues = new ArrayList();
		if (null == hippaSegmentName || ("".equals(hippaSegmentName.trim()))) {
			log.info("HippaSegment name cannot be null.");
			return possibleHippaCodeValues;
		}

		if (!DomainConstants.EB01_NAME.equals(hippaSegmentName.trim().toUpperCase())) {
			log.info("Expected hippaSegment is " + DomainConstants.EB01_NAME
					+ " but found" + hippaSegmentName);
			return possibleHippaCodeValues;

		}
		if (null == variableFormat || ("".equals(variableFormat.trim()))) {
			log.info("Variable format name cannot be null.");
			return possibleHippaCodeValues;
		}
		
		String query = null;
		query = eb01CodeValues
				+ " where b.var_frmt = '"
				+ variableFormat.trim().toUpperCase()
				+ "' "
				+ " and b.data_element_id = '"
				+ hippaSegmentName.trim().toUpperCase() + "' "
				 + " order by numeric , alpha ASC ";
		
		
		/* START June 2013 Release CR 47 - UM value is fetched regardless of PVA. So commenting out following 
		 * code */ 
		// CR29
		/*
		if (null != pva && !"".equals(pva)) {
			if (DomainConstants.HMO.equalsIgnoreCase(pva) || DomainConstants.HM.equalsIgnoreCase(pva)
					|| DomainConstants.H.equalsIgnoreCase(pva)) {
				query = query + " order by numeric , alpha ASC ";
			}
		} else {
			query = query + " and b.data_element_val != 'UM'"
		
			+ " order by numeric , alpha ASC ";
		}*/
		/* END June 2013 Release */
		
		if (noOfRecords > 0) {
			noOfRecords = noOfRecords + 1;
			query ="select * from ("+query+") where rownum <" + noOfRecords;
		}
		HippaCodeLocateQuery hippaCodeLocateQuery = new HippaCodeLocateQuery(
				dataSource, query);//List<HippaCodeValue>
		possibleHippaCodeValues = hippaCodeLocateQuery.execute();
		if (null != possibleHippaCodeValues
				&& !possibleHippaCodeValues.isEmpty()) {
			log.trace("No. of possible hippa code values for "
					+ hippaSegmentName + " is "
					+ possibleHippaCodeValues.size());
		}
		return possibleHippaCodeValues;
	}

	/**
	 * @return Returns the dataSource.
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource The dataSource to set.
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepository#getHippaSegmentDescription(java.lang.String)
	 */
	public HippaSegment getHippaSegmentDescription(String hippaSegmentName) {
		
		HippaSegment hippaSegment = null;
		String hippaDescQuery = " SELECT DATA_ELEMENT_ID, DATA_ELEMENT_NAME,DATA_ELEMENT_DEF from BX_HIPPA_SEGMENT "
				+ " WHERE DATA_ELEMENT_ID='" + hippaSegmentName.trim() + "'";

		HippaSegmentDescQuery hippaCodeLocateQuery = new HippaSegmentDescQuery(
				dataSource, hippaDescQuery);
		List list = hippaCodeLocateQuery.execute();
		if(list.size() > 0){
			hippaSegment =(HippaSegment) hippaCodeLocateQuery.execute().get(0);
		}
		return hippaSegment;
	}

	private class HippaSegmentDescQuery extends MappingSqlQuery {

		private HippaSegmentDescQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		/* (non-Javadoc)
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet, int)
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			HippaSegment hippaSegment = new HippaSegment();
			hippaSegment.setHippaSegmentName(rs.getString("DATA_ELEMENT_ID"));
			hippaSegment.setDescription(rs.getString("DATA_ELEMENT_NAME"));
			hippaSegment.setHippaSegmentDefinition(rs.getString("DATA_ELEMENT_DEF"));
			return hippaSegment;
		}
	}
	
	/**
	 * Added for retrieving value and description for UMRULE popup.
	 * @param hippaSegmentName
	 * @return HippaSegment
	 */
	public List getAvailableUMRule(String hippaSegmentName, int noOfRecords) {
		List possibleHippaCodeValues = new ArrayList();
		if (null == hippaSegmentName || ("".equals(hippaSegmentName.trim()))) {
			log.debug("HippaSegment name cannot be null.");
			return possibleHippaCodeValues;
		}
		String query = umRuleValues + " where b.rule_typ_cd= '"
				+ hippaSegmentName.trim() + "'"
				+ "and b.wpd_del_flag = 'N' "
				+ " order by numeric , alpha ASC ";
		if (noOfRecords > 0) {
			query = "select * from (" + query + ") where rownum <="
					+ noOfRecords;
		}
		log.debug("query : " + query);
		HippaCodeLocateQuery hippaCodeLocateQuery = new HippaCodeLocateQuery(
				dataSource, query);
		possibleHippaCodeValues = hippaCodeLocateQuery.execute();
		if (null != possibleHippaCodeValues
				&& !possibleHippaCodeValues.isEmpty()) {
			log.debug("No. of possible hippa code values for "
					+ hippaSegmentName.trim() + " is "
					+ possibleHippaCodeValues.size());
		}
		return possibleHippaCodeValues;
	}
	
	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepository#viewRuleSequenceIndicators(java.lang.String)
	 * Modified existing query to remove CLM_ICD_VRSN_IND,CLM_SRVC_CLS_HIGH,CLM_SRVC_CLS_LOW,CLM_LMT_CLS_HIGH,CLM_LMT_CLS_LOW,
       CLM_HCPT_LOW_VAL,CLM_HCPT_HIGH_VAL,CLM_DIAG_LOW_VAL,CLM_DIAG_HIGH_VAL,CLM_REV_LOW_VAL,CLM_REV_HIGH_VAL,
       CLM_ICDP_LOW_VAL,CLM_ICDP_HIGH_VAL,SRVC_CLS_HIGH,SRVC_CLS_LOW ,LMT_CLS_HIGH   and LMT_CLS_LOW  fields under the Rule Sequence Number*/
	
	public List viewRuleSequenceIndicators(String ruleId) {
		List ruleSequenceList = null;
		if(null!=ruleId && !"".equals(ruleId.trim())){
			ruleId = ruleId.trim();
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT RULEDAT.RULE_SQNC_NBR, RULEDAT.EXCLSN_IND," +
					" RULEDAT.CLM_TYPE, RULEDAT.PLC_OF_SRVC, RULEDAT.PAT_LOW_AGE," +
					" RULEDAT.PAT_HIGH_AGE, RULEDAT.GNDR_CD, RULEDAT.PRVDR_ID," +
					" RULEDAT.PRVDR_SPCLTY_CD, RULEDAT.BNFT_ACCUM_NM ," +
					" RULEDAT.BNFT_ACCUM_LMT_AMT, RULEDAT.BNFT_ACCUM_LMT_NBR," +
					" RULEDAT.NTFY_ONLY_IND, RULEDAT.CLNL_RVW_IND ," +
					" RULEDAT.DLR_LMT, RULEDAT.SERIVCE_CD , RULEDAT.GRP_ST," +
					" RULEDAT.LEN_OF_STAY, RULEDAT.ITS_SPCLTY_CD ," +
					" TO_DATE(RULEDAT.SRVC_STRT_DT,'DD-MON-YYYY') AS SRVC_STRT_DT," +
					" TO_DATE(RULEDAT.SRVC_END_DT,'DD-MON-YYYY') AS SRVC_END_DT," +
					" RULEDAT.MBR_RELSHP_CDE, RULEDAT.PRCDR_MODFR_CDE, RULEDAT.EDIT_CDE_1," +
					" RULEDAT.EDIT_CDE_2, RULEDAT.PRVDR_ST_CD, RULEDAT.BILL_TYP_CD ," +
					" RULEDAT.TT_CD, RULEDAT.ATCHMT_IND, RULEDAT.PAT_MBR_CD," +
					" RULEDAT.HOSP_FCIL_CD, RULEDAT.DAYS_FROM_INJRY, RULEDAT.DAYS_FROM_ILNS," +
					" RULEDAT.HMO_CLS_CD, RULEDAT.TOT_CHRG_SIGN, RULEDAT.TOT_CHARGE_AMOUNT," +
					" RULEDAT.WPD_DEL_FLAG, RULEDAT.CPAY_IND, RULEDAT.HNDRD_PCT_IND," +
					" RULEDAT.MEDCR_ASGN_IND, RULEDAT.PROC_MDFR_CD2, RULEDAT.SPRT_HCPS_IND," +
					" RULEDAT.DIAG_IND, RULEDAT.PCP_IND, RULEDAT.PVDR_LIC_ID, RULEDAT.PVDR_MDCR_ID," +
					" RULEDAT.BLNG_PVDR_NR, RULEDAT.RNDR_PVDR_NR, RULEDAT.BLNG_NPI, RULEDAT.RNDR_NPI," +
					" RULEDAT.ELGBL_EXPNS_SIGN_CD, RULEDAT.ELGBL_EXPNS_AMT, RULEDAT.AGE_TYPE_CD," +
					" RULEDAT.RULE_VRSN_NBR, RULEDAT.DRG_CD, RULEDAT.PROV_SPEC_CD_IND " +
					" FROM RULE_DAT RULEDAT,RULE RUL " +
					" WHERE RUL.RULE_ID = '"+ruleId+"' AND RULEDAT.RULE_ID(+) = RUL.RULE_ID " +
					" ORDER BY RULEDAT.RULE_SQNC_NBR ");
			
			UMRuleViewQuery umRuleViewQuery = new UMRuleViewQuery(dataSource,buffer.toString());
			ruleSequenceList = umRuleViewQuery.execute();
			if(null != ruleSequenceList){
				log.debug("Size of UM Rule View list : "+ruleSequenceList.size());
			}
		}
		return ruleSequenceList;
	}
	private final static class UMRuleViewQuery extends MappingSqlQuery{
		private UMRuleViewQuery(DataSource dataSource, String query){
			super(dataSource, query);
			compile();
		}
		// The row will be mapped to UMRule object.
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			UMRule umRule = new UMRule();
			umRule.setRuleSqncNbr(rs.getString("RULE_SQNC_NBR"));
			umRule.setExclsnInd(rs.getString("EXCLSN_IND"));
			umRule.setClmType(rs.getString("CLM_TYPE"));
			umRule.setPlcOfSrvc(rs.getString("PLC_OF_SRVC"));
			umRule.setPatLowAge(rs.getString("PAT_LOW_AGE"));
			umRule.setPatHighAge(rs.getString("PAT_HIGH_AGE"));
			umRule.setGndrCode(rs.getString("GNDR_CD"));
			umRule.setPrvdrId(rs.getString("PRVDR_ID"));
			umRule.setPrvdrSpcltyCd(rs.getString("PRVDR_SPCLTY_CD"));
			umRule.setBnftAccumNm(rs.getString("BNFT_ACCUM_NM"));
			umRule.setBnftAccumLmtAmt(rs.getString("BNFT_ACCUM_LMT_AMT"));
			umRule.setBnftAccumLmtNbr(rs.getString("BNFT_ACCUM_LMT_NBR"));
			umRule.setNtfyOnlyInd(rs.getString("NTFY_ONLY_IND"));
			umRule.setClnlRvwInd(rs.getString("CLNL_RVW_IND"));
			umRule.setDlrLmt(rs.getString("DLR_LMT"));
			umRule.setSerivceCd(rs.getString("SERIVCE_CD"));
			umRule.setGrpSt(rs.getString("GRP_ST"));
			umRule.setLenOfStay(rs.getString("LEN_OF_STAY"));
			umRule.setItsSpcltyCd(rs.getString("ITS_SPCLTY_CD"));
			umRule.setSrvcStrtDt(rs.getDate("SRVC_STRT_DT"));
			umRule.setSrvcEndDt(rs.getDate("SRVC_END_DT"));
			umRule.setMbrRelshpCde(rs.getString("MBR_RELSHP_CDE"));
			umRule.setPrcdrModfrCde(rs.getString("PRCDR_MODFR_CDE"));
			umRule.setEditCde1(rs.getString("EDIT_CDE_1"));
			umRule.setEditCde2(rs.getString("EDIT_CDE_2"));
			umRule.setPrvdrStCd(rs.getString("PRVDR_ST_CD"));
			umRule.setBillTypCd(rs.getString("BILL_TYP_CD"));
			umRule.setTtCd(rs.getString("TT_CD"));
			umRule.setAtchmtInd(rs.getString("ATCHMT_IND"));
			umRule.setPatMbrCd(rs.getString("PAT_MBR_CD"));
			umRule.setHospFcilCd(rs.getString("HOSP_FCIL_CD"));
			umRule.setDaysFromInjry(rs.getString("DAYS_FROM_INJRY"));
			umRule.setDaysFromIlns(rs.getString("DAYS_FROM_ILNS"));
			umRule.setHmoClsCd(rs.getString("HMO_CLS_CD"));
			umRule.setTotChrgSign(rs.getString("TOT_CHRG_SIGN"));
			umRule.setTotChargeAmount(rs.getString("TOT_CHARGE_AMOUNT"));
			umRule.setWpdDelFlag(rs.getString("WPD_DEL_FLAG"));
			umRule.setCpayInd(rs.getString("CPAY_IND"));
			umRule.setHndrdPctInd(rs.getString("HNDRD_PCT_IND"));
			umRule.setMedcrAsgnInd(rs.getString("MEDCR_ASGN_IND"));
			umRule.setProcMdfrCd2(rs.getString("PROC_MDFR_CD2"));
			umRule.setSprtHcpsInd(rs.getString("SPRT_HCPS_IND"));
			umRule.setDiagInd(rs.getString("DIAG_IND"));
			umRule.setPcpInd(rs.getString("PCP_IND"));
			
			umRule.setPvdrLicId(rs.getString("PVDR_LIC_ID"));
			umRule.setPvdrMdcrId(rs.getString("PVDR_MDCR_ID"));
			umRule.setBlngPvdrNr(rs.getString("BLNG_PVDR_NR"));
			umRule.setRndrPvdrNr(rs.getString("RNDR_PVDR_NR"));
			umRule.setBlngNpi(rs.getString("BLNG_NPI"));
			umRule.setRndrNpi(rs.getString("RNDR_NPI")); 
			umRule.setElgblExpnsSignCd(rs.getString("ELGBL_EXPNS_SIGN_CD"));
			umRule.setElgblExpnsAmt(rs.getString("ELGBL_EXPNS_AMT"));
			umRule.setAgeTypeCd(rs.getString("AGE_TYPE_CD"));
			umRule.setRuleVerNmbr(rs.getInt("RULE_VRSN_NBR"));
			umRule.setDrgCd(rs.getString("DRG_CD"));
			umRule.setProvSpecCdInd(rs.getString("PROV_SPEC_CD_IND")); 
			
			return umRule;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepository#viewRuleSequenceIndicators(java.lang.String)
	 */
	// Added new query which returns CodeSequences corresponding to a Rule ID //
	public List  viewRuleCodeSequenceIndicators(String ruleId,List ruleSequenceNumberList,int RuleVersion) {
		List ruleCodeSeqnceList = null;
		if(null!=ruleId && !"".equals(ruleId.trim())){
			ruleId = ruleId.trim();
			StringBuffer buffer = new StringBuffer();;
			buffer.append("SELECT RULE_ID ,  RULE_TYPE_CD, RULE_VRSN_NBR , RULE_SQNC_NBR ,"); 
			buffer.append(" CD_SQNC_NBR , LINE_HCPT_LOW_VAL, LINE_HCPT_HIGH_VAL, LINE_DIAG_LOW_VAL,");
			buffer.append(" LINE_DIAG_HIGH_VAL, LINE_REV_LOW_VAL, LINE_REV_HIGH_VAL,");
			buffer.append(" LINE_ICDP_LOW_VAL  , LINE_ICDP_HIGH_VAL  , LINE_SRVC_CLS_HIGH , LINE_SRVC_CLS_LOW ,");
			buffer.append(" LINE_LMT_CLS_HIGH , LINE_LMT_CLS_LOW  , LINE_ICDP_VRSN_IND , LINE_DIAG_VRSN_IND  ");
			buffer.append(" FROM RULE_CD_SQNC");
			buffer.append(" WHERE RULE_ID = '"+ruleId+"' AND RULE_VRSN_NBR='"+RuleVersion+"' ");
			buffer.append(" AND RULE_SQNC_NBR IN ( ");
			buffer.append(StringUtils.collectionToDelimitedString(ruleSequenceNumberList,",","'","'"));
			buffer.append(")");
			umRuleCodeSeqnceQuery umRuleCodeSeqnceQuery = new umRuleCodeSeqnceQuery(dataSource,buffer.toString());
			ruleCodeSeqnceList = umRuleCodeSeqnceQuery.execute();
			if(null != ruleCodeSeqnceList){
				log.debug("Size of UM Rule View list : "+ruleCodeSeqnceList.size());
			}
		}
		return ruleCodeSeqnceList;
	}
	private final static class umRuleCodeSeqnceQuery extends MappingSqlQuery{
		private umRuleCodeSeqnceQuery(DataSource dataSource, String query){
			super(dataSource, query);
			compile();
		}
		// The row will be mapped to UMRule object.
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			UMRuleCodeSequence umRuleCodeSequence = new UMRuleCodeSequence();
			umRuleCodeSequence.setRuleID(rs.getString("RULE_ID"));
			umRuleCodeSequence.setRuleTypeCd(rs.getString("RULE_TYPE_CD"));
			umRuleCodeSequence.setRuleVersn(rs.getString("RULE_VRSN_NBR"));
			umRuleCodeSequence.setRuleSqncNbr(rs.getString("RULE_SQNC_NBR"));
			umRuleCodeSequence.setCodeSqncNbr(rs.getString("CD_SQNC_NBR"));
			umRuleCodeSequence.setLineHcptLowVal(rs.getString("LINE_HCPT_LOW_VAL"));
			umRuleCodeSequence.setLineHcptHighVal(rs.getString("LINE_HCPT_HIGH_VAL"));
			umRuleCodeSequence.setLineDiagLowVal(rs.getString("LINE_DIAG_LOW_VAL"));
			umRuleCodeSequence.setLineDiagHighVal(rs.getString("LINE_DIAG_HIGH_VAL"));
			umRuleCodeSequence.setLineRevLowVal(rs.getString("LINE_REV_LOW_VAL"));
			umRuleCodeSequence.setLineRevHighVal(rs.getString("LINE_REV_HIGH_VAL"));
			umRuleCodeSequence.setLineIcdpLowVal(rs.getString("LINE_ICDP_LOW_VAL"));
			umRuleCodeSequence.setLineIcdpHighVal(rs.getString("LINE_ICDP_HIGH_VAL"));
			umRuleCodeSequence.setLineSrvcClsHigh(rs.getString("LINE_SRVC_CLS_HIGH"));
			umRuleCodeSequence.setLineSrvcClsLow(rs.getString("LINE_SRVC_CLS_LOW"));
			umRuleCodeSequence.setLineLmtClsHigh(rs.getString("LINE_LMT_CLS_HIGH"));
			umRuleCodeSequence.setLineLmtClsLow(rs.getString("LINE_LMT_CLS_LOW"));
			umRuleCodeSequence.setLineIcdVrsnIndicator(rs.getString("LINE_ICDP_VRSN_IND"));
			umRuleCodeSequence.setLineDiagVrsnIndicator(rs.getString("LINE_DIAG_VRSN_IND"));
			
			return umRuleCodeSequence;
		}
		
	}
	
	// Added new query which returns ClaimLevelSequences corresponding to a Rule ID //
	public List  viewClaimLevelSequenceIndicators(String ruleId,List ruleSequenceNumberList,int RuleVersion) {
		List claimLevelSeqnceList = null;
		if(null!=ruleId && !"".equals(ruleId.trim())){
			ruleId = ruleId.trim();
			StringBuffer buffer = new StringBuffer();;
			buffer.append("Select RULE_ID   ,  RULE_TYPE_CD, RULE_VRSN_NBR , RULE_SQNC_NBR ,"); 
			buffer.append(" CLM_SQNC_NBR , CLM_SRVC_CD	, CLM_PROC_MDFR_CD1 , CLM_PROC_MDFR_CD2 ,");
			buffer.append(" CLM_TT_CD, CLM_POS_CD	, CLM_HMO_CLS_CD, CLM_SAME_DAY_SVC_IND ,");
			buffer.append(" CLM_SPRTG_PROC_CD_IND, CLM_DIAG_IND");
			buffer.append(" FROM RULE_CLM_SQNC");
			buffer.append(" WHERE RULE_ID = '"+ruleId+"' AND RULE_VRSN_NBR='"+RuleVersion+"' ");
			buffer.append(" AND RULE_SQNC_NBR IN ( ");
			buffer.append(StringUtils.collectionToDelimitedString(ruleSequenceNumberList,",","'","'"));
			buffer.append(")");
			
			
			umRuleClaimLevelViewQuery umRuleClaimLevelViewQuery = new umRuleClaimLevelViewQuery(dataSource,buffer.toString());
			claimLevelSeqnceList = umRuleClaimLevelViewQuery.execute();
			if(null != claimLevelSeqnceList){
				log.debug("Size of UM Rule View list : "+claimLevelSeqnceList.size());
			}
		}
		return claimLevelSeqnceList;
	}
	private final static class umRuleClaimLevelViewQuery extends MappingSqlQuery{
		private umRuleClaimLevelViewQuery(DataSource dataSource, String query){
			super(dataSource, query);
			compile();
		}
		// The row will be mapped to UMRule object.
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			ClaimLevelSequence claimLevelSequence = new ClaimLevelSequence();
			claimLevelSequence.setRuleID(rs.getString("RULE_ID"));
			claimLevelSequence.setRuleTypeCd(rs.getString("RULE_TYPE_CD"));
			claimLevelSequence.setRuleVersn(rs.getString("RULE_VRSN_NBR"));
			claimLevelSequence.setRuleSqncNbr(rs.getString("RULE_SQNC_NBR"));
			claimLevelSequence.setClaimSqncNbr(rs.getString("CLM_SQNC_NBR"));
			claimLevelSequence.setClaimServCd(rs.getString("CLM_SRVC_CD"));
			claimLevelSequence.setClaimPrcdrModfrCde1(rs.getString("CLM_PROC_MDFR_CD1"));
			claimLevelSequence.setClaimPrcdrModfrCde2(rs.getString("CLM_PROC_MDFR_CD2"));
			claimLevelSequence.setClaimTTCd(rs.getString("CLM_TT_CD"));
			claimLevelSequence.setClaimPOSCd(rs.getString("CLM_POS_CD"));
			claimLevelSequence.setClmHmoClsCd(rs.getString("CLM_HMO_CLS_CD"));
			claimLevelSequence.setClmSameDaySvcInd(rs.getString("CLM_SAME_DAY_SVC_IND"));
			claimLevelSequence.setClmSprtProcCdInd(rs.getString("CLM_SPRTG_PROC_CD_IND"));
			claimLevelSequence.setClaimDiagIndicator(rs.getString("CLM_DIAG_IND"));
			
			return claimLevelSequence;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepository#viewRuleSequenceIndicators(java.lang.String)
	 */
	// Added new query which returns ClaimCodeSequences corresponding to a Rule ID //

	public List  viewRuleClaimCodeSequenceIndicators(String ruleId,List ruleSequenceNumberList,int RuleVersion) {
		List ruleClaimCodeSeqnceList = null;
		if(null!=ruleId && !"".equals(ruleId.trim())){
			ruleId = ruleId.trim();
			StringBuffer buffer = new StringBuffer();;
			buffer.append("Select RULE_ID ,  RULE_TYPE_CD, RULE_VRSN_NBR, RULE_SQNC_NBR ,"); 
			buffer.append(" CLM_SQNC_NBR , CLM_CD_SQNC_NBR , CLM_HCPT_LOW_VAL, CLM_HCPT_HIGH_VAL , ");
			buffer.append(" CLM_DIAG_LOW_VAL, CLM_DIAG_HIGH_VAL , CLM_REV_LOW_VAL, CLM_REV_HIGH_VAL, ");
			buffer.append(" CLM_ICDP_LOW_VAL, CLM_ICDP_HIGH_VAL, CLM_SRVC_CLS_HIGH, CLM_SRVC_CLS_LOW ,");
			buffer.append(" CLM_LMT_CLS_HIGH, CLM_LMT_CLS_LOW , CLM_ICDP_VRSN_IND, CLM_DIAG_VRSN_IND   ");
			buffer.append(" FROM RULE_CLM_CD_SQNC");
			buffer.append(" WHERE RULE_ID = '"+ruleId+"' AND RULE_VRSN_NBR='"+RuleVersion+"' ");
			buffer.append(" AND RULE_SQNC_NBR IN ( ");
			buffer.append(StringUtils.collectionToDelimitedString(ruleSequenceNumberList,",","'","'"));
			buffer.append(")");
			
			
			umRuleClaimCodeSeqnceQuery umRuleClaimCodeSeqnceQuery = new umRuleClaimCodeSeqnceQuery(dataSource,buffer.toString());
			ruleClaimCodeSeqnceList = umRuleClaimCodeSeqnceQuery.execute();
			if(null != ruleClaimCodeSeqnceList){
				log.debug("Size of UM Rule View list : "+ruleClaimCodeSeqnceList.size());
			}
		}
		return ruleClaimCodeSeqnceList;
	}
	private final static class umRuleClaimCodeSeqnceQuery extends MappingSqlQuery{
		private umRuleClaimCodeSeqnceQuery(DataSource dataSource, String query){
			super(dataSource, query);
			compile();
		}
		// The row will be mapped to UMRule object.
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			ClaimCodeSequence claimCodeSequence = new ClaimCodeSequence();
			claimCodeSequence.setRuleID(rs.getString("RULE_ID"));
			claimCodeSequence.setRuleTypeCd(rs.getString("RULE_TYPE_CD"));
			claimCodeSequence.setRuleVersn(rs.getString("RULE_VRSN_NBR"));
			claimCodeSequence.setRuleSqncNbr(rs.getString("RULE_SQNC_NBR"));
			claimCodeSequence.setClaimSqncNbr(rs.getString("CLM_SQNC_NBR"));
			claimCodeSequence.setClaimCodeSqncNbr(rs.getString("CLM_CD_SQNC_NBR"));
			claimCodeSequence.setClaimHcptLowVal(rs.getString("CLM_HCPT_LOW_VAL"));
			claimCodeSequence.setClaimHcptHighVal(rs.getString("CLM_HCPT_HIGH_VAL"));
			claimCodeSequence.setClaimDiagLowVal(rs.getString("CLM_DIAG_LOW_VAL"));
			claimCodeSequence.setClaimDiagHighVal(rs.getString("CLM_DIAG_HIGH_VAL"));
			claimCodeSequence.setClaimRevLowVal(rs.getString("CLM_REV_LOW_VAL"));
			claimCodeSequence.setClaimRevHighVal(rs.getString("CLM_REV_HIGH_VAL"));
			claimCodeSequence.setClaimIcdpLowVal(rs.getString("CLM_ICDP_LOW_VAL"));
			claimCodeSequence.setClaimIcdpHighVal(rs.getString("CLM_ICDP_HIGH_VAL"));
			claimCodeSequence.setClaimSrvcClsHigh(rs.getString("CLM_SRVC_CLS_HIGH"));
			claimCodeSequence.setClaimSrvcClsLow(rs.getString("CLM_SRVC_CLS_LOW"));
			claimCodeSequence.setClaimLmtClsHigh(rs.getString("CLM_LMT_CLS_HIGH"));
			claimCodeSequence.setClaimLmtClsLow(rs.getString("CLM_LMT_CLS_LOW"));
			claimCodeSequence.setClaimIcdVrsnIndicator(rs.getString("CLM_ICDP_VRSN_IND"));
			claimCodeSequence.setClaimDiagVrsnIndicator(rs.getString("CLM_DIAG_VRSN_IND"));
			
			return claimCodeSequence;
		}
		
	}
	
	public List findMatchingAccumulator(String svcCode, String name){
		List accumList = null;
		boolean svcCodeEmpty = false;
		StringBuffer selectQuery = new StringBuffer();
		selectQuery.append("SELECT * FROM ( ");
		selectQuery.append(" SELECT");
		selectQuery.append(" SVC_CDE ,NAME ,ROOT_CASE_FLG ,ROOT_SUBS_CERT_FLG ,ROOT_MBR_CDE ,FROM_DT_FLG ,");
		selectQuery.append(" THRU_DT_FLG ,LOOKUP_IND ,ADTL_KEY ,OCCURS_FLG ,DAYS_FLG ,MONIES_FLG ,IMAGE_IND ,");
		selectQuery.append(" M204_IND ,AION_IND ,MANUEL_IND ,ADJUD_SRT_CDE ,GRP_SRT_SEQ, SYSTEM ");
		selectQuery.append(" from BX_ACCUMULATOR_VAL");
		if((svcCode != null && svcCode.trim().length() > 0) 
				|| (name != null && name.trim().length() > 0 )){
			selectQuery.append(" where ");
		}
		if(svcCode != null && svcCode.trim().length() > 0){
			svcCodeEmpty = true;
			svcCode = svcCode.replaceAll("\'","\'\'");
			svcCode = " '%"+svcCode.trim()+"%' ";
			svcCode = svcCode.toUpperCase();
			selectQuery.append("SVC_CDE LIKE ");
			selectQuery.append(svcCode);
		}
		if(name != null && name.trim().length() > 0) {
			if(svcCodeEmpty){
				selectQuery.append(" AND ");
			}
			name = name.replaceAll("\'","\'\'");
			name = " '%"+name.trim()+"%' ";
			name = name.toUpperCase();
			selectQuery.append("NAME LIKE ");
			selectQuery.append(name);
		}
		selectQuery.append(" ORDER BY GRP_SRT_SEQ ");
		selectQuery.append(") WHERE ROWNUM < 51");
		log.debug("Query : " + selectQuery.toString());
		AccumulatorQuery accumulatorQuery = new AccumulatorQuery(dataSource, selectQuery.toString());
		accumList = accumulatorQuery.execute();
		return accumList;
	}
	/**
	 * The method retrieve all the accum values 
	 * from BX_ACCUMULATOR_VAL table. 
	 * @param rowCount
	 * @return List<Accumulator>
	 */
	public List getAvailableAccumulators(int rowCount){
		List accumList = null;
		StringBuffer getAllAccumQuery = new StringBuffer();
		getAllAccumQuery.append("SELECT * FROM ( SELECT");
		getAllAccumQuery.append(" SVC_CDE ,NAME ,ROOT_CASE_FLG ,ROOT_SUBS_CERT_FLG ,ROOT_MBR_CDE ,FROM_DT_FLG ,");
		getAllAccumQuery.append(" THRU_DT_FLG ,LOOKUP_IND ,ADTL_KEY ,OCCURS_FLG ,DAYS_FLG ,MONIES_FLG ,IMAGE_IND ,");
		getAllAccumQuery.append(" M204_IND ,AION_IND ,MANUEL_IND ,ADJUD_SRT_CDE ,GRP_SRT_SEQ, SYSTEM");
		getAllAccumQuery.append(" FROM BX_ACCUMULATOR_VAL ORDER BY GRP_SRT_SEQ )where rownum < ");
		getAllAccumQuery.append(rowCount);
		AccumulatorQuery accumulatorQuery = new AccumulatorQuery(dataSource, getAllAccumQuery.toString());
		accumList = accumulatorQuery.execute();
		return accumList;
	}
	/**
	 * The methid retrieve all the accum values 
	 * from BX_ACCUMULATOR_VAL table. 
	 * @param rowCount
	 * @return List<Accumulator>
	 */
	public List getAccumulators(Set accumHippaSegmentList, String system){
		List accumList = null;
		List accum= new ArrayList(accumHippaSegmentList);
		StringBuffer getAllAccumQuery = new StringBuffer();
			getAllAccumQuery.append("SELECT");
			getAllAccumQuery.append(" SVC_CDE ,NAME ,ROOT_CASE_FLG ,ROOT_SUBS_CERT_FLG ,ROOT_MBR_CDE ,FROM_DT_FLG ,");
			getAllAccumQuery.append(" THRU_DT_FLG ,LOOKUP_IND ,ADTL_KEY ,OCCURS_FLG ,DAYS_FLG ,MONIES_FLG ,IMAGE_IND ,");
			getAllAccumQuery.append(" M204_IND ,AION_IND ,MANUEL_IND ,ADJUD_SRT_CDE ,GRP_SRT_SEQ, SYSTEM");
			getAllAccumQuery.append(" FROM BX_ACCUMULATOR_VAL WHERE SVC_CDE in (");
			if(!accum.isEmpty() && null!=accum){
			for(int i =0;i<accum.size();i++){
				if(i==accum.size()-1){
					getAllAccumQuery.append("\'"+accum.get(i).toString().trim()+"\'");
				}else{
					getAllAccumQuery.append("\'"+accum.get(i).toString().trim()+"\',");	
				}
			}
			}
			else{
				getAllAccumQuery.append("\'"+"\'");
			}
			getAllAccumQuery.append(")");
			getAllAccumQuery.append("AND UPPER (TRIM(SYSTEM)) IN(");
			if(system.equals("LG")){
				getAllAccumQuery.append("'LG','SENIOR FACETS'");
			}else if(system.equals("ISG")){
				getAllAccumQuery.append("'ISG'");
			}
			getAllAccumQuery.append(")ORDER BY GRP_SRT_SEQ ");
		AccumulatorQuery accumulatorQuery = new AccumulatorQuery(dataSource, getAllAccumQuery.toString());
		log.debug("AllAccumQuery "+getAllAccumQuery.toString());
		accumList = accumulatorQuery.execute();
		return accumList;
	}

	
	private class AccumulatorQuery extends MappingSqlQuery {

		private AccumulatorQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		/* (non-Javadoc)
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet, int)
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			Accumulator accumulator = new Accumulator();
			accumulator.setAdjudSrtCde(rs.getString("ADJUD_SRT_CDE"));
			accumulator.setAdtlKey(rs.getString("ADTL_KEY"));
			accumulator.setAionInd(rs.getString("AION_IND"));
			accumulator.setDaysFlg(rs.getString("DAYS_FLG"));
			accumulator.setFromDtFlg(rs.getString("FROM_DT_FLG"));
			accumulator.setGrpSrtSeq(rs.getString("GRP_SRT_SEQ"));
			accumulator.setImageInd(rs.getString("IMAGE_IND"));
			accumulator.setLookupInd(rs.getString("LOOKUP_IND"));
			accumulator.setM204Ind(rs.getString("M204_IND"));
			accumulator.setManualInd(rs.getString("MANUEL_IND"));
			accumulator.setMoniesFlg(rs.getString("MONIES_FLG"));
			accumulator.setName(rs.getString("NAME"));
			accumulator.setOccursFlg(rs.getString("OCCURS_FLG"));
			accumulator.setRootCaseFlg(rs.getString("ROOT_CASE_FLG"));
			accumulator.setRootMbrCde(rs.getString("ROOT_MBR_CDE"));
			accumulator.setRootSubsCertFlg(rs.getString("ROOT_SUBS_CERT_FLG"));
			accumulator.setSvcCode(rs.getString("SVC_CDE"));
			accumulator.setThruDtFlg(rs.getString("THRU_DT_FLG"));
			accumulator.setSystem(rs.getString("SYSTEM"));
			return accumulator;
		}
	}
/*Modified existing query to get Rule version and TAG fields from RULE table */
	public UMRule fetchRuleInfo(String ruleId) {
		List ruleInfo = null;
		UMRule umRuleObj = null;
		if(null!=ruleId && !"".equals(ruleId.trim())){
			ruleId = ruleId.trim();
			StringBuffer buffer = new StringBuffer();;
			buffer.append("SELECT RULE_ID, RULE_SHRT_DESC,RULE_GRP_IND,TAG,RULE_VRSN_NBR,RULE_LONG_DESC" );
			buffer.append(" FROM RULE WHERE RULE_ID = '"+ruleId+"'" );
			buffer.append(" and RULE_VRSN_NBR=(SELECT MAX(RULE_VRSN_NBR) FROM RULE WHERE RULE_ID = '"+ruleId+"')" );
			RuleInfoQuery ruleInfoQuery = new RuleInfoQuery(dataSource,buffer.toString());
			ruleInfo = ruleInfoQuery.execute();
			if(null != ruleInfo){
				log.debug("Size of UM Rule View list : "+ruleInfo.size());
				umRuleObj = (UMRule)ruleInfo.get(0);
			}
		}
		return umRuleObj;
	}
	private final static class RuleInfoQuery extends MappingSqlQuery{
		private RuleInfoQuery(DataSource dataSource, String query){
			super(dataSource, query);
			compile();
		}
		// The row will be mapped to UMRule object.
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			UMRule umRule = new UMRule();
			umRule.setHippaSegment(new HippaSegment());
			umRule.getHippaSegment().setName(rs.getString("RULE_ID"));
			umRule.getHippaSegment().setDescription(rs.getString("RULE_SHRT_DESC"));
			umRule.setTag(rs.getString("TAG"));
			if(rs.getString("RULE_VRSN_NBR")!=null)
			umRule.setRuleVerNmbr(Integer.parseInt(rs.getString("RULE_VRSN_NBR")));
			umRule.setRuleGrpInd(rs.getString("RULE_GRP_IND"));
			umRule.setLongDescription(rs.getString("RULE_LONG_DESC"));
			return umRule;
		}
	}

	public List viewGrpRule(String ruleId) {
		List grpRuleInfoList = new ArrayList();
		if(null!=ruleId && !"".equals(ruleId.trim())){
			ruleId = ruleId.trim();
			StringBuffer buffer = new StringBuffer();;
			buffer.append("SELECT A.RULE_ID, A.RULE_SHRT_DESC,A.TAG AS TAG,A.RULE_VRSN_NBR,A.RULE_GRP_IND AS RULE_GRP_IND,A.RULE_LONG_DESC" );
			buffer.append(" FROM RULE A, GRP_RULE B" );
			buffer.append(" WHERE A.RULE_ID = B.RULE_ID AND B.WPD_DEL_FLAG = 'N'");
			buffer.append(" AND B.GRP_RULE_ID = '"+ruleId+"' ORDER BY A.RULE_ID,A.RULE_SHRT_DESC ASC");
			
			RuleInfoQuery grpRulesQuery = new RuleInfoQuery(dataSource,buffer.toString());
			grpRuleInfoList = grpRulesQuery.execute();
			if(null != grpRuleInfoList){
				log.debug("Size of UM Rule View list : "+grpRuleInfoList.size());
			}
		}
		return grpRuleInfoList;
	}
	
	/**
	 * This function returns the associated Benefit structure of a variable
	 * @param Variable object
	 * @param noOfRecords
	 * @return variableBenefitStructureList
	 */
	public List<String> getVariableBenefitStructure(Variable variable){
		
		StringBuffer benefitStructureQuery = new StringBuffer();
		benefitStructureQuery.append(" SELECT BENEFIT_STRUCTURE  VARIABLE_BNFT_STRUCTURE FROM LG_CONTRACT_DETAIL WHERE CONTRACT_VAR = '");
		benefitStructureQuery.append(variable.getVariableId());
		benefitStructureQuery.append("' ");
		benefitStructureQuery.append(" UNION ");
		benefitStructureQuery.append(" SELECT CPFXP_STRUCT VARIABLE_BNFT_STRUCTURE FROM ISG_CPFXP_CONTXREF WHERE CPFXP_CONTVAR = '");
		benefitStructureQuery.append(variable.getVariableId());
		benefitStructureQuery.append("' ");
		
		VariableBenefitStructureQuery variableBenefitStructureQuery = new VariableBenefitStructureQuery(dataSource,benefitStructureQuery.toString());
		
		List<String> variableBenefitStructureList = new ArrayList<String>();
		variableBenefitStructureList = variableBenefitStructureQuery.execute();
		return variableBenefitStructureList;
	}
	private final static class VariableBenefitStructureQuery extends MappingSqlQuery{
		private VariableBenefitStructureQuery(DataSource dataSource, String query){
			super(dataSource, query);
			compile();
		}
		// The row will be mapped to UMRule object.
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			return rs.getString("VARIABLE_BNFT_STRUCTURE");
		}
	}
	/**
	 * This function returns the age tier variables associated to the corresponding benefit structure and of format 'AGE'
	 * @param variableBenefitStructure,searchString
	 * @return ageTierVariableList
	 */
	public List<Variable> getAgeTierVariables(String variableBenefitStructure, String searchString){
		StringBuffer ageTierVariableQuery = new StringBuffer();
		ageTierVariableQuery.append(" SELECT CONTVAR.CONTRACT_VAR VAR, CONTVAR.CONT_VAR_TX  VARDESC ");
		ageTierVariableQuery.append(" FROM CONTVAR  CONTVAR, LG_CONTRACT_DETAIL CONTRACT_DETAIL  ");
		ageTierVariableQuery.append(" WHERE CONTVAR.CONTRACT_VAR_FORMAT = 'AGE'  ");
		ageTierVariableQuery.append(" AND CONTVAR.CONTRACT_VAR = CONTRACT_DETAIL.CONTRACT_VAR  ");
		if(null != variableBenefitStructure && !("").equals(variableBenefitStructure)){
			ageTierVariableQuery.append(" AND CONTRACT_DETAIL.benefit_structure IN(");
			ageTierVariableQuery.append(variableBenefitStructure);
			ageTierVariableQuery.append(") ");
		}
		if(null != searchString && !("").equals(searchString)){
			ageTierVariableQuery.append(" AND CONTVAR.CONT_VAR_TX LIKE'%");
			ageTierVariableQuery.append(searchString);
			ageTierVariableQuery.append("%' ");
		}
		ageTierVariableQuery.append(" UNION ");
		ageTierVariableQuery.append(" SELECT CONTVAR.CPFXP_CONTVAR VAR, CONTVAR.CPFXP_TEXT VARDESC ");
		ageTierVariableQuery.append(" FROM ISG_CPFXP_CONTVAR CONTVAR, ISG_CPFXP_CONTXREF CONTXREF ");
		ageTierVariableQuery.append(" WHERE CONTVAR.CPFXP_FORMAT = 'AGE' ");
		ageTierVariableQuery.append(" AND CONTVAR.CPFXP_CONTVAR =  CONTXREF.CPFXP_CONTVAR ");
		if(null != variableBenefitStructure && !("").equals(variableBenefitStructure)){
			ageTierVariableQuery.append(" AND CONTXREF.CPFXP_STRUCT IN(");
			ageTierVariableQuery.append(variableBenefitStructure);
			ageTierVariableQuery.append(") ");
		}
		if(null != searchString && !("").equals(searchString)){
			ageTierVariableQuery.append(" AND CONTVAR.CPFXP_TEXT LIKE'%");
			ageTierVariableQuery.append(searchString);
			ageTierVariableQuery.append("%' ");
		}
		AgeTiervariablesQuery ageTiervariablesQuery = new AgeTiervariablesQuery(dataSource,ageTierVariableQuery.toString());
		List<Variable> ageTierVariableList = new ArrayList<Variable>();
		ageTierVariableList = ageTiervariablesQuery.execute();
		return ageTierVariableList;
	}
	
	private final static class AgeTiervariablesQuery extends MappingSqlQuery{
		private AgeTiervariablesQuery(DataSource dataSource, String query){
			super(dataSource, query);
			compile();
		}
		// The row will be mapped to UMRule object.
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			Variable variable = new Variable();
			variable.setVariableId(rs.getString("VAR"));
			variable.setVariableDescritpion(rs.getString("VARDESC"));
			return variable;
		}
	}
	

	// SSCR19537 April04 - Start
   
    public  Map<String,String> fetchHippaSegmentDescription(String hippaSegmentName){
    	Map<String, String> hippaSegmentValueMap = new HashMap<String, String>();
		if(null != hippaSegmentName && !hippaSegmentName.equalsIgnoreCase("")){
			int noOfRecords = 0;
			
			List<HippaCodeValue> hippaCodeList = findMatchingHippaCodeValuesFromDataDictionary(
					hippaSegmentName, null, noOfRecords);
			for (int count = 0; count < hippaCodeList.size(); count++) {
	
				HippaCodeValue codeValue = (HippaCodeValue) hippaCodeList.get(count);
				hippaSegmentValueMap.put(codeValue.getValue(), codeValue.getDescription());
				
			}
		}
		return hippaSegmentValueMap;
	}

	public int getEBSegmentsCount(String variableId) {
		StringBuffer EBSegmentQuery = new StringBuffer();
		EBSegmentQuery.append("SELECT count(*)");
		EBSegmentQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL WHERE VAR_MAPG_SYS_ID IN(");
		EBSegmentQuery.append("SELECT VAR_MAPG_SYS_ID FROM BX_CNTRCT_VAR_MAPG WHERE VAR_MAPG_STTS_CD NOT IN (");
		EBSegmentQuery.append("'NOT_APPLICABLE','BUILDING') AND CNTRCT_VAR_ID ='");
		EBSegmentQuery.append(variableId.trim());
		EBSegmentQuery.append("') AND DATA_ELEMENT_ID LIKE 'EB%'");
		
		EBVariableQuery ebVariableQuery = new EBVariableQuery(dataSource, EBSegmentQuery.toString());
		List<Integer> ebVarcount = new ArrayList<Integer>();
		ebVarcount = ebVariableQuery.execute();
		if(ebVarcount.get(0)>0)
			return ebVarcount.get(0);
		else{
			StringBuffer EBSegBuildingStatusQuery = new StringBuffer();
			EBSegBuildingStatusQuery.append("SELECT count(*) FROM BX_CNTRCT_VAR_MAPG_VAL WHERE VAR_MAPG_SYS_ID IN(SELECT VAR_MAPG_SYS_ID FROM BX_CNTRCT_VAR_MAPG WHERE VAR_MAPG_STTS_CD IN ('BUILDING') AND CNTRCT_VAR_ID ='");
			EBSegBuildingStatusQuery.append(variableId.trim());
			EBSegBuildingStatusQuery.append("') AND DATA_ELEMENT_ID LIKE 'EB%'");
			ebVariableQuery = new EBVariableQuery(dataSource, EBSegBuildingStatusQuery.toString());
			List<Integer> ebVarcount1 = new ArrayList<Integer>();
			ebVarcount1 = ebVariableQuery.execute();
			if(ebVarcount1.get(0)>0){
				//Check for already data in audit trail table.
				StringBuffer EBAuditTrailQuery = new StringBuffer();
				EBAuditTrailQuery.append("SELECT COUNT(*) FROM BX_CNTRCT_VAR_AUDIT_TRAIL WHERE CNTRCT_VAR_ID IN ('");
				EBAuditTrailQuery.append(variableId.trim());
				EBAuditTrailQuery.append("') AND MAPG_STATUS IN ('OBJECT TRANSFERRED','OBJECT_TRANSFERRED','PUBLISHED')");
				ebVariableQuery = new EBVariableQuery(dataSource, EBAuditTrailQuery.toString());
				List<Integer> ebVarco = new ArrayList<Integer>();
				ebVarco = ebVariableQuery.execute();
				return ebVarco.get(0); 	
			}else{
				return 0;
			}
		}
	}
	
	public int getEBSegmentsTempCount(String variableId) {
		StringBuffer EBSegmentTempQuery = new StringBuffer();
		EBSegmentTempQuery.append("SELECT count(*)");
		EBSegmentTempQuery.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL WHERE VAR_MAPG_SYS_ID IN(");
		EBSegmentTempQuery.append("SELECT VAR_MAPG_SYS_ID FROM TEMP_BX_CNTRCT_VAR_MAPG WHERE VAR_MAPG_STTS_CD NOT IN (");
		EBSegmentTempQuery.append("'NOT_APPLICABLE','BUILDING') AND CNTRCT_VAR_ID ='");
		EBSegmentTempQuery.append(variableId.trim());
		EBSegmentTempQuery.append("') AND DATA_ELEMENT_ID LIKE 'EB%'");
		
		EBVariableQuery ebVariableTestQuery = new EBVariableQuery(dataSource, EBSegmentTempQuery.toString());
		List<Integer> ebVarcount = new ArrayList<Integer>();
		ebVarcount = ebVariableTestQuery.execute();
		if(ebVarcount.get(0)>0)
			return ebVarcount.get(0);
		else{
			StringBuffer EBSegBuildingStatusTempQuery = new StringBuffer();
			EBSegBuildingStatusTempQuery.append("SELECT count(*) FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL WHERE VAR_MAPG_SYS_ID IN(SELECT VAR_MAPG_SYS_ID FROM TEMP_BX_CNTRCT_VAR_MAPG WHERE VAR_MAPG_STTS_CD IN ('BUILDING') AND CNTRCT_VAR_ID ='");
			EBSegBuildingStatusTempQuery.append(variableId.trim());
			EBSegBuildingStatusTempQuery.append("') AND DATA_ELEMENT_ID LIKE 'EB%'");
			ebVariableTestQuery = new EBVariableQuery(dataSource, EBSegBuildingStatusTempQuery.toString());
			List<Integer> ebVarTempcount1 = new ArrayList<Integer>();
			ebVarTempcount1 = ebVariableTestQuery.execute();
			if(ebVarTempcount1.get(0)>0){
				//Check for already data in audit trail table.
				StringBuffer EBAuditTrailQuery = new StringBuffer();
				EBAuditTrailQuery.append("SELECT COUNT(*) FROM BX_CNTRCT_VAR_AUDIT_TRAIL WHERE CNTRCT_VAR_ID IN ('");
				EBAuditTrailQuery.append(variableId.trim());
				EBAuditTrailQuery.append("') AND MAPG_STATUS IN ('OBJECT TRANSFERRED','OBJECT_TRANSFERRED','PUBLISHED')");
				ebVariableTestQuery = new EBVariableQuery(dataSource, EBAuditTrailQuery.toString());
				List<Integer> ebVarTempco = new ArrayList<Integer>();
				ebVarTempco = ebVariableTestQuery.execute();
				return ebVarTempco.get(0); 	
			}else{
				return 0;
			}
		}
	}
	
	private final static class EBVariableQuery extends MappingSqlQuery{
		private EBVariableQuery(DataSource dataSource, String query){
			super(dataSource, query);
			compile();
		}

		
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			int val = rs.getInt(1);
			return val;
		}
	}
	public String getEBMappingAssocDetails(String variableId){
		StringBuffer EBMappingQuery = new StringBuffer();
		EBMappingQuery.append("SELECT CNTRCT_VAR_ID FROM BX_CNTRCT_VAR_MAPG WHERE VAR_MAPG_STTS_CD IN ('PUBLISHED','OBJECT_TRANSFERRED','BEING_MODIFIED','SCHEDULED_TO_TEST') AND VAR_MAPG_SYS_ID IN");
		EBMappingQuery.append("(SELECT VAR_MAPG_SYS_ID FROM BX_CNTRCT_VAR_MAPG_VAL WHERE DATA_ELEMENT_ID = 'HSD02' AND DATA_ELEMENT_VAL = '");
		EBMappingQuery.append(variableId);
		EBMappingQuery.append("') ");
		/*EBMappingQuery.append("UNION SELECT CNTRCT_VAR_ID FROM BX_CNTRCT_VAR_MAPG WHERE VAR_MAPG_STTS_CD IN ('BEING_MODIFIED') AND VAR_MAPG_SYS_ID IN");
		EBMappingQuery.append("(SELECT VAR_MAPG_SYS_ID FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL WHERE DATA_ELEMENT_ID = 'HSD02' AND DATA_ELEMENT_VAL = '");
		EBMappingQuery.append(variableId);
		EBMappingQuery.append("')");*/
		
		EBMappingQuery ebMappingQuery = new EBMappingQuery(dataSource, EBMappingQuery.toString());
		List<String> ebVariable = new ArrayList<String>();
		try{
			ebVariable = ebMappingQuery.execute();
			if(null != ebVariable){
				return ebVariable.toString();
			}else {
				StringBuffer EBMappingBuildSttsQuery = new StringBuffer();
				EBMappingBuildSttsQuery.append("SELECT CNTRCT_VAR_ID FROM BX_CNTRCT_VAR_MAPG WHERE VAR_MAPG_STTS_CD IN ('BUILDING') AND VAR_MAPG_SYS_ID IN");
				EBMappingBuildSttsQuery.append("(SELECT VAR_MAPG_SYS_ID FROM BX_CNTRCT_VAR_MAPG_VAL WHERE DATA_ELEMENT_ID = 'HSD02' AND DATA_ELEMENT_VAL = '");
				EBMappingBuildSttsQuery.append(variableId);
				EBMappingBuildSttsQuery.append("')");
				
				ebMappingQuery = new EBMappingQuery(dataSource, EBMappingBuildSttsQuery.toString());
				List<String> ebBuldSttsVariable = new ArrayList<String>();
				try{
					ebBuldSttsVariable = ebMappingQuery.execute();
					if(null != ebVariable || ebVariable.size()>0 || !ebVariable.isEmpty()){
						BxUtil.variablesFromBldngStts(ebVariable);
						StringBuffer HSD02AuditTrailQuery = new StringBuffer();
						HSD02AuditTrailQuery.append("SELECT COUNT(*) FROM BX_CNTRCT_VAR_AUDIT_TRAIL WHERE CNTRCT_VAR_ID IN ('");
						HSD02AuditTrailQuery.append(BxUtil.variablesFromBldngStts(ebBuldSttsVariable));
						HSD02AuditTrailQuery.append("') AND MAPG_STATUS IN ('OBJECT TRANSFERRED','OBJECT_TRANSFERRED','PUBLISHED')");
						ebMappingQuery = new EBMappingQuery(dataSource, HSD02AuditTrailQuery.toString());
						List<Integer> ebAudVarBldStts = new ArrayList<Integer>();
						ebAudVarBldStts = ebMappingQuery.execute();
						if(null != ebAudVarBldStts){
							return ebVariable.toString();
						}else{
							return "";
						}
						 	
					}	
				}catch(Exception e){
					e.printStackTrace();
					return "";
				}
				return "";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	public String getEBMappingAssocDetailsTemp(String variableId){
		StringBuffer EBMappingTestQuery = new StringBuffer();
		EBMappingTestQuery.append("SELECT CNTRCT_VAR_ID FROM TEMP_BX_CNTRCT_VAR_MAPG WHERE VAR_MAPG_STTS_CD IN ('PUBLISHED','OBJECT_TRANSFERRED','BEING_MODIFIED','SCHEDULED_TO_TEST') AND VAR_MAPG_SYS_ID IN");
		EBMappingTestQuery.append("(SELECT VAR_MAPG_SYS_ID FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL WHERE DATA_ELEMENT_ID = 'HSD02' AND DATA_ELEMENT_VAL = '");
		EBMappingTestQuery.append(variableId);
		EBMappingTestQuery.append("') ");
		
		EBMappingQuery ebMappingTestQuery = new EBMappingQuery(dataSource, EBMappingTestQuery.toString());
		List<String> ebTempVariable = new ArrayList<String>();
		try{
			ebTempVariable = ebMappingTestQuery.execute();
			if(null != ebTempVariable){
				return ebTempVariable.toString();
			}else {
				StringBuffer EBMappingBuildSttsTempQuery = new StringBuffer();
				EBMappingBuildSttsTempQuery.append("SELECT CNTRCT_VAR_ID FROM TEST_BX_CNTRCT_VAR_MAPG WHERE VAR_MAPG_STTS_CD IN ('BUILDING') AND VAR_MAPG_SYS_ID IN");
				EBMappingBuildSttsTempQuery.append("(SELECT VAR_MAPG_SYS_ID TEMP_FROM BX_CNTRCT_VAR_MAPG_VAL WHERE DATA_ELEMENT_ID = 'HSD02' AND DATA_ELEMENT_VAL = '");
				EBMappingBuildSttsTempQuery.append(variableId);
				EBMappingBuildSttsTempQuery.append("')");
				
				ebMappingTestQuery = new EBMappingQuery(dataSource, EBMappingBuildSttsTempQuery.toString());
				List<String> ebBuldSttsVariable = new ArrayList<String>();
				try{
					ebBuldSttsVariable = ebMappingTestQuery.execute();
					if(null != ebTempVariable || ebTempVariable.size()>0 || !ebTempVariable.isEmpty()){
						BxUtil.variablesFromBldngStts(ebTempVariable);
						StringBuffer HSD02AuditTrailQuery = new StringBuffer();
						HSD02AuditTrailQuery.append("SELECT COUNT(*) FROM BX_CNTRCT_VAR_AUDIT_TRAIL WHERE CNTRCT_VAR_ID IN ('");
						HSD02AuditTrailQuery.append(BxUtil.variablesFromBldngStts(ebBuldSttsVariable));
						HSD02AuditTrailQuery.append("') AND MAPG_STATUS IN ('OBJECT TRANSFERRED','OBJECT_TRANSFERRED','PUBLISHED')");
						ebMappingTestQuery = new EBMappingQuery(dataSource, HSD02AuditTrailQuery.toString());
						List<Integer> ebAudVarBldSttsTemp = new ArrayList<Integer>();
						ebAudVarBldSttsTemp = ebMappingTestQuery.execute();
						if(null != ebAudVarBldSttsTemp){
							return ebTempVariable.toString();
						}else{
							return "";
						}
						 	
					}	
				}catch(Exception e){
					e.printStackTrace();
					return "";
				}
				return "";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	public boolean isMappingBeingModified(String varaiableId) {
		
		StringBuffer TempTabQuery = new StringBuffer();
		TempTabQuery.append("SELECT IN_TEMP_TAB FROM BX_CNTRCT_VAR_MAPG WHERE CNTRCT_VAR_ID = '");
		TempTabQuery.append(varaiableId);
		TempTabQuery.append("'");
		
		RetrieveMappingInTempFlag retrieveMappingInTempFlag = new RetrieveMappingInTempFlag(
				dataSource, TempTabQuery.toString());
		List<String> ebTempVariable = new ArrayList<String>();
		
		ebTempVariable = retrieveMappingInTempFlag.execute();

		if (null != ebTempVariable
				&& ebTempVariable.size() > 0) {
			log.debug("*****************variableList size : "
					+ ebTempVariable.size());
			for (String tempTabFlag : ebTempVariable) {
				if (tempTabFlag.equals("Y")) {
					return true;
				}
			}

		}
		
		return false;
	}
	
	private class RetrieveMappingInTempFlag extends MappingSqlQuery {

		public RetrieveMappingInTempFlag(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			//Mapping mapping = new Mapping();
			
			String tempTab = rs.getString(1);
			return tempTab;
		}
	}
	private final static class EBMappingQuery extends MappingSqlQuery{
		private EBMappingQuery(DataSource dataSource, String query){
			super(dataSource, query);
			compile();
		}

		
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			String variable = rs.getString(1);
			return variable;
		}
	}
	@Override
	public int getEBSegmentsCountIcon(String variableId) {
		// TODO Auto-generated method stub
		StringBuffer EBSegmentQuery = new StringBuffer();
		EBSegmentQuery.append("SELECT count(*)");
		EBSegmentQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL WHERE VAR_MAPG_SYS_ID IN(");
		EBSegmentQuery.append("SELECT VAR_MAPG_SYS_ID FROM BX_CNTRCT_VAR_MAPG WHERE ");
		EBSegmentQuery.append(" CNTRCT_VAR_ID ='");
		EBSegmentQuery.append(variableId.trim());
		EBSegmentQuery.append("') AND DATA_ELEMENT_ID LIKE 'EB%'");
		
		EBVariableQuery ebVariableQuery = new EBVariableQuery(dataSource, EBSegmentQuery.toString());
		List<Integer> ebVarcount = new ArrayList<Integer>();
		ebVarcount = ebVariableQuery.execute();
		if(ebVarcount.get(0)>0){
			return ebVarcount.get(0);
		}else{
			return 0;
		}
	}

	@Override
	public int getEBSegmentsTempCountIcon(String variableId) {
		// TODO Auto-generated method stub
		StringBuffer EBSegmentQuery = new StringBuffer();
		EBSegmentQuery.append("SELECT count(*)");
		EBSegmentQuery.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL WHERE VAR_MAPG_SYS_ID IN(");
		EBSegmentQuery.append("SELECT VAR_MAPG_SYS_ID FROM TEMP_BX_CNTRCT_VAR_MAPG WHERE ");
		EBSegmentQuery.append(" CNTRCT_VAR_ID ='");
		EBSegmentQuery.append(variableId.trim());
		EBSegmentQuery.append("') AND DATA_ELEMENT_ID LIKE 'EB%'");
		
		EBVariableQuery ebVariableQuery = new EBVariableQuery(dataSource, EBSegmentQuery.toString());
		List<Integer> ebVarcount = new ArrayList<Integer>();
		ebVarcount = ebVariableQuery.execute();
		if(ebVarcount.get(0)>0){
			return ebVarcount.get(0);
		}else{
			return 0;
		}	
		}

	@Override
	public String getEBMappingAssocDetailsIcon(String variableId) {
		// TODO Auto-generated method stub
		StringBuffer EBMappingQuery = new StringBuffer();
		EBMappingQuery.append("SELECT DATA_ELEMENT_VAL");
		EBMappingQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL WHERE VAR_MAPG_SYS_ID IN(");
		EBMappingQuery.append("SELECT VAR_MAPG_SYS_ID FROM BX_CNTRCT_VAR_MAPG WHERE ");
		EBMappingQuery.append(" CNTRCT_VAR_ID ='");
		EBMappingQuery.append(variableId.trim());
		EBMappingQuery.append("') AND DATA_ELEMENT_ID = 'HSD02'");
		
		EBMappingQuery ebMappingQuery = new EBMappingQuery(dataSource, EBMappingQuery.toString());
		List<String> ebVariable = new ArrayList<String>();
		try{
			ebVariable = ebMappingQuery.execute();
			if(null != ebVariable){
				return ebVariable.toString();
			}
			else{
				return "";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public String getEBMappingAssocDetailsTempIcon(String variableId) {
		// TODO Auto-generated method stub
		StringBuffer EBMappingQuery = new StringBuffer();
		EBMappingQuery.append("SELECT DATA_ELEMENT_VAL");
		EBMappingQuery.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL WHERE VAR_MAPG_SYS_ID IN(");
		EBMappingQuery.append("SELECT VAR_MAPG_SYS_ID FROM TEMP_BX_CNTRCT_VAR_MAPG WHERE ");
		EBMappingQuery.append(" CNTRCT_VAR_ID ='");
		EBMappingQuery.append(variableId.trim());
		EBMappingQuery.append("') AND DATA_ELEMENT_ID = 'HSD02'");
		
		EBMappingQuery ebMappingQuery = new EBMappingQuery(dataSource, EBMappingQuery.toString());
		List<String> ebVariable = new ArrayList<String>();
		try{
			ebVariable = ebMappingQuery.execute();
			if(null != ebVariable){
				return ebVariable.toString();
			}
			else{
				return "";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	}
}