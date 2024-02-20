/*
 * <ReferenceDataRepositoryImpl.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.referencedata.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepositoryImpl;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.ebx.mapping.repository.ItemRepository;
import com.wellpoint.ets.ebx.referencedata.util.ReferenceDataUtil;
import com.wellpoint.ets.ebx.referencedata.vo.CategoryServiceTypeMappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.CategoryVariableMappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.DataTypeToEB01MappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.ErrorCodeVO;
import com.wellpoint.ets.ebx.referencedata.vo.ErrorExclusionVO;
import com.wellpoint.ets.ebx.referencedata.vo.HeaderRuleToEB03MappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.MinorHeadingCatagoryCdeMappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.ReferenceDataValueVO;
import com.wellpoint.ets.ebx.referencedata.vo.ServiceTypeCodeToEB11VO;
import com.wellpoint.ets.ebx.referencedata.vo.ServiceTypeMappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.ServiceTypeToEB11DataObject;
import com.wellpoint.ets.ebx.referencedata.vo.StandardMessageVO;

/**
 * @author UST Global - www.ust-global.com <br />
 * @version $Id: $
 */
public class ReferenceDataRepositoryImpl implements ReferenceDataRepository {

	/**
	 * Comment for <code>dataSource</code> Data source.
	 */
	private DataSource dataSource;

	/**
	 * Comment for <code>exclusionCodeFetchQuery</code> Exclusion code fetch
	 * query.
	 */
	private String exclusionCodeFetchQuery;

	/**
	 * Comment for <code>exclusionAllFetchQuery</code>
	 */
	private String exclusionAllFetchQuery;

	/**
	 * Comment for <code>auditTrailUpdateQuery</code> Audit trail update query.
	 */
	private String auditTrailUpdateQuery;

	/**
	 * Comment for <code>auditTrailInsertQuery</code> Audit trail insert query.
	 */
	private String auditTrailInsertQuery;

	/**
	 * Comment for <code>exclusionInsertQuery</code> Exclusion insert query.
	 */
	private String exclusionInsertQuery;

	/**
	 * Comment for <code>exclusionUpdateQuery</code> Exclusion Update query.
	 */
	private String exclusionUpdateQuery;

	/**
	 * Comment for <code>exclusionIDSequenceQuery</code> To fetch from the
	 * sequence.
	 */
	private String exclusionIDSequenceQuery;

	/**
	 * Comment for <code>errorCodeDeleteQuery</code>
	 */
	private String errorCodeDeleteQueryAudit;

	/**
	 * Comment for <code>exclusionDeleteQuery</code>
	 */
	private String exclusionDeleteQuery;

	/**
	 * Comment for <code>errorCodeDeleteQueryExclusions</code>
	 */
	private String errorCodeDeleteQueryExclusions;

	/**
	 * Comment for <code>auditTrailFetchQuery</code>
	 */
	private String auditTrailFetchQuery;

	/**
	 * Comment for <code>totalCountExclusionQuery</code>
	 */
	private String totalCountExclusionQuery;

	private String minorHeadingCatagoryCodeMappingQurry;

	/**
	 * @return Returns the minorHeadingCatagoryCodeMappingQurry.
	 */
	public String getMinorHeadingCatagoryCodeMappingQurry() {
		return minorHeadingCatagoryCodeMappingQurry;
	}

	/**
	 * @param minorHeadingCatagoryCodeMappingQurry
	 *            The minorHeadingCatagoryCodeMappingQurry to set.
	 */
	public void setMinorHeadingCatagoryCodeMappingQurry(
			String minorHeadingCatagoryCodeMappingQurry) {
		this.minorHeadingCatagoryCodeMappingQurry = minorHeadingCatagoryCodeMappingQurry;
	}
	
	public String getCategoryMappingDeleteQuery() {
		return categoryMappingDeleteQuery;
	}

	public void setCategoryMappingDeleteQuery(String categoryMappingDeleteQuery) {
		this.categoryMappingDeleteQuery = categoryMappingDeleteQuery;
	}

	public String getInsertCategoryEB03MappingQuery() {
		return insertCategoryEB03MappingQuery;
	}

	public void setInsertCategoryEB03MappingQuery(
			String insertCategoryEB03MappingQuery) {
		this.insertCategoryEB03MappingQuery = insertCategoryEB03MappingQuery;
	}

	public String getDeleteTempCategoryEB03MappingQuery() {
		return deleteTempCategoryEB03MappingQuery;
	}

	public void setDeleteTempCategoryEB03MappingQuery(
			String deleteTempCategoryEB03MappingQuery) {
		this.deleteTempCategoryEB03MappingQuery = deleteTempCategoryEB03MappingQuery;
	}

	private String categoryMappingDeleteQuery;
	private String insertCategoryEB03MappingQuery;
	private String deleteTempCategoryEB03MappingQuery;
	private HashMap eb03Map = new HashMap();

	private ItemRepository itemRepository;

	public ItemRepository getItemRepository() {
		return itemRepository;
	}

	public void setItemRepository(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	/**
	 * Comment for <code>logger</code> Logger class.
	 */
	private static Logger logger = Logger
			.getLogger(ReferenceDataRepositoryImpl.class);
	
/*******************Manage Header Rule EB03 Associations April 2012 Release SSCR14181 START******************/
	private String headerRuleToEB03DeleteAllQuery;

	private String headerRuleToEB03ConditionalDeleteQuery;	

	private String manageHeaderRuleToEB03AuditTrialQuery;

	private String headerRuleToEB03InsertQuery;
		/**
		 * @return Returns the headerRuleToEB03DeleteAllQuery.
		 */
		public String getHeaderRuleToEB03DeleteAllQuery() {
			return headerRuleToEB03DeleteAllQuery;
		}

		public void setHeaderRuleToEB03DeleteAllQuery(
				String headerRuleToEB03DeleteAllQuery) {
			this.headerRuleToEB03DeleteAllQuery = headerRuleToEB03DeleteAllQuery;
		}
		
		/**
		 * @return Returns the headerRuleToEB03ConditionalDeleteQuery.
		 */
		
		public String getHeaderRuleToEB03ConditionalDeleteQuery() {
			return headerRuleToEB03ConditionalDeleteQuery;
		}

		public void setHeaderRuleToEB03ConditionalDeleteQuery(
				String headerRuleToEB03ConditionalDeleteQuery) {
			this.headerRuleToEB03ConditionalDeleteQuery = headerRuleToEB03ConditionalDeleteQuery;
		}

		/**
		 * @return Returns the manageHeaderRuleToEB03AuditTrialQuery.
		 */
		
		public String getManageHeaderRuleToEB03AuditTrialQuery() {
			return manageHeaderRuleToEB03AuditTrialQuery;
		}

		public void setManageHeaderRuleToEB03AuditTrialQuery(
				String manageHeaderRuleToEB03AuditTrialQuery) {
			this.manageHeaderRuleToEB03AuditTrialQuery = manageHeaderRuleToEB03AuditTrialQuery;
		}
		
		/**
		 * @return Returns the headerRuleToEB03InsertQuery.
		 */
		
		public String getHeaderRuleToEB03InsertQuery() {
			return headerRuleToEB03InsertQuery;
		}

		public void setHeaderRuleToEB03InsertQuery(String headerRuleToEB03InsertQuery) {
			this.headerRuleToEB03InsertQuery = headerRuleToEB03InsertQuery;
		}

/***************Manage Header Rule EB03 Associations April 2012 Release SSCR14181 END********************/
	
	// April 2012 - Eb01- data type- start
	private String eB01ToDataTypeAssnDeleteQuery;
	private String manageDatatypeToEB01AuditTrialQuery;
	private String dataTypeValueQueryForEdit;
	private String manageDatatypeToEB01InsertQueryForSave;
	private String manageDatatypeToEB01DeleteQueryForSave;
	private String dataTypeToEB01HistoryFetchQuery;

	public String getDataTypeToEB01HistoryFetchQuery() {
		return dataTypeToEB01HistoryFetchQuery;
	}

	public void setDataTypeToEB01HistoryFetchQuery(
			String dataTypeToEB01HistoryFetchQuery) {
		this.dataTypeToEB01HistoryFetchQuery = dataTypeToEB01HistoryFetchQuery;
	}

	public String getManageDatatypeToEB01DeleteQueryForSave() {
		return manageDatatypeToEB01DeleteQueryForSave;
	}

	public void setManageDatatypeToEB01DeleteQueryForSave(
			String manageDatatypeToEB01DeleteQueryForSave) {
		this.manageDatatypeToEB01DeleteQueryForSave = manageDatatypeToEB01DeleteQueryForSave;
	}

	public String getManageDatatypeToEB01InsertQueryForSave() {
		return manageDatatypeToEB01InsertQueryForSave;
	}

	public void setManageDatatypeToEB01InsertQueryForSave(
			String manageDatatypeToEB01InsertQueryForSave) {
		this.manageDatatypeToEB01InsertQueryForSave = manageDatatypeToEB01InsertQueryForSave;
	}

	public String getDataTypeValueQueryForEdit() {
		return dataTypeValueQueryForEdit;
	}

	public void setDataTypeValueQueryForEdit(String dataTypeValueQueryForEdit) {
		this.dataTypeValueQueryForEdit = dataTypeValueQueryForEdit;
	}

	public String getEB01ToDataTypeAssnDeleteQuery() {
		return eB01ToDataTypeAssnDeleteQuery;
	}

	public void setEB01ToDataTypeAssnDeleteQuery(String eB01ToDataTypeAssnDeleteQuery) {
		this.eB01ToDataTypeAssnDeleteQuery = eB01ToDataTypeAssnDeleteQuery;
	}

	public String getManageDatatypeToEB01AuditTrialQuery() {
		return manageDatatypeToEB01AuditTrialQuery;
	}

	public void setManageDatatypeToEB01AuditTrialQuery(
			String manageDatatypeToEB01AuditTrialQuery) {
		this.manageDatatypeToEB01AuditTrialQuery = manageDatatypeToEB01AuditTrialQuery;
	}
	// April Release Eb01 - data type - End

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.repository.ReferenceDataRepository#fetchErrorCodes(java.lang.String)
	 * @param matchErrorCode
	 * @return Function which fetches the list of error codes for auto complete.
	 */
	public List fetchErrorCodes(String matchValue) {
		ErrorCodeFetchQuery errorCodeFetchQuery = new ErrorCodeFetchQuery(
				dataSource, getExclusionQuery(matchValue));
		List errorCodes = errorCodeFetchQuery.execute();
		logger.info(errorCodes);
		return errorCodes;
	}

	public ErrorCodeVO fetchAuditTrail(String errorCode) {
		AuditTrailFetchQuery auditTrailFetchQuery = new AuditTrailFetchQuery(
				dataSource);
		ErrorCodeVO errorCodeVO = new ErrorCodeVO();
		Object[] inputParams = new Object[] { errorCode };
		List auditTrailList = auditTrailFetchQuery.execute(inputParams);
		// Audit trail list will be greater than 1 if error code exists in the
		// DB. if comment is NULL, still NULL would be returned.
		if (null != auditTrailList && auditTrailList.size() > 0) {
			errorCodeVO = new ErrorCodeVO();
			errorCodeVO.setAuditTrail((String) auditTrailList.get(0));
		}
		return errorCodeVO;
	}

	/**
	 * @param errorExclusionVO
	 * @return Used for other DB validations if any in the future.
	 */
	public int getTotalCountForExclusionForValidation(
			ErrorExclusionVO errorExclusionVO) {
		TotalCountFetchQuery countFetchQuery = new TotalCountFetchQuery(
				dataSource);
		// String errorCode = DomainConstants.EMPTY;
		int exclusionId = 0;
		int totCount = 0;
		if (null != errorExclusionVO) {
			exclusionId = errorExclusionVO.getExclusionId();
			// errorCode = errorExclusionVO.getErrorCode();
		}
		Object[] inputParams = new Object[] { Integer.valueOf(exclusionId) };
		List countList = countFetchQuery.execute(inputParams);
		Integer cnt;
		if (null != countList && countList.size() > 0) {
			cnt = (Integer) countList.get(0);
			totCount = cnt.intValue();
		}

		return totCount;
	}

	public List getMinorHeadingCatagoryCodeMapping() {
		MinorHeadingCatagoryCodeMappingQuery mnrHdgQuesry = new MinorHeadingCatagoryCodeMappingQuery(
				dataSource);
		List mapingList = mnrHdgQuesry.execute();
		return mapingList;
	}

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.repository.ReferenceDataRepository#fetchExclusions(java.lang.String)
	 * @param errorCode
	 * @return List. This function returns an error code which contains the
	 *         audit trail and the error VO list.
	 */
	public ErrorCodeVO fetchExclusions(String errorCode) {
		ErrorCodeVO errorCodeVO = new ErrorCodeVO();
		// Fetch exclusions.
		ExclusionCodeFetchQuery exclusionCodeFetchQuery = new ExclusionCodeFetchQuery(
				dataSource);
		Object[] inputParams = new Object[] { errorCode };
		List exclusionsList = exclusionCodeFetchQuery.execute(inputParams);
		errorCodeVO.setErrorCode(errorCode);
		errorCodeVO.setExclusionList(exclusionsList);
		return errorCodeVO;
	}

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.repository.ReferenceDataRepository#deleteExclusion(com.wellpoint.ets.ebx.referencedata.vo.ErrorCodeVO)
	 * @param errorCodeVO
	 * @return
	 */
	public boolean deleteExclusion(ErrorCodeVO errorCodeVO) {
		if (null == errorCodeVO || null == errorCodeVO.getExclusionList()
				|| errorCodeVO.getExclusionList().size() == 0) {
			return false;
		}
		ErrorExclusionVO errorExclusionVO = (ErrorExclusionVO) errorCodeVO
				.getExclusionList().get(0);
		if (null != errorExclusionVO) {
			ExclusionDeleteQuery exclusionDeleteQuery = new ExclusionDeleteQuery(
					dataSource, getExclusionDeleteQuery());
			exclusionDeleteQuery.update(Integer.valueOf(errorExclusionVO
					.getExclusionId()));
			AuditTrailUpdateQuery auditTrailUpdateQuery = new AuditTrailUpdateQuery(
					dataSource);
			auditTrailUpdateQuery.update(errorCodeVO);
		} else {
			return false;
		}
		return true;
	}

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.repository.ReferenceDataRepository#deleteErrorCode(java.lang.String)
	 * @param errorCode
	 * @return
	 */
	public boolean deleteAllExclusions(ErrorCodeVO errorCodeVO) {
		if (errorCodeVO == null) {
			return false;
		}
		AuditTrailUpdateQuery auditTrailUpdateQuery = new AuditTrailUpdateQuery(
				dataSource);
		int numRowsDeleted = auditTrailUpdateQuery.update(errorCodeVO);
		if (numRowsDeleted == 0) {
			errorCodeVO
					.setOperationMessages("The error code do not exist in the database");
			return true;
		}
		ErrorCodeDeleteQuery exclusionDeleteQueryOne = new ErrorCodeDeleteQuery(
				dataSource, getErrorCodeDeleteQueryExclusions());
		int numRowsAfffected = exclusionDeleteQueryOne.update(errorCodeVO
				.getErrorCode());
		logger.info("Number of rows affected by Delete all" + numRowsAfffected);
		if (numRowsAfffected == 0) {
			errorCodeVO
					.setOperationMessages("No exclusions exist for the error code");
		}
		return true;
	}

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.repository.ReferenceDataRepository#saveExclusion(com.wellpoint.ets.ebx.referencedata.vo.ErrorCodeVO)
	 * @param errorCodeVO
	 * @return
	 */
	public boolean saveExclusion(ErrorCodeVO errorCodeVO) {
		if (null == errorCodeVO || null == errorCodeVO.getExclusionList()
				|| errorCodeVO.getExclusionList().size() == 0) {
			return false;
		}
		AuditTrailUpdateQuery auditTrailUpdateQuery = new AuditTrailUpdateQuery(
				dataSource);
		int numRowsAffectedError = auditTrailUpdateQuery.update(errorCodeVO);
		if (numRowsAffectedError != 1) {
			AuditTrailInsertQuery auditTrailInsertQuery = new AuditTrailInsertQuery(
					dataSource);
			numRowsAffectedError = auditTrailInsertQuery.update(errorCodeVO);
		}
		// Insert or update successful
		if (numRowsAffectedError == 1) {
			// Update or Insert Exclusions as well.
			ErrorExclusionVO errorExclusionVO = (ErrorExclusionVO) errorCodeVO
					.getExclusionList().get(0);
			if (null != errorExclusionVO) {
				int numRowsAffectedExclusion = 0;
				if (0 == errorExclusionVO.getExclusionId()) {
					// Insert
					ExclusionSequenceFetchQuery exclusionSequenceFetchQuery = new ExclusionSequenceFetchQuery(
							dataSource);
					List sequenceList = exclusionSequenceFetchQuery.execute();
					Integer sequence;
					if (null != sequenceList) {
						sequence = (Integer) sequenceList.get(0);
						errorExclusionVO.setExclusionId(sequence.intValue());
					}
					logger
							.info("The new Exception ID created for insert action:"
									+ errorExclusionVO.getExclusionId());
					ExclusionInsertQuery exclusionInsertQuery = new ExclusionInsertQuery(
							dataSource);
					numRowsAffectedExclusion = exclusionInsertQuery
							.update(errorExclusionVO);
				} else {
					// Update
					logger.info("The Exception ID getting updated:"
							+ errorExclusionVO.getExclusionId());
					ExclusionUpdateQuery exclusionUpdateQuery = new ExclusionUpdateQuery(
							dataSource);
					numRowsAffectedExclusion = exclusionUpdateQuery
							.update(errorExclusionVO);
				}
				logger.info("Number of rows affected by Update"
						+ numRowsAffectedExclusion);
				if (numRowsAffectedExclusion == 1) {
					return true;
				}

			}
		}
		return false;
	}

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.repository.ReferenceDataRepository#fetchAllExclusions()
	 * @return List. Fetch all exclusions.
	 */
	public List fetchAllExclusions() {
		ExclusionAllFetchQuery exclusionCodeFetchQuery = new ExclusionAllFetchQuery(
				dataSource);
		List exclusionList = exclusionCodeFetchQuery.execute();
		return exclusionList;
	}

	/**
	 * @author UST Global - www.ust-global.com <br />
	 * @version $Id: $ ExclusionCodeFetchQuery.
	 */
	private class ExclusionCodeFetchQuery extends MappingSqlQuery {

		/**
		 * @param dataSource
		 */
		public ExclusionCodeFetchQuery(DataSource dataSource) {
			super(dataSource, getExclusionCodeFetchQuery());
			declareParameter(new SqlParameter(Types.VARCHAR));
		}

		/**
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 * @param arg0
		 * @param arg1
		 * @return
		 * @throws SQLException
		 *             Map row. Query is available at dao-config.xml.
		 */
		public Object mapRow(ResultSet resultSet, int rowCount)
				throws SQLException {
			ErrorExclusionVO exclusionVO = new ErrorExclusionVO();
			exclusionVO.setExclusionId(resultSet.getInt(1));
			exclusionVO.setErrorCode(resultSet.getString(2));
			exclusionVO.setPrimaryExclusion(resultSet.getString(3));
			exclusionVO.setPrimaryValues(resultSet.getString(4));
			exclusionVO.setSecondaryExclusion(resultSet.getString(5));
			exclusionVO.setSecondaryValues(resultSet.getString(6));
			exclusionVO.setExclusionCount(resultSet.getInt(7));
			return exclusionVO;

		}
	}

	/**
	 * @author UST Global - www.ust-global.com <br />
	 * @version $Id: $ ExclusionCodeFetchQuery.
	 */
	private class ExclusionAllFetchQuery extends MappingSqlQuery {
		/**
		 * @param dataSource
		 */
		public ExclusionAllFetchQuery(DataSource dataSource) {
			super(dataSource, getExclusionAllFetchQuery());
		}

		/**
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 * @param arg0
		 * @param arg1
		 * @return
		 * @throws SQLException
		 *             Map row. Query is available at dao-config.xml.
		 */
		public Object mapRow(ResultSet resultSet, int rowCount)
				throws SQLException {
			ErrorExclusionVO exclusionVO = new ErrorExclusionVO();
			exclusionVO.setExclusionId(resultSet.getInt(1));
			exclusionVO.setErrorCode(resultSet.getString(2));
			exclusionVO.setPrimaryExclusion(resultSet.getString(3));
			exclusionVO.setPrimaryValues(resultSet.getString(4));
			exclusionVO.setSecondaryExclusion(resultSet.getString(5));
			exclusionVO.setSecondaryValues(resultSet.getString(6));
			exclusionVO.setExclusionCount(resultSet.getInt(7));
			return exclusionVO;

		}
	}

	/**
	 * @author UST Global - www.ust-global.com <br />
	 * @version $Id: $ To get the audit trail.
	 */
	private class AuditTrailFetchQuery extends MappingSqlQuery {
		private AuditTrailFetchQuery(DataSource dataSource) {
			super(dataSource, getAuditTrailFetchQuery());
			declareParameter(new SqlParameter(Types.VARCHAR));
		}

		/**
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 * @param resultSet
		 * @param rowCount
		 * @return
		 * @throws SQLException
		 */
		public Object mapRow(ResultSet resultSet, int rowCount)
				throws SQLException {
			return resultSet.getString(1);
		}
	}

	/**
	 * @author UST Global - www.ust-global.com <br />
	 * @version $Id: $ To get the audit trail.
	 */
	private class TotalCountFetchQuery extends MappingSqlQuery {
		/**
		 * @param dataSource
		 */
		private TotalCountFetchQuery(DataSource dataSource) {
			super(dataSource, getTotalCountExclusionQuery());
			declareParameter(new SqlParameter(Types.INTEGER));
		}

		/**
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 * @param resultSet
		 * @param rowCount
		 * @return
		 * @throws SQLException
		 */
		public Object mapRow(ResultSet resultSet, int rowCount)
				throws SQLException {
			return Integer.valueOf(resultSet.getInt(1));
		}

	}

	/**
	 * @author UST Global - www.ust-global.com <br />
	 * @version $Id: $ To get the audit trail.
	 */
	private class MinorHeadingCatagoryCodeMappingQuery extends MappingSqlQuery {
		/**
		 * @param dataSource
		 */
		private MinorHeadingCatagoryCodeMappingQuery(DataSource dataSource) {
			super(dataSource, getMinorHeadingCatagoryCodeMappingQurry());
		}

		/**
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 * @param resultSet
		 * @param rowCountO
		 * @return
		 * @throws SQLException
		 */
		public Object mapRow(ResultSet resultSet, int rowCount)
				throws SQLException {
			MinorHeadingCatagoryCdeMappingVO mnr = new MinorHeadingCatagoryCdeMappingVO();
			mnr.setSystem(resultSet.getString("SYSTEM"));
			mnr.setMinorHeading(resultSet.getString("MINOR"));
			mnr.setCategoryCode(resultSet.getString("CATAGORY"));
			return mnr;
		}

	}

	/**
	 * @author UST Global - www.ust-global.com <br />
	 * @version $Id: $ ExclusionCodeFetchQuery.
	 */
	private class ExclusionSequenceFetchQuery extends MappingSqlQuery {

		public ExclusionSequenceFetchQuery(DataSource dataSource) {
			super(dataSource, getExclusionIDSequenceQuery());
		}

		/**
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 * @param arg0
		 * @param arg1
		 * @return
		 * @throws SQLException
		 *             Map row. Query is available at dao-config.xml.
		 */
		public Object mapRow(ResultSet resultSet, int rowCount)
				throws SQLException {
			return Integer.valueOf(resultSet.getInt(1));
		}

	}

	/**
	 * @author UST Global - www.ust-global.com <br />
	 * @version $Id: $ Inner class for Error code fetch. Returns list of Error
	 *          codes.
	 */
	private class ErrorCodeFetchQuery extends MappingSqlQuery {
		public ErrorCodeFetchQuery(DataSource dataSource, String exclusionQuery) {
			super(dataSource, exclusionQuery);
		}

		/**
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 * @param resultSet
		 * @param arg1
		 * @return
		 * @throws SQLException
		 *             Map row.
		 */
		public Object mapRow(ResultSet resultSet, int rowCount)
				throws SQLException {
			return resultSet.getString(1);
		}

	}

	/**
	 * @author UST Global - www.ust-global.com <br />
	 * @version $Id: $ Error code delete query.
	 */
	public class ErrorCodeDeleteQuery extends SqlUpdate {
		/**
		 * @param dataSource
		 */
		public ErrorCodeDeleteQuery(DataSource dataSource, String sqlQuery) {
			super(dataSource, sqlQuery);
			declareParameter(new SqlParameter("ERROR_CD", Types.VARCHAR));
			compile();
		}

		/**
		 * @see org.springframework.jdbc.object.SqlUpdate#update(java.lang.String)
		 * @param errorCode
		 * @return update.
		 */
		public int update(String errorCode) {
			Object[] objs = new Object[] { errorCode };
			return super.update(objs);
		}
	}

	/**
	 * @author UST Global - www.ust-global.com <br />
	 * @version $Id: $ Error code delete query.
	 */
	private class ExclusionDeleteQuery extends SqlUpdate {

		/**
		 * @param dataSource
		 */
		private ExclusionDeleteQuery(DataSource dataSource, String sqlQuery) {
			super(dataSource, sqlQuery);
			declareParameter(new SqlParameter("EXCLUSION_ID", Types.INTEGER));
			compile();
		}

		/**
		 * @param exclusionId
		 * @return
		 */
		private int update(Integer exclusionId) {
			Object[] objs = new Object[] { exclusionId };
			return super.update(objs);
		}

	}

	/**
	 * @author UST Global - www.ust-global.com <br />
	 * @version $Id: $
	 */
	private class ExclusionUpdateQuery extends SqlUpdate {

		/**
		 * @param dataSource
		 */
		private ExclusionUpdateQuery(DataSource dataSource) {
			super(dataSource, getExclusionUpdateQuery());
			declareParameter(new SqlParameter("ERROR_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("EXCLUSION_TYPE_PRIMARY",
					Types.VARCHAR));
			declareParameter(new SqlParameter("EXCLUSION_VAL_PRIMARY",
					Types.VARCHAR));
			declareParameter(new SqlParameter("EXCLUSION_TYPE_SECONDARY",
					Types.VARCHAR));
			declareParameter(new SqlParameter("EXCLUSION_VAL_SECONDARY",
					Types.VARCHAR));
			declareParameter(new SqlParameter("EXCLUSION_COUNT", Types.INTEGER));
			declareParameter(new SqlParameter("LAST_UPDATED_USER_ID",
					Types.VARCHAR));
			declareParameter(new SqlParameter("EXCLUSION_ID", Types.INTEGER));
			compile();
		}

		/**
		 * @param errorExclusionVO
		 * @return
		 */
		private int update(ErrorExclusionVO errorExclusionVO) {
			Object[] objs = new Object[] { errorExclusionVO.getErrorCode(),
					errorExclusionVO.getPrimaryExclusion(),
					errorExclusionVO.getPrimaryValues(),
					errorExclusionVO.getSecondaryExclusion(),
					errorExclusionVO.getSecondaryValues(),
					Integer.valueOf(errorExclusionVO.getExclusionCount()),
					errorExclusionVO.getLastUpdatedUser(),
					Integer.valueOf(errorExclusionVO.getExclusionId()), };
			return super.update(objs);
		}

	}

	/**
	 * @author UST Global - www.ust-global.com <br />
	 * @version $Id: $
	 */
	private class AuditTrailUpdateQuery extends SqlUpdate {

		/**
		 * @param dataSource
		 */
		private AuditTrailUpdateQuery(DataSource dataSource) {
			super(dataSource, getAuditTrailUpdateQuery());
			declareParameter(new SqlParameter("COMMENTS_LOG", Types.CLOB));
			declareParameter(new SqlParameter("LAST_UPDATED_USER_ID",
					Types.VARCHAR));
			declareParameter(new SqlParameter("ERROR_CD", Types.VARCHAR));
			compile();
		}

		private int update(ErrorCodeVO errorCodeVO) {
			Object[] objs = new Object[] { errorCodeVO.getAuditTrail(),
					errorCodeVO.getLastUpdatedUser(),
					errorCodeVO.getErrorCode() };
			return super.update(objs);
		}

	}

	/**
	 * @author UST Global - www.ust-global.com <br />
	 * @version $Id: $
	 */
	private class ExclusionInsertQuery extends SqlUpdate {

		/**
		 * @param dataSource
		 */
		private ExclusionInsertQuery(DataSource dataSource) {
			super(dataSource, getExclusionInsertQuery());
			declareParameter(new SqlParameter("EXCLUSION_ID", Types.INTEGER));
			declareParameter(new SqlParameter("ERROR_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("EXCLUSION_TYPE_PRIMARY",
					Types.VARCHAR));
			declareParameter(new SqlParameter("EXCLUSION_VAL_PRIMARY",
					Types.VARCHAR));
			declareParameter(new SqlParameter("EXCLUSION_TYPE_SECONDARY",
					Types.VARCHAR));
			declareParameter(new SqlParameter("EXCLUSION_VAL_SECONDARY",
					Types.VARCHAR));
			declareParameter(new SqlParameter("EXCLUSION_COUNT", Types.INTEGER));
			declareParameter(new SqlParameter("CREATED_USER_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("LAST_UPDATED_USER_ID",
					Types.VARCHAR));
			compile();
		}

		/**
		 * @param errorExclusionVO
		 * @return
		 */
		private int update(ErrorExclusionVO errorExclusionVO) {
			Object[] objs = new Object[] {
					Integer.valueOf(errorExclusionVO.getExclusionId()),
					errorExclusionVO.getErrorCode(),
					errorExclusionVO.getPrimaryExclusion(),
					errorExclusionVO.getPrimaryValues(),
					errorExclusionVO.getSecondaryExclusion(),
					errorExclusionVO.getSecondaryValues(),
					Integer.valueOf(errorExclusionVO.getExclusionCount()),
					errorExclusionVO.getCreatedUser(),
					errorExclusionVO.getLastUpdatedUser() };
			return super.update(objs);
		}

	}

	/**
	 * @author UST Global - www.ust-global.com <br />
	 * @version $Id: $
	 */
	private class AuditTrailInsertQuery extends SqlUpdate {
		/**
		 * @param dataSource
		 */
		private AuditTrailInsertQuery(DataSource dataSource) {
			super(dataSource, getAuditTrailInsertQuery());
			declareParameter(new SqlParameter("ERROR_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("COMMENTS_LOG", Types.CLOB));
			declareParameter(new SqlParameter("CREATED_USER_ID", Types.VARCHAR));
			declareParameter(new SqlParameter("LAST_UPDATED_USER_ID",
					Types.VARCHAR));
			compile();
		}

		/**
		 * @param errorCodeVO
		 * @return
		 */
		private int update(ErrorCodeVO errorCodeVO) {
			Object[] objs = new Object[] { errorCodeVO.getErrorCode(),
					errorCodeVO.getAuditTrail(), errorCodeVO.getCreatedUser(),
					errorCodeVO.getLastUpdatedUser() };
			return super.update(objs);
		}
	}

	/**
	 * @return Data source.
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource
	 *            Data source.
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @return auditTrailFetchQuery
	 */
	public String getAuditTrailFetchQuery() {
		return auditTrailFetchQuery;
	}

	/**
	 * @param auditTrailFetchQuery
	 *            auditTrailFetchQuery
	 */
	public void setAuditTrailFetchQuery(String auditTrailFetchQuery) {
		this.auditTrailFetchQuery = auditTrailFetchQuery;
	}

	/**
	 * @return exclusionCodeFetchQuery
	 */
	public String getExclusionCodeFetchQuery() {
		return exclusionCodeFetchQuery;
	}

	/**
	 * @param exclusionCodeFetchQuery
	 *            exclusionCodeFetchQuery
	 */
	public void setExclusionCodeFetchQuery(String exclusionCodeFetchQuery) {
		this.exclusionCodeFetchQuery = exclusionCodeFetchQuery;
	}

	/**
	 * @return exclusionDeleteQuery
	 */
	public String getExclusionDeleteQuery() {
		return exclusionDeleteQuery;
	}

	/**
	 * @param exclusionDeleteQuery
	 *            exclusionDeleteQuery
	 */
	public void setExclusionDeleteQuery(String exclusionDeleteQuery) {
		this.exclusionDeleteQuery = exclusionDeleteQuery;
	}

	/**
	 * @return exclusionUpdateQuery
	 */
	public String getExclusionUpdateQuery() {
		return exclusionUpdateQuery;
	}

	/**
	 * @param exclusionUpdateQuery
	 *            exclusionUpdateQuery
	 */
	public void setExclusionUpdateQuery(String exclusionUpdateQuery) {
		this.exclusionUpdateQuery = exclusionUpdateQuery;
	}

	/**
	 * @param matchValue
	 * @return Exclusions query
	 */
	private String getExclusionQuery(String matchValue) {
		matchValue = ReferenceDataUtil.trimAndNullToEmptyString(matchValue)
				.toUpperCase();
		StringBuffer exclusionQuery = new StringBuffer(
				"SELECT TEMP.ERROR_CD FROM (SELECT ERROR_CD FROM BX_ERROR_CODE_AUDIT_TRAIL WHERE ERROR_CD LIKE '");
		exclusionQuery.append(matchValue.replaceAll("'", "''"));
		exclusionQuery.append("%'");
		exclusionQuery.append(" ORDER BY ERROR_CD) TEMP WHERE ROWNUM <= 20 ");
		return exclusionQuery.toString();
	}

	/**
	 * @return
	 */
	public String getAuditTrailInsertQuery() {
		return auditTrailInsertQuery;
	}

	/**
	 * @param auditTrailInsertQuery
	 */
	public void setAuditTrailInsertQuery(String auditTrailInsertQuery) {
		this.auditTrailInsertQuery = auditTrailInsertQuery;
	}

	/**
	 * @return
	 */
	public String getAuditTrailUpdateQuery() {
		return auditTrailUpdateQuery;
	}

	/**
	 * @param auditTrailUpdateQuery
	 */
	public void setAuditTrailUpdateQuery(String auditTrailUpdateQuery) {
		this.auditTrailUpdateQuery = auditTrailUpdateQuery;
	}

	/**
	 * @return
	 */
	public String getExclusionInsertQuery() {
		return exclusionInsertQuery;
	}

	/**
	 * @param exclusionInsertQuery
	 */
	public void setExclusionInsertQuery(String exclusionInsertQuery) {
		this.exclusionInsertQuery = exclusionInsertQuery;
	}

	/**
	 * @return
	 */
	public String getExclusionIDSequenceQuery() {
		return exclusionIDSequenceQuery;
	}

	/**
	 * @param exclusionIDSequenceQuery
	 */
	public void setExclusionIDSequenceQuery(String exclusionIDSequenceQuery) {
		this.exclusionIDSequenceQuery = exclusionIDSequenceQuery;
	}

	/**
	 * @return
	 */
	public String getErrorCodeDeleteQueryAudit() {
		return errorCodeDeleteQueryAudit;
	}

	/**
	 * @param errorCodeDeleteQueryAudit
	 */
	public void setErrorCodeDeleteQueryAudit(String errorCodeDeleteQueryAudit) {
		this.errorCodeDeleteQueryAudit = errorCodeDeleteQueryAudit;
	}

	/**
	 * @return
	 */
	public String getErrorCodeDeleteQueryExclusions() {
		return errorCodeDeleteQueryExclusions;
	}

	/**
	 * @param errorCodeDeleteQueryExclusions
	 */
	public void setErrorCodeDeleteQueryExclusions(
			String errorCodeDeleteQueryExclusions) {
		this.errorCodeDeleteQueryExclusions = errorCodeDeleteQueryExclusions;
	}

	/**
	 * @return
	 */
	public String getTotalCountExclusionQuery() {
		return totalCountExclusionQuery;
	}

	/**
	 * @param totalCountExclusionQuery
	 */
	public void setTotalCountExclusionQuery(String totalCountExclusionQuery) {
		this.totalCountExclusionQuery = totalCountExclusionQuery;
	}

	/**
	 * @return
	 */
	public String getExclusionAllFetchQuery() {
		return exclusionAllFetchQuery;
	}

	/**
	 * @param exclusionAllFetchQuery
	 */
	public void setExclusionAllFetchQuery(String exclusionAllFetchQuery) {
		this.exclusionAllFetchQuery = exclusionAllFetchQuery;
	}

	/**
	 * @param getting
	 *            the EB03 value for validating the EB03
	 */

	public Map getEB03Values(String hippaCode) {
		eb03ListDescription();
		return eb03Map;
	}

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.repository.ReferenceDataRepository#fetchCategoryCodeServiceTypeMapping(CategoryServiceTypeMappingVO)
	 * @param CategoryServiceTypeMappingVO
	 * @return List of mappings. This function returns list of mappings
	 */
	public List fetchCategoryCodeServiceTypeMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO) {

		List categoryServiceTypeMappingList = null;

		CategoryServiceTypeFetchQuery categoryServiceTypeFetchQuery = new CategoryServiceTypeFetchQuery(
				dataSource,
				getCategoryCodeMappingQuery(categoryServiceTypeMappingVO));
		eb03ListDescription();
		categoryServiceTypeMappingList = categoryServiceTypeFetchQuery
				.execute();
		List listAfterProcessing = new ArrayList();
		// iterate and make as group

		Iterator itr = categoryServiceTypeMappingList.iterator();
		CategoryServiceTypeMappingVO voafteriteration = new CategoryServiceTypeMappingVO();

		Mapping mapping = new Mapping();

		HippaSegment EB03 = new HippaSegment();
		HippaCodeValue hippaCodeValue = null;

		CategoryServiceTypeMappingVO vobeforeiteration = null;
		List hippaCodeSelectedValues = new ArrayList();
		String categoryString = "";
		int i = 0;
		int j = 0;
		String prevCategoryCode = "";
		String prevSystem = "";
		// logic for getting the comma separated EB03

		while (itr.hasNext()) {
			vobeforeiteration = (CategoryServiceTypeMappingVO) itr.next();

			if (i > 0) {
				if ((!prevCategoryCode.equals(vobeforeiteration
						.getCategoryCode()))
						|| (!prevSystem.equals(vobeforeiteration.getSystem()))) {

					listAfterProcessing.add(voafteriteration);
					voafteriteration = new CategoryServiceTypeMappingVO();

					categoryString = "";
					hippaCodeSelectedValues = new ArrayList();
					i = 1;
					j = 1;
				} else {
					j++;
					i++;
				}

			} else {
				j++;
				i++;
			}
			if (null != vobeforeiteration.getServiceType()) {
				if (null != vobeforeiteration.getServiceType()
						|| !""
								.equals(vobeforeiteration.getServiceType()
										.trim())) {
					if (j > 1) {

						categoryString = categoryString + ",";
						categoryString = categoryString + " ";
					}

					categoryString = categoryString
							+ vobeforeiteration.getServiceType();
					hippaCodeValue = new HippaCodeValue();
					hippaCodeValue.setValue(vobeforeiteration.getServiceType());
					hippaCodeValue.setDescription(vobeforeiteration
							.getServiceTypeDesc());
					hippaCodeSelectedValues.add(hippaCodeValue);

				}
			}
			prevCategoryCode = vobeforeiteration.getCategoryCode();
			prevSystem = vobeforeiteration.getSystem();

			voafteriteration.setCategoryCode(vobeforeiteration
					.getCategoryCode());
			voafteriteration.setCategoryDesc(vobeforeiteration
					.getCategoryDesc());
			voafteriteration.setSystem(vobeforeiteration.getSystem());
			voafteriteration.setServiceTypelist(hippaCodeSelectedValues);
			EB03.setHippaCodeSelectedValues(hippaCodeSelectedValues);
			mapping.setEb03(EB03);
			voafteriteration.setMapping(mapping);

			voafteriteration.setServiceType(categoryString);

		}
		if (i >= 1) {

			// EB03.setHippaCodeSelectedValues(hippaCodeSelectedValues);
			// mapping.setEb03(EB03);
			// voafteriteration.setMapping(mapping);
			listAfterProcessing.add(voafteriteration);
		}
		return listAfterProcessing;
	}

	/**
	 * Fetch all the category codes with and without mappings in both LG and ISG
	 * - T52
	 * 
	 * @return List
	 */
	public List fetchAllCategoryCodes() {

		AllCategoryServiceTypeFetchQuery allCategoryServiceTypeFetchQuery = new AllCategoryServiceTypeFetchQuery(
				dataSource, getAllCategoryCodesQuery());
		eb03ListDescription();
		List categoryServiceTypeMappingList = allCategoryServiceTypeFetchQuery
				.execute();

		return categoryServiceTypeMappingList;
	}

	public List updateCategoryCodeMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO,
			CategoryServiceTypeMappingVO selectVO, List eb03Values) {
		String system = categoryServiceTypeMappingVO.getSystem();
		String categorycode = categoryServiceTypeMappingVO.getCategoryCode();
		User user = new User();
		user.setCreatedUserName("USER");
		user.setLastUpdatedUserName("USER");
		// delete the category code mapping for category code and system

		DeleteTempEB03CategoryMapping deleteTempEB03CategoryMapping = new DeleteTempEB03CategoryMapping(
				dataSource, getDeleteTempCategoryEB03MappingQuery());
		deleteTempEB03CategoryMapping.delete(categorycode, system);

		// insert after delete
		PersistCategoryEB03Mapping persistCategoryEB03Mapping = new PersistCategoryEB03Mapping(
				dataSource, getInsertCategoryEB03MappingQuery());

		if ((null != eb03Values)) {

			if ((null != eb03Values) && (!eb03Values.isEmpty())) {

				for (int i = 0; i < eb03Values.size(); i++) {

					HippaCodeValue eb03HippaValue = (HippaCodeValue) eb03Values
							.get(i);
					persistCategoryEB03Mapping.insert(system, categorycode,
							eb03HippaValue, user);
				}
			} else {

				HippaCodeValue eb03HippaValue = new HippaCodeValue();
				eb03HippaValue.setValue(" ");
				persistCategoryEB03Mapping.insert(system, categorycode,
						eb03HippaValue, user);
			}
		}
		// need to change later
		// categoryServiceTypeMappingVO=new CategoryServiceTypeMappingVO();
		List categoryCodeList = fetchCategoryCodeServiceTypeMapping(selectVO);
		return categoryCodeList;
	}

	public List createCategoryCodeMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO,
			CategoryServiceTypeMappingVO selectVO, List eb03Values) {
		String system = categoryServiceTypeMappingVO.getSystem();
		String categorycode = categoryServiceTypeMappingVO.getCategoryCode();
		User user = new User();
		user.setCreatedUserName("USER");
		user.setLastUpdatedUserName("USER");

		PersistCategoryEB03Mapping persistCategoryEB03Mapping = new PersistCategoryEB03Mapping(
				dataSource, getInsertCategoryEB03MappingQuery());

		if ((null != eb03Values)) {

			if ((null != eb03Values) && (!eb03Values.isEmpty())) {

				for (int i = 0; i < eb03Values.size(); i++) {

					HippaCodeValue eb03HippaValue = (HippaCodeValue) eb03Values
							.get(i);
					persistCategoryEB03Mapping.insert(system, categorycode,
							eb03HippaValue, user);
				}
			}
			// if mapping not created and status is not applicable
			else {

				HippaCodeValue eb03HippaValue = new HippaCodeValue();
				eb03HippaValue.setValue(" ");
				persistCategoryEB03Mapping.insert(system, categorycode,
						eb03HippaValue, user);
			}
		}
		// need to change later
		// categoryServiceTypeMappingVO=new CategoryServiceTypeMappingVO();
		List categoryCodeList = fetchCategoryCodeServiceTypeMapping(selectVO);
		return categoryCodeList;
	}

	public List deleteCategoryCodeMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO,
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVOselect) {

		/**
		 * Input Parameters for the query.
		 */
		Object[] inputParams = new Object[] {
				categoryServiceTypeMappingVO.getCategoryCode(),
				categoryServiceTypeMappingVO.getSystem() };

		// select CONTRACT_VAR ,CATAGORY from CONTVAR where CATAGORY=?

		CategoryMappingDeleteQuery categoryMappingDeleteQuery = new CategoryMappingDeleteQuery(
				dataSource, getCategoryMappingDeleteQuery());
		categoryMappingDeleteQuery.update(inputParams);
		List categoryCodeMappinglist = fetchCategoryCodeServiceTypeMapping(categoryServiceTypeMappingVOselect);
		return categoryCodeMappinglist;
	}

	/**
	 * Fetch category code -service type mapping
	 * 
	 * @param categoryServiceTypeMappingVO
	 * @return String query
	 */
	private String getCategoryCodeMappingQuery(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO) {

		StringBuffer ISGMainQuery = null;
		StringBuffer LGMainQuery = null;

		String categoryCode = "";
		String serviceType = "";
		String system = "";
		String categoryDesc = "";

		if (null != categoryServiceTypeMappingVO.getCategoryCode()
				&& !categoryServiceTypeMappingVO.getCategoryCode().equals("")) {
			categoryCode = categoryServiceTypeMappingVO.getCategoryCode();
		}
		if (null != categoryServiceTypeMappingVO.getDescription()
				&& !categoryServiceTypeMappingVO.getDescription().equals("")) {

			categoryDesc = categoryServiceTypeMappingVO.getDescription();
		}
		if (null != categoryServiceTypeMappingVO.getServiceType()
				&& !categoryServiceTypeMappingVO.getServiceType().equals("")) {

			serviceType = categoryServiceTypeMappingVO.getServiceType();
		}
		if (null != categoryServiceTypeMappingVO.getSystem()
				&& !categoryServiceTypeMappingVO.getSystem().equals("")) {

			system = categoryServiceTypeMappingVO.getSystem();
		}

		// if ("".equals(categoryCode) && "".equals(categoryDesc)
		// && "".equals(system)) {
		// if (!"".equals(serviceType)) {
		// categoryCodeMappingQuery = new StringBuffer(
		// "SELECT CATEGORY_CODE, SRVC_TYP_CODE, SYSTEM "
		// +
		// " FROM BX_CATEGORY_SRVC_TYP_VLDN WHERE CATEGORY_CODE IN(SELECT DISTINCT CATEGORY_CODE "
		// +
		// "FROM BX_CATEGORY_SRVC_TYP_VLDN WHERE UPPER(TRIM(SRVC_TYP_CODE)) = UPPER(TRIM('"
		// + serviceType + "')) ) ORDER BY CATEGORY_CODE,SYSTEM");
		// System.out
		// .println("EB03" + categoryCodeMappingQuery.toString());
		// return categoryCodeMappingQuery.toString();
		// }
		//	
		// }

		// ISG Main query part 1
		ISGMainQuery = new StringBuffer(
				"SELECT DISTINCT UPPER(TRIM(CPFXP_VALUE)) AS CAT_CODE, UPPER(TRIM(CPFXP_DESC)) AS CAT_DESC, EWPDTAB.SRVC_TYP_CODE,  "
						+ " 'ISG' AS SYSTEM_CODE "
						+ " FROM ISG_CPFXP_CODETBLE ISG_TAB "
						+ " LEFT OUTER JOIN(SELECT DISTINCT CATEGORY_CODE, SRVC_TYP_CODE, SYSTEM "
						+ " FROM BX_CATEGORY_SRVC_TYP_VLDN WHERE UPPER(TRIM(SYSTEM)) = UPPER(TRIM('ISG')) ");
		// ISG Main query part 2
		ISGMainQuery
				.append("  ) EWPDTAB "
						+ " ON UPPER(TRIM(EWPDTAB.CATEGORY_CODE)) = UPPER(TRIM(ISG_TAB.CPFXP_VALUE)) ");
		// Service type code - Part 1
		if (null != categoryServiceTypeMappingVO.getServiceType()
				&& !categoryServiceTypeMappingVO.getServiceType().equals("")) {
			ISGMainQuery
					.append(" RIGHT OUTER JOIN ( "
							+ " SELECT CATEGORY_CODE, SRVC_TYP_CODE, SYSTEM "
							+ " FROM BX_CATEGORY_SRVC_TYP_VLDN WHERE CATEGORY_CODE IN(SELECT DISTINCT CATEGORY_CODE "
							+ " FROM BX_CATEGORY_SRVC_TYP_VLDN "
							+ " WHERE UPPER(TRIM(SRVC_TYP_CODE)) = UPPER(TRIM('"
							+ serviceType
							+ "')) AND UPPER(TRIM(SYSTEM)) = UPPER(TRIM('ISG'))) AND UPPER(TRIM(SYSTEM)) = UPPER(TRIM('ISG')))  EWPDTAB2 "
							+ " ON UPPER(TRIM(EWPDTAB.CATEGORY_CODE)) = UPPER(TRIM(EWPDTAB2.CATEGORY_CODE)) ");
		}
		// Main query for ISG - Part 3
		ISGMainQuery
				.append(" WHERE CPFXP_ID = '021' AND UPPER(TRIM(CPFXP_VALUE)) <> ' ' ");
		// Category Code
		if (null != categoryServiceTypeMappingVO.getCategoryCode()
				&& !categoryServiceTypeMappingVO.getCategoryCode().equals("")) {
			ISGMainQuery
					.append(" AND UPPER(TRIM(CPFXP_VALUE)) LIKE UPPER(TRIM('"
							+ categoryCode + "')) escape '`' ");
		}
		// Category Description
		if (null != categoryServiceTypeMappingVO.getDescription()
				&& !categoryServiceTypeMappingVO.getDescription().equals("")) {
			ISGMainQuery
					.append(" AND UPPER(TRIM(CPFXP_DESC)) LIKE '%'||UPPER(TRIM('"
							+ categoryDesc + " '))||'%' ");

		}
		// Service type code - Part 3
		if (null != categoryServiceTypeMappingVO.getServiceType()
				&& !categoryServiceTypeMappingVO.getServiceType().equals("")) {

			ISGMainQuery
					.append(" AND UPPER(TRIM(EWPDTAB.SRVC_TYP_CODE)) <> ' ' ");
		}

		// Main query for LG - Part 1
		LGMainQuery = new StringBuffer(
				" SELECT DISTINCT UPPER(TRIM(J_VALUE)) AS CAT_CODE, UPPER(TRIM(J_DESC)) AS CAT_DESC, EWPDTAB.SRVC_TYP_CODE, "
						+ " 'LG' AS SYSTEM_CODE "
						+ " FROM LG_JTABLE_VALUES A "
						+ " LEFT OUTER JOIN(SELECT DISTINCT CATEGORY_CODE, SRVC_TYP_CODE, SYSTEM "
						+ " FROM BX_CATEGORY_SRVC_TYP_VLDN WHERE UPPER(TRIM(SYSTEM)) = UPPER(TRIM('LG')) "
						+ "  ) EWPDTAB "
						+ " ON UPPER(TRIM(EWPDTAB.CATEGORY_CODE)) = UPPER(TRIM(J_VALUE)) ");
		// Service type code - Part 1
		if (null != categoryServiceTypeMappingVO.getServiceType()
				&& !categoryServiceTypeMappingVO.getServiceType().equals("")) {
			LGMainQuery
					.append(" RIGHT OUTER JOIN ( "
							+ " SELECT CATEGORY_CODE, SRVC_TYP_CODE, SYSTEM "
							+ " FROM BX_CATEGORY_SRVC_TYP_VLDN WHERE CATEGORY_CODE IN(SELECT DISTINCT CATEGORY_CODE "
							+ " FROM BX_CATEGORY_SRVC_TYP_VLDN "
							+ " WHERE UPPER(TRIM(SRVC_TYP_CODE)) = UPPER(TRIM('"
							+ serviceType
							+ "')) AND UPPER(TRIM(SYSTEM)) = UPPER(TRIM('LG'))) AND UPPER(TRIM(SYSTEM)) = UPPER(TRIM('LG')))  EWPDTAB2 "
							+ " ON UPPER(TRIM(EWPDTAB.CATEGORY_CODE)) = UPPER(TRIM(EWPDTAB2.CATEGORY_CODE)) ");
		}
		// Main query for LG - Part 2
		LGMainQuery
				.append("  WHERE A.J_CODE = '021' AND UPPER(TRIM(J_VALUE)) <> ' '   ");
		// Category Code
		if (null != categoryServiceTypeMappingVO.getCategoryCode()
				&& !categoryServiceTypeMappingVO.getCategoryCode().equals("")) {
			LGMainQuery.append(" AND UPPER(TRIM(J_VALUE)) LIKE UPPER(TRIM('"
					+ categoryCode + "')) escape '`' ");
		}
		// Category Description
		if (null != categoryServiceTypeMappingVO.getDescription()
				&& !categoryServiceTypeMappingVO.getDescription().equals("")) {

			LGMainQuery
					.append(" AND UPPER(TRIM(A.J_DESC)) LIKE '%'||UPPER(TRIM('"
							+ categoryDesc + "'))||'%' ");
		}
		// Service type code - Part 3
		if (null != categoryServiceTypeMappingVO.getServiceType()
				&& !categoryServiceTypeMappingVO.getServiceType().equals("")) {

			LGMainQuery
					.append(" AND UPPER(TRIM(EWPDTAB.SRVC_TYP_CODE)) <> ' ' ");
		}

		if (null != system) {
			if ("".equals(system.trim())) {
				LGMainQuery.append(" UNION ");
				LGMainQuery.append(ISGMainQuery);

				LGMainQuery.append(" ORDER BY CAT_CODE ,SYSTEM_CODE ");

				return LGMainQuery.toString();

			} else if ("LG".equals(system.trim().toUpperCase())) {
				LGMainQuery.append(" ORDER BY CAT_CODE ,SYSTEM_CODE ");

				return LGMainQuery.toString();

			} else if ("ISG".equals(system.trim().toUpperCase())) {
				ISGMainQuery.append(" ORDER BY CAT_CODE ,SYSTEM_CODE ");

				return ISGMainQuery.toString();

			}
		}
		return "";
	}

	/**
	 * category code service type query
	 */

	private class CategoryServiceTypeFetchQuery extends MappingSqlQuery {

		public CategoryServiceTypeFetchQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			// declareParameter(new SqlParameter(Types.VARCHAR));
		}

		/**
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 * @param arg0
		 * @param arg1
		 * @return
		 * @throws SQLException
		 *             Map row. Query is available at dao-config.xml.
		 */
		public Object mapRow(ResultSet resultSet, int rowCount)
				throws SQLException {
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO = new CategoryServiceTypeMappingVO();
			categoryServiceTypeMappingVO
					.setCategoryCode(resultSet.getString(1));
			categoryServiceTypeMappingVO
					.setCategoryDesc(resultSet.getString(2));
			categoryServiceTypeMappingVO.setServiceType(resultSet.getString(3));
			categoryServiceTypeMappingVO.setSystem(resultSet.getString(4));
			categoryServiceTypeMappingVO.setServiceTypeDesc((String) eb03Map
					.get(resultSet.getString("SRVC_TYP_CODE")));

			return categoryServiceTypeMappingVO;

		}

	}

	/**
	 * Fetch All category codes with and without service type mapping - T52
	 * 
	 * @return String query
	 */
	private String getAllCategoryCodesQuery() {
		StringBuffer selectAllQuery = new StringBuffer(
				"select * from (SELECT DISTINCT UPPER(TRIM(CPFXP_VALUE)) AS CAT_CODE, "
						+ "UPPER(TRIM(CPFXP_DESC)) AS CAT_DESC, EWPDTAB.SRVC_TYP_CODE, 'ISG'AS SYSTEM_CODE "
						+ "FROM ISG_CPFXP_CODETBLE ISG_TAB "
						+ "LEFT OUTER JOIN(SELECT DISTINCT CATEGORY_CODE, SRVC_TYP_CODE, SYSTEM "
						+ "FROM BX_CATEGORY_SRVC_TYP_VLDN WHERE SYSTEM = 'ISG') EWPDTAB "
						+ "ON UPPER(TRIM(EWPDTAB.CATEGORY_CODE)) = UPPER(TRIM(ISG_TAB.CPFXP_VALUE)) "
						+ "WHERE CPFXP_ID = '021' AND UPPER(TRIM(CPFXP_VALUE)) <> ' ' "
						+ "UNION "
						+ "SELECT DISTINCT UPPER(TRIM(J_VALUE)) AS CAT_CODE, UPPER(TRIM(A.J_DESC)) AS CAT_DESC, "
						+ "EWPDTAB.SRVC_TYP_CODE, 'LG' AS SYSTEM_CODE "
						+ "FROM LG_JTABLE_VALUES A "
						+ "LEFT OUTER JOIN(SELECT DISTINCT CATEGORY_CODE, SRVC_TYP_CODE, SYSTEM "
						+ "FROM BX_CATEGORY_SRVC_TYP_VLDN WHERE SYSTEM = 'LG') EWPDTAB "
						+ "ON UPPER(TRIM(EWPDTAB.CATEGORY_CODE)) = UPPER(TRIM(J_VALUE)) "
						+ "WHERE A.J_CODE = '021' AND UPPER(TRIM(J_VALUE)) <> ' '   )where SRVC_TYP_CODE is not null");

		String allCategoryCodesQuery = selectAllQuery.toString();

		return allCategoryCodesQuery;
	}

	/**
	 * @author UST Global - www.ust-global.com <br />
	 * @version $Id: $ To get all the category codes with and without mappings
	 *          in both LG and ISG - T52
	 */
	private class AllCategoryServiceTypeFetchQuery extends MappingSqlQuery {

		public AllCategoryServiceTypeFetchQuery(DataSource dataSource,
				String query) {
			super(dataSource, query);
		}

		/**
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 * @param arg0
		 * @param arg1
		 * @return
		 * @throws SQLException
		 *             Map row. Query is available at dao-config.xml.
		 */
		public Object mapRow(ResultSet resultSet, int rowCount)
				throws SQLException {
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO = new CategoryServiceTypeMappingVO();
			categoryServiceTypeMappingVO
					.setCategoryCode(resultSet.getString(1));
			categoryServiceTypeMappingVO.setDescription(resultSet.getString(2));
			categoryServiceTypeMappingVO.setServiceType(resultSet.getString(3));
			categoryServiceTypeMappingVO.setSystem(resultSet.getString(4));

			return categoryServiceTypeMappingVO;

		}

	}

	/**
	 * fetch category code variable mapping for LG and ISG
	 * 
	 * @param mapping
	 * @param hippaValue
	 */
	public List fetchCategoryCodeVariableMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO) {
		String categoryVariableMapping = "";
		// Query for LG and ISG
		if (null != categoryServiceTypeMappingVO.getSystem()) {
			if ("LG".equals(categoryServiceTypeMappingVO.getSystem().trim()
					.toUpperCase())) {
				if (null != categoryServiceTypeMappingVO.getCategoryCode()) {
					categoryVariableMapping = "select CONTRACT_VAR,CONT_VAR_TX from  CONTVAR where UPPER(TRIM(CATAGORY))=UPPER(TRIM('"
							+ categoryServiceTypeMappingVO.getCategoryCode()
							+ "'))";
				}
			} else if ("ISG".equals(categoryServiceTypeMappingVO.getSystem()
					.trim().toUpperCase())) {
				categoryVariableMapping = "select CPFXP_CONTVAR,CPFXP_TEXT from ISG_CPFXP_CONTVAR where UPPER(TRIM(CPFXP_CATAGORY)) = UPPER(TRIM('"
						+ categoryServiceTypeMappingVO.getCategoryCode()
						+ "'))";
			}

		}

		CategoryVariableMappingQuery categoryVariableMappingQuery = new CategoryVariableMappingQuery(
				dataSource, categoryVariableMapping);
		List categoryVariablelist = categoryVariableMappingQuery.execute();
		return categoryVariablelist;
	}

	/**
	 * @author UST Global - www.ust-global.com <br />
	 * @version $Id: $ Inner class for Error code fetch. Returns list of Error
	 *          codes.
	 */

	private class CategoryVariableMappingQuery extends MappingSqlQuery {

		public CategoryVariableMappingQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			// declareParameter(new SqlParameter(Types.VARCHAR));
		}

		/**
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 * @param resultSet
		 * @param arg1
		 * @return
		 * @throws SQLException
		 *             Map row.
		 */
		public Object mapRow(ResultSet resultSet, int rowCount)
				throws SQLException {
			CategoryVariableMappingVO categoryVariableMappingVO = new CategoryVariableMappingVO();
			categoryVariableMappingVO.setVariable(resultSet.getString(1));
			categoryVariableMappingVO.setVariableDesc(resultSet.getString(2));
			return categoryVariableMappingVO;
		}

	}

	/**
	 * Inner class for persisting header rule
	 * 
	 */
	private final class PersistCategoryEB03Mapping extends SqlUpdate {

		private PersistCategoryEB03Mapping(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}

		/**
		 * sets the values to be persisted
		 * 
		 * @param mapping
		 * @param hippaValue
		 */
		protected void insert(String system, String categorycode,
				HippaCodeValue hippaValue, User user) {
			Object[] objs = new Object[] { categorycode, hippaValue.getValue(),
					user.getCreatedUserName(), system,
					user.getLastUpdatedUserName(), };
			super.update(objs);
		}

	}

	/**
	 * Inner class for deleting the Temp mapping
	 * 
	 */
	private final class DeleteTempEB03CategoryMapping extends SqlUpdate {

		private DeleteTempEB03CategoryMapping(DataSource dataSource,
				String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}

		/**
		 * sets the values to be deleted
		 * 
		 * @param mapping
		 */
		protected void delete(String categorycode, String system) {
			Object[] objs = new Object[] { categorycode, system };
			super.update(objs);
		}

	}

	/**
	 * 
	 * A private method for deleting the custom Message from temp table
	 * 
	 */
	private final class CategoryMappingDeleteQuery extends SqlUpdate {

		public CategoryMappingDeleteQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));

			compile();
		}

	}

	/**
	 * sets the values for EB03 to a list to get the description
	 * @param mapping
	 * @param hippaValue
	 */
		private void eb03ListDescription(){
			/* Reference Data Values -  START*/
			String hippaSegmentName = DomainConstants.EB03_NAME;
			int noOfRecords = 0;
			List eb03List = hippaSegmentRepository
			.findMatchingHippaCodeValuesFromDataDictionary(
					hippaSegmentName, null, noOfRecords);
			
			for (int count = 0; count < eb03List.size(); count++) {

				HippaCodeValue codeValue = (HippaCodeValue) eb03List.get(count);
				eb03Map.put(codeValue.getValue(), codeValue.getDescription());
				
			}	
		}


		private HippaSegmentRepositoryImpl hippaSegmentRepository;
		
		private String selectCatalogNamesQuery;
		
		private String selectHPNCatalogNamesQuery;
		
		public String getSelectHPNCatalogNamesQuery() {
			return selectHPNCatalogNamesQuery;
		}

		public void setSelectHPNCatalogNamesQuery(String selectHPNCatalogNamesQuery) {
			this.selectHPNCatalogNamesQuery = selectHPNCatalogNamesQuery;
		}

		private String fetchDataValuesOfCatalogQuery;
		
		private String catalogMinMaxLengthQuery;
		
		private String checkDuplicateDataValueQuery;	
		
		private String insertDataValueQuery;
		
		private String recordDataValueAuditTrailQuery;
		
		private String updateDataValueQuery;
		
		private String countVariablesQuery;
		
		private String countHeaderRulesQuery;
		
		private String countMessagesQuery;
		
		private String deleteDataValueQuery;
		
		private String catalogHistoryFetchQuery;

		/**
		 * @return getHippaSegmentRepository
		 */
		public HippaSegmentRepositoryImpl getHippaSegmentRepository() {
			return hippaSegmentRepository;
		}
		/**
		 * @param getHippaSegmentRepository
		 */
		public void setHippaSegmentRepository(
				HippaSegmentRepositoryImpl hippaSegmentRepository) {
			this.hippaSegmentRepository = hippaSegmentRepository;
		}
		/**
		 * @return catalogHistoryFetchQuery
		 */
		public String getCatalogHistoryFetchQuery() {
			return catalogHistoryFetchQuery;
		}
		/**
		 * @param catalogHistoryFetchQuery
		 */
		public void setCatalogHistoryFetchQuery(String catalogHistoryFetchQuery) {
			this.catalogHistoryFetchQuery = catalogHistoryFetchQuery;
		}
		/**
		 * @return deleteDataValueQuery
		 */
		public String getDeleteDataValueQuery() {
			return deleteDataValueQuery;
		}
		/**
		 * @param deleteDataValueQuery
		 */
		public void setDeleteDataValueQuery(String deleteDataValueQuery) {
			this.deleteDataValueQuery = deleteDataValueQuery;
		}
		/**
		 * @return countHeaderRulesQuery
		 */
		public String getCountHeaderRulesQuery() {
			return countHeaderRulesQuery;
		}
		/**
		 * @param countHeaderRulesQuery
		 */
		public void setCountHeaderRulesQuery(String countHeaderRulesQuery) {
			this.countHeaderRulesQuery = countHeaderRulesQuery;
		}
		/**
		 * @return countMessagesQuery
		 */
		public String getCountMessagesQuery() {
			return countMessagesQuery;
		}
		/**
		 * @param countMessagesQuery
		 */
		public void setCountMessagesQuery(String countMessagesQuery) {
			this.countMessagesQuery = countMessagesQuery;
		}
		/**
		 * @return countVariablesQuery
		 */
		public String getCountVariablesQuery() {
			return countVariablesQuery;
		}
		/**
		 * @param countVariablesQuery
		 */
		public void setCountVariablesQuery(String countVariablesQuery) {
			this.countVariablesQuery = countVariablesQuery;
		}
		/**
		 * @return updateDataValueQuery
		 */
		public String getUpdateDataValueQuery() {
			return updateDataValueQuery;
		}
		/**
		 * @param updateDataValueQuery
		 */
		public void setUpdateDataValueQuery(String updateDataValueQuery) {
			this.updateDataValueQuery = updateDataValueQuery;
		}
		/**
		 * @return recordDataValueAuditTrailQuery
		 */
		public String getRecordDataValueAuditTrailQuery() {
			return recordDataValueAuditTrailQuery;
		}
		/**
		 * @param recordDataValueAuditTrailQuery
		 */
		public void setRecordDataValueAuditTrailQuery(
				String recordDataValueAuditTrailQuery) {
			this.recordDataValueAuditTrailQuery = recordDataValueAuditTrailQuery;
		}
		/**
		 * @return insertDataValueQuery
		 */
		public String getInsertDataValueQuery() {
			return insertDataValueQuery;
		}
		/**
		 * @param insertDataValueQuery
		 */
		public void setInsertDataValueQuery(String insertDataValueQuery) {
			this.insertDataValueQuery = insertDataValueQuery;
		}
		/**
		 * @return checkDuplicateDataValueQuery
		 */
		public String getCheckDuplicateDataValueQuery() {
			return checkDuplicateDataValueQuery;
		}
		/**
		 * @param checkDuplicateDataValueQuery
		 */
		public void setCheckDuplicateDataValueQuery(String checkDuplicateDataValueQuery) {
			this.checkDuplicateDataValueQuery = checkDuplicateDataValueQuery;
		}
		/**
		 * @return catalogMinMaxLengthQuery
		 */
		public String getCatalogMinMaxLengthQuery() {
			return catalogMinMaxLengthQuery;
		}
		/**
		 * @param catalogMinMaxLengthQuery
		 */
		public void setCatalogMinMaxLengthQuery(String catalogMinMaxLengthQuery) {
			this.catalogMinMaxLengthQuery = catalogMinMaxLengthQuery;
		}
		/**
		 * @return fetchDataValuesOfCatalogQuery
		 */
		public String getFetchDataValuesOfCatalogQuery() {
			return fetchDataValuesOfCatalogQuery;
		}
		/**
		 * @param fetchDataValuesOfCatalogQuery
		 */
		public void setFetchDataValuesOfCatalogQuery(
				String fetchDataValuesOfCatalogQuery) {
			this.fetchDataValuesOfCatalogQuery = fetchDataValuesOfCatalogQuery;
		}
		/**
		 * @return selectCatalogNamesQuery
		 */
		public String getSelectCatalogNamesQuery() {
			return selectCatalogNamesQuery;
		}
		
		/**
		 * @param selectCatalogNamesQuery
		 */
		public void setSelectCatalogNamesQuery(String selectCatalogNamesQuery) {
			this.selectCatalogNamesQuery = selectCatalogNamesQuery;
		}
		
		/**
		 * @author UST Global - www.ust-global.com <br />
		 * @version $Id: $ To fetch the matching catalog names for Auto Complete
		 */
		private class FetchCatalogNamesQuery extends MappingSqlQuery {
			
			private FetchCatalogNamesQuery(DataSource dataSource) {
				super(dataSource, getSelectCatalogNamesQuery());
				declareParameter(new SqlParameter(Types.VARCHAR));
			}
			
			/**
			 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
			 *      int)
			 * @param resultSet
			 * @param rowCountO
			 * @return
			 * @throws SQLException
			 */
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {
				return resultSet.getString(DomainConstants.DATA_ELEMENT_ID);
				
			}
		}
		
		private class FetchHPNCatalogNamesQuery extends MappingSqlQuery {
			
			private FetchHPNCatalogNamesQuery(DataSource dataSource) {
				super(dataSource, getSelectHPNCatalogNamesQuery());
				declareParameter(new SqlParameter(Types.VARCHAR));
			}
			
			/**
			 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
			 *      int)
			 * @param resultSet
			 * @param rowCountO
			 * @return
			 * @throws SQLException
			 */
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {
				return resultSet.getString(DomainConstants.DATA_ELEMENT_ID);
				
			}
		}
		/**
		 * @param String
		 * @return List
		 * Fetch all the catalog names matching the match value
		 * 
		 */
		public List fetchCatalogNames(String matchValue) {

			FetchCatalogNamesQuery catalogNamesQuery  = new FetchCatalogNamesQuery(dataSource);
			Object[] inputParams = new Object[] { matchValue };
			List catalogNameList = catalogNamesQuery.execute(inputParams);
			return catalogNameList;
		}

		/**
		 * @param String
		 * @return List
		 * Fetch all the HPN catalog names matching the match value
		 * 
		 */
		public List fetchHPNCatalogNames(String matchValue) {

			FetchHPNCatalogNamesQuery catalogNamesQuery  = new FetchHPNCatalogNamesQuery(dataSource);
			Object[] inputParams = new Object[] { matchValue };
			List catalogNameList = catalogNamesQuery.execute(inputParams);
			return catalogNameList;
		}
		/**
		 * @author UST Global - www.ust-global.com <br />
		 * @version $Id: $ To fetch all the data values of a catalog name
		 */
		private class FetchDataValuesOfCatalogNameQuery extends MappingSqlQuery {
			
			private FetchDataValuesOfCatalogNameQuery(DataSource dataSource) {
				super(dataSource, getFetchDataValuesOfCatalogQuery());
				declareParameter(new SqlParameter(Types.VARCHAR));
			}
			
			/**
			 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
			 *      int)
			 * @param resultSet
			 * @param rowCount
			 * @return
			 * @throws SQLException
			 */
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {
				
				ReferenceDataValueVO referenceDataValueVO = new ReferenceDataValueVO();
				referenceDataValueVO.setPrimaryCode(resultSet.getString(DomainConstants.DATA_ELEMENT_VAL));
				referenceDataValueVO.setSecondaryCode(resultSet.getString(DomainConstants.VAL_DESC_TXT));
				referenceDataValueVO.setDescription(resultSet.getString(DomainConstants.VAL_CMNTS));
				return referenceDataValueVO;
			}
		}
		
		/**
		 * @param catalogName
		 * @return ReferenceDataValueVO
		 * Fetch all the data values corresponding to the catalog name
		 */
		public List fetchItems(String catalogName) {

			FetchDataValuesOfCatalogNameQuery dataValuesOfCatalogQuery = new FetchDataValuesOfCatalogNameQuery(dataSource);
			Object[] inputParams = new Object[] {catalogName};
			return dataValuesOfCatalogQuery.execute(inputParams);
		}
		
		/**
		 * @author UST Global - www.ust-global.com <br />
		 * @version $Id: $ To check if the data value is already existing for the catalog
		 */
		private class CheckDuplicateDataValuesQuery extends MappingSqlQuery {
			
			private CheckDuplicateDataValuesQuery(DataSource dataSource) {
				super(dataSource, getCheckDuplicateDataValueQuery());
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
			}
			
			/**
			 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
			 *      int)
			 * @param resultSet
			 * @param rowCountO
			 * @return
			 * @throws SQLException
			 */
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {		
				return Integer.valueOf(resultSet.getInt(1));
			}
		}
		
		/**
		 * Inner class for inserting data into BX_HIPPA_SEGMENT_VAL table
		 *
		 */
		private static final class AddDataValueToCatalog extends SqlUpdate {

			private AddDataValueToCatalog(DataSource dataSource, String query) {
		        super(dataSource, query);
		        declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
			    compile();
		    }	
			
			/**
			 * @param objects
			 */
			protected void insert(Object[] objects) {				
				super.update(objects);
			}
			
	    }
		/**
		 * Inner class for inserting data into BX_DATA_VAL_AUDIT_TRIAL table
		 *
		 */
		private static final class RecordDataValueAuditTrail extends SqlUpdate {

			private RecordDataValueAuditTrail(DataSource dataSource, String query) {
		        super(dataSource, query);
		        declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
			    compile();
		    }	
			
			/**
			 * @param objects
			 */
			protected void insert(Object[] objects) {				
				super.update(objects);
			}
			
	    }
		
		/**
		 * @param referenceDataValueVO
		 * @param status
		 * Method to insert data into BX_DATA_VAL_AUDIT_TRIAL table
		 */
		private void createDataValueAuditTrial(
				ReferenceDataValueVO referenceDataValueVO, String status) {

			RecordDataValueAuditTrail dataValueAuditTrail = new RecordDataValueAuditTrail(
					dataSource, getRecordDataValueAuditTrailQuery());
			Object[] auditTrialObjects = new Object[] {
					referenceDataValueVO.getCatalogName(),
					referenceDataValueVO.getPrimaryCode(),
					referenceDataValueVO.getAdditionalComments(),
					referenceDataValueVO.getUserID(), status };
			dataValueAuditTrail.insert(auditTrialObjects);
			logger.info("Audit trial created!!!");
		}
		
		/**
		 * @param referenceDataValueVO
		 * @return String
		 * Add the new data value to the Catalog Name
		 */
		public String addItem(ReferenceDataValueVO referenceDataValueVO) {

			String statusOfAddition = null;
			String catalogName = referenceDataValueVO.getCatalogName();
			String dataValue = referenceDataValueVO.getPrimaryCode();
			String userID = referenceDataValueVO.getUserID();
			
			// check if DB has the same Data Value
			CheckDuplicateDataValuesQuery checkDuplicateDataValuesQuery = new CheckDuplicateDataValuesQuery(
					dataSource);
			Object[] inputParams = new Object[] { catalogName, dataValue };
			List countList = checkDuplicateDataValuesQuery.execute(inputParams);
			int countOfRecords = 0;
			if (null != countList && countList.size() > 0) {
				countOfRecords = ((Integer) countList.get(0)).intValue();
			}
			/*
			 * if there are no records existing with same data value for the catalog
			 * the new record will be inserted to the table
			 */
			if (countOfRecords == 0) {

				// INSERT data into the DB
				AddDataValueToCatalog addDataValueToCatalog = new AddDataValueToCatalog(
						dataSource, getInsertDataValueQuery());
				Object[] dataValueObjects = new Object[] { catalogName, dataValue,
						referenceDataValueVO.getSecondaryCode(),
						referenceDataValueVO.getDescription(), userID, userID };
				addDataValueToCatalog.insert(dataValueObjects);
				// INSERT data into audit trial table
				createDataValueAuditTrial(referenceDataValueVO,
						DomainConstants.CREATE_STATUS);

				statusOfAddition = DomainConstants.SUCCESS;

			} else {
				statusOfAddition = DomainConstants.DUPLICATE;
			}

			return statusOfAddition;
		}
		
		/**
		 * @author UST Global - www.ust-global.com <br />
		 * @version $Id: $ To update the data value of a catalog
		 */
		private class UpdateDataValueOfCatalog extends SqlUpdate {

			/**
			 * @param dataSource
			 */
			private UpdateDataValueOfCatalog(DataSource dataSource) {
				super(dataSource, getUpdateDataValueQuery());
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				compile();
			}

			/**
			 * @param objects
			 * @return int
			 */
			private int update(ReferenceDataValueVO referenceDataValueVO) {
				
				Object[] objects = new Object[] {
						referenceDataValueVO.getSecondaryCode(),
						referenceDataValueVO.getDescription(),
						referenceDataValueVO.getUserID(),
						referenceDataValueVO.getCatalogName(),
						referenceDataValueVO.getPrimaryCode()
					};
				return super.update(objects);
			}

		}
		
		/**
		 * @param referenceDataValueVO
		 * @return String
		 * Update the data value of the Catalog Name
		 */
		public String updateItem(ReferenceDataValueVO referenceDataValueVO) {

			String updateStatus = null;
			// UPDATE data in BX_DATA_VAL_AUDIT_TRIAL table
			UpdateDataValueOfCatalog dataValueOfCatalog = new UpdateDataValueOfCatalog(
					dataSource);
			int updateRowCount = dataValueOfCatalog.update(referenceDataValueVO);
			if (updateRowCount == 1) {
				// INSERT data into audit trial table
				createDataValueAuditTrial(referenceDataValueVO,
						DomainConstants.UPDATE_STATUS);
				updateStatus = DomainConstants.SUCCESS;
			}
			return updateStatus;
		}
		
		/**
		 * @author UST Global - www.ust-global.com <br />
		 * @version $Id: $ To find the count of variables mapped to data value
		 */
		private class GetVariablesCountQuery extends MappingSqlQuery {
			
			private GetVariablesCountQuery(DataSource dataSource) {
				super(dataSource, getCountVariablesQuery());
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
			}
			
			/**
			 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
			 *      int)
			 * @param resultSet
			 * @param rowCountO
			 * @return
			 * @throws SQLException
			 */
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {		
				return Integer.valueOf(resultSet.getInt(1));
			}
		}
		
		/**
		 * @param catalogValueCheck
		 * @return String
		 * Query for finding the count of SPS IDs mapped to the data value
		 */
		private String getSPSIDCountQuery(String catalogValueCheck) {

			StringBuffer spsIDCountFinderQuery = new StringBuffer(
					"SELECT SUM(COUNT_SPS) FROM (" + "SELECT COUNT(*) COUNT_SPS "
							+ "FROM BX_SPS_MAPNG WHERE " + catalogValueCheck
							+ "' AND IN_TEMP_TAB = 'N' " + "UNION "
							+ "SELECT COUNT(*) TEMP_COUNT_SPS "
							+ "FROM TEMP_BX_SPS_MAPG WHERE " + catalogValueCheck
							+ "' AND SPS_PARAM_ID IN " + "(SELECT SPS_PARAM_ID "
							+ "FROM BX_SPS_MAPNG " + "WHERE IN_TEMP_TAB = 'Y'))");
			return spsIDCountFinderQuery.toString();
		}
		
		/**
		 * @author UST Global - www.ust-global.com <br />
		 * @version $Id: $ To find the count of SPS IDs mapped to the data value
		 */
		private static final class GetSPSIDsCountQuery extends MappingSqlQuery {
			
			private GetSPSIDsCountQuery(DataSource dataSource, String query) {
				super(dataSource, query);
			}		
			/**
			 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
			 *      int)
			 * @param resultSet
			 * @param rowCountO
			 * @return
			 * @throws SQLException
			 */
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {		
				return Integer.valueOf(resultSet.getInt(1));
			}
		}
		
		/**
		 * @param catalogValue
		 * @param primaryCode
		 * @return int
		 * Method to invoke GetSPSIDsCountQuery 
		 */
		private int getSPSIDsCount(String catalogValue, String primaryCode) {
			int countOfSPSIDs = 0;
			StringBuffer catalogValueCheck = new StringBuffer(catalogValue);
			catalogValueCheck.append(DomainConstants.EQUAL_TO);
			catalogValueCheck.append(DomainConstants.SINGLE_QUOTATION);
			catalogValueCheck.append(primaryCode);
			GetSPSIDsCountQuery getSPSIDsCountQuery = new GetSPSIDsCountQuery(
					dataSource, getSPSIDCountQuery(catalogValueCheck.toString()));
			List spsIdCountList = getSPSIDsCountQuery.execute();
			if(null != spsIdCountList && spsIdCountList.size() > 0) {
				countOfSPSIDs = ((Integer)spsIdCountList.get(0)).intValue();		
			}
			
			return countOfSPSIDs;
		}
		
		/**
		 * @author UST Global - www.ust-global.com <br />
		 * @version $Id: $ To find the count of Header Rules or Messages
		 * 					mapped to the data value
		 */
		private static final class GetRuleMessageCountQuery extends MappingSqlQuery {
			
			private GetRuleMessageCountQuery(DataSource dataSource, String query) {
				super(dataSource, query);
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
			}		
			/**
			 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
			 *      int)
			 * @param resultSet
			 * @param rowCountO
			 * @return
			 * @throws SQLException
			 */
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {		
				return Integer.valueOf(resultSet.getInt(1));
			}
		}
		
		/**
		 * @param referenceDataValueVO
		 * @return ReferenceDataValueVO
		 * Checks if data value is mapped to SPS/VAriable/Header Rule/Message
		 */
		public ReferenceDataValueVO checkItemMappings(
				ReferenceDataValueVO referenceDataValueVO) {

			String catalogName = referenceDataValueVO.getCatalogName();
			String primaryCode = referenceDataValueVO.getPrimaryCode();
			ReferenceDataValueVO dataValueVO = new ReferenceDataValueVO();
			// find variables count for any Catalog
			GetVariablesCountQuery getVariablesCountQuery = new GetVariablesCountQuery(
					dataSource);
			Object[] inputParams = new Object[] { catalogName, primaryCode,
					catalogName, primaryCode };
			List variableCountList = getVariablesCountQuery.execute(inputParams);
			int countOfVariables = 0;
			if (null != variableCountList && variableCountList.size() > 0) {
				countOfVariables = ((Integer) variableCountList.get(0)).intValue();
				dataValueVO.setNumberOfVariables(countOfVariables);
			}
			// find SPS count for EB01/EB02/EB06/EB09/HSD01/HSD03/HSD05/HSD07/HSD08
			// catalogs
			String[] spsCatalogs = new String[] { DomainConstants.EB01_NAME,
					DomainConstants.EB02_NAME, DomainConstants.EB06_NAME,
					DomainConstants.EB09_NAME };
			int countOfSPSIDs = 0;
			for (int count = 0; count < spsCatalogs.length; count++) {
				if (spsCatalogs[count].equals(catalogName)) {
					StringBuffer catalogValue = new StringBuffer(catalogName);
					catalogValue.append(DomainConstants.UNDERSCORE);
					catalogValue.append(DomainConstants.VALUE);
					countOfSPSIDs = getSPSIDsCount(catalogValue.toString(),
							primaryCode);
				}
			}
			if (DomainConstants.HSD01_NAME.equals(catalogName)) {
				countOfSPSIDs = getSPSIDsCount(DomainConstants.HSD1_VALUE,
						primaryCode);
			}
			if (DomainConstants.HSD03_NAME.equals(catalogName)) {
				countOfSPSIDs = getSPSIDsCount(DomainConstants.HSD3_VALUE,
						primaryCode);
			}
			if (DomainConstants.HSD05_NAME.equals(catalogName)) {
				countOfSPSIDs = getSPSIDsCount(DomainConstants.HSD5_VALUE,
						primaryCode);
			}
			if (DomainConstants.HSD07_NAME.equals(catalogName)) {
				countOfSPSIDs = getSPSIDsCount(DomainConstants.HSD7_VALUE,
						primaryCode);
			}
			if (DomainConstants.HSD08_NAME.equals(catalogName)) {
				countOfSPSIDs = getSPSIDsCount(DomainConstants.HSD8_VALUE,
						primaryCode);
			}
			dataValueVO.setNumberOfSPS(countOfSPSIDs);
			// find header rule count for EB03
			if (DomainConstants.EB03_NAME.equals(catalogName)) {
				GetRuleMessageCountQuery ruleCountQuery = new GetRuleMessageCountQuery(
						dataSource, getCountHeaderRulesQuery());
				Object[] ruleInputParams = new Object[] { primaryCode, primaryCode };
				List ruleCountList = ruleCountQuery.execute(ruleInputParams);
				int countOfRules = 0;
				if (null != ruleCountList && ruleCountList.size() > 0) {
					countOfRules = ((Integer) ruleCountList.get(0)).intValue();
					dataValueVO.setNumberOfHeaderRules(countOfRules);
				}
			}
			// find message count for Note Type
			if (DomainConstants.NOTE_TYPE_CODE.equals(catalogName)) {
				GetRuleMessageCountQuery messageCountQuery = new GetRuleMessageCountQuery(
						dataSource, getCountMessagesQuery());
				Object[] messageInputParams = new Object[] { primaryCode,
						primaryCode };
				List messageCountList = messageCountQuery
						.execute(messageInputParams);
				int countOfMessages = 0;
				if (null != messageCountList && messageCountList.size() > 0) {
					countOfMessages = ((Integer) messageCountList.get(0))
							.intValue();
					dataValueVO.setNumberOfMessages(countOfMessages);
				}
			}

			return dataValueVO;
		}
		
		/**
		 * @author UST Global - www.ust-global.com <br />
		 * @version $Id: $ Data value delete query class
		 */
		private static final class DataValueDeleteQuery extends SqlUpdate {

			/**
			 * @param dataSource
			 * @param sqlQuery
			 */
			private DataValueDeleteQuery(DataSource dataSource, String sqlQuery) {
				super(dataSource, sqlQuery);
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				compile();
			}

			/**
			 * @param 
			 * @return
			 */
			private int update(ReferenceDataValueVO referenceDataValueVO) {
				Object[] objects = new Object[] {
					referenceDataValueVO.getCatalogName(),
					referenceDataValueVO.getPrimaryCode()
				};
				return super.update(objects);
			}

		}
		
		/**
		 * @param referenceDataValueVO
		 * @return String
		 * Delete the data value of the Catalog Name
		 */
		public String deleteItem(ReferenceDataValueVO referenceDataValueVO) {
			String deleteStatus = null;
			// execute delete query
			DataValueDeleteQuery dataValueDeleteQuery = new DataValueDeleteQuery(
					dataSource, getDeleteDataValueQuery());
			int deleteRowCount = dataValueDeleteQuery.update(referenceDataValueVO);
			logger.info("deleteRowCount = " + deleteRowCount);
			// insert deleted record into the audit trial table
			if (deleteRowCount > 0) {
				createDataValueAuditTrial(referenceDataValueVO,
						DomainConstants.DELETE_STATUS);
				deleteStatus = DomainConstants.SUCCESS;
			}
			return deleteStatus;
		}
		
		/**
		 * @author UST Global - www.ust-global.com <br />
		 * @version $Id: $ To fetch the deletion history of values of a catalog
		 */
		private static final class FetchCatalogHistoryQuery extends MappingSqlQuery {
			
			private FetchCatalogHistoryQuery(DataSource dataSource, String query) {
				super(dataSource, query);
				declareParameter(new SqlParameter(Types.VARCHAR));
			}		
			/**
			 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
			 *      int)
			 * @param resultSet
			 * @param rowCountO
			 * @return
			 * @throws SQLException
			 */
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {		
				
				ReferenceDataValueVO referenceDataValueVO = new ReferenceDataValueVO();
				referenceDataValueVO.setPrimaryCode(resultSet
						.getString(DomainConstants.DATA_VALUE));
				referenceDataValueVO.setAdditionalComments(resultSet
						.getString(DomainConstants.ADD_COMMENT));
				String deletedDateAndTime = BxUtil.getFormattedDate(resultSet
						.getTimestamp(DomainConstants.CREATD_TM_STMP));
				referenceDataValueVO.setDateAndTime(deletedDateAndTime);
				referenceDataValueVO.setUserID(resultSet
						.getString(DomainConstants.CREATD_USER_ID));
				referenceDataValueVO.setAuditStatus(resultSet
						.getString(DomainConstants.DATA_VAL_STS));
				return referenceDataValueVO;
			}
		}
		
		/**
		 * @return List
		 * @param catalogName
		 * Fetch the deleted data value details of the Catalog 
		 */
		public List fetchHistoryOfCatalog(String catalogName) {
			
			FetchCatalogHistoryQuery fetchCatalogHistoryQuery = new FetchCatalogHistoryQuery(
					dataSource, getCatalogHistoryFetchQuery());
			List deletedDataValueList = fetchCatalogHistoryQuery.execute(catalogName);
			logger.info("deletedDataValueList size = " + deletedDataValueList.size());
			return deletedDataValueList;
		}

		/**
		 * @author UST Global - www.ust-global.com <br />
		 * @version $Id: $ To fetch the matching catalog names for Auto Complete
		 */
		private class FetchCatalogMinMaxLengthQuery extends MappingSqlQuery {
			
			private FetchCatalogMinMaxLengthQuery(DataSource dataSource) {
				super(dataSource, getCatalogMinMaxLengthQuery());
				declareParameter(new SqlParameter(Types.VARCHAR));
			}
			
			/**
			 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
			 *      int)
			 * @param resultSet
			 * @param rowCountO
			 * @return
			 * @throws SQLException
			 */
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {
				ReferenceDataValueVO referenceDataValueVO = new ReferenceDataValueVO();
				referenceDataValueVO.setCatalogName(resultSet
						.getString(DomainConstants.DATA_ELEMENT_ID));
				referenceDataValueVO.setCatalogMinLength(resultSet
						.getInt(DomainConstants.DATA_ELEMENT_LEN_MIN));
				referenceDataValueVO.setCatalogMaxLength(resultSet
						.getInt(DomainConstants.DATA_ELEMENT_LEN_MAX));
				return referenceDataValueVO;
				
			}
		}
		
		/**
		 * @param catalogName
		 * @return List
		 * Fetch the min and max lengths of the Catalog
		 */
		public ReferenceDataValueVO getCatalogMinMaxLengths(String catalogName) {
			
			FetchCatalogMinMaxLengthQuery catalogMinMaxLengthQuery = new FetchCatalogMinMaxLengthQuery(
					dataSource);
			Object[] inputParams = new Object[] { catalogName };
			List dataValueList = catalogMinMaxLengthQuery.execute(inputParams);
			ReferenceDataValueVO referenceDataValueVO = null;
			if(null != dataValueList && dataValueList.size() > 0) {
				referenceDataValueVO = (ReferenceDataValueVO) dataValueList
				.get(0);
			} 
			return referenceDataValueVO;		
		}
		/**
		 * This method is used to validate the exclusion criteria values.
		 * @param exclusionList - Contains the a List of ErrorExclusionVO 
		 * @return Map  
		 *   Key - Exclusion Item name.
		 *   Values - List of Invalid Exclusion values.
		 */
		public Map validateExclusionList(List exclusionList) {
			ErrorExclusionVO errorExclusionVO = (ErrorExclusionVO) exclusionList.get(0);						
			return findInvalidExclusions(getItemsToBeExcluded(errorExclusionVO));
		}	
		/**
		 * This method will collect the items to be excluded from both primary exclusion criteria 
		 * and secondary exclusion criteria. 
		 * @param errorExclusionVO
		 * @return Map
		 *   Key - Exclusion Item name.
		 *   Values - List of Exclusion Item value.
		 */
		private Map getItemsToBeExcluded(ErrorExclusionVO errorExclusionVO) {
			/*
			Key (String) - Item name. eg : CONTRACT
			Value (List) - List of Exclusion Items. eg: List of Contracts
			*/
			Map exclusionMap = new HashMap();
			if (null != errorExclusionVO
					&& null != errorExclusionVO.getPrimaryExclusion()
					&& errorExclusionVO.getPrimaryExclusion().trim().length() != 0
					&& null != errorExclusionVO.getPrimaryValues()
					&& errorExclusionVO.getPrimaryValues().trim().length() != 0) {
				exclusionMap
				.put(errorExclusionVO.getPrimaryExclusion(), BxUtil
						.convertCSVToArrayList(errorExclusionVO
								.getPrimaryValues()));
			}
			if (null != errorExclusionVO
					&& null != errorExclusionVO.getSecondaryExclusion()
					&& errorExclusionVO.getSecondaryExclusion().trim().length() != 0
					&& null != errorExclusionVO.getSecondaryValues()
					&& errorExclusionVO.getSecondaryValues().trim().length() != 0) {
				exclusionMap.put(errorExclusionVO.getSecondaryExclusion(), BxUtil
						.convertCSVToArrayList(errorExclusionVO
								.getSecondaryValues()));
			}

			return exclusionMap;
		}
		/**
		 * This method will check whether the input exclusion items are present in the database.
		 * This method will return the exclusion items which are not present in the Database.
		 * @param exclusionMap - Contains the Exclusion items
		 * @return Map
		 * 	 Key - Exclusion Item name.
		 *   Values - List of Invalid Exclusion Items.
		 */
		private Map findInvalidExclusions(Map exclusionMap) {
			String query = this.createQueryForExclusionValues(exclusionMap);
			FindValidExclusionItemsQuery findValidExclusionItemsQuery = new FindValidExclusionItemsQuery(
					dataSource, query);
			List validAttributes = findValidExclusionItemsQuery
			.execute();
			Iterator iterator = validAttributes.iterator();
			Map resultMap;
			while(iterator.hasNext()) {
				resultMap = (Map)iterator.next();
				Set keySet = resultMap.keySet();
				Iterator keyIterator = keySet.iterator();
				while (keyIterator.hasNext()) {
					String key = (String)keyIterator.next();
					String result = (String)resultMap.get(key);
					List exclusionList = (List)exclusionMap.get(key);
					exclusionList.remove(result);
				}
			}
			return exclusionMap; 
		}
		/**
		 * This method will create the query string for fetching the exclusion items.
		 * @param exclusionMap - Contains the exclusion items.
		 * @return String 
		 * 	The query for fetching the exclusion items.
		 */
		private String createQueryForExclusionValues(Map exclusionMap) {
			String query = "";
			if (exclusionMap.containsKey("CONTRACT")) {				
				String inputValueCSV = BxUtil.convertArrayToCSVwithSingleQuote(
						(List)exclusionMap.get("CONTRACT"));								
				query = "select distinct 'CONTRACT' type, CNTRCT_ID as ExclusionItem from" + 
				" CMST_CNTRCT_MSTR where CNTRCT_ID in ("+inputValueCSV+")"+
				" UNION"+
				" select distinct 'CONTRACT' type, CONTRACT as ExclusionItem  from CONTRACT where CONTRACT in ("+inputValueCSV+")"+
				" UNION"+
				" select distinct 'CONTRACT' type, CPFXP_CONT_ID as ExclusionItem"+  
				" from ISG_CPFXP_CONTRACT where CPFXP_CONT_ID in ("+inputValueCSV+")";
			}
			if (exclusionMap.containsKey("SPS")) {					
				String inputValueCSV = BxUtil.convertArrayToCSVwithSingleQuote(
						(List)exclusionMap.get("SPS"));					
				if (query.trim().length() > 0) {
					query = query + " UNION ";
				}
				query = query + " select  distinct 'SPS' type, c.prmry_cd  as ExclusionItem from CATALOG B,ITEM C where B.CTLG_ID IN "+  
			    		" (select CTLG_ID from CATALOG where upper(CTLG_NAME) =upper('reference'))" +
			    		" and B.CTLG_ID = C.CTLG_ID and c.prmry_cd in ("+inputValueCSV+")";
			}
			if (exclusionMap.containsKey("HEADERRULE")) {					
				String inputValueCSV = BxUtil.convertArrayToCSVwithSingleQuote(
						(List)exclusionMap.get("HEADERRULE"));					
				if (query.trim().length() > 0) {
					query = query + " UNION ";
				}
				query = query + " SELECT  distinct 'HEADERRULE' type, RULE_ID	as ExclusionItem FROM  RULE "+
				 		" WHERE RULE_TYP_CD = 'WPDAUTOBG' "+ 
				 		" AND  WPD_DEL_FLAG = 'N' and RULE_ID in ("+inputValueCSV+") ";
			}
			if (exclusionMap.containsKey("VARIABLE")) {
				String inputValueCSV = BxUtil.convertArrayToCSVwithSingleQuote(
						(List)exclusionMap.get("VARIABLE"));		
				if (query.trim().length() > 0) {
					query = query + " UNION ";
				}
				query = query + " select distinct 'VARIABLE' type, contVar as ExclusionItem from ( "+
						"SELECT DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar from ( "+
						"SELECT e.CONTRACT_VAR , 'LG' FROM CONTVAR E )LG "+
						"FULL OUTER JOIN "+
						"(SELECT c.cpfxp_contvar ,'ISG' FROM ISG_CPFXP_CONTVAR c ) ISG "+
						"ON LG.CONTRACT_VAR = ISG.cpfxp_contvar "+
						") where contVar in ("+inputValueCSV+") ";
			}
			if (exclusionMap.containsKey("ACCUM")) {
				String inputValueCSV = BxUtil.convertArrayToCSVwithSingleQuote(
						(List)exclusionMap.get("ACCUM"));		
				if (query.trim().length() > 0) {
					query = query + " UNION ";
				}
				query = query + " select distinct 'ACCUM' type, SVC_CDE as ExclusionItem " +
						" from BX_ACCUMULATOR_VAL where SVC_CDE in ("+inputValueCSV+")";

			}
			if (exclusionMap.containsKey("PRODUCT")) {
				String inputValueCSV = BxUtil.convertArrayToCSVwithSingleQuote(
						(List)exclusionMap.get("PRODUCT"));		
				if (query.trim().length() > 0) {
					query = query + " UNION ";
				}
				query = query + "select distinct 'PRODUCT' type, PRODCUT_CODE as ExclusionItem from ( "+
						"select PRMRY_CD as PRODCUT_CODE from item where ctlg_id = 1786 "+
						"UNION "+
						"select CDCV_DOMN_VAL as PRODCUT_CODE from RCMS_PRODUCT_CODE_VAL where CDCI_CD_ITM_ID ='1786' OR CDCI_CD_ITM_ID ='1945' "+
						") where PRODCUT_CODE in ("+inputValueCSV+")";

			}
			return query;
		}
		/**
		 * Inner class for retrieving the exclusion items.
		 *
		 */
		private static final class FindValidExclusionItemsQuery extends MappingSqlQuery {
			
			private FindValidExclusionItemsQuery(DataSource dataSource, String query) {
				
				super(dataSource, query);
				super.compile();
			}		
			/**
			 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
			 *      int)
			 * @param resultSet
			 * @param rowCountO
			 * @return
			 * @throws SQLException
			 */
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {	
				Map resultMap = new HashMap();
				resultMap.put(resultSet.getString("type"), resultSet.getString("ExclusionItem"));
				return resultMap;
			}
		}
		
		/* Reference Data Values -  END */	
		/*************************Manage Header Rule EB03 Associations April 2012 Release SSCR14181 START ******************************/
		/**
		 * @param catalogName
		 * @return List
		 * Returns the search result, which contains the header rule mappings to EB03
		 */
		public List fetchHeaderRuleToEB03Mapping(HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO){

			List headerRuleEB03ResultSet = null;
			StringBuffer headerRuleToEb03MappingQuery = new StringBuffer();
			String queryCondition = null;
			
			//EB03 Search criteria alone
			if( null != headerRuleToEB03MappingVO.getEb03Value() && null == headerRuleToEB03MappingVO.getHeaderRuleValue()){
				queryCondition = " AND B.DATA_ELEMENT_VAL = '"+headerRuleToEB03MappingVO.getEb03Value()+"' ";
			}
			
			//HEADER RULE Search criteria alone
			if(null != headerRuleToEB03MappingVO.getHeaderRuleValue() && null == headerRuleToEB03MappingVO.getEb03Value()){
				queryCondition = " AND A.RULE_ID = '"+headerRuleToEB03MappingVO.getHeaderRuleValue()+"' ";
			}	
			
			//BOTH EB03 and HEADER RULE Search criteria alone
			if(null != headerRuleToEB03MappingVO.getHeaderRuleValue() && null != headerRuleToEB03MappingVO.getEb03Value()){
				queryCondition = " AND A.RULE_ID = '"+headerRuleToEB03MappingVO.getHeaderRuleValue()+"' "
							   	+" AND B.DATA_ELEMENT_VAL = '"+headerRuleToEB03MappingVO.getEb03Value()+"' ";;
			}	
			
			headerRuleToEb03MappingQuery.append(" SELECT DISTINCT B.DATA_ELEMENT_VAL EB03, ");
			headerRuleToEb03MappingQuery.append(" COMMA_SEPERATED_HEADERRUL_EB03(A.SRVC_TYP_CODE) COMMA_SEPERATED_HEADER_RULES, ");
			headerRuleToEb03MappingQuery.append(" B.VAL_DESC_TXT EB03DESCRIPTION ");
			headerRuleToEb03MappingQuery.append(" FROM BX_RULE_SRVC_TYP_VLDN A RIGHT OUTER JOIN BX_HIPPA_SEGMENT_VAL B " );
			headerRuleToEb03MappingQuery.append(" on A.SRVC_TYP_CODE = B.DATA_ELEMENT_VAL " );	
			headerRuleToEb03MappingQuery.append(" WHERE 1 =1  ");
			headerRuleToEb03MappingQuery.append(" AND B.DATA_ELEMENT_ID = 'EB03' ");
			if(null != queryCondition)
			headerRuleToEb03MappingQuery.append(queryCondition);
			
				HeaderRuleToEB03MappingQuery headerRuleToEB03MappingQuery = new HeaderRuleToEB03MappingQuery(
						dataSource,headerRuleToEb03MappingQuery.toString());
				headerRuleEB03ResultSet = headerRuleToEB03MappingQuery.execute();
				
			return headerRuleEB03ResultSet;
		
		}
		/**
		 * @param HeaderRuleToEB03MappingVO
		 * @return List
		 * Returns all the header rule id, header rule descriptions corresponding to an EB03
		 */
		public List fetchHeaderRuleDetails(HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO){
			List headerRuleEB03ResultSet = null;
			StringBuffer headerRuleToEb03MappingQuery = new StringBuffer();
			headerRuleToEb03MappingQuery.append(" SELECT DISTINCT A.RULE_ID,B.RULE_SHRT_DESC  FROM BX_RULE_SRVC_TYP_VLDN A,RULE B ");
			headerRuleToEb03MappingQuery.append(" WHERE A.RULE_ID = B.RULE_ID ");
			headerRuleToEb03MappingQuery.append(" AND B.RULE_TYP_CD = 'WPDAUTOBG'");
			headerRuleToEb03MappingQuery.append(" AND B.WPD_DEL_FLAG = 'N' ");
			headerRuleToEb03MappingQuery.append(" AND A.SRVC_TYP_CODE='");
			headerRuleToEb03MappingQuery.append(headerRuleToEB03MappingVO.getEb03Value());
			headerRuleToEb03MappingQuery.append("' ORDER BY RULE_ID ");
			HeaderRuleDetailsQuery headerRuleDetailsQuery = new HeaderRuleDetailsQuery(
					dataSource,headerRuleToEb03MappingQuery.toString());
			headerRuleEB03ResultSet = headerRuleDetailsQuery.execute();
			return headerRuleEB03ResultSet;
		}
		/**
		 * HeaderRuleToEB03MappingQuery
		 */

		private class HeaderRuleToEB03MappingQuery extends MappingSqlQuery {

			public HeaderRuleToEB03MappingQuery(DataSource dataSource, String query) {
				super(dataSource, query);
				// declareParameter(new SqlParameter(Types.VARCHAR));
			}
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {
				HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO = new HeaderRuleToEB03MappingVO();
				headerRuleToEB03MappingVO.setEb03Value(resultSet.getString("EB03"));
				headerRuleToEB03MappingVO.setCommaSeperatedHeaderRules(resultSet.getString("COMMA_SEPERATED_HEADER_RULES"));
				headerRuleToEB03MappingVO.setEb03Description(resultSet.getString("EB03DESCRIPTION"));
				return headerRuleToEB03MappingVO;

			}
		}
		/**
		 * HeaderRuleDetailsQuery
		 */

		private class HeaderRuleDetailsQuery extends MappingSqlQuery {

			public HeaderRuleDetailsQuery(DataSource dataSource, String query) {
				super(dataSource, query);
				// declareParameter(new SqlParameter(Types.VARCHAR));
			}
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {
				HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO = new HeaderRuleToEB03MappingVO();
				headerRuleToEB03MappingVO.setHeaderRuleValue(resultSet.getString("RULE_ID"));
				headerRuleToEB03MappingVO.setHeaderRuleDescription(resultSet.getString("RULE_SHRT_DESC"));
				return headerRuleToEB03MappingVO;

			}
		}
		
		
		public List deleteHeaderRuleToEB03Mapping(HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO,
				HeaderRuleToEB03MappingVO headerRuleToEB03MappingVOSearchCriteria){
			HeaderRuleToEB03DeleteAllQuery headerRuleToEB03DeleteAllQuery = new HeaderRuleToEB03DeleteAllQuery(
					dataSource, getHeaderRuleToEB03DeleteAllQuery());
			int numRowsAfffected = headerRuleToEB03DeleteAllQuery.update(headerRuleToEB03MappingVO
					.getEb03Value());
			logger.info("Number of rows affected by Delete all" + numRowsAfffected);
			if (numRowsAfffected == 0) {
				headerRuleToEB03MappingVO
						.setOperationMessages("No header rules exist for the error code.");
			}
			if(numRowsAfffected >= 1){
				String systemComment = "";
				if(null !=headerRuleToEB03MappingVO.getEb03Value()){
					systemComment = "ALL HEADER RULE ASSOCIATION(S) FOR EB03 = '"+headerRuleToEB03MappingVO.getEb03Value()+"' ARE DELETED";
				}
				headerRuleToEB03MappingVO
						.setOperationMessages("Header Rule(s) associated with EB03 = '"+headerRuleToEB03MappingVO.getEb03Value()
								+"' are deleted successfully");
				createManageHeaderRuleToEB03AuditTrial(headerRuleToEB03MappingVO,systemComment);
			}
			List headerRuleEB03ResultSet = fetchHeaderRuleToEB03Mapping(headerRuleToEB03MappingVOSearchCriteria);
			return headerRuleEB03ResultSet;
		}
		/**
		 * HeaderRuleToEB03MappingQuery
		 */

		public class HeaderRuleToEB03DeleteAllQuery extends SqlUpdate {
			/**
			 * @param dataSource
			 */
			public HeaderRuleToEB03DeleteAllQuery(DataSource dataSource, String sqlQuery) {
				super(dataSource, sqlQuery);
				declareParameter(new SqlParameter("SRVC_TYP_CODE", Types.VARCHAR));
				compile();
			}
			public int delete(String eb03Value) {
				Object[] objs = new Object[] { eb03Value };
				return super.update(objs);
			}
		}
		/**
		 * HeaderRuleToEB03MappingQuery
		 */

		public class HeaderRuleToEB03ConditionalDeleteQuery extends SqlUpdate {
			/**
			 * @param dataSource
			 */
			public HeaderRuleToEB03ConditionalDeleteQuery(DataSource dataSource, String sqlQuery) {
				super(dataSource, sqlQuery);
				declareParameter(new SqlParameter("SRVC_TYP_CODE", Types.VARCHAR));
				declareParameter(new SqlParameter("RULE_ID", Types.VARCHAR));
				compile();
			}
			public int delete(String eb03Value, String headerRuleValue) {
				Object[] objs = new Object[] { eb03Value,headerRuleValue };
				return super.update(objs);
			}
		}


		/**
		 * @param referenceDataValueVO
		 * @param status
		 * Method to insert data into BX_DATA_VAL_AUDIT_TRIAL table
		 */
		private void createManageHeaderRuleToEB03AuditTrial(
				HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO, String systemComment) {

			ManageHeaderRuleToEB03AuditTrial manageHeaderRuleToEB03AuditTrial = new ManageHeaderRuleToEB03AuditTrial(
					dataSource, getManageHeaderRuleToEB03AuditTrialQuery());
			Object[] auditTrialObjects = new Object[] {
					headerRuleToEB03MappingVO.getEb03Value(),
					systemComment, 
					headerRuleToEB03MappingVO.getUserComments(),
					headerRuleToEB03MappingVO.getLastUpdatedUser()
					};
			manageHeaderRuleToEB03AuditTrial.insert(auditTrialObjects);
			logger.info("Audit trial created!!!");
		}

		/**
		 * Inner class for inserting data into BX_DATA_VAL_AUDIT_TRIAL table
		 *
		 */
		private static final class ManageHeaderRuleToEB03AuditTrial extends SqlUpdate {

			private ManageHeaderRuleToEB03AuditTrial(DataSource dataSource, String query) {
		        super(dataSource, query);
		        declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
			    compile();
		    }	
			protected void insert(Object[] objects) {				
				super.update(objects);
			}
	    }
		
		
		public List possibleHeaderRuleValues(String CSVwithSingleQuoteHeaderRulesFromPage){
			List headerRules = null;
			StringBuffer possibleHeaderRules = new StringBuffer();
			possibleHeaderRules.append(" SELECT RULE_ID FROM RULE WHERE 1=1 ");
			if(null != CSVwithSingleQuoteHeaderRulesFromPage){
				possibleHeaderRules.append(" AND RULE_ID IN ( ");
				possibleHeaderRules.append(CSVwithSingleQuoteHeaderRulesFromPage);
				possibleHeaderRules.append(" ) ");
			}
			possibleHeaderRules.append(" AND RULE_TYP_CD = 'WPDAUTOBG' ");
			possibleHeaderRules.append(" AND WPD_DEL_FLAG = 'N' ");
			
			PossibleHeaderRuleValuesQuery possibleHeaderRuleValuesQuery = new PossibleHeaderRuleValuesQuery(
					dataSource,possibleHeaderRules.toString());
			headerRules = possibleHeaderRuleValuesQuery.execute();
			return headerRules;
		}
		
		private class PossibleHeaderRuleValuesQuery extends MappingSqlQuery {

			public PossibleHeaderRuleValuesQuery(DataSource dataSource, String query) {
				super(dataSource, query);
				// declareParameter(new SqlParameter(Types.VARCHAR));
			}
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {
				return resultSet.getString("RULE_ID");

			}
		}
		public List saveHeaderRuleToEB03Mapping (HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO,
				HeaderRuleToEB03MappingVO headerRuleToEB03MappingVOForSelect,List insertHeaderRuleList){

			List headerRuleMappingListFromDB = fetchHeaderRuleDetails(headerRuleToEB03MappingVO);
			List deleteHeaderRuleList = new ArrayList();
			int numRowsAfffectedForDelete = 0;
			int numRowsAfffectedInsert = 0;
			String deleteComment = "";
			String insertComment = "";


			if(null != headerRuleMappingListFromDB){
				Iterator headerRuleMappingListFromDBItr = headerRuleMappingListFromDB.iterator();
				while (headerRuleMappingListFromDBItr.hasNext()) {
					HeaderRuleToEB03MappingVO eb03MappingVO = (HeaderRuleToEB03MappingVO) headerRuleMappingListFromDBItr.next();
					deleteHeaderRuleList.add(eb03MappingVO.getHeaderRuleValue());
				}
			}
			HeaderRuleToEB03ConditionalDeleteQuery headerRuleToEB03ConditionalDeleteQuery = new HeaderRuleToEB03ConditionalDeleteQuery(
					dataSource, getHeaderRuleToEB03ConditionalDeleteQuery());

			//Delete the Header Rule mapping which is not in the page list, but in the db list
			if (null != deleteHeaderRuleList) {
				for(int i = 0; i<deleteHeaderRuleList.size();i++){

					String headerRule = (String) deleteHeaderRuleList.get(i);
					if(!insertHeaderRuleList.contains(headerRule)){
						if (null != deleteComment && !DomainConstants.EMPTY.equals(deleteComment)) {
							deleteComment = deleteComment + ", ";
							deleteComment = deleteComment + "'"+headerRule+"'";
						} else {
							deleteComment = "'"+headerRule+"'";
						}

						//delete query
						numRowsAfffectedForDelete = headerRuleToEB03ConditionalDeleteQuery.delete(headerRuleToEB03MappingVO.getEb03Value(),headerRule);
					}
				}
			}

			HeaderRuleToEB03InsertQuery headerRuleToEB03InsertQuery = new HeaderRuleToEB03InsertQuery(
					dataSource, getHeaderRuleToEB03InsertQuery());
			//Insert the Header Rule mapping which is in the page list, but not in the db list
			if (null != insertHeaderRuleList) {
				for(int i = 0; i<insertHeaderRuleList.size();i++){

					String headerRule = (String) insertHeaderRuleList.get(i);
					if(!deleteHeaderRuleList.contains(headerRule)){
						if (null != insertComment && !DomainConstants.EMPTY.equals(insertComment)) {
							insertComment = insertComment + ", ";
							insertComment = insertComment + "'"+headerRule+"'";
						} else {
							insertComment = "'"+headerRule+"'";
						}
						//insert query
						numRowsAfffectedInsert = headerRuleToEB03InsertQuery.insert(headerRule,
								headerRuleToEB03MappingVO.getEb03Value(),headerRuleToEB03MappingVO.getLastUpdatedUser());
					}
				}
			}
			//Insert Value to the Audit Trial
			if(numRowsAfffectedForDelete >= 1 && numRowsAfffectedInsert >= 1) {
				String systemComment = "";
					systemComment = insertComment+" ADDED AND "+deleteComment+" DELETED";
					createManageHeaderRuleToEB03AuditTrial(headerRuleToEB03MappingVO,systemComment);
			} else if (numRowsAfffectedForDelete >= 1 && numRowsAfffectedInsert == 0) {
				String systemComment = "";
					systemComment = deleteComment+" DELETED";
					createManageHeaderRuleToEB03AuditTrial(headerRuleToEB03MappingVO,systemComment);
			} else if (numRowsAfffectedForDelete == 0 && numRowsAfffectedInsert >= 1) {
				String systemComment = "";
				systemComment = insertComment+" ADDED";
				createManageHeaderRuleToEB03AuditTrial(headerRuleToEB03MappingVO,systemComment);
			} else if(numRowsAfffectedForDelete == 0 && numRowsAfffectedInsert == 0){
				headerRuleToEB03MappingVO
						.setOperationMessages("No Modification to be saved.");
			}
			
			return fetchHeaderRuleToEB03Mapping(headerRuleToEB03MappingVOForSelect);
		}
		
		/**
		 * Inner class for persisting header rule
		 * 
		 */
		private final class HeaderRuleToEB03InsertQuery extends SqlUpdate {

			private HeaderRuleToEB03InsertQuery(DataSource dataSource, String query) {
				super(dataSource, query);
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				compile();
			}

			/**
			 * sets the values to be persisted
			 * 
			 * @param mapping
			 * @param hippaValue
			 */
			protected int insert(String headerRuleId, String eb03Id,String user) {
				Object[] objs = new Object[] { headerRuleId,eb03Id,user,user };
				return super.update(objs);
			}

		}
		
		public List viewHistoryOfHeaderRuleToEB03Mapping(HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO){
			List headerRuleToEB03ViewHistoryList = null;
			StringBuffer headerRuleToEB03ViewHistoryQuery = new StringBuffer();
			headerRuleToEB03ViewHistoryQuery.append(" SELECT EB03,CREATD_TM_STMP,CREATD_USER_ID, SYS_CMNTS, USR_CMNTS ");
			headerRuleToEB03ViewHistoryQuery.append(" FROM BX_RUL_SRVC_TYP_VALDN_AUDT_TRL");
			headerRuleToEB03ViewHistoryQuery.append(" WHERE EB03='");
			headerRuleToEB03ViewHistoryQuery.append(headerRuleToEB03MappingVO.getEb03Value());
			headerRuleToEB03ViewHistoryQuery.append("' ORDER BY CREATD_TM_STMP DESC ");
			
			HeaderRuleToEB03ViewHistoryQuery headerRuleToEB03ViewHistoryQueryObject = new HeaderRuleToEB03ViewHistoryQuery(
					dataSource,headerRuleToEB03ViewHistoryQuery.toString());
			headerRuleToEB03ViewHistoryList = headerRuleToEB03ViewHistoryQueryObject.execute();
			return headerRuleToEB03ViewHistoryList;
		}
		/**
		 * HeaderRuleDetailsQuery
		 */

		private class HeaderRuleToEB03ViewHistoryQuery extends MappingSqlQuery {

			public HeaderRuleToEB03ViewHistoryQuery(DataSource dataSource, String query) {
				super(dataSource, query);
				// declareParameter(new SqlParameter(Types.VARCHAR));
			}
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {
				HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO = new HeaderRuleToEB03MappingVO();
				headerRuleToEB03MappingVO.setEb03Value(resultSet.getString("EB03"));
				headerRuleToEB03MappingVO.setUpdatedDateAndTime(BxUtil.getFormattedDate(resultSet.getTimestamp("CREATD_TM_STMP")));
				headerRuleToEB03MappingVO.setLastUpdatedUser(resultSet.getString("CREATD_USER_ID"));
				headerRuleToEB03MappingVO.setSystemComments(resultSet.getString("SYS_CMNTS"));
				headerRuleToEB03MappingVO.setUserComments(resultSet.getString("USR_CMNTS"));
				
				
				return headerRuleToEB03MappingVO;

			}
		}
		/**
		 * method to fetch the header rule for auto complete.
		 * @param inputString - search string for date type auto complete
		 * @return List - List of header rules corresponding to the search string.
		 */
		public List fetchHeaderRuleForAutoComplete(String inputString, boolean autoCompleteFlag){

			StringBuffer query = new StringBuffer();
					query.append(" SELECT * FROM ");
					query.append("(SELECT RULE_ID,RULE_SHRT_DESC FROM RULE WHERE  1=1 ");
					if(autoCompleteFlag){
						query.append(" AND RULE_ID LIKE '"+inputString.trim()+"' ");
					}else{
						query.append(" AND RULE_SHRT_DESC LIKE '%"+inputString.trim()+"' ");
					}
					query.append(" AND RULE_TYP_CD = 'WPDAUTOBG' ");
					query.append(" AND WPD_DEL_FLAG = 'N' ");
					query.append( " ORDER BY RULE_ID ASC) ");
						query.append(" WHERE ROWNUM <= 50");
						
			HeaderRuleSearchQueryForAutoComplete headerRuleSearchQueryForAutoComplete = new HeaderRuleSearchQueryForAutoComplete(
					dataSource, query.toString());
			List headerRuleForAutoComplete = headerRuleSearchQueryForAutoComplete.execute();
			return headerRuleForAutoComplete;
		
		}
		private class HeaderRuleSearchQueryForAutoComplete extends MappingSqlQuery {

			private HeaderRuleSearchQueryForAutoComplete(DataSource dataSource, String query) {
				super(dataSource, query);
				compile();
			}

			protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
				HeaderRuleToEB03MappingVO mappingVO = new HeaderRuleToEB03MappingVO();
				mappingVO.setHeaderRuleValue(rs.getString("RULE_ID"));
				mappingVO.setHeaderRuleDescription(rs.getString("RULE_SHRT_DESC"));
				return mappingVO;
			}
		}
/*************************Manage Header Rule EB03 Associations April 2012 Release SSCR14181 END ******************************/
		// EB01 - Data Type Association - START	
		
		/**
		 * @param inputString - search string.
		 * @return List - List of matching data types 
		 * 	Method to fetch the data type values.
		 */
		public List fetchDataTypeForAutoComplete(String inputString,boolean autoCompleteFlag) {
			
			StringBuffer selectQureyToFetchDataTypeForAutoComplete = new StringBuffer();
			selectQureyToFetchDataTypeForAutoComplete.append("SELECT VALUE,");
			selectQureyToFetchDataTypeForAutoComplete.append(" CASE");
			selectQureyToFetchDataTypeForAutoComplete.append(" WHEN DTYP_DATA_TYPE_NAME IS NOT NULL");
			selectQureyToFetchDataTypeForAutoComplete.append(" THEN DTYP_DATA_TYPE_NAME");
			selectQureyToFetchDataTypeForAutoComplete.append(" WHEN DTYP_DATA_TYPE_NAME IS NULL");
			selectQureyToFetchDataTypeForAutoComplete.append(" AND CDCV_DESC_TXT IS NOT NULL");
			selectQureyToFetchDataTypeForAutoComplete.append(" THEN CDCV_DESC_TXT");
			selectQureyToFetchDataTypeForAutoComplete.append(" ELSE CPFXP_DESC");
			selectQureyToFetchDataTypeForAutoComplete.append(" END DATATYPEDESC");
			selectQureyToFetchDataTypeForAutoComplete.append(" FROM");
			selectQureyToFetchDataTypeForAutoComplete.append(" (SELECT VALUE,");
			selectQureyToFetchDataTypeForAutoComplete.append(" EWPD.DTYP_DATA_TYPE_NAME,");
			selectQureyToFetchDataTypeForAutoComplete.append(" UPPER(TRIM(LG.CDCV_DESC_TXT)) CDCV_DESC_TXT,");
			selectQureyToFetchDataTypeForAutoComplete.append(" ISG.CPFXP_DESC");
			selectQureyToFetchDataTypeForAutoComplete.append(" FROM (");
			selectQureyToFetchDataTypeForAutoComplete.append(" (SELECT LG.CDCV_DOMN_VAL VALUE");
			selectQureyToFetchDataTypeForAutoComplete.append(" FROM LG_RCMS_UDCV_USRDFND_CDVAL LG");
			selectQureyToFetchDataTypeForAutoComplete.append(" WHERE LG.CDCI_CD_ITM_ID ='1933'");
			if(autoCompleteFlag){
				selectQureyToFetchDataTypeForAutoComplete.append(" AND CDCV_DOMN_VAL LIKE '"+inputString.trim()+"')");
			}else{
				selectQureyToFetchDataTypeForAutoComplete.append(" AND CDCV_DESC_TXT LIKE '%"+inputString.trim()+"')");
			}
			selectQureyToFetchDataTypeForAutoComplete.append(" UNION");
			selectQureyToFetchDataTypeForAutoComplete.append(" (SELECT ISG.CPFXP_VALUE VALUE");
			selectQureyToFetchDataTypeForAutoComplete.append(" FROM ISG_CPFXP_CODETBLE ISG");
			selectQureyToFetchDataTypeForAutoComplete.append(" WHERE CPFXP_NAME ='Benefit Variable Formats'");
			if(autoCompleteFlag){
				selectQureyToFetchDataTypeForAutoComplete.append(" AND CPFXP_VALUE LIKE '"+inputString.trim()+"')");
			}else{
				selectQureyToFetchDataTypeForAutoComplete.append(" AND CPFXP_DESC LIKE '%"+inputString.trim()+"')");
			}
			selectQureyToFetchDataTypeForAutoComplete.append(" UNION");
			selectQureyToFetchDataTypeForAutoComplete.append(" (SELECT EWPD.DTYP_DATA_TYPE_LGND VALUE");
			selectQureyToFetchDataTypeForAutoComplete.append(" FROM DTYP_DATA_TYPE EWPD");
			selectQureyToFetchDataTypeForAutoComplete.append(" WHERE 1=1");
			if(autoCompleteFlag){
				selectQureyToFetchDataTypeForAutoComplete.append(" AND DTYP_DATA_TYPE_LGND LIKE '"+inputString.trim()+"')");
			}else{
				selectQureyToFetchDataTypeForAutoComplete.append(" AND DTYP_DATA_TYPE_NAME LIKE '%"+inputString.trim()+"')");
			}
			selectQureyToFetchDataTypeForAutoComplete.append(" ) X");
			selectQureyToFetchDataTypeForAutoComplete.append(" LEFT OUTER JOIN DTYP_DATA_TYPE EWPD");
			selectQureyToFetchDataTypeForAutoComplete.append(" ON EWPD.DTYP_DATA_TYPE_LGND = X.VALUE");
			selectQureyToFetchDataTypeForAutoComplete.append(" LEFT OUTER JOIN LG_RCMS_UDCV_USRDFND_CDVAL LG");
			selectQureyToFetchDataTypeForAutoComplete.append(" ON LG.CDCV_DOMN_VAL = X.VALUE");
			selectQureyToFetchDataTypeForAutoComplete.append(" AND LG.CDCI_CD_ITM_ID ='1933'");
			selectQureyToFetchDataTypeForAutoComplete.append(" LEFT OUTER JOIN ISG_CPFXP_CODETBLE ISG");
			selectQureyToFetchDataTypeForAutoComplete.append(" ON ISG.CPFXP_VALUE = X.VALUE");
			selectQureyToFetchDataTypeForAutoComplete.append(" AND ISG.CPFXP_NAME ='Benefit Variable Formats'");
			selectQureyToFetchDataTypeForAutoComplete.append(" ) ORDER BY VALUE ASC");
			
			DataTypeSearchQueryForAutoComplete dataTypeSearchQueryForAutoComplete = new DataTypeSearchQueryForAutoComplete(
					dataSource, selectQureyToFetchDataTypeForAutoComplete.toString());
			List dataTypeForAutoComplete = dataTypeSearchQueryForAutoComplete.execute();
			return dataTypeForAutoComplete;
		}
		

			private class DataTypeSearchQueryForAutoComplete extends MappingSqlQuery {

				private DataTypeSearchQueryForAutoComplete(DataSource dataSource, String query) {
					super(dataSource, query);
					compile();
				}

				protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
					DataTypeToEB01MappingVO mappingVO = new DataTypeToEB01MappingVO();
					mappingVO.setDataTypeValue(rs.getString(DomainConstants.VALUE));
					mappingVO.setDataTypeDescription(rs.getString(DomainConstants.DATA_TYPE_DESC));
					return mappingVO;
				}
			}
		
		
		/**
		 * method to fetch the EB01 - data type association values.
		 * @param mappingVO - contains search parameters, either eb01 value or data type or both.
		 * @return List of DataTypeToEB01MappingVO
		 */
		public List fetchDataTypeToEB01Mapping(DataTypeToEB01MappingVO mappingVO) {
			
			StringBuffer selectQureyToFetchDataTypeAssn = new StringBuffer();
			
			selectQureyToFetchDataTypeAssn.append(" SELECT DISTINCT B.DATA_ELEMENT_VAL "+DomainConstants.EB01_NAME+", ");
			selectQureyToFetchDataTypeAssn.append(" B.VAL_DESC_TXT "+DomainConstants.EB01_DESC+",");
			selectQureyToFetchDataTypeAssn.append(" COMMA_SEPERATED_DATATYPE_EB01(A.DATA_ELEMENT_VAL)  "+DomainConstants.DATA_TYPE+"");
			selectQureyToFetchDataTypeAssn.append(" FROM BX_CNTRCT_VAR_VALDN_MAPG A ");
			selectQureyToFetchDataTypeAssn.append(" RIGHT OUTER JOIN BX_HIPPA_SEGMENT_VAL B ");
			selectQureyToFetchDataTypeAssn.append(" ON A.DATA_ELEMENT_VAL = B.DATA_ELEMENT_VAL ");
			selectQureyToFetchDataTypeAssn.append(" WHERE B.DATA_ELEMENT_ID = 'EB01' ");
			
			if (null != mappingVO.getEb01value() && !DomainConstants.EMPTY.equals(mappingVO.getEb01value())) {
				selectQureyToFetchDataTypeAssn.append("AND B.DATA_ELEMENT_VAL = '"+mappingVO.getEb01value()+"'");
			}
			if (null != mappingVO.getDataTypeValue() && !DomainConstants.EMPTY.equals(mappingVO.getDataTypeValue())) {
				selectQureyToFetchDataTypeAssn.append("AND A.VAR_FRMT = '"+mappingVO.getDataTypeValue()+"'");
			}
			DataTypeSelectQuery dataTypeSelectQuery = new DataTypeSelectQuery(
					dataSource, selectQureyToFetchDataTypeAssn.toString());
			List dataType = dataTypeSelectQuery.execute();
			return dataType;
		}
		private class DataTypeSelectQuery extends MappingSqlQuery {

			private DataTypeSelectQuery(DataSource dataSource, String query) {
				super(dataSource, query);
				compile();
			}

			protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
				DataTypeToEB01MappingVO mappingVO = new DataTypeToEB01MappingVO();
				if(null != rs.getString(DomainConstants.EB01_NAME))
					mappingVO.setEb01value(rs.getString(DomainConstants.EB01_NAME).toUpperCase());
				if(null != rs.getString(DomainConstants.DATA_TYPE))
					mappingVO.setDataTypeValue(rs.getString(DomainConstants.DATA_TYPE).toUpperCase());
				if(null != rs.getString(DomainConstants.EB01_DESC))
					mappingVO.setEb01Description(rs.getString(DomainConstants.EB01_DESC).toUpperCase());
				return mappingVO;
			}
		}
		/**
		 * method to delete the EB01 - data type association values.
		 * @param eb01MappingVODelete - contains EB01 value whose association to be delete and the user comments for audit trail.
		 * @param eb01MappingToFetch - contains search criteria (EB01 or data type or both) after delete action.
		 * @return List - List of EB01 - data type association values after delete action.
		 */
		public List deleteDatatypeToEB01Mapping(DataTypeToEB01MappingVO eb01MappingVODelete, DataTypeToEB01MappingVO eb01MappingToFetch) {

			EB01ToDataTypeAssnDeleteQuery eB01ToDataTypeAssnDeleteQuery = new EB01ToDataTypeAssnDeleteQuery(
					dataSource, getEB01ToDataTypeAssnDeleteQuery());
			int numRowsAfffected = eB01ToDataTypeAssnDeleteQuery.update(eb01MappingVODelete.getEb01value());
			logger.info("Number of rows affected by Delete all" + numRowsAfffected);
			
			if (numRowsAfffected == 0) {
				eb01MappingVODelete.setOperationMessages("No data types exist for the EB01.");
			}
			if(numRowsAfffected >= 1){
				String systemComment = "";
				if(null !=eb01MappingVODelete.getEb01value()){
					systemComment = "ALL DATA TYPE ASSOCIATION(S) FOR EB01 ='"+eb01MappingVODelete.getEb01value()+"' ARE DELETED";
				}
				eb01MappingVODelete
						.setOperationMessages("Data Type(s) associated with EB01 ='"+ eb01MappingVODelete.getEb01value()
								+"' are deleted successfully");
				createManageDataTypeToEB01AuditTrial(eb01MappingVODelete,systemComment);
			}
			
			List dataTypeEB01ResultSet = fetchDataTypeToEB01Mapping(eb01MappingToFetch);
			return dataTypeEB01ResultSet;	
			
		}
		private class EB01ToDataTypeAssnDeleteQuery extends SqlUpdate {

			public EB01ToDataTypeAssnDeleteQuery(DataSource dataSource, String sqlQuery) {
				super(dataSource, sqlQuery);
				declareParameter(new SqlParameter("DATA_ELEMENT_VAL", Types.VARCHAR));
				compile();
			}
			public int update(String eb01Id) {
				Object[] objs = new Object[] { eb01Id };
				return super.update(objs);
			}
		}
		
		
		/**
		 * @param referenceDataValueVO
		 * @param status
		 * Method to insert data into BX_DATA_VAL_AUDIT_TRIAL table
		 */
		private void createManageDataTypeToEB01AuditTrial(
				DataTypeToEB01MappingVO eb01MappingVODelete, String systemComment) {

			ManageDatatypeToEB01AuditTrial manageDatatypeToEB01AuditTrial = new ManageDatatypeToEB01AuditTrial(
					dataSource, getManageDatatypeToEB01AuditTrialQuery());
			Object[] auditTrialObjects = new Object[] {
					eb01MappingVODelete.getEb01value(),
					systemComment, 
					eb01MappingVODelete.getUserComments(),
					eb01MappingVODelete.getLastUpdatedUser()
					};
			manageDatatypeToEB01AuditTrial.insert(auditTrialObjects);
			logger.info("Audit trial created!!!");
		}

		/**
		 * Inner class for inserting data into BX_DATA_VAL_AUDIT_TRIAL table
		 *
		 */
		private static final class ManageDatatypeToEB01AuditTrial extends SqlUpdate {

			private ManageDatatypeToEB01AuditTrial(DataSource dataSource, String query) {
		        super(dataSource, query);
		        declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
			    compile();
		    }	
			
			/**
			 * @param objects
			 */
			protected void insert(Object[] objects) {				
				super.update(objects);
			}
			
	    }
		/**
		 *  Method to edit the EB01 to Data Type association.
		 * @param eb01MappingVOEdit
		 * @return List - contains EB01 - data type association values for edit action.
		 */
		public List editDataTypeToEB01Mapping(DataTypeToEB01MappingVO eb01MappingVOEdit) {
			
			FetchDataTypeValueQueryForEdit dataTypeValueQueryForEdit = new FetchDataTypeValueQueryForEdit(
					dataSource, getDataTypeValueQueryForEdit());
			List dataTypeEB01ResultSetForEdit = dataTypeValueQueryForEdit.execute(eb01MappingVOEdit.getEb01value());
			return dataTypeEB01ResultSetForEdit;
		}
		
		
		private static final class FetchDataTypeValueQueryForEdit extends MappingSqlQuery {
			
			private FetchDataTypeValueQueryForEdit(DataSource dataSource, String query) {
				super(dataSource, query);
				declareParameter(new SqlParameter(Types.VARCHAR));
			}		
			/**
			 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
			 *      int)
			 * @param resultSet
			 * @param rowCountO
			 * @return
			 * @throws SQLException
			 */
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {		
				
				DataTypeToEB01MappingVO eb01MappingVOEdit = new DataTypeToEB01MappingVO();
				eb01MappingVOEdit.setDataTypeValue(resultSet.getString(DomainConstants.DATA_TYPE));
				eb01MappingVOEdit.setDataTypeDescription(resultSet.getString(DomainConstants.DATA_TYPE_DESC));
				return eb01MappingVOEdit;
			}
		}
		
		/**
		 * Method to save the changes in EB01 to Data Type association.
		 * @param eb01MappingVOToUpdate
		 * @param eb01MappingVOToFetch
		 * @param dataTypeListToInsert
		 * @return List -  contains EB01 - data type association values after save.
		 */
		public List saveDatatypeToEB01Mapping(DataTypeToEB01MappingVO eb01MappingVOToUpdate, DataTypeToEB01MappingVO eb01MappingVOToFetch,
				List dataTypeListFromPage) {
			List dataTypeListDBDelete = new ArrayList();
			List dataTypeListToInsert = new ArrayList();
			List dataTypeListDB = editDataTypeToEB01Mapping(eb01MappingVOToUpdate);
			int numRowsAfffectedForDelete = 0;
			int numRowsAfffectedInsert = 0;
			String deleteComment = "";
			String insertComment = "";
			
			if (null != dataTypeListDB) {
				Iterator dataTypeListDBItr = dataTypeListDB.iterator();
				while(dataTypeListDBItr.hasNext()) {
					DataTypeToEB01MappingVO mappingVODB = (DataTypeToEB01MappingVO) dataTypeListDBItr.next();
					dataTypeListDBDelete.add(mappingVODB.getDataTypeValue());
				}
			}
			if (null != dataTypeListFromPage) {
				Iterator dataTypeListFromPageItr = dataTypeListFromPage.iterator();
				while(dataTypeListFromPageItr.hasNext()) {
					DataTypeToEB01MappingVO mappingVOPage = (DataTypeToEB01MappingVO) dataTypeListFromPageItr.next();
					dataTypeListToInsert.add(mappingVOPage.getDataTypeValue());
				}
			}
			if (null != dataTypeListDBDelete) {
				for (int countDB = 0; countDB < dataTypeListDBDelete.size(); countDB++) {
					String dataTypeValue = (String) dataTypeListDBDelete.get(countDB);
					if (!dataTypeListToInsert.contains(dataTypeValue)) {
						if (null != deleteComment && !DomainConstants.EMPTY.equals(deleteComment)) {
							deleteComment = deleteComment + ", ";
							deleteComment = deleteComment + "'"+dataTypeValue+"'";
						} else {
							deleteComment = "'"+dataTypeValue+"'";
						}
						numRowsAfffectedForDelete = deleteDataTypeForSave(eb01MappingVOToUpdate, dataTypeValue);
					}
				}
			}
			if (null != dataTypeListToInsert) {
				for (int countPage = 0; countPage < dataTypeListToInsert.size(); countPage++) {
					String dataTypeValue = (String) dataTypeListToInsert.get(countPage);
					if (!dataTypeListDBDelete.contains(dataTypeValue)) {
						if (null != insertComment && !DomainConstants.EMPTY.equals(insertComment)) {
							insertComment = insertComment + ", ";
							insertComment = insertComment + "'"+dataTypeValue+"'";
						} else {
							insertComment = "'"+ dataTypeValue+"'";
						}
						numRowsAfffectedInsert = insertDataTypeForSave(eb01MappingVOToUpdate, dataTypeValue);
					}
				}
			}
			if(numRowsAfffectedForDelete >= 1 && numRowsAfffectedInsert >= 1) {
				String systemComment = "";
					systemComment = insertComment+" ADDED AND "+deleteComment+
											" DELETED";
					createManageDataTypeToEB01AuditTrial(eb01MappingVOToUpdate,systemComment);
			} else if (numRowsAfffectedForDelete >= 1 && numRowsAfffectedInsert == 0) {
				String systemComment = "";
					systemComment = deleteComment+" DELETED";
					createManageDataTypeToEB01AuditTrial(eb01MappingVOToUpdate,systemComment);
			} else if (numRowsAfffectedForDelete == 0 && numRowsAfffectedInsert >= 1) {
				String systemComment = "";
				systemComment = insertComment+" ADDED";
				createManageDataTypeToEB01AuditTrial(eb01MappingVOToUpdate,systemComment);
			}else if(numRowsAfffectedForDelete == 0 && numRowsAfffectedInsert == 0){
				eb01MappingVOToUpdate
						.setOperationMessages("No Modification to be saved.");
	}
			List dataTypeValueList = fetchDataTypeToEB01Mapping(eb01MappingVOToFetch);
			return dataTypeValueList;
		}

		private int insertDataTypeForSave(
				DataTypeToEB01MappingVO eb01MappingVOToUpdate, String dataTypeValue) {

			ManageDatatypeToEB01Insert manageDatatypeToEB01Insert = new ManageDatatypeToEB01Insert(
					dataSource, getManageDatatypeToEB01InsertQueryForSave());
			Object[] auditTrialObjects = new Object[] {
					"EB01",
					"Eligibility or Benefit Information", 
					eb01MappingVOToUpdate.getEb01value(),
					dataTypeValue,
					eb01MappingVOToUpdate.getLastUpdatedUser()
					};
			int numRowsAfffected = manageDatatypeToEB01Insert.insert(auditTrialObjects);
			logger.info("Data Type Inserted!!!");
			return numRowsAfffected;
		}

		/**
		 * Inner class for inserting data into BX_DATA_VAL_AUDIT_TRIAL table
		 *
		 */
		private static final class ManageDatatypeToEB01Insert extends SqlUpdate {

			private ManageDatatypeToEB01Insert(DataSource dataSource, String query) {
		        super(dataSource, query);
		        declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
			    compile();
		    }	
			
			/**
			 * @param objects
			 */
			protected int insert(Object[] objects) {				
				return super.update(objects);
			}
			
	    }
		private int deleteDataTypeForSave(DataTypeToEB01MappingVO eb01MappingVOToUpdate, String dataTypeValue) {
			ManageDatatypeToEB01Delete manageDatatypeToEB01Delete = new ManageDatatypeToEB01Delete(
					dataSource, getManageDatatypeToEB01DeleteQueryForSave());
			int numRowsAfffected = manageDatatypeToEB01Delete.update(eb01MappingVOToUpdate.getEb01value(), dataTypeValue);
			logger.info("Data Type Deleted!!!");
			return numRowsAfffected;
		}
		private class ManageDatatypeToEB01Delete extends SqlUpdate {

			public ManageDatatypeToEB01Delete(DataSource dataSource, String sqlQuery) {
				super(dataSource, sqlQuery);
				declareParameter(new SqlParameter("DATA_ELEMENT_VAL", Types.VARCHAR));
				declareParameter(new SqlParameter("VAR_FRMT", Types.VARCHAR));
				compile();
			}
			public int update(String eb01Id, String dataTypeValue) {
				Object[] objs = new Object[] { eb01Id, dataTypeValue};
				return super.update(objs);
			}
		}
		
		/**
		 * Method to show the history of save and delete actions.
		 * @param eb01MappingVOLocate
		 * @return List - Contains audit trail values corresponding the EB01 value.
		 */
		public List viewHistoryOfDatatypeToEB01Mapping(DataTypeToEB01MappingVO eb01MappingVOLocate) {
			FetchDataTypeToEB01HistoryQuery fetchDataTypeToEB01HistoryQuery = new FetchDataTypeToEB01HistoryQuery(
					dataSource, getDataTypeToEB01HistoryFetchQuery());
			List auditTrailHistory = fetchDataTypeToEB01HistoryQuery.execute(eb01MappingVOLocate.getEb01value());
			return auditTrailHistory;
		}
		private static final class FetchDataTypeToEB01HistoryQuery extends MappingSqlQuery {
			
			private FetchDataTypeToEB01HistoryQuery(DataSource dataSource, String query) {
				super(dataSource, query);
				declareParameter(new SqlParameter(Types.VARCHAR));
			}		
			/**
			 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
			 *      int)
			 * @param resultSet
			 * @param rowCountO
			 * @return
			 * @throws SQLException
			 */
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {		
				
				DataTypeToEB01MappingVO mappingVOHistory = new DataTypeToEB01MappingVO();
				mappingVOHistory.setEb01value(resultSet.getString(1));
				String updatedTimeStamp = BxUtil.getFormattedDate(resultSet.getTimestamp(2));
				mappingVOHistory.setUpdatedDateAndTime(updatedTimeStamp);
				mappingVOHistory.setUserId(resultSet.getString(3));
				mappingVOHistory.setSystemComments(resultSet.getString(4));
				mappingVOHistory.setUserComments(resultSet.getString(5));
				return mappingVOHistory;
			}
		}
		// EB01 - Data Type Association - END
		// BXNI June Release Changes - Start
		private String standardMessageFetchQuery;
		private String standardMessageUpdateQuery;
		private String standardMessageAuditTrailInsertQuery;
		private String standardMessageInsertQuery;
		private String standardMessageDeleteQuery;
		private String standardMessageAuditTrailQuery;
		private String standardMessageEditQuery;
		
		/**
		 * @return the standardMessageEditQuery
		 */
		public String getStandardMessageEditQuery() {
			return standardMessageEditQuery;
		}

		/**
		 * @param standardMessageEditQuery the standardMessageEditQuery to set
		 */
		public void setStandardMessageEditQuery(String standardMessageEditQuery) {
			this.standardMessageEditQuery = standardMessageEditQuery;
		}

		public String getStandardMessageAuditTrailQuery() {
			return standardMessageAuditTrailQuery;
		}

		public void setStandardMessageAuditTrailQuery(
				String standardMessageAuditTrailQuery) {
			this.standardMessageAuditTrailQuery = standardMessageAuditTrailQuery;
		}

		public String getStandardMessageDeleteQuery() {
			return standardMessageDeleteQuery;
		}

		public void setStandardMessageDeleteQuery(String standardMessageDeleteQuery) {
			this.standardMessageDeleteQuery = standardMessageDeleteQuery;
		}

		public String getStandardMessageInsertQuery() {
			return standardMessageInsertQuery;
		}

		public void setStandardMessageInsertQuery(String standardMessageInsertQuery) {
			this.standardMessageInsertQuery = standardMessageInsertQuery;
		}

		public String getStandardMessageAuditTrailInsertQuery() {
			return standardMessageAuditTrailInsertQuery;
		}

		public void setStandardMessageAuditTrailInsertQuery(
				String standardMessageAuditTrailInsertQuery) {
			this.standardMessageAuditTrailInsertQuery = standardMessageAuditTrailInsertQuery;
		}

		public String getStandardMessageUpdateQuery() {
			return standardMessageUpdateQuery;
		}

		public void setStandardMessageUpdateQuery(String standardMessageUpdateQuery) {
			this.standardMessageUpdateQuery = standardMessageUpdateQuery;
		}

		public String getStandardMessageFetchQuery() {
			return standardMessageFetchQuery;
		}

		public void setStandardMessageFetchQuery(String standardMessageFetchQuery) {
			this.standardMessageFetchQuery = standardMessageFetchQuery;
		}

		/* (non-Javadoc)
		 * @see com.wellpoint.ets.ebx.referencedata.repository.ReferenceDataRepository#fetchStandardMessage(com.wellpoint.ets.ebx.referencedata.vo.StandardMessageVO)
		 */
		public List<StandardMessageVO> fetchStandardMessage(StandardMessageVO standardMessageToFetch) {
			String standardMessage = standardMessageToFetch.getStandardMessage();
			standardMessage = "%" + standardMessage + "%";
			FetchStandardMessageQuery fetchStandardMessageQuery = new FetchStandardMessageQuery(dataSource,getStandardMessageFetchQuery());
			Object[] inputParams = new Object[] { standardMessage };
			List<StandardMessageVO> stdMessageVOSearchResult = fetchStandardMessageQuery.execute(inputParams);
			return stdMessageVOSearchResult;
		}
		private static final class FetchStandardMessageQuery extends MappingSqlQuery {

			public FetchStandardMessageQuery(DataSource dataSource,
					String standardMessageFetchQuery) {
				super(dataSource, standardMessageFetchQuery);
				declareParameter(new SqlParameter(Types.VARCHAR));
			}

			@Override
			protected Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {
				StandardMessageVO messageVO = new StandardMessageVO();
				messageVO.setStandardMessageId(resultSet.getString(1));
				messageVO.setStandardMessage(resultSet.getString(2));
				return messageVO;
			}
			
		}
		/* (non-Javadoc)
		 * @see com.wellpoint.ets.ebx.referencedata.repository.ReferenceDataRepository#deleteStandardMessage(com.wellpoint.ets.ebx.referencedata.vo.StandardMessageVO)
		 */
		public void deleteStandardMessage(StandardMessageVO standardMessageToDelete) {
			StringBuffer systemComment = new StringBuffer();
			systemComment.append("'" +editStandardMessage(standardMessageToDelete.getStandardMessageId()));
			StandardMessageQueryDelete standardMessageQueryDelete = new StandardMessageQueryDelete(
					dataSource, getStandardMessageDeleteQuery());
			int numRowsAfffected = standardMessageQueryDelete.update(standardMessageToDelete.getStandardMessageId());
			if (numRowsAfffected >= 1) {
				systemComment.append("' DELETED.") ;
				standardMessageToDelete.setOperationMessages("Standard message has been deleted succesfully");
				standardMessageToDelete.setSystemComments(systemComment.toString());
				updateStandardMessageAuditTrail(standardMessageToDelete);
			}
		}
		private class StandardMessageQueryDelete extends SqlUpdate {

			public StandardMessageQueryDelete(DataSource dataSource, String sqlQuery) {
				super(dataSource, sqlQuery);
				declareParameter(new SqlParameter(Types.VARCHAR));
				compile();
			}
			public int update(String standardMessage) {
				Object[] objs = new Object[] { standardMessage };
				return super.update(objs);
			}
		}
		
		
		/* (non-Javadoc)
		 * @see com.wellpoint.ets.ebx.referencedata.repository.ReferenceDataRepository#createStandardMessage(com.wellpoint.ets.ebx.referencedata.vo.StandardMessageVO)
		 */
		public void createStandardMessage(StandardMessageVO standardMessageToSave){
			if (!checkForStandardMessageExistsDB(standardMessageToSave)) {
				int numberOfRowsAffected = insertNewStandardMessage(standardMessageToSave);
				if (numberOfRowsAffected >= 1) {
					String systemCommentCreate = "'" +standardMessageToSave.getStandardMessage()+ "' CREATED." ;
					standardMessageToSave.setOperationMessages("Standard message has been created succesfully");
					standardMessageToSave.setSystemComments(systemCommentCreate);
					updateStandardMessageAuditTrail(standardMessageToSave);
				}
			}
		}
		/**
		 * Method to insert new standard message.
		 * @param standardMessageToSave
		 * @return
		 */
		private int insertNewStandardMessage(StandardMessageVO standardMessageToSave) {
			StandardMessageQuery standardMessageQuery = new StandardMessageQuery(
					dataSource, getStandardMessageInsertQuery());
			Object[] inputParms = new Object[] {
					standardMessageToSave.getStandardMessage(),
					standardMessageToSave.getLastUpdatedUser(),
					standardMessageToSave.getLastUpdatedUser()
					};
			int numRowsAfffected = standardMessageQuery.insert(inputParms);
			return numRowsAfffected;
		}

		private static final class StandardMessageQuery extends SqlUpdate {

			private StandardMessageQuery(DataSource dataSource, String query) {
		        super(dataSource, query);
		        declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
			    compile();
		    }	
			
			/**
			 * @param objects
			 * @return 
			 */
			protected int insert(Object[] objects) {				
				return super.update(objects);
			}
			
	    }
		
		/* (non-Javadoc)
		 * @see com.wellpoint.ets.ebx.referencedata.repository.ReferenceDataRepository#updateStandardMessage(com.wellpoint.ets.ebx.referencedata.vo.StandardMessageVO)
		 */
		public void updateStandardMessage(StandardMessageVO standardMessageToSave){
			
			if (!checkForStandardMessageExistsDB(standardMessageToSave)) {
				
				StringBuffer systemComment = new StringBuffer();
				systemComment.append("'");
				systemComment.append(editStandardMessage(standardMessageToSave.getStandardMessageId()));
				systemComment.append("'");
				UpdateStandardMessageQuery updateStandardMessageQuery = new UpdateStandardMessageQuery(dataSource);
				int numberOfRowsAffectedUpdate = updateStandardMessageQuery.update(standardMessageToSave);
				if (numberOfRowsAffectedUpdate >= 1) {
					systemComment.append(" CHANGED TO '" +standardMessageToSave.getStandardMessage() + "'");
					standardMessageToSave.setOperationMessages("Standard message has been updated succesfully");
					standardMessageToSave.setSystemComments(systemComment.toString());
					updateStandardMessageAuditTrail(standardMessageToSave);
				}
			}
		}

		/** 
		 * This method will check whether the given standard message is already availbale in the DB.
		 * @param standardMessageToSave
		 * @return
		 */
		private boolean checkForStandardMessageExistsDB(StandardMessageVO standardMessageToSave) {
			// This flag will set to true, if the standard message already exists in the DB.
			boolean stdMsgExists = false;
			StandardMessageVO standardMessage = new StandardMessageVO();
			standardMessage.setStandardMessage(DomainConstants.EMPTY);
			List <StandardMessageVO> standardMessageFromDB = fetchStandardMessage(standardMessage);
			if (null != standardMessageFromDB && !DomainConstants.EMPTY.equals(standardMessageFromDB)) {
				for (StandardMessageVO stdMsgVO : standardMessageFromDB) {
					if (stdMsgVO.getStandardMessage().equalsIgnoreCase(standardMessageToSave.getStandardMessage())) {
						stdMsgExists = true;
						break;
					}
				}
			}
			return stdMsgExists;
		}

		private class UpdateStandardMessageQuery extends SqlUpdate {

			public UpdateStandardMessageQuery(DataSource dataSource) {
				super(dataSource, getStandardMessageUpdateQuery());
				declareParameter(new SqlParameter("STD_MESSAGE", Types.VARCHAR));
				declareParameter(new SqlParameter("LST_CHG_USR", Types.VARCHAR));
				declareParameter(new SqlParameter("STD_MSG_SYS_ID", Types.VARCHAR));
			}

			public int update(StandardMessageVO standardMessageToSave) {
				Object[] objs = new Object[] {standardMessageToSave.getStandardMessage(), standardMessageToSave.getLastUpdatedUser(),
						standardMessageToSave.getStandardMessageId()};
				return super.update(objs);
			}
			
		}
		
		/* (non-Javadoc)
		 * @see com.wellpoint.ets.ebx.referencedata.repository.ReferenceDataRepository#viewHistoryOfStandardMessage()
		 */
		public List<StandardMessageVO> viewHistoryOfStandardMessage() {
			StandardMessageHistoryQuery standardMessageHistoryQuery = new StandardMessageHistoryQuery(dataSource, getStandardMessageAuditTrailQuery());
			List standardMessageHistory = standardMessageHistoryQuery.execute();
			return standardMessageHistory;
		}
		
		private static final class StandardMessageHistoryQuery extends MappingSqlQuery {
			private StandardMessageHistoryQuery(DataSource dataSource, String query) {
				super(dataSource, query);
			}

			@Override
			protected Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {
				StandardMessageVO messageVO = new StandardMessageVO();
				messageVO.setLastUpdatedUser(resultSet.getString(1));
				String updatedDateStamp = BxUtil.getFormattedDate(resultSet.getTimestamp(2));
				messageVO.setUpdatedDateAndTime(updatedDateStamp);
				messageVO.setSystemComments(resultSet.getString(3));
				messageVO.setUserComments(resultSet.getString(4));
				return messageVO;
			}
		}
		
		/**
		 * Method to insert an entry in the audit trail after delete and create action.
		 * @param standardMessage
		 */
		private void updateStandardMessageAuditTrail(StandardMessageVO standardMessage) {

			StandardMessageAuditTrailQuery standardMessageAuditTrailQuery = new StandardMessageAuditTrailQuery(
					dataSource, getStandardMessageAuditTrailInsertQuery());
			Object[] auditTrialObjects = new Object[] {
					standardMessage.getSystemComments(),
					standardMessage.getUserComments().toUpperCase(),
					standardMessage.getLastUpdatedUser()
					};
			standardMessageAuditTrailQuery.insert(auditTrialObjects);
			logger.info("Audit trial created!!!");
		}
		private static final class StandardMessageAuditTrailQuery extends SqlUpdate {

			private StandardMessageAuditTrailQuery(DataSource dataSource, String query) {
		        super(dataSource, query);
		        declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
				declareParameter(new SqlParameter(Types.VARCHAR));
			    compile();
		    }	
			
			/**
			 * @param objects
			 */
			protected void insert(Object[] objects) {				
				super.update(objects);
			}
	    }

		/**
		 * Method to edit the standard.
		 * @param stdMsgIdEdit
		 * @return
		 */
		public String editStandardMessage(String stdMsgIdEdit) {
			String stdMsg = "";
			StandardMessageEdit standardMessageEdit  =  new StandardMessageEdit(dataSource, getStandardMessageEditQuery());
			Object[] inputParams = new Object[] { stdMsgIdEdit };
			List <String> standardMessage = standardMessageEdit.execute(inputParams);
			if(null != standardMessage && !standardMessage.isEmpty()) {
				stdMsg = standardMessage.get(0);
			}
			return stdMsg;
		}
		
		private class StandardMessageEdit extends MappingSqlQuery {
			public StandardMessageEdit(DataSource dataSource, String query) {
				super(dataSource, query);
				declareParameter(new SqlParameter(Types.VARCHAR));
			}

			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {
				return resultSet.getString(1);
			}

		}
		// BXNI June Release Changes - End
		/***********************************BXNI November Release Start****************************************************/
		private String servicetypeMappingsDeleteQuery;
		private String servicetypeMappingsInsertQuery;
		private String servicetypeMappingsDeleteAllFromLOBTableQuery;
		private String servicetypeMappingsDeleteAllFromMBUTableQuery;
		private String servicetypeMappingsDeleteAllFromMappingTableQuery;
		private String servicetypeMappingsAuditTrialInsertQuery;
		private String serviceTypeMappingHistoryFetchQuery;
		
		public String getServicetypeMappingsDeleteQuery() {
			return servicetypeMappingsDeleteQuery;
		}
		public void setServicetypeMappingsDeleteQuery(String servicetypeMappingsDeleteQuery) {
			this.servicetypeMappingsDeleteQuery = servicetypeMappingsDeleteQuery;
		}
		public String getServicetypeMappingsInsertQuery() {
			return servicetypeMappingsInsertQuery;
		}
		public void setServicetypeMappingsInsertQuery(
				String servicetypeMappingsInsertQuery) {
			this.servicetypeMappingsInsertQuery = servicetypeMappingsInsertQuery;
		}
		public String getServicetypeMappingsDeleteAllFromLOBTableQuery() {
			return servicetypeMappingsDeleteAllFromLOBTableQuery;
		}
		public void setServicetypeMappingsDeleteAllFromLOBTableQuery(
				String servicetypeMappingsDeleteAllFromLOBTableQuery) {
			this.servicetypeMappingsDeleteAllFromLOBTableQuery = servicetypeMappingsDeleteAllFromLOBTableQuery;
		}
		public String getServicetypeMappingsDeleteAllFromMBUTableQuery() {
			return servicetypeMappingsDeleteAllFromMBUTableQuery;
		}
		public void setServicetypeMappingsDeleteAllFromMBUTableQuery(
				String servicetypeMappingsDeleteAllFromMBUTableQuery) {
			this.servicetypeMappingsDeleteAllFromMBUTableQuery = servicetypeMappingsDeleteAllFromMBUTableQuery;
		}
		public String getServicetypeMappingsDeleteAllFromMappingTableQuery() {
			return servicetypeMappingsDeleteAllFromMappingTableQuery;
		}
		public void setServicetypeMappingsDeleteAllFromMappingTableQuery(
				String servicetypeMappingsDeleteAllFromMappingTableQuery) {
			this.servicetypeMappingsDeleteAllFromMappingTableQuery = servicetypeMappingsDeleteAllFromMappingTableQuery;
		}
		public String getServicetypeMappingsAuditTrialInsertQuery() {
			return servicetypeMappingsAuditTrialInsertQuery;
		}
		public void setServicetypeMappingsAuditTrialInsertQuery(
				String servicetypeMappingsAuditTrialInsertQuery) {
			this.servicetypeMappingsAuditTrialInsertQuery = servicetypeMappingsAuditTrialInsertQuery;
		}
		public String getServiceTypeMappingHistoryFetchQuery() {
			return serviceTypeMappingHistoryFetchQuery;
		}
		public void setServiceTypeMappingHistoryFetchQuery(
				String serviceTypeMappingHistoryFetchQuery) {
			this.serviceTypeMappingHistoryFetchQuery = serviceTypeMappingHistoryFetchQuery;
		}

		
		/**
		 * Method to fetch the Service type Code mappings by doing a like search based on either LOB/State or MBU.
		 * @param ServiceTypeMappingVO
		 * @return result List of ServiceTypeMappingVO objects.
		 */
		public List<ServiceTypeMappingVO> fetchServiceTypeCodeToEB11Mapping (ServiceTypeMappingVO VOToFetch){
			//Data Object list of type ServiceTypeToEB11DataObject to store the resultset of the query
			List<ServiceTypeToEB11DataObject> serviceTypeCodeToEB11DataObjectList = null;
			//final resultList of type ServiceTypeMappingVO
			List<ServiceTypeMappingVO> resultList = null;
			
			StringBuffer serviceTypeCodeToEB11MappingQuery = new StringBuffer();
			String queryCondition = null;
			// if LOB Search criteria is entered by the user
			if( null != VOToFetch.getLineOfBusiness() && !VOToFetch.getLineOfBusiness().isEmpty()){
				queryCondition = " AND upper(mstr.LOB_NAME)  LIKE upper('%"+VOToFetch.getLineOfBusiness()+"%') ";
			}
			// if MBU Search criteria is entered by the user
			if( null != VOToFetch.getCommaSeperatedMbu() && !VOToFetch.getCommaSeperatedMbu().isEmpty()){
				queryCondition = " AND assn.MBU_CODE  IN ("+BxUtil.getFormattedRuleIds(VOToFetch.getCommaSeperatedMbu())+" )";
			}
			
			//SQL Query to fetch the mappings
			serviceTypeCodeToEB11MappingQuery.append(" SELECT DISTINCT  mstr.LOB_SYS_ID LOB_ID, mstr.LOB_NAME LINE_OF_BUSINESS, mstr.SSB_IND SSB_IND, ");
			serviceTypeCodeToEB11MappingQuery.append(" COMMA_SEPERATED_MBU_VALUES(mstr.LOB_SYS_ID) COMMA_SEPERATED_MBU_VALUES ");
			serviceTypeCodeToEB11MappingQuery.append(" FROM BX_LOB_MSTR mstr, BX_LOB_MBU_ASSN assn ");
			serviceTypeCodeToEB11MappingQuery.append(" WHERE mstr.LOB_SYS_ID = assn.LOB_SYS_ID (+) ");
			if(null != queryCondition){
				serviceTypeCodeToEB11MappingQuery.append(queryCondition);
			}
			ServiceTypeToEB11FetchQuery serviceTypeToEB11FetchQuery = new ServiceTypeToEB11FetchQuery(
					dataSource,serviceTypeCodeToEB11MappingQuery.toString());
			serviceTypeCodeToEB11DataObjectList = serviceTypeToEB11FetchQuery.execute();
			//to create the main ServicetypeMappingVO object from the data object
			resultList = createServiceTypeMappingObject(serviceTypeCodeToEB11DataObjectList);
			List<ServiceTypeCodeToEB11VO> mappingsList = new ArrayList<ServiceTypeCodeToEB11VO>();
			//sort the result list according to the lob name
			if(null!= resultList){
				sortServiceTypeMappingVOResultList(resultList);
			}
			return resultList;
		}
		//method sorts the serviceTypeCodeMappingsList
		private void sortServiceTypeCodeToEB11VOList(List<ServiceTypeCodeToEB11VO> mappingsList) {
	        Collections.sort(mappingsList, new Comparator<ServiceTypeCodeToEB11VO>() {
	            public int compare(ServiceTypeCodeToEB11VO a, ServiceTypeCodeToEB11VO b) {
	            	if(StringUtils.isBlank(a.getServiceTypeCode()) || StringUtils.isBlank(b.getServiceTypeCode())){
	            		return 0;
	            	}
	            	if(BxUtil.isInteger(a.getServiceTypeCode()) && BxUtil.isInteger(b.getServiceTypeCode())){
	            		return Integer.parseInt(a.getServiceTypeCode()) - Integer.parseInt(b.getServiceTypeCode());
	            	}else{
	            		return a.getServiceTypeCode().compareTo(b.getServiceTypeCode());
	            	}
	            	
	            }
	        });
	    }
		//method sorts the resultList
		private void sortServiceTypeMappingVOResultList(List<ServiceTypeMappingVO> serviceTypeMappingVOResultList) {
			Collections.sort(serviceTypeMappingVOResultList, new Comparator<ServiceTypeMappingVO>() {
	            public int compare(ServiceTypeMappingVO a, ServiceTypeMappingVO b) {
	                if(StringUtils.isBlank(a.getLineOfBusiness()) || StringUtils.isBlank(b.getLineOfBusiness())){
	                	return 0;
	                }
	            	return a.getLineOfBusiness().compareTo(b.getLineOfBusiness());
	            }
	        });
	    }

		/**
		 * ServiceTypeToEB11FetchQuery
		 * Query fetches the Service Type - Eb11 mappings
		 */

		private class ServiceTypeToEB11FetchQuery extends MappingSqlQuery {

			public ServiceTypeToEB11FetchQuery(DataSource dataSource, String query) {
				super(dataSource, query);
			}
			@Override
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {
				ServiceTypeToEB11DataObject serviceTypeToEB11DataObject = new ServiceTypeToEB11DataObject();
				serviceTypeToEB11DataObject.setLobId(resultSet.getString("LOB_ID"));
				serviceTypeToEB11DataObject.setLineOfBusiness(resultSet.getString("LINE_OF_BUSINESS"));
				serviceTypeToEB11DataObject.setCommaSeperatedMbu(resultSet.getString("COMMA_SEPERATED_MBU_VALUES"));
				serviceTypeToEB11DataObject.setSsbIndicator(resultSet.getString("SSB_IND"));
				return serviceTypeToEB11DataObject;

			}
		}
		/**
		 * Private method creates the main resultList to be returned from the list of data object
		 * @param List of type ServiceTypeToEB11DataObject List
		 * @return List of Type ServiceTypeMappingVO
		 */
		private List<ServiceTypeMappingVO> createServiceTypeMappingObject(List<ServiceTypeToEB11DataObject> serviceTypeCodeToEB11DataObjectList){
			//Final ResultVO List which is returned
			List<ServiceTypeMappingVO> resultVOList = new ArrayList<ServiceTypeMappingVO>();
			//Result Object
			ServiceTypeMappingVO resultVO = null;
			//ResultVO map maintained for grouping up of the STC-EB11 mappings corresponding to the LOB
			//key of the map will be the lobId and value will be the final ServiceTypeMappingVO object
			Map <String, ServiceTypeMappingVO> resultVOMap = new HashMap<String, ServiceTypeMappingVO>();
			//Iterating the data object
			for(int i = 0; i <serviceTypeCodeToEB11DataObjectList.size();i++){
				ServiceTypeToEB11DataObject serviceTypeToEB11DataObject = serviceTypeCodeToEB11DataObjectList.get(i);				
				//if the map already contains the key, then takes only the STC-EB11 mappings and adds to the ServiceTypeCodeToEB11VO List
				if (resultVOMap.containsKey(serviceTypeToEB11DataObject.getLobId())) {
					//the mapping is obtained by passing the key
					ServiceTypeMappingVO resultVOTemp = resultVOMap.get(serviceTypeToEB11DataObject.getLobId());
					//STC-EB11 mappings from the data object is set to ServiceTypeCodeToEB11VO object and adding to a list,
					//finally setting it to the result object
					ServiceTypeCodeToEB11VO serviceTypeCodeToEB11VO = createServiceTypeCodeToEB11VOObject(serviceTypeToEB11DataObject);
					//if the ServiceTypeCodeToEB11VO List exists, then adding the ServiceTypeCodeToEB11VO object to the list.
					if (null != resultVOTemp.getServiceTypeCodeToEB11VOList()) {
						resultVOTemp.getServiceTypeCodeToEB11VOList().add(serviceTypeCodeToEB11VO);
					}
					//if the ServiceTypeCodeToEB11VO List is null, then creates a new list and add the ServiceTypeCodeToEB11VO object to it.
					else {
						List<ServiceTypeCodeToEB11VO> serviceTypeCodeToEB11VOList = new ArrayList<ServiceTypeCodeToEB11VO>();
						serviceTypeCodeToEB11VOList.add(serviceTypeCodeToEB11VO);
						resultVOTemp.setServiceTypeCodeToEB11VOList(serviceTypeCodeToEB11VOList);
					}					
				}
				//the map does not contains the key. hence creating a new ServiceTypeMappingVO and setting all the attributes
				else {
					resultVO = new ServiceTypeMappingVO();
					//lob will be the same for data object and result object
					resultVO.setLobId(serviceTypeToEB11DataObject.getLobId());
					resultVO.setLineOfBusiness(serviceTypeToEB11DataObject.getLineOfBusiness());
					resultVO.setCommaSeperatedMbu(serviceTypeToEB11DataObject.getCommaSeperatedMbu());
					if(DomainConstants.SSB_INDICATOR_N.equals(serviceTypeToEB11DataObject.getSsbIndicator())){
						resultVO.setSsbIndicator(DomainConstants.SSB_INDICATOR_N);
					}else if(DomainConstants.SSB_INDICATOR_Y.equals(serviceTypeToEB11DataObject.getSsbIndicator())){
						resultVO.setSsbIndicator(DomainConstants.SSB_INDICATOR_Y);
					}
					//STC-EB11 mappings from the data object is set to ServiceTypeCodeToEB11VO object and adding to a list,
					//finally setting it to the result object
					ServiceTypeCodeToEB11VO serviceTypeCodeToEB11VO = createServiceTypeCodeToEB11VOObject(serviceTypeToEB11DataObject);
					//creating a new list and adding the ServiceTypeCodeToEB11VO object to it.
					List<ServiceTypeCodeToEB11VO> serviceTypeCodeToEB11VOList = new ArrayList<ServiceTypeCodeToEB11VO>();
					serviceTypeCodeToEB11VOList.add(serviceTypeCodeToEB11VO);
					resultVO.setServiceTypeCodeToEB11VOList(serviceTypeCodeToEB11VOList);
					//putting the key and value into the resultVOMap
					resultVOMap.put(serviceTypeToEB11DataObject.getLobId(), resultVO);
				}			
			}
			//adding the ServiceTypeMappingVO to the result list
			resultVOList.addAll(resultVOMap.values());
			return resultVOList;
		}
		/**
		 * Private method creates the ServiceTypeCodeToEB11VO object
		 * @param ServiceTypeToEB11DataObject
		 * @return ServiceTypeCodeToEB11VO
		 */
		private ServiceTypeCodeToEB11VO createServiceTypeCodeToEB11VOObject(ServiceTypeToEB11DataObject serviceTypeToEB11DataObject){
			ServiceTypeCodeToEB11VO serviceTypeCodeToEB11VO = new ServiceTypeCodeToEB11VO();
			serviceTypeCodeToEB11VO.setServiceTypeCode(serviceTypeToEB11DataObject.getServiceType());
			serviceTypeCodeToEB11VO.setServiceTypeCodeDesc(serviceTypeToEB11DataObject.getServiceTypeDesc());
			serviceTypeCodeToEB11VO.setEb11(serviceTypeToEB11DataObject.getEb11());
			serviceTypeCodeToEB11VO.setEb11Desc(serviceTypeToEB11DataObject.getEb11Desc());
			serviceTypeCodeToEB11VO.setPlaceOfService(serviceTypeToEB11DataObject.getPlaceOfService());
			serviceTypeCodeToEB11VO.setPlaceOfServiceDesc(serviceTypeToEB11DataObject.getPlaceOfServiceDesc());
			return serviceTypeCodeToEB11VO;
		}
		/**
		 * Method to view the Service type Code-EB11 mappings associated with an LOB/State
		 * @param ServiceTypeMappingVO
		 * @return result List of ServiceTypeMappingVO object.
		 */
		public List<ServiceTypeMappingVO> viewServiceTypeCodeToEB11Mapping (ServiceTypeMappingVO VOToView){
			//Data Object list of type ServiceTypeToEB11DataObject to store the resultset of the query
			List<ServiceTypeToEB11DataObject> serviceTypeCodeToEB11DataObjectList = null;
			//final resultList of type ServiceTypeMappingVO
			List<ServiceTypeMappingVO> resultList = null;
			
			StringBuffer viewServiceTypeToEB11MappingsQuery = new StringBuffer();
			String queryCondition = "AND mstr.LOB_SYS_ID = '"+VOToView.getLobId()+"'";
			
			//SQL Query to fetch the STC-EB11 mappings
			viewServiceTypeToEB11MappingsQuery.append(" SELECT DISTINCT  mstr.LOB_SYS_ID LOB_ID, mstr.LOB_NAME LINE_OF_BUSINESS,mstr.SSB_IND SSB_IND, ");
			viewServiceTypeToEB11MappingsQuery.append(" COMMA_SEPERATED_MBU_VALUES(mstr.LOB_SYS_ID) COMMA_SEPERATED_MBU_VALUES, ");
			viewServiceTypeToEB11MappingsQuery.append(" mapg.STC SERVICE_TYPE_CODE, mapg.EB11 EB11, mapg.PLC_OF_SRVC PLACE_OF_SERVICE, ");
			viewServiceTypeToEB11MappingsQuery.append(" (select val_desc_txt from bx_hippa_segment_val where data_element_id = 'EB03' and data_element_val = mapg.STC) SERVICE_TYPE_CODE_desc, ");
			viewServiceTypeToEB11MappingsQuery.append(" (select val_desc_txt from bx_hippa_segment_val where data_element_id = 'III02' and data_element_val = mapg.PLC_OF_SRVC) PLACE_OF_SERVICE_desc, ");
			viewServiceTypeToEB11MappingsQuery.append(" (select val_desc_txt from bx_hippa_segment_val where data_element_id = 'EB11' and data_element_val = mapg.EB11) EB11_desc ");
			viewServiceTypeToEB11MappingsQuery.append(" FROM BX_LOB_MSTR mstr, BX_LOB_MBU_ASSN assn, BX_STC_EB11_MAPPING mapg ");
			viewServiceTypeToEB11MappingsQuery.append(" WHERE mstr.LOB_SYS_ID = assn.LOB_SYS_ID(+)  ");
			viewServiceTypeToEB11MappingsQuery.append(" AND	 mstr.LOB_SYS_ID = mapg.LOB_SYS_ID(+) ");
			viewServiceTypeToEB11MappingsQuery.append(queryCondition);
			
			ServiceTypeToEB11ViewQuery serviceTypeToEB11ViewQuery = new ServiceTypeToEB11ViewQuery(
					dataSource,viewServiceTypeToEB11MappingsQuery.toString());
			serviceTypeCodeToEB11DataObjectList = serviceTypeToEB11ViewQuery.execute();
			//to create the main ServicetypeMappingVO object from the data object
			resultList = createServiceTypeMappingObject(serviceTypeCodeToEB11DataObjectList);
			if(null != resultList && !resultList.isEmpty()){
				resultList.get(0).setOperationMessages(VOToView.getOperationMessages());
			}
			
			
			List<ServiceTypeCodeToEB11VO> mappingsList = new ArrayList<ServiceTypeCodeToEB11VO>();
			if(null!= resultList){
				mappingsList = resultList.get(0).getServiceTypeCodeToEB11VOList();
				
			}
			//sort the result list according to the service Type code
			if(null!= mappingsList){
				sortServiceTypeCodeToEB11VOList(mappingsList); 
			}
			return resultList;
		}
		/**
		 * ServiceTypeToEB11FetchQuery
		 * Query fetches the Service Type - Eb11 mappings
		 */

		private class ServiceTypeToEB11ViewQuery extends MappingSqlQuery {

			public ServiceTypeToEB11ViewQuery(DataSource dataSource, String query) {
				super(dataSource, query);
			}
			@Override
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {
				ServiceTypeToEB11DataObject serviceTypeToEB11DataObject = new ServiceTypeToEB11DataObject();
				serviceTypeToEB11DataObject.setLobId(resultSet.getString("LOB_ID"));
				serviceTypeToEB11DataObject.setLineOfBusiness(resultSet.getString("LINE_OF_BUSINESS"));
				serviceTypeToEB11DataObject.setCommaSeperatedMbu(resultSet.getString("COMMA_SEPERATED_MBU_VALUES"));
				serviceTypeToEB11DataObject.setSsbIndicator(resultSet.getString("SSB_IND"));
				serviceTypeToEB11DataObject.setServiceType(resultSet.getString("SERVICE_TYPE_CODE"));
				serviceTypeToEB11DataObject.setServiceTypeDesc(resultSet.getString("SERVICE_TYPE_CODE_desc"));
				serviceTypeToEB11DataObject.setEb11(resultSet.getString("EB11"));
				serviceTypeToEB11DataObject.setEb11Desc(resultSet.getString("EB11_desc"));
				serviceTypeToEB11DataObject.setPlaceOfService(resultSet.getString("PLACE_OF_SERVICE"));
				serviceTypeToEB11DataObject.setPlaceOfServiceDesc(resultSet.getString("PLACE_OF_SERVICE_desc"));
				return serviceTypeToEB11DataObject;

			}
		}
		
		/**
		 * Method to delete all the  Service type Code-EB11 mappings associated with an LOB/State
		 * @param ServiceTypeMappingVO
		 * @return result List of ServiceTypeMappingVO object list.
		 */
		public List<ServiceTypeMappingVO> deleteServiceTypeCodeToEB11Mapping (ServiceTypeMappingVO vOToDelete, ServiceTypeMappingVO vOToFetch){
			//Delete from the mapping table BX_STC_EB11_MAPPING
			ServiceTypeAssnDeleteAllFromMappingTable serviceTypeAssnDeleteAllFromMappingTable = new ServiceTypeAssnDeleteAllFromMappingTable(
					dataSource, getServicetypeMappingsDeleteAllFromMappingTableQuery());
			int rowsAfffectedMappingTable = serviceTypeAssnDeleteAllFromMappingTable.update(vOToDelete.getLobId());
			logger.info("Number of rows affected while Deleting from mapping table: " + rowsAfffectedMappingTable);
			
			//Delete from the MBU association table BX_LOB_MBU_ASSN
			ServiceTypeAssnDeleteAllFromMBUAssnTable serviceTypeAssnDeleteAllFromMBUAssnTable = new ServiceTypeAssnDeleteAllFromMBUAssnTable(
					dataSource, getServicetypeMappingsDeleteAllFromMBUTableQuery());
			int rowsAfffectedAssnTable = serviceTypeAssnDeleteAllFromMBUAssnTable.update(vOToDelete.getLobId());
			logger.info("Number of rows affected while Deleting from MBU association table: " + rowsAfffectedAssnTable);
			
			
			//Delete from the LOB Master table BX_LOB_MSTR
			ServiceTypeAssnDeleteAllFromLobTable serviceTypeAssnDeleteAllFromLobTable = new ServiceTypeAssnDeleteAllFromLobTable(
					dataSource, getServicetypeMappingsDeleteAllFromLOBTableQuery());
			int rowsAfffectedLobTable = serviceTypeAssnDeleteAllFromLobTable.update(vOToDelete.getLobId());
			logger.info("Number of rows affected while Deleting from MBU association table: " + rowsAfffectedLobTable);

			if(rowsAfffectedMappingTable >= 1 || rowsAfffectedAssnTable >=1 || rowsAfffectedLobTable >= 1 ){
				//Audit trial insert
				String systemComments = "LOB '"+vOToDelete.getLineOfBusiness()+"' and all associated mappings Deleted.";
				updateServiceTypeCodeToEB11MappingAuditTrail(vOToDelete,systemComments);
			}
			return fetchServiceTypeCodeToEB11Mapping(vOToFetch);
		}
		/**
		 * ServiceTypeAssnDeleteAllFromMappingTable Query
		 */
		private class ServiceTypeAssnDeleteAllFromMappingTable extends SqlUpdate {

			public ServiceTypeAssnDeleteAllFromMappingTable(DataSource dataSource, String sqlQuery) {
				super(dataSource, sqlQuery);
				declareParameter(new SqlParameter("LOB_SYS_ID", Types.INTEGER));
				compile();
			}
			public int update(String lobId) {
				Object[] objs = new Object[] { lobId };
				return super.update(objs);
			}
		}
		
		/**
		 * ServiceTypeAssnDeleteAllFromMBUAssnTable Query
		 */
		private class ServiceTypeAssnDeleteAllFromMBUAssnTable extends SqlUpdate {

			public ServiceTypeAssnDeleteAllFromMBUAssnTable(DataSource dataSource, String sqlQuery) {
				super(dataSource, sqlQuery);
				declareParameter(new SqlParameter("LOB_SYS_ID", Types.INTEGER));
				compile();
			}
			public int update(String lobId) {
				Object[] objs = new Object[] { lobId };
				return super.update(objs);
			}
		}
		
		/**
		 * ServiceTypeAssnDeleteAllFromLobTable Query
		 */
		private class ServiceTypeAssnDeleteAllFromLobTable extends SqlUpdate {

			public ServiceTypeAssnDeleteAllFromLobTable(DataSource dataSource, String sqlQuery) {
				super(dataSource, sqlQuery);
				declareParameter(new SqlParameter("LOB_SYS_ID", Types.INTEGER));
				compile();
			}
			public int update(String lobId) {
				Object[] objs = new Object[] { lobId };
				return super.update(objs);
			}
		}
		
		
		public List<ServiceTypeMappingVO> editServiceTypeCodeToEB11Mapping(ServiceTypeMappingVO VOToEdit){
			return null;
		}
		public List<ServiceTypeMappingVO> saveServiceTypeCodeToEB11Mapping (ServiceTypeMappingVO vOToSave, ServiceTypeMappingVO vOToFetch){
			
			String deleteComment = "";
			String insertComment = "";
			int numRowsAfffectedForDelete = 0;
			int numRowsAfffectedInsert = 0;
			List<String> systemCommentsAuditTrialList = new ArrayList<String>();
			
			//get the serviceTypeCodeToEB11VOList From Page for comparing with the DB List for appropriate Insertion /Deletion of Data
			List<ServiceTypeCodeToEB11VO> serviceTypeCodeToEB11VOListFromPage = vOToSave.getServiceTypeCodeToEB11VOList();
			
			//get the serviceTypeCodeToEB11VOList From DB for comparing with the Page List for appropriate Insertion /Deletion of Data
			
			List<ServiceTypeMappingVO> viewResultList = new ArrayList<ServiceTypeMappingVO>();
			ServiceTypeMappingVO serviceTypeMappingVOFromDB = null;
			if(null != vOToSave.getLobId() && !vOToSave.getLobId().isEmpty()){
				viewResultList = viewServiceTypeCodeToEB11Mapping(vOToSave);
			}
			if(null != viewResultList && !viewResultList.isEmpty()){
				 serviceTypeMappingVOFromDB = viewResultList.get(0);
			}
			List<ServiceTypeCodeToEB11VO> serviceTypeCodeToEB11VOListFromDB =serviceTypeMappingVOFromDB.getServiceTypeCodeToEB11VOList();
			
			boolean deleteMappingsFlag = false;
			
			//Iterate the DB List and for each value of the DB list, iterate the page list to check whether the mapping exists in DB list. 
			//If exists, then no operation to be done.
			//if the mapping from DB does not exists in the page list, then Delete operation to be fired for that mapping
			
			for(ServiceTypeCodeToEB11VO dbMapping: serviceTypeCodeToEB11VOListFromDB){
				deleteMappingsFlag = false;
				for (ServiceTypeCodeToEB11VO pageMapping: serviceTypeCodeToEB11VOListFromPage){
					String serviceTypeCodeDB = "";
					if(null != dbMapping.getServiceTypeCode()){
						serviceTypeCodeDB = dbMapping.getServiceTypeCode();
					}
					String eb11DB = "";
					if(null != dbMapping.getEb11()){
						eb11DB = dbMapping.getEb11();
					}
					String placeOfServiceDB = "";
					if(null != dbMapping.getPlaceOfService()){
						placeOfServiceDB = dbMapping.getPlaceOfService();
					}
					
					String serviceTypeCodePage = "";
					if(null != pageMapping.getServiceTypeCode()){
						serviceTypeCodePage = pageMapping.getServiceTypeCode();
					}
					String eb11Page = "";
					if(null != pageMapping.getEb11()){
						eb11Page = pageMapping.getEb11();
					}
					String placeOfServicePage = "";
					if(null != pageMapping.getPlaceOfService()){
						placeOfServicePage = pageMapping.getPlaceOfService();
					}
					
					//if the mapping comparison becomes true, means the db mapping is there in page mapping list, and no deletion needed.
					if(serviceTypeCodeDB.equals(serviceTypeCodePage) && eb11DB.equals(eb11Page) && placeOfServiceDB.equals(placeOfServicePage)){
						deleteMappingsFlag = true;
						break;
					}
				}
				if(!deleteMappingsFlag){
					numRowsAfffectedForDelete = deleteServiceTypeCodeToEB11MappingForSave(vOToSave.getLobId(),dbMapping);
					if(numRowsAfffectedForDelete >= 1){
						//Code for Audit trial Insert while deleting the mapping
						String systemCommentsInsert = "EB03 = '"+dbMapping.getServiceTypeCode()+"'";
						if(!StringUtils.isBlank(dbMapping.getEb11())){
							systemCommentsInsert = systemCommentsInsert + ", EB11 = '"+dbMapping.getEb11()+"'";
						}
						if(!StringUtils.isBlank(dbMapping.getPlaceOfService())){
							systemCommentsInsert = systemCommentsInsert + ", POS = '"+dbMapping.getPlaceOfService()+"'";
						}
						systemCommentsInsert = systemCommentsInsert + " mapping deleted";
						systemCommentsAuditTrialList.add(systemCommentsInsert);
					}
				}
			}
			
			boolean insertMappingsFlag = false;
			
			//Iterate the Page List and for each value of the Page list, iterate the DB list to check whether the mapping exists in page list. 
			//If exists, then no operation to be done.
			//if the mapping from page list does not exists in the DB list, then Insert operation to be fired for that mapping
			
			for(ServiceTypeCodeToEB11VO pageMapping: serviceTypeCodeToEB11VOListFromPage){
				insertMappingsFlag = false;
				for (ServiceTypeCodeToEB11VO dbMapping: serviceTypeCodeToEB11VOListFromDB){

					String serviceTypeCodePage = "";
					if(null != pageMapping.getServiceTypeCode()){
						serviceTypeCodePage = pageMapping.getServiceTypeCode();
					}
					String eb11Page = "";
					if(null != pageMapping.getEb11()){
						eb11Page = pageMapping.getEb11();
					}
					String placeOfServicePage = "";
					if(null != pageMapping.getPlaceOfService()){
						placeOfServicePage = pageMapping.getPlaceOfService();
					}

					String serviceTypeCodeDB = "";
					if(null != dbMapping.getServiceTypeCode()){
						serviceTypeCodeDB = dbMapping.getServiceTypeCode();
					}
					String eb11DB = "";
					if(null != dbMapping.getEb11()){
						eb11DB = dbMapping.getEb11();
					}
					String placeOfServiceDB = "";
					if(null != dbMapping.getPlaceOfService()){
						placeOfServiceDB = dbMapping.getPlaceOfService();
					}

					//if the mapping comparison becomes true, means the page mapping is there in db, and no insertion needed.
					if(serviceTypeCodePage.equals(serviceTypeCodeDB) && eb11Page.equals(eb11DB) && placeOfServicePage.equals(placeOfServiceDB)){
						insertMappingsFlag = true;
						break;
					}
				}
				if(!insertMappingsFlag){
					numRowsAfffectedInsert = insertServiceTypeCodeToEB11MappingForSave(vOToSave.getLobId(),vOToSave.getLastUpdatedUser(),pageMapping);
					if(numRowsAfffectedInsert >= 1){
						//Code for Audit trial Insert while deleting the mapping
						String systemCommentsDelete = "EB03 = '"+pageMapping.getServiceTypeCode()+"'";
						if(!StringUtils.isBlank(pageMapping.getEb11())){
							systemCommentsDelete = systemCommentsDelete + ", EB11 = '"+pageMapping.getEb11()+"'";
						}
						if(!StringUtils.isBlank(pageMapping.getPlaceOfService())){
							systemCommentsDelete = systemCommentsDelete + ", POS = '"+pageMapping.getPlaceOfService()+"'";
						}
						systemCommentsDelete = systemCommentsDelete + " mapping added";
						systemCommentsAuditTrialList.add(systemCommentsDelete);
					}
				}
			}
			
			if(null != systemCommentsAuditTrialList && !systemCommentsAuditTrialList.isEmpty()){
				updateServiceTypeCodeToEB11MappingAuditTrail(vOToSave, BxUtil.getAsCSV(systemCommentsAuditTrialList));
			}
			if(numRowsAfffectedForDelete >= 1 && numRowsAfffectedInsert >= 1) {
				String systemComment = "";
			} else if (numRowsAfffectedForDelete >= 1 && numRowsAfffectedInsert == 0) {
				String systemComment = "";
			} else if (numRowsAfffectedForDelete == 0 && numRowsAfffectedInsert >= 1) {
				String systemComment = "";
			}else if(numRowsAfffectedForDelete == 0 && numRowsAfffectedInsert == 0 && insertMappingsFlag && deleteMappingsFlag){
				if(StringUtils.isBlank(vOToSave.getOperationMessages())){ 
					vOToSave
						.setOperationMessages(DomainConstants.NO_MODFCN_TO_SAVE);
				}
			}
			
			return viewServiceTypeCodeToEB11Mapping(vOToSave);
		}
		
		private int deleteServiceTypeCodeToEB11MappingForSave(String lobId,ServiceTypeCodeToEB11VO dbMapping) {
			StringBuffer deleteServiceTypeMappingsQuery = new StringBuffer();
			deleteServiceTypeMappingsQuery.append(" DELETE FROM BX_STC_EB11_MAPPING WHERE LOB_SYS_ID = '"+lobId+"' ");
			deleteServiceTypeMappingsQuery.append(" AND STC = '"+dbMapping.getServiceTypeCode()+"' ");
			if(null != dbMapping.getEb11() && !dbMapping.getEb11().isEmpty()){
				deleteServiceTypeMappingsQuery.append(" AND EB11 = '"+dbMapping.getEb11()+"' ");
			}else{
				deleteServiceTypeMappingsQuery.append(" AND EB11 IS NULL  ");
			}
			if(null != dbMapping.getPlaceOfService() && !dbMapping.getPlaceOfService().isEmpty()){
				deleteServiceTypeMappingsQuery.append(" AND PLC_OF_SRVC = '"+dbMapping.getPlaceOfService()+"'");
			}else{
				deleteServiceTypeMappingsQuery.append(" AND PLC_OF_SRVC IS NULL ");
			}
			
			ServiceTypeMappingsDelete serviceTypeMappingsDelete = new ServiceTypeMappingsDelete(
					dataSource, deleteServiceTypeMappingsQuery.toString());
			int numRowsAfffected = serviceTypeMappingsDelete.update();
			logger.info("Number of rows affected while Deleting the mapping: " + numRowsAfffected);
			
			return numRowsAfffected;
		}
		private class ServiceTypeMappingsDelete extends SqlUpdate {

			public ServiceTypeMappingsDelete(DataSource dataSource, String sqlQuery) {
				super(dataSource, sqlQuery);
				compile();
			}
			public int update() {
				return super.update();
			}
		}
		
		private int insertServiceTypeCodeToEB11MappingForSave(String lobId,String user,ServiceTypeCodeToEB11VO pageMapping) {
			ServiceTypeMappingsInsert serviceTypeMappingsInsert = new ServiceTypeMappingsInsert(
					dataSource, getServicetypeMappingsInsertQuery());
			int numRowsAfffected = serviceTypeMappingsInsert.update(lobId,user,pageMapping);
			logger.info("Number of rows affected while Inserting the mapping: " + numRowsAfffected);
			
			return numRowsAfffected;
		}
		private class ServiceTypeMappingsInsert extends SqlUpdate {

			public ServiceTypeMappingsInsert(DataSource dataSource, String sqlQuery) {
				super(dataSource, sqlQuery);
				declareParameter(new SqlParameter("LOB_SYS_ID", Types.INTEGER));
				declareParameter(new SqlParameter("STC", Types.VARCHAR));
				declareParameter(new SqlParameter("EB11", Types.VARCHAR));
				declareParameter(new SqlParameter("PLC_OF_SRVC", Types.VARCHAR));
				declareParameter(new SqlParameter("LST_CHG_TMS", Types.VARCHAR));
				compile();
			}
			public int update(String lobId,String user, ServiceTypeCodeToEB11VO pageMapping) {
				Object[] objs = new Object[] { lobId,
						pageMapping.getServiceTypeCode(),
						pageMapping.getEb11(),
						pageMapping.getPlaceOfService(),user };
				return super.update(objs);
			}
		}
		/**
		 * Method checks if an LOB already exists while create flow. If so, returns true
		 * @param lobName
		 * @return boolean
		 */
		public boolean isLOBMappingExists(String lobName){
			StringBuffer mappingExists = new StringBuffer();
			List<ServiceTypeToEB11DataObject> serviceTypeCodeToEB11DataObjectList = null;
			List<ServiceTypeMappingVO> resultList = null;
			
			mappingExists.append(" SELECT LOB_SYS_ID LOB_ID FROM BX_LOB_MSTR WHERE upper(LOB_NAME) =upper('"+lobName+"') ");
			
			IsLOBMappingsExistsQuery isLOBMappingsExistsQuery = new IsLOBMappingsExistsQuery(
					dataSource,mappingExists.toString());
			serviceTypeCodeToEB11DataObjectList = isLOBMappingsExistsQuery.execute();
			//to create the main ServicetypeMappingVO object from the data object
			resultList = createServiceTypeMappingObject(serviceTypeCodeToEB11DataObjectList);
			if(resultList != null && !resultList.isEmpty()){
				return true;
			}else{
				return false;
			}
		}
		
		
		/**
		 * IsLOBMappingsExistsQuery
		 * Query fetches the Service Type - Eb11 mappings
		 */

		private class IsLOBMappingsExistsQuery extends MappingSqlQuery {

			public IsLOBMappingsExistsQuery(DataSource dataSource, String query) {
				super(dataSource, query);
			}
			@Override
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {
				
				ServiceTypeToEB11DataObject serviceTypeToEB11DataObject = new ServiceTypeToEB11DataObject();
				serviceTypeToEB11DataObject.setLobId(resultSet.getString("LOB_ID"));
				return serviceTypeToEB11DataObject;
			}
		}
		/**
		 * Method inserts LOB into the LOB table and returns the lob_sys_id created
		 * @param vOToSave
		 */
		public String persistLOB(ServiceTypeMappingVO voToSaveAfterValidation){
			
			StringBuffer insertLOB = new StringBuffer();
			insertLOB.append(" INSERT INTO BX_LOB_MSTR VALUES( (SELECT NVL(MAX(LOB_SYS_ID),0)+1 FROM BX_LOB_MSTR), '");
			insertLOB.append(voToSaveAfterValidation.getLineOfBusiness()+"', '"+voToSaveAfterValidation.getSsbIndicator()+"', sysdate, '"+
								voToSaveAfterValidation.getLastUpdatedUser()+"')");

			PersistLOB persistLOB = new PersistLOB(dataSource, insertLOB.toString());
			int numRowsAfffected = persistLOB.update();
			
			if(numRowsAfffected >= 1){
				logger.info("Number of rows affected while persisting the LOB: " + numRowsAfffected);
				voToSaveAfterValidation.setOperationMessages(DomainConstants.SAVE_SERVICE_TYPE_ASSN_SUCCESS_MSG);
				
				StringBuffer lobMappingExists = new StringBuffer();
				List<ServiceTypeToEB11DataObject> serviceTypeCodeToEB11DataObjectList = null;
				List<ServiceTypeMappingVO> resultList = new ArrayList<ServiceTypeMappingVO>();


				lobMappingExists.append(" SELECT LOB_SYS_ID LOB_ID FROM BX_LOB_MSTR WHERE LOB_NAME ='");
				lobMappingExists.append(voToSaveAfterValidation.getLineOfBusiness());
				lobMappingExists.append("' ");

				IsLOBMappingsExistsQuery isLOBMappingsExistsQuery = new IsLOBMappingsExistsQuery(
						dataSource,lobMappingExists.toString());
				serviceTypeCodeToEB11DataObjectList = isLOBMappingsExistsQuery.execute();

				if(null != serviceTypeCodeToEB11DataObjectList && !serviceTypeCodeToEB11DataObjectList.isEmpty()){
					return serviceTypeCodeToEB11DataObjectList.get(0).getLobId();
				}else{
					return null;
				}
			}else{
				return null;
			}
		}
		/**
		 * PersistLOBQuery
		 */
		private class PersistLOB extends SqlUpdate {

			public PersistLOB(DataSource dataSource, String sqlQuery) {
				super(dataSource, sqlQuery);
				compile();
			}
			public int update() {
				return super.update();
			}
		}
		
		/**
		 * Method checks if an MBU already exists while create flow. If so, returns true
		 * @param mbuInCSVwithSingleQuote
		 * @return coma separated existing mbu's
		 */
		public String isMBUMappingExists(String mbuInCSVwithSingleQuote, String lobId){
			StringBuffer mbuMappingExists = new StringBuffer();
			List<ServiceTypeToEB11DataObject> serviceTypeCodeToEB11DataObjectList = null;
			List<ServiceTypeMappingVO> resultList = null;
			
			mbuMappingExists.append(" SELECT DISTINCT LOB_SYS_ID LOB_ID,MBU_CODE FROM BX_LOB_MBU_ASSN WHERE MBU_CODE IN("+mbuInCSVwithSingleQuote+")");
			if(!StringUtils.isBlank(lobId)){
				mbuMappingExists.append(" AND LOB_SYS_ID <> '"+lobId+"'");
			}
			IsMBUMappingsExistsQuery isMBUMappingsExistsQuery = new IsMBUMappingsExistsQuery(
					dataSource,mbuMappingExists.toString());
			serviceTypeCodeToEB11DataObjectList = isMBUMappingsExistsQuery.execute();
			List<String> existingMBUList = new ArrayList<String>();
			for(ServiceTypeToEB11DataObject i: serviceTypeCodeToEB11DataObjectList ){
				existingMBUList.add(i.getCommaSeperatedMbu());
			}
			String existingMBU = BxUtil.getAsCSV(existingMBUList);
			return existingMBU;
		}
		/**
		 * IsMBUMappingsExistsQuery
		 * Query fetches the Service Type - Eb11 mappings
		 */

		private class IsMBUMappingsExistsQuery extends MappingSqlQuery {

			public IsMBUMappingsExistsQuery(DataSource dataSource, String query) {
				super(dataSource, query);
			}
			@Override
			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {
				
				ServiceTypeToEB11DataObject serviceTypeToEB11DataObject = new ServiceTypeToEB11DataObject();
				serviceTypeToEB11DataObject.setLobId(resultSet.getString("LOB_ID"));
				serviceTypeToEB11DataObject.setCommaSeperatedMbu(resultSet.getString("MBU_CODE"));
				return serviceTypeToEB11DataObject;
			}
		}
		/**
		 * Method inserts the MBU Codes into the MBU Assn table while create function
		 * @param vOToSave
		 */
		public void persistMBU(ServiceTypeMappingVO voToSaveAfterValidation){
			
			 List<ServiceTypeToEB11DataObject> serviceTypeCodeToEB11DataObjectList = null;
			 List<ServiceTypeMappingVO> resultList = null;
			 //Insert the new MBU Values
			 List<String> mbuList = new ArrayList<String>();
			 if(!StringUtils.isBlank(voToSaveAfterValidation.getCommaSeperatedMbu())){
					mbuList = BxUtil.convertCSVToArrayList(voToSaveAfterValidation.getCommaSeperatedMbu());
				}
			 for(String mbuName: mbuList){
				 StringBuffer insertMBU = new StringBuffer();
				 insertMBU.append("INSERT INTO BX_LOB_MBU_ASSN VALUES('"+voToSaveAfterValidation.getLobId()+
						 "', '"+mbuName+"',sysdate, '"+voToSaveAfterValidation.getLastUpdatedUser()+"')");
				 PersistMBU persistMBU = new PersistMBU(dataSource, insertMBU.toString());
					int numRowsAfffected = persistMBU.update();
					if(numRowsAfffected >= 1){
						logger.info("Number of rows affected while persisting the MBU's: " + numRowsAfffected);
						voToSaveAfterValidation.setOperationMessages(DomainConstants.SAVE_SERVICE_TYPE_ASSN_SUCCESS_MSG);
					}
			 	}
			}
		/**
		 * PersistMBUQuery
		 */
		private class PersistMBU extends SqlUpdate {

			public PersistMBU(DataSource dataSource, String sqlQuery) {
				super(dataSource, sqlQuery);
				compile();
			}
			public int update() {
				return super.update();
			}
		}
		/**
		 * Method checks if LOB is changed at the time of Update Operation.Returns the lob_sys_id which will be compared with the one from page
		 * @param String lobName
		 * @return String lobId
		 */
		public String isLOBChangedWhileUpdate(String lobName){
			StringBuffer mappingExists = new StringBuffer();
			List<ServiceTypeToEB11DataObject> serviceTypeCodeToEB11DataObjectList = null;
			List<ServiceTypeMappingVO> resultList = null;
			
			mappingExists.append(" SELECT LOB_SYS_ID LOB_ID FROM BX_LOB_MSTR WHERE upper(LOB_NAME) =upper('"+lobName+"') ");
			
			IsLOBMappingsExistsQuery isLOBMappingsExistsQuery = new IsLOBMappingsExistsQuery(
					dataSource,mappingExists.toString());
			serviceTypeCodeToEB11DataObjectList = isLOBMappingsExistsQuery.execute();
			
			String lobId = "";
			if(null != serviceTypeCodeToEB11DataObjectList && !serviceTypeCodeToEB11DataObjectList.isEmpty()){
				lobId = serviceTypeCodeToEB11DataObjectList.get(0).getLobId();
			}
			return lobId;
		}
		/**
		 * Method checks if an MBU already exists with another lob id while update flow. If so, returns true
		 * @param mbuInCSVwithSingleQuote
		 * @return comma separated existing mbu's
		 */
		public String isMBUMappingExistsWhileUpdate(String mbuInCSVwithSingleQuote, String lobid){
			StringBuffer mbuMappingExists = new StringBuffer();
			List<ServiceTypeToEB11DataObject> serviceTypeCodeToEB11DataObjectList = null;
			List<ServiceTypeMappingVO> resultList = null;
			
			mbuMappingExists.append(" SELECT DISTINCT LOB_SYS_ID LOB_ID,MBU_CODE FROM BX_LOB_MBU_ASSN WHERE MBU_CODE IN("+mbuInCSVwithSingleQuote+") ");
			mbuMappingExists.append(" AND LOB_SYS_ID NOT IN (SELECT LOB_SYS_ID FROM BX_LOB_MSTR WHERE LOB_SYS_ID = '"+lobid+"') ");
			IsMBUMappingsExistsQuery isMBUMappingsExistsQuery = new IsMBUMappingsExistsQuery(
					dataSource,mbuMappingExists.toString());
			serviceTypeCodeToEB11DataObjectList = isMBUMappingsExistsQuery.execute();
			List<String> existingMBUList = new ArrayList<String>();
			for(ServiceTypeToEB11DataObject i: serviceTypeCodeToEB11DataObjectList ){
				existingMBUList.add(i.getCommaSeperatedMbu());
			}
			String existingMBU = BxUtil.getAsCSV(existingMBUList);
			return existingMBU;
		}
		
		
		/**
		 * Method updates the LOB Name with the new LOB name from page
		 * @param vOToSave
		 */
		public void persistLOBWhileUpdate(ServiceTypeMappingVO voToSaveAfterValidation){
			
			StringBuffer updateLOB = new StringBuffer();
			updateLOB.append(" UPDATE BX_LOB_MSTR SET LOB_NAME = '"+voToSaveAfterValidation.getLineOfBusiness()+
					"', SSB_IND = '"+voToSaveAfterValidation.getSsbIndicator()+"' WHERE LOB_SYS_ID = '"+voToSaveAfterValidation.getLobId()+"'");
			PersistLOB persistLOB = new PersistLOB(dataSource, updateLOB.toString());
			int rowsAffctedWhileLobUpdate = persistLOB.update();
			if(rowsAffctedWhileLobUpdate >= 1){
				logger.info("Number of rows affected while updating LOB master table: Updated Rows:" + rowsAffctedWhileLobUpdate);
				voToSaveAfterValidation.setOperationMessages(DomainConstants.SAVE_SERVICE_TYPE_ASSN_SUCCESS_MSG);
			}
		}
		/**
		 * Method updates the LOB_MBU_ASSN table. Called while update flow.
		 * Deletes all the existing MBU's and inserts the new ones.
		 * @param vOToSave
		 */
		
		public void persistMBUWhileUpdate(ServiceTypeMappingVO voToSaveAfterValidation){
			//Delete the MBU values
			StringBuffer deleteMBU = new StringBuffer();
			deleteMBU.append(" DELETE FROM BX_LOB_MBU_ASSN WHERE LOB_SYS_ID = '"+voToSaveAfterValidation.getLobId()+"' ");
			PersistMBU persistMBUWhileDelete = new PersistMBU(dataSource, deleteMBU.toString());
			int rowsAffectedWhileDeleting = persistMBUWhileDelete.update();
			int rowsAffectedWhileInserting = 0;
			//Insert the new MBU Values
			List<String> mbuList = new ArrayList<String>();
			if(!StringUtils.isBlank(voToSaveAfterValidation.getCommaSeperatedMbu())){
				mbuList = BxUtil.convertCSVToArrayList(voToSaveAfterValidation.getCommaSeperatedMbu());
			}
			
			 for(String mbuName: mbuList){
				 StringBuffer insertMBU = new StringBuffer();
				 insertMBU.append("INSERT INTO BX_LOB_MBU_ASSN VALUES('"+voToSaveAfterValidation.getLobId()+"', '"+mbuName+"',sysdate, '"+voToSaveAfterValidation.getLastUpdatedUser()+"')");
				 PersistMBU persistMBUWhileInsert = new PersistMBU(dataSource, insertMBU.toString());
				 rowsAffectedWhileInserting = persistMBUWhileInsert.update();
			 }
			 if(rowsAffectedWhileDeleting >= 1 && rowsAffectedWhileInserting >= 1){
				 logger.info("Number of rows affected while updating MBU association table: Deleted Rows:" + rowsAffectedWhileDeleting+", Inserted Rows:"+rowsAffectedWhileInserting);
				 voToSaveAfterValidation.setOperationMessages(DomainConstants.SAVE_SERVICE_TYPE_ASSN_SUCCESS_MSG);
			 }
		}
		
		/**
		 * Method to insert an entry in the audit trail after delete and create action.
		 * @param ServiceTypeMappingVO
		 */
		private void updateServiceTypeCodeToEB11MappingAuditTrail(ServiceTypeMappingVO serviceTypeMappingVO, String systemComments) {
			
			PersistAuditTrial persistAuditTrial = new PersistAuditTrial(
					dataSource, getServicetypeMappingsAuditTrialInsertQuery());
			int rowsAfffectedAuditTrialInsert = persistAuditTrial.update(serviceTypeMappingVO,systemComments);
			logger.info("Number of rows affected while inserting into Audit Trial table: " + rowsAfffectedAuditTrialInsert);
			
		}
		
		/**
		 * PersistAuditTrial Query
		 */
		private class PersistAuditTrial extends SqlUpdate {

			public PersistAuditTrial(DataSource dataSource, String sqlQuery) {
				super(dataSource, sqlQuery);
				declareParameter(new SqlParameter("LOB_NAME", Types.VARCHAR));
				declareParameter(new SqlParameter("SYS_CMNTS", Types.CLOB));
				declareParameter(new SqlParameter("USR_CMNTS", Types.VARCHAR));
				declareParameter(new SqlParameter("CREATD_USER_ID", Types.VARCHAR));
				compile();
			}
			public int update(ServiceTypeMappingVO serviceTypeMappingVO, String systemComments) {
				Object[] objs = new Object[] {
						serviceTypeMappingVO.getLineOfBusiness(),
						systemComments,
						serviceTypeMappingVO.getUserComments(),
						serviceTypeMappingVO.getLastUpdatedUser()
				};
				return super.update(objs);
			}
		}
		/**
		 * Method to fetch data from the audit trial corresponding to lob name.
		 * @param ServiceTypeMappingVO
		 */
		public List<ServiceTypeToEB11DataObject> viewHistoryOfServiceTypeCodeToEB11Mapping(ServiceTypeMappingVO viewHistoryVO){

			FetchServiceTypeMappingHistoryQuery fetchServiceTypeMappingHistoryQuery = new FetchServiceTypeMappingHistoryQuery(
					dataSource, getServiceTypeMappingHistoryFetchQuery());
			List<ServiceTypeToEB11DataObject> auditTrailHistory = fetchServiceTypeMappingHistoryQuery.execute(viewHistoryVO.getLineOfBusiness());
			return auditTrailHistory;
		
		}
		
		private static final class FetchServiceTypeMappingHistoryQuery extends MappingSqlQuery {
			
			private FetchServiceTypeMappingHistoryQuery(DataSource dataSource, String query) {
				super(dataSource, query);
				declareParameter(new SqlParameter(Types.VARCHAR));
			}		

			public Object mapRow(ResultSet resultSet, int rowCount)
					throws SQLException {		
				
				ServiceTypeToEB11DataObject mappingVOHistory = new ServiceTypeToEB11DataObject();
				mappingVOHistory.setLineOfBusiness(resultSet.getString(1));
				String updatedTimeStamp = BxUtil.getFormattedDate(resultSet.getTimestamp(2));
				mappingVOHistory.setLastUpdatedDate(updatedTimeStamp);
				mappingVOHistory.setLastUpdatedUser(resultSet.getString(3));
				mappingVOHistory.setSystemComments(resultSet.getString(4));
				mappingVOHistory.setUserComments(resultSet.getString(5));
				return mappingVOHistory;
			}
		}
		/***********************************BXNI November Release End****************************************************/
}
