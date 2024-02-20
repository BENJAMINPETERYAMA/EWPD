/*
 * Created on Sep 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.framework.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.contract.bo.Contract;

/**
 * @author U14659
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ContractHistoryLogDAOImpl extends HistoryLogDAO {
	
	private InsertLog insert;

	private RetrieveLog retrieve;
	
	private RetrieveVersion versionLog;

	private String retrieveSQL;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.HistoryLogDAO#persist(com.wellpoint.wpd.business.framework.bo.HistoryLogEntry)
	 */
	/*
	 * Persist method for inserting Log data to the DB.
	 */
	public void persist(HistoryLogEntry historyLogEntry) throws SQLException {
		

		insert = new InsertLog(getDataSource());

		if (historyLogEntry != null) {
			try {

				insert.update(new Object[] { historyLogEntry.getId(),
						new Integer(getContractVersion(historyLogEntry.getDateSegmentId())),
						historyLogEntry.getAction(),
						historyLogEntry.getUserId(),
						historyLogEntry.getTimestamp(),
						historyLogEntry.getEffectiveDate(),});

			} catch (Exception e) {
				
				if (e instanceof SQLException)
					throw (SQLException) e;
				else
					throw new RuntimeException(e);
			}
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.HistoryLogDAO#retrieveLog(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      java.util.List) Method to retrive log information from DB
	 */

	public List retrieveLog(BusinessObject businessObject, List actions)  {
		

		Contract contract = (Contract) businessObject;

		// Retreive Query.
		this.retrieveSQL = "select b.entity_id ,b.entity_version, b.entity_stts_cd ,count(c.dt_sgmnt_sys_id) as count"
				+ ",b.USER_ID , b.TIMESTAMP "
				+ "from CMST_CNTRCT_MSTR A,CNTRCT_TRANS_LOG B , CNTRCT_DT_SGMNT C "
				+ "where B.entity_id = ? and b.entity_stts_cd in (?) and b.entity_version <= ? "
				+ "and A.cntrct_id = b.entity_id "
				+ "and A.cntrct_vrsn_nbr = b.entity_version "
				+ "and c.cntrct_sys_id = a.cntrct_sys_id "
				+ "group by b.entity_id,b.entity_version, b.entity_stts_cd ,b.USER_ID , b.TIMESTAMP, c.cntrct_sys_id";

		retrieve = new RetrieveLog(getDataSource());
		List logEntries = null;
		String action = new String();

		int i = 0;
		//Will get data from the list and assign it to a string seperated by
		// comas.
		if (actions != null && actions.size() > 0) {
			action += (String) actions.get(i);
			if (i < actions.size() - 1)
				action += ",";
			i++;
		}
		logEntries = retrieve.execute(new Object[] {
				contract.getContractId(), action,
				new Integer(contract.getVersion()) });
		return logEntries;
	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * to retrieve Contract Version
	 *      
	 */

	public int getContractVersion(int dateSegmentSysId){
		this.retrieveSQL ="SELECT MAX(CNTRCT_VRSN_NBR) FROM CMST_CNTRCT_MSTR WHERE " +
				"CNTRCT_SYS_ID IN(SELECT CNTRCT_SYS_ID  FROM CNTRCT_DT_SGMNT_ASSN WHERE DT_SGMNT_SYS_ID = ?) " +
				"AND CNTRCT_STTS_CD != 'MARKED_FOR_DELETION' GROUP BY CNTRCT_ID";
		
		versionLog = new RetrieveVersion(getDataSource());
		List contractVersion =versionLog.execute(new Object[] {
				new Integer(dateSegmentSysId) });
		return ((ContractHistoryLogRecord)contractVersion.get(0)).getVersion();
	}

	/*
	 * Map the place holders in the object-context.xml for retreive and
	 * specifies the type of the data. @author u18739
	 * 
	 * TODO To change the template for this generated type comment go to Window -
	 * Preferences - Java - Code Style - Code Templates
	 */

	class RetrieveLog extends MappingSqlQuery {

		public RetrieveLog(DataSource dataSource) {
			super(dataSource, retrieveSQL);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.INTEGER));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 */
		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			ContractHistoryLogRecord historyLogRecord = new ContractHistoryLogRecord();
			historyLogRecord.setId(rs.getString(1));
			historyLogRecord.setVersion(rs.getInt(2));
			historyLogRecord.setAction(rs.getString(3));
			historyLogRecord.setNoOfDateSegments(rs.getInt(4));
			historyLogRecord.setUserId(rs.getString(5));
			historyLogRecord.setTimestamp(rs.getDate(6));
			return historyLogRecord;

		}
	}
	
	class RetrieveVersion extends MappingSqlQuery {

		public RetrieveVersion(DataSource dataSource) {
			super(dataSource, retrieveSQL);
			declareParameter(new SqlParameter(Types.INTEGER));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 */
		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			ContractHistoryLogRecord historyLogRecord = new ContractHistoryLogRecord();
			historyLogRecord.setVersion(rs.getInt(1));
			return historyLogRecord;

		}
	}

	/**
	 * @return Returns the retrieve.
	 */
	public RetrieveLog getRetrieve() {
		return retrieve;
	}

	/**
	 * @param retrieve
	 *            The retrieve to set.
	 */
	public void setRetrieve(RetrieveLog retrieve) {
		this.retrieve = retrieve;
	}

	/**
	 * @return Returns the retrieveSQL.
	 */
	public String getRetrieveSQL() {
		return retrieveSQL;
	}

	/*
	 * @param retrieveSQL The retrieveSQL to set.
	 */

	public void setRetrieveSQL(String retrieveSQL) {
		this.retrieveSQL = retrieveSQL;
	}
}