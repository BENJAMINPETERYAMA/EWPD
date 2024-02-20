/*
 * Created on Sep 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.framework.bo;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import javax.sql.DataSource;


import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U14659
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class HistoryLogDAO extends JdbcDaoSupport {

	private InsertLog insert;

	private String insertSQL;

	public static final String APPLICATION_CONTEXT = "com/wellpoint/wpd/common/configfiles/dao-mapping.xml";

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
						new Integer(historyLogEntry.getVersion()),
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

	public abstract List retrieveLog(BusinessObject businessObject, List actions);

	/*
	 * Map the place holders in the Logger-dao-mapping.xml for insert and specifies
	 * the type of the data. @author u18739
	 * 
	 * TODO To change the template for this generated type comment go to Window -
	 * Preferences - Java - Code Style - Code Templates
	 */
	class InsertLog extends SqlUpdate {
		public InsertLog(DataSource dataSource) {
			super(dataSource, insertSQL);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.TIMESTAMP));
			declareParameter(new SqlParameter(Types.DATE));
		}
	}

	/**
	 * @return Returns the insert.
	 */
	public InsertLog getInsert() {
		return insert;
	}

	/**
	 * @param insert
	 *            The insert to set.
	 */
	public void setInsert(InsertLog insert) {
		this.insert = insert;
	}

	/**
	 * @return Returns the insertSQL.
	 */
	public String getInsertSQL() {
		return insertSQL;
	}

	/**
	 * @param insertSQL
	 *            The insertSQL to set.
	 */
	public void setInsertSQL(String insertSQL) {
		this.insertSQL = insertSQL;
	}
}