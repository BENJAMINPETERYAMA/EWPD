/*
 * Created on May 17, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

/**
 * @author u19278
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SequenceGeneratorRepositoryImpl implements
		SequenceGeneratorRepository {
	private SequenceQuery sequenceQuery;

	private DataSource dataSource;

	protected class SequenceQuery extends MappingSqlQuery {

		/**
		 * Create a new instance of SeequenceQuery.
		 * @param ds the DataSource to use for the query
		 * @param sql SQL string to use for the query
		 */
		protected SequenceQuery(DataSource ds, String sqlSeq) {
			super(ds, sqlSeq);
		}

		protected Object mapRow(ResultSet rs, int rownum) throws SQLException {

			Long sequence = Long.valueOf(rs.getLong(1));

			return sequence;
		}
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.SequenceGeneratorRepository#generateSequence(java.lang.String)
	 */
	public Long generateSequence(String columnName) {
		StringBuffer sqlSeq = new StringBuffer();
		sqlSeq.append("select ");
		sqlSeq.append(columnName.trim());
		sqlSeq.append(".nextval from dual");
		sequenceQuery = new SequenceQuery(getDataSource(), sqlSeq.toString());

		List seqList = null;
		Long sequence = null;		
		seqList = this.sequenceQuery.execute();

		if (null != seqList) {
			sequence = (Long) seqList.get(0);
		}
		
		return sequence;
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
}
