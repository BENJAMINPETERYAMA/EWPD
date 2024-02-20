package com.wellpoint.wpd.common.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLevel;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierCriteria;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierDefinition;
import com.wellpoint.wpd.common.override.benefit.bo.TierDefinitionBO;

public class BenefitTierUtil {

	public static List getTieredList(List criteriaListFrmDB){
    	
    	List tierDefList = new ArrayList();
    	List tierBOList = new ArrayList();
    	int oldCntrctTierId = 0;
    	int newCntrctTierId = 0;
    	
    	BenefitTierDefinition tierDefinition = null;
    	BenefitTier tierBO = null;
    	
    	if(null!= criteriaListFrmDB && !criteriaListFrmDB.isEmpty()){
    		
    		int oldTierDefId = 0;
        	int newTierDefId = 0;
    		
			TierDefinitionBO oldTierDef = (TierDefinitionBO)criteriaListFrmDB.get(0);
			oldTierDefId = oldTierDef.getTierDefId();
			
			tierDefinition = new BenefitTierDefinition(oldTierDefId);
			tierDefinition.setBenefitTierDefinitionSysId(oldTierDef.getTierDefId());
			tierDefinition.setBenefitTierDefinitionName(oldTierDef.getTierDesc());
			tierDefinition.setDataType(oldTierDef.getDataType());
			tierDefList.add(tierDefinition);
			
			for(int i=1; i< criteriaListFrmDB.size(); i++){ 	
								
		    	TierDefinitionBO newTierDef = (TierDefinitionBO)criteriaListFrmDB.get(i);
		    	tierDefinition = new BenefitTierDefinition(newTierDef.getTierDefId());
		    	newTierDefId = newTierDef.getTierDefId();
		    	
		    	if(oldTierDefId != newTierDefId){
		    		tierDefinition.setBenefitTierDefinitionSysId(newTierDef.getTierDefId());
		    		tierDefinition.setBenefitTierDefinitionName(newTierDef.getTierDesc());
		    		tierDefinition.setDataType(newTierDef.getDataType());
		    		tierDefList.add(tierDefinition);
		    	}
		    	oldTierDefId = newTierDefId;
			}
			
			oldTierDef = (TierDefinitionBO)criteriaListFrmDB.get(0);
			oldCntrctTierId= oldTierDef.getTierSysId();
			oldTierDefId = oldTierDef.getTierDefId();
			
			tierBO = new BenefitTier(tierDefinition);
			tierBO.setBenefitTierSysId(oldCntrctTierId);
			tierBO.setBenefitTierDefinition(new BenefitTierDefinition(oldTierDefId));
			tierBO.setCriteriaIndicator(oldTierDef.getCriteriaIndicator());
			tierBO.setDisplaySequenceNo(oldTierDef.getDispSeqNo());
			tierBOList.add(tierBO);
			
			for(int i=1;i<criteriaListFrmDB.size();i++){
				
				tierBO = new BenefitTier(tierDefinition);
				
		    	TierDefinitionBO newTierDef = (TierDefinitionBO)criteriaListFrmDB.get(i);
		    	newCntrctTierId = newTierDef.getTierSysId();
		    	
		    	if(oldCntrctTierId != newCntrctTierId){
		    		tierBO.setBenefitTierSysId(newCntrctTierId);
		    		tierBO.setBenefitTierDefinition(new BenefitTierDefinition(newTierDef.getTierDefId()));
		    		tierBO.setCriteriaIndicator(newTierDef.getCriteriaIndicator());
					tierBO.setDisplaySequenceNo(newTierDef.getDispSeqNo());
		    		tierBOList.add(tierBO);
		    	}
		    	oldCntrctTierId = newCntrctTierId;
			}
			
			List tierList = null;
		
			for(int i=0; i<tierDefList.size();i++){
				
				tierList = new ArrayList();
				BenefitTierDefinition tierDef = (BenefitTierDefinition)tierDefList.get(i);
				int tierDefId = tierDef.getBenefitTierDefinitionSysId();
				for(int j=0; j<tierBOList.size();j++){
					    
					BenefitTier tier = (BenefitTier)tierBOList.get(j);
					int tierDefId_temp = tier.getBenefitTierDefinition().getBenefitTierDefinitionSysId();
					if(tierDefId == tierDefId_temp){
						tier.setBenefitTierDefinition(tierDef);
						tierList.add(tier);
					}
				}
				tierDef.setBenefitTiers(tierList);
			}
			
			List criteriaList = null;
			for(int i=0;i<tierDefList.size();i++){
				
				BenefitTierDefinition defBO = (BenefitTierDefinition)tierDefList.get(i);
				List cntTierList = defBO.getBenefitTiers();
				
				for(int j=0;j<defBO.getBenefitTiers().size();j++){
					
					BenefitTier tier = (BenefitTier)cntTierList.get(j);
					int cntrctTierSysId = tier.getBenefitTierSysId();
					criteriaList = new ArrayList();
					
					for(int k=0; k<criteriaListFrmDB.size();k++){
						TierDefinitionBO tierDefBO = (TierDefinitionBO)criteriaListFrmDB.get(k);
						int cntrctTierSysId_db = tierDefBO.getTierSysId();
						
						if(cntrctTierSysId == cntrctTierSysId_db){
							BenefitTierCriteria criteriaBO = new BenefitTierCriteria();
							criteriaBO.setBenefitTierCriteriaSysId(tierDefBO.getTierCriteriaSysId());
							criteriaBO.setBenefitTierCriteriaValue(tierDefBO.getCriteriaVal());
							criteriaBO.setBenefitTierCriteriaName(tierDefBO.getCriteriaName());
							criteriaBO.setBenefitTier(tier);
							criteriaList.add(criteriaBO);
						}
					}
					tier.setBenefitTierCriteriaList(criteriaList);
				}
			}
    	}
    	return tierDefList;
    }

    //to arrange lines into levels 
	public static List getLvlLineList(List benefitDefinitionsList) {
	
	    Logger.logInfo("entered method getValuesFromDefinitionList");
	
	    // TODO Auto-generated method stub
	   List benefitLevelList = new ArrayList();
	    for (int i = 0; i < benefitDefinitionsList.size(); i++) {
	    	TierDefinitionBO entityBenefitLine = (TierDefinitionBO) benefitDefinitionsList
	                .get(i);
	        //sets values to the benefitLevel List
	        //arrange line in appropriate level
	    	benefitLevelList = setValuesToBenefitLevelForTier(entityBenefitLine, benefitLevelList); 
	    	
	
	    }
	    return benefitLevelList;
	}
	
	 private static List setValuesToBenefitLevelForTier(TierDefinitionBO entityBenefitLine,
	            List benefitLevelList) {

	        Logger.logInfo("entered method setValuesToBenefitLevel");

	        // TODO Auto-generated method stub
	        BenefitLevel benefitLevelBO = null;
	        /*
	         * //checks if the benefit level list size is not equal to zero
	         * if(benefitLevelList.size()!= 0){
	         * benefitLevelBO=(BenefitLevel)benefitLevelList.get(benefitLevelList.size()-1); }
	         * 
	         * //checks if the benefit LevelList size is 0 or if the previous
	         * levelId is equal to the present levelId if(benefitLevelList.size()==0 ||
	         * entityBenefitLine.getLevelSysId()!=benefitLevelBO.getLevelId()){
	         */
	        BenefitLevel tempBO = null;
	        boolean checkFlag = false;
	        // checks if the benefit level list size is not equal to zero
	        if (benefitLevelList.size() != 0) {
	            /*
	             * benefitLevelBO = (BenefitLevel)benefitLevelList.
	             * get(benefitLevelList.size() - 1);
	             */
	            for (int i = 0; i < benefitLevelList.size(); i++) {
	                tempBO = (BenefitLevel) benefitLevelList.get(i);
	                if (tempBO.getLevelId() == entityBenefitLine.getLevelSysId() && tempBO.getTierSysId() == entityBenefitLine.getTierSysId()) {
	                    benefitLevelBO = (BenefitLevel) benefitLevelList.get(i);
	                    checkFlag = true;
	                }
	            }
	        }
	        // checks if the benefit LevelList size is 0 or if the previous levelId
	        // is equal to the present levelId
	        if (benefitLevelList.size() == 0 ||
	        //(entityBenefitLine.getLevelSysId()!= benefitLevelBO.getLevelId())){
	                !checkFlag) {
	            BenefitLevel entityBenefitLevel = new BenefitLevel();
	            entityBenefitLevel.setLevelDesc(entityBenefitLine.getLevelDesc());
	            entityBenefitLevel.setLevelId(entityBenefitLine.getLevelSysId());
	            entityBenefitLevel.setTermDesc(entityBenefitLine.getBnftLvlTerm());
	            entityBenefitLevel.setQualifierDesc(entityBenefitLine.getLevelQualDesc());
	            entityBenefitLevel.setTierSysId(entityBenefitLine.getTierSysId());
	            entityBenefitLevel.setTierDefSysId(entityBenefitLine.getTierDefId());
	            //CARS START
	            //DESCRIPTION : Setting frequency value to the list
	            entityBenefitLevel.setFrequencyId(entityBenefitLine.getFrequencyValue());
	            entityBenefitLevel.setLowerLevelFrequencyValue(entityBenefitLine.getLowerLevelFrequencyValue()+"");
	            entityBenefitLevel.setLowerLevelDescValue(entityBenefitLine.getLowerLevelDescriptionValue());
	            //CARS END
	            //Sets the status of level hide/unhide
	            
	            // entityBenefitLevel.setReferenceDesc(entityBenefitLine.getReferenceDesc());

	            //sets benefit lines to the benefit Levels
	            entityBenefitLevel.setBenefitLines(new ArrayList());
	            entityBenefitLevel.getBenefitLines().add(
	                    getBenefitLineBOForTier(entityBenefitLine));
	            benefitLevelList.add(entityBenefitLevel);

	        } else {
	            //add benefit lines to the existing benefit level
	            benefitLevelBO.getBenefitLines().add(
	                    getBenefitLineBOForTier(entityBenefitLine));

	        }
	        return benefitLevelList;

	    }
	 //CARS:AM2:START
	 public static List getLvlLineListForTerms(List benefitDefinitionsList) {
		
		    Logger.logInfo("entered method getValuesFromDefinitionList");
		
		    // TODO Auto-generated method stub
		   List benefitLevelList = new ArrayList();
		    for (int i = 0; i < benefitDefinitionsList.size(); i++) {
		    	TierDefinitionBO entityBenefitLine = (TierDefinitionBO) benefitDefinitionsList
		                .get(i);
		        //sets values to the benefitLevel List		    	
		    	benefitLevelList = setValuesToBenefitTierFromLine(entityBenefitLine, benefitLevelList);
		
		    }
		    return benefitLevelList;
		}
	 private static List setValuesToBenefitTierFromLine(TierDefinitionBO entityBenefitLine,List benefitLevelList) 
	 {
	 	String seperator="#";
	 	BenefitTier  benefitTier = null;
	 	BenefitTier  tempBO = null;
        boolean checkFlag = false;
        // checks if the benefit level list size is not equal to zero
        if (benefitLevelList.size() != 0) 
        {
            for (int i = 0; i < benefitLevelList.size(); i++) 
            {
                tempBO = (BenefitTier) benefitLevelList.get(i);
                if ( tempBO.getBenefitTierSysId() == entityBenefitLine.getTierSysId()) 
                {
                	benefitTier = (BenefitTier) benefitLevelList.get(i);
                    checkFlag = true;
                }
            }
        }
        // checks if the benefit LevelList size is 0 or if the previous levelId
        // is equal to the present levelId
        if (benefitLevelList.size() == 0 ||
        //(entityBenefitLine.getLevelSysId()!= benefitLevelBO.getLevelId())){
                !checkFlag) {
        	BenefitTier entityBenefitLevel = new BenefitTier(entityBenefitLine.getTierSysId());
        	List termList = new ArrayList();
        	/*CARS|AM2|POS|START*/
        	Map termsPVAMap = new HashMap(0);
        	List pvaList = new ArrayList(0);
        	
        	String pva = entityBenefitLine.getLinePvaCode();
			if(pva.equalsIgnoreCase("ALL"))
			{
				if(!pvaList.contains("HMO"))
					pvaList.add("HMO");
				if(!pvaList.contains("PPO"))
					pvaList.add("PPO");
			}
			else if (pva.equalsIgnoreCase("HMO") ) 
			{
				if(!pvaList.contains("HMO"))
					pvaList.add("HMO");
			}
			else if (( pva.equalsIgnoreCase("NPAR") || pva.equalsIgnoreCase("PAR") || pva.equalsIgnoreCase("OPT")))
			{ 
				if(!pvaList.contains("PPO"))
					pvaList.add("PPO");
			}        			
			else
			{
				if(!pvaList.contains("HMO"))
					pvaList.add("HMO");
				if(!pvaList.contains("PPO"))
					pvaList.add("PPO");
			}        	        	
        	termsPVAMap.put(entityBenefitLine.getBnftLvlTerm(),pvaList);
        	entityBenefitLevel.setTermsPVAMap(termsPVAMap);
        	/*CARS|AM2|POS|END*/
			termList.add(entityBenefitLine.getBnftLvlTerm());
        	//entityBenefitLevel.setTermsQuery(" TRM_PRMRY_CDS = '"+entityBenefitLine.getBnftLvlTerm()+"' ");
        	entityBenefitLevel.setTermsQuery(entityBenefitLine.getBnftLvlTerm());
            entityBenefitLevel.setTermList(termList);
            entityBenefitLevel.setBenefitTierSysId(entityBenefitLine.getTierSysId());
            //entityBenefitLevel.setTierDefSysId(entityBenefitLine.getTierDefId());
            //Sets the status of level hide/unhide
            
            // entityBenefitLevel.setReferenceDesc(entityBenefitLine.getReferenceDesc());

            //sets benefit lines to the benefit Levels
            //entityBenefitLevel.setBenefitLines(new ArrayList());
            //entityBenefitLevel.getBenefitLines().add(  getBenefitLineBOForTier(entityBenefitLine));
            benefitLevelList.add(entityBenefitLevel);

        } else {
            //add benefit lines to the existing benefit level
        	/*CARS|AM2|POS|START*/
        	if(!(benefitTier.getTermsPVAMap().containsKey(entityBenefitLine.getBnftLvlTerm())))
        	{
        		benefitTier.getTermsPVAMap().put(entityBenefitLine.getBnftLvlTerm(),entityBenefitLine.getLinePvaCode());
        		Map termsPVAMap = new HashMap(0);
            	List pvaList = new ArrayList(0);
            	pvaList.add(entityBenefitLine.getLinePvaCode());
            	benefitTier.getTermsPVAMap().put(entityBenefitLine.getBnftLvlTerm(),pvaList);
        	}
        	else
        	{
        		List existingPVAListInMap = (ArrayList)benefitTier.getTermsPVAMap().get(entityBenefitLine.getBnftLvlTerm());
        		if(existingPVAListInMap != null && existingPVAListInMap.size()!=0 && !existingPVAListInMap.contains(entityBenefitLine.getLinePvaCode()))
        		{
        			String pva = entityBenefitLine.getLinePvaCode();
        			if(pva.equalsIgnoreCase("ALL"))
        			{
        				if(!existingPVAListInMap.contains("HMO"))
        					existingPVAListInMap.add("HMO");
        				if(!existingPVAListInMap.contains("PPO"))
        					existingPVAListInMap.add("PPO");
        			}
        			else if (pva.equalsIgnoreCase("HMO") ) 
        			{
        				if(!existingPVAListInMap.contains("HMO"))
        					existingPVAListInMap.add("HMO");
        			}
        			else if (( pva.equalsIgnoreCase("NPAR") || pva.equalsIgnoreCase("PAR") || pva.equalsIgnoreCase("OPT")))
        			{ 
        				if(!existingPVAListInMap.contains("PPO"))
        					existingPVAListInMap.add("PPO");
        			}        			
        			else
        			{
        				if(!existingPVAListInMap.contains("HMO"))
        					existingPVAListInMap.add("HMO");
        				if(!existingPVAListInMap.contains("PPO"))
        					existingPVAListInMap.add("PPO");
        			}
        		}        		
        	}
        	/*CARS|AM2|POS|END*/        	
        	if(!(benefitTier.getTermList().contains(entityBenefitLine.getBnftLvlTerm()))){
        		benefitTier.getTermList().add(entityBenefitLine.getBnftLvlTerm());
        		benefitTier.setTermsQuery(benefitTier.getTermsQuery()+seperator+entityBenefitLine.getBnftLvlTerm());
        		benefitTier.setBenefitTierSysId(entityBenefitLine.getTierSysId());        	
        	}
        }
        return benefitLevelList;
    }
	 //CARS:AM2:END
	 private static BenefitLine getBenefitLineBOForTier(TierDefinitionBO entityBenefitLine) {

	        Logger.logInfo("entered method getBenefitLineBO");

	        BenefitLine entityBenefitLineToSet = new BenefitLine();
	        entityBenefitLineToSet.setBenefitValue(entityBenefitLine.getLineBnftVal());
	        //Setting the non-overridden benefit Values as part of Enhancement
	        entityBenefitLineToSet.setLineValue(entityBenefitLine.getLineVal());
	        entityBenefitLineToSet.setProviderArrangementDesc(entityBenefitLine.getPvaDesc());
	        entityBenefitLineToSet.setLineSysId(entityBenefitLine.getLineSysId());
	        entityBenefitLineToSet.setReferenceDesc(entityBenefitLine.getLevelRefDesc());
	        entityBenefitLineToSet.setDataTypeLegend(entityBenefitLine.getLineDataTypeLgnd());
	        entityBenefitLineToSet.setProviderArrangementId(entityBenefitLine.getLinePvaCode());
	        entityBenefitLineToSet.setTierSysId(entityBenefitLine.getTierSysId());
	        entityBenefitLineToSet.setBnftLineNotesExist(entityBenefitLine.getBnftLineNotesExist());
	        //Sets the benefit customized sys id
	        //Sets the status of line hide/unhide 
	        
	        if (null != entityBenefitLine.getLineDateType()
	                && !(entityBenefitLine.getLineDateType()).equals("")) {
	            entityBenefitLineToSet.setDataTypeDesc(entityBenefitLine
	                    .getLineDateType());
	        }
	        // **Change**
	        entityBenefitLineToSet.setDataTypeId(entityBenefitLine.getLineDataTypeId());
	        
	        // **End**
	        return entityBenefitLineToSet;
	    }
	 
	 public static int compareCriteria(BenefitTierCriteria startCriteria,
			BenefitTierCriteria endCriteria, String dataType) {
		if (dataType == null) {
			dataType = "String";
		}
		if (dataType.equalsIgnoreCase("String")) {
			return startCriteria.getBenefitTierCriteriaValue().compareTo(
					endCriteria.getBenefitTierCriteriaValue());
		} else if (dataType.equalsIgnoreCase("Numeric")) {
			return new Integer(Integer.parseInt(startCriteria
					.getBenefitTierCriteriaValue()))
					.compareTo(new Integer(Integer.parseInt(endCriteria
							.getBenefitTierCriteriaValue())));
		} else if (dataType.equalsIgnoreCase("Date")) {
			return (startCriteria.getBenefitTierCriteriaValue())
					.compareTo((endCriteria
							.getBenefitTierCriteriaValue()));
		} else {
			return 0;
		}
	}
	 
	 public static boolean checkSameCriteria(BenefitTierCriteria startCriteria,
			BenefitTierCriteria endCriteria, String dataType) {
		if (dataType == null) {
			dataType = "String";
		}
		if (dataType.equalsIgnoreCase("String")) {
			return startCriteria.getBenefitTierCriteriaValue().equals(
					endCriteria.getBenefitTierCriteriaValue());
		} else if (dataType.equalsIgnoreCase("Numeric")) {
			return new Integer(Integer.parseInt(startCriteria
					.getBenefitTierCriteriaValue()))
					.equals(new Integer(Integer.parseInt(endCriteria
							.getBenefitTierCriteriaValue())));
		} else if (dataType.equalsIgnoreCase("Date")) {
			return (startCriteria.getBenefitTierCriteriaValue())
					.equals((endCriteria
							.getBenefitTierCriteriaValue()));
		} else {
			return false;
		}
	} 
}
