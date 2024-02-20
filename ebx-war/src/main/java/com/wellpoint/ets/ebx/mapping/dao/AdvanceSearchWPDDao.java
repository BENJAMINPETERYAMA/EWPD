/*
 * <AdvanceSearchWPDDao.java>
 *
 * � 2016 - 2017 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

/*
 * This class contains the method for maintaining count and entryToBatch  
 */

public class AdvanceSearchWPDDao {

	private static Logger logger = Logger.getLogger(AdvanceSearchWPDDao.class);

	Connection dbConnection = null;

	

	public void addEntryToBatch(DataSource dataSource, String selectquery, Long wpd_massid, String userid,
			String criteria) throws SQLException {

		PreparedStatement pStmt = null;
		try {

			final String query = "INSERT INTO PRODUCT_PROD.BX_WPD_VAR_MAPG_BLK_RPT(REQ_ID,USR_ID,QRY_STRNG,SRCH_CRITERIA,DATE_CREATED) "
					+ "VALUES (?,?,?,?,SYSDATE)";

			dbConnection = dataSource.getConnection();

			pStmt = dbConnection.prepareStatement(query);
			ByteArrayInputStream in = new ByteArrayInputStream(selectquery.getBytes());
			ByteArrayInputStream ba = new ByteArrayInputStream(criteria.getBytes());
			pStmt.setLong(1, wpd_massid);
			pStmt.setString(2, userid);
			pStmt.setBinaryStream(3, in, selectquery.getBytes().length);
			pStmt.setBinaryStream(4, ba, criteria.getBytes().length);
			pStmt.executeQuery();

		} catch (final SQLException sqlEx) {
			logger.error("Error Message", sqlEx);
			sqlEx.printStackTrace();
			throw sqlEx;
		} finally {
			try {

				if (pStmt != null) {
					pStmt.close();
				}
				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (final SQLException sqlEx) {
				logger.error("Error Message", sqlEx);
				sqlEx.printStackTrace();
			}
		}
	}

}