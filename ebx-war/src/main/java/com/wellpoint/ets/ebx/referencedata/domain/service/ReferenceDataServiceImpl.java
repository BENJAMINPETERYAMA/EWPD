/*
 * <ReferenceDataServiceImpl.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.referencedata.domain.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.ReferenceDataResult;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.ebx.referencedata.repository.ReferenceDataRepository;
import com.wellpoint.ets.ebx.referencedata.vo.CategoryServiceTypeMappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.DataTypeToEB01MappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.ErrorCodeVO;
import com.wellpoint.ets.ebx.referencedata.vo.ErrorExclusionVO;
import com.wellpoint.ets.ebx.referencedata.vo.HeaderRuleToEB03MappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.ReferenceDataValueVO;
import com.wellpoint.ets.ebx.referencedata.vo.ServiceTypeCodeToEB11VO;
import com.wellpoint.ets.ebx.referencedata.vo.ServiceTypeMappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.ServiceTypeToEB11DataObject;
import com.wellpoint.ets.ebx.referencedata.vo.StandardMessageVO;

/**
 * @author UST Global - www.ust-global.com <br />
 * @version $Id: $
 */
public class ReferenceDataServiceImpl implements ReferenceDataService {

	/**
	 * Comment for <code>referenceDataRepository</code> Reference for
	 * repository.
	 */
	private ReferenceDataRepository referenceDataRepository;

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.domain.service.ReferenceDataService#fetchErrorCodes(java.lang.String)
	 * @param matchErrorCode
	 * @return list of error codes.
	 */
	public List fetchErrorCodes(String matchValue) {
		return referenceDataRepository.fetchErrorCodes(matchValue);
	}

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.domain.service.ReferenceDataService#fetchExclusions(java.lang.String)
	 * @param roleCode
	 * @return Error Code VO.
	 */
	public ErrorCodeVO fetchExclusions(String errorCode) {
		return referenceDataRepository.fetchExclusions(errorCode);
	}

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.domain.service.ReferenceDataService#saveExclusion(com.wellpoint.ets.ebx.referencedata.vo.ErrorCodeVO)
	 * @param errorCodeVO
	 * @return Save exclusion.
	 */
	public ReferenceDataResult saveExclusion(ErrorCodeVO errorCodeVO) {
		/*
		 * Map validationResult
		 * Key (String) - Item name. eg : CONTRACT
		 * Value (List) - List of Exclusion Items. eg: List of Contracts
		 */
		Map validationResult = referenceDataRepository.validateExclusionList(errorCodeVO.getExclusionList());
		ReferenceDataResult referenceDataResult = new ReferenceDataResult();
		List warningMessage = new ArrayList();
		boolean isWarningExists=false;
		
		Set keySet = validationResult.keySet();
		Iterator keyIterator = keySet.iterator();
		while (keyIterator.hasNext()) {
			String exclusionCriteria = (String)keyIterator.next();
			List invalidExclusionValues = (List)validationResult.get(exclusionCriteria);
			// Product Line is not validated .
			if (null != invalidExclusionValues && !invalidExclusionValues.isEmpty() 
					&& !exclusionCriteria.equalsIgnoreCase("PRODUCTLINE")) {
				warningMessage.add(createWarningMessage(exclusionCriteria, invalidExclusionValues));
				isWarningExists=true;
			}
		}
		if(isWarningExists){
			warningMessage.add("Please edit or delete if required.");
		}
		referenceDataResult.setWarningMsgsList(warningMessage);
		boolean status = referenceDataRepository.saveExclusion(errorCodeVO);
		referenceDataResult.setStatus(status);
		
		return referenceDataResult;
	}

	private String createWarningMessage(String exclusionCriteria, List invalidExclusionValues) {
		if (null != exclusionCriteria && exclusionCriteria.equals("HEADERRULE")) {
			exclusionCriteria = "HEADER RULE";
		}
		if (null != exclusionCriteria && exclusionCriteria.equals("PRODUCT")) {
			exclusionCriteria = "PRODUCT CODE";
		}
		return "Invalid "+exclusionCriteria+" ID : " + BxUtil.getAsCSV(invalidExclusionValues);
	}

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.domain.service.ReferenceDataService#fetchAllExclusions()
	 * @return List Fetch all exclusions.
	 */
	public List fetchAllExclusions() {
		return referenceDataRepository.fetchAllExclusions();
	}

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.domain.service.ReferenceDataService#fetchAuditTrail(java.lang.String)
	 * @param errorCode
	 * @return Fetch audit trail.
	 */
	public ErrorCodeVO fetchAuditTrail(String errorCode) {
		return referenceDataRepository.fetchAuditTrail(errorCode);
	}

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.domain.service.ReferenceDataService#deleteErrorCode(java.lang.String)
	 * @param errorCode
	 * @return Delete all exclusions.
	 */
	public boolean deleteAllExclusions(ErrorCodeVO errorCodeVO) {
		return referenceDataRepository.deleteAllExclusions(errorCodeVO);
	}

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.domain.service.ReferenceDataService#deleteExclusion(com.wellpoint.ets.ebx.referencedata.vo.ErrorCodeVO)
	 * @param errorCodeVO
	 * @return Delete exclusion.
	 */
	public boolean deleteExclusion(ErrorCodeVO errorCodeVO) {
		return referenceDataRepository.deleteExclusion(errorCodeVO);
	}

	/**
	 * @see com.wellpoint.ets.ebx.referencedata.domain.service.ReferenceDataService#getTotalCountForExclusionForValidation(com.wellpoint.ets.ebx.referencedata.vo.ErrorExclusionVO)
	 * @param errorExclusionVO
	 * @return Validate for total error count and any further DB validations can
	 *         go here.
	 */
	public int getTotalCountForExclusionForValidation(
			ErrorExclusionVO errorExclusionVO) {
		return referenceDataRepository
				.getTotalCountForExclusionForValidation(errorExclusionVO);
	}

	/**
	 * @return ReferenceDataRepository.
	 */
	public ReferenceDataRepository getReferenceDataRepository() {
		return referenceDataRepository;
	}

	/**
	 * @param referenceDataRepository
	 *            ReferenceDataRepository.
	 */
	public void setReferenceDataRepository(
			ReferenceDataRepository referenceDataRepository) {
		this.referenceDataRepository = referenceDataRepository;
	}

	// tenison
	/**
	 * @param CategoryServiceTypeMappingVO
	 *            return list .
	 */
	public List fetchCategoryCodeMappingResult(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO) {

		return referenceDataRepository
				.fetchCategoryCodeServiceTypeMapping(categoryServiceTypeMappingVO);

	}

	/**
	 * @param CategoryServiceTypeMappingVO
	 * @param CategoryServiceTypeMappingVO
	 *            return list .
	 */
	public List updateCategoryCodeMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO,
			CategoryServiceTypeMappingVO selectVO, List mapping) {
		return referenceDataRepository.updateCategoryCodeMapping(
				categoryServiceTypeMappingVO, selectVO, mapping);
	}

	/**
	 * @param CategoryServiceTypeMappingVO
	 * @param CategoryServiceTypeMappingVO
	 *            return list .
	 */
	public List deleteCategoryCodeMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO,
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVOselect) {
		return referenceDataRepository.deleteCategoryCodeMapping(
				categoryServiceTypeMappingVO,
				categoryServiceTypeMappingVOselect);
	}

	/**
	 * @param CategoryServiceTypeMappingVO
	 *            return list .
	 */
	public List fetchCategoryCodeVariableMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO) {
		return referenceDataRepository
				.fetchCategoryCodeVariableMapping(categoryServiceTypeMappingVO);
	}

	/**
	 * @param CategoryServiceTypeMappingVO
	 * @param CategoryServiceTypeMappingVO
	 * @param List
	 *            return list .
	 */
	public List createCategoryCodeMapping(
			CategoryServiceTypeMappingVO categoryServiceTypeMappingVO,
			CategoryServiceTypeMappingVO selectVO, List mapping) {
		return referenceDataRepository.createCategoryCodeMapping(
				categoryServiceTypeMappingVO, selectVO, mapping);
	}

	public List getMinorHeadingCatagoryCodeMapping() {
		return referenceDataRepository.getMinorHeadingCatagoryCodeMapping();
	}

	/**
	 * @param String
	 *            return Map .
	 */
	public Map getEB03Values(String hippaCode) {
		return referenceDataRepository.getEB03Values(hippaCode);
	}

	/**
	 * Fetch all the category codes with and without mappings in both LG and ISG
	 * - T52
	 * 
	 * @return List
	 */
	public List fetchAllCategoryCodes() {
		return referenceDataRepository.fetchAllCategoryCodes();
	}
	/* Reference Data Values -  START */
	/**
	 * @param String
	 * @return List
	 * Fetch all the reference data values matching the match value
	 * 
	 */
	public List fetchCatalogNames(String matchValue, boolean isHPNRef) {
		if(!isHPNRef){
			return referenceDataRepository.fetchCatalogNames(matchValue);
		}
		else{
			return referenceDataRepository.fetchHPNCatalogNames(matchValue);
		}
	}
	/**
	 * @param catalogName
	 * @return ReferenceDataValueVO
	 * Fetch all the data values corresponding to the catalog name
	 */
	public List fetchItems(String catalogName) {
		return referenceDataRepository.fetchItems(catalogName);
	}
	
	/**
	 * @param referenceDataValueVO
	 * @return String
	 * Add the new data value to the Catalog Name
	 */
	public String addItem(ReferenceDataValueVO referenceDataValueVO) {
		return referenceDataRepository.addItem(referenceDataValueVO);
	}
	
	/**
	 * @param referenceDataValueVO
	 * @return String
	 * Update the data value of the Catalog Name
	 */
	public String updateItem(ReferenceDataValueVO referenceDataValueVO) {
		return referenceDataRepository.updateItem(referenceDataValueVO);
	}
	
	/**
	 * @param referenceDataValueVO
	 * @return String
	 * Delete the data value of the Catalog Name
	 */
	public String deleteItem(ReferenceDataValueVO referenceDataValueVO) {
		return referenceDataRepository.deleteItem(referenceDataValueVO);
	}
	
	/**
	 * @param referenceDataValueVO
	 * @return ReferenceDataValueVO
	 * Checks if data value is mapped to SPS/VAriable/Header Rule
	 */
	public ReferenceDataValueVO checkItemMappings(ReferenceDataValueVO referenceDataValueVO) {
		return referenceDataRepository.checkItemMappings(referenceDataValueVO);
	}
	
	/**
	 * @return List
	 * Fetch the deleted data value details of the Catalog 
	 */
	public List fetchHistoryOfCatalog(String catalogName) {
		return referenceDataRepository.fetchHistoryOfCatalog(catalogName);
	}

	/**
	 * @param catalogName
	 * @return List
	 * Fetch the min and max lengths of the Catalog
	 */
	public ReferenceDataValueVO getCatalogMinMaxLengths(String catalogName) {
		return referenceDataRepository.getCatalogMinMaxLengths(catalogName);
	}
	
	/* Reference Data Values -  END */	
	
	/*************************Manage Header Rule EB03 Associations April 2012 Release SSCR14181 START ******************************/	
	/**
	 * @param catalogName
	 * @return List
	 * Returns the search result, which contains the header rule mappings to EB03
	 */
	public List fetchHeaderRuleToEB03Mapping(HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO){
		return referenceDataRepository.fetchHeaderRuleToEB03Mapping(headerRuleToEB03MappingVO);
	}
	/**
	 * @param HeaderRuleToEB03MappingVO
	 * @return List
	 * Returns all the header rule id, header rule descriptions corresponding to an EB03
	 */
	public List fetchHeaderRuleDetails(HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO){
		return referenceDataRepository.fetchHeaderRuleDetails(headerRuleToEB03MappingVO);
	}
	/**
	 * @param catalogName
	 * @return List
	 * Returns the search result, which contains the header rule mappings to EB03
	 */
	public List deleteHeaderRuleToEB03Mapping(HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO,
			HeaderRuleToEB03MappingVO headerRuleToEB03MappingVOSearchCriteria){
		return referenceDataRepository.deleteHeaderRuleToEB03Mapping(headerRuleToEB03MappingVO,headerRuleToEB03MappingVOSearchCriteria);
	}
	
	public List possibleHeaderRuleValues(String CSVwithSingleQuoteHeaderRulesFromPage){
		return referenceDataRepository.possibleHeaderRuleValues(CSVwithSingleQuoteHeaderRulesFromPage);
	}
	public List saveHeaderRuleToEB03Mapping (HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO,
			HeaderRuleToEB03MappingVO headerRuleToEB03MappingVOForSelect,List headerRuleListFromPage){
		return referenceDataRepository.saveHeaderRuleToEB03Mapping(headerRuleToEB03MappingVO,
				headerRuleToEB03MappingVOForSelect,headerRuleListFromPage);
	}
	public List viewHistoryOfHeaderRuleToEB03Mapping(HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO){
		return referenceDataRepository.viewHistoryOfHeaderRuleToEB03Mapping(headerRuleToEB03MappingVO);
	}
	/**
	 * method to fetch the header rule for auto complete.
	 * @param inputString - search string for date type auto complete
	 * @return List - List of header rules corresponding to the search string.
	 */
	public List fetchHeaderRuleForAutoComplete(String inputString,boolean autoCompleteFlag){
		return referenceDataRepository.fetchHeaderRuleForAutoComplete(inputString, autoCompleteFlag);
	}
/*************************Manage Header Rule EB03 Associations April 2012 Release SSCR14181 END ******************************/	
	
	/* EB01- DataType Association -  START */
	/**
	 * method to fetch the data type for auto complete.
	 * @param inputString - search string for date type auto complete
	 * @return List - List of data types corresponding to the search string.
	 */
	public List fetchDataTypeForAutoComplete(String inputString,boolean autoCompleteFlag) {
		return referenceDataRepository.fetchDataTypeForAutoComplete(inputString,autoCompleteFlag);
	}
	
	/**
	 * method to fetch the EB01 - data type association values.
	 * @param mappingVO - contains search parameters, either eb01 value or data type or both.
	 * @return List of DataTypeToEB01MappingVO
	 */
	public List fetchDataTypeToEB01Mapping(DataTypeToEB01MappingVO mappingVO) {
		return referenceDataRepository.fetchDataTypeToEB01Mapping(mappingVO);
		
	}
	/**
	 * method to delete the EB01 - data type association values.
	 * @param eb01MappingVODelete - contains EB01 value whose association to be delete and the user comments for audit trail.
	 * @param eb01MappingToFetch - contains search criteria (EB01 or data type or both) after delete action.
	 * @return List - List of EB01 - data type association values after delete action.
	 */
	public List deleteDatatypeToEB01Mapping(DataTypeToEB01MappingVO eb01MappingVODelete, DataTypeToEB01MappingVO eb01MappingToFetch) {
		return referenceDataRepository.deleteDatatypeToEB01Mapping(eb01MappingVODelete, eb01MappingToFetch);
	}
	
	/**
	 *  Method to edit the EB01 to Data Type association.
	 * @param eb01MappingVOEdit
	 * @return List - contains EB01 - data type association values for edit action.
	 */
	public List editDataTypeToEB01Mapping(DataTypeToEB01MappingVO eb01MappingVOEdit) {
		return referenceDataRepository.editDataTypeToEB01Mapping(eb01MappingVOEdit);
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
		return referenceDataRepository.saveDatatypeToEB01Mapping(eb01MappingVOToUpdate, eb01MappingVOToFetch, dataTypeListToInsert);
	}
	/**
	 * Method to show the history of save and delete actions.
	 * @param eb01MappingVOLocate
	 * @return List - Contains audit trail values corresponding the EB01 value.
	 */
	public List viewHistoryOfDatatypeToEB01Mapping(DataTypeToEB01MappingVO eb01MappingVOLocate) {
		return referenceDataRepository.viewHistoryOfDatatypeToEB01Mapping(eb01MappingVOLocate);
	}
	/* EB01- DataType Association -  END */
	// BXNI June Release Changes - Start
	/**
	 * Method to fetch the standard message by doing a like search.
	 * @param standardMessageToFetch
	 * @return List of standard messages.
	 */
	public List<StandardMessageVO> fetchStandardMessage(StandardMessageVO standardMessageToFetch) {
		return referenceDataRepository.fetchStandardMessage(standardMessageToFetch);
		
	}
	
	/**
	 * Method to delete the standard message.
	 * @param standardMessageToFetch
	 * @param standardMessageToDelete
	 * @return return the list of standard messages..
	 */
	@Transactional (rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = false)
	public List<StandardMessageVO> deleteStandardMessage(StandardMessageVO standardMessageToFetch, StandardMessageVO standardMessageToDelete) {
		referenceDataRepository.deleteStandardMessage(standardMessageToDelete);
		return referenceDataRepository.fetchStandardMessage(standardMessageToFetch);
	}
	
	/**
	 * Method to save the changes in the existing standard message and create a new standard message.
	 * If the create flag is true, it will create a new standard message else, it will update the existing standard message.
	 * @param standardMessageToFetch
	 * @param standardMessageToSave
	 * @param create
	 * @return List of standard messages,
	 */
	@Transactional (rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = false)
	public List<StandardMessageVO> saveStandardMessage(StandardMessageVO standardMessageToFetch, StandardMessageVO standardMessageToSave, boolean create) {
		if(create) {
			referenceDataRepository.createStandardMessage(standardMessageToSave);
		} else {
			referenceDataRepository.updateStandardMessage(standardMessageToSave);
			
		}
		return referenceDataRepository.fetchStandardMessage(standardMessageToFetch);
	}
	
	/**
	 * Method to fetch the standard message history.
	 * @return List of audit trail history.
	 */
	public List<StandardMessageVO> viewHistoryOfStandardMessage() {
		return referenceDataRepository.viewHistoryOfStandardMessage();
	}
	
	/**
	 * Method to edit the standard.
	 * @param stdMsgIdEdit
	 * @return
	 */
	public String editStandardMessage(String stdMsgIdEdit) {
		return referenceDataRepository.editStandardMessage(stdMsgIdEdit);
	}
	// BXNI June Release Changes - End
	/***********************************BXNI November Release Start****************************************************/
	/**
	 * Method to fetch the Service type Code mappings by doing a like search based on either LOB/State or MBU.
	 * @param ServiceTypeMappingVO
	 * @return result List of ServiceTypeMappingVO object.
	 */
	public List<ServiceTypeMappingVO> fetchServiceTypeCodeToEB11Mapping (ServiceTypeMappingVO VOToFetch){
		return referenceDataRepository.fetchServiceTypeCodeToEB11Mapping(VOToFetch);
	}
	/**
	 * Method to view the Service type Code-EB11 mappings associated with an LOB/State
	 * @param ServiceTypeMappingVO
	 * @return result List of ServiceTypeMappingVO object.
	 */
	public List<ServiceTypeMappingVO> viewServiceTypeCodeToEB11Mapping (ServiceTypeMappingVO VOToView){
		return referenceDataRepository.viewServiceTypeCodeToEB11Mapping(VOToView);
	}
	@Transactional (rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = false)
	public List<ServiceTypeMappingVO> deleteServiceTypeCodeToEB11Mapping (ServiceTypeMappingVO vOToDelete, ServiceTypeMappingVO vOToFetch){
		return referenceDataRepository.deleteServiceTypeCodeToEB11Mapping(vOToDelete,vOToFetch);
	}
	public List<ServiceTypeMappingVO> editServiceTypeCodeToEB11Mapping(ServiceTypeMappingVO VOToEdit){
		return null;
	}
	@Transactional (rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = false)
	public List<ServiceTypeMappingVO> saveServiceTypeCodeToEB11Mapping (ServiceTypeMappingVO vOToSave, ServiceTypeMappingVO vOToFetch){
		List<String> validationList = (null!=vOToSave.getValidationMessageList())? (vOToSave.getValidationMessageList()): new ArrayList<String>();
		List<ServiceTypeMappingVO> resultVOList = null;
		
		if(DomainConstants.ACTION_CREATE.equals(vOToSave.getAction())){
			//checks whether the LOB is already there in the LOB table, If so then validation Error
			String lobIdFromDB = referenceDataRepository.isLOBChangedWhileUpdate(vOToSave.getLineOfBusiness());
			//comparing the incoming lobid with the lobId from the db, if not equals, then error 
			if(null != lobIdFromDB && !lobIdFromDB.isEmpty() && !lobIdFromDB.equals(vOToSave.getLobId())){
				validationList.add("Mapping to LOB '"+vOToSave.getLineOfBusiness()+"' already exists.");
				vOToSave.setValidationMessageList(validationList);
			}
			/*boolean isLOBMappingExists = referenceDataRepository.isLOBMappingExists(vOToSave.getLineOfBusiness());
			if(isLOBMappingExists){
				validationList.add("Mapping to LOB '"+vOToSave.getLineOfBusiness()+"' already exists.");
				vOToSave.setValidationMessageList(validationList);
			}*/
			String mbuInCSVwithSingleQuote = "";
			//comma separated mbu changed to mbuInCSVwithSingleQuote
			if(!StringUtils.isBlank(vOToSave.getCommaSeperatedMbu())){
				mbuInCSVwithSingleQuote = BxUtil.convertCSVToCSVwithSingleQuote(vOToSave.getCommaSeperatedMbu());
			}
			//checks whether MBU already exists, if so, return all the existing MBU's as comma separated
			String existingMbu = "";
			if(!StringUtils.isBlank(mbuInCSVwithSingleQuote)){
				existingMbu  = referenceDataRepository.isMBUMappingExists(mbuInCSVwithSingleQuote,vOToSave.getLobId());
			}
			//checking if mbu exists, if so then validation Error
			if(null != existingMbu && !existingMbu.equals("")){
				validationList.add("MBU(s) "+existingMbu+" already mapped to different LOB");
				vOToSave.setValidationMessageList(validationList);
			}
			//validating the Service Type mappings
			ServiceTypeMappingVO voToSaveAfterValidation =  validateServiceTypeMappings(vOToSave,validationList);
			//if validation list is not empty, then the validation list is added to the resultVO list and returned
			if( null != voToSaveAfterValidation.getValidationMessageList() && !voToSaveAfterValidation.getValidationMessageList().isEmpty()){
				resultVOList = new ArrayList<ServiceTypeMappingVO>();
				resultVOList.add(voToSaveAfterValidation);
			}else{
				//inserting into the LOB master table and getting the lobID
				String lobId = referenceDataRepository.persistLOB(voToSaveAfterValidation);
				voToSaveAfterValidation.setLobId(lobId);
				
				//Inserting the MBU value to the association table
				referenceDataRepository.persistMBU(voToSaveAfterValidation);
				
				resultVOList = new ArrayList<ServiceTypeMappingVO>();
				resultVOList = referenceDataRepository.saveServiceTypeCodeToEB11Mapping(voToSaveAfterValidation, vOToFetch);
			}
			
		}else if(DomainConstants.ACTION_UPDATE.equals(vOToSave.getAction())){
			//checks whether the LOB is changed while Update Operation
			String lobIdFromDB = referenceDataRepository.isLOBChangedWhileUpdate(vOToSave.getLineOfBusiness());
			//comparing the incoming lobid with the lobId from the db, if not equals, then error 
			if(null != lobIdFromDB && !lobIdFromDB.isEmpty() && !lobIdFromDB.equals(vOToSave.getLobId())){
				validationList.add("Mapping to LOB '"+vOToSave.getLineOfBusiness()+"' already exists.");
				vOToSave.setValidationMessageList(validationList);
			}
			String mbuInCSVwithSingleQuote = "";
			//comma separated mbu changed to mbuInCSVwithSingleQuote
			if(!StringUtils.isBlank(vOToSave.getCommaSeperatedMbu())){
				mbuInCSVwithSingleQuote = BxUtil.convertCSVToCSVwithSingleQuote(vOToSave.getCommaSeperatedMbu());
			}
			//checks whether MBU already exists, if so, return all the existing MBU's as comma separated
			String existingMbu = "";
			if(!StringUtils.isBlank(mbuInCSVwithSingleQuote)){
				existingMbu  = referenceDataRepository.isMBUMappingExists(mbuInCSVwithSingleQuote,vOToSave.getLobId());
			}
			if(null != existingMbu && !existingMbu.equals("")){
				validationList.add("MBU(s) "+existingMbu+" already mapped to different LOB");
				vOToSave.setValidationMessageList(validationList);
			}
			//validating the Service Type mappings
			ServiceTypeMappingVO voToSaveAfterValidation =  validateServiceTypeMappings(vOToSave,validationList);
			//if validation list is not empty, then the validation list is added to the resultVO list and returned
			if( null != voToSaveAfterValidation.getValidationMessageList() && !voToSaveAfterValidation.getValidationMessageList().isEmpty()){
				resultVOList = new ArrayList<ServiceTypeMappingVO>();
				resultVOList.add(voToSaveAfterValidation);
			}else{
				//update the current lob name in the db with the new lob name for the lob id.
				referenceDataRepository.persistLOBWhileUpdate(voToSaveAfterValidation);
				
				//Delete the current mbu mappings and insert the mbu mappings from page for the lob id.
				referenceDataRepository.persistMBUWhileUpdate(voToSaveAfterValidation);
				
				resultVOList = new ArrayList<ServiceTypeMappingVO>();
				resultVOList = referenceDataRepository.saveServiceTypeCodeToEB11Mapping(voToSaveAfterValidation, vOToFetch);
			}
			
		}else{
			//validating the Service Type mappings
			ServiceTypeMappingVO voToSaveAfterValidation =  validateServiceTypeMappings(vOToSave,validationList);
			//if validation list is not empty, then the validation list is added to the resultVO list and returned
			if( null != voToSaveAfterValidation.getValidationMessageList() && !voToSaveAfterValidation.getValidationMessageList().isEmpty()){
				resultVOList = new ArrayList<ServiceTypeMappingVO>();
				resultVOList.add(voToSaveAfterValidation);
			}else{
				resultVOList = new ArrayList<ServiceTypeMappingVO>();
				resultVOList = referenceDataRepository.saveServiceTypeCodeToEB11Mapping(voToSaveAfterValidation, vOToFetch);
			}
		}
		return resultVOList;
	}
	private ServiceTypeMappingVO validateServiceTypeMappings(ServiceTypeMappingVO vOToSave, List<String> validationList){
		
		
		List<ServiceTypeCodeToEB11VO> serviceTypeMappingsList = vOToSave.getServiceTypeCodeToEB11VOList();
		//List contains the value of mappings converted into String format separated by tilde(`)
		List<String> keyList = new ArrayList<String>() ;
		String mappingKeyMain="";
		//List contains the value of mappings without the place of service value converted into String format separated by tilde(`)
		List<String> mappingWithoutPOSList = new ArrayList<String>();
		List<String> mappingWithPOSList = new ArrayList<String>();
		List<String> duplicateEB03List = new ArrayList<String>();
		for(ServiceTypeCodeToEB11VO serviceTypeMapping: serviceTypeMappingsList){
			mappingKeyMain = serviceTypeMapping.getServiceTypeCode()+"~"+serviceTypeMapping.getPlaceOfService();
			
			if(null != serviceTypeMapping.getPlaceOfService() && !serviceTypeMapping.getPlaceOfService().isEmpty()){
				String keyWithPlaceOfService = serviceTypeMapping.getServiceTypeCode();
				mappingWithPOSList.add(keyWithPlaceOfService);
				
			}else{
				String keyWithOutPlaceOfService = serviceTypeMapping.getServiceTypeCode();
				mappingWithoutPOSList.add(keyWithOutPlaceOfService);
			}
			if(keyList.contains(mappingKeyMain)){
				String duplicateValiditationMsg = "Mapping already exists for EB03 = '"+serviceTypeMapping.getServiceTypeCode()+"'";
				if(!StringUtils.isBlank(serviceTypeMapping.getPlaceOfService())){
					duplicateValiditationMsg = duplicateValiditationMsg+", POS = "+serviceTypeMapping.getPlaceOfService();
				}
				if(!validationList.contains(duplicateValiditationMsg)){
					duplicateEB03List.add(serviceTypeMapping.getServiceTypeCode());
					validationList.add(duplicateValiditationMsg);
				}
				vOToSave.setValidationMessageList(validationList);
			}
			keyList.add(mappingKeyMain);
		}
			//Iterate key list without place of service to check if a pos mapping is present, then there must exist a mapping without POS
			for(String key: mappingWithPOSList){
				String posValidationMessage = "";
				if(!mappingWithoutPOSList.contains(key) && !duplicateEB03List.contains(key)){
					posValidationMessage = "EB03 = '"+key+"' requires a mapping without Place Of Service";
					if(!validationList.contains(posValidationMessage)){
						validationList.add(posValidationMessage);
					}
				}
					vOToSave.setValidationMessageList(validationList);
			}
		return vOToSave;
	}
	
	public List<ServiceTypeToEB11DataObject> viewHistoryOfServiceTypeCodeToEB11Mapping(ServiceTypeMappingVO viewHistoryVO){
		return referenceDataRepository.viewHistoryOfServiceTypeCodeToEB11Mapping(viewHistoryVO);
	}
	
	
	/***********************************BXNI November Release End****************************************************/
}
