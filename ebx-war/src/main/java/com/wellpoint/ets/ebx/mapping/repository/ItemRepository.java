/*
 * <ItemRepository.java>
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
import java.sql.Types;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.wellpoint.ets.bx.mapping.domain.entity.Catalog;
import com.wellpoint.ets.bx.mapping.domain.entity.Item;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;

/**
 * @author UST-GLOBAL
 * This class is used to retrieve the Hippa code values from ITEM and CATALOG tables
 *
 */
public class ItemRepository {
	
	private DataSource dataSource;
	/**
	 * for retrieving hipaa code values starting with the searchString. 
	 * Flag can be used to indicate whether to retrieve all the records or limit it to 50
	 * (depends on how many records we need to display)
	 * @param catalog
	 * @param searchString
	 * @param flag
	 * @return List
	 */
	public List getItems(Catalog catalog, String searchString, boolean limitFlag, List formats) {
		
		String itemQuery = "";
		List items = null;
		
		
		if(null!=catalog && null!=catalog.getCatalogName())
		{
			if(catalog.getCatalogName().equals(DomainConstants.EB01_NAME) && (null != formats)){
				
				itemQuery = itemQueryForEB01(searchString, limitFlag, formats);				
			}
			else{
				itemQuery = itemQuery(searchString, limitFlag);
			}
			
			items = executeItemQuery(dataSource, itemQuery, searchString, 
					catalog.getCatalogName(), formats, true);			
						
			if((null == items || items.isEmpty() || items.size() == 0) && 
					catalog.getCatalogName().equals(DomainConstants.EB01_NAME) && (null != formats)){				
				itemQuery = itemQuery(searchString, limitFlag);	
				items = executeItemQuery(dataSource, itemQuery, searchString, 
						catalog.getCatalogName(), formats, false);					
				
			}
			return items;
		}
		else
		{
			return null;
		}
	}
	/**
	 * method to set the parameter for executing the item query
	 * @param dataSource
	 * @param itemQuery
	 * @param searchString
	 * @param catalogName
	 * @param formats
	 * @param doFilter
	 * @return
	 */
	private List executeItemQuery(DataSource dataSource, String itemQuery, String searchString, 
					String catalogName, List formats, boolean doFilter){
		
		ItemQuery locateSPSIDItemQuery = new ItemQuery(
				dataSource, itemQuery, searchString, catalogName, formats, doFilter);
		
		if (null != searchString && !("".equalsIgnoreCase(searchString)) && 
				(catalogName.equals(DomainConstants.EB01_NAME)) && (null != formats) && (doFilter)){
			
			Object[] parameter = new Object[1];
			searchString = searchString + "%";
			parameter[0] = searchString;
			return locateSPSIDItemQuery.execute(parameter);
		}
		else if(null != searchString && !("".equalsIgnoreCase(searchString)) && 
				(catalogName.equals(DomainConstants.EB01_NAME)) && (null != formats) && (!doFilter)){
			Object[] parameter = new Object[2];
			parameter[0] = catalogName;
			searchString = searchString + "%";
			parameter[1] = searchString;
			return locateSPSIDItemQuery.execute(parameter);
		}
		else if(null == searchString && 
				(catalogName.equals(DomainConstants.EB01_NAME)) && (null != formats) && (doFilter)){
			
			return locateSPSIDItemQuery.execute();
		}
		else if(null == searchString && 
				(catalogName.equals(DomainConstants.EB01_NAME)) && (null != formats) && (!doFilter)){
			Object[] parameter = new Object[1];
			parameter[0] = catalogName;
			return locateSPSIDItemQuery.execute(parameter);			
		}
		else if (null != searchString && !("".equalsIgnoreCase(searchString)) && (null == formats)) {
			Object[] parameter = new Object[2];
			parameter[0] = catalogName;
			searchString = searchString + "%";
			parameter[1] = searchString;
			return locateSPSIDItemQuery.execute(parameter);

		} else {
			Object[] parameter = new Object[1];
			parameter[0] = catalogName;
			return locateSPSIDItemQuery.execute(parameter);
		}

	}
	/**
	 * @author UST-GLOBAL
	 * Inner class for executing the item query
	 */
	private class ItemQuery extends MappingSqlQuery {

		public ItemQuery(DataSource dataSource, String query,
				String searchString, String catalogName, List formats, boolean doFilter) {
			super(dataSource, query);
			if(null != formats && (!doFilter)){
				super.declareParameter(new SqlParameter("c.ctlg_name",
						Types.VARCHAR));
			}
			if(null == formats){
				super.declareParameter(new SqlParameter("c.ctlg_name",
						Types.VARCHAR));				
			}
			if (null != searchString && !("".equalsIgnoreCase(searchString))) {
				super.declareParameter(new SqlParameter("b.prmry_cd",
						Types.VARCHAR));
			}
			compile();

		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Item item = new Item();
			item.setPrimaryCode(rs.getString("Value"));
			item.setSecondaryCode(rs.getString("Description"));
			return item;
		}
	}
	/**
	 * This method is used to construct the query for fetching the Item values for a specific catalog
	 * @param searchString - 
	 * @param limitFlag - whether to limit the number of records till 20 or not.
	 * @return String - query
	 */
	private String itemQuery(String searchString, boolean limitFlag) {
		
		String query = "select b.prmry_cd as \"Value\", "
			+ "b.scndry_cd as \"Description\" , "
			+" CASE " 
			+" WHEN " 
			+" INSTR( b.prmry_cd, '.' ,1, '2' ) > 0 OR " 
			+" INSTR( b.prmry_cd, '+' ,1, '2' ) > 0 OR " 
			+" INSTR( b.prmry_cd, '-' ,1, '2' ) > 0 OR " 
			+" LENGTH(TRIM(TRANSLATE(b.prmry_cd, ' +-.0123456789', ' '))) IS NOT NULL " 
			+" THEN b.prmry_cd " 
			+" END            alpha , " 
			+" CASE " 
			+" WHEN " 
			+" INSTR( b.prmry_cd, '.' ,1, '2' ) <= 0 AND "
			+" INSTR( b.prmry_cd, '+' ,1, '2' ) <= 0 AND " 
			+" INSTR( b.prmry_cd, '-' ,1, '2' ) <= 0 AND " 
			+" LENGTH(TRIM(TRANSLATE(b.prmry_cd, ' +-.0123456789', ' '))) IS NULL " 
			+" THEN TO_NUMBER(b.prmry_cd) " 
			+" END            numeric  "
			+ "from ITEM b, catalog c"
			+" Where c.ctlg_id = b.ctlg_id"
			+" and c.ctlg_name=?";
		
		if (null != searchString && !("".equalsIgnoreCase(searchString))) {
			query = query + " and b.prmry_cd like ? ";
		}
		if (limitFlag) {
			query = query + " and rownum <= 20 ";
		}
		//for sorting by numeric and alphabets
		query = query + "order by numeric , alpha ASC";		
		return query;
	}
	/**
	 * Method to set the item query for EB01 with the condition for sps formats
	 * @param searchString
	 * @param limitFlag
	 * @param formats
	 * @return
	 */
	private String itemQueryForEB01(String searchString, boolean limitFlag, List formats) {
		
		StringBuffer query = new StringBuffer("select distinct b.data_element_val as \"Value\", "
			+ "i.scndry_cd as \"Description\" , "
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
			+ "from bx_cntrct_var_valdn_mapg b, item i, catalog v  "
			+ "where  b.data_element_val = i.prmry_cd and v.ctlg_id = i.ctlg_id and b.data_element_id = v.ctlg_name "
			+" and v.ctlg_name = 'EB01' ");			
		
		if (null != searchString && !("".equalsIgnoreCase(searchString))) {
			query.append(" and b.data_element_val like ? ");
		}
		if (limitFlag) {
			query.append(" and rownum <= 20 ");
		}
		if(null != formats && !formats.isEmpty()){
			query.append( " and b.var_frmt in ( ");
			Iterator formatsIter = formats.iterator();
			while(formatsIter.hasNext()){
				
				query.append(" '"+formatsIter.next()+ "'" + " ,");
				
			}
			query = query.delete((query.length()-2), query.length());
			query.append(" )");
		}
		//for sorting by numeric and alphabets
		query.append("order by numeric , alpha ASC");		
		return query.toString();
	}
	/**
	 * @return DataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
