/*
 * <ReferenceDataService.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.referencedata.domain.service;

import java.util.List;
import java.util.Map;

import com.wellpoint.ets.bx.mapping.domain.vo.ReferenceDataResult;
import com.wellpoint.ets.ebx.referencedata.vo.CategoryServiceTypeMappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.DataTypeToEB01MappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.ErrorCodeVO;
import com.wellpoint.ets.ebx.referencedata.vo.ErrorExclusionVO;
import com.wellpoint.ets.ebx.referencedata.vo.HeaderRuleToEB03MappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.ReferenceDataValueVO;
import com.wellpoint.ets.ebx.referencedata.vo.ServiceTypeMappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.ServiceTypeToEB11DataObject;
import com.wellpoint.ets.ebx.referencedata.vo.StandardMessageVO;

/**
 * @author UST-GLOBAL Interface for Service Layer
 * 
 */
public interface ReferenceDataService {
	/**
	 * @param matchErrorCode
	 * @return Fetch error codes.
	 */
	public List fetchErrorCodes(String matchValue);

	/**
	 * @param roleCode
	 * @return Fetch exclusions.
	 */
	public ErrorCodeVO fetchExclusions(String errorCode);

	/**
	 * @param errorCodeVO
	 * @return Save exclusion.
	 */
	public ReferenceDataResult saveExclusion(ErrorCodeVO errorCodeVO);

	/**
	 * @return List Fecth all exclusions
	 */
	public List fetchAllExclusions();

	/**
	 * @param errorCode
	 * @return Fetch audit trail
	 */
	public ErrorCodeVO fetchAuditTrail(String errorCode);

	/**
	 * @param errorCodeVO
	 * @return Delete exclusion.
	 */
	public boolean deleteExclusion(ErrorCodeVO errorCodeVO);

	/**
	 * @param errorCode
	 * @return Delete all exclusions.
	 */
	public boolean deleteAllExclusions(ErrorCodeVO errorCodeVO);

	/**
	 * @param errorExclusionVO
	 * @return Validate against DB.
	 */
	public int getTotalCountForExclusionForValidation(
			ErrorExclusionVO errorExclusionVO);

	/**
	 * @param categoryServiceTypeMappingVO
	 * @return List
	 */
	public List fetchCategoryCodeMappingResult(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO);

	/**
	 * @param categoryServiceTypeMappingVO
	 * @return List
	 */
	public List updateCategoryCodeMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO,
			CategoryServiceTypeMappingVO selectVO, List mapping);

	/**
	 * @param categoryServiceTypeMappingVO
	 * @param categoryServiceTypeMappingVO
	 * @return List
	 */
	public List deleteCategoryCodeMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO,
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVOselect);

	/**
	 * @param categoryServiceTypeMappingVO
	 * @return List
	 */
	public List fetchCategoryCodeVariableMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO);

	/**
	 * @param categoryServiceTypeMappingVO
	 * @param categoryServiceTypeMappingVO
	 * @param List
	 * @return List
	 */
	public List createCategoryCodeMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO,
			CategoryServiceTypeMappingVO selectVO, List mapping);

	/**
	 * @param hippaCode
	 * @return Map
	 */
	public Map getEB03Values(String hippaCode);

	/**
	 * Fetch all the category codes with and without mappings in both LG and ISG
	 * - T52
	 * 
	 * @return List
	 */
	public List fetchAllCategoryCodes();

	/**
	 * 
	 * @return
	 */
	public List getMinorHeadingCatagoryCodeMapping();
	/* Reference Data Values -  START */
	/**
	 * @param String
	 * @return List
	 * Fetch all the reference data values matching the match value
	 * 
	 */
	public List fetchCatalogNames(String matchValue, boolean isHPNRef);
	
	/**
	 * @param catalogName
	 * @return ReferenceDataValueVO
	 * Fetch all the data values corresponding to the catalog name
	 */
	public List fetchItems(String catalogName);
	
	/**
	 * @param referenceDataValueVO
	 * @return String
	 * Add the new data value to the Catalog Name
	 */
	public String addItem(ReferenceDataValueVO referenceDataValueVO);
	
	/**
	 * @param referenceDataValueVO
	 * @return String
	 * Update the data value of the Catalog Name
	 */
	public String updateItem(ReferenceDataValueVO referenceDataValueVO);
	
	/**
	 * @param referenceDataValueVO
	 * @return String
	 * Delete the data value of the Catalog Name
	 */
	public String deleteItem(ReferenceDataValueVO referenceDataValueVO);
	
	/**
	 * @param referenceDataValueVO
	 * @return ReferenceDataValueVO
	 * Checks if data value is mapped to SPS/VAriable/Header Rule
	 */
	public ReferenceDataValueVO checkItemMappings(ReferenceDataValueVO referenceDataValueVO);
	
	
	/**
	 * @param catalogName 
	 * @return List
	 * Fetch the deleted data value details of the Catalog 
	 */
	public List fetchHistoryOfCatalog(String catalogName);
	
	/**
	 * @param catalogName
	 * @return List
	 * Fetch the min and max lengths of the Catalog
	 */
	public ReferenceDataValueVO getCatalogMinMaxLengths(String catalogName);
	
	/* Reference Data Values -  END */

/*************************Manage Header Rule EB03 Associations April 2012 Release SSCR14181 START ******************************/	
	/**
	 * @param catalogName
	 * @return List
	 * Returns the search result, which contains the header rule mappings to EB03
	 */
	public List fetchHeaderRuleToEB03Mapping(HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO);
	/**
	 * @param HeaderRuleToEB03MappingVO
	 * @return List
	 * Returns all the header rule id, header rule descriptions corresponding to an EB03
	 */
	public List fetchHeaderRuleDetails(HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO);
	/**
	 * @param catalogName
	 * @return List
	 * Returns the search result, which contains the header rule mappings to EB03
	 */
	public List deleteHeaderRuleToEB03Mapping(HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO,
			HeaderRuleToEB03MappingVO headerRuleToEB03MappingVOSearchCriteria);
	
	public List possibleHeaderRuleValues(String CSVwithSingleQuoteHeaderRulesFromPage);
	
	public List saveHeaderRuleToEB03Mapping (HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO,
			HeaderRuleToEB03MappingVO headerRuleToEB03MappingVOForSelect,List headerRuleListFromPage);
	
	public List viewHistoryOfHeaderRuleToEB03Mapping(HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO);
	/**
	 * method to fetch the header rule for auto complete.
	 * @param inputString - search string for date type auto complete
	 * @return List - List of header rules corresponding to the search string.
	 */
public List fetchHeaderRuleForAutoComplete(String inputString, boolean autoCompleteFlag);
/*************************Manage Header Rule EB03 Associations April 2012 Release SSCR14181 END ******************************/	
	
	/* EB01- DataType Association -  START */
	/**
	 * method to fetch the data type for auto complete.
	 * @param inputString - search string for date type auto complete
	 * @return List - List of data types corresponding to the search string.
	 */
	public List fetchDataTypeForAutoComplete(String inputString,boolean autoCompleteFlag);
	
	/**
	 * method to fetch the EB01 - data type association values.
	 * @param mappingVO - contains search parameters, either eb01 value or data type or both.
	 * @return List of DataTypeToEB01MappingVO
	 */
	public List fetchDataTypeToEB01Mapping(DataTypeToEB01MappingVO mappingVO);
	
	/**
	 * method to delete the EB01 - data type association values.
	 * @param eb01MappingVODelete - contains EB01 value whose association to be delete and the user comments for audit trail.
	 * @param eb01MappingToFetch - contains search criteria (EB01 or data type or both) after delete action.
	 * @return List - List of EB01 - data type association values after delete action.
	 */
	public List deleteDatatypeToEB01Mapping(DataTypeToEB01MappingVO eb01MappingVODelete, DataTypeToEB01MappingVO eb01MappingToFetch);
	
	/**
	 *  Method to edit the EB01 to Data Type association.
	 * @param eb01MappingVOEdit
	 * @return List - contains EB01 - data type association values for edit action.
	 */
	public List editDataTypeToEB01Mapping(DataTypeToEB01MappingVO eb01MappingVOEdit);
	
	
	/**
	 * Method to save the changes in EB01 to Data Type association.
	 * @param eb01MappingVOToUpdate
	 * @param eb01MappingVOToFetch
	 * @param dataTypeListToInsert
	 * @return List -  contains EB01 - data type association values after save.
	 */
	public List saveDatatypeToEB01Mapping(DataTypeToEB01MappingVO eb01MappingVOToUpdate,DataTypeToEB01MappingVO eb01MappingVOToFetch,
			List dataTypeListToInsert);
	
	/**
	 * Method to show the history of save and delete actions.
	 * @param eb01MappingVOLocate
	 * @return List - Contains audit trail values corresponding the EB01 value.
	 */
	public List viewHistoryOfDatatypeToEB01Mapping(DataTypeToEB01MappingVO eb01MappingVOLocate);
	/* EB01- DataType Association -  END */
	
	// BXNI June Release Changes - Start
	/**
	 * Method to fetch the standard message by doing a like search.
	 * @param standardMessageToFetch
	 * @return List of standard messages.
	 */
	public List<StandardMessageVO> fetchStandardMessage(StandardMessageVO standardMessageToFetch);
	
	/**
	 * Method to delete the standard message.
	 * @param standardMessageToFetch
	 * @param standardMessageToDelete
	 * @return return the list of standard messages..
	 */
	public List<StandardMessageVO> deleteStandardMessage(StandardMessageVO standardMessageToFetch, StandardMessageVO standardMessageToDelete);
	
	/**
	 * Method to save the changes in the existing standard message and create a new standard message.
	 * @param standardMessageToFetch
	 * @param standardMessageToSave
	 * @param create
	 * @return List of standard messages,
	 */
	public List<StandardMessageVO> saveStandardMessage(StandardMessageVO standardMessageToFetch, StandardMessageVO standardMessageToSave, boolean create);
	
	/**
	 * Method to fetch the standard message history.
	 * @return List of audit trail history.
	 */
	public List<StandardMessageVO> viewHistoryOfStandardMessage();
	
	/**
	 * Method to edit the standard message.
	 * @param stdMsgIdEdit
	 * @return
	 */
	public String editStandardMessage(String stdMsgIdEdit);
	// BXNI June Release Changes - End
	/***********************************BXNI November Release Start****************************************************/
	/**
	 * Method to fetch the Service type Code mappings by doing a like search based on either LOB/State or MBU.
	 * @param ServiceTypeMappingVO
	 * @return result List of ServiceTypeMappingVO object.
	 */
	public List<ServiceTypeMappingVO> fetchServiceTypeCodeToEB11Mapping (ServiceTypeMappingVO VOToFetch);
	/**
	 * Method to view the Service type Code-EB11 mappings associated with an LOB/State
	 * @param ServiceTypeMappingVO
	 * @return result List of ServiceTypeMappingVO object.
	 */
	public List<ServiceTypeMappingVO> viewServiceTypeCodeToEB11Mapping (ServiceTypeMappingVO vOToView);
	public List<ServiceTypeMappingVO> deleteServiceTypeCodeToEB11Mapping (ServiceTypeMappingVO vOToDelete, ServiceTypeMappingVO vOToFetch);
	public List<ServiceTypeMappingVO> editServiceTypeCodeToEB11Mapping(ServiceTypeMappingVO vOToEdit);
	public List<ServiceTypeMappingVO> saveServiceTypeCodeToEB11Mapping (ServiceTypeMappingVO vOToSave, ServiceTypeMappingVO vOToFetch);
	public List<ServiceTypeToEB11DataObject> viewHistoryOfServiceTypeCodeToEB11Mapping(ServiceTypeMappingVO viewHistoryVO);
	
	//DF
	/***********************************BXNI November Release End****************************************************/
}
