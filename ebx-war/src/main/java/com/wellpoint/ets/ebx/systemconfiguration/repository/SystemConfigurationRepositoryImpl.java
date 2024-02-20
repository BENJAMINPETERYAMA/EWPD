package com.wellpoint.ets.ebx.systemconfiguration.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

public class SystemConfigurationRepositoryImpl implements ConfigurationInfoRepository {
	
	private DataSource dataSource;
	private String getAllSystemConfigurationQuery;
	private String updateSystemConfigurationQuery;
	private String checkIfSystemConfigurationAlreadyExistsQuery;
	private String deleteSystemConfigurationQuery;
	private String addSystemConfigurationQuery;
	private String loadBackEndRegionDetailsBasedOnSystemQuery;
	private String loadBackEndRegionDetailsBasedOnBackEndQuery;
	private String checkIfSystemConfigurationAlreadyExistsWithNullQuery;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}	

	public String getGetAllSystemConfigurationQuery() {
		return getAllSystemConfigurationQuery;
	}

	public void setGetAllSystemConfigurationQuery(
			String getAllSystemConfigurationQuery) {
		this.getAllSystemConfigurationQuery = getAllSystemConfigurationQuery;
	}

	public String getUpdateSystemConfigurationQuery() {
		return updateSystemConfigurationQuery;
	}

	public void setUpdateSystemConfigurationQuery(
			String updateSystemConfigurationQuery) {
		this.updateSystemConfigurationQuery = updateSystemConfigurationQuery;
	}

	public String getCheckIfSystemConfigurationAlreadyExistsQuery() {
		return checkIfSystemConfigurationAlreadyExistsQuery;
	}

	public void setCheckIfSystemConfigurationAlreadyExistsQuery(
			String checkIfSystemConfigurationAlreadyExistsQuery) {
		this.checkIfSystemConfigurationAlreadyExistsQuery = checkIfSystemConfigurationAlreadyExistsQuery;
	}	

	public String getDeleteSystemConfigurationQuery() {
		return deleteSystemConfigurationQuery;
	}

	public void setDeleteSystemConfigurationQuery(
			String deleteSystemConfigurationQuery) {
		this.deleteSystemConfigurationQuery = deleteSystemConfigurationQuery;
	}

	public String getAddSystemConfigurationQuery() {
		return addSystemConfigurationQuery;
	}

	public void setAddSystemConfigurationQuery(String addSystemConfigurationQuery) {
		this.addSystemConfigurationQuery = addSystemConfigurationQuery;
	}

	public String getLoadBackEndRegionDetailsBasedOnSystemQuery() {
		return loadBackEndRegionDetailsBasedOnSystemQuery;
	}

	public void setLoadBackEndRegionDetailsBasedOnSystemQuery(
			String loadBackEndRegionDetailsBasedOnSystemQuery) {
		this.loadBackEndRegionDetailsBasedOnSystemQuery = loadBackEndRegionDetailsBasedOnSystemQuery;
	}

	public String getLoadBackEndRegionDetailsBasedOnBackEndQuery() {
		return loadBackEndRegionDetailsBasedOnBackEndQuery;
	}

	public void setLoadBackEndRegionDetailsBasedOnBackEndQuery(
			String loadBackEndRegionDetailsBasedOnBackEndQuery) {
		this.loadBackEndRegionDetailsBasedOnBackEndQuery = loadBackEndRegionDetailsBasedOnBackEndQuery;
	}

	public String getCheckIfSystemConfigurationAlreadyExistsWithNullQuery() {
		return checkIfSystemConfigurationAlreadyExistsWithNullQuery;
	}

	public void setCheckIfSystemConfigurationAlreadyExistsWithNullQuery(
			String checkIfSystemConfigurationAlreadyExistsWithNullQuery) {
		this.checkIfSystemConfigurationAlreadyExistsWithNullQuery = checkIfSystemConfigurationAlreadyExistsWithNullQuery;
	}

	@Override
	public List<SystemConfigurationVO> getAllSystemConfigurations() throws SQLException {
		FetchAllSystemConfiguration fetchAllSystemConfiguration = 
			new FetchAllSystemConfiguration(dataSource, getAllSystemConfigurationQuery);
		List<SystemConfigurationVO> systemConfigurationVOList = fetchAllSystemConfiguration.execute();
		return systemConfigurationVOList;
	}
	
	
	private class FetchAllSystemConfiguration extends MappingSqlQuery{
		
		FetchAllSystemConfiguration(DataSource dataSource, String sqlQuery){
			super(dataSource, sqlQuery);
			compile();
		}

		@Override
		protected Object mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			SystemConfigurationVO systemConfigurationVO = new SystemConfigurationVO();
			systemConfigurationVO.setSystemConfigurationID(resultSet.getString("SYSTEM_CONFIGURATION_ID"));
			systemConfigurationVO.setSystem(resultSet.getString("SYSTEM_NAME"));
			systemConfigurationVO.setFunctionality(resultSet.getString("FUNCTIONALITY"));
			systemConfigurationVO.setEnvironment(resultSet.getString("ENVIRONMENT"));
			systemConfigurationVO.setBackEndRegion(resultSet.getString("BACK_END_REGION"));
			systemConfigurationVO.setBackEndRegionDescription(resultSet.getString("BACK_END_REGION_DESCRIPTION"));
			systemConfigurationVO.setSenderID(resultSet.getString("SENDER_ID"));
			systemConfigurationVO.setSourceEnvironment(resultSet.getString("SRC_ENV"));
			systemConfigurationVO.setDestinationEnvironment(resultSet.getString("DEST_ENV"));
			return systemConfigurationVO;
		}
	}

	@Override
	public int addSystemConfiguration(SystemConfigurationVO systemConfigurationVO) throws SQLException {
		int updatedNumberOfRows = 0;
		AddSystemConfiguration addSystemConfiguration = 
			new AddSystemConfiguration(dataSource, addSystemConfigurationQuery);
		updatedNumberOfRows = addSystemConfiguration.insertSystemConfiguration(systemConfigurationVO);		
		return updatedNumberOfRows;
		
	}
	
	private class AddSystemConfiguration extends SqlUpdate{
		private AddSystemConfiguration(DataSource dataSource, String sqlQuery){
			super(dataSource, sqlQuery);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}
		
		protected int insertSystemConfiguration(SystemConfigurationVO configurationVO){
			int updatedNumberOfRows = 0;
			List<Object> parameterList = new ArrayList<Object>();
			parameterList.add(configurationVO.getSystem());
			parameterList.add(configurationVO.getFunctionality());
			parameterList.add(configurationVO.getEnvironment());
			parameterList.add(configurationVO.getBackEndRegion());
			parameterList.add(configurationVO.getBackEndRegionDescription());
			parameterList.add(configurationVO.getSenderID());
			parameterList.add(configurationVO.getSourceEnvironment());			
			parameterList.add(configurationVO.getDestinationEnvironment());
			updatedNumberOfRows = super.update(parameterList.toArray());
			return updatedNumberOfRows;
		}
		
	}
	
	public List<SystemConfigurationVO> checkIfSystemConfigurationAlreadyExists(
			SystemConfigurationVO systemConfigurationVO) throws SQLException{
		
		String sqlQuery = checkIfSystemConfigurationAlreadyExistsQuery;
		String backEndRegion = systemConfigurationVO.getBackEndRegion();
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(systemConfigurationVO.getSystem().toUpperCase());
		parameterList.add(systemConfigurationVO.getFunctionality().toUpperCase());
		parameterList.add(systemConfigurationVO.getEnvironment().toUpperCase());
		if(backEndRegion == null || backEndRegion.trim().equals("") ){
			sqlQuery = checkIfSystemConfigurationAlreadyExistsWithNullQuery;
		}else{
			parameterList.add(systemConfigurationVO.getBackEndRegion().toUpperCase());
		}
		//CheckIFConfigurationExists checkIFConfigurationExists =  new CheckIFConfigurationExists(dataSource,checkIfSystemConfigurationAlreadyExistsQuery);
		CheckIFConfigurationExists checkIFConfigurationExists =  new CheckIFConfigurationExists(dataSource,
				systemConfigurationVO,sqlQuery );
		List<SystemConfigurationVO> systemConfigurationList =  checkIFConfigurationExists.execute(parameterList.toArray());
		return systemConfigurationList;		
	}
	
	private class CheckIFConfigurationExists extends MappingSqlQuery{
		CheckIFConfigurationExists(DataSource dataSource, SystemConfigurationVO systemConfigurationVO, String sqlQuery){
			super(dataSource, sqlQuery);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			String backEndRegion = systemConfigurationVO.getBackEndRegion();
			if(backEndRegion != null && !backEndRegion.trim().equals("") ){
				declareParameter(new SqlParameter(Types.VARCHAR));	
			}		
			compile();
		}
		
		@Override
		protected Object mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			SystemConfigurationVO systemConfigurationVO = new SystemConfigurationVO();
			systemConfigurationVO.setSystemConfigurationID(resultSet.getString("SYSTEM_CONFIGURATION_ID"));
			return systemConfigurationVO;
		}		
	}
	

	@Override
	public int editSystemConfiguration(
			SystemConfigurationVO systemConfigurationVO) throws SQLException {
		int updatedNumberOfRows = 0;
		UpdateSystemConfiguration updateSystemConfiguration = 
			new UpdateSystemConfiguration(dataSource,updateSystemConfigurationQuery);
		updatedNumberOfRows =  updateSystemConfiguration.update(systemConfigurationVO);
		return updatedNumberOfRows;
	}
	
	private class UpdateSystemConfiguration extends SqlUpdate{
		private UpdateSystemConfiguration(DataSource dataSource, String sqlQuery){
			super(dataSource, sqlQuery);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.INTEGER));
			compile();
		}
		
		protected int update(SystemConfigurationVO configurationVO){
			int updatedNumberOfRows = 0;
			List<Object> parameterList = new ArrayList<Object>();
			parameterList.add(configurationVO.getSystem());
			parameterList.add(configurationVO.getFunctionality());
			parameterList.add(configurationVO.getEnvironment());
			parameterList.add(configurationVO.getBackEndRegion());
			parameterList.add(configurationVO.getBackEndRegionDescription());
			parameterList.add(configurationVO.getSenderID());
			parameterList.add(configurationVO.getSourceEnvironment());			
			parameterList.add(configurationVO.getDestinationEnvironment());
			parameterList.add(configurationVO.getSystemConfigurationID());
			updatedNumberOfRows = super.update(parameterList.toArray());
			return updatedNumberOfRows;
		}
		
	}

	@Override
	public int deleteSystemConfiguration(int systemConfigurationID) throws SQLException {
		int updatedNumberOfRows = 0;
		DeleteSystemConfiguration deleteSystemConfiguration = 
			new DeleteSystemConfiguration(dataSource,deleteSystemConfigurationQuery);
		updatedNumberOfRows =  deleteSystemConfiguration.deleteConfiguration(systemConfigurationID);
		return updatedNumberOfRows;
	}
	
	private class DeleteSystemConfiguration extends SqlUpdate{
		private DeleteSystemConfiguration(DataSource dataSource, String sqlQuery){
			super(dataSource, sqlQuery);
			declareParameter(new SqlParameter(Types.INTEGER));
			compile();
		}
		protected int deleteConfiguration(int systemConfigurationID){
			int updatedNoOfRows = 0;
			List<Object> parameterList = new ArrayList<Object>();
			parameterList.add(systemConfigurationID);
			updatedNoOfRows = super.update(parameterList.toArray());
			return updatedNoOfRows;
		}
	}

	@Override
	public List<SystemConfigurationVO> getBackEndRegionDescription(
			SystemConfigurationVO systemConfigurationVO) throws SQLException {
		List<SystemConfigurationVO> systemConfigurationVOList = new ArrayList<SystemConfigurationVO>();		
		LoadBackEndRegionDesciption loadBackEndRegionDesciption = 
			new LoadBackEndRegionDesciption(dataSource,loadBackEndRegionDetailsBasedOnBackEndQuery);
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(systemConfigurationVO.getSystem().toUpperCase());
		parameterList.add(systemConfigurationVO.getFunctionality().toUpperCase());
		parameterList.add(systemConfigurationVO.getEnvironment().toUpperCase());
		parameterList.add(systemConfigurationVO.getBackEndRegion().toUpperCase());
		systemConfigurationVOList = loadBackEndRegionDesciption.execute(parameterList.toArray());
		return systemConfigurationVOList;
	}

	
	private class LoadBackEndRegionDesciption extends MappingSqlQuery{
		LoadBackEndRegionDesciption(DataSource dataSource, String sqlQuery){
			super(dataSource, sqlQuery);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}
		
		@Override
		protected Object mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			SystemConfigurationVO systemConfigurationVO = new SystemConfigurationVO();
			systemConfigurationVO.setSystemConfigurationID(resultSet.getString("SYSTEM_CONFIGURATION_ID"));
			systemConfigurationVO.setBackEndRegion(resultSet.getString("BACK_END_REGION"));
			systemConfigurationVO.setBackEndRegionDescription(resultSet.getString("BACK_END_REGION_DESCRIPTION"));
			systemConfigurationVO.setSenderID(resultSet.getString("SENDER_ID"));
			systemConfigurationVO.setSourceEnvironment(resultSet.getString("SRC_ENV"));
			systemConfigurationVO.setDestinationEnvironment(resultSet.getString("DEST_ENV"));
			return systemConfigurationVO;
		}		
	}
	
	@Override
	public List<SystemConfigurationVO> loadBackEndRegionBasedOnSystem(String functionality,
			String system, String environment) throws SQLException {
		List<SystemConfigurationVO> systemConfigurationVOList = new ArrayList<SystemConfigurationVO>();
		LoadBackEndRegions loadBackEndRegions = 
			new LoadBackEndRegions(dataSource, loadBackEndRegionDetailsBasedOnSystemQuery);
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(system.toUpperCase());		
		parameterList.add(functionality.toUpperCase());
		parameterList.add(environment.toUpperCase());
		systemConfigurationVOList = loadBackEndRegions.execute(parameterList.toArray());
		return systemConfigurationVOList;
	}
	
	private class LoadBackEndRegions extends MappingSqlQuery{
		LoadBackEndRegions(DataSource dataSource, String sqlQuery){
			super(dataSource, sqlQuery);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}
		
		@Override
		protected Object mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			SystemConfigurationVO systemConfigurationVO = new SystemConfigurationVO();
			systemConfigurationVO.setSystemConfigurationID(resultSet.getString("SYSTEM_CONFIGURATION_ID"));
			systemConfigurationVO.setBackEndRegion(resultSet.getString("BACK_END_REGION"));
			systemConfigurationVO.setBackEndRegionDescription(resultSet.getString("BACK_END_REGION_DESCRIPTION"));
			systemConfigurationVO.setSenderID(resultSet.getString("SENDER_ID"));
			systemConfigurationVO.setSourceEnvironment(resultSet.getString("SRC_ENV"));
			systemConfigurationVO.setDestinationEnvironment(resultSet.getString("DEST_ENV"));
			return systemConfigurationVO;
		}		
	}
}
