/*
 * Created on Jul 9, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.blaze.ruleservice.contractvalidation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import com.wellpoint.wpd.common.contract.model.AdminOption;
import com.wellpoint.wpd.common.contract.model.Benefit;
import com.wellpoint.wpd.common.contract.model.BenefitComponent;
import com.wellpoint.wpd.common.contract.model.BenefitLines;
import com.wellpoint.wpd.common.contract.model.Contract;
import com.wellpoint.wpd.common.contract.model.MembershipInformation;
import com.wellpoint.wpd.common.contract.model.Message;
import com.wellpoint.wpd.common.contract.model.Messages;
import com.wellpoint.wpd.common.contract.model.Note;
import com.wellpoint.wpd.common.contract.model.PricingInformation;
import com.wellpoint.wpd.common.contract.model.Product;
import com.wellpoint.wpd.common.contract.model.Question;
import com.wellpoint.wpd.common.contract.model.Rule;
import com.wellpoint.wpd.common.contract.model.RuleCategory;
import com.wellpoint.wpd.common.contract.model.SuperProcessSteps;

/**
 * @author U14659
 * 
 * Class with methods to convert from model beans to web service model beans and
 * vice-versa
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class WSUtil {

	/**
	 * 
	 * Method to convert from ws model bean to model bean
	 * 
	 * @param ruleCategory
	 * @return
	 */

	private static RuleCategory convert(
			com.wellpoint.wpd.common.contract.ws.model.RuleCategory ruleCategory) {

		if (ruleCategory == null)
			return null;

		RuleCategory result = new RuleCategory();

		result.setName(convert(ruleCategory.getName()));
		result.setInvoker(convert(ruleCategory.getInvoker()));
		result.setDescription(convert(ruleCategory.getDescription()));

		return result;

	}

	/**
	 * 
	 * Method to convert model bean to ws model bean
	 * 
	 * @param superProcessSteps
	 * @return
	 */

	private static com.wellpoint.wpd.common.contract.ws.model.SuperProcessSteps convert(
			SuperProcessSteps superProcessSteps) {

		if (superProcessSteps == null)
			return null;

		com.wellpoint.wpd.common.contract.ws.model.SuperProcessSteps result = new com.wellpoint.wpd.common.contract.ws.model.SuperProcessSteps();

		result.setSuperProcessStep(convert(superProcessSteps.getSuperProcessStep()));
		result.setAdminMethod(convert(superProcessSteps.getAdminMethod()));
		result.setReference(convert(superProcessSteps.getReference()));

		return result;
	}

	/**
	 * 
	 * Method to convert model bean to ws model bean
	 * 
	 * @param pricingInformation
	 * @return
	 */

	private static com.wellpoint.wpd.common.contract.ws.model.PricingInformation convert(
			PricingInformation pricingInformation) {

		if (pricingInformation == null)
			return null;

		com.wellpoint.wpd.common.contract.ws.model.PricingInformation result = new com.wellpoint.wpd.common.contract.ws.model.PricingInformation();

		result.setPricing(convert(pricingInformation.getPricing()));
		result.setCoverage(convert(pricingInformation.getCoverage()));
		result.setNetwork(convert(pricingInformation.getNetwork()));

		return result;
	}

	/**
	 * 
	 * Method to convert model bean to ws model bean
	 * 
	 * @param rule
	 * @return
	 */

	private static com.wellpoint.wpd.common.contract.ws.model.Rule convert(
			Rule rule) {

		if (rule == null)
			return null;

		com.wellpoint.wpd.common.contract.ws.model.Rule result = new com.wellpoint.wpd.common.contract.ws.model.Rule();

		result.setRuleType(convert(rule.getRuleType()));
		result.setEwpdRuleId(convert(rule.getEwpdRuleId()));
		result.setRuleId(convert(rule.getRuleId()));
		result.setDescription(convert(rule.getDescription()));
		result.setPva(convert(rule.getPva()));
		result.setGroupIndicator(convert(rule.getGroupIndicator()));
		result.setValue(convert(rule.getValue()));

		return result;
	}

	/**
	 * 
	 * Method to convert model bean to ws model bean
	 * 
	 * @param question
	 * @return
	 */

	private static com.wellpoint.wpd.common.contract.ws.model.Question convert(
			Question question) {

		if (question == null)
			return null;

		com.wellpoint.wpd.common.contract.ws.model.Question result = new com.wellpoint.wpd.common.contract.ws.model.Question();

		result.setQuestion(convert(question.getQuestion()));
		result.setAnswer(convert(question.getAnswer()));
		result.setReference(convert(question.getReference()));
		result.setPva(convert(question.getPva()));
		result.setNote(convert(question.getNote()));

		return result;

	}

	/**
	 * 
	 * 
	 * Method to convert model bean to ws model bean
	 * 
	 * @param membershipInformation
	 * @return
	 */

	private static com.wellpoint.wpd.common.contract.ws.model.MembershipInformation convert(
			MembershipInformation membershipInformation) {

		if (membershipInformation == null)
			return null;

		com.wellpoint.wpd.common.contract.ws.model.MembershipInformation result = new com.wellpoint.wpd.common.contract.ws.model.MembershipInformation();

		result.setCaseNumberName(convert(membershipInformation.getCaseNumberName()));
		Calendar caseeffectiveCancelDate = Calendar.getInstance();
		if (membershipInformation.getCaseeffectiveCancelDate() != null)
			caseeffectiveCancelDate.setTime(membershipInformation
					.getCaseeffectiveCancelDate());
		result.setCaseeffectiveCancelDate(caseeffectiveCancelDate);
		result.setCaseHQState(convert(membershipInformation.getCaseHQState()));
		result.setGroupNumberName(convert(membershipInformation.getGroupNumberName()));
		Calendar groupeffectiveCancelDate = Calendar.getInstance();
		if (membershipInformation.getGroupeffectiveCancelDate() != null)
			groupeffectiveCancelDate.setTime(membershipInformation
					.getGroupeffectiveCancelDate());
		result.setGroupeffectiveCancelDate(groupeffectiveCancelDate);
		result.setFundingArrangement(convert(membershipInformation
				.getFundingArrangement()));
		result.setMbuCodeValue(convert(membershipInformation.getMbuCodeValue()));
		result.setRerateCodeValue(convert(membershipInformation.getRerateCodeValue()));

		return result;
	}

	/**
	 * 
	 * 
	 * Method to convert model bean to ws model bean
	 * 
	 * @param note
	 * @return
	 */

	private static com.wellpoint.wpd.common.contract.ws.model.Note convert(
			Note note) {

		if (note == null)
			return null;

		com.wellpoint.wpd.common.contract.ws.model.Note result = new com.wellpoint.wpd.common.contract.ws.model.Note();

		result.setNoteId(convert(note.getNoteId()));
		result.setNoteName(convert(note.getNoteName()));

		return result;
	}

	/**
	 * 
	 * 
	 * Method to convert model bean to ws model bean
	 * 
	 * @param benefitLines
	 * @return
	 */

	private static com.wellpoint.wpd.common.contract.ws.model.BenefitLines convert(
			BenefitLines benefitLines) {

		if (benefitLines == null)
			return null;

		com.wellpoint.wpd.common.contract.ws.model.BenefitLines result = new com.wellpoint.wpd.common.contract.ws.model.BenefitLines();

		result.setTerm(convert(benefitLines.getTerm()));
		result.setQualifier(convert(benefitLines.getQualifier()));
		result.setPva(convert(benefitLines.getPva()));
		result.setFormat(convert(benefitLines.getFormat()));
		result.setBenefitValue(convert(benefitLines.getBenefitValue()));
		result.setReference(convert(benefitLines.getReference()));
		result.setBenefitLevelDescription(convert(benefitLines
				.getBenefitLevelDescription()));
		result.setNote(convert(benefitLines.getNote()));
		result.setLevelId(new Integer(benefitLines.getLevelId()));

		return result;
	}

	/**
	 * 
	 * Method to convert model bean to ws model bean
	 * 
	 * @param adminOption
	 * @return
	 */

	private static com.wellpoint.wpd.common.contract.ws.model.AdminOption convert(
			AdminOption adminOption) {

		if (adminOption == null)
			return null;

		
		com.wellpoint.wpd.common.contract.ws.model.AdminOption result = new com.wellpoint.wpd.common.contract.ws.model.AdminOption();

		result.setAdminLevel(convert(adminOption.getAdminLevel()));
		if(adminOption.getQuestions() != null){
			Object[] questions = new Object[adminOption.getQuestions().size()];
		for (int i = 0; i < adminOption.getQuestions().size(); i++) {
			questions[i] = convert((Question) adminOption.getQuestions().get(i));
		}
		result.setQuestions(questions);
		}
		result.setAdminOptionName(convert(adminOption.getAdminOptionName()));
		

		return result;
	}

	/**
	 * 
	 * Method to convert model bean to ws model bean
	 * 
	 * @param benefit
	 * @return
	 */

	private static com.wellpoint.wpd.common.contract.ws.model.Benefit convert(
			Benefit benefit) {

		if (benefit == null)
			return null;

		com.wellpoint.wpd.common.contract.ws.model.Benefit result = new com.wellpoint.wpd.common.contract.ws.model.Benefit();

		result.setLineOfBusiness(convertStringListToObjectArray(benefit
				.getLineOfBusiness()));
		result.setBusinessEntity(convertStringListToObjectArray(benefit
				.getBusinessEntity()));
		result.setBusinessGroup(convertStringListToObjectArray(benefit
				.getBusinessGroup()));

		result.setBenefitName(convert(benefit.getBenefitName()));
		result.setBenefitMeaning(convert(benefit.getBenefitMeaning()));
		result.setBenefitType(convert(benefit.getBenefitType()));
		result.setBenefitCategory(convert(benefit.getBenefitCategory()));
		result.setDescription(convert(benefit.getDescription()));
		result.setBenefitRuleId(convert(benefit.getBenefitRuleId()));

		result.setTerm(convertStringListToObjectArray(benefit.getTerm()));
		result.setQualifier(convertStringListToObjectArray(benefit
				.getQualifier()));
		result.setPva(convertStringListToObjectArray(benefit.getPva()));
		result
				.setDataType(convertStringListToObjectArray(benefit
						.getDataType()));

		result.setVersion(new Integer(benefit.getVersion()));
		if(benefit.getNotes()!= null){
		Object[] notes = new Object[benefit.getNotes().size()];
		for (int i = 0; i < benefit.getNotes().size(); i++) {
			notes[i] = convert((Note) benefit.getNotes().get(i));
		}
		result.setNotes(notes);
		}
		
		
		if(benefit.getBenefitLines()!= null){
		Object[] benefitLines = new Object[benefit.getBenefitLines().size()];
		for (int i = 0; i < benefit.getBenefitLines().size(); i++) {
			benefitLines[i] = convert((BenefitLines) benefit.getBenefitLines()
					.get(i));
		}
		result.setBenefitLines(benefitLines);
		}
		
		if(benefit.getAdminOptions()!= null){
		Object[] adminOptions = new Object[benefit.getAdminOptions().size()];
		for (int i = 0; i < benefit.getAdminOptions().size(); i++) {
			adminOptions[i] = convert((AdminOption) benefit.getAdminOptions()
					.get(i));
		}
		
		result.setAdminOptions(adminOptions);
		}
		
		if(benefit.getSuperProcessSteps() !=null){
		Object[] superProcessSteps = new Object[benefit.getSuperProcessSteps()
				.size()];
		for (int i = 0; i < benefit.getSuperProcessSteps().size(); i++) {
			superProcessSteps[i] = convert((SuperProcessSteps) benefit
					.getSuperProcessSteps().get(i));
		}
		result.setSuperProcessSteps(superProcessSteps);
		}

		result.setAdministration(convert(benefit.getAdministration()));

		return result;
	}

	/**
	 * 
	 * Method to convert model bean to ws model bean
	 * 
	 * @param benefitComponent
	 * @return
	 */

	private static com.wellpoint.wpd.common.contract.ws.model.BenefitComponent convert(
			BenefitComponent benefitComponent) {

		if (benefitComponent == null)
			return null;

		com.wellpoint.wpd.common.contract.ws.model.BenefitComponent result = new com.wellpoint.wpd.common.contract.ws.model.BenefitComponent();

		result
				.setLineOfBusiness(convertStringListToObjectArray(benefitComponent
						.getLineOfBusiness()));
		result
				.setBusinessEntity(convertStringListToObjectArray(benefitComponent
						.getBusinessEntity()));
		result.setBusinessGroup(convertStringListToObjectArray(benefitComponent
				.getBusinessGroup()));

		result.setBenefitComponentName(convert(benefitComponent
				.getBenefitComponentName()));
		result.setBenefitComponentType(convert(benefitComponent
				.getBenefitComponentType()));
		result.setBenefitComponentDescription(convert(benefitComponent
				.getBenefitComponentDescription()));
		result.setBenefitRuleId(convert(benefitComponent.getBenefitRuleId()));
		result.setEffectiveDate(convert(benefitComponent.getEffectiveDate()));
		result.setExpiryDate(convert(benefitComponent.getExpiryDate()));
		result.setVersion(convert(benefitComponent.getVersion()));
		
		if(benefitComponent.getBenefits() != null){
		Object[] benefits = new Object[benefitComponent.getBenefits().size()];
		for (int i = 0; i < benefitComponent.getBenefits().size(); i++) {
			benefits[i] = convert((Benefit) benefitComponent.getBenefits().get(
					i));
		}
		result.setBenefits(benefits);
		}
		
		if(benefitComponent.getNotes() !=null){
		Object[] notes = new Object[benefitComponent.getNotes().size()];
		for (int i = 0; i < benefitComponent.getNotes().size(); i++) {
			notes[i] = convert((Note) benefitComponent.getNotes().get(i));
		}
		result.setNotes(notes);
		}

		return result;
	}

	/**
	 * 
	 * Method to convert model bean to ws model bean
	 * 
	 * @param product
	 * @return
	 */

	private static com.wellpoint.wpd.common.contract.ws.model.Product convert(
			Product product) {

		if (product == null)
			return null;

		com.wellpoint.wpd.common.contract.ws.model.Product result = new com.wellpoint.wpd.common.contract.ws.model.Product();

		result.setLineOfBusiness(convertStringListToObjectArray(product
				.getLineOfBusiness()));
		result.setBusinessEntity(convertStringListToObjectArray(product
				.getBusinessEntity()));
		result.setBusinessGroup(convertStringListToObjectArray(product
				.getBusinessGroup()));

		result.setProductName(convert(product.getProductName()));
		result.setProductFamily(convert(product.getProductFamily()));
		result.setProductType(convert(product.getProductType()));
		result.setProductStructureName(convert(product.getProductStructureName()));
		result.setProductStructureVersion(product.getProductStructureVersion());
		result.setProductStructureDescription(convert(product
				.getProductStructureDescription()));
		Calendar effectiveDate = Calendar.getInstance();
		if (product.getEffectiveDate() != null)
			effectiveDate.setTime(product.getEffectiveDate());
		result.setEffectiveDate(effectiveDate);
		Calendar expiryDate = Calendar.getInstance();
		if (product.getExpiryDate() != null)
			expiryDate.setTime(product.getExpiryDate());
		result.setExpiryDate(expiryDate);
		result.setVersion(product.getVersion());
		
		if(product.getBenefitComponents() != null ){
		Object[] benefitComponents = new Object[product.getBenefitComponents()
				.size()];
		for (int i = 0; i < product.getBenefitComponents().size(); i++) {
			benefitComponents[i] = convert((BenefitComponent) product
					.getBenefitComponents().get(i));
		}
		result.setBenefitComponents(benefitComponents);
		}

		return result;
	}

	/**
	 * 
	 * Method to convert model bean to ws model bean
	 * 
	 * @param contract
	 * @return
	 */

	public static com.wellpoint.wpd.common.contract.ws.model.Contract convert(
			Contract contract) {

		if (contract == null)
			return null;

		com.wellpoint.wpd.common.contract.ws.model.Contract result = new com.wellpoint.wpd.common.contract.ws.model.Contract();

		result.setLineOfBusiness(convertStringListToObjectArray(contract
				.getLineOfBusiness()));
		result.setBusinessEntity(convertStringListToObjectArray(contract
				.getBusinessEntity()));
		result.setBusinessGroup(convertStringListToObjectArray(contract
				.getBusinessGroup()));

		result.setContractId(convert(contract.getContractId()));
		result.setContractType(convert(contract.getContractType()));
		result.setBaseContractId(convert(contract.getBaseContractId()));
		Calendar contractEffectiveDate = Calendar.getInstance();

		if (contract.getBaseContractEffectiveDate() != null)
			contractEffectiveDate.setTime(contract
					.getBaseContractEffectiveDate());
		result.setBaseContractEffectiveDate(contractEffectiveDate);
		result.setGroupSize(convert(contract.getGroupSize()));
		Calendar effectiveDate = Calendar.getInstance();

		if (contract.getEffectiveDate() != null)
			effectiveDate.setTime(contract.getEffectiveDate());
		result.setEffectiveDate(effectiveDate);
		Calendar expiryDate = Calendar.getInstance();

		if (contract.getExpiryDate() != null)
			expiryDate.setTime(contract.getExpiryDate());
		result.setExpiryDate(expiryDate);

		
		if(contract
				.getPricingInformation() != null){
		Object[] pricingInformation = new Object[contract
				.getPricingInformation().size()];
		for (int i = 0; i < contract.getPricingInformation().size(); i++) {
			pricingInformation[i] = convert((PricingInformation) contract
					.getPricingInformation().get(i));
		}
		result.setPricingInformation(pricingInformation);
		}

		result.setNote(convert(contract.getNote()));
		
		if(contract
				.getAdminOptions() != null){
		Object[] adminOptions = new Object[contract.getAdminOptions().size()];
		for (int i = 0; i < contract.getAdminOptions().size(); i++) {
			adminOptions[i] = convert((AdminOption) contract.getAdminOptions()
					.get(i));
		}
		result.setAdminOptions(adminOptions);
		}
		
		if(contract.getRules() != null){
		Object[] rules = new Object[contract.getRules().size()];
		for (int i = 0; i < contract.getRules().size(); i++) {
			rules[i] = convert((Rule) contract.getRules().get(i));
		}
		result.setRules(rules);
		}

		result.setProduct(convert(contract.getProduct()));

		result.setProductCode(convert(contract.getProductCode()));
		result.setStandardPlanCode(convert(contract.getStandardPlanCode()));
		result.setBenefitPlan(convert(contract.getBenefitPlan()));
		result.setProductFamily(convert(contract.getProductFamily()));
		result.setCorporatePlanCode(convert(contract.getCorporatePlanCode()));
		result.setBrandName(convert(contract.getBrandName()));
		result
				.setCobAdjudicationIndcator(convert(contract
						.getCobAdjudicationIndcator()));
		result.setMedicareAdjudicationIndicator(convert(contract
				.getMedicareAdjudicationIndicator()));
		result.setItsAdjudicationIndicator(convert(contract
				.getItsAdjudicationIndicator()));
		result.setRegulatoryAgency(convert(contract.getRegulatoryAgency()));
		result.setComplianceStatus(convert(contract.getComplianceStatus()));
		result.setProductNameCode(convert(contract.getProductNameCode()));
		Calendar contractTermDate = Calendar.getInstance();
		if (contract.getContractTermDate() != null)
			contractTermDate.setTime(contract.getContractTermDate());
		result.setContractTermDate(contractTermDate);
		result.setMultiPlanIndicator(convert(contract.getMultiPlanIndicator()));
		result.setPerformanceGuarentee(convert(contract.getPerformanceGuarentee()));
		result.setSalesMarketDate(convert(contract.getSalesMarketDate()));
		
		if(contract.getMembershipInformations() != null){
		Object[] membershipInformations = new Object[contract
				.getMembershipInformations().size()];
		for (int i = 0; i < contract.getMembershipInformations().size(); i++) {
			membershipInformations[i] = convert((MembershipInformation) contract
					.getMembershipInformations().get(i));
		}

		result.setMembershipInformations(membershipInformations);
		}

		return result;
	}

	/**
	 * 
	 * Method to convert from ws model bean to model bean
	 * 
	 * @param message
	 * @return
	 */

	private static Message convert(
			com.wellpoint.wpd.common.contract.ws.model.Message message) {

		if (message == null)
			return null;

		Message result = new Message();

		result.setRuleCategoryName(message.getRuleCategoryName());
		result.setRuleCategoryDescription(message.getRuleCategoryDescription());
		result.setValidationMessage(message.getValidationMessage());

		return result;

	}

	/**
	 * 
	 * Method to convert from ws model bean to model bean
	 * 
	 * @param messages
	 * @return
	 */

	public static Messages convert(
			com.wellpoint.wpd.common.contract.ws.model.Messages messages) {

		if (messages == null)
			return null;

		Messages result = new Messages();
		List messageList = new ArrayList();
		if(messages.getMessages() != null){
		for (int i = 0; i < messages.getMessages().length; i++) {

			messageList
					.add(convert((com.wellpoint.wpd.common.contract.ws.model.Message) messages
							.getMessages()[i]));
		}
		}

		result.setMessages(messageList);

		return result;
	}

	/**
	 * 
	 * Method to convert a vector of ws model bean to model bean list
	 * 
	 * @param ruleSet
	 * @return
	 */
	public static List convertRuleSet(Vector ruleSet) {

		List result = new ArrayList();
		if(ruleSet != null){
		for (int i = 0; i < ruleSet.size(); i++) {
			result
					.add(convert((com.wellpoint.wpd.common.contract.ws.model.RuleCategory) ruleSet
							.get(i)));
		}
		}

		return result;

	}

	/**
	 * 
	 * Method to convert a List of strings to an Object array
	 * 
	 * @param list
	 * @return
	 */
	private static Object[] convertStringListToObjectArray(List list) {

		if (list == null)
			return null;

		Object[] result = new Object[list.size()];

		for (int i = 0; i < list.size(); i++) {
			result[i] = convert((String) list.get(i));
		}

		return result;
	}
	
	public static String convert(String val){
		String result="";
		if(val!= null){
			char arr[]=val.toCharArray();
			for(int i=0;i<arr.length;i++)
				if(arr[i] == (char)0)
					result+="";
				else
					result+=""+arr[i];
			
		}
		return result;
	}
}

