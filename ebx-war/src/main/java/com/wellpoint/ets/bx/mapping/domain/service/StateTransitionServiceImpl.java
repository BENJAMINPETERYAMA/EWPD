/*
 * Created on May 20, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.repository.MappingRepository;
import com.wellpoint.ets.bx.mapping.repository.StateTransitionRepository;

/**
 * @author u22093
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StateTransitionServiceImpl implements StateTransitionService {
	
	private MappingRepository mappingRepository;
	private StateTransitionRepository stateTransitionRepository;
	private static Logger log = Logger.getLogger(StateTransitionServiceImpl.class);
	
	public boolean transformToBeingModified(Mapping mapping){
		
		Mapping mappingToPersist = null;
		boolean tempTablePersistStatus = false;
		mappingToPersist = mappingRepository.retrieveMapping(mapping.getVariable().getVariableId());
		
		if(mappingToPersist.getInTempTable().equals("N")){
			stateTransitionRepository.updateMstrToBeingModified(mapping);
			mappingToPersist.setVariableMappingStatus(DomainConstants.STATUS_BEING_MODIFIED);
		//	mappingToPersist.setUser(mapping.getUser());
			tempTablePersistStatus = mappingRepository.persistVariableMappingInTemp(mappingToPersist);
			log.info("success");
		}else{
			mappingToPersist = mappingRepository.retrieveBeingModified(mapping.getVariable().getVariableId());
			mappingToPersist.setUser(mapping.getUser());
			mappingToPersist.setVariableMappingStatus(DomainConstants.STATUS_BEING_MODIFIED);			
			stateTransitionRepository.updateStatusInTemp(mappingToPersist);
		}
		return tempTablePersistStatus;
	}
	
	public int transformToScheduledToTest(Mapping mapping){
		
		int status = 0;
		Mapping mappingToPersist = null;
		
		
		mappingToPersist = mappingRepository.retrieveMapping(mapping.getVariable().getVariableId());
		if(mappingToPersist.getInTempTable().equals("N")){
			
			mapping.setVariableMappingStatus(DomainConstants.STATUS_SCHEDULED_TO_TEST);
			status = stateTransitionRepository.updateStatusInMstr(mapping);
			
		}
		else{
			mapping.setVariableMappingStatus(DomainConstants.STATUS_SCHEDULED_TO_TEST);
			status = stateTransitionRepository.updateStatusInTemp(mapping);

		}	
		return status;
	}
	
		public int notApplicable(Mapping mapping) {
			int status = 0;
			Mapping mappingToPersist = null;
			
			mappingToPersist = mappingRepository.retrieveMapping(mapping.getVariable().getVariableId());
		
			if(mappingToPersist.getInTempTable().equals("Y")){
				stateTransitionRepository.rollBackTempMapping(mappingToPersist);
			}
			mapping.setVariableMappingStatus(DomainConstants.STATUS_NOT_APPLICABLE);
			status = stateTransitionRepository.updateStatusInMstr(mapping);
			mapping.setInTempTable("N");
			stateTransitionRepository.updateBeingModifiedFlag(mapping);
			
		return status;
	}
	
	public int approve(Mapping mapping){
		int status = 0;
				
		if(isUpdateMstr(mapping)){
			status = stateTransitionRepository.updateStatusInMstr(mapping);
		}
		else{
			status = stateTransitionRepository.updateStatusInTemp(mapping);
		}	
		return status;
	}
	
	public Mapping publishMapping(Mapping mapping, String variableId){
		
		Mapping retriveMappingTemp =  null;
		
		
		if(isUpdateMstr(mapping)){
			mapping.setVariableMappingStatus(DomainConstants.STATUS_PUBLISHED);
			stateTransitionRepository.updateStatusMstrForDatafeed(mapping);
		}
		else{
			boolean status =false;
			
			retriveMappingTemp = mappingRepository.retrieveBeingModified(variableId);
			retriveMappingTemp.setVariableMappingStatus(DomainConstants.STATUS_PUBLISHED);			
			//status = mappingRepository.mergeMapping(mapping);
			
			if(status){
				stateTransitionRepository.rollBackTempMapping(retriveMappingTemp);
			}
				mapping =retriveMappingTemp;
			}		
		return mapping;
	}
	
	public List<Mapping> publishMapping(int inClauseSize, int batchSize,
			List<String> tempVarList,List<Mapping> mapping){
		
		List<Mapping> retriveMappingTemp =  null;
		List<Mapping> totalMappingList = new ArrayList<Mapping>();
		if(null != mapping && mapping.size() != 0) {
			int i = stateTransitionRepository.updateStatusMstrForProdDatafeed();
			for(Mapping mapObj : mapping) {
				mapObj.setVariableMappingStatus(DomainConstants.STATUS_PUBLISHED);
			}
			log.debug("updateStatusMstrForProdDatafeed size = "+ i);
			totalMappingList.addAll(mapping);
		}
		if(null != tempVarList && tempVarList.size() != 0){
			boolean status =false;
			
			retriveMappingTemp = mappingRepository.retrieveBeingModifiedForProdDataFeed(inClauseSize, tempVarList);
			if(null != retriveMappingTemp && retriveMappingTemp.size() != 0) {
				for(Mapping tempMapping : retriveMappingTemp) {
					tempMapping.setVariableMappingStatus(DomainConstants.STATUS_PUBLISHED);
				}
			}
			status = mappingRepository.mergeMapping(batchSize, retriveMappingTemp);
			
			if(status){
				stateTransitionRepository.rollBackTempMapping(batchSize, retriveMappingTemp);
			}
			totalMappingList.addAll(retriveMappingTemp);
		}
		return totalMappingList;		
	}
	
	
	private boolean isUpdateMstr(Mapping mapping) {
		mapping.setUpdateMstr(false);
		if(mapping.getInTempTable().equals("N")){
			mapping.setUpdateMstr(true);
		}
		return mapping.isUpdateMstr();
	}
	
	
	/**
	 * @param retriveMappingTemp
	 * @return
	 */

	

	public int transformToPreviousPublishedMapping(Mapping mapping){
		int status = 0;
		boolean tempDeleteStatus = stateTransitionRepository.rollBackTempMapping(mapping);
		
		mapping.setInTempTable("N");
		mapping.setVariableMappingStatus("PUBLISHED");
		stateTransitionRepository.updateBeingModifiedFlag(mapping);
		
		if(tempDeleteStatus){
			
			status = 1;
		}
		return status;
		
	}
	
	public int transferMappingToTestRegion(Mapping mapping){	
		
		int updateStatus =0;
		
		if(isUpdateMstr(mapping)){
			updateStatus = stateTransitionRepository.updateStatusMstrForDatafeed(mapping);
		}
		else{
			
			updateStatus = stateTransitionRepository.updateStatusTempForDatafeed(mapping);
		}
		
		return updateStatus;
	}
	
	public int transferMappingToTestRegion(){	
		
		int updateMstrStatus =0;
		int updateTempStatus =0;
		updateMstrStatus = stateTransitionRepository.updateStatusMstrForDatafeed();
		updateTempStatus = stateTransitionRepository.updateStatusTempForDatafeed();
		return updateMstrStatus+updateTempStatus;
	}
	
	public boolean transformToEditableState(Mapping mapping , Mapping retrieveMapping){
		
		boolean mappingResult = true;
		String varaiableId = mapping.getVariable().getVariableId();
		if (mappingRepository.isMappingBeingModified(varaiableId)) {
			mapping.setVariableMappingStatus(DomainConstants.STATUS_BEING_MODIFIED);
			mappingResult = mappingRepository.updateBeingModified(mapping,retrieveMapping);			
		}		
		else{	
			mapping.setVariableMappingStatus(DomainConstants.STATUS_BUILDING);
			mappingResult = mappingRepository.updateMapping(mapping,retrieveMapping);					
		}
		
		return mappingResult;
	}
	/**
	 * @return Returns the mappingRepository.
	 */
	public MappingRepository getMappingRepository() {
		return mappingRepository;
	}
	/**
	 * @param mappingRepository The mappingRepository to set.
	 */
	public void setMappingRepository(MappingRepository mappingRepository) {
		this.mappingRepository = mappingRepository;
	}
	/**
	 * @return Returns the stateTransitionRepository.
	 */
	public StateTransitionRepository getStateTransitionRepository() {
		return stateTransitionRepository;
	}
	/**
	 * @param stateTransitionRepository The stateTransitionRepository to set.
	 */
	public void setStateTransitionRepository(
			StateTransitionRepository stateTransitionRepository) {
		this.stateTransitionRepository = stateTransitionRepository;
	}

}
