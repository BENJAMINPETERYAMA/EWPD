package com.wellpoint.ets.bx.mapping.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSDetail;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.VariableMappingService;
import com.wellpoint.ets.bx.mapping.domain.vo.MassUpdateCriteria;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.ebx.mapping.domain.service.LocateService;
import com.wellpoint.ets.ebx.simulation.vo.ContractMappingVO;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.simulation.vo.MajorHeadingsVO;
import com.wellpoint.ets.ebx.simulation.vo.MinorHeadingsVO;
import com.wellpoint.ets.ebx.simulation.util.SimulationResourceBundle;

/**
 * @author UST-GLOBAL
 * This is an Util class helps mass update.
 * 
 */
public class MappingUtil {
	
	/**
	 *  This method find mappings for ebx mass update.
	 * @param locateService
	 * @param massUpdateCriteria
	 * @return
	 * @throws EBXException
	 */
	public static Mapping findMapping(LocateService locateService, MassUpdateCriteria massUpdateCriteria) throws EBXException{
		List status = new ArrayList();
		status.add(DomainConstants.UNMAPPED_STATUS);
		Mapping mapping = null;
		Mapping ketMapping = getKeyMapping(massUpdateCriteria);
		List mappingList = (ArrayList)locateService.getRecords(ketMapping, null, null, -1, 21, null);
		if((null != mappingList) && mappingList.size() > 0){
			mapping = (Mapping)mappingList.get(0);
		}else{
			List unMappedList = (ArrayList)locateService.getRecords(ketMapping, status, null, -1, 21, null);
			if((null != unMappedList) && unMappedList.size() > 0){
				mapping = (Mapping)unMappedList.get(0);
			}else{
				throw new EBXException("Mapping not found");
			}
		}
		addMissingValues(mapping);
		removeEmptyValues(mapping.getEb03());
		if(null == mapping.getInTempTable()){
			mapping.setInTempTable("N");
		}
		updateUser(mapping, massUpdateCriteria);
		return mapping;

	}
	
	/**
	 * This method find mappings for bx mass update.
	 * @param variableMappingService
	 * @param massUpdateCriteria
	 * @return
	 * @throws EBXException
	 */
	public static Mapping findMapping(VariableMappingService variableMappingService, MassUpdateCriteria massUpdateCriteria) throws EBXException{
		Mapping mapping = null;
		try{
			mapping = variableMappingService.retreiveMapping(massUpdateCriteria.getVariableId().trim(), Integer.valueOf(21));
		}catch(Exception e){
			
		}
		if(null == mapping){
			Variable variable = new Variable();
			variable.setVariableId(massUpdateCriteria.getVariableId().trim());
			List unMappedList = variableMappingService.retrieveVariableInfo(variable);
			if((null != unMappedList) && unMappedList.size() > 0){
				Variable var = (Variable)unMappedList.get(0);
				mapping = new Mapping();				
				mapping.setVariableMappingStatus(DomainConstants.UNMAPPED_STATUS);
				mergeVariableDetailsToMapping(mapping, var);
			}else{
				throw new EBXException("Mapping not found");
			}
		}
		addMissingValues(mapping);
		removeEmptyValues(mapping.getEb03());
		addMissingVariableSepecificValues(mapping);
		mergeVariableDetails(mapping);
		updateUser(mapping, massUpdateCriteria);
		return mapping;

	}
	
	/**
	 * This method set variable info in mapping object, 
	 * @param mapping
	 * @param variable
	 */
	private static void mergeVariableDetailsToMapping(Mapping mapping, Variable variable){
		mapping.setVariable(variable);
	}
	
	/**
	 * This method will set the default values of variable if that is null, other wise case null pointer exception.
	 * @param mapping
	 */
	private static void addMissingVariableSepecificValues(Mapping mapping){
		if(null == mapping){
			return;
		}
		if(null == mapping.getInTempTable()){
			mapping.setInTempTable("N");
		}
		if(null == mapping.getSensitiveBenefitIndicator()){
			mapping.setSensitiveBenefitIndicator("N");
		}
		
		if(null == mapping.getMsg_type_required()){
			mapping.setMsg_type_required("N");
		}
		
	}
	
	/**
	 * This method sets empty description if its null other wise cause null pointer exception, and sets variable format thats needed for validation.
	 * @param mapping
	 * @throws EBXException
	 */
	private static void mergeVariableDetails(Mapping mapping) throws EBXException{
		if(null == mapping || null == mapping.getVariable()){
			throw new EBXException("Variable Mapping not found");
		}
		if(null != mapping.getVariableList() && mapping.getVariableList().size() > 0){
			Variable variable =  (Variable)mapping.getVariableList().get(0);
			mapping.getVariable().setVariableFormat(variable.getVariableFormat());
			mapping.getVariable().setIsgCatagory(variable.getIsgCatagory());
			mapping.getVariable().setLgCatagory(variable.getLgCatagory());
			mapping.getVariable().setVariableSystem(variable.getVariableSystem());
			if (null != variable && null != variable.getPva()) {
				mapping.getVariable().setPva(variable.getPva());
			}
		}
		if(null == mapping.getVariable().getDescription()){
			mapping.getVariable().setDescription("");
		}
	}
	
	/**
	 * this method find and merge for mass update.
	 * @param locateService
	 * @param massUpdateCriteria
	 * @return
	 * @throws EBXException
	 */
	public static Mapping findAndMergeMapping(LocateService locateService, MassUpdateCriteria massUpdateCriteria) throws EBXException{
		
		return mergeMapping(findMapping(locateService, massUpdateCriteria), massUpdateCriteria);

	}
	
	/**
	 * this method find and merge for mass update.
	 * @param variableMappingService
	 * @param massUpdateCriteria
	 * @return
	 * @throws EBXException
	 */
	public static Mapping findAndMergeMapping(VariableMappingService variableMappingService, MassUpdateCriteria massUpdateCriteria) throws EBXException{
		
		return mergeMapping(findMapping(variableMappingService, massUpdateCriteria), massUpdateCriteria);

	}
	
	/**
	 * This method sets sps id and rule id in mapping which is need to locate mapping.
	 * @param massUpdateCriteria
	 * @return
	 */
	private static Mapping getKeyMapping(MassUpdateCriteria massUpdateCriteria){
		Mapping mapping = new Mapping();
		if(BxUtil.hasText(massUpdateCriteria.getSpsId())){
			mapping.setSpsId(new SPSId());
			mapping.getSpsId().setSpsId(massUpdateCriteria.getSpsId().trim());
			List spsDetailsList = new ArrayList();
			SPSDetail spsDetail = new SPSDetail();
			spsDetail.setSpsDataType("");
			spsDetail.setSpsPVA("");
			spsDetail.setSpsType("");
			spsDetailsList.add(spsDetail);
			mapping.getSpsId().setSpsDetail(spsDetailsList);
			
		}
		
		if(BxUtil.hasText(massUpdateCriteria.getRuleId())){
			mapping.setRule(new Rule());
			mapping.getRule().setHeaderRuleId(massUpdateCriteria.getRuleId().trim());
		}		
		
		
		return mapping;
	}
	
	/**
	 *  merge massupdate values to mapping.
	 * @param mapping
	 * @param massUpdateCriteria
	 * @return
	 * @throws EBXException
	 */
	public static Mapping mergeMapping(Mapping mapping, MassUpdateCriteria massUpdateCriteria) throws EBXException{
		if(BxUtil.hasText(massUpdateCriteria.getVariableId())){
			updateEB03Value(mapping, massUpdateCriteria);
			updateEB0169(mapping, massUpdateCriteria);
			updateEB02(mapping, massUpdateCriteria);
			updateHSDValues(mapping, massUpdateCriteria);
			updateNoteType(mapping, massUpdateCriteria);
			updateII02(mapping, massUpdateCriteria);
			updateMessage(mapping,massUpdateCriteria);
		}else if(BxUtil.hasText(massUpdateCriteria.getRuleId()) && BxUtil.hasText(massUpdateCriteria.getSpsId())){
			updateNoteType(mapping, massUpdateCriteria);
			updateMessage(mapping,massUpdateCriteria);
		}else if(BxUtil.hasText(massUpdateCriteria.getSpsId())){
			updateEB0169(mapping, massUpdateCriteria);
			updateEB02(mapping, massUpdateCriteria);
			updateHSDValues(mapping, massUpdateCriteria);
		}else if(BxUtil.hasText(massUpdateCriteria.getRuleId())){
			updateEB03Value(mapping, massUpdateCriteria);
		}
		updateUser(mapping, massUpdateCriteria);
		return mapping;
	}

	private static void updateMessage(Mapping mapping,
			MassUpdateCriteria massUpdateCriteria) throws EBXException {
		if(null != massUpdateCriteria.getNvMessage()){
			//Updated as part of SSCR 19537
			String updatedMessage = updateHippaSegmentValuesForMessage(mapping.getMessage(),DomainConstants.MESSAGE, massUpdateCriteria, massUpdateCriteria.getCfMessage(), massUpdateCriteria.getNvMessage());
			if(null != mapping.getEb03() && null != mapping.getEb03().getEb03Association() ){
				List<EB03Association> eb03AssnList = mapping.getEb03().getEb03Association();
				for(EB03Association eb03AssnObj : eb03AssnList){
					if(null != eb03AssnObj.getEb03()){
						eb03AssnObj.setMessage(updatedMessage);
					}
					
				}
				
			}
			mapping.setMessage(updatedMessage);
		}
		
	}

	private static String updateHippaSegmentValuesForMessage(String message, String hippaSegmentName,MassUpdateCriteria muc, 
			String[] criteriaFields, String newValue) throws EBXException {
		String[] spclChars = {"*","^","~","{","}","#","_",":",";","?","!","[","]","<",">","-"};
		if(criteriaFields.length == 0 && newValue.trim().equals("")){
			updateMassUpdateCritieria(muc, hippaSegmentName, "");
			return muc.getUpdatedMessage().toUpperCase();
		}
		if(criteriaFields.length > 0 && newValue.trim().equals("")){
			if(null == message){
				throw new EBXException("Criteria field(s) not found");
//				return getEmptyHippaSegment(hippaSegment, hippaSegmentName);
			}
			String currentValue = message;
			if(!BxUtil.hasText(currentValue)){
				throw new EBXException("Criteria field(s) not found");
//				return getEmptyHippaSegment(hippaSegment, hippaSegmentName);
			}
			for(int i =0 ; i< criteriaFields.length ; i++){
				if(currentValue.trim().equalsIgnoreCase(criteriaFields[i].trim())){
					updateMassUpdateCritieria(muc, hippaSegmentName, "");
					return muc.getUpdatedMessage().toUpperCase();
				}				
			}
			
		}
		if(BxUtil.hasText(newValue)){
			if(newValue.length() > 75){
				throw new EBXException("The message can not be more than 75 characters");
			}
			for(String str:spclChars){
				if(newValue.contains(str)){
					throw new EBXException("* ^ ~ { } # – _ : ; ? ! [ ] < > are invalid characters for Message Text");
				}
			}
		}

		//Update EB/HS Code DB field with value from New Value in this case Criteria Field is empty.
		if(criteriaFields.length == 0 && BxUtil.hasText(newValue)){
			updateMassUpdateCritieria(muc, hippaSegmentName, newValue);
			return muc.getUpdatedMessage().toUpperCase();
		}
		//Update EB/HS Code DB field with value from New Value for those fields where the current value in DB falls in values mentioned in Criteria Field
		if(criteriaFields.length > 0 && BxUtil.hasText(newValue)){
			if(null == message){
				return message;
			}
			String currentValue = message;
			if(!BxUtil.hasText(currentValue)){
				throw new EBXException("Criteria field(s) not found");
//				return getEmptyHippaSegment(hippaSegment, hippaSegmentName);
			}
			
			for(int i =0 ; i< criteriaFields.length ; i++){
				if(currentValue.trim().equalsIgnoreCase(criteriaFields[i].trim())){
					updateMassUpdateCritieria(muc, hippaSegmentName, newValue);
					return muc.getUpdatedMessage().toUpperCase();
				}				
			}
			
		}
		throw new EBXException("Criteria field(s) not found");
	}

	/**
	 * updates user information.
	 * @param mapping
	 * @param muc
	 */
	private static void updateUser(Mapping mapping, MassUpdateCriteria muc){
		if(null == mapping.getUser()){
			mapping.setUser(new User());
		}
		if(!BxUtil.hasText(mapping.getUser().getCreatedUserName())){
			mapping.getUser().setCreatedUserName(muc.getUserId());
		}
		mapping.getUser().setLastUpdatedUserName(muc.getUserId());
	}
	
	/**
	 * merges EB01, EB06, EB09
	 * @param mapping
	 * @param muc
	 * @throws EBXException
	 */
	private static void updateEB0169(Mapping mapping, MassUpdateCriteria muc)throws EBXException{
		if(null != muc.getNvEb01()){
			mapping.setEb01(updateHippaSegmentValues(mapping.getEb01(),DomainConstants.EB01_NAME, muc, muc.getCfEb01(), muc.getNvEb01()));
		}
		if(null != muc.getNvEb06()){
			mapping.setEb06(updateHippaSegmentValues(mapping.getEb06(),DomainConstants.EB06_NAME, muc, muc.getCfEb06(), muc.getNvEb06()));
		}
		if(null != muc.getNvEb09()){
			mapping.setEb09(updateHippaSegmentValues(mapping.getEb09(),DomainConstants.EB09_NAME, muc, muc.getCfEb09(), muc.getNvEb09()));
		}
		
	}
	
	/**
	 * merges EB02
	 * @param mapping
	 * @param muc
	 * @throws EBXException
	 */
	private static void updateEB02(Mapping mapping, MassUpdateCriteria muc) throws EBXException{
		if(null != muc.getNvEb02()){
			mapping.setEb02(updateHippaSegmentValues(mapping.getEb02(),DomainConstants.EB02_NAME, muc, muc.getCfEb02(), muc.getNvEb02()));
		}
	}
	
	/**
	 * merges III02
	 * @param mapping
	 * @param muc
	 * @throws EBXException
	 */
	private static void updateII02(Mapping mapping, MassUpdateCriteria muc) throws EBXException{
		if(null != muc.getNvIII02()){
			//Added as part of SSCR 19537
			HippaSegment updatedIii02 = updateHippaSegmentValues(mapping.getIi02(),DomainConstants.III02_NAME, muc, muc.getCfIII02(), muc.getNvIII02());
			if(null != mapping.getEb03() && null != mapping.getEb03().getEb03Association() ){
				List<EB03Association> eb03AssnList = mapping.getEb03().getEb03Association();
				for(EB03Association eb03AssnObj : eb03AssnList){
					if(null != eb03AssnObj.getEb03()){
						eb03AssnObj.setIii02List(null != updatedIii02 && null != updatedIii02.getHippaCodeSelectedValues() ? updatedIii02.getHippaCodeSelectedValues() : new ArrayList<HippaCodeValue>());
					}
					
				}
			}
			mapping.setIi02(updatedIii02);
		}
	}
	
	/**
	 * merges Note Type
	 * @param mapping
	 * @param muc
	 * @throws EBXException
	 */
	private static void updateNoteType(Mapping mapping, MassUpdateCriteria muc) throws EBXException{
		if(null != muc.getNvNoteType()){
			// changed for Reference Data Values
			
			//Added as part of SSCR 19537
			HippaSegment updatedNoteType = updateHippaSegmentValues(mapping.getNoteTypeCode(), DomainConstants.NOTETYPECODE, muc,muc.getCfNoteType(), muc.getNvNoteType());
			updatedNoteType.setDescription(DomainConstants.NOTE_TYPE_CODE);
			updatedNoteType.setName(DomainConstants.NOTE_TYPE_CODE);
			if(null != mapping.getEb03() && null != mapping.getEb03().getEb03Association() ){
				List<EB03Association> eb03AssnList = mapping.getEb03().getEb03Association();
				for(EB03Association eb03AssnObj : eb03AssnList){
					if(null != eb03AssnObj.getEb03()){
						eb03AssnObj.setNoteType(null != updatedNoteType && null != updatedNoteType.getHippaCodeSelectedValues() && !updatedNoteType.getHippaCodeSelectedValues().isEmpty()
								? (HippaCodeValue)updatedNoteType.getHippaCodeSelectedValues().get(0) : new HippaCodeValue());
					}
					
				}
			}
			mapping.setNoteTypeCode(updatedNoteType);
		}
	}
	
	/**
	 * merges HSD01 to HSD08
	 * @param mapping
	 * @param muc
	 * @throws EBXException
	 */
	private static void updateHSDValues(Mapping mapping, MassUpdateCriteria muc) throws EBXException{
		if(null != muc.getNvHsd01()){
			mapping.setHsd01(updateHippaSegmentValues(mapping.getHsd01(),DomainConstants.HSD01_NAME, muc, muc.getCfHsd01(), muc.getNvHsd01()));
		}
		if(null != muc.getNvHsd02()){
			mapping.setHsd02(updateHippaSegmentValues(mapping.getHsd02(),DomainConstants.HSD02_NAME, muc, muc.getCfHsd02(), muc.getNvHsd02()));
		}
		if(null != muc.getNvHsd03()){
			mapping.setHsd03(updateHippaSegmentValues(mapping.getHsd03(),DomainConstants.HSD03_NAME, muc, muc.getCfHsd03(), muc.getNvHsd03()));
		}
		if(null != muc.getNvHsd04()){
			mapping.setHsd04(updateHippaSegmentValues(mapping.getHsd04(),DomainConstants.HSD04_NAME, muc, muc.getCfHsd04(), muc.getNvHsd04()));
		}
		if(null != muc.getNvHsd05()){
			mapping.setHsd05(updateHippaSegmentValues(mapping.getHsd05(),DomainConstants.HSD05_NAME, muc, muc.getCfHsd05(), muc.getNvHsd05()));
		}
		if(null != muc.getNvHsd06()){
			mapping.setHsd06(updateHippaSegmentValues(mapping.getHsd06(),DomainConstants.HSD06_NAME, muc, muc.getCfHsd06(), muc.getNvHsd06()));
		}
		if(null != muc.getNvHsd07()){
			mapping.setHsd07(updateHippaSegmentValues(mapping.getHsd07(),DomainConstants.HSD07_NAME, muc, muc.getCfHsd07(), muc.getNvHsd07()));
		}
		if(null != muc.getNvHsd08()){
			mapping.setHsd08(updateHippaSegmentValues(mapping.getHsd08(),DomainConstants.HSD08_NAME, muc, muc.getCfHsd08(), muc.getNvHsd08()));
		}
		
	}
	
	/**
	 * This updates the updated value field in mass update criteria. this needs for the xl generation.
	 * @param muc
	 * @param hippaSegmentName
	 * @param updatedValue
	 */
	private static void updateMassUpdateCritieria(MassUpdateCriteria muc, String hippaSegmentName,String updatedValue){
		if(DomainConstants.EB01_NAME.equals(hippaSegmentName)){
			muc.setUpdatedEb01(updatedValue);
		}else if(DomainConstants.EB02_NAME.equals(hippaSegmentName)){
			muc.setUpdatedEb02(updatedValue);
		}else if(DomainConstants.EB03_NAME.equals(hippaSegmentName)){
			muc.setUpdatedEb03(updatedValue);
		}else if(DomainConstants.EB06_NAME.equals(hippaSegmentName)){
			muc.setUpdatedEb06(updatedValue);
		}else if(DomainConstants.EB09_NAME.equals(hippaSegmentName)){
			muc.setUpdatedEb09(updatedValue);
		}else if(DomainConstants.HSD01_NAME.equals(hippaSegmentName)){
			muc.setUpdatedHsd01(updatedValue);
		}else if(DomainConstants.HSD02_NAME.equals(hippaSegmentName)){
			muc.setUpdatedHsd02(updatedValue);
		}else if(DomainConstants.HSD03_NAME.equals(hippaSegmentName)){
			muc.setUpdatedHsd03(updatedValue);
		}else if(DomainConstants.HSD04_NAME.equals(hippaSegmentName)){
			muc.setUpdatedHsd04(updatedValue);
		}else if(DomainConstants.HSD05_NAME.equals(hippaSegmentName)){
			muc.setUpdatedHsd05(updatedValue);
		}else if(DomainConstants.HSD06_NAME.equals(hippaSegmentName)){
			muc.setUpdatedHsd06(updatedValue);
		}else if(DomainConstants.HSD07_NAME.equals(hippaSegmentName)){
			muc.setUpdatedHsd07(updatedValue);
		}else if(DomainConstants.HSD08_NAME.equals(hippaSegmentName)){
			muc.setUpdatedHsd08(updatedValue);
		}else if(DomainConstants.III02_NAME.equals(hippaSegmentName)){
			muc.setUpdatedIII02(updatedValue);
		}else if(DomainConstants.NOTETYPECODE.equals(hippaSegmentName)){
			muc.setUpdatedNoteType(updatedValue);
		}else if(DomainConstants.MESSAGE.equals(hippaSegmentName)){
			muc.setUpdatedMessage(updatedValue);
		}
	}
	
	/**
	 *  update hippa segment according to the massupdate criteria.
	 * @param hippaSegment
	 * @param hippaSegmentName
	 * @param muc
	 * @param criteriaFields
	 * @param newValue
	 * @return
	 * @throws EBXException
	 */
	private static HippaSegment updateHippaSegmentValues(HippaSegment hippaSegment, String hippaSegmentName,MassUpdateCriteria muc, 
			String[] criteriaFields, String newValue) throws EBXException{
		//Clear EB/HS Code DB values when New Value  and Criteria Field is empty.
		if(criteriaFields.length == 0 && newValue.trim().equals("")){
			updateMassUpdateCritieria(muc, hippaSegmentName, "");
			markHippaCodeForDelete(hippaSegment.getHippaCodeSelectedValues(), newValue);
			return hippaSegment;
		}
		//Clear EB/HS Code DB values for those where the current value in DB falls in values mentioned in Criteria Field when New Value is empty.
		if(criteriaFields.length > 0 && newValue.trim().equals("")){
			if(null == hippaSegment || null == hippaSegment.getHippaCodeSelectedValues() || hippaSegment.getHippaCodeSelectedValues().size() == 0){
				throw new EBXException("Criteria field(s) not found");
//				return getEmptyHippaSegment(hippaSegment, hippaSegmentName);
			}
			String currentValue = ((HippaCodeValue)(hippaSegment.getHippaCodeSelectedValues().get(0))).getValue();
			if(!BxUtil.hasText(currentValue)){
				throw new EBXException("Criteria field(s) not found");
//				return getEmptyHippaSegment(hippaSegment, hippaSegmentName);
			}
			for(int i =0 ; i< criteriaFields.length ; i++){
				if(currentValue.trim().equals(criteriaFields[i].trim())){
					updateMassUpdateCritieria(muc, hippaSegmentName, "");
					markHippaCodeForDelete(hippaSegment.getHippaCodeSelectedValues(), newValue);
					return hippaSegment;
				}				
			}
			
		}
		//Update EB/HS Code DB field with value from New Value in this case Criteria Field is empty.
		if(criteriaFields.length == 0 && BxUtil.hasText(newValue)){
			updateMassUpdateCritieria(muc, hippaSegmentName, newValue);
			return setHippaSegmentValue(hippaSegment, hippaSegmentName, newValue);
		}
		//Update EB/HS Code DB field with value from New Value for those fields where the current value in DB falls in values mentioned in Criteria Field
		if(criteriaFields.length > 0 && BxUtil.hasText(newValue)){
			if(null == hippaSegment || null == hippaSegment.getHippaCodeSelectedValues() || hippaSegment.getHippaCodeSelectedValues().size() == 0){
				return hippaSegment;
			}
			String currentValue = ((HippaCodeValue)(hippaSegment.getHippaCodeSelectedValues().get(0))).getValue();
			if(!BxUtil.hasText(currentValue)){
				throw new EBXException("Criteria field(s) not found");
//				return getEmptyHippaSegment(hippaSegment, hippaSegmentName);
			}
			for(int i =0 ; i< criteriaFields.length ; i++){
				if(currentValue.trim().equals(criteriaFields[i].trim())){
					updateMassUpdateCritieria(muc, hippaSegmentName, newValue);
					return setHippaSegmentValue(hippaSegment, hippaSegmentName, newValue);
				}				
			}
			
		}
		throw new EBXException("Criteria field(s) not found");
	}
	
	/**
	 * this sets value to hippa segment
	 * @param hippaSegment
	 * @param hippaSegmentName
	 * @param value
	 * @return
	 */
	private static HippaSegment setHippaSegmentValue(HippaSegment hippaSegment, String hippaSegmentName, String value){
		
		if(null == hippaSegment){
			hippaSegment = new HippaSegment();
			hippaSegment.setHippaCodeSelectedValues(new ArrayList());
		}
		
		if(null == hippaSegment.getHippaCodeSelectedValues()){
			hippaSegment.setHippaCodeSelectedValues(new ArrayList());
		}
		
		if(hippaSegment.getHippaCodeSelectedValues().size() == 0){
			HippaCodeValue hippaCodeValue = new HippaCodeValue();
			hippaCodeValue.setValue(value.trim());
			hippaSegment.getHippaCodeSelectedValues().add(hippaCodeValue);	
		}else{
			((HippaCodeValue)(hippaSegment.getHippaCodeSelectedValues().get(0))).setValue(value.trim());
		}
		hippaSegment.setName(hippaSegmentName);
		return hippaSegment;
	}
	
	/**
	 * gets hippasegment , if existing null retuns a new one.
	 * @param hippaSegment
	 * @param hippaSegmentName
	 * @return
	 */
	private static HippaSegment getHippSegment(HippaSegment hippaSegment, String hippaSegmentName){
		if(null == hippaSegment){
			hippaSegment = new HippaSegment();
			hippaSegment.setName(hippaSegmentName);
			hippaSegment.setHippaCodeSelectedValues(new ArrayList());
		}
		
		if(null == hippaSegment.getHippaCodeSelectedValues()){
			hippaSegment.setHippaCodeSelectedValues(new ArrayList());
		}
		return hippaSegment;
	}
	
	/**
	 * splits commaseperated values.
	 * @param hippaSegment
	 * @return
	 */
	private static String getCommaSeperatedValue(HippaSegment hippaSegment){
		StringBuffer value = new StringBuffer();
		if(null == hippaSegment || null == hippaSegment.getHippaCodeSelectedValues() || hippaSegment.getHippaCodeSelectedValues().size() == 0){
			return "";
		}
		Iterator itr = hippaSegment.getHippaCodeSelectedValues().iterator();
		while(itr.hasNext()){
			HippaCodeValue hippaCodeValue = (HippaCodeValue)itr.next();
			if(BxUtil.hasText(hippaCodeValue.getValue())){
				value.append(hippaCodeValue.getValue());
				if(itr.hasNext()){
					value.append(", ");
				}
			}
		}
		return value.toString();
	}
	
	private static HippaCodeValue findHippaCodeValue(String value,List hippaSegments){
		if(!BxUtil.hasText(value) || null == hippaSegments 
				){
			return null;
		}
		Iterator itr = hippaSegments.iterator();
		while(itr.hasNext()){
			HippaCodeValue hippaCodeValue = (HippaCodeValue)itr.next();
			if(null == hippaCodeValue || !BxUtil.hasText(hippaCodeValue.getValue())){
				continue;
			}
			
			if(value.trim().equals(hippaCodeValue.getValue().trim())){
				return hippaCodeValue;
			}
		}
		return null;
	}

	/**
	 * remove empty values from hippasegment.
	 * @param hippaSegment
	 */
	public static void removeEmptyValues(HippaSegment hippaSegment){
		List selectedValues = hippaSegment.getHippaCodeSelectedValues();
		if(null == selectedValues){
			return;
		}
		List removableList = new ArrayList();
		Iterator itr = selectedValues.iterator();
		while(itr.hasNext()){
			HippaCodeValue hippaCodeValue = (HippaCodeValue) itr.next();
			if(null == hippaCodeValue || !BxUtil.hasText(hippaCodeValue.getValue())){
				removableList.add(hippaCodeValue);
			}
		}
		
		selectedValues.removeAll(removableList);
	}
	
	/**
	 * updates EB03 values for mass update criteria.
	 * @param mapping
	 * @param muc
	 * @throws EBXException
	 */
	private static void updateEB03Value(Mapping mapping, MassUpdateCriteria muc)
			throws EBXException {

		if (null == muc.getNvEb03() || null == muc.getCfEb03()) {
			return;
		}
		HippaSegment eb03HippaSegment = getHippSegment(mapping.getEb03(),
				DomainConstants.EB03_NAME);

		removeEmptyValues(eb03HippaSegment);
		// For SSCR 19537
		EB03Association eb03AssnCopyObject = (null != eb03HippaSegment
				&& null != eb03HippaSegment.getEb03Association()
				&& !eb03HippaSegment.getEb03Association().isEmpty()
				&& null != eb03HippaSegment.getEb03Association().get(0) ? eb03HippaSegment
				.getEb03Association().get(0)
				: new EB03Association());
		EB03Association eb03AssnObj = new EB03Association();
		createCopyObject(eb03AssnObj, eb03AssnCopyObject);

		if (muc.getNvEb03().length == 0 && muc.getCfEb03().length == 0) {
			// 1) If CF AND NV is blank.
			markHippaCodeForDelete(eb03HippaSegment, muc.getCfEb03());
		} else if (muc.getNvEb03().length > 0 && muc.getCfEb03().length == 0) {
			// 1) If CF is blank and NV consists of 30, 33 - Then add 30 and 33
			// to all selected records.
			for (int i = 0; i < muc.getNvEb03().length; i++) {
				HippaCodeValue hippaCodeValue = new HippaCodeValue();
				hippaCodeValue.setValue(muc.getNvEb03()[i].trim());
				eb03HippaSegment.getHippaCodeSelectedValues().add(
						hippaCodeValue);

				// Added as part of SSCR 19537
				eb03AssnObj.setEb03(hippaCodeValue);
				eb03HippaSegment.getEb03Association().add(eb03AssnObj);
				// Ends here
			}
		} else if (muc.getNvEb03().length == 0 && muc.getCfEb03().length > 0) {
			// 2) If CF has 30, 33 and NV is blank - Remove 30 and 33 from all
			// the selected records.
			List removingList = new ArrayList();

			removingList = markHippaCodeForDelete(eb03HippaSegment, muc
					.getCfEb03());
			if (removingList.size() == 0) {
				throw new EBXException("Criteria field(s) not found");
			}
		} else if (muc.getNvEb03().length > 0 && muc.getCfEb03().length > 0) {
			// 3) If CF has 30, 33 and NV has 88 - Replace 30 and 33 with 88.
			// This means, one mapping need not contain 30 and 33, instead it
			// can either 30 or 33. And all these will be updated with 88.
			// [Correction from previous communication]
			// 4) If CF has 30 and NV has 81 and 88 - Replace 30 with 81 and 88.
			// This mean for all mappings with a value 30 - Remove 30 and add
			// both 81 and 88. [Correction from previous communication]
			// 5) System must not allow multiple EB03 in CF and NV at sametime.

			if (!(muc.getNvEb03().length > 1 && muc.getCfEb03().length > 1)) {
				List hippaCodeMarkForDelete = markHippaCodeForDelete(
						eb03HippaSegment, muc.getCfEb03());

				if (hippaCodeMarkForDelete.size() > 0) {
					//Here delete--add del call if doesnt wrk for add
					for (int i = 0; i < muc.getNvEb03().length; i++) {
						HippaCodeValue hippaCodeValue = new HippaCodeValue();
						hippaCodeValue.setValue(muc.getNvEb03()[i].trim());
						eb03HippaSegment.getHippaCodeSelectedValues().add(
								hippaCodeValue);
						// Added as part of SSCR 19537
						eb03AssnObj.setEb03(hippaCodeValue);
						eb03HippaSegment.getEb03Association().add(eb03AssnObj);
						// Ends here
					}
				} else {
					throw new EBXException("Criteria field(s) not found");
				}
			}

		}

		eb03HippaSegment
				.setHippaCodeSelectedValues(removeDuplicateHippaCodes(eb03HippaSegment
						.getHippaCodeSelectedValues()));
		//SSCR 19537
		removeDuplicateFromAssnObject(eb03HippaSegment);
		removeEmptyAssnObject(eb03HippaSegment);
		
		updateMassUpdateCritieria(muc, DomainConstants.EB03_NAME,
				getCommaSeperatedValue(eb03HippaSegment));
		mapping.setEb03(eb03HippaSegment);

	}
	
	private static List markHippaCodeForDelete(HippaSegment eB03HippaSegment, String[] oldEB03s){
		List hippaCodesMarkedAsDelete = null;
		if(null != eB03HippaSegment){
			List<EB03Association> listOfEb03Association = eB03HippaSegment.getEb03Association();
			List<HippaCodeValue> listOfEb03HippaCodes = eB03HippaSegment.getHippaCodeSelectedValues();
			
			
			if(listOfEb03HippaCodes != null && listOfEb03HippaCodes.size() > 0){
				hippaCodesMarkedAsDelete = new ArrayList();
				HippaCodeValue hippaCodeValue = null;
				EB03Association eB03Assn = null;
				if(!(oldEB03s != null && oldEB03s.length >0)){
					Iterator itr = listOfEb03HippaCodes.iterator();
					while(itr.hasNext()){
						hippaCodeValue = (HippaCodeValue) itr.next();
						hippaCodeValue.setValue("");
					}
					// For SSCR 19537
					Iterator itrForEB03Assn = listOfEb03Association.iterator();
					while(itrForEB03Assn.hasNext()){
						eB03Assn = (EB03Association) itrForEB03Assn.next();
						eB03Assn.getEb03().setValue("");
						hippaCodesMarkedAsDelete.add(hippaCodeValue);
					}//Ends
					
				} else{
					
					for(int i = 0; i < oldEB03s.length; i++){
						hippaCodeValue = findHippaCodeValue(oldEB03s[i], listOfEb03HippaCodes);
						if(hippaCodeValue != null){
							hippaCodeValue.setValue("");
							
						}
						hippaCodesMarkedAsDelete.add(hippaCodeValue);
					}
				}
			}
		}
		return hippaCodesMarkedAsDelete;
	}
	
	private static List markHippaCodeForDelete(List hippaCodeList, String oldEB03s){
		List hippaCodesMarkedAsDelete = null;
		if(hippaCodeList != null && hippaCodeList.size() > 0){
			hippaCodesMarkedAsDelete = new ArrayList();
			HippaCodeValue hippaCodeValue = null;
			if(!(oldEB03s != null && oldEB03s.trim().length() > 0)){
				Iterator itr = hippaCodeList.iterator();
				while(itr.hasNext()){
					hippaCodeValue = (HippaCodeValue) itr.next();
					hippaCodeValue.setValue("");
					hippaCodesMarkedAsDelete.add(hippaCodeValue);
				}
				
			} else{
					hippaCodeValue = findHippaCodeValue(oldEB03s, hippaCodeList);
					if(hippaCodeValue != null){
						hippaCodeValue.setValue("");
						hippaCodesMarkedAsDelete.add(hippaCodeValue);
					}
			}
		}
		return hippaCodesMarkedAsDelete;
	}
	
	
	private static List removeDuplicateHippaCodes(List hippaCodes){
		Hashtable hippaCodeTable = null;
		List hippaCodeList = null;
		if(null != hippaCodes && hippaCodes.size() > 0) {
			hippaCodeList = new ArrayList();
			hippaCodeTable = new Hashtable();
			HippaCodeValue hippaCodeValue = null;
			for(int i =0; i < hippaCodes.size(); i++){
				hippaCodeValue = (HippaCodeValue)hippaCodes.get(i);
				if(hippaCodeValue.getValue() != null && hippaCodeValue.getValue().trim().length() == 0){
					hippaCodeList.add(hippaCodeValue);
				} else if(hippaCodeTable.get(hippaCodeValue.getValue().toUpperCase()) == null){
					hippaCodeTable.put(hippaCodeValue.getValue().toUpperCase(), hippaCodeValue);
				}
			}
			hippaCodeList.addAll(new ArrayList(hippaCodeTable.values()));
			return hippaCodeList;
		} else {
			return null;
		}
	}
	
	/**
	 * Adds missing values which are mandatory for save and validation.
	 * @param mapping
	 */
	private static void addMissingValues(Mapping mapping){
		mapping.setEb01(setHippaSegmentNameAndValue(mapping.getEb01(), DomainConstants.EB01_NAME));
		mapping.setEb02(setHippaSegmentNameAndValue(mapping.getEb02(), DomainConstants.EB02_NAME));
		mapping.setEb03(setHippaSegmentNameAndValue(mapping.getEb03(), DomainConstants.EB03_NAME));
		mapping.setEb06(setHippaSegmentNameAndValue(mapping.getEb06(), DomainConstants.EB06_NAME));
		mapping.setEb09(setHippaSegmentNameAndValue(mapping.getEb09(), DomainConstants.EB09_NAME));
		mapping.setHsd01(setHippaSegmentNameAndValue(mapping.getHsd01(), DomainConstants.HSD01_NAME));
		mapping.setHsd02(setHippaSegmentNameAndValue(mapping.getHsd02(), DomainConstants.HSD02_NAME));
		mapping.setHsd03(setHippaSegmentNameAndValue(mapping.getHsd03(), DomainConstants.HSD03_NAME));
		mapping.setHsd04(setHippaSegmentNameAndValue(mapping.getHsd04(), DomainConstants.HSD04_NAME));
		mapping.setHsd05(setHippaSegmentNameAndValue(mapping.getHsd05(), DomainConstants.HSD05_NAME));
		mapping.setHsd06(setHippaSegmentNameAndValue(mapping.getHsd06(), DomainConstants.HSD06_NAME));
		mapping.setHsd07(setHippaSegmentNameAndValue(mapping.getHsd07(), DomainConstants.HSD07_NAME));
		mapping.setHsd08(setHippaSegmentNameAndValue(mapping.getHsd08(), DomainConstants.HSD08_NAME));
		mapping.setIi02(setHippaSegmentNameAndValue(mapping.getIi02(), DomainConstants.III02_NAME));
		// changed for Reference Data Values
		mapping.setNoteTypeCode(setHippaSegmentNameAndValue(mapping.getNoteTypeCode(), DomainConstants.NOTE_TYPE_CODE));
		mapping.setAccum(setHippaSegmentNameAndValue(mapping.getAccum(), DomainConstants.ACCUM_NAME));
		mapping.setUtilizationMgmntRule(setHippaSegmentNameAndValue(mapping.getUtilizationMgmntRule(), DomainConstants.UMRULE_NAME));
		if (null != mapping.getSpsId() && null != mapping.getSpsId().getSpsDetail()
				&& (mapping.getSpsId().getSpsDetail().isEmpty())) {
			List spsDetails = new ArrayList();
			SPSDetail spsDetail = new SPSDetail();
			spsDetail.setSpsDataType("");
			spsDetail.setSpsPVA("");
			spsDetail.setSpsType("");
			spsDetails.add(spsDetail);
			mapping.getSpsId().setSpsDetail(spsDetails);
			
		}
	}
	
	/**
	 * sets hippasegment name and value
	 * @param hippaSegment
	 * @param name
	 * @return
	 */
	private static HippaSegment setHippaSegmentNameAndValue(HippaSegment hippaSegment, String name){
		if(null == hippaSegment){
			hippaSegment = new HippaSegment();;
		}
		if(!BxUtil.hasText(hippaSegment.getName())){
			hippaSegment.setName(name);
		}
		if(null == hippaSegment.getHippaCodeSelectedValues()){
			hippaSegment.setHippaCodeSelectedValues(new ArrayList());
		}
		if(hippaSegment.getHippaCodeSelectedValues().size() == 0){
			HippaCodeValue hippaCodeValue = new HippaCodeValue();
			hippaCodeValue.setValue("");
			hippaSegment.getHippaCodeSelectedValues().add(hippaCodeValue);
			return hippaSegment;
		}
		
		for(Iterator itr = hippaSegment.getHippaCodeSelectedValues().iterator(); itr.hasNext();){
			HippaCodeValue hippaCodeValue = (HippaCodeValue)itr.next();
			if(null == hippaCodeValue){
				continue;					
			}else{
				if(!BxUtil.hasText(hippaCodeValue.getValue())){
					hippaCodeValue.setValue("");
				}
			}
			
		}
		return hippaSegment;
	}

	/**
	 * checks whether the status is eligible for update.
	 * @param newStatus
	 * @param mapping
	 * @throws EBXException
	 */
	public static void checkStatusForStatusUpdate(String newStatus, Mapping mapping) throws EBXException{
		if(DomainConstants.STATUS_NOT_APPLICABLE.equals(newStatus.trim())){
			return;
		}
		if(DomainConstants.STATUS_SCHEDULED_TO_TEST.equals(newStatus.trim())){
			if(DomainConstants.STATUS_BEING_MODIFIED.equals(mapping.getVariableMappingStatus().trim()) ||
					DomainConstants.STATUS_BUILDING.equals(mapping.getVariableMappingStatus().trim())){
				return;
			}else{
				throw new EBXException(new StringBuffer("Mapping with status ").append(mapping.getVariableMappingStatus().trim())
						.append(" can not be send to test").toString());
			}
		}
		
		if(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION.equals(newStatus.trim())){
			if(DomainConstants.STATUS_BEING_MODIFIED.equals(mapping.getVariableMappingStatus().trim()) ||
					DomainConstants.STATUS_BUILDING.equals(mapping.getVariableMappingStatus().trim()) ||
					DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(mapping.getVariableMappingStatus().trim())){
				return;
			}else{
				throw new EBXException(new StringBuffer("Mapping with status ").append(mapping.getVariableMappingStatus().trim())
						.append(" can not be send to production").toString());
			}
		}
		
	}

	/**
	 * Method to get the coded value of the variable in the contract.
	 * @param contract
	 * @param variable
	 * @return
	 */
	public static String getVariableCodedValueInContract(ContractVO contract, String variable) {
		String variableValue = "";
		HashMap majorHeadingsMap = (HashMap) contract.getMajorHeadings();
		if (majorHeadingsMap.size() > 0) {
			Iterator iteratorMajor = majorHeadingsMap.keySet().iterator();
			while (iteratorMajor.hasNext()) {
				String keyMajor = (String) iteratorMajor.next();
				MajorHeadingsVO majorHeadingObj = (MajorHeadingsVO) majorHeadingsMap
				.get(keyMajor);
				// Get MinorHeadings Map
				HashMap minorHeadingsMap = (HashMap) majorHeadingObj
						.getMinorHeadings();
				Iterator iteratorMinor = minorHeadingsMap.keySet()
						.iterator();
				while (iteratorMinor.hasNext()) {
					String keyMinor = (String) iteratorMinor.next();
					MinorHeadingsVO minorHeadingObj = (MinorHeadingsVO) minorHeadingsMap
					.get(keyMinor);
					// get Mappings Map
					HashMap mappingsMap = (HashMap) minorHeadingObj
							.getMappings();
					Iterator iteratorMappings = mappingsMap.entrySet()
					.iterator();
					while (iteratorMappings.hasNext()) {
						ContractMappingVO mappingObj = (ContractMappingVO) ((Map.Entry) iteratorMappings
								.next()).getValue();
						if(null != mappingObj && null != mappingObj.getVariable()&& null != mappingObj.getVariable().getVariableId()) {
							if(variable.equalsIgnoreCase(mappingObj.getVariable().getVariableId())) {
								return mappingObj.getVariable().getVariableValue();
							}
						}
					}
				}
			}
		}
		return variableValue;
	}

	/**
	 * Method updates the Sensitive Benefit Indicator of the variable id/header rule id automatically ,based on the values of EB03
	 * If Variable/Header Rule contains a sensitive EB03, then indicator is set to Y
	 * If variable/Header Rule contains no sensitive EB03, then the indicator is set to N
	 * BXNI June 2012 Release
	 * 
	 * @param mapping
	 * @throws EBXException
	 */
	public static boolean updateSensitiveBenefitIndicator(Mapping mapping) throws EBXException{
		HippaSegment hippaSegmentEB03 = mapping.getEb03();
		boolean isSensitiveBenefitPresent = false;
		boolean isSensitiveBenefitIndicatorChanged = false;
		if (null != hippaSegmentEB03) {
			List eb03ValuesList = hippaSegmentEB03.getHippaCodeSelectedValues();
			if (null != eb03ValuesList && eb03ValuesList.size() > 0) {
				//Iterator eb03Iterator = eb03ValuesList.iterator();
				List sensitiveBenefitsList = SimulationResourceBundle.getResourceBundle(
						DomainConstants.SENSITIVE_EB03,
						DomainConstants.PROPERTY_FILE_NAME);
				for(int i = 0; i< eb03ValuesList.size();i++){
					HippaCodeValue hippaCodeValue= (HippaCodeValue)eb03ValuesList.get(i);
					
					if(sensitiveBenefitsList.contains(hippaCodeValue.getValue())){
						mapping.setSensitiveBenefitIndicator("Y");
						isSensitiveBenefitPresent = true;
						isSensitiveBenefitIndicatorChanged = true;
						break;
					}
				}
				if(!isSensitiveBenefitPresent){
					mapping.setSensitiveBenefitIndicator("N");
					isSensitiveBenefitIndicatorChanged = true;
				}
			}
		}
		return isSensitiveBenefitIndicatorChanged;
	}
	
	public static boolean isEb03AssnValuesPresent(MassUpdateCriteria muc) {

		boolean isEb03AssnToBeUpdated = false;
		if (null != muc) {
			if ((null != muc.getCfEb03() && muc.getCfEb03().length > 0)
					|| (null != muc.getCfNoteType() && muc.getCfNoteType().length > 0)
					|| (null != muc.getCfIII02() && muc.getCfIII02().length > 0)
					|| (null != muc.getCfMessage() && muc.getCfMessage().length > 0)
					|| (null != muc.getNvEb03() && muc.getNvEb03().length > 0)
					|| (null != muc.getNvMessage()//&& !muc.getNvMessage().equals(DomainConstants.EMPTY)
							)
					|| (null != muc.getNvNoteType()//&& !muc.getNvNoteType().equals(DomainConstants.EMPTY)
							)
					|| (null != muc.getNvIII02()//&& !muc.getNvIII02().equals(DomainConstants.EMPTY)
							)) {
				isEb03AssnToBeUpdated = true;

			}
		}
		return isEb03AssnToBeUpdated;
	}
	private static void removeDuplicateFromAssnObject(HippaSegment eB03HippaSegment){
		List<EB03Association> eb03ValuesToBeRemoved = new ArrayList<EB03Association>();
		Set<String> eb03DuplicationSet = new HashSet<String>();
		if(null != eB03HippaSegment.getEb03Association()){
			for(EB03Association eb03Assn : eB03HippaSegment.getEb03Association()){
				String eb03Val = (null != eb03Assn.getEb03() && null != eb03Assn.getEb03().getValue() ? eb03Assn.getEb03().getValue().trim() : DomainConstants.EMPTY);
				if(eb03DuplicationSet.contains(eb03Val)){
					eb03ValuesToBeRemoved.add(eb03Assn);
				}else{
				  eb03DuplicationSet.add(eb03Val);
				}
			}
			if(null != eb03ValuesToBeRemoved && !eb03ValuesToBeRemoved.isEmpty()){
				for(EB03Association eb03AssnObj : eb03ValuesToBeRemoved){
					if(null != eb03AssnObj){
						eB03HippaSegment.getEb03Association().remove(eb03AssnObj);
					}
				}
				
			}
		}
	}
	
	private static void createCopyObject(EB03Association newAssnObj, EB03Association oldAssnObj){
		if(null != oldAssnObj){
			newAssnObj.setCommaSeparatedIII02String(oldAssnObj.getCommaSeparatedIII02String());
			newAssnObj.setCommaSeparatedIII02StringWithDesc(oldAssnObj.getCommaSeparatedIII02StringWithDesc());
			newAssnObj.setEb03(oldAssnObj.getEb03());
			newAssnObj.setEb03String(oldAssnObj.getEb03String());
			newAssnObj.setIii02List(oldAssnObj.getIii02List());
			newAssnObj.setMessage(oldAssnObj.getMessage());
			newAssnObj.setMsgReqdInd(oldAssnObj.getMsgReqdInd());
			newAssnObj.setNoteType(oldAssnObj.getNoteType());
		}
		
	}
	
	private static void removeEmptyAssnObject(HippaSegment eb03HippaSegment){
		List<EB03Association> eb03ValuesToBeRemoved = new ArrayList<EB03Association>();
		if (null != eb03HippaSegment.getEb03Association()) {
			for (EB03Association eb03Assn : eb03HippaSegment
					.getEb03Association()) {
				String eb03Val = (null != eb03Assn.getEb03()
						&& null != eb03Assn.getEb03().getValue() ? eb03Assn
						.getEb03().getValue().trim()
						: DomainConstants.EMPTY);
				if (null == eb03Val || eb03Val.isEmpty()) {
					eb03ValuesToBeRemoved.add(eb03Assn);
				}
			}
			
			if(null != eb03ValuesToBeRemoved && !eb03ValuesToBeRemoved.isEmpty()){
				for(EB03Association eb03AssnObj : eb03ValuesToBeRemoved){
					if(null != eb03AssnObj){
						eb03HippaSegment.getEb03Association().remove(eb03AssnObj);
					}
				}
				
			}
		}
	}
}
