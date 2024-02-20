/*
 * <WPDValidationRepositoryImpl.java>
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
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
/**
 * @author UST-GLOBAL This is an implementation class for performing the 
 * validation on category code , service type code
 */
public class WPDValidationRepositoryImpl implements ValidationRepository {
	
	private DataSource dataSource;
	
	/**
	 * Method returns the List of valid values for category code - EB03 combination
	 * @param mapping
	 * @return List
	 */
	public List getServiceTypeCode(Mapping mapping) {
		
		String query = getQuery(mapping);
		
		CategoryCodeQuery categoryCodeEb03Qry = new CategoryCodeQuery(
				dataSource, query);
		List eb03List = categoryCodeEb03Qry.execute();
		
		return eb03List;
	}
	/**
	 * @author UST-GLOBAL This is an inner class for execcuting the query for
	 * validation on category code , service type code
	 */
	private class CategoryCodeQuery extends MappingSqlQuery {

		public CategoryCodeQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new String(rs.getString("eb03"));
		}
	}
	
	/**
	 * The query is determined based on the system = LG or ISG 
	 * @param mapping
	 * @return
	 */
	private String getQuery(Mapping mapping){
		
		String lgQuery ="";
		String isgQuery ="";
		String query = "";
		if(null != mapping.getVariable().getLgCatagory() && 
				((DomainConstants.WPD_LG.equals(mapping.getVariable().getVariableSystem())) || 
						(DomainConstants.WPD_LG_ISG.equals(mapping.getVariable().getVariableSystem())))){
			lgQuery = "select lg.srvc_typ_code eb03 from bx_category_srvc_typ_vldn lg where " +
							 " lg.category_code = '" + mapping.getVariable().getLgCatagory() + "'" +
							" and lg.system = 'LG' ";
		}
		if(null != mapping.getVariable().getIsgCatagory() && 
				((DomainConstants.WPD_ISG.equals(mapping.getVariable().getVariableSystem())) || 
						(DomainConstants.WPD_LG_ISG.equals(mapping.getVariable().getVariableSystem())))){
			
			isgQuery = "select isg.srvc_typ_code eb03 from bx_category_srvc_typ_vldn isg where " +
			 				  " isg.category_code = '" + mapping.getVariable().getIsgCatagory() + "'" +
			 				  " and isg.system = 'ISG' ";
		}
		
		if(DomainConstants.WPD_LG.equals(mapping.getVariable().getVariableSystem())){			
			query = lgQuery;			
		}
		else if(DomainConstants.WPD_ISG.equals(mapping.getVariable().getVariableSystem())){			
			query = isgQuery;
		}
		else if(DomainConstants.WPD_LG_ISG.equals(mapping.getVariable().getVariableSystem())){			
			query = lgQuery + " union " + isgQuery;
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
