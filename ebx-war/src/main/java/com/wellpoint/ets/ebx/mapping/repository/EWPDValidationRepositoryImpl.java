/*
 * <EWPDValidationRepositoryImpl.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.object.MappingSqlQuery;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
/**
 * @author UST-GLOBAL This is an implementation class for performing the 
 * validation on Rule and service type code
 */
public class EWPDValidationRepositoryImpl implements ValidationRepository {
	
	private DataSource dataSource;
	/**
	 * Method returns the List of valid values for RULE -EB03
	 * @param mapping
	 * @return List
	 */
	public List getServiceTypeCode(Mapping mapping) {
		String query = getQuery(mapping);
		
		RuleEb03Query ruleEb03Query = new RuleEb03Query(
				dataSource, query);
		List eb03List = ruleEb03Query.execute();
		
		return eb03List;
	}
	/**
	 * @author u22093 Inner class for the RULE EB03 query
	 *
	 */
	private class RuleEb03Query extends MappingSqlQuery {

		public RuleEb03Query(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new String(rs.getString("srvc_typ_code"));
		}
	}
	
	/**
	 * Method to determine the query for Rule , service type
	 * code validation
	 * @param mapping
	 * @return
	 */
	private String getQuery(Mapping mapping){
		
		String query = "";
		if(null != mapping.getRule().getHeaderRuleId()){
			query = "select srvc_typ_code from bx_rule_srvc_typ_vldn where rule_id = '" 
				+ mapping.getRule().getHeaderRuleId()+ "'";
		}		
		return query;
	}
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
