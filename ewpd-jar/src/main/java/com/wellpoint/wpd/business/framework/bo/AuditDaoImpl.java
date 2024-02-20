/*
 * AuditDaoImpl.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: AuditDaoImpl.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class AuditDaoImpl extends JdbcDaoSupport implements AuditDao {

	private String retrieveSql;
	private String retrieveTimeZoneSql;

	private RetrieveTime retrieveTime;
	private RetrieveTimeZone retrieveTimeZone;

	/**
	 * @return Returns the retrieveTime.
	 */
	public RetrieveTime getRetrieveTime() {
		return retrieveTime;
	}

	/**
	 * @param retrieveTime
	 *            The retrieveTime to set.
	 */
	public void setRetrieveTime(RetrieveTime retrieveTime) {
		this.retrieveTime = retrieveTime;
	}

    /**
     * @return Returns the retrieveTimeZone.
     */
    public RetrieveTimeZone getRetrieveTimeZone() {
        return retrieveTimeZone;
    }
    /**
     * @param retrieveTimeZone The retrieveTimeZone to set.
     */
    public void setRetrieveTimeZone(RetrieveTimeZone retrieveTimeZone) {
        this.retrieveTimeZone = retrieveTimeZone;
    }
    
	/**
	 * @return Returns the retrieveSql.
	 */
	public String getRetrieveSql() {
		return retrieveSql;
	}

	/**
	 * @param retrieveSql
	 *            The retrieveSql to set.
	 */
	public void setRetrieveSql(String retrieveSql) {
		this.retrieveSql = retrieveSql;
	}

    /**
     * @return Returns the retrieveTimeZoneSql.
     */
    public String getRetrieveTimeZoneSql() {
        return retrieveTimeZoneSql;
    }
    /**
     * @param retrieveTimeZoneSql The retrieveTimeZoneSql to set.
     */
    public void setRetrieveTimeZoneSql(String retrieveTimeZoneSql) {
        this.retrieveTimeZoneSql = retrieveTimeZoneSql;
    }
    
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.AuditDao#retrieveCurrentDate()
	 */
	public Date retrieveCurrentDate() {
		retrieveTime = new RetrieveTime(getDataSource());
		//Date date = null;
		List resultList = retrieveTime.execute(new Object[] {});
		if(resultList != null){
		    return (Date)resultList.get(0);
		}
		//if (null != resultList && resultList.size() == 1) {
		//	Iterator iterator = resultList.iterator();
		//	date = (Date) iterator.next();
		//}
		// convert to GMT
		return null;
	}
	
	public TimeZone retrieveTimeZone(){
		retrieveTimeZone = new RetrieveTimeZone(getDataSource());
		List resultList = retrieveTimeZone.execute(new Object[] {});
		if(resultList != null){
		    return (TimeZone)resultList.get(0);
		}
		return null;
	}

	class RetrieveTime extends MappingSqlQuery {

		public RetrieveTime(DataSource dataSource) {
			super(dataSource, retrieveSql);
		}

		/**
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 * @param rs -
		 *            ResultSet object
		 * @param rowNum -
		 *            the row number to process
		 * @return
		 * @throws SQLException
		 */
		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Timestamp date = rs.getTimestamp(1);
			return date;
		}

	}
	
	class RetrieveTimeZone extends MappingSqlQuery {
	    
	    public RetrieveTimeZone(DataSource dataSource){
	        super(dataSource, retrieveTimeZoneSql);
	    }
	    
		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			String strTimeZone  = rs.getString(1);
			int seperatorPosition = strTimeZone.indexOf(":");
			String hoursPart = strTimeZone.substring(0,seperatorPosition);
			String minutesPart = strTimeZone.substring(seperatorPosition+1).trim();
			//Bug when we parse int +05 string
			String hoursPartTemp = hoursPart.substring(1, hoursPart.length());
			int hoursOffset = Integer.parseInt(hoursPartTemp);
			
			int minutesOffset = Integer.parseInt(minutesPart);
			int rawOffsetInMilliSecs = hoursOffset*60*60*1000 + minutesOffset*60*1000;
			String timeZoneId = "";
			if(hoursPart.equals("-07") && minutesPart.equals("00")){
			    timeZoneId = "America/Los_Angeles";
			}
			if(hoursPart.equals("+05") && minutesPart.equals("30")){
			    timeZoneId = "Asia/Calcutta";
			}
			if(hoursPart.equals("-04") && minutesPart.equals("00")){
			    timeZoneId = "America/New_York";
			}			
			TimeZone timeZone = new SimpleTimeZone(rawOffsetInMilliSecs, timeZoneId);
			String timeZoneDisplayName = timeZone.getDisplayName(true, SimpleTimeZone.SHORT);
			return timeZone;
		}
	}

}
