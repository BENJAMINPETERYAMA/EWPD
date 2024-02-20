package com.wellpoint.ets.bx.mapping.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;


public class EbxStateTransitionRepositoryImpl implements EbxStateTransitionRepository
{
	private DataSource dataSource;
	private static Logger log = Logger.getLogger(EbxStateTransitionRepositoryImpl.class);
	
    public int updateStatusInMstr(SpiderUMRuleMapping spiderUMRuleMapping){
		
		UpdateMstrStatus updateMstrStatus = new UpdateMstrStatus(getDataSource());
		updateMstrStatus.update(spiderUMRuleMapping);
		return 1;
	}
    
    private class UpdateMstrStatus extends SqlUpdate {
		private static final String SQL_QUERY =  "UPDATE BX_SPDR_UM_MAPG SET STATUS =?, " +
				" LAST_UPDT_DATE = sysdate, LAST_UPDT_USER = ?  WHERE  UM_RULE_ID = ? ";

		public UpdateMstrStatus(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("STATUS", Types.VARCHAR));
			declareParameter(new SqlParameter("LAST_UPDT_USER", Types.VARCHAR));
			declareParameter(new SqlParameter("UM_RULE_ID", Types.VARCHAR));
			
			compile();
		}

		public void update(SpiderUMRuleMapping spiderUMRuleMapping) {

			Object[] objs = new Object[] {spiderUMRuleMapping.getUmStatus(),
					spiderUMRuleMapping.getUser().getLastUpdatedUserName(),spiderUMRuleMapping.getUmRuleId() };
			super.update(objs);
		}
	}
    
    public boolean updateBeingModified(SpiderUMRuleMapping spiderUMRuleMapping) {
		
		UpdateMappingTempMstrQuery updateMappingTempMstrQuery = new UpdateMappingTempMstrQuery(
				getDataSource());
		updateMappingTempMstrQuery.update(spiderUMRuleMapping);

		return true;
	}

    private class UpdateMappingTempMstrQuery extends SqlUpdate {
	
	private static final String SQL_QUERY = "UPDATE BX_SPDR_UM_MAPG SET STATUS = ?, LAST_UPDT_DATE= sysdate, LAST_UPDT_USER= ? WHERE UM_RULE_ID = ? ";

	public UpdateMappingTempMstrQuery(DataSource dataSource) {
		super(dataSource, SQL_QUERY);
		
		declareParameter(new SqlParameter("STATUS", Types.VARCHAR));
		declareParameter(new SqlParameter("LAST_UPDT_USER", Types.VARCHAR));
		declareParameter(new SqlParameter("UM_RULE_ID", Types.VARCHAR));
		compile();
	}

	public void update(SpiderUMRuleMapping spiderUMRuleMapping) {
		

		Object[] objs = new Object[] { 
							
				spiderUMRuleMapping.getUmStatus(),
				spiderUMRuleMapping.getUser().getLastUpdatedUserName(),				
				spiderUMRuleMapping.getUmRuleId() };
		super.update(objs);
	  }
    }
    
    public boolean isMappingBeingModified(String ruleId){
		
		String tempFlag = "";
		
		SpiderUMRuleMapping retrieveMapping = null;
		
		final String retrieveQuery = "select STATUS "
			+ "from BX_SPDR_UM_MAPG "
			+ "where UM_RULE_ID = '"
			+ ruleId
			+ "'";			

		log.debug("Query :" + retrieveQuery);
		RetrieveMappingInTempFlag retrieveMappingInTempFlag = new RetrieveMappingInTempFlag(
				dataSource, retrieveQuery);
	
		List resultMappingRetrieveMstr = retrieveMappingInTempFlag.execute();		
		
		if(null != resultMappingRetrieveMstr && resultMappingRetrieveMstr.size() > 0){
            log.debug("*****************ruleList size : "+resultMappingRetrieveMstr.size());            
            
            retrieveMapping = (SpiderUMRuleMapping) resultMappingRetrieveMstr.get(0);
            tempFlag =  retrieveMapping.getUmStatus();
		}
		if(tempFlag.equals(DomainConstants.STATUS_BEING_MODIFIED)) {
			return true;
		}
		return false;
	}
    
    private class RetrieveMappingInTempFlag extends MappingSqlQuery {

		public RetrieveMappingInTempFlag(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpiderUMRuleMapping spiderUMRuleMapping = new SpiderUMRuleMapping();
			spiderUMRuleMapping.setUmStatus(rs.getString("STATUS"));
			return spiderUMRuleMapping;
		}
	}
    

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		EbxStateTransitionRepositoryImpl.log = log;
	}
    

}
