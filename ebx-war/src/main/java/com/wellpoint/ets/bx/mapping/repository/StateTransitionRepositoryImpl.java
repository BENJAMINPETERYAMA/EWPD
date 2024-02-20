/*
 * Created on May 24, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
/**
 * @author u22093
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StateTransitionRepositoryImpl implements StateTransitionRepository{
	
	private DataSource dataSource;
	private static Logger log = Logger.getLogger(StateTransitionRepositoryImpl.class);
	
	private class UpdateMappingMstrStatus extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE BX_CNTRCT_VAR_MAPG SET IN_TEMP_TAB =?, VAR_MAPG_STTS_CD =?, " +
				"LST_CHG_TMS = sysdate, LST_CHG_USR = ? WHERE CNTRCT_VAR_ID =?";

		public UpdateMappingMstrStatus(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("IN_TEMP_TAB", Types.VARCHAR));
			declareParameter(new SqlParameter("VAR_MAPG_STTS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("LST_CHG_USR", Types.VARCHAR));
			declareParameter(new SqlParameter("CNTRCT_VAR_ID", Types.VARCHAR));
			
			compile();
		}

		public void update(Mapping mapping) {

			Object[] objs = new Object[] { mapping.getInTempTable(),mapping.getVariableMappingStatus(),
					mapping.getUser().getLastUpdatedUserName(), mapping.getVariable().getVariableId()};
			super.update(objs);
		}
	}
	
	private class UpdateMstrStatus extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE BX_CNTRCT_VAR_MAPG SET VAR_MAPG_STTS_CD =?, " +
				"LST_CHG_TMS = sysdate, LST_CHG_USR = ?, DATA_FEED_IND = ?, AUDIT_LOCK = ? WHERE CNTRCT_VAR_ID =?";

		public UpdateMstrStatus(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("VAR_MAPG_STTS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("LST_CHG_USR", Types.VARCHAR));
			declareParameter(new SqlParameter("DATA_FEED_IND", Types.VARCHAR));
			declareParameter(new SqlParameter("AUDIT_LOCK", Types.VARCHAR));
			declareParameter(new SqlParameter("CNTRCT_VAR_ID", Types.VARCHAR));
			
			compile();
		}

		public void update(Mapping mapping) {

			Object[] objs = new Object[] {mapping.getVariableMappingStatus(),
					mapping.getUser().getLastUpdatedUserName(),mapping.getDataFeedInd(),mapping.getAuditLock(), mapping.getVariable().getVariableId() };
			super.update(objs);
		}
	}
	
	private class UpdateTempStatus extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE TEMP_BX_CNTRCT_VAR_MAPG SET VAR_MAPG_STTS_CD =?, " +
				"LST_CHG_TMS = sysdate, LST_CHG_USR = ?, DATA_FEED_IND = ? WHERE CNTRCT_VAR_ID =?";

		public UpdateTempStatus(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("VAR_MAPG_STTS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("LST_CHG_USR", Types.VARCHAR));
			declareParameter(new SqlParameter("DATA_FEED_IND", Types.VARCHAR));
			declareParameter(new SqlParameter("CNTRCT_VAR_ID", Types.VARCHAR));	
			
			compile();
		}

		public void update(Mapping mapping) {

			Object[] objs = new Object[] {mapping.getVariableMappingStatus(),
					mapping.getUser().getLastUpdatedUserName(),mapping.getDataFeedInd(), mapping.getVariable().getVariableId()};
			super.update(objs);
		}
	}
	

	private class DeleteTempRecord extends SqlUpdate {
		private static final String SQL_QUERY = "DELETE FROM TEMP_BX_CNTRCT_VAR_MAPG WHERE CNTRCT_VAR_ID =? " +
				"AND VAR_MAPG_SYS_ID = ?" ;

		public DeleteTempRecord(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("CNTRCT_VAR_ID", Types.VARCHAR));	
			declareParameter(new SqlParameter("VAR_MAPG_SYS_ID", Types.BIGINT));
			compile();
		}

		public void delete(Mapping mapping) {

			Object[] objs = new Object[] {mapping.getVariable().getVariableId(), mapping.getVariableSystemId()};
			super.update(objs);
		}
	}
	private class DeleteTempChildRecord extends SqlUpdate {
		private static final String SQL_QUERY = "DELETE FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL WHERE VAR_MAPG_SYS_ID =?" ;

		public DeleteTempChildRecord(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("VAR_MAPG_SYS_ID", Types.BIGINT));			
			compile();
		}

		public void delete(Mapping mapping) {

			Object[] objs = new Object[] {mapping.getVariableSystemId()};
			super.update(objs);
		}
	}
	
	private class DeleteTempRecordForProdDataFeed extends SqlUpdate {
		
		private SimpleJdbcTemplate simpleJdbcTemplate;
		
		public void setDataSource(DataSource dataSource) {
	        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	    }
		private static final String SQL_QUERY = "DELETE FROM TEMP_BX_CNTRCT_VAR_MAPG WHERE CNTRCT_VAR_ID =?" +
				"AND VAR_MAPG_SYS_ID = ?" ;
		
		int count = 0;

		protected void batchDelete(int batchSize, List<Mapping> mappingList) {
			List<Object[]> batch = new ArrayList<Object[]>();
	        for (Mapping mapping : mappingList) {
	        	Object[] values = new Object[] {mapping.getVariable().getVariableId(), mapping.getVariableSystemId()};
	            batch.add(values);
	            if(++count % batchSize == 0) {
	            	int[] deleteCounts = simpleJdbcTemplate.batchUpdate(SQL_QUERY, batch);
	            	log.debug("DeleteTempRecordForProdDataFeed = "+deleteCounts.length);
	            	batch = new ArrayList<Object[]>();
	            }
	        }
	        int[] deleteCounts = simpleJdbcTemplate.batchUpdate(SQL_QUERY, batch);
	        log.debug("DeleteTempRecordForProdDataFeed = "+deleteCounts.length);
		}
	}
	private class DeleteTempChildRecordForProdDataFeed extends SqlUpdate {
		
		private SimpleJdbcTemplate simpleJdbcTemplate;
		
		public void setDataSource(DataSource dataSource) {
	        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	    }	
		private static final String SQL_QUERY = "DELETE FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL WHERE VAR_MAPG_SYS_ID =?" ;
		
		int count = 0;

		protected void batchDelete(int batchSize, List<Mapping> mappingList) {
			List<Object[]> batch = new ArrayList<Object[]>();
	        for (Mapping mapping : mappingList) {
	        	Object[] values = new Object[] {mapping.getVariableSystemId()};
	            batch.add(values);
	            if(++count % batchSize == 0) {
	            	int[] deleteCounts = simpleJdbcTemplate.batchUpdate(SQL_QUERY, batch);
	            	log.debug("DeleteTempChildRecordForProdDataFeed = "+deleteCounts.length);
	            	batch = new ArrayList<Object[]>();
	            }
	        }
	        int[] deleteCounts = simpleJdbcTemplate.batchUpdate(SQL_QUERY, batch);
	        log.debug("DeleteTempChildRecordForProdDataFeed = "+deleteCounts.length);
		}
	}
	/*
	 * deleting mstr table and Mstr child table
	 * 
	 * */
	private class DeleteMstrRecord extends SqlUpdate {
		private static final String SQL_QUERY = "DELETE FROM BX_CNTRCT_VAR_MAPG WHERE CNTRCT_VAR_ID =?" +
				"AND VAR_MAPG_SYS_ID = ?" ;

		public DeleteMstrRecord(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("CNTRCT_VAR_ID", Types.VARCHAR));	
			declareParameter(new SqlParameter("VAR_MAPG_SYS_ID", Types.BIGINT));
			compile();
		}

		public void delete(Mapping mapping) {

			Object[] objs = new Object[] {mapping.getVariable().getVariableId(), mapping.getVariableSystemId()};
			super.update(objs);
		}
	}
	private class DeleteMstrChildRecord extends SqlUpdate {
		private static final String SQL_QUERY = "DELETE FROM BX_CNTRCT_VAR_MAPG_VAL WHERE VAR_MAPG_SYS_ID =?" ;

		public DeleteMstrChildRecord(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("VAR_MAPG_SYS_ID", Types.BIGINT));			
			compile();
		}

		public void delete(Mapping mapping) {

			Object[] objs = new Object[] {mapping.getVariableSystemId()};
			super.update(objs);
		}
	}
	
	private class UpdateBeingModifiedFlag extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE BX_CNTRCT_VAR_MAPG SET IN_TEMP_TAB =?, " +
				"LST_CHG_TMS = sysdate, LST_CHG_USR = ?,  VAR_MAPG_STTS_CD = ? WHERE VAR_MAPG_SYS_ID =?";

		public UpdateBeingModifiedFlag(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("IN_TEMP_TAB", Types.VARCHAR));
			declareParameter(new SqlParameter("LST_CHG_USR", Types.VARCHAR));
			declareParameter(new SqlParameter("VAR_MAPG_STTS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("VAR_MAPG_SYS_ID", Types.BIGINT));			
			compile();
		}

		public void update(Mapping mapping) {

			Object[] objs = new Object[] { mapping.getInTempTable(),mapping.getUser().getLastUpdatedUserName(),
					mapping.getVariableMappingStatus(), mapping.getVariableSystemId()};
			super.update(objs);
		}
	}
	
	public void updateMstrToBeingModified(Mapping mapping){
		
		UpdateMappingMstrStatus updateMappingMstrStatus = new UpdateMappingMstrStatus(
				getDataSource());
		mapping.setInTempTable("Y");
		mapping.setVariableMappingStatus("BEING_MODIFIED");
		updateMappingMstrStatus.update(mapping);
		
	}	
	public void updateBeingModifiedFlag(Mapping mapping){
		
		UpdateBeingModifiedFlag updateBeingModifiedFlag = new UpdateBeingModifiedFlag(
				getDataSource());
				
		updateBeingModifiedFlag.update(mapping);
		
	}	
	public int updateStatusInMstr(Mapping mapping){
		
		UpdateMstrStatus updateMstrStatus = new UpdateMstrStatus(
				getDataSource());
		updateMstrStatus.update(mapping);
		return 1;
	}
	
	public int updateStatusInTemp(Mapping mapping){
		
		UpdateTempStatus UpdateTempStatus = new UpdateTempStatus(
				getDataSource());
		UpdateTempStatus.update(mapping);
		return 1;
	}
	public boolean rollBackTempMapping(Mapping mapping){
		
		DeleteTempRecord deleteTempRecord = new DeleteTempRecord(getDataSource());
		DeleteTempChildRecord deleteTempChildRecord = new DeleteTempChildRecord(getDataSource());
		
		deleteTempChildRecord.delete(mapping);
		deleteTempRecord.delete(mapping);
		return true;
	}
	
	public boolean rollBackTempMapping(int batchSize, List<Mapping> mappingList){
		
		DeleteTempRecordForProdDataFeed deleteTempRecord = new DeleteTempRecordForProdDataFeed();
		DeleteTempChildRecordForProdDataFeed deleteTempChildRecord = new DeleteTempChildRecordForProdDataFeed();
		
		deleteTempRecord.setDataSource(getDataSource());
		deleteTempChildRecord.setDataSource(getDataSource());
		
		deleteTempChildRecord.batchDelete(batchSize, mappingList);
		deleteTempRecord.batchDelete(batchSize, mappingList);
		return true;
	}
	
	
	public boolean rollBackMstrMapping(Mapping mapping){
		DeleteMstrRecord deleteMstrRecord = new DeleteMstrRecord(getDataSource());
		DeleteMstrChildRecord deleteMstrChildRecord = new DeleteMstrChildRecord(getDataSource());
		deleteMstrRecord.delete(mapping);
		deleteMstrChildRecord.delete(mapping);
		return true;
		
	}
	private class UpdateStatusMstrForDatafeed extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE BX_CNTRCT_VAR_MAPG SET VAR_MAPG_STTS_CD =?, " +
				"LST_CHG_TMS = sysdate, DATA_FEED_IND = ? WHERE CNTRCT_VAR_ID =?";

		public UpdateStatusMstrForDatafeed(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("VAR_MAPG_STTS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("DATA_FEED_IND", Types.VARCHAR));
			declareParameter(new SqlParameter("CNTRCT_VAR_ID", Types.VARCHAR));			
			compile();
		}

		public void update(Mapping mapping) {

			Object[] objs = new Object[] {mapping.getVariableMappingStatus(), mapping.getDataFeedInd(),
					mapping.getVariable().getVariableId() };
			super.update(objs);
		}
	}
	
	private class UpdateStatusTempForDatafeed extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE TEMP_BX_CNTRCT_VAR_MAPG SET VAR_MAPG_STTS_CD =?, " +
				"LST_CHG_TMS = sysdate, DATA_FEED_IND = ? WHERE CNTRCT_VAR_ID =?";

		public UpdateStatusTempForDatafeed(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("VAR_MAPG_STTS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("DATA_FEED_IND", Types.VARCHAR));
			declareParameter(new SqlParameter("CNTRCT_VAR_ID", Types.VARCHAR));				
			compile();
		}

		public void update(Mapping mapping) {

			Object[] objs = new Object[] {mapping.getVariableMappingStatus(),mapping.getDataFeedInd(),
					mapping.getVariable().getVariableId()};
			super.update(objs);
		}
	}
	
	private class UpdateStatusMstrForProdDatafeed extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE BX_CNTRCT_VAR_MAPG SET VAR_MAPG_STTS_CD =?, " +
				"LST_CHG_TMS = sysdate WHERE VAR_MAPG_STTS_CD = 'SCHEDULED_TO_PRODUCTION' " +
                				"and IN_TEMP_TAB = 'N'";

		public UpdateStatusMstrForProdDatafeed(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("VAR_MAPG_STTS_CD", Types.VARCHAR));
			compile();
		}

		public int update() {

			Object[] objs = new Object[] {"PUBLISHED"};//put it directly in query
			int i = super.update(objs);
			return i;
		}
	}
	
	private class UpdateStatusTempForProdDatafeed extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE TEMP_BX_CNTRCT_VAR_MAPG SET VAR_MAPG_STTS_CD =?, " +
				"LST_CHG_TMS = sysdate WHERE VAR_MAPG_STTS_CD = 'SCHEDULED_TO_PRODUCTION'";

		public UpdateStatusTempForProdDatafeed(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("VAR_MAPG_STTS_CD", Types.VARCHAR));
			compile();
		}

		public int update() {

			Object[] objs = new Object[] {"PUBLISHED"};
			int i = super.update(objs);
			return i;
		}
	}
	
	private class UpdateStatusMstrtoObjTransForDatafeed extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE BX_CNTRCT_VAR_MAPG SET VAR_MAPG_STTS_CD = ?, " +
				"LST_CHG_TMS = sysdate, DATA_FEED_IND = ? WHERE VAR_MAPG_STTS_CD = 'SCHEDULED_TO_TEST' " +
                				"and IN_TEMP_TAB = 'N' and (DATA_FEED_IND <> 'Y' or DATA_FEED_IND IS NULL)";

		public UpdateStatusMstrtoObjTransForDatafeed(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("VAR_MAPG_STTS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("DATA_FEED_IND", Types.VARCHAR));
			compile();
		}

		public int update() {

			Object[] objs = new Object[] {"OBJECT_TRANSFERRED","N"};
			int i = super.update(objs);
			return i;
		}
	}
	
	private class UpdateStatusMstrtoSchProdForDatafeed extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE BX_CNTRCT_VAR_MAPG SET VAR_MAPG_STTS_CD = ?, " +
		"LST_CHG_TMS = sysdate, DATA_FEED_IND = ? WHERE VAR_MAPG_STTS_CD = 'SCHEDULED_TO_TEST' " +
        				"and IN_TEMP_TAB = 'N' and DATA_FEED_IND = 'Y'";

		public UpdateStatusMstrtoSchProdForDatafeed(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("VAR_MAPG_STTS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("DATA_FEED_IND", Types.VARCHAR));
			compile();
		}

		public int update() {

			Object[] objs = new Object[] {"SCHEDULED_TO_PRODUCTION","N"};
			int i = super.update(objs);
			return i;
		}
	}
	
	private class UpdateStatusTemptoObjTransForDatafeed extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE TEMP_BX_CNTRCT_VAR_MAPG SET VAR_MAPG_STTS_CD = ?, " +
		"LST_CHG_TMS = sysdate, DATA_FEED_IND = ? WHERE VAR_MAPG_STTS_CD = 'SCHEDULED_TO_TEST' " +
        				"and (DATA_FEED_IND <> 'Y' or DATA_FEED_IND IS NULL)";

		public UpdateStatusTemptoObjTransForDatafeed(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("VAR_MAPG_STTS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("DATA_FEED_IND", Types.VARCHAR));
			compile();
		}

		public int update() {

			Object[] objs = new Object[] {"OBJECT_TRANSFERRED","N"};
			int i = super.update(objs);
			return i;
		}
	}
	
	private class UpdateStatusTemptoSchProdForDatafeed extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE TEMP_BX_CNTRCT_VAR_MAPG SET VAR_MAPG_STTS_CD = ?, " +
		"LST_CHG_TMS = sysdate, DATA_FEED_IND = ? WHERE VAR_MAPG_STTS_CD = 'SCHEDULED_TO_TEST' " +
        				"and DATA_FEED_IND = 'Y'";

		public UpdateStatusTemptoSchProdForDatafeed(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("VAR_MAPG_STTS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("DATA_FEED_IND", Types.VARCHAR));
			compile();
		}

		public int update() {

			Object[] objs = new Object[] {"SCHEDULED_TO_PRODUCTION","N"};
			int i = super.update(objs);
			return i;
		}
	}
	
	public int updateStatusMstrForDatafeed(){
		
		UpdateStatusMstrtoObjTransForDatafeed updateStatusMstrtoObjTransForDatafeed = new UpdateStatusMstrtoObjTransForDatafeed(
				getDataSource());
		int i = updateStatusMstrtoObjTransForDatafeed.update();
		UpdateStatusMstrtoSchProdForDatafeed updateStatusMstrtoSchProdForDatafeed = new UpdateStatusMstrtoSchProdForDatafeed(
				getDataSource());
		int j = updateStatusMstrtoSchProdForDatafeed.update();
		return i+j;
	}
	
	public int updateStatusTempForDatafeed(){
		
		UpdateStatusTemptoObjTransForDatafeed updateStatusTemptoObjTransForDatafeed = new UpdateStatusTemptoObjTransForDatafeed(
				getDataSource());
		int i = updateStatusTemptoObjTransForDatafeed.update();
		UpdateStatusTemptoSchProdForDatafeed updateStatusTemptoSchProdForDatafeed = new UpdateStatusTemptoSchProdForDatafeed(
				getDataSource());
		int j = updateStatusTemptoSchProdForDatafeed.update();
		return i+j;
	}
	
	public int updateStatusMstrForProdDatafeed(){
		
		UpdateStatusMstrForProdDatafeed updateStatusMstrForDatafeed = new UpdateStatusMstrForProdDatafeed(
				getDataSource());
		int i = updateStatusMstrForDatafeed.update();
		return i;
	}
	
	public int updateStatusTempForProdDatafeed(){
		
		UpdateStatusTempForProdDatafeed updateStatusTempForDatafeed = new UpdateStatusTempForProdDatafeed(
				getDataSource());
		int i = updateStatusTempForDatafeed.update();
		return i;
	}
	
	
	public int updateStatusMstrForDatafeed(Mapping mapping){
		
		UpdateStatusMstrForDatafeed updateStatusMstrForDatafeed = new UpdateStatusMstrForDatafeed(
				getDataSource());
		updateStatusMstrForDatafeed.update(mapping);
		return 1;
	}
	
	public int updateStatusTempForDatafeed(Mapping mapping){
		
		UpdateStatusTempForDatafeed updateStatusTempForDatafeed = new UpdateStatusTempForDatafeed(
				getDataSource());
		updateStatusTempForDatafeed.update(mapping);
		return 1;
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
