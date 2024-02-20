package com.wellpoint.ets.bx.mapping.repository;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSMappingRetrieveResult;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;

public interface MappingRepository {
    /*
     * if the contract variable is not in the db,then the mapping is new.
     */
	boolean isMappingAlreadyCreated(Mapping mapping);

	boolean persistNewVariableMapping(Mapping mapping);
	
	void persistChildTable(Long sysID,String hippaSegmentName, List hippaCodeSelectedValues, String createdUserName);
	
	List findMatchingVariables(Variable variable);		
	
	public boolean updateBeingModified(Mapping mapping, Mapping retrieveMapping);
	
	public boolean updateMapping(Mapping mapping, Mapping retrieveMapping);
	
	boolean persistVariableMappingInTemp(Mapping mapping);	
	
	List retrieveVariableForEdit(Mapping mapping);
	
	public Mapping retrieveMapping(String variableId);
	
	public List<Mapping> retrieveMappingForTestDataFeed();
	
	public List<Mapping> retrieveMappingForProdDataFeed(int inClauseSize, List<String> variableList);

	public List<Mapping> retrieveBeingModifiedForProdDataFeed(int inClauseSize,List<String> variableList);
	
	public Mapping retrieveBeingModified(String variableId);	
	
	public List retrieveVariableInfo(Variable variable);
	
	public boolean isMappingBeingModified(String varaiableId);
	
	public String retrieveStatus(String varaiableId);
	
	public int isValidVariableID(String variableId);
	
	public boolean mergeMapping(int batchSize, List<Mapping> mapping);
	
	//public boolean mergeMapping(Mapping mapping);
	
	public SPSMappingRetrieveResult getSPSMappingMain(String spsId);
	
}
