package com.wellpoint.ets.ebx.mapping.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.object.MappingSqlQuery;
import com.wellpoint.ets.bx.mapping.domain.vo.VariableHdngVO;

public class VaribaleHeadings implements Runnable {

	public HashMap getSearchResultMapPart2() {
		return searchResultMapPart2;
	}
	public void setSearchResultMapPart2(HashMap searchResultMapPart2) {
		this.searchResultMapPart2 = searchResultMapPart2;
	}

	public static final Logger log = Logger.getLogger(VaribaleHeadings.class);
	
	HashMap searchResultMapPart2 = new HashMap();
	
	List searchResultListPart2 = new ArrayList();
	
	private DataSource dataSource;
	
	private String query;
	
	public VaribaleHeadings() {
		super();
	}
	/**
	 * 
	 * @param dataSource
	 */
	public VaribaleHeadings(DataSource dataSource, String query) {
		this.dataSource = dataSource;
		this.query = query;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run() Thread run method.
	 */
	public void run() {
		if (null != query) {
			GetExcelReportSearchRecordsVarQry2 advSearchVarQry2 = new GetExcelReportSearchRecordsVarQry2(
					dataSource, query);
			long starttime = System.currentTimeMillis();
			searchResultListPart2=advSearchVarQry2.execute();
			long endtime = System.currentTimeMillis();
			log.info(">>>>>>>>>> Time taken for advance search heading query execution is " + (endtime - starttime));
		}
	}
	
	private class GetExcelReportSearchRecordsVarQry2 extends MappingSqlQuery {
		
		private GetExcelReportSearchRecordsVarQry2(DataSource dataSource,
				String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			VariableHdngVO variableHdngVO = new VariableHdngVO();
			variableHdngVO.setVariableId(rs.getString("VAR"));
			variableHdngVO.setBenefitStructure(rs.getString("BENEFIT_STRUCTURE"));
			variableHdngVO.setMinorHeading(rs
						.getString("MINOR"));
			variableHdngVO.setMajorHeading(rs
						.getString("MAJOR"));
			//List headersList ;
			String variableId = null;
					variableId = variableHdngVO.getVariableId();
					if (null == searchResultMapPart2.get(variableId)) {
						//headersList = new ArrayList();
						searchResultMapPart2.put(variableId, variableHdngVO);
					}
					//headersList = (ArrayList) searchResultMapPart2.get(variableId);
					//headersList.add(variableHdngVO);
					
			return variableHdngVO;
			
		}
	}

	

}