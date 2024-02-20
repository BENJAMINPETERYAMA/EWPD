/*
 * <ReferenceDataFacadeImpl.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.referencedata.application;

import java.util.List;
import java.util.Map;

import com.wellpoint.ets.bx.mapping.domain.vo.ReferenceDataResult;
import com.wellpoint.ets.ebx.referencedata.domain.service.ReferenceDataService;
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
 * @author UST-GLOBAL Implementation for Reference data Facade Layer
 * 
 */
public class ReferenceDataFacadeImpl implements ReferenceDataFacade {

	/**
	 * Comment for <code>referenceDataService</code> Reference for service.
	 */
	private ReferenceDataService referenceDataService;

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.application.ReferenceDataFacade#fetchErrorCodes(java.lang.String)
	 * @param matchValue
	 * @return List of error codes.
	 */
	public List fetchErrorCodes(String matchValue) {
		return referenceDataService.fetchErrorCodes(matchValue);
	}

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.application.ReferenceDataFacade#fetchExclusions(java.lang.String)
	 * @param roleCode
	 * @return ErrorCodeVO. Fetch all exclusions for an error code.
	 */
	public ErrorCodeVO fetchExclusions(String errorCode) {
		return referenceDataService.fetchExclusions(errorCode);
	}

	public int getTotalCountForExclusionForValidation(
			ErrorExclusionVO errorExclusionVO) {
		return referenceDataService
				.getTotalCountForExclusionForValidation(errorExclusionVO);
	}

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.application.ReferenceDataFacade#saveExclusion(com.wellpoint.ets.ebx.referencedata.vo.ErrorCodeVO)
	 * @param errorCodeVO
	 * @return
	 */
	public ReferenceDataResult saveExclusion(ErrorCodeVO errorCodeVO) {
		return referenceDataService.saveExclusion(errorCodeVO);
	}

	public ErrorCodeVO fetchAuditTrail(String errorCode) {
		return referenceDataService.fetchAuditTrail(errorCode);
	}

	/**
	 * @param errorCode
	 * @return
	 */
	public boolean deleteAllExclusions(ErrorCodeVO errorCodeVO) {
		return referenceDataService.deleteAllExclusions(errorCodeVO);
	}

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.application.ReferenceDataFacade#deleteExclusion(com.wellpoint.ets.ebx.referencedata.vo.ErrorCodeVO)
	 * @param errorCodeVO
	 * @return
	 */
	public boolean deleteExclusion(ErrorCodeVO errorCodeVO) {
		return referenceDataService.deleteExclusion(errorCodeVO);
	}

	/**
	 * @return referenceDataService
	 */
	public ReferenceDataService getReferenceDataService() {
		return referenceDataService;
	}

	/**
	 * @param referenceDataService
	 *            ReferenceDataService
	 */
	public void setReferenceDataService(
			ReferenceDataService referenceDataService) {
		this.referenceDataService = referenceDataService;
	}

	/**
	 * @param CategoryServiceTypeMappingVO
	 * @param List
	 * @return return List.
	 */
	public List fetchCategoryCodeMappingResult(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO) {
		return referenceDataService
				.fetchCategoryCodeMappingResult(categoryServiceTypeMappingVO);
	}

	/**
	 * @param CategoryServiceTypeMappingVO
	 * @param CategoryServiceTypeMappingVO
	 * @param List
	 * @return return List.
	 */
	public List updateCategoryCodeMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO,
			CategoryServiceTypeMappingVO selectVO, List mapping) {
		return referenceDataService.updateCategoryCodeMapping(
				categoryServiceTypeMappingVO, selectVO, mapping);
	}

	/**
	 * @param CategoryServiceTypeMappingVO
	 * @param CategoryServiceTypeMappingVO
	 * @return return List.
	 */
	public List deleteCategoryCodeMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO,
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVOselect) {
		return referenceDataService.deleteCategoryCodeMapping(
				categoryServiceTypeMappingVO,
				categoryServiceTypeMappingVOselect);
	}

	/**
	 * @param CategoryServiceTypeMappingVO
	 * @return return List.
	 */
	public List fetchCategoryCodeVariableMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO) {
		return referenceDataService
				.fetchCategoryCodeVariableMapping(categoryServiceTypeMappingVO);
	}

	/**
	 * @param CategoryServiceTypeMappingVO
	 * @param CategoryServiceTypeMappingVO
	 * @param List
	 * @return return List.
	 */
	public List createCategoryCodeMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO,
			CategoryServiceTypeMappingVO selectVO, List mapping) {
		return referenceDataService.createCategoryCodeMapping(
				categoryServiceTypeMappingVO, selectVO, mapping);
	}

	/**
	 * @param hippacode
	 * @return return Map.
	 */
	public Map getEB03Values(String hippacode) {
		return referenceDataService.getEB03Values(hippacode);
	}

	/**
	 * Fetch all the category codes with and without mappings in both LG and ISG
	 * - T52
	 * 
	 * @return List
	 */
	public List fetchAllCategoryCodesMapping() {
		return referenceDataService.fetchAllCategoryCodes();
	}
	/* Reference Data Values -  START */
	/**
	 * @param String
	 * @return List
	 * Fetch all the reference data values matching the match value
	 * 
	 */
	public List fetchCatalogNames(String matchValue, boolean isHPNRef) {
		return referenceDataService.fetchCatalogNames(matchValue, isHPNRef);
	}
	
	/**
	 * @param catalogName
	 * @return ReferenceDataValueVO
	 * Fetch all the data values corresponding to the catalog name
	 */
	public List fetchItems(String catalogName) {
		return referenceDataService.fetchItems(catalogName);
	}

	/**
	 * @param referenceDataValueVO
	 * @return String
	 * Add the new data value to the Catalog Name
	 */
	public String addItem(ReferenceDataValueVO referenceDataValueVO) {
		return referenceDataService.addItem(referenceDataValueVO);
	}

	/**
	 * @param referenceDataValueVO
	 * @return String
	 * Update the data value of the Catalog Name
	 */
	public String updateItem(ReferenceDataValueVO referenceDataValueVO) {
		return referenceDataService.updateItem(referenceDataValueVO);
	}
	
	/**
	 * @param referenceDataValueVO
	 * @return String
	 * Delete the data value of the Catalog Name
	 */
	public String deleteItem(ReferenceDataValueVO referenceDataValueVO) {
		return referenceDataService.deleteItem(referenceDataValueVO);
	}
	/**
	 * @param referenceDataValueVO
	 * @return ReferenceDataValueVO
	 * Checks if data value is mapped to SPS/VAriable/Header Rule
	 */
	public ReferenceDataValueVO checkItemMappings(ReferenceDataValueVO referenceDataValueVO) {
		return referenceDataService.checkItemMappings(referenceDataValueVO);
	}
	
	/**
	 * @return List
	 * Fetch the deleted data value details of the Catalog 
	 */
	public List fetchHistoryOfCatalog(String catalogName) {
		return referenceDataService.fetchHistoryOfCatalog(catalogName);
	}
	
	/**
	 * @param catalogName
	 * @return List
	 * Fetch the min and max lengths of the Catalog
	 */
	public ReferenceDataValueVO getCatalogMinMaxLengths(String catalogName) {
		return referenceDataService.getCatalogMinMaxLengths(catalogName);
	}
	
	/* Reference Data Values -  END */

/*************************Manage Header Rule EB03 Associations April 2012 Release SSCR14181 START ******************************/
	/**
	 * @param catalogName
	 * @return List
	 * Returns the search result, which contains the header rule mappings to EB03
	 */
	public List fetchHeaderRuleToEB03Mapping(HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO){
		return referenceDataService.fetchHeaderRuleToEB03Mapping(headerRuleToEB03MappingVO);
	}
	/**
	 * @param HeaderRuleToEB03MappingVO
	 * @return List
	 * Returns all the header rule id, header rule descriptions corresponding to an EB03
	 */
	public List fetchHeaderRuleDetails(HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO){
		return referenceDataService.fetchHeaderRuleDetails(headerRuleToEB03MappingVO);
	}

	public List deleteHeaderRuleToEB03Mapping(HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO,
			HeaderRuleToEB03MappingVO headerRuleToEB03MappingVOSearchCriteria){
		return referenceDataService.deleteHeaderRuleToEB03Mapping(headerRuleToEB03MappingVO,headerRuleToEB03MappingVOSearchCriteria);
	}
	public List possibleHeaderRuleValues(String CSVwithSingleQuoteHeaderRulesFromPage){
		return referenceDataService.possibleHeaderRuleValues(CSVwithSingleQuoteHeaderRulesFromPage);
	}
	public List saveHeaderRuleToEB03Mapping (HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO,
			HeaderRuleToEB03MappingVO headerRuleToEB03MappingVOForSelect,List headerRuleListFromPage){
		return referenceDataService.saveHeaderRuleToEB03Mapping(headerRuleToEB03MappingVO,
				headerRuleToEB03MappingVOForSelect,headerRuleListFromPage);
	}
	public List viewHistoryOfHeaderRuleToEB03Mapping(HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO){
		return referenceDataService.viewHistoryOfHeaderRuleToEB03Mapping(headerRuleToEB03MappingVO);
	}
		/**
		 * method to fetch the header rule for auto complete.
		 * @param inputString - search string for date type auto complete
		 * @return List - List of header rules corresponding to the search string.
		 */
	public List fetchHeaderRuleForAutoComplete(String inputString, boolean autoCompleteFlag){
		return referenceDataService.fetchHeaderRuleForAutoComplete(inputString,autoCompleteFlag);
	}
/*************************Manage Header Rule EB03 Associations April 2012 Release SSCR14181 END ******************************/
	/* EB01- DataType Association -  START */
	/**
	 * method to fetch the data type for auto complete.
	 * @param inputString - search string for date type auto complete
	 * @return List - List of data types corresponding to the search string.
	 */
	public List fetchDataTypeForAutoComplete(String inputString,boolean autoCompleteFlag) {
		return referenceDataService.fetchDataTypeForAutoComplete(inputString,autoCompleteFlag);
	}
	
	/**
	 * method to fetch the EB01 - data type association values.
	 * @param mappingVO - contains search parameters, either eb01 value or data type or both.
	 * @return List of DataTypeToEB01MappingVO
	 */
	public List fetchDataTypeToEB01Mapping(DataTypeToEB01MappingVO mappingVO) {
		return referenceDataService.fetchDataTypeToEB01Mapping(mappingVO);
		
	}
	
	/**
	 * method to delete the EB01 - data type association values.
	 * @param eb01MappingVODelete - contains EB01 value whose association to be delete and the user comments for audit trail.
	 * @param eb01MappingToFetch - contains search criteria (EB01 or data type or both) after delete action.
	 * @return List - List of EB01 - data type association values after delete action.
	 */
	public List deleteDatatypeToEB01Mapping(DataTypeToEB01MappingVO eb01MappingVODelete, DataTypeToEB01MappingVO eb01MappingToFetch) {
		return referenceDataService.deleteDatatypeToEB01Mapping(eb01MappingVODelete, eb01MappingToFetch);
	}
	
	/**
	 *  Method to edit the EB01 to Data Type association.
	 * @param eb01MappingVOEdit
	 * @return List - contains EB01 - data type association values for edit action.
	 */
	public List editDataTypeToEB01Mapping(DataTypeToEB01MappingVO eb01MappingVOEdit) {
		return referenceDataService.editDataTypeToEB01Mapping(eb01MappingVOEdit);
	}
	
	/**
	 * Method to save the changes in EB01 to Data Type association.
	 * @param eb01MappingVOToUpdate
	 * @param eb01MappingVOToFetch
	 * @param dataTypeListToInsert
	 * @return List -  contains EB01 - data type association values after save.
	 */
	public List saveDatatypeToEB01Mapping(DataTypeToEB01MappingVO eb01MappingVOToUpdate,DataTypeToEB01MappingVO eb01MappingVOToFetch,
			List dataTypeListToInsert) {
		return referenceDataService.saveDatatypeToEB01Mapping(eb01MappingVOToUpdate, eb01MappingVOToFetch, dataTypeListToInsert);
	}
	/**
	 * Method to show the history of save and delete actions.
	 * @param eb01MappingVOLocate
	 * @return List - Contains audit trail values corresponding the EB01 value.
	 */
	public List viewHistoryOfDatatypeToEB01Mapping(DataTypeToEB01MappingVO eb01MappingVOLocate) {
		return referenceDataService.viewHistoryOfDatatypeToEB01Mapping(eb01MappingVOLocate);
	}
	/* EB01- DataType Association -  END */
	// BXNI June Release Changes - Start
	/**
	 * Method to fetch the standard message by doing a like search.
	 * @param standardMessageToFetch
	 * @return List of standard messages.
	 */
	public List<StandardMessageVO> fetchStandardMessage(StandardMessageVO standardMessageToFetch) {
		return referenceDataService.fetchStandardMessage(standardMessageToFetch);
		
	}
	
	/**
	 * Method to delete the standard message.
	 * @param standardMessageToFetch
	 * @param standardMessageToDelete
	 * @return return the list of standard messages..
	 */
	public List<StandardMessageVO> deleteStandardMessage(StandardMessageVO standardMessageToFetch, StandardMessageVO standardMessageToDelete) {
		return referenceDataService.deleteStandardMessage(standardMessageToFetch, standardMessageToDelete);
	}
	
	/**
	 * Method to save the changes in the existing standard message and create a new standard message.
	 * @param standardMessageToFetch
	 * @param standardMessageToSave
	 * @param create
	 * @return List of standard messages,
	 */
	public List<StandardMessageVO> saveStandardMessage(StandardMessageVO standardMessageToFetch, StandardMessageVO standardMessageToSave, boolean create) {
		return referenceDataService.saveStandardMessage(standardMessageToFetch, standardMessageToSave, create);
	}
	
	/**
	 * Method to fetch the standard message history.
	 * @return List of audit trail history.
	 */
	public List<StandardMessageVO> viewHistoryOfStandardMessage() {
		return referenceDataService.viewHistoryOfStandardMessage();
	}

	/**
	 * Method to edit the standard message.
	 * @param stdMsgIdEdit
	 * @return
	 */
	@Override
	public String editStandardMessage(String stdMsgIdEdit) {
		return referenceDataService.editStandardMessage(stdMsgIdEdit);
	}
	// BXNI June Release Changes - End
	/***********************************BXNI November Release Start****************************************************/
	/**
	 * Method to fetch the Service type Code mappings by doing a like search based on either LOB/State or MBU.
	 * @param ServiceTypeMappingVO
	 * @return result List of ServiceTypeMappingVO object.
	 */
	public List<ServiceTypeMappingVO> fetchServiceTypeCodeToEB11Mapping (ServiceTypeMappingVO VOToFetch){
		return referenceDataService.fetchServiceTypeCodeToEB11Mapping(VOToFetch);
	}
	/**
	 * Method to view the Service type Code-EB11 mappings associated with an LOB/State
	 * @param ServiceTypeMappingVO
	 * @return result List of ServiceTypeMappingVO object.
	 */
	public List<ServiceTypeMappingVO> viewServiceTypeCodeToEB11Mapping (ServiceTypeMappingVO VOToView){
		return referenceDataService.viewServiceTypeCodeToEB11Mapping(VOToView);
	}
	public List<ServiceTypeMappingVO> deleteServiceTypeCodeToEB11Mapping (ServiceTypeMappingVO vOToDelete, ServiceTypeMappingVO vOToFetch){
		return referenceDataService.deleteServiceTypeCodeToEB11Mapping(vOToDelete,vOToFetch);
	}
	public List<ServiceTypeMappingVO> editServiceTypeCodeToEB11Mapping(ServiceTypeMappingVO VOToEdit){
		return null;
	}
	public List<ServiceTypeMappingVO> saveServiceTypeCodeToEB11Mapping (ServiceTypeMappingVO VOToSave, ServiceTypeMappingVO VOToFetch){
		return referenceDataService.saveServiceTypeCodeToEB11Mapping(VOToSave,VOToFetch);
	}
	public List<ServiceTypeToEB11DataObject> viewHistoryOfServiceTypeCodeToEB11Mapping(ServiceTypeMappingVO viewHistoryVO){
		return referenceDataService.viewHistoryOfServiceTypeCodeToEB11Mapping(viewHistoryVO);
	}
	
	
	/***********************************BXNI November Release End****************************************************/
}
