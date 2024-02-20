/*
 * Created on May 19, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentValidationResult;
import com.wellpoint.ets.ebx.simulation.vo.ErrorDetailVO;
import com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentService;
import com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentServiceImpl;

/**
 * @author u23641
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class HSDCodesValidator extends Validator implements
		HippaSegmentValidator {

	private HippaSegmentService hippaSegmentService;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentValidator#validate
	 * (com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public List validate(Mapping mapping) throws DomainException {
		if (null == mapping) {
			throw new DomainException("Mapping  object expected.");
		}
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		List hippaSegmentValidationResultList = new ArrayList();
		short status = 0;
		ValidationUtility validationUtility = new ValidationUtility();
		String regex = "[0-9]|[0-9]*.[0-9]+";
		Pattern pattern = Pattern.compile(regex);

		// Validate HSD01 and HSD02 : (BTRD)T2.1.22
		String hsd1 = mapping.getHsd01Value();
		String hsd2 = mapping.getHsd02Value().get(0).toString();
		boolean pass = validationUtility.validateHSDComparison(hsd1, hsd2);
		if (!pass) {
			hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
					hippaSegmentValidationResult, status,
					ValidatorConstants.DEPENDENCY_CHECK_FAILURE_FOR_HSD,
					new String[] { DomainConstants.HSD01_NAME,
							DomainConstants.HSD02_NAME });
			hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
		} else {
			if (hsd1 != null && !hsd1.trim().equals("")) {
				hippaSegmentValidationResultList.add(validate(
						mapping.getHsd01().getName(),
						mapping.getHsd01().getHippaCodePossibleValues(),
						mapping.getHsd01().getHippaCodeSelectedValues(),
						mapping).get(0));

              // condition added for BXNI.
				// hsd2 not equal to null then check for validation else no
				// error.
				if (null != hsd2 && !"".equalsIgnoreCase(hsd2)
						&& pattern.matches(regex, hsd2)) {
					boolean comparision = validationUtility.compare(
							ValidatorConstants.HSD02_MIN,
							ValidatorConstants.HSD02_MAX,
							Double.parseDouble(hsd2));
					if (comparision) {
						status = 1;
						hippaSegmentValidationResult = setHippaSegVldnRslt(
								mapping, hippaSegmentValidationResult, status,
								ValidatorConstants.VALIDATION_SUCCESS,
								new String[] { DomainConstants.HSD02_NAME });
						hippaSegmentValidationResultList
								.add(hippaSegmentValidationResult);
					} else {
						status = 0;
						hippaSegmentValidationResult = setHippaSegVldnRslt(
								mapping, hippaSegmentValidationResult, status,
								ValidatorConstants.INVALID_HSD02,
								new String[] { DomainConstants.HSD02_NAME });
						hippaSegmentValidationResultList
								.add(hippaSegmentValidationResult);
					}
				}

				else {
					status = 1;
					hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
							hippaSegmentValidationResult, status,
							ValidatorConstants.HSD_VALIDATION_SUCCESSFUL,
							new String[] {});
					hippaSegmentValidationResultList
							.add(hippaSegmentValidationResult);
				}
			}

		}
		// BXNI Change:
		if ((null == hsd1 || "".equalsIgnoreCase(hsd1))
				&& (null == hsd2 || "".equalsIgnoreCase(hsd2))) {
			status = 1;
			hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
					hippaSegmentValidationResult, status,
					ValidatorConstants.HSD_VALIDATION_SUCCESSFUL,
					new String[] {});
			hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
		}

		// Validate HSD03 and HSD04
		status = 0;
		hippaSegmentValidationResult = new HippaSegmentValidationResult();
		String hsd3 = mapping.getHsd03Value();
		String hsd4 = mapping.getHsd04Value();
		pass = new ValidationUtility().validateHSDComparison(hsd3, hsd4);
		if (!pass) {
			hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
					hippaSegmentValidationResult, status,
					ValidatorConstants.DEPENDENCY_CHECK_FAILURE_FOR_HSD,
					new String[] { DomainConstants.HSD03_NAME,
							DomainConstants.HSD04_NAME });
			hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
		} else {
			if (hsd3 != null && !hsd3.trim().equals("")) {
				hippaSegmentValidationResultList.add(validate(
						mapping.getHsd03().getName(),
						mapping.getHsd03().getHippaCodePossibleValues(),
						mapping.getHsd03().getHippaCodeSelectedValues(),
						mapping).get(0));
				if (null != hsd4 && !"".equalsIgnoreCase(hsd4)
						&& pattern.matches(regex, hsd4)) {
					boolean comparision = validationUtility.compare(
							ValidatorConstants.HSD04_MIN,
							ValidatorConstants.HSD04_MAX,
							Double.parseDouble(hsd4));
					if (comparision) {
						status = 1;
						hippaSegmentValidationResult = setHippaSegVldnRslt(
								mapping, hippaSegmentValidationResult, status,
								ValidatorConstants.VALIDATION_SUCCESS,
								new String[] {});
						hippaSegmentValidationResultList
								.add(hippaSegmentValidationResult);
					} else {
						status = 0;
						hippaSegmentValidationResult = setHippaSegVldnRslt(
								mapping, hippaSegmentValidationResult, status,
								ValidatorConstants.INVALID_HSD04,
								new String[] { DomainConstants.HSD04_NAME });
						hippaSegmentValidationResultList
								.add(hippaSegmentValidationResult);
					}
				}

				else {
					status = 1;
					hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
							hippaSegmentValidationResult, status,
							ValidatorConstants.VALIDATION_SUCCESS,
							new String[] { DomainConstants.HSD03_NAME,
									DomainConstants.HSD04_NAME });
					hippaSegmentValidationResultList
							.add(hippaSegmentValidationResult);
				}
			}

		}
		// BXNI Change:
		if ((null == hsd3 || "".equalsIgnoreCase(hsd3))
				&& (null == hsd4 || "".equalsIgnoreCase(hsd4))) {
			status = 1;
			hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
					hippaSegmentValidationResult, status,
					ValidatorConstants.HSD_VALIDATION_SUCCESSFUL,
					new String[] {});
			hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
		}

		// Validate HSD05 and HSD06
		status = 0;
		hippaSegmentValidationResult = new HippaSegmentValidationResult();
		String hsd5 = mapping.getHsd05Value();
		String hsd6 = mapping.getHsd06Value();
		pass = new ValidationUtility().validateHSDComparison(hsd5, hsd6);
		if (!pass) {
			hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
					hippaSegmentValidationResult, status,
					ValidatorConstants.DEPENDENCY_CHECK_FAILURE_FOR_HSD,
					new String[] { DomainConstants.HSD05_NAME,
							DomainConstants.HSD06_NAME });
			hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
		} else {
			if (hsd5 != null && !"".equals(hsd5.trim())) {
				hippaSegmentValidationResultList.add(validate(
						mapping.getHsd05().getName(),
						mapping.getHsd05().getHippaCodePossibleValues(),
						mapping.getHsd05().getHippaCodeSelectedValues(),
						mapping).get(0));

				if (null != hsd6 && !"".equalsIgnoreCase(hsd6)
						&& pattern.matches(regex, hsd6)) {
					boolean comparision = validationUtility.compare(
							ValidatorConstants.HSD06_MIN,
							ValidatorConstants.HSD06_MAX,
							Double.parseDouble(hsd6));
					if (comparision) {
						status = 1;
						hippaSegmentValidationResult = setHippaSegVldnRslt(
								mapping, hippaSegmentValidationResult, status,
								ValidatorConstants.VALIDATION_SUCCESS,
								new String[] {});
						hippaSegmentValidationResultList
								.add(hippaSegmentValidationResult);
					} else {
						status = 0;
						hippaSegmentValidationResult = setHippaSegVldnRslt(
								mapping, hippaSegmentValidationResult, status,
								ValidatorConstants.INVALID_HSD06,
								new String[] { DomainConstants.HSD06_NAME });
						hippaSegmentValidationResultList
								.add(hippaSegmentValidationResult);
					}
				}

			} else {
				status = 1;
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						hippaSegmentValidationResult, status,
						ValidatorConstants.VALIDATION_SUCCESS, new String[] {
								DomainConstants.HSD05_NAME,
								DomainConstants.HSD06_NAME });
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}
		// BXNI Change:
		if ((null == hsd5 || "".equalsIgnoreCase(hsd5))
				&& (null == hsd6 || "".equalsIgnoreCase(hsd6))) {
			status = 1;
			hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
					hippaSegmentValidationResult, status,
					ValidatorConstants.HSD_VALIDATION_SUCCESSFUL,
					new String[] {});
			hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
		}

		// Validate HSD07 and HSD08
		status = 0;
		hippaSegmentValidationResult = new HippaSegmentValidationResult();
		String hsd7 = mapping.getHsd07Value();
		String hsd8 = mapping.getHsd08Value();
		pass = new ValidationUtility().compare(hsd7, hsd8);
		if (!pass) {
			hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
					hippaSegmentValidationResult, status,
					ValidatorConstants.DEPENDENCY_CHECK_FAILURE_FOR_HSD_7_8,
					new String[] { DomainConstants.HSD07_NAME,
							DomainConstants.HSD08_NAME });
			hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
		} else {
			if (hsd7 != null && !"".equals(hsd7.trim())) {
				hippaSegmentValidationResultList.add(validate(
						mapping.getHsd07().getName(),
						mapping.getHsd07().getHippaCodePossibleValues(),
						mapping.getHsd07().getHippaCodeSelectedValues(),
						mapping).get(0));
				hippaSegmentValidationResultList.add(validate(
						mapping.getHsd08().getName(),
						mapping.getHsd08().getHippaCodePossibleValues(),
						mapping.getHsd08().getHippaCodeSelectedValues(),
						mapping).get(0));
			} else {
				status = 1;
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						hippaSegmentValidationResult, status,
						ValidatorConstants.VALIDATION_SUCCESS, new String[] {
								DomainConstants.HSD07_NAME,
								DomainConstants.HSD08_NAME });
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}
		
		validateHSD01_HSD05_E020(mapping, hippaSegmentValidationResultList);
		if(mapping.getHsd02Value().size() > 1){
			validationHSD02LimitationVariables(mapping,hippaSegmentValidationResultList);
		}
		return hippaSegmentValidationResultList;
	}
	/*
	 * /**
		 * Code logic is changed as part of BXNI December release
		 * The screen side validation for E020 will be reported if the variable/sps is mapped to 
		 * HSD01 = DY and HSD05 as 7 (Day ) or 13 (24 hour )
		 * HSD01 = HS and HSD05 as 6 (hour )
		 * HSD01 = MN and HSD05 as 34 (month) 
		 * HSD01 = VS and HSD05 as 27 (visit ) 
		 * 
		 * Below stated EB Validations are covered in EB06Validator Class
		 * EB09 = DY and EB06 as 7 (Day ) or 13 (24 hour )
		 * EB09 = HS and EB06 as 6 (hour ) 
		 * EB09 = MN and EB06 as 34 (month ) 
		 * EB09 = VS and EB06 as 27 (visit ) 
		 * EB09 = YY and EB06 value is 21 (year) or 22 (service year) or 23 (calendar year) or blank
		 * 
		 */
	
	/**
	 * @return the hippaSegmentService
	 */
	public HippaSegmentService getHippaSegmentService() {
		return hippaSegmentService;
	}

	/**
	 * @param hippaSegmentService the hippaSegmentService to set
	 */
	public void setHippaSegmentService(HippaSegmentService hippaSegmentService) {
		this.hippaSegmentService = hippaSegmentService;
	}

	private void validateHSD01_HSD05_E020(Mapping mapping, List<HippaSegmentValidationResult> hippaSegmentValidationResultList ){
		 
		 	String hsd05Value = "";
			hsd05Value = mapping.getHsd05Value();
			String hsd01Value = "";
			hsd01Value = mapping.getHsd01Value();
			short status = 2;
			
			
			if((!(DomainConstants.EMPTY).equalsIgnoreCase(hsd01Value)) && (!(DomainConstants.EMPTY).equalsIgnoreCase(hsd05Value))){
				
				if ((((hsd05Value.equalsIgnoreCase(DomainConstants.EB06_7)) || (hsd05Value
						.equalsIgnoreCase(DomainConstants.EB06_13))) && (hsd01Value
						.equalsIgnoreCase(DomainConstants.DY)))
						|| ((hsd05Value
								.equalsIgnoreCase(DomainConstants.EB06_6)) && (hsd01Value
								.equalsIgnoreCase(DomainConstants.HS)))
						|| ((hsd05Value
								.equalsIgnoreCase(DomainConstants.EB06_34)) && (hsd01Value
								.equalsIgnoreCase(DomainConstants.MN)))
						|| ((hsd05Value
								.equalsIgnoreCase(DomainConstants.EB06_27)) && (hsd01Value
								.equalsIgnoreCase(DomainConstants.VS)))) {
				HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
				hippaSegmentValidationResult = setHippaSegVldnRslt(
						mapping,
						new HippaSegmentValidationResult(),
						status,
						ValidatorConstants.WARNING_MESSAGE_HSD05_HSD01_E020,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
				}
			}
		
		
		
	}
	
	/*
	 * Change by AF48640
	 * HSD02 limitation variables should be either in object transferred, published, approved
	 * The EB Segment should be not present for the Limitation variables
	 * Throw error using the pop up in the UI
	 * 
	 */	
	private void validationHSD02LimitationVariables(Mapping mapping, List<HippaSegmentValidationResult> hippaSegmentValidationResultList ){
		List selectedValues = mapping.getHsd02Value();
		short status = 0;
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		ValidationUtility validationUtility = new ValidationUtility();
		if(selectedValues.size() > 0){
			if(selectedValues.size()>1){
				for(Object obj: selectedValues){
					String limitationVariable = obj.toString();
					
					//passing the limitation variable to validate about it's EB segment
					if(!limitationVariable.isEmpty()){
					boolean isEBpresent = mapping.getHippaSegmentService().isEBSegmentPresent(limitationVariable);
					if(isEBpresent){
						status = 0;
						hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, ValidatorConstants.HSD02_EB_SEGMENT, new String[] {limitationVariable.trim().toUpperCase().toString()});
						hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
					} 
					}
				}
			}else{
				if(selectedValues.size() == 1 ){
					String hsd2 = selectedValues.get(0).toString().trim();
					if( hsd2 != null && !hsd2.equalsIgnoreCase("") &&  hsd2.length() < 3 ){
						boolean comparision = validationUtility.compare(
								ValidatorConstants.HSD02_MIN,
								ValidatorConstants.HSD02_MAX,
								Double.parseDouble(hsd2));
					}else{
						//passing the limitation variable to validate about it's EB segment
						//Added condition to check HSD02 additional fields in wpd
						if(hsd2 != null && !hsd2.equalsIgnoreCase("") &&  hsd2.length() > 2){
						boolean isEBpresent = mapping.getHippaSegmentService().isEBSegmentPresent(hsd2);
						if(isEBpresent){
							status = 0;
							hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, ValidatorConstants.HSD02_EB_SEGMENT, new String[] {hsd2.trim().toUpperCase().toString()});
							hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
						}
						}
					}
				}
			}
		}else{
			
		}
	}
}


