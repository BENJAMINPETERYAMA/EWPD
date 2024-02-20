/*
 * Created on May 20, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentValidationResult;
import com.wellpoint.ets.bx.mapping.util.BxUtil;

/**
 * @author U19103
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class NoteTypeIndValidator extends Validator implements
		HippaSegmentValidator {

	// validating for note type indicator for valid values

	public List validate(Mapping mapping) throws DomainException {
		if (null == mapping) {
			throw new DomainException("Mapping  object expected.");
		}
		List <HippaSegmentValidationResult> hippaSegmentValidationResultList = new ArrayList<HippaSegmentValidationResult> ();
		List hippaCodeSelectedValues = new ArrayList();
		List hippaCodePossibleValues = null;
		if (mapping.getNoteTypeCode() == null) {
			return null;
		}

		if (mapping.getNoteTypeCode().getHippaCodeSelectedValues() != null) {
			hippaCodeSelectedValues = mapping.getNoteTypeCode()
					.getHippaCodeSelectedValues();
		}
		hippaCodePossibleValues = mapping.getNoteTypeCode()
				.getHippaCodePossibleValues();
		if (null != mapping.getIndvdlEb03AssnIndicator()
				&& mapping.getIndvdlEb03AssnIndicator().equalsIgnoreCase("Y")) {
			hippaSegmentValidationResultList = validateNoteTypeForValidHippaValues(hippaCodePossibleValues, mapping, hippaSegmentValidationResultList);
		} else {
			hippaSegmentValidationResultList = validate(mapping.getNoteTypeCode().getName(), hippaCodePossibleValues, hippaCodeSelectedValues,mapping);
		}
		validateNoteTypeBasedOnEB01(mapping, hippaSegmentValidationResultList);
		validateNoteTypeForMessageText(mapping,
				hippaSegmentValidationResultList);
		return hippaSegmentValidationResultList;
	}

	/**
	 * @param mapping
	 * @param hippaSegmentValidationResultList
	 */
	public void validateNoteTypeBasedOnEB01(Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList) {
		short status = 0;
		String eb01Value = "";
		String noteTypeValue = "";
		boolean errorForFExist = false;
		boolean errorForCGExist = false;
		String errorMessage = "";

		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		if (null != mapping) {
			if (null != mapping.getEB01Value()
					&& !mapping.getEB01Value().equalsIgnoreCase(DomainConstants.EMPTY)) {
				eb01Value = mapping.getEB01Value();
			}

			if (null != mapping.getVariable() && null != mapping.getVariable().getVariableId()
					&& !StringUtils.isEmpty(mapping.getVariable().getVariableId())) {
				
				if (null != mapping.getIndvdlEb03AssnIndicator()
						&& DomainConstants.Y.equalsIgnoreCase(mapping.getIndvdlEb03AssnIndicator())) {
					if (null != mapping.getEb03() && null != mapping.getEb03().getEb03Association()
							&& !mapping.getEb03().getEb03Association().isEmpty()) {
						
						List <EB03Association> eb03Association = mapping.getEb03().getEb03Association();
						Iterator <EB03Association> eb03AssnIterator = eb03Association.iterator();
						while (eb03AssnIterator.hasNext()) {
							EB03Association eb03Assn = eb03AssnIterator.next();
							noteTypeValue = (null != eb03Assn && null != eb03Assn.getNoteType() && null != eb03Assn.getNoteType().getValue()
									? eb03Assn.getNoteType().getValue() : "");

							if (null != eb01Value && !DomainConstants.EMPTY.equals(eb01Value)
									&& null != noteTypeValue && !DomainConstants.EMPTY.equals(noteTypeValue)) {
								if (eb01Value.equalsIgnoreCase(DomainConstants.LIMITATIONS)
										&& !noteTypeValue
										.equalsIgnoreCase(DomainConstants.NOTE_TYPE_B)) {
									errorForFExist = true;
									break;
									
								} else if ((eb01Value.equalsIgnoreCase(DomainConstants.DEDUCTIBLE)
										|| eb01Value.equalsIgnoreCase(DomainConstants.OUT_OF_POCKET))
										&& (!((noteTypeValue.equalsIgnoreCase(DomainConstants.NOTE_TYPE_A))
												|| (noteTypeValue.equalsIgnoreCase(DomainConstants.NOTE_TYPE_B))

				                                || (noteTypeValue.equalsIgnoreCase(DomainConstants.NOTE_TYPE_D))))){
									errorForCGExist = true;
									break;
									
								}
							}
						}
					}
				} else {
					if (null != mapping.getNoteTypeCodeValue()
							&& !StringUtils.isEmpty(mapping.getNoteTypeCodeValue())) {
						noteTypeValue = mapping.getNoteTypeCodeValue();
					}
					
					if (null != eb01Value && !DomainConstants.EMPTY.equals(eb01Value)
							&& null != noteTypeValue && !DomainConstants.EMPTY.equals(noteTypeValue)) {
						if (eb01Value.equalsIgnoreCase(DomainConstants.LIMITATIONS)
								&& !noteTypeValue
								.equalsIgnoreCase(DomainConstants.NOTE_TYPE_B)) {
							errorForFExist = true;
							
						} else if ((eb01Value.equalsIgnoreCase(DomainConstants.DEDUCTIBLE)

                                || eb01Value.equalsIgnoreCase(DomainConstants.OUT_OF_POCKET))

                                && (!((noteTypeValue.equalsIgnoreCase(DomainConstants.NOTE_TYPE_A))

                                || (noteTypeValue.equalsIgnoreCase(DomainConstants.NOTE_TYPE_B))

                                || (noteTypeValue.equalsIgnoreCase(DomainConstants.NOTE_TYPE_D))))) {

                        errorForCGExist = true;

                        

                }


					}
				}

				if (errorForFExist) {
					errorMessage = ValidatorConstants.NOTE_TYPE_EB01_F_VALIDATION;
				}
				if (errorForCGExist) {
					errorMessage = ValidatorConstants.NOTE_TYPE_EB01_CG_VALIDATION;
				}

				if (errorForFExist || errorForCGExist) {
					hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
							hippaSegmentValidationResult, status, errorMessage,
							new String[] {});
					hippaSegmentValidationResultList
							.add(hippaSegmentValidationResult);
					errorForFExist = false;
					errorForCGExist = false;
				}
			}

			if (null != mapping.getSpsId() && null != mapping.getRule()
					&& null != mapping.getSpsId().getSpsId() && null != mapping.getRule().getHeaderRuleId()
					&& !StringUtils.isEmpty(mapping.getSpsId().getSpsId()) 
					&& !StringUtils.isEmpty(mapping.getRule().getHeaderRuleId())) {
				
				if (null != mapping.getIndvdlEb03AssnIndicator()
						&& DomainConstants.Y.equalsIgnoreCase(mapping.getIndvdlEb03AssnIndicator())) {
					if (null != mapping.getEb03() && null != mapping.getEb03().getEb03Association()
							&& !mapping.getEb03().getEb03Association().isEmpty()) {
						
						List <EB03Association> eb03Association = mapping.getEb03().getEb03Association();
						Iterator <EB03Association> eb03AssnIterator = eb03Association.iterator();
						while (eb03AssnIterator.hasNext()) {
							EB03Association eb03Assn = eb03AssnIterator.next();
							noteTypeValue = eb03Assn.getNoteType().getValue();

							if (null != eb01Value && !DomainConstants.EMPTY.equals(eb01Value)
									&& null != noteTypeValue && !DomainConstants.EMPTY.equals(noteTypeValue)) {
								if (eb01Value.equalsIgnoreCase(DomainConstants.LIMITATIONS)
										&& !noteTypeValue.equalsIgnoreCase(DomainConstants.NOTE_TYPE_013)) {
									
									errorForFExist = true;
									break;
									
								} else if ((eb01Value.equalsIgnoreCase(DomainConstants.DEDUCTIBLE)
										|| eb01Value.equalsIgnoreCase(DomainConstants.OUT_OF_POCKET))
										&& (!((noteTypeValue.equalsIgnoreCase(DomainConstants.NOTE_TYPE_001))
												||(noteTypeValue.equalsIgnoreCase(DomainConstants.NOTE_TYPE_013))
												||noteTypeValue.equalsIgnoreCase(DomainConstants.NOTE_TYPE_006)))) 
									{
									errorForCGExist = true;
									break;
									
								}
							}
						}
					}
				} else {
					if (null != mapping.getNoteTypeCodeValue()
							&& !StringUtils.isEmpty(mapping.getNoteTypeCodeValue())) {
						noteTypeValue = mapping.getNoteTypeCodeValue();
					}
					
					if (null != eb01Value && !DomainConstants.EMPTY.equals(eb01Value)
							&& null != noteTypeValue && !DomainConstants.EMPTY.equals(noteTypeValue)) {
						if (eb01Value.equalsIgnoreCase(DomainConstants.LIMITATIONS)
								&& !noteTypeValue
								.equalsIgnoreCase(DomainConstants.NOTE_TYPE_013)) {
							errorForFExist = true;
							
						} else if ((eb01Value.equalsIgnoreCase(DomainConstants.DEDUCTIBLE)
								|| eb01Value.equalsIgnoreCase(DomainConstants.OUT_OF_POCKET))
								&& (!((noteTypeValue.equalsIgnoreCase(DomainConstants.NOTE_TYPE_001))
										||(noteTypeValue.equalsIgnoreCase(DomainConstants.NOTE_TYPE_013))
										||noteTypeValue.equalsIgnoreCase(DomainConstants.NOTE_TYPE_006)))) 
							{
							errorForCGExist = true;
							
						}
					}
				}

				if (errorForFExist) {
					errorMessage = ValidatorConstants.NOTE_TYPE_EB01_F_VALIDATION_CUSTOM_MESSAGE;
				}
				if (errorForCGExist) {
					errorMessage = ValidatorConstants.NOTE_TYPE_EB01_CG_VALIDATION_CUSTOM_MESSAGE;
				}

				if (errorForFExist || errorForCGExist) {
					hippaSegmentValidationResult = setHippaSegVldnRslt(
							mapping, hippaSegmentValidationResult, status,
							errorMessage, new String[] {});
					hippaSegmentValidationResultList
							.add(hippaSegmentValidationResult);
					errorForFExist = false;
					errorForCGExist = false;

				}
			}
			//Added to avoid returninfg blank HippaSegmentValidationResultList For Custom Message
			if(null != hippaSegmentValidationResultList
					&& hippaSegmentValidationResultList.isEmpty()){
				status = 1;
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						hippaSegmentValidationResult, status,
						ValidatorConstants.VALIDATION_SUCCESS,
						new String[] { DomainConstants.NOTE_TYPE_CODE });
				hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
			}
			
		}
	}

	/**
	 * Method to check for the warning if note type is coded but message is empty for Variable/ SPS/ Individual EB03 level
	 * @param mapping
	 * @param hippaSegmentValidationResultList
	 */
	private void validateNoteTypeForMessageText(Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList) {
		// Set the status for warning.
		short status = 2;
		if (null != mapping) {
			
			String indAssnInd = mapping.getIndvdlEb03AssnIndicator();
			if (null != indAssnInd && DomainConstants.Y.equalsIgnoreCase(indAssnInd)) {
				validateNoteTypeMessageTextForIndivdualMapping(mapping, hippaSegmentValidationResultList, status);
			} else {
				validateNoteTypeMessageTextForVariable(mapping, hippaSegmentValidationResultList, status);
			}
		}
	}

	/**
	 * Method will set the warning if note type is coded but message is empty at Variable/ SPS level.
	 * @param mapping
	 * @param hippaSegmentValidationResultList
	 * @param status
	 */
	private void validateNoteTypeMessageTextForVariable(Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList, short status) {
		
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		String noteTypeValue = "";
		String msgText = "";
		
		if (null != mapping.getNoteTypeCodeValue()
				&& !StringUtils.isEmpty(mapping.getNoteTypeCodeValue())) {
			noteTypeValue = mapping.getNoteTypeCodeValue();
		}
		if (null != mapping.getMessage()
				&& !StringUtils.isEmpty(mapping.getMessage())) {
			msgText = mapping.getMessage();
		}
		
		if (!StringUtils.isEmpty(noteTypeValue)
				&& StringUtils.isEmpty(msgText)) {
			String warningMessage = ValidatorConstants.NOTE_TYPE_MSG_TEXT_VALIDATION;
			hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
					hippaSegmentValidationResult, status,
					warningMessage, new String[] {});
			hippaSegmentValidationResultList
					.add(hippaSegmentValidationResult);
		}
	}

	/**
	 * Method will set the warning if note type is coded but message is empty at individual EB03 level.
	 * @param mapping
	 * @param hippaSegmentValidationResultList
	 * @param status
	 */
	private void validateNoteTypeMessageTextForIndivdualMapping(
			Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList, short status) {
		
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		List <String> emptyMsgEb03List = new ArrayList<String>();
		String noteTypeValue = "";
		String msgText = "";
			
		if (null != mapping.getEb03() && null != mapping.getEb03().getEb03Association()
				&& !mapping.getEb03().getEb03Association().isEmpty()) {
			List <EB03Association> eb03Associations = mapping.getEb03().getEb03Association();
			for (EB03Association eb03Assn : eb03Associations) {
				if (null != eb03Assn && null != eb03Assn.getEb03() && null != eb03Assn.getEb03().getValue()) {
					msgText = eb03Assn.getMessage();
					if (null != eb03Assn.getNoteType()) {
						noteTypeValue = eb03Assn.getNoteType().getValue();
					}
				}
				if (!StringUtils.isEmpty(noteTypeValue) && StringUtils.isEmpty(msgText)) {
					String eb03WithEmptMasg = eb03Assn.getEb03().getValue();
					emptyMsgEb03List.add(eb03WithEmptMasg);
				}
			}
			if(!emptyMsgEb03List.isEmpty()) {
				
				String warningMessage = ValidatorConstants.NOTE_TYPE_MSG_TEXT_VALIDATION_INDVDL_EB03;
				String commaSeparatedEb03s = BxUtil.getAsCSV(emptyMsgEb03List);
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						hippaSegmentValidationResult, status,
						warningMessage, new String[] {commaSeparatedEb03s});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
			
		}
	}
	
	/**
	 * Method to check valid Note Type Values.
	 * @param hippaCodePossibleValues
	 * @param mapping
	 * @param hippaSegmentValidationResultList
	 * @return
	 */
	private List <HippaSegmentValidationResult> validateNoteTypeForValidHippaValues(List<HippaCodeValue> hippaCodePossibleValues, Mapping mapping, List<HippaSegmentValidationResult> hippaSegmentValidationResultList){
		
		String noteTypeVal = "";
		String errorMessage = "";
		short status = 0;
		List<String> possibleHippaCodesForNoteTypeList = new ArrayList<String>();
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		List<String> invalidNoteTypeList = new ArrayList<String>();
		
		for(HippaCodeValue possibleHippaCodesForNoteType : hippaCodePossibleValues){
			if(null != possibleHippaCodesForNoteType && null != possibleHippaCodesForNoteType.getValue()){
				possibleHippaCodesForNoteTypeList.add(possibleHippaCodesForNoteType.getValue());
			}
		}
		
		if (null != mapping.getEb03() && null != mapping.getEb03().getEb03Association()
				&& !mapping.getEb03().getEb03Association().isEmpty()) {
			List <EB03Association> eb03Associations = mapping.getEb03().getEb03Association();
			if (!eb03Associations.isEmpty()) {
				for (EB03Association eb03Assn : eb03Associations) {
					if (null != eb03Assn && null != eb03Assn.getNoteType() && null != eb03Assn.getNoteType().getValue()) {
						noteTypeVal = eb03Assn.getNoteType().getValue().trim();
						if (!DomainConstants.EMPTY.equals(noteTypeVal)) {
							if(!possibleHippaCodesForNoteTypeList.contains(noteTypeVal)){
								invalidNoteTypeList.add(noteTypeVal);
							}
						}
					}
				}
			}
		}
		if(null != invalidNoteTypeList && !invalidNoteTypeList.isEmpty()){
			String commaSeparatedinvalidNoteTypeList = BxUtil.getAsCSV(invalidNoteTypeList);
			errorMessage = ValidatorConstants.INVALID_NOTE_TYPE;
			hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
					hippaSegmentValidationResult, status,
					errorMessage, new String[]{commaSeparatedinvalidNoteTypeList});
			hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
		}
		return hippaSegmentValidationResultList;
	
		
	}

}