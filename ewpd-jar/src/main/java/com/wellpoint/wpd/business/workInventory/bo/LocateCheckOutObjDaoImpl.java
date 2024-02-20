/*
 * LocateCheckOutObjDaoImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.workInventory.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.wellpoint.wpd.common.bo.CheckedOutObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LocateCheckOutObjDaoImpl.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class LocateCheckOutObjDaoImpl extends JdbcDaoSupport implements
		LocateCheckOutObjDao {

	private String locateQuery;

	private CheckedOutObjectMapper checkedOutObjectMapper;

	class CheckedOutObjectMapper extends MappingSqlQuery {

		public CheckedOutObjectMapper(DataSource dataSource) {
			super(dataSource, locateQuery);
			declareParameter(new SqlParameter(Types.VARCHAR));
		}

		/**
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 * @param arg0
		 * @param arg1
		 * @return
		 * @throws SQLException
		 */
		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			CheckedOutObject chkOutObjVO = new CheckedOutObject();
			chkOutObjVO.setName(rs.getString(1));
			chkOutObjVO.setKey(rs.getString(2));
			chkOutObjVO.setCheckoutDate(rs.getTimestamp(3));
			chkOutObjVO.setDuration(rs.getInt(4));
			chkOutObjVO.setUserId(rs.getString(5));
			chkOutObjVO.setExpiryDate(new Timestamp(chkOutObjVO.getCheckoutDate().getTime() + chkOutObjVO.getDuration() * 24 * 60 * 60 * 1000));
			return chkOutObjVO;
		}
	}

	public List retrieveChkOutObjList(CheckOutLocateCriteria chkOutLocateCriteria) {
		checkedOutObjectMapper = new CheckedOutObjectMapper(getDataSource());
		List resultList = checkedOutObjectMapper
				.execute(new Object[] { chkOutLocateCriteria.getUserId() });
		if (null != resultList) {
			return resultList;
		}
		return null;
	}

	/**
	 * @return Returns the locateQuery.
	 */
	public String getLocateQuery() {
		return locateQuery;
	}

	/**
	 * @param locateQuery
	 *            The locateQuery to set.
	 */
	public void setLocateQuery(String locateQuery) {
		this.locateQuery = locateQuery;
	}

	/**
	 * @return Returns the checkedOutObjectMapper.
	 */
	public CheckedOutObjectMapper getCheckedOutObjectMapper() {
		return checkedOutObjectMapper;
	}

	/**
	 * @param checkedOutObjectMapper
	 *            The checkedOutObjectMapper to set.
	 */
	public void setCheckedOutObjectMapper(
			CheckedOutObjectMapper checkedOutObjectMapper) {
		this.checkedOutObjectMapper = checkedOutObjectMapper;
	}
}