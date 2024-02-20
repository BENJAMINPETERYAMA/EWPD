/*
 * Created on Jun 1, 2010
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

import com.wellpoint.ets.bx.mapping.application.security.ModuleBO;
import com.wellpoint.ets.bx.mapping.application.security.RoleBO;
import com.wellpoint.ets.bx.mapping.application.security.TaskBO;

/**
 * @author u20622
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SecurityRepositoryImpl implements SecurityRepository {

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.SecurityRepository#getModuleBO(long)
	 */
	private DataSource dataSource;


	public List getModuleBO(long id) {

		String query = " select b.MOD_SYS_ID,b.MOD_NM from SCRTY_MOD_INFO b where MOD_SYS_ID "
				+ "in(select a.MOD_SYS_ID from SCRTY_ROLE_MOD_ASSN a where ROLE_SYS_ID = "
				+ id + " ) order by b.MOD_NM asc";

		SecurityModuleQuery moduleQry = new SecurityModuleQuery(dataSource,
				query);
		List moduleList = moduleQry.execute();

		return moduleList;
	}

	private class SecurityModuleQuery extends MappingSqlQuery {

		private SecurityModuleQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

			//mapping.setVariable(new Variable());
			ModuleBO moduleBO = new ModuleBO();
			moduleBO.setModuleId(rs.getInt("MOD_SYS_ID"));
			moduleBO.setModuleName(rs.getString("MOD_NM"));
			return moduleBO;
		}
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.SecurityRepository#getTaskBO(long, long)
	 */
	public List getTaskBO(long moduleId, long roleId) {

		String query = "select a.TASK_SYS_ID, a.TASK_NM from SCRTY_TASK_INFO a,SCRTY_ROLE_MOD_TASK_ASSN b "
				+ " where a.TASK_SYS_ID = b.TASK_SYS_ID and b.ROLE_MOD_ASSN_SYS_ID in(select ROLE_MOD_ASSN_SYS_ID "
				+ " from SCRTY_ROLE_MOD_ASSN where  MOD_SYS_ID = "
				+ moduleId
				+ " and  ROLE_SYS_ID = " + roleId + " )";

		SecurityTaskQuery taskQuery = new SecurityTaskQuery(dataSource, query);
		List taskList = taskQuery.execute();

		return taskList;
	}

	private class SecurityTaskQuery extends MappingSqlQuery {

		private SecurityTaskQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TaskBO taskBO = new TaskBO();
			taskBO.setTaskId(rs.getInt("TASK_SYS_ID"));
			taskBO.setTaskName(rs.getString("TASK_NM"));

			return taskBO;
		}
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

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.SecurityRepository#getRoleId(com.wellpoint.ets.bx.mapping.application.security.RoleBO)
	 */
	public RoleBO getRole(RoleBO roleBO) {

		String query = " SELECT ROLE_SYS_ID, ROLE_NM, ROLE_DESC, LST_CHG_USR, LST_CHG_TMS, CREATD_USER_ID, "
				+ " CREATD_TM_STMP FROM SCRTY_ROLE_INFO WHERE ROLE_NM = '"+roleBO.getRoleName() + "'";

		SecurityRoleQuery roleQuery = new SecurityRoleQuery(dataSource, query);
		List roleList = roleQuery.execute();
		if(roleList.size() > 0) {
			return (RoleBO)roleList.get(0);
		}
		return null;
	}

	private class SecurityRoleQuery extends MappingSqlQuery {

		private SecurityRoleQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

			//mapping.setVariable(new Variable());
			RoleBO roleBO = new RoleBO();
			roleBO.setRoleId((rs.getInt("ROLE_SYS_ID")));
			roleBO.setRoleName(rs.getString("ROLE_NM"));
			return roleBO;
		}
	}
	
	public boolean isDataFeedRunning(){
		
		String query = "select FEED_RUN_FLG from DAT_FEED_STAT where FEED_TYP ='BX'";
		
		DataFeedRunningQuery dataFeedRunningQuery = new DataFeedRunningQuery(dataSource, query);
		
		List dfRunFlagList = dataFeedRunningQuery.execute();
		
		boolean dfRunFlag = false;
		
		if(null != dfRunFlagList){
		
			Integer flag = (Integer)dfRunFlagList.get(0);		
			
			if(flag.equals(Integer.valueOf(1)))
			
				dfRunFlag = true;
		}
		
		return dfRunFlag;
	}	
	private class DataFeedRunningQuery extends MappingSqlQuery {

		private DataFeedRunningQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Integer dfRunFlag = Integer.valueOf(rs.getInt("FEED_RUN_FLG"));
			return dfRunFlag;
		}
	}
}