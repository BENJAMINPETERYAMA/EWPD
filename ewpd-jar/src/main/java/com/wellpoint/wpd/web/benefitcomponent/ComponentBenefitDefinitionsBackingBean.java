/*
 * ComponentBenefitDefinitionsBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.benefitcomponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.RandomStringUtils;

import com.wellpoint.wpd.business.datatype.locatecriteria.DataTypeLocateResult;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.benefitcomponent.request.LocateComponentsBenefitDefinitionRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.UpdateBenefitLinesRequest;
import com.wellpoint.wpd.common.benefitcomponent.response.LocateComponentsBenefitDefinitionResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.UpdateBenefitLinesResponse;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitLineForViewVO;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitWrapperVO;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLevel;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Bean to get the benefit definition of the attached benefit in a Benefit Component 
 * This bean will bind with the jsp pages.
 * ComponentBenefitDefinitionsBackingBean contains the getters and setters of the 
 * variables and respective functions
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ComponentBenefitDefinitionsBackingBean extends BenefitComponentBackingBean {

	// variable declarations

	private HtmlPanelGrid headerPanel = new HtmlPanelGrid();

	private HtmlPanelGrid panel = new HtmlPanelGrid();

	List benefitLevelList;

	List benefitLineList;

	List benefitDefinitionsList;

	List hideLevelsList;

	List hideHeaderPanelLevelsList; //for header display

	private String selectedRow;

	private HashMap levelIdMap = new HashMap();

	private HashMap lineBenefitValueMap = new HashMap();

	private HashMap lineIdMap = new HashMap();

	private HashMap lineFreqValueMap = new HashMap();
	//added this field to increase the perfomance
	private HashMap hiddenLineFreqValueMap = new HashMap();//hiddenBenefitValueMap

	private String levelsToDelete;

	private int benefitComponentId;

	private String benefitComponent;

	private String headerDisplay; // for header display if there are hidden lines

	private String visibleLinesDisplay; //11/29

	private boolean mandate;

	//Change Hide Level/Line

	private HashMap levelVisibleMap = new HashMap(); //for level visible check box

	private HashMap lineVisibleMap = new HashMap(); //for line visible check box

	private boolean showHidden = false; //for show All check box

	private boolean levelVisible; //for level visible check box

	private boolean lineVisible; //for line visible check box

	private boolean editFlag = true;

	private List validationMessages = new ArrayList(10);

	//added new fields for solving perfomance issue
	private HashMap hiddenBenefitValueMap = new HashMap();

	private HashMap hiddenLevelFlagMap = new HashMap();

	private HashMap hiddenLineFlagMap = new HashMap();

	private String state = WebConstants.STATE;

	private String status = WebConstants.STATUS_BUILDING;

	private int version = WebConstants.VERSION;

	private BenefitWrapperVO benefitWrapperVO = null;
	//Hash Map for level description
	private Map dataHiddenValDesc = new HashMap();
	
	private Map dataHiddenOutputValDesc = new HashMap();	
	
	private Map dataHiddenValQualifier = new HashMap();
	
	private Map dataHiddenValTerm = new HashMap();
	
	private HashMap hiddenLowerLevelFreqValueMap = new HashMap();
	
	private HashMap dataHiddenLowerLevelValDesc = new HashMap();
	
	private boolean viewMode = false;
	
	private String dummyVar;
	
	private boolean errorFlag = true;
	
	private List informationMessageToDisplayOnPage = new ArrayList();

	public String getcompBenDefPrint() {
		return "componentBenefitDefinitionsPrint";
	}

	/**
	 * @return state
	 * 
	 * Returns the state.
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 * 
	 * Sets the state.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return status
	 * 
	 * Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 * 
	 * Sets the status.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return version
	 * 
	 * Returns the version.
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version
	 * 
	 * Sets the version.
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return hiddenLevelFlagMap
	 * 
	 * Returns the hiddenLevelFlagMap.
	 */
	public HashMap getHiddenLevelFlagMap() {
		return hiddenLevelFlagMap;
	}

	/**
	 * @param hiddenLevelFlagMap
	 * 
	 * Sets the hiddenLevelFlagMap.
	 */
	public void setHiddenLevelFlagMap(HashMap hiddenLevelFlagMap) {
		this.hiddenLevelFlagMap = hiddenLevelFlagMap;
	}

	/**
	 * @return hiddenLineFlagMap
	 * 
	 * Returns the hiddenLineFlagMap.
	 */
	public HashMap getHiddenLineFlagMap() {
		return hiddenLineFlagMap;
	}

	/**
	 * @param hiddenLineFlagMap
	 * 
	 * Sets the hiddenLineFlagMap.
	 */
	public void setHiddenLineFlagMap(HashMap hiddenLineFlagMap) {
		this.hiddenLineFlagMap = hiddenLineFlagMap;
	}

	/**
	 * @return hiddenBenefitValueMap
	 * 
	 * Returns the hiddenBenefitValueMap.
	 */
	public HashMap getHiddenBenefitValueMap() {
		return hiddenBenefitValueMap;
	}

	/**
	 * @param hiddenBenefitValueMap
	 * 
	 * Sets the hiddenBenefitValueMap.
	 */
	public void setHiddenBenefitValueMap(HashMap hiddenBenefitValueMap) {
		this.hiddenBenefitValueMap = hiddenBenefitValueMap;
	}

	/**
	 * Constructor to set the bread crumb text
	 *
	 */
	public ComponentBenefitDefinitionsBackingBean() {
		super();
		// set the breadcrumbtext for view
		String mode = getBenefitComponentSessionObject()
				.getBenefitComponentMode();
		if (mode.equals("View")) {
			String name = getBenefitComponentSessionObject()
					.getBenefitComponentName();
			this
					.setBreadCrumbText("Product Configuration >> Benefit Component "
							+ "(" + name + ") >> View");
		}
		// set the bread crumb text 
		else {
			String breadCrumbText = "Product Configuration >> Benefit Component ("
					+ getBenefitComponentSessionObject()
							.getBenefitComponentName()
					+ /*
					 ") >> Standard Definition(" + getSession().getAttribute("SESSION_BNFT_NM").toString() + */") >> Edit";
			this.setBreadCrumbText(breadCrumbText);
		}

	}

	/**
	 * Method to load Standard Defifntion page
	 * @return
	 */
	public String loadStandardDefinition() { // Also Used for benefit level/line hide 
		getRequest().setAttribute("RETAIN_Value", "");
		return "loadStandardDefinition";
	}

	/**
	 * 
	 * @return string for componentbenefit definitions view
	 */
	public String loadStandardDefinitionView() {

		return "loadStandardDefinitionView";
	}

	/**
	 * Benefit level/line hide
	 * @return
	 */

	public String loadAssociatedBenefitLines() {

		return "loadStandardDefinition";

	}

	/**
	 * @return Returns the benefitWrapperBO.
	 */
	public BenefitWrapperVO getBenefitWrapperVO() {
		return benefitWrapperVO;
	}

	/**
	 * @param benefitWrapperBO
	 *            The benefitWrapperBO to set.
	 */
	public void setBenefitWrapperVO(BenefitWrapperVO benefitWrapperVO) {
		this.benefitWrapperVO = benefitWrapperVO;
	}

	private List benefitLevelsListForView;

	/**
	 * @param benefitLevelsListForView The benefitLevelsListForView to set.
	 */
	public void setBenefitLevelsListForView(List benefitLevelsListForView) {
		this.benefitLevelsListForView = benefitLevelsListForView;
	}

	/**
	 * @return Returns the benefitLevelsListForView.
	 */
	public List getBenefitLevelsListForView() {
		this.benefitLevelsListForView = new ArrayList(
				benefitLevelList == null ? 0 : benefitLevelList.size());
		BenefitLineForViewVO benefitLineForViewVO;
		BenefitLevel benefitLevel;
		List benefitLines;
		//  BenefitWrapperVO benefitWrapperVO = this.getBenefitWrapperVO();
		editFlag = false;
		List benefitDefinitonsList = this.getBenefitDefinitionsList();
		int idCount = 0;
		int minLength;
		int maxLength;
		if (null != benefitDefinitonsList) {

			BenefitLine benefitLine = null;
			//BenefitLevelVO benefitLevelVO = null;

			int rowNumber = 0;

			getValuesFromDefinitonList(benefitDefinitonsList);
			boolean  flag = this.isViewMode();
        	//FIX START
			if(this.isViewMode()){
				minLength = 14;
				maxLength = 28;
			}else{
				minLength = 16;
				maxLength = 32;				
			} 
        	//FIX END
			if (null != benefitLevelList && !benefitLevelList.isEmpty()) {

				// Collections.sort(benefitLevelList);

				for (int i = 0; i < benefitLevelList.size(); i++) {
					int levelId = 0;

					if (benefitLevelList.size() > 0) {
						benefitLevel = (BenefitLevel) benefitLevelList.get(i);
						benefitLines = benefitLevel.getBenefitLines();

						//  List benefitLines = benefitLineBO.getBenefitLines();

						if (null != benefitLines && !benefitLines.isEmpty()) {

							//Collections.sort(benefitLines);
							int lineCount = 0;
							for (int j = 0; j < (benefitLines.size() + 1); j++) {

								int benefitLineId = 0;
								String seq = WebConstants.EMPTY_STRING;
								String description = WebConstants.EMPTY_STRING;
								String term= WebConstants.EMPTY_STRING;  
								String qualifier = WebConstants.EMPTY_STRING;  
								String pva = WebConstants.EMPTY_STRING;
								String format = WebConstants.EMPTY_STRING;
								String benefitValue = WebConstants.EMPTY_STRING;
								String reference = WebConstants.EMPTY_STRING;
								boolean notesExist = false;
								boolean renderNotesAttachmentImage = false;

								int lineId = 0;
								if (benefitLines.size() > 0) {
									benefitLine = (BenefitLine) benefitLines
											.get(lineCount);
								}

								if (null != benefitLine) {

									description = benefitLevel.getLevelDesc();
									pva = benefitLine
											.getProviderArrangementId();
									format = benefitLine.getDataTypeLegend();
									if(null != benefitLevel.getTermDesc()){
										String termVal = benefitLevel.getTermDesc().replaceAll(",",", ");
										term = termVal;
									}
									if(null != benefitLevel.getFrequencyDesc()){
										if(null != benefitLevel.getFrequencyDesc() 
												&& !"0".equalsIgnoreCase(benefitLevel.getFrequencyDesc()))
										qualifier = benefitLevel.getFrequencyDesc()+" - ";
									}
									if(null != benefitLevel.getQualifierDesc()){
										if(null !=qualifier && !WebConstants.EMPTY_STRING.equalsIgnoreCase(qualifier)
												&& !"-".equalsIgnoreCase(qualifier.trim())){
											qualifier += benefitLevel.getQualifierDesc().replaceAll(",",", ");
										}else{
											qualifier = benefitLevel.getQualifierDesc().replaceAll(",",", ");
										}
									}
										benefitValue = (WPDStringUtil.spaceSeparatedString(benefitLine
												.getBenefitValue(),9));
									reference = benefitLine.getReferenceDesc();
									benefitLineId = benefitLine.getLineSysId();
									if (null != benefitLine
											.getBnftLineNotesExist()) {
										notesExist = benefitLine
												.getBnftLineNotesExist()
												.toUpperCase().equals("Y");
									}
								}

								if (j == 0) {
									pva = WebConstants.EMPTY_STRING;
									format = WebConstants.EMPTY_STRING;
									benefitValue = WebConstants.EMPTY_STRING;
									reference = WebConstants.EMPTY_STRING;
									renderNotesAttachmentImage = false;

								} else {
									seq = WebConstants.EMPTY_STRING;
									description = WebConstants.EMPTY_STRING;
									term = WebConstants.EMPTY_STRING;  
									qualifier = WebConstants.EMPTY_STRING;
																	
									String benefitType = getBenefitComponentSessionObject().bcComponentType;

									if (null != benefitType
											&& !"".equals(benefitType)) {
										if (benefitType
												.equals(WebConstants.STD_TYPE)) {
											renderNotesAttachmentImage = true;
										} else if (benefitType
												.equals(WebConstants.MNDT_TYPE)) {
											renderNotesAttachmentImage = false;
										}
									}
								}

								benefitLineForViewVO = new BenefitLineForViewVO();
		                    	String desc = null;		                    	
		                        if(description.length()>minLength){
		                        	String[] strTokenizerArr = description.split(" ");
		                        	for(int num=0;num<strTokenizerArr.length;num++){
		                        		if(strTokenizerArr[num].length()>maxLength){
		                        			strTokenizerArr[num] = strTokenizerArr[num].substring(0,minLength)+" "+
		                        				strTokenizerArr[num].substring(minLength,maxLength)+" "+strTokenizerArr[num].substring(maxLength);
		                        		}else if(strTokenizerArr[num].length()>minLength){
		                        			strTokenizerArr[num] = strTokenizerArr[num].substring(0,minLength)+" "+strTokenizerArr[num].substring(minLength);
		                        		}
		                        	}
		                        	for(int num=0;num<strTokenizerArr.length;num++){
		                        		if(null==desc)
		                        			desc = strTokenizerArr[num];
		                        		else
		                        			desc = desc +" "+ strTokenizerArr[num];
		                        	}
		                        	description = desc;	
		                        }
								benefitLineForViewVO
										.setDescription(description);
								benefitLineForViewVO.setTerm(term); 
								benefitLineForViewVO.setQualifier(qualifier); 
								benefitLineForViewVO.setPva(pva);
								benefitLineForViewVO.setFormat(format);
								benefitLineForViewVO
										.setBenefitValue(benefitValue);
								benefitLineForViewVO.setReference(reference);
								benefitLineForViewVO.setBenefitLineId(""
										+ benefitLineId);
								benefitLineForViewVO.setNotesExist(notesExist);
								benefitLineForViewVO
										.setRenderNotesAttachmentImage(renderNotesAttachmentImage);

								this.benefitLevelsListForView
										.add(benefitLineForViewVO);

								idCount++;
								if (j >= 1)
									lineCount++;
								else
									lineCount = 0;
							}
						}
					}
				}
			}
		}
		return benefitLevelsListForView;
	}
	/**
	 * This method is to save the updated lines
	 * @return
	 */
	public String save() {
		
		TimeHandler th = new TimeHandler();
    	Logger.logInfo(th.startPerfLogging("U23914_Benefit_Component_Coverage","ComponentBenefitDefinitionsBackingBean","save"));		
		
		boolean checkFlag = false;
		boolean levelIdChecked = false;
		boolean levelDescChecked = false;
		
		//Maps from session as part of stabilization release
		
		dataHiddenOutputValDesc = getBenefitComponentSessionObject().getDataHiddenOutputValDescFromSession();		
		dataHiddenLowerLevelValDesc = (HashMap) getBenefitComponentSessionObject().getDataHiddenLowerLevelValDescFromSession();
		levelIdMap = (HashMap) getBenefitComponentSessionObject().getLevelIdMapFromSession();
		hiddenLevelFlagMap = (HashMap) getBenefitComponentSessionObject().getHiddenLevelFlagMapSession();
		dataHiddenValTerm = getBenefitComponentSessionObject().getDataHiddenValTermSession();
		dataHiddenValQualifier = getBenefitComponentSessionObject().getDataHiddenValQualifierFromSession();
		hiddenLineFreqValueMap = (HashMap) getBenefitComponentSessionObject().getHiddenLineFreqValueMapFromSession();
		hiddenLowerLevelFreqValueMap = (HashMap) getBenefitComponentSessionObject().getHiddenLowerLevelFreqValueMapFromSession();
		hiddenLineFlagMap =(HashMap) getBenefitComponentSessionObject().getHiddenLineFlagMapFromSession();
		lineIdMap = (HashMap) getBenefitComponentSessionObject().getLineIdMapFromSession();
		
		
		// get the all details from the map and make each row in the page as a Benefit Level 
		List updatedBenefitLevelList = new ArrayList(
				this.getLineIdMap() == null ? 0 : this.getLineIdMap().keySet()
						.size());
		//List latestBenefitLevelList = new ArrayList();
		boolean validationFlag = true;
		//boolean allLineHide = false; // benefit level/line hide
		int lines = 0;
		List messages = new ArrayList();
		String truncatedLvlDesc = null;
		boolean descriptionFlag = true;
		if (null != this.getLevelIdMap()
				&& null != this.getLineBenefitValueMap()
				&& null != this.getLineFreqValueMap()
				&& null != this.getLineIdMap()) {
			// get the key set from the map
			Set levelIdKeySet = this.getLevelIdMap().keySet();
			Set lineBnftValKeySet = this.getLineBenefitValueMap().keySet();
			Set lineIdKeySet = this.getLineIdMap().keySet();
			Set levelFreqValKeySet = this.getLineFreqValueMap().keySet();

			//**All level hide
			if (isAllLevelHidden(levelIdKeySet)) {
				validationFlag = false;
			} else {
				//**End

				// levelId Iterator
				Iterator levelIdIterator = levelIdKeySet.iterator();
				// iterate the levelIdKeySet
				while (levelIdIterator.hasNext()) {

					// get the levelId Key
					Object levelIdKey = levelIdIterator.next();
					// levelId
					
					String levelId = this.getLevelIdMap().get(levelIdKey)
							.toString();
					
					// ** Benefit Level/line hide
					Boolean levelHide = (Boolean) this.getLevelVisibleMap()
							.get(new Long(Integer.parseInt(levelId)));
					//** End
					levelIdChecked = false;
					levelDescChecked = false;
					// lineId Iterator
					Iterator lineIdIterator = lineIdKeySet.iterator();
					// iterate the lineIdKeySet
					while (lineIdIterator.hasNext()) {
						lines++;
						// get the lineIdKey
						Object lineIdKey = lineIdIterator.next();
						// get the levelId separately form the lineLevelVal
						String levelLineVal = this.getLineIdMap()
								.get(lineIdKey).toString();
						//** Benefit Level/line hide
						Boolean lineHide = (Boolean) this.getLineVisibleMap()
								.get(lineIdKey);
						//** End
						String appendedLevelVal = null;
						String appendedLineVal = null;
						String appendedDataTypeId = null;
						String appendedLevelDesc = null;
						if (null != levelLineVal && !levelLineVal.equals("")) {
							// **Change**                  

							// new code
							StringTokenizer tokenizer = new StringTokenizer(
									levelLineVal, ":");
							int i = 0;
							while (tokenizer.hasMoreTokens()) {
								String tokens = tokenizer.nextToken();
								if (i == 0) {
									appendedLineVal = tokens;
								} else if (i == 1) {
									appendedLevelVal = tokens;
								} else if (i == 2) {
									appendedDataTypeId = tokens;
								} else if (i == 3) {
									appendedLevelDesc = tokens;
								}
								i++;
							}
							// **End**                    	
						}
						// compare the appendedLevelKey with the levelIdKey
						if (levelIdKey.toString().equals(appendedLevelVal)) {
							// Create the benefit line bo Object
							BenefitLine updatedBenefitLine = new BenefitLine();
							if (null != levelId && !levelId.equals("")) {
								updatedBenefitLine.setLevelSysId(Integer
										.parseInt(levelId));

								//**Benefit Level/line hide
								if (levelHide.booleanValue()) {
									updatedBenefitLine.setLevelHide("T");
									updatedBenefitLine.setLineHide("T");
								} else {
									updatedBenefitLine.setLevelHide("F");
									//updatedBenefitLine.setLineHide("F");
								}
								updatedBenefitLine.setLevelHideStatus(levelHide
										.booleanValue());
								//updatedBenefitLine.setLineHideStatus(lineHide.booleanValue());
								//** End
							}
							// line id
							if (null != appendedLineVal
									&& !appendedLineVal.equals("")) {
								updatedBenefitLine.setLineSysId(new Integer(
										appendedLineVal).intValue());

							}
							// lineBnftVal Iterator
							Iterator lineBnftValIterator = lineBnftValKeySet
									.iterator();
							// line bnftValue
							while (lineBnftValIterator.hasNext()) {
								// get the lineIdBnftValKey
								Object lineIdBnftValKey = lineBnftValIterator
										.next();
								// compare the lineId key with the lineLevelBnftValKey
								if (lineIdBnftValKey.equals(lineIdKey)) {
									checkFlag = true;
									// check whether the override value is Valid.
									if (null != this.getLineBenefitValueMap()
											.get(lineIdBnftValKey)) {
										if (!isValid(appendedDataTypeId, this
												.getLineBenefitValueMap().get(
														lineIdBnftValKey)
												.toString(), appendedLevelDesc)) {
											//return "componentBenefitDefinition";
											validationFlag = false;
										}
										// **End**                                    
										updatedBenefitLine.setBenefitValue(this
												.getLineBenefitValueMap().get(
														lineIdBnftValKey)
												.toString());

										//  change for solving performace issue on 10th Dec 2007
										Object oldBenefitValue = hiddenBenefitValueMap.get(lineIdBnftValKey);
										
										String newBenefitValue = this.getLineBenefitValueMap().get(lineIdBnftValKey).toString();
										
										if(null!=newBenefitValue && newBenefitValue.equals("null"))
											newBenefitValue=null;
										
										String oldBnftValue = oldBenefitValue.toString();										
										
										if(null!=oldBnftValue && oldBnftValue.equals("null"))
											oldBnftValue=null;
										
										
										if(null == oldBnftValue || oldBnftValue.equals("")){
											if(null != newBenefitValue && !newBenefitValue.equals("")){
												updatedBenefitLine.setModified(true);
											}
										}
										if(null != oldBnftValue && !oldBnftValue.equals("")){
											if(null != newBenefitValue && !newBenefitValue.equals("")&& !oldBnftValue.equals(newBenefitValue)){
												
												updatedBenefitLine.setModified(true);
											}
										}
										if(null != oldBnftValue && !oldBnftValue.equals("")){
											if(null == newBenefitValue || newBenefitValue.equals("")){
												
												updatedBenefitLine.setModified(true);
											}
										}
										
									}
									//end

									//**Benefit Level/line hide
									if (!levelHide.booleanValue()) {
										if (lineHide.booleanValue())
											updatedBenefitLine.setLineHide("T");
										else {
											updatedBenefitLine.setLineHide("F");
										}
									}
									updatedBenefitLine
											.setLineHideStatus(lineHide
													.booleanValue());
									//** End

									// change for performace issue on 11th Dec 2007
									Object oldLevelFlagObject = this
											.getLevelIdMap().get(levelIdKey);
									Object oldLevelFlagValue = hiddenLevelFlagMap
											.get(new Long(oldLevelFlagObject
													.toString()));
									boolean lineFlag = true;
									if (levelHide.booleanValue()) {
										if (null != oldLevelFlagValue
												&& oldLevelFlagValue.toString()
														.equals("false")) {
											
											updatedBenefitLine.setModified(true);
											//isSeqChanged = true;
										}
									} else {
										Object oldLineFlagValue = hiddenLineFlagMap
												.get(lineIdKey);
										if (oldLineFlagValue.equals("false")) {
											lineFlag = false;
										} else {
											lineFlag = true;
										}
										//Boolean lineFlag = (Boolean)(oldLineFlagValue);
										if (!(lineHide.booleanValue() == lineFlag)) {
											
											updatedBenefitLine.setModified(true);
										}
									}
									//end
									//	                                }
									break;
								}
							}
//							Start -- Saving the Frequency

							// levelFreqVal Iterator
							Iterator levelFreqValIterator = levelFreqValKeySet
									.iterator();
							// level Frequency Value
							while (levelFreqValIterator.hasNext()) {
								// get the levelIdFreqValKey
								Object levelIdFreqValKey = levelFreqValIterator
										.next();
								// compare the lineId key with the lineLevelFreqValKey
								if (levelId.equals(levelIdFreqValKey.toString())) {
									// check whether the override value is Valid.
									if (null != this.getLineFreqValueMap()
											.get(levelIdFreqValKey)) {
										//Frequency Validation should be checked once per level
										if(!levelIdChecked){
											if (!isValidFrequency(appendedDataTypeId, this
													.getLineFreqValueMap().get(levelIdFreqValKey).toString(), appendedLevelDesc, validationFlag)) {
												validationFlag = false;
											}
											levelIdChecked = true;
										}
										String val = this.getLineFreqValueMap().get(
												levelIdFreqValKey).toString();
										if(null != val && !"".equals(val)){
											boolean isNumber = WPDStringUtil.isNumber(this
													.getLineFreqValueMap().get(
															levelIdFreqValKey).toString());
											if(isNumber){
												int freqVal = Integer.parseInt(this
														.getLineFreqValueMap().get(
																levelIdFreqValKey).toString());
												updatedBenefitLine.setFrequencyValue(freqVal);
												//Added to increase the performance
												//Checking if frequency value is changed then only this would persist.
												Object oldLevelFreqVal = hiddenLineFreqValueMap.get(levelIdFreqValKey);
												if (null != oldLevelFreqVal
														&& null != levelIdFreqValKey.toString()
														&& !((this.getLineFreqValueMap()
																.get(levelIdFreqValKey).toString())
																.equals(oldLevelFreqVal.toString()))) {
													
													updatedBenefitLine.setModified(true);
												}
											}											
										}
									}
									break;
								}
							}
							//End -- Saving the Frequency
							//Description Change Start
							boolean isDescriptionTrunkated = false;
							Object descriptionValue;
							Object descriptionOutputValue;
							Object oldFrequencyValue;
							Object newFrequencyValue;
							Object termValue;
							Object qualifierValue;
							int noOfTokens;
							Object lowerLevelFrequencyValue;
							Object lowerLevelDescriptionValue;
							//description value binded with input text dataHiddenValDesc dataHiddenOutputValDesc
							descriptionOutputValue = dataHiddenOutputValDesc.get(new Long(levelId.toString().trim()));
							descriptionValue = dataHiddenValDesc.get(new Long(levelId.toString().trim()));
							//frequency value binded with input hidden
							oldFrequencyValue = hiddenLineFreqValueMap.get(new Long(levelId.toString().trim()));
							//frequency value binded with input text
							newFrequencyValue = lineFreqValueMap.get(new Long(levelId.toString().trim()));
							//term value binded with input text
							termValue = dataHiddenValTerm.get(new Long(levelId.toString().trim()));
							//qualifier value binded with input text
							qualifierValue = dataHiddenValQualifier.get(new Long(levelId.toString().trim()));	
							lowerLevelFrequencyValue = hiddenLowerLevelFreqValueMap.get(new Long(levelId.toString().trim()));
							lowerLevelDescriptionValue = dataHiddenLowerLevelValDesc.get(new Long(levelId.toString().trim()));
							if(!StringUtil.isEmpty(descriptionValue)){
							//Checking null all the object(description, term, qualifier, frequency)
							if((!StringUtil.isEmpty(descriptionValue)) && (!StringUtil.isEmpty(qualifierValue)) && (!StringUtil.isEmpty(termValue)) && (!StringUtil.isEmpty(oldFrequencyValue)) && (!StringUtil.isEmpty(newFrequencyValue)) && (WPDStringUtil.isNumber(newFrequencyValue.toString().trim()))){								
								if(descriptionOutputValue.toString().toUpperCase().trim().equals(descriptionValue.toString().toUpperCase().trim())){
									String description = descriptionValue.toString().toUpperCase().trim();
									String term = termValue.toString().trim();
									//Fix for Aggregate term Start
									term = WPDStringUtil.commaSeparatedString(term);
									
									String qualifier = qualifierValue.toString().trim();
									qualifier = WPDStringUtil.commaSeparatedString(qualifier);
									
									String frequency = oldFrequencyValue.toString().trim();
									String changeDesc;
									//checking if the frequency value is 1
									if(frequency.equalsIgnoreCase(WebConstants.ACTION_BENEFIT)){									
										//description is combination of term qualifier and frequency
					                	changeDesc  = term+WebConstants.PER_STRING+qualifier;
					                }else{
					                	changeDesc  = term+WebConstants.PER_STRING+frequency+WebConstants.SPACE_STRING+qualifier;
					                }//Compares the old description and new description
									//FIX START
									if(changeDesc.length() > 32){
										changeDesc = changeDesc.substring(0,32).trim();
									}
									if(description.length() > 32){
										description = changeDesc.substring(0,32).trim();
									}
									//FIX END
					                if(description.equalsIgnoreCase(changeDesc)){
					                	if(!(newFrequencyValue.toString().trim().equalsIgnoreCase(oldFrequencyValue.toString().trim()))){
						                	int frequencyValue = new Integer(newFrequencyValue.toString().trim()).intValue();
						                	frequency = frequencyValue+WebConstants.EMPTY_STRING;
						                	if(frequency.equalsIgnoreCase(WebConstants.ACTION_BENEFIT)){									
												//description is combination of term qualifier and frequency
							                	changeDesc  = term+WebConstants.PER_STRING+qualifier;
							                }else{
							                	changeDesc  = term+WebConstants.PER_STRING+frequency+WebConstants.SPACE_STRING+qualifier;
							                }//Compares the old description and new description
						                	//Keeping only 32 characters, while level description is more than 32 characters
						                	if(null !=changeDesc && changeDesc.length()>32){
						                		changeDesc = changeDesc.substring(0,32).trim();
						                		isDescriptionTrunkated = true;
						                	}
						                	updatedBenefitLine.setLevelDesc(changeDesc);
					                	}else{
					                		description = descriptionValue.toString().toUpperCase().trim();
						                	if(null !=description && description.length()>32){
						                		description = description.substring(0,32);
						                		isDescriptionTrunkated = true;
						                	}
					                		updatedBenefitLine.setLevelDesc(description);
					                	}
					                }else{
					                	if(lowerLevelFrequencyValue.toString().toUpperCase().trim().equals(newFrequencyValue.toString().toUpperCase().trim())){
					                		//updatedBenefitLine.setFrequencyValue(new Integer(lowerLevelFrequencyValue.toString()).intValue());
											String lvlDescriptionValue = lowerLevelDescriptionValue.toString().toUpperCase().trim();
											if(lvlDescriptionValue.length() > 32){
												lvlDescriptionValue = lvlDescriptionValue.substring(0,32).trim();
												updatedBenefitLine.setLevelDesc(lvlDescriptionValue);
						                		isDescriptionTrunkated = true;
											}
											updatedBenefitLine.setLevelDesc(lvlDescriptionValue);
					                	}else{
						                	//Keeping only 32 characters, while level description is more than 32 characters
						                	description = descriptionValue.toString().toUpperCase().trim();
						                	if(null !=description && description.length()>32){
						                		description = description.substring(0,32);
						                		isDescriptionTrunkated = true;
						                	}
											updatedBenefitLine.setLevelDesc(description);
					                	}
									}
							}else{
								String lvlDescriptionValue = descriptionValue.toString().toUpperCase().trim();
			                	//Keeping only 32 characters, while level description is more than 32 characters
			                	if(null !=lvlDescriptionValue && lvlDescriptionValue.length()>32){
			                		lvlDescriptionValue = lvlDescriptionValue.substring(0,32).trim();
			                		updatedBenefitLine.setLevelDesc(lvlDescriptionValue);
			                		isDescriptionTrunkated = true;
			                	}else{
									updatedBenefitLine.setLevelDesc(lvlDescriptionValue);
			                	}
			                	updatedBenefitLine.setModified(true);
							}							
							//FIX	
							}else{
			                	//Keeping only 32 characters, while level description is more than 32 characters
			                	if(null !=descriptionValue && descriptionValue.toString().length()>32){
									String lvlDescriptionValue = descriptionValue.toString().toUpperCase().trim().substring(0,32);
			                		updatedBenefitLine.setLevelDesc(lvlDescriptionValue);
			                		isDescriptionTrunkated = true;
			                	}else{
									updatedBenefitLine.setLevelDesc(descriptionValue.toString().toUpperCase().trim());
			                	}
							}
							}else if(descriptionFlag){
								validationMessages.add(new ErrorMessage(
				                        WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_REQUIRED));
								validationFlag = false;
								descriptionFlag = false;
							}
							if(null != descriptionValue){
								//FIX START
									if(isDescriptionTrunkated && (!levelDescChecked)){
										truncatedLvlDesc = updatedBenefitLine.getLevelDesc();
										InformationalMessage informationalMessage = new InformationalMessage(WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_LENGTH);
										informationalMessage.setParameters(new String[] { truncatedLvlDesc });
										messages.add(informationalMessage);
										isDescriptionTrunkated = false;
										levelDescChecked = true;
									}
								//FIX END
							}
							
							updatedBenefitLine
									.setEntitySysId(getBenefitComponentSessionObject()
											.getBenefitComponentId());
							updatedBenefitLine.setBenefitSysId(Integer
									.parseInt(getSession().getAttribute(
											"SESSION_BNFT_ID").toString()));
							// add the updatedQuestion to the list
							updatedBenefitLevelList.add(updatedBenefitLine);

						}
					}

				}
			}
			if (validationFlag) {

				/* connect to the database and update the details of benefitLevels */
				// create the request
				UpdateBenefitLinesRequest updateBenefitLinesRequest = (UpdateBenefitLinesRequest) this
						.getServiceRequest(ServiceManager.UPDATE_BENEFIT_LINES_REQUEST);
				// set the updatedBenefitLEvelsList to the request
				updateBenefitLinesRequest
						.setUpdatedBenefitLines(updatedBenefitLevelList);
				// TODO set the version, key and identifier
				updateBenefitLinesRequest
						.setMainObjectIdentifier(getBenefitComponentSessionObject()
								.getBenefitComponentName());
				updateBenefitLinesRequest
						.setMainObjectKey(getBenefitComponentSessionObject()
								.getBenefitComponentId());
				updateBenefitLinesRequest
						.setVersionNumber(getBenefitComponentSessionObject()
								.getBenefitComponentVersionNumber());
				updateBenefitLinesRequest
						.setDomainList(getBenefitComponentSessionObject()
								.getBusinessDomainList());
				// get the response
				UpdateBenefitLinesResponse updateBenefitLinesResponse =(UpdateBenefitLinesResponse) executeService(updateBenefitLinesRequest);
				updateBenefitLinesResponse.getMessages();
				
				//added the message from the response to the class variable --Defect fix for WAS7 Migration
				informationMessageToDisplayOnPage = updateBenefitLinesResponse.getMessages();
				
				//Adding Frequency/Level description messages
				if(null != messages && messages.size()>0){
					List allMessages = new ArrayList();
					allMessages = updateBenefitLinesResponse.getMessages();
					allMessages.addAll(messages);
				}

			} else {
				addAllMessagesToRequest(validationMessages);
				return WebConstants.EMPTY_STRING;
			}
		}
		getRequest().setAttribute("RETAIN_Value", "");
		Logger.logInfo(th.endPerfLogging());
		return "componentBenefitDefinition";
	}

	/**
	 * Validating Frequency Value
	 * 
	 * @param dataTypeId
	 * @param value
	 * @param levelDesc
	 * @return
	 */
	private boolean isValidFrequency(String dataTypeId, String value,String levelDesc, boolean validationFlag){
		boolean isValid = true;
		boolean isNumber = true;
		int dataType = 0;
		
		ErrorMessage errorMessage = null;

		if(null != value && !"".equals(value)){
			isNumber = WPDStringUtil.isNumber(value);

			if (!isNumber) {
				errorMessage = new ErrorMessage(
						BusinessConstants.MSG_BENEFIT_LEVEL_FREQUENCY_NOT_NUMBER);
            	//Checking null for the description. Sets the description to the 
            	//error message to state validation happen for that description
				if (null != levelDesc)
					errorMessage.setParameters(new String[] { levelDesc });
				validationMessages.add(errorMessage);
				addAllMessagesToRequest(validationMessages);
				isValid = false;
			}else if(isErrorFlag()){
				//Checking that frequency value should not be less than 1
				int freqVal = Integer.parseInt(value);
				if(freqVal==0)
				{
					errorMessage = new ErrorMessage(
					WebConstants.MSG_BENEFIT_LEVEL_FREQUENCY_NOT_CORRECT);
					validationMessages.add(errorMessage);
					addAllMessagesToRequest(validationMessages);
					isValid = false;
					setErrorFlag(false);
				}
			}
		}else{
			//Checking that Frequency can not be empty.
			errorMessage = new ErrorMessage(
					BusinessConstants.MSG_BENEFIT_LEVEL_FREQUENCY_NOT_EMPTY);
			if (null != levelDesc)
				errorMessage.setParameters(new String[] { levelDesc });
			validationMessages.add(errorMessage);
			addAllMessagesToRequest(validationMessages);
			isValid = false;
		}
		
		return isValid;
	}	
	/**
	 * 
	 * @return
	 */
	private boolean isValid(String dataTypeId, String value, String levelDesc) {
		//List validationMessages = null;
		boolean isValid = true;
		String sysDataType = null;
		String dataTypeName = null;
		int dataType = 0;
		boolean isNumber = true;
		boolean isDecimalNumber = true;
		boolean isGreaterThanHundred = true;
		boolean isBooleanFlag = true;
		boolean isMaxInteger = false;
		boolean isValidPrecision = true;

		if (null != dataTypeId && !"".equals(dataTypeId)) {
			dataType = Integer.parseInt(dataTypeId);
		}
		List universeDataTypeList = WPDStringUtil.getUniverseDataTypeList();
		if (dataType != 0) {
			DataTypeLocateResult dataTypeDetails = null;
			dataTypeDetails = WPDStringUtil.getDataTypeDetails(
					universeDataTypeList, dataType);
			if (null != dataTypeDetails) {
				sysDataType = dataTypeDetails.getDataTypeDesc().toUpperCase()
						.trim();
				dataTypeName = dataTypeDetails.getDataTypeName().toUpperCase()
						.trim();
			}
		}
		ErrorMessage errorMessage = null;
		if (null != value && !"".equals(value) && null != sysDataType
				&& !"".equals(sysDataType)) {
			if ("BOOLEAN".equals(sysDataType.toUpperCase()))
				isBooleanFlag = true;
			else if ("DOLLAR".equals(sysDataType.toUpperCase()))
				isNumber = WPDStringUtil.isNumber(value);
			else if ("PERCENTAGE".equals(sysDataType.toUpperCase())) {
				isDecimalNumber = WPDStringUtil.isDecimalNumber(value);
				if (isDecimalNumber) {
					isValidPrecision = WPDStringUtil.isValidPrecision(value,
							WebConstants.ALLOWED_NUMBER_OF_PRECISION);
					if (isValidPrecision) {
						isGreaterThanHundred = WPDStringUtil
								.isGreaterThanHundred(value);
					}
				}
			} else if ("INTEGER".equals(sysDataType.toUpperCase())) {
				isNumber = WPDStringUtil.isNumber(value);
				if (isNumber) {
					isMaxInteger = WPDStringUtil.isMaxInteger(value);
				}
			} else if ("FLOAT".equals(sysDataType.toUpperCase())) {
				isDecimalNumber = WPDStringUtil.isDecimalNumber(value);
				if (isDecimalNumber) {
					isValidPrecision = WPDStringUtil.isValidPrecision(value,
							WebConstants.ALLOWED_NUMBER_OF_PRECISION);
				}
			}
		}
		if (!isBooleanFlag || !isNumber || !isDecimalNumber
				|| !isGreaterThanHundred || isMaxInteger || !isValidPrecision) {
			isValid = false;
		}
		if (!isBooleanFlag) {
			errorMessage = new ErrorMessage(
					WebConstants.DATA_TYPE_MISMATCH_BENEFIT_LEVEL);
			if (null != dataTypeName && null != levelDesc)
				errorMessage.setParameters(new String[] { dataTypeName,
						levelDesc });
			validationMessages.add(errorMessage);
			addAllMessagesToRequest(validationMessages);
		}
		if (!isNumber) {
			errorMessage = new ErrorMessage(
					"benefitlevel.benefit.value.not.number");
			if (null != dataTypeName && null != levelDesc)
				errorMessage.setParameters(new String[] { dataTypeName,
						levelDesc });
			validationMessages.add(errorMessage);
			addAllMessagesToRequest(validationMessages);
		}
		if (isMaxInteger) {
			errorMessage = new ErrorMessage(
					"benefitlevel.benefit.value.max.integer");
			if (null != dataTypeName && null != levelDesc)
				errorMessage.setParameters(new String[] { dataTypeName,
						levelDesc });
			validationMessages.add(errorMessage);
			addAllMessagesToRequest(validationMessages);
		}
		if (!isDecimalNumber) {
			errorMessage = new ErrorMessage(
					"benefitlevel.benefit.value.not.decimalnumber");
			if (null != dataTypeName && null != levelDesc)
				errorMessage.setParameters(new String[] { dataTypeName,
						levelDesc });
			validationMessages.add(errorMessage);
			addAllMessagesToRequest(validationMessages);
		}
		if (!isValidPrecision) {
			errorMessage = new ErrorMessage(
					"benefitlevel.benefit.value.valid.precision");
			if (null != dataTypeName && null != levelDesc)
				errorMessage
						.setParameters(new String[] {
								String
										.valueOf(WebConstants.ALLOWED_NUMBER_OF_PRECISION),
								dataTypeName, levelDesc });
			validationMessages.add(errorMessage);
			addAllMessagesToRequest(validationMessages);
		}
		if (!isGreaterThanHundred) {
			errorMessage = new ErrorMessage(
					"benefitlevel.benefit.value.greater.hundred");
			if (null != dataTypeName && null != levelDesc)
				errorMessage.setParameters(new String[] { dataTypeName,
						levelDesc });
			validationMessages.add(errorMessage);
			addAllMessagesToRequest(validationMessages);
		}
		return isValid;
	}

	//**All level hide
	private boolean isAllLevelHidden(Set levelIdKeySet) {
		// levelId Iterator
		Iterator levelIdIterator1 = levelIdKeySet.iterator();
		int count = 0;
		ErrorMessage errorMessage = null;
		// iterate the levelIdKeySet
		while (levelIdIterator1.hasNext()) {
			// get the levelId Key
			Object levelIdKey = levelIdIterator1.next();
			// levelId
			String levelId = this.getLevelIdMap().get(levelIdKey).toString();
			//** Benefit Level/line hide
			Boolean levelHide = (Boolean) this.getLevelVisibleMap().get(
					new Long(Integer.parseInt(levelId)));
			if (levelHide.booleanValue()) {
				count++;
			}
			
		}
		if (this.getLevelIdMap().size() == count) {
			errorMessage = new ErrorMessage(WebConstants.ALL_LEVEL_HIDE);

			validationMessages.add(errorMessage);
			addAllMessagesToRequest(validationMessages);
			return true;
		}
		return false;
	}

	/**
	 * This method is used to get the levels which is to be hide
	 *
	 */
	private void hideSelectedLevel() {
		// gets the level ids to delete which contains the ids with ~ appended
		String levelIds = this.getLevelsToDelete();
		List levelIdsList = new ArrayList(2);
		levelIdMap = (HashMap) getBenefitComponentSessionObject().getLevelIdMapFromSession();
		if (null != levelIds && !levelIds.equals("")) {
			// check whether ~ is there
			int indexOfTilda = levelIds.indexOf("~");
			if (indexOfTilda != -1) {
				StringTokenizer tokenizer = new StringTokenizer(levelIds, "~");
				while (tokenizer.hasMoreTokens()) {
					levelIdsList.add(tokenizer.nextToken());
				}
			} else {
				levelIdsList.add(levelIds);
			}
		}
		List levelsForHide = new ArrayList(levelIdsList.size());
		for (int i = 0; i < levelIdsList.size(); i++) {
			int selectedRowNumber = 0;
			if (null != (String) levelIdsList.get(i)
					&& !((String) levelIdsList.get(i)).equals("")) {
				selectedRowNumber = new Integer((String) levelIdsList.get(i))
						.intValue();
			}
			// iterate the levelId map and get the selected levelId
			if (null != this.getLevelIdMap()) {
				// get the key set from the map
				Set levelIdMapKeySet = this.getLevelIdMap().keySet();
				// iterate the keyset and match the corresponding key and take the selected levelId
				Iterator levelIdMapKeySetIterator = levelIdMapKeySet.iterator();
				while (levelIdMapKeySetIterator.hasNext()) {
					Object key = levelIdMapKeySetIterator.next();
					if (null != key) {
						if (new Integer(key.toString()).intValue() == selectedRowNumber) {
							// get the selected level Id
							
							int selectedLevelId = new Integer(this
									.getLevelIdMap().get(key).toString())
									.intValue();
							levelsForHide.add(new Integer(selectedLevelId));
							
						}
					}

				}
			}
		}
		this.setHideLevelsList(levelsForHide);
	}

	/** This method sets the benefitLevelValues to the PanelGrid
	 * @param i
	 * @param benefitLevelValues
	 */
	private void setBenefitLevelValuesToGrid(int i,
			BenefitLevel benefitLevelValues, int lineSize, int rowNum,
			int lineCount, boolean isLevelHideStatus) {
		// level desc
		HtmlOutputText levelDesc = new HtmlOutputText();
		HtmlInputText levelDescInputText = new HtmlInputText();
    	String description = benefitLevelValues.getLevelDesc().trim();
		if (null != benefitLevelValues.getLevelDesc()) {
        	String desc = null;
            if(description.length()>18){
            	String[] strTokenizerArr = description.split(" ");
            	for(int num=0;num<strTokenizerArr.length;num++){
            		if(strTokenizerArr[num].length()>18){
            			strTokenizerArr[num] = strTokenizerArr[num].substring(0,18)+" "+
            				strTokenizerArr[num].substring(18);
            		}            	}
            	for(int num=0;num<strTokenizerArr.length;num++){
            		if(null==desc)
            			desc = strTokenizerArr[num];
            		else
            			desc = desc +" "+ strTokenizerArr[num];
            	}
            	description = desc;
            }
			levelDesc.setValue(description);
			levelDescInputText.setValue(benefitLevelValues.getLevelDesc().trim());
		} else {
			levelDesc.setValue(WebConstants.EMPTY_STRING);
			levelDescInputText.setValue(WebConstants.EMPTY_STRING);
		}		
		//levelDesc.setId("levelDesc" + benefitLevelValues.getLevelId());
		levelDesc.setId("levelDesc" + i);
		//FIX
		HtmlInputHidden hiddenForOutputDescription = new HtmlInputHidden();
		hiddenForOutputDescription.setId("hidOutputValDesc" + i);        
		hiddenForOutputDescription.setValue(benefitLevelValues.getLevelDesc().trim().replaceAll(",",", "));
		
		//Commented as part of stabilization release
        /* ValueBinding hidOutputValDesc = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{ComponentBenefitDefinitionsBackingBean.dataHiddenOutputValDesc["+ benefitLevelValues.getLevelId() +"]}");
        hiddenForOutputDescription.setValueBinding("value",hidOutputValDesc);*/
		
		this.dataHiddenOutputValDesc.put(new Long(benefitLevelValues.getLevelId()),benefitLevelValues.getLevelDesc().trim().replaceAll(",",", "));
		
		//FIX
		//START setting the Lower level description in the hidden 
		HtmlInputHidden hiddenLowerLevelDescription = new HtmlInputHidden();
		hiddenLowerLevelDescription.setId("hidLowerLevelValDesc" + i);        
		hiddenLowerLevelDescription.setValue(benefitLevelValues.getLowerLevelDescValue().trim());
        
		//Commented as part of stabilization release
		/*ValueBinding hidLowerLevelValDesc = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{ComponentBenefitDefinitionsBackingBean.dataHiddenLowerLevelValDesc["+ benefitLevelValues.getLevelId() +"]}");
        hiddenLowerLevelDescription.setValueBinding("value",hidLowerLevelValDesc);*/
		this.dataHiddenLowerLevelValDesc.put(new Long(benefitLevelValues.getLevelId()),benefitLevelValues.getLowerLevelDescValue().trim());
		       
        
		//END Lower level description in the hidden 
		levelDescInputText.setStyleClass("formInputField");
		levelDescInputText.setId("levelDescInputText" + benefitLevelValues.getLevelId());
		//DESCRIPTION FIX START
		if (!BusinessUtil.isSystemGeneratedDescription(benefitLevelValues
				.getLevelDesc(), benefitLevelValues.getTermDesc(),
				benefitLevelValues.getQualifierDesc(), Integer
						.parseInt(benefitLevelValues.getFrequencyDesc()))){
						
			if(benefitLevelValues.getFrequencyDesc().equalsIgnoreCase(benefitLevelValues.getLowerLevelFrequencyValue())){
				
				levelDescInputText.setStyle("width:125px;display:none");
			}else{
				levelDesc.setStyle("display:none");
				levelDescInputText.setStyle("width:125px");
				
			}
		}else{
			levelDescInputText.setStyle("width:125px;display:none");
		}
		//DESCRIPTION FIX END
		levelDescInputText.setMaxlength(32);
		//Binding the value for description text 
        ValueBinding hidValItemDesc = FacesContext
                .getCurrentInstance()
                .getApplication()
                .createValueBinding(
                        "#{ComponentBenefitDefinitionsBackingBean.dataHiddenValDesc["
                                + benefitLevelValues.getLevelId() + 
                                "]}");
        levelDescInputText.setValueBinding("value", hidValItemDesc);
        //Setting the value binding to the frequency field HtmlInputText
		// level PVA
		HtmlOutputText levelPVA = new HtmlOutputText();
		levelPVA.setValue(" ");

		// level BenefitValue
		HtmlOutputText levelBnftValue = new HtmlOutputText();
		levelBnftValue.setValue(" ");

		// level Reference
		HtmlOutputText levelReference = new HtmlOutputText();
		levelReference.setValue(" ");

		// hidden level id
		HtmlInputHidden hiddenLevelId = new HtmlInputHidden();
		hiddenLevelId.setId("hiddenLevelId" + i);
		hiddenLevelId.setValue(new Integer(benefitLevelValues.getLevelId()));
		// set the value to the map

		//Commented as part of stabilization release
		/*ValueBinding levelIdValBind = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{ComponentBenefitDefinitionsBackingBean.levelIdMap["
								+ i + "]}");
		hiddenLevelId.setValueBinding("value", levelIdValBind);*/
		
		this.levelIdMap.put(new Long(i),new Integer(benefitLevelValues.getLevelId()));

		//**Change level/line hide
		HtmlSelectBooleanCheckbox levelHideCheckBox = new HtmlSelectBooleanCheckbox();
		levelHideCheckBox.setId("checkBox" + "A" + i + "A" + lineSize + "A"
				+ lineCount);
		levelHideCheckBox.setValue(Boolean.valueOf(benefitLevelValues
				.isLevelHideStatus()));
		levelHideCheckBox.setValueBinding("value", FacesContext
				.getCurrentInstance().getApplication().createValueBinding(
						"#{ComponentBenefitDefinitionsBackingBean.levelVisibleMap["
								+ benefitLevelValues.getLevelId() + "]}"));
		levelHideCheckBox.setTitle("Hide/Unhide");
		//11/28
		levelHideCheckBox
				.setOnclick("checkTheCorrespondingBenefitLines(this);");
		//**end

		//Change for Performance on Dec 11th 2007
		HtmlInputHidden hiddenLevelFlag = new HtmlInputHidden();

		hiddenLevelFlag.setId("hiddenLevelFlag" + "A" + i + "A" + lineSize
				+ "A" + lineCount);
		hiddenLevelFlag.setValue("" + benefitLevelValues.isLevelHideStatus());
		
		//Commented as part of stabilization release
		/*ValueBinding valForhiddenLevelFlag = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{ComponentBenefitDefinitionsBackingBean.hiddenLevelFlagMap["
								+ benefitLevelValues.getLevelId() + "]}");
		hiddenLevelFlag.setValueBinding("value", valForhiddenLevelFlag);*/
		// end 
		this.hiddenLevelFlagMap.put(new Long(benefitLevelValues.getLevelId()),"" + benefitLevelValues.isLevelHideStatus());
		
		HtmlOutputText noteImage = new HtmlOutputText();
		noteImage.setValue(" ");

		// combining the hidden field and delete image 
		HtmlOutputLabel lblImage = new HtmlOutputLabel();
		lblImage.setFor("levelDesc" + i);
		//lblImage.setId("lblImage" + i);
		lblImage.setId("lblImage"+RandomStringUtils.randomAlphanumeric(15));
		//sets the size to a hidden variable
		HtmlInputHidden hiddenLineSize = new HtmlInputHidden();
		hiddenLineSize.setId("hiddenLineSize" + i);
		hiddenLineSize.setValue(new Integer(lineSize));

		//      sets the size to a hidden variable
		HtmlInputHidden hiddenRowSize = new HtmlInputHidden();
		hiddenRowSize.setId("hiddenRowNum" + i);
		hiddenRowSize.setValue(new Integer(rowNum));

		//**Change level/line hide    
		if (!(this.getBenefitComponentSessionObject().bcComponentType)
				.equals("MANDATE"))
			lblImage.getChildren().add(levelHideCheckBox);
		//**end
		lblImage.getChildren().add(hiddenLevelFlag);

		lblImage.getChildren().add(hiddenLevelId);
		lblImage.getChildren().add(hiddenLineSize);
		lblImage.getChildren().add(hiddenRowSize);

		HtmlOutputText dummyText = new HtmlOutputText();
		levelPVA.setValue(" ");

			//Start - Term, Qualifier & Frequency Enhancement
			//Level Term
			HtmlOutputText levelTerm = new HtmlOutputText();
			levelTerm.setId("Term"+i);		
			if(null != benefitLevelValues.getTermDesc()){			
				String term = benefitLevelValues.getTermDesc().replaceAll(",",", ");
				levelTerm.setValue(term);
			}
			else{
				levelTerm.setValue(" ");
			}
			HtmlInputHidden hiddenForTerm = new HtmlInputHidden();
	        hiddenForTerm.setId("hiddenTerm" + i);        
	        hiddenForTerm.setValue(benefitLevelValues.getTermDesc().trim().replaceAll(",",", "));
	        
	        //Commented as part of stabilization release
	        /*ValueBinding hidValTermDesc = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{ComponentBenefitDefinitionsBackingBean.dataHiddenValTerm["+ benefitLevelValues.getLevelId() +"]}");
	        hiddenForTerm.setValueBinding("value",hidValTermDesc); */
	        
	        this.dataHiddenValTerm.put(new Long(benefitLevelValues.getLevelId()),benefitLevelValues.getTermDesc().trim().replaceAll(",",", "));
			//Level Qualifier
			HtmlOutputText levelQualifier = new HtmlOutputText();
			levelQualifier.setId("Qualifier"+i);		
			if(null != benefitLevelValues.getQualifierDesc()){				       
				levelQualifier.setValue(benefitLevelValues.getQualifierDesc().replaceAll(",",", "));			
			}
			else{
				levelQualifier.setValue(" ");
			}
			HtmlInputHidden hiddenForQualifier = new HtmlInputHidden();
			hiddenForQualifier.setId("hiddenQualifier" + i);        
			if(null != benefitLevelValues.getQualifierDesc()){
				hiddenForQualifier.setValue(benefitLevelValues.getQualifierDesc().trim());
			}else{
				hiddenForQualifier.setValue("");
			}
			//Commented as part of stabilization release
	        /*ValueBinding hidValQualifier = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{ComponentBenefitDefinitionsBackingBean.dataHiddenValQualifier["+ benefitLevelValues.getLevelId() +"]}");
	        hiddenForQualifier.setValueBinding("value",hidValQualifier);   */
			if(null != benefitLevelValues.getQualifierDesc()){
				this.dataHiddenValQualifier.put(new Long(benefitLevelValues.getLevelId()),benefitLevelValues.getQualifierDesc().trim());
			}else{
				this.dataHiddenValQualifier.put(new Long(benefitLevelValues.getLevelId()),"");
			}
			//Level Frequency Value
			HtmlInputText levelFrequency = new HtmlInputText();
			levelFrequency.setSize(3);
			levelFrequency.setMaxlength(3);
			levelFrequency.setId("lineFreqValue" + i);
			levelFrequency.setStyleClass("formInputField");
			levelFrequency.setStyle("width:30px;");
			levelFrequency.setValue(benefitLevelValues.getFrequencyDesc());
			levelFrequency.setOnkeypress("return isNumber(this);");
			levelFrequency.setOnchange("return descriptionChange(this)");
			levelFrequency.setDisabled(isLevelHideStatus);
			//Set the value to the map
			ValueBinding levelFreqValueBind = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{ComponentBenefitDefinitionsBackingBean.lineFreqValueMap["
									+ benefitLevelValues.getLevelId() + "]}");
			levelFrequency.setValueBinding("value", levelFreqValueBind);
			//Hidden Frequency is added to increase the perfomance
			
			HtmlInputHidden hiddenLevelFreqValue = new HtmlInputHidden();

			hiddenLevelFreqValue.setId("hiddenLevelFreqValue"+ i);
			hiddenLevelFreqValue.setValue(benefitLevelValues.getFrequencyDesc());
			//Commented as part of stabilization release
				/*ValueBinding valForhiddenLevelFreq = FacesContext
						.getCurrentInstance().getApplication().createValueBinding(
								"#{ComponentBenefitDefinitionsBackingBean.hiddenLineFreqValueMap["
										+ benefitLevelValues.getLevelId() + "]}");
				hiddenLevelFreqValue.setValueBinding("value",
						valForhiddenLevelFreq);*/
			this.hiddenLineFreqValueMap.put(new Long(benefitLevelValues.getLevelId()),benefitLevelValues.getFrequencyDesc());
			//START Hidden Lower level frequency value
				HtmlInputHidden hiddenLowerLevelFreqValue = new HtmlInputHidden();

				hiddenLowerLevelFreqValue.setId("hiddenLowerLevelFreqValue"+ i);
				hiddenLowerLevelFreqValue.setValue(benefitLevelValues.getLowerLevelFrequencyValue());
				//Commented as part of stabilization release
					/*ValueBinding valForhiddenLowerLevelFreq = FacesContext
							.getCurrentInstance().getApplication().createValueBinding(
									"#{ComponentBenefitDefinitionsBackingBean.hiddenLowerLevelFreqValueMap["
											+ benefitLevelValues.getLevelId() + "]}");
					hiddenLowerLevelFreqValue.setValueBinding("value",
							valForhiddenLowerLevelFreq);*/
				this.hiddenLowerLevelFreqValueMap.put(new Long(benefitLevelValues.getLevelId()),benefitLevelValues.getLowerLevelFrequencyValue());
				
			//END Hidden Lower level frequency value		
			//Adding a blank space with "-"
			HtmlOutputLabel blankValue = new HtmlOutputLabel();
			blankValue.setValue(" - ");
			blankValue.setId("blankValue"+RandomStringUtils.randomAlphanumeric(15));
			// Adding Frequency & Qualifier to the Label
			HtmlOutputLabel levelFreqQualValue = new HtmlOutputLabel();
			levelFreqQualValue.setId("levelFreqQualValue"+RandomStringUtils.randomAlphanumeric(15));
			//In case, If Frequency is zero, Frequency Level would not display
			if(null !=benefitLevelValues.getFrequencyDesc()
					&& Integer.parseInt(benefitLevelValues.getFrequencyDesc())>0){
				levelFreqQualValue.getChildren().add(levelFrequency);
				levelFreqQualValue.getChildren().add(blankValue);
			}
			levelFreqQualValue.getChildren().add(levelQualifier);
			levelFreqQualValue.getChildren().add(hiddenLevelFreqValue);
			levelFreqQualValue.getChildren().add(hiddenForQualifier);
			levelFreqQualValue.getChildren().add(hiddenLowerLevelFreqValue);
			//End - Term, Qualifier & Frequency Enhancement
		HtmlOutputLabel lblDesc = new HtmlOutputLabel();
		lblDesc.setId("lblDesc"+RandomStringUtils.randomAlphanumeric(15));
		lblDesc.getChildren().add(levelDesc);
		lblDesc.getChildren().add(levelDescInputText);
		lblDesc.getChildren().add(hiddenForOutputDescription);
		lblDesc.getChildren().add(hiddenLowerLevelDescription);
		HtmlOutputLabel lblTerm = new HtmlOutputLabel();
		lblTerm.setId("lblTerm"+RandomStringUtils.randomAlphanumeric(15));
		lblTerm.getChildren().add(levelTerm);
		lblTerm.getChildren().add(hiddenForTerm);
		//Adding Level,HtmlInPutBox & HtmlOutPutBox to the panel
		panel.getChildren().add(lblDesc);
		panel.getChildren().add(lblTerm);
		panel.getChildren().add(levelFreqQualValue);
		panel.getChildren().add(levelPVA);
		panel.getChildren().add(dummyText);
		panel.getChildren().add(levelBnftValue);
		panel.getChildren().add(levelReference);
		if (!(getBenefitComponentSessionObject().bcComponentType)
				.equals("MANDATE")) {
			panel.getChildren().add(noteImage);
			panel.getChildren().add(lblImage);
		}
	}

	/** This method sets the benefitLevelValues to the PanelGrid
	 * @param i
	 * @param benefitLevelValues
	 */
	private void setBenefitLevelValuesToGridForView(int i,
			BenefitLevel benefitLevelValues, int lineSize, int rowNum,
			int lineCount) {
		// level desc
		HtmlOutputText levelDesc = new HtmlOutputText();
		if (null != benefitLevelValues.getLevelDesc()) {
			levelDesc.setValue(benefitLevelValues.getLevelDesc().trim());
		} else {
			levelDesc.setValue(WebConstants.EMPTY_STRING);
		}
		levelDesc.setId("levelDesc" + benefitLevelValues.getLevelId());

		// level PVA
		HtmlOutputText levelPVA = new HtmlOutputText();
		levelPVA.setValue(" ");

		// level BenefitValue
		HtmlOutputText levelBnftValue = new HtmlOutputText();
		levelBnftValue.setValue(" ");

		// level Reference
		HtmlOutputText levelReference = new HtmlOutputText();
		levelReference.setValue(" ");

		// hidden level id
		HtmlInputHidden hiddenLevelId = new HtmlInputHidden();
		hiddenLevelId.setId("hiddenLevelId" + i);
		hiddenLevelId.setValue(new Integer(benefitLevelValues.getLevelId()));
		// set the value to the map
		ValueBinding levelIdValBind = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{ComponentBenefitDefinitionsBackingBean.levelIdMap["
								+ i + "]}");
		hiddenLevelId.setValueBinding("value", levelIdValBind);

		HtmlOutputText noteImage = new HtmlOutputText();
		noteImage.setValue(" ");

		// combining the hidden field and delete image 
		HtmlOutputLabel lblImage = new HtmlOutputLabel();
		lblImage.setFor("levelDesc" + i);
		//lblImage.setId("lblImage" + i);
		lblImage.setId("lblImage"+RandomStringUtils.randomAlphanumeric(15));
		//sets the size to a hidden variable
		HtmlInputHidden hiddenLineSize = new HtmlInputHidden();
		hiddenLineSize.setId("hiddenLineSize" + i);
		hiddenLineSize.setValue(new Integer(lineSize));

		//      sets the size to a hidden variable
		HtmlInputHidden hiddenRowSize = new HtmlInputHidden();
		hiddenRowSize.setId("hiddenRowNum" + i);
		hiddenRowSize.setValue(new Integer(rowNum));

		lblImage.getChildren().add(hiddenLevelId);
		lblImage.getChildren().add(hiddenLineSize);
		lblImage.getChildren().add(hiddenRowSize);

		HtmlOutputText dummyText = new HtmlOutputText();
		dummyText.setValue(" ");

		// add to the panel
		panel.getChildren().add(levelDesc);
		panel.getChildren().add(levelPVA);
		panel.getChildren().add(dummyText);
		panel.getChildren().add(levelBnftValue);
		panel.getChildren().add(levelReference);
		if (!(getBenefitComponentSessionObject().bcComponentType)
				.equals("MANDATE")) {
			panel.getChildren().add(noteImage);
			//panel.getChildren().add(lblImage);
		}
	}

	private void setBenefitLevelValuesToTableForView(int i,
			BenefitLevel benefitLevelValues, int lineSize, int rowNum,
			int lineCount) {
		// level desc
		HtmlOutputText levelDesc = new HtmlOutputText();
		if (null != benefitLevelValues.getLevelDesc()) {
			levelDesc.setValue(benefitLevelValues.getLevelDesc().trim());
		} else {
			levelDesc.setValue(WebConstants.EMPTY_STRING);
		}
		levelDesc.setId("levelDesc" + benefitLevelValues.getLevelId());

		// level PVA
		HtmlOutputText levelPVA = new HtmlOutputText();
		levelPVA.setValue(" ");

		// level BenefitValue
		HtmlOutputText levelBnftValue = new HtmlOutputText();
		levelBnftValue.setValue(" ");

		// level Reference
		HtmlOutputText levelReference = new HtmlOutputText();
		levelReference.setValue(" ");

		// hidden level id
		HtmlInputHidden hiddenLevelId = new HtmlInputHidden();
		hiddenLevelId.setId("hiddenLevelId" + i);
		hiddenLevelId.setValue(new Integer(benefitLevelValues.getLevelId()));
		// set the value to the map
		ValueBinding levelIdValBind = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{ComponentBenefitDefinitionsBackingBean.levelIdMap["
								+ i + "]}");
		hiddenLevelId.setValueBinding("value", levelIdValBind);

		HtmlOutputText noteImage = new HtmlOutputText();
		noteImage.setValue(" ");

		// combining the hidden field and delete image 
		HtmlOutputLabel lblImage = new HtmlOutputLabel();
		lblImage.setFor("levelDesc" + i);
		//lblImage.setId("lblImage" + i);
		lblImage.setId("lblImage"+RandomStringUtils.randomAlphanumeric(15));
		//sets the size to a hidden variable
		HtmlInputHidden hiddenLineSize = new HtmlInputHidden();
		hiddenLineSize.setId("hiddenLineSize" + i);
		hiddenLineSize.setValue(new Integer(lineSize));

		//      sets the size to a hidden variable
		HtmlInputHidden hiddenRowSize = new HtmlInputHidden();
		hiddenRowSize.setId("hiddenRowNum" + i);
		hiddenRowSize.setValue(new Integer(rowNum));

		//**Change level/line hide    
		//         if(!(this.getBenefitComponentSessionObject().bcComponentType).equals("MANDATE"))
		//         	lblImage.getChildren().add(levelHideCheckBox);	
		//**end
		//        lblImage.getChildren().add(hiddenLevelFlag);

		lblImage.getChildren().add(hiddenLevelId);
		lblImage.getChildren().add(hiddenLineSize);
		lblImage.getChildren().add(hiddenRowSize);

		HtmlOutputText dummyText = new HtmlOutputText();
		dummyText.setValue(" ");

		// add to the panel
		panel.getChildren().add(levelDesc);
		panel.getChildren().add(levelPVA);
		panel.getChildren().add(dummyText);
		panel.getChildren().add(levelBnftValue);
		panel.getChildren().add(levelReference);
		if (!(getBenefitComponentSessionObject().bcComponentType)
				.equals("MANDATE")) {
			panel.getChildren().add(noteImage);
			//panel.getChildren().add(lblImage);
		}
	}

	private void setBenefitLevelValuesToGridForPrint(int i,
			BenefitLevel benefitLevelValues, int lineSize, int rowNum,
			int lineCount) {
		// level desc
		HtmlOutputText levelDesc = new HtmlOutputText();
		if (null != benefitLevelValues.getLevelDesc()) {
			levelDesc.setValue(benefitLevelValues.getLevelDesc().trim());
		} else {
			levelDesc.setValue(WebConstants.EMPTY_STRING);
		}
		levelDesc.setId("levelDesc" + benefitLevelValues.getLevelId());

		// level PVA
		HtmlOutputText levelPVA = new HtmlOutputText();
		levelPVA.setValue(" ");

		// level BenefitValue
		HtmlOutputText levelBnftValue = new HtmlOutputText();
		levelBnftValue.setValue(" ");

		// level Reference
		HtmlOutputText levelReference = new HtmlOutputText();
		levelReference.setValue(" ");

		// hidden level id
		HtmlInputHidden hiddenLevelId = new HtmlInputHidden();
		hiddenLevelId.setId("hiddenLevelId" + i);
		hiddenLevelId.setValue(new Integer(benefitLevelValues.getLevelId()));
		// set the value to the map
		ValueBinding levelIdValBind = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{ComponentBenefitDefinitionsBackingBean.levelIdMap["
								+ i + "]}");
		hiddenLevelId.setValueBinding("value", levelIdValBind);

		HtmlOutputText noteImage = new HtmlOutputText();
		noteImage.setValue(" ");

		// combining the hidden field and delete image 
		HtmlOutputLabel lblImage = new HtmlOutputLabel();
		lblImage.setFor("levelDesc" + i);
		//lblImage.setId("lblImage" + i);
		lblImage.setId("lblImage"+RandomStringUtils.randomAlphanumeric(15));
		//sets the size to a hidden variable
		HtmlInputHidden hiddenLineSize = new HtmlInputHidden();
		hiddenLineSize.setId("hiddenLineSize" + i);
		hiddenLineSize.setValue(new Integer(lineSize));

		//      sets the size to a hidden variable
		HtmlInputHidden hiddenRowSize = new HtmlInputHidden();
		hiddenRowSize.setId("hiddenRowNum" + i);
		hiddenRowSize.setValue(new Integer(rowNum));

		//**Change level/line hide    
		//         if(!(this.getBenefitComponentSessionObject().bcComponentType).equals("MANDATE"))
		//         	lblImage.getChildren().add(levelHideCheckBox);	
		//**end
		//        lblImage.getChildren().add(hiddenLevelFlag);

		lblImage.getChildren().add(hiddenLevelId);
		lblImage.getChildren().add(hiddenLineSize);
		lblImage.getChildren().add(hiddenRowSize);

		HtmlOutputText dummyText = new HtmlOutputText();
		dummyText.setValue(" ");

		// add to the panel
		panel.getChildren().add(levelDesc);
		panel.getChildren().add(levelPVA);
		panel.getChildren().add(dummyText);
		panel.getChildren().add(levelBnftValue);
		panel.getChildren().add(levelReference);
		//        if(!(this.getBenefitComponentSessionObject().bcComponentType).equals("MANDATE")){
		//	        panel.getChildren().add(noteImage);	
		//panel.getChildren().add(lblImage);
		//        }
	}

	/** This method gets the values from the benefit definiton List and sets it to the level list and line list
	 * @param benefitDefinitionsList
	 */
	private void getValuesFromDefinitonList(List benefitDefinitionsList) {
		benefitLevelList = new ArrayList(benefitDefinitionsList.size());
		for (int i = 0; i < benefitDefinitionsList.size(); i++) {
			BenefitLine entityBenefitLine = (BenefitLine) benefitDefinitionsList
					.get(i);
			// sets values to the benefitLevel List
			setValuesToBenefitLevel(entityBenefitLine, benefitLevelList);
		}
	}

	/**This method sets values to the benefit level List
	 * @param entityBenefitLine
	 * @param benefitLevelList
	 */
	private void setValuesToBenefitLevel(BenefitLine entityBenefitLine,
			List benefitLevelList) {
		BenefitLevel benefitLevelBO = null;
		BenefitLevel tempBO = null;
		boolean checkFlag = false;
		// checks if the benefit level list size is not equal to zero
		if (benefitLevelList.size() != 0) {
			for (int i = 0; i < benefitLevelList.size(); i++) {
				tempBO = (BenefitLevel) benefitLevelList.get(i);
				if (tempBO.getLevelId() == entityBenefitLine.getLevelSysId()) {
					benefitLevelBO = (BenefitLevel) benefitLevelList.get(i);
					checkFlag = true;
				}
			}
		}
		// checks if the benefit LevelList size is 0 or if the previous levelId is equal to the present levelId
		if (benefitLevelList.size() == 0 || !checkFlag) {
			BenefitLevel entityBenefitLevel = new BenefitLevel();
			entityBenefitLevel.setLevelDesc(entityBenefitLine.getLevelDesc());
			entityBenefitLevel.setLevelId(entityBenefitLine.getLevelSysId());
			entityBenefitLevel.setQualifierDesc(entityBenefitLine
					.getQualifierDesc());
			entityBenefitLevel.setTermDesc(entityBenefitLine.getTermDesc());			
			entityBenefitLevel.setFrequencyDesc(entityBenefitLine.getFrequencyValue()+"");
			//Setting the Benefit levels decription and 
			entityBenefitLevel.setLowerLevelDescValue(entityBenefitLine.getLowerLevelDescValue());
			entityBenefitLevel.setLowerLevelFrequencyValue(entityBenefitLine.getLowerLevelFrequencyValue()+"");
			//**Benefit Level/Line hide
			if (null != entityBenefitLine.getLevelHide()) {
				if (entityBenefitLine.getLevelHide().equals("T"))
					entityBenefitLevel.setLevelHideStatus(true);
				else
					entityBenefitLevel.setLevelHideStatus(false);
			}
			//** End

			//sets benefit lines to the benefit levels
			entityBenefitLevel.setBenefitLines(new ArrayList(1));
			entityBenefitLevel.getBenefitLines().add(
					getBenefitLineBO(entityBenefitLine));
			benefitLevelList.add(entityBenefitLevel);
		} else {
			// sets benefit lines to the benefit levels
			benefitLevelBO.getBenefitLines().add(
					getBenefitLineBO(entityBenefitLine));
		}
	}

	/**
	 * This method sets values to benefit Lines
	 * @param entityBenefitLine
	 * @return
	 */

	private BenefitLine getBenefitLineBO(BenefitLine entityBenefitLine) {
		BenefitLine entityBenefitLineToSet = new BenefitLine();
		entityBenefitLineToSet.setBenefitValue(entityBenefitLine
				.getBenefitValue());
		//Setting the non-overridden benefit Values as part of Enhancement
		entityBenefitLineToSet.setLineValue(entityBenefitLine.getLineValue());
		entityBenefitLineToSet.setProviderArrangementDesc(entityBenefitLine
				.getProviderArrangementDesc());
		entityBenefitLineToSet.setLineSysId(entityBenefitLine.getLineSysId());
		entityBenefitLineToSet.setDataTypeDesc(entityBenefitLine
				.getDataTypeDesc());
		entityBenefitLineToSet.setDataTypeId(entityBenefitLine.getDataTypeId());
		entityBenefitLineToSet.setReferenceDesc(entityBenefitLine
				.getReferenceDesc());
		entityBenefitLineToSet.setDataTypeLegend(entityBenefitLine
				.getDataTypeLegend());
		entityBenefitLineToSet.setProviderArrangementId(entityBenefitLine
				.getProviderArrangementId());
		entityBenefitLineToSet.setBnftLineNotesExist(entityBenefitLine
				.getBnftLineNotesExist());
		//      **Benefit Level/Line hide
		if (null != entityBenefitLine.getLevelHide()
				&& null != entityBenefitLine.getLineHide()) {
			if (entityBenefitLine.getLevelHide().equals("T")) {
				entityBenefitLineToSet.setLevelHideStatus(true);
			} else {
				entityBenefitLineToSet.setLevelHideStatus(false);
			}
			if (entityBenefitLine.getLineHide().equals("T")) {
				entityBenefitLineToSet.setLineHideStatus(true);
			} else {
				entityBenefitLineToSet.setLineHideStatus(false);
			}
		}
		//** End

		return entityBenefitLineToSet;
	}

	/**
	 * This method sets the benefitLineValues to the panel Grid
	 * 
	 * @param benefitLevelValues
	 * @param j
	 * @param benefitLineValues
	 * @param i
	 */
	private void setBenefitLineValuesToGridForView(
			BenefitLevel benefitLevelValues, int j,
			BenefitLine benefitLineValues, int i, int lineSize, int lineNum) {
		// line desc
		HtmlOutputText lineDesc = new HtmlOutputText();
		lineDesc.setValue(" ");

		// line PVA
		HtmlOutputText linePVA = new HtmlOutputText();
		linePVA.setValue(benefitLineValues.getProviderArrangementId());

		// line DataType
		HtmlOutputText lineDataType = new HtmlOutputText();
		lineDataType.setValue(benefitLineValues.getDataTypeDesc());

		// line BenefitValue
		HtmlOutputText lineBnftValue = new HtmlOutputText();
		//        HtmlSelectOneMenu seloneMenuForBnftValue = new HtmlSelectOneMenu();
		UIComponent object = null;

		String sysDataType = null;
		int dataType = 0;
		String dataTypeId = benefitLineValues.getDataTypeId();
		if (null != dataTypeId && !"".equals(dataTypeId)) {
			dataType = Integer.parseInt(dataTypeId);
		}
		List universeDataTypeList = WPDStringUtil.getUniverseDataTypeList();
		if (dataType != 0) {
			// verify            
			DataTypeLocateResult dataTypeDetails = null;
			dataTypeDetails = WPDStringUtil.getDataTypeDetails(
					universeDataTypeList, dataType);
			if (null != dataTypeDetails) {
				sysDataType = dataTypeDetails.getDataTypeDesc().toUpperCase()
						.trim();
			}
		}
		//output text for view
		HtmlOutputText lineBnftValueView = new HtmlOutputText();
		lineBnftValueView.setId("lineBnftValueView" + j + "_" + i);
		lineBnftValueView.setValue(benefitLineValues.getBenefitValue());
		HtmlInputHidden hiddenBenefitValue = new HtmlInputHidden();

		if (null != sysDataType) {

			//Change for Performance on Dec 10th 2007
			hiddenBenefitValue.setId("hiddenBenefitValue" + j + "_" + i);
			hiddenBenefitValue.setValue(""
					+ benefitLineValues.getBenefitValue());
			ValueBinding valForhiddenBenefitValue = FacesContext
					.getCurrentInstance().getApplication().createValueBinding(
							"#{ComponentBenefitDefinitionsBackingBean.hiddenBenefitValueMap["
									+ benefitLineValues.getLineSysId() + "]}");
			hiddenBenefitValue.setValueBinding("value",
					valForhiddenBenefitValue);
			// end 
			if (sysDataType.equals("BOOLEAN")) {
				// Code changed as part of the Enhancement to display the benefit values same as 
				//that of the benefit value in the benefit

				if (null != benefitLineValues.getBenefitValue()
						&& !benefitLineValues.getBenefitValue().equals("")) {
					if (benefitLineValues.getBenefitValue().equals("YES")) {
						lineBnftValueView.setValue("YES");

					} else if (benefitLineValues.getBenefitValue().equals("NO")) {
						lineBnftValueView.setValue("NO");
					} else if (benefitLineValues.getBenefitValue().equals("Y")) {
						lineBnftValueView.setValue("Y");
					} else if (benefitLineValues.getBenefitValue().equals("N")) {
						lineBnftValueView.setValue("N");
					} else {
						lineBnftValueView.setValue("");
					}
				}

			} else {
				lineBnftValue.setId("lineBnftValue" + j + "_" + i);
				lineBnftValue.setValue(benefitLineValues.getBenefitValue());
				ValueBinding bnftValueBinding = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{ComponentBenefitDefinitionsBackingBean.lineBenefitValueMap["
										+ benefitLineValues.getLineSysId()
										+ "]}");
				lineBnftValue.setValueBinding("value", bnftValueBinding);
				lineBnftValue.setStyle("width:75px;");
			}
		}
		// **End**        

		// lineEmptyString
		HtmlOutputText lineEmptyString = new HtmlOutputText();
		lineEmptyString.setValue(" ");

		HtmlOutputText lineEmptyString2 = new HtmlOutputText();
		lineEmptyString2.setValue(" ");

		// hidden field for storing the benefitLineSysId
		HtmlInputHidden hiddenLineId = new HtmlInputHidden();
		hiddenLineId.setId("hiddenLineId" + j + "_" + i);
		// **change        
		hiddenLineId.setValue(new Integer(benefitLineValues.getLineSysId())
				+ ":" + i + ":" + benefitLineValues.getDataTypeId() + ":"
				+ benefitLevelValues.getLevelDesc());
		// **end        
		// set the value to the map
		ValueBinding lineIdValBind = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{ComponentBenefitDefinitionsBackingBean.lineIdMap["
								+ benefitLineValues.getLineSysId() + "]}");
		hiddenLineId.setValueBinding("value", lineIdValBind);

		// combining the lineBenefitValue, DataType, hiddenLineIdField and EmptyString
		HtmlOutputLabel lblBnftValue = new HtmlOutputLabel();
		lblBnftValue.setId("lblBnftValue"+RandomStringUtils.randomAlphanumeric(15));
		//lblBnftValue.setId("lblBnftValue" + j + "_" + i);
		lblBnftValue.setFor("lineBnftValue" + j + "_" + i);
		// **Change**        

		if (null != sysDataType) {
			lblBnftValue.getChildren().add(hiddenBenefitValue);

			if (sysDataType.equals("DOLLAR")) {
				if (!(getBenefitComponentSessionObject().bcComponentType)
						.equals("MANDATE"))
					lblBnftValue.getChildren().add(lineBnftValue);
				else
					lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(lineEmptyString);
				lblBnftValue.getChildren().add(hiddenLineId);
			} else if (sysDataType.equals("PERCENTAGE")) {
				if (!(getBenefitComponentSessionObject().bcComponentType)
						.equals("MANDATE"))
					lblBnftValue.getChildren().add(lineBnftValue);
				else
					lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(lineEmptyString);
				lblBnftValue.getChildren().add(hiddenLineId);
			} else if (sysDataType.equals("STRING")) {
				if (!(this.getBenefitComponentSessionObject().bcComponentType)
						.equals("MANDATE"))
					lblBnftValue.getChildren().add(lineBnftValue);
				else
					lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(lineEmptyString);
				lblBnftValue.getChildren().add(hiddenLineId);
			} else if (sysDataType.equals("BOOLEAN")) {
				lblBnftValue.getChildren().add(lineEmptyString);
				if (!(this.getBenefitComponentSessionObject().bcComponentType)
						.equals("MANDATE"))
					lblBnftValue.getChildren().add(lineBnftValueView);
				else
					lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(hiddenLineId);
			} else {
				if (!(this.getBenefitComponentSessionObject().bcComponentType)
						.equals("MANDATE"))
					lblBnftValue.getChildren().add(lineBnftValueView);
				else
					lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(lineEmptyString);
				lblBnftValue.getChildren().add(hiddenLineId);
			}
		}
		// **End**        

		// line reference
		HtmlOutputText lineReference = new HtmlOutputText();
		lineReference.setValue(benefitLineValues.getReferenceDesc());

		// line image
		HtmlOutputText lineImage = new HtmlOutputText();
		lineDesc.setValue(" ");

		// ******Change - Override BenefitLine Notes at benefitComponent
		HtmlOutputLabel lblRefAndNotes = new HtmlOutputLabel();
		//lblBnftValue.setId("lblRefAndNotes" + j + "_" + i);
		lblRefAndNotes.setId("lblRefAndNotes"+RandomStringUtils.randomAlphanumeric(15));
		lblRefAndNotes.setFor("lblRefAndNotes" + j + "_" + i);

		HtmlGraphicImage noteImage = new HtmlGraphicImage();
		noteImage.setUrl("../../images/notes_exist.gif");
		noteImage.setStyle("cursor:hand;");
		noteImage.setId("noteImage" + j + "_" + i);

		HtmlCommandButton notesButton = new HtmlCommandButton();
		notesButton.setValue("NotesButton");
		if (benefitLineValues.getBnftLineNotesExist().equals("Y"))
			notesButton.setImage("../../images/notes_exist.gif");
		else
			notesButton.setImage("../../images/page.gif");
		notesButton.setTitle("Note");
		notesButton.setStyle("border:0;");
		notesButton.setAlt("Notes");
		notesButton.setId("notesButton" + j + "_" + i);
		notesButton.setOnclick("notesAction("
				+ benefitLineValues.getLineSysId() + ");return false;");
		lblRefAndNotes.getChildren().add(lineReference);
		// Enhancement		
		// Display notesButton only if the componentType is STANDARD
		if (!(this.getBenefitComponentSessionObject().bcComponentType)
				.equals("MANDATE")) {
			lineImage.getChildren().add(notesButton);
		}

		// End - Enhancement	

		// lgnd data type
		HtmlOutputText lgndDataType = new HtmlOutputText();
		lgndDataType.setValue(benefitLineValues.getDataTypeLegend());
		lgndDataType.setId("lgndDataType" + j + "_" + i);

		// ***** End

		//Start - Enhancement
		//line term
		HtmlOutputText lineTerm = new HtmlOutputText();
		if(null != benefitLevelValues.getTermDesc()){
			lineTerm.setValue(benefitLevelValues.getTermDesc());
		}
		else{
			lineTerm.setValue(" ");
		}

		// line Frequency & Qualifier
		HtmlOutputText lineQualifier = new HtmlOutputText();
		if(null != benefitLevelValues.getQualifierDesc()){
			lineQualifier.setValue(benefitLevelValues.getQualifierDesc());
		}
		else{
			lineQualifier.setValue(" ");
		}
		// line FrequencyValue
		HtmlInputText lineFreqValue = new HtmlInputText();
		lineFreqValue.setSize(3);
		lineFreqValue.setId("lineFreqValue" + j + "_" + i);
		lineFreqValue.setStyleClass("formInputField");
		lineFreqValue.setStyle("width:25px;");
		lineFreqValue.setValue(benefitLineValues.getFrequencyValue()+"");
		//lineQualifier.getChildren().add(lineFreqValue+" - ");
		//End - Enhancement
		
		//lblBnftValue.getChildren().add(hiddenLineFlag);
		// adding to the panel
		panel.getChildren().add(lineDesc);
		panel.getChildren().add(lineTerm);
		panel.getChildren().add(lineFreqValue);		
		panel.getChildren().add(linePVA);
		panel.getChildren().add(lgndDataType);
		panel.getChildren().add(lblBnftValue);
		panel.getChildren().add(lblRefAndNotes);

		if (!(this.getBenefitComponentSessionObject().bcComponentType)
				.equals("MANDATE")) {
			panel.getChildren().add(lineImage);
			// panel.getChildren().add(lineHideCheckBox);
		}

	}

	private void setBenefitLineValuesToGridForPrint(
			BenefitLevel benefitLevelValues, int j,
			BenefitLine benefitLineValues, int i, int lineSize, int lineNum) {
		// line desc
		HtmlOutputText lineDesc = new HtmlOutputText();
		lineDesc.setValue(" ");

		// line PVA
		HtmlOutputText linePVA = new HtmlOutputText();
		linePVA.setValue(benefitLineValues.getProviderArrangementId());

		// line DataType
		HtmlOutputText lineDataType = new HtmlOutputText();
		lineDataType.setValue(benefitLineValues.getDataTypeDesc());

		// line BenefitValue
		HtmlOutputText lineBnftValue = new HtmlOutputText();

		String sysDataType = null;
		int dataType = 0;
		String dataTypeId = benefitLineValues.getDataTypeId();
		if (null != dataTypeId && !"".equals(dataTypeId)) {
			dataType = Integer.parseInt(dataTypeId);
		}
		List universeDataTypeList = WPDStringUtil.getUniverseDataTypeList();
		if (dataType != 0) {
			// verify            
			DataTypeLocateResult dataTypeDetails = null;
			dataTypeDetails = WPDStringUtil.getDataTypeDetails(
					universeDataTypeList, dataType);
			if (null != dataTypeDetails) {
				sysDataType = dataTypeDetails.getDataTypeDesc().toUpperCase()
						.trim();
			}
		}
		//output text for view
		HtmlOutputText lineBnftValueView = new HtmlOutputText();
		lineBnftValueView.setId("lineBnftValueView" + j + "_" + i);
		lineBnftValueView.setValue(benefitLineValues.getBenefitValue());
		HtmlInputHidden hiddenBenefitValue = new HtmlInputHidden();

		if (null != sysDataType) {

			//Change for Performance on Dec 10th 2007
			hiddenBenefitValue.setId("hiddenBenefitValue" + j + "_" + i);
			hiddenBenefitValue.setValue(""
					+ benefitLineValues.getBenefitValue());
			ValueBinding valForhiddenBenefitValue = FacesContext
					.getCurrentInstance().getApplication().createValueBinding(
							"#{ComponentBenefitDefinitionsBackingBean.hiddenBenefitValueMap["
									+ benefitLineValues.getLineSysId() + "]}");
			hiddenBenefitValue.setValueBinding("value",
					valForhiddenBenefitValue);
			// end 
			if (sysDataType.equals("BOOLEAN")) {

				if (null != benefitLineValues.getBenefitValue()
						&& !benefitLineValues.getBenefitValue().equals("")) {
					if (benefitLineValues.getBenefitValue().equals("YES")) {
						lineBnftValueView.setValue("YES");

					} else if (benefitLineValues.getBenefitValue().equals("NO")) {
						lineBnftValueView.setValue("NO");
					} else if (benefitLineValues.getBenefitValue().equals("Y")) {
						lineBnftValueView.setValue("Y");
					} else if (benefitLineValues.getBenefitValue().equals("N")) {
						lineBnftValueView.setValue("N");
					} else {
						lineBnftValueView.setValue("");
					}
				}

			} else {
				lineBnftValue.setId("lineBnftValue" + j + "_" + i);
				lineBnftValue.setValue(benefitLineValues.getBenefitValue());
				ValueBinding bnftValueBinding = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{ComponentBenefitDefinitionsBackingBean.lineBenefitValueMap["
										+ benefitLineValues.getLineSysId()
										+ "]}");
				lineBnftValue.setValueBinding("value", bnftValueBinding);
				lineBnftValue.setStyle("width:75px;");
			}
		}
		// **End**        

		// lineEmptyString
		HtmlOutputText lineEmptyString = new HtmlOutputText();
		lineEmptyString.setValue(" ");

		HtmlOutputText lineEmptyString2 = new HtmlOutputText();
		lineEmptyString2.setValue(" ");

		// hidden field for storing the benefitLineSysId
		HtmlInputHidden hiddenLineId = new HtmlInputHidden();
		hiddenLineId.setId("hiddenLineId" + j + "_" + i);
		// **change        
		hiddenLineId.setValue(new Integer(benefitLineValues.getLineSysId())
				+ ":" + i + ":" + benefitLineValues.getDataTypeId() + ":"
				+ benefitLevelValues.getLevelDesc());
		// **end        
		// set the value to the map
		ValueBinding lineIdValBind = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{ComponentBenefitDefinitionsBackingBean.lineIdMap["
								+ benefitLineValues.getLineSysId() + "]}");
		hiddenLineId.setValueBinding("value", lineIdValBind);

		// combining the lineBenefitValue, DataType, hiddenLineIdField and EmptyString
		HtmlOutputLabel lblBnftValue = new HtmlOutputLabel();
		lblBnftValue.setId("lblBnftValue"+RandomStringUtils.randomAlphanumeric(15));
		//lblBnftValue.setId("lblBnftValue" + j + "_" + i);
		lblBnftValue.setFor("lineBnftValue" + j + "_" + i);
		// **Change**        

		if (null != sysDataType) {
			lblBnftValue.getChildren().add(hiddenBenefitValue);

			if (sysDataType.equals("DOLLAR")) {
				if (!(this.getBenefitComponentSessionObject().bcComponentType)
						.equals("MANDATE"))
					lblBnftValue.getChildren().add(lineBnftValue);
				else
					lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(lineEmptyString);
				lblBnftValue.getChildren().add(hiddenLineId);
			} else if (sysDataType.equals("PERCENTAGE")) {
				if (!(this.getBenefitComponentSessionObject().bcComponentType)
						.equals("MANDATE"))
					lblBnftValue.getChildren().add(lineBnftValue);
				else
					lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(lineEmptyString);
				lblBnftValue.getChildren().add(hiddenLineId);
			} else if (sysDataType.equals("STRING")) {
				if (!(this.getBenefitComponentSessionObject().bcComponentType)
						.equals("MANDATE"))
					lblBnftValue.getChildren().add(lineBnftValue);
				else
					lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(lineEmptyString);
				lblBnftValue.getChildren().add(hiddenLineId);
			} else if (sysDataType.equals("BOOLEAN")) {
				lblBnftValue.getChildren().add(lineEmptyString);

				lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(hiddenLineId);
			} else {
				if (!(this.getBenefitComponentSessionObject().bcComponentType)
						.equals("MANDATE"))
					lblBnftValue.getChildren().add(lineBnftValue);
				else
					lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(lineEmptyString);
				lblBnftValue.getChildren().add(hiddenLineId);
			}
		}
		// **End**        

		// line reference
		HtmlOutputText lineReference = new HtmlOutputText();
		lineReference.setValue(benefitLineValues.getReferenceDesc());

		// line image
		HtmlOutputText lineImage = new HtmlOutputText();
		lineDesc.setValue(" ");

		// ******Change - Override BenefitLine Notes at benefitComponent
		HtmlOutputLabel lblRefAndNotes = new HtmlOutputLabel();
		lblRefAndNotes.setId("lblRefAndNotes"+RandomStringUtils.randomAlphanumeric(15));
		//lblBnftValue.setId("lblRefAndNotes" + j + "_" + i);
		lblRefAndNotes.setFor("lblRefAndNotes" + j + "_" + i);

		lblRefAndNotes.getChildren().add(lineReference);

		// lgnd data type
		HtmlOutputText lgndDataType = new HtmlOutputText();
		lgndDataType.setValue(benefitLineValues.getDataTypeLegend());
		lgndDataType.setId("lgndDataType" + j + "_" + i);

		// ***** End

		//lblBnftValue.getChildren().add(hiddenLineFlag);
		// adding to the panel
		panel.getChildren().add(lineDesc);
		panel.getChildren().add(linePVA);
		panel.getChildren().add(lgndDataType);
		panel.getChildren().add(lblBnftValue);
		panel.getChildren().add(lblRefAndNotes);

	}

	/**
	 * This method sets the benefitLineValues to the panel Grid
	 * 
	 * @param benefitLevelValues
	 * @param j
	 * @param benefitLineValues
	 * @param i
	 */
	private void setBenefitLineValuesToGrid(BenefitLevel benefitLevelValues,
			int j, BenefitLine benefitLineValues, int i, int lineSize,
			int lineNum, boolean isHidden) {
		// line desc
		HtmlOutputText lineDesc = new HtmlOutputText();
		lineDesc.setValue(" ");

		// line PVA
		HtmlOutputText linePVA = new HtmlOutputText();
		linePVA.setValue(benefitLineValues.getProviderArrangementId());

		// line DataType
		HtmlOutputText lineDataType = new HtmlOutputText();
		lineDataType.setValue(benefitLineValues.getDataTypeDesc());

		// line BenefitValue
		HtmlInputText lineBnftValue = new HtmlInputText();
		HtmlSelectOneMenu seloneMenuForBnftValue = new HtmlSelectOneMenu();
		UIComponent object = null;

		//**Change level/line hide
		HtmlSelectBooleanCheckbox lineHideCheckBox = new HtmlSelectBooleanCheckbox();
		lineHideCheckBox.setId("checkBox" + "A" + i + "A" + lineSize + "A"
				+ lineNum + "A" + "Line");
		lineHideCheckBox.setValue(Boolean.valueOf(benefitLineValues
				.isLineHideStatus()));
		lineHideCheckBox.setValueBinding("value", FacesContext
				.getCurrentInstance().getApplication().createValueBinding(
						"#{ComponentBenefitDefinitionsBackingBean.lineVisibleMap["
								+ benefitLineValues.getLineSysId() + "]}"));
		lineHideCheckBox.setTitle("Hide/Unhide");
		lineHideCheckBox.setOnclick("checkTheCorrespondingBenefitLevel(this)");
		//**end

		//		Change for Performance on Dec 11th 2007
		HtmlInputHidden hiddenLineFlag = new HtmlInputHidden();
		hiddenLineFlag.setId("hiddenLevelFlag" + "A" + i + "A" + lineSize + "A"
				+ lineNum + "A" + "Line");
		hiddenLineFlag.setValue("" + benefitLineValues.isLineHideStatus());
		//Commented as part of stabilization release
		/*ValueBinding valForhiddenLineFlag = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{ComponentBenefitDefinitionsBackingBean.hiddenLineFlagMap["
								+ benefitLineValues.getLineSysId() + "]}");
		hiddenLineFlag.setValueBinding("value", valForhiddenLineFlag);*/
		this.hiddenLineFlagMap.put(new Long(benefitLineValues.getLineSysId()),"" + benefitLineValues.isLineHideStatus());
		// end 

		String sysDataType = null;
		int dataType = 0;
		String dataTypeId = benefitLineValues.getDataTypeId();
		if (null != dataTypeId && !"".equals(dataTypeId)) {
			dataType = Integer.parseInt(dataTypeId);
		}
		List universeDataTypeList = WPDStringUtil.getUniverseDataTypeList();
		if (dataType != 0) {
			// verify            
			DataTypeLocateResult dataTypeDetails = null;
			dataTypeDetails = WPDStringUtil.getDataTypeDetails(
					universeDataTypeList, dataType);
			if (null != dataTypeDetails) {
				sysDataType = dataTypeDetails.getDataTypeDesc().toUpperCase()
						.trim();
			}
		}
		//output text for view
		HtmlOutputText lineBnftValueView = new HtmlOutputText();
		lineBnftValueView.setId("lineBnftValueView" + j + "_" + i);
		lineBnftValueView.setValue(benefitLineValues.getBenefitValue());
		HtmlInputHidden hiddenBenefitValue = new HtmlInputHidden();

		if (null != sysDataType) {

			//Change for Performance on Dec 10th 2007
			hiddenBenefitValue.setId("hiddenBenefitValue" + j + "_" + i);
			hiddenBenefitValue.setValue(""
					+ benefitLineValues.getBenefitValue());
			ValueBinding valForhiddenBenefitValue = FacesContext
					.getCurrentInstance().getApplication().createValueBinding(
							"#{ComponentBenefitDefinitionsBackingBean.hiddenBenefitValueMap["
									+ benefitLineValues.getLineSysId() + "]}");
			hiddenBenefitValue.setValueBinding("value",
					valForhiddenBenefitValue);
			// end 
			if (sysDataType.equals("BOOLEAN")) {
				UISelectItems selectItems = new UISelectItems();
				List possibleBnftVal = new ArrayList(12);
				possibleBnftVal.add(new SelectItem("  ", "  "));

				// Code changed as part of the Enhancement to display the benefit values same as 
				//that of the benefit value in the benefit
				if (null != benefitLineValues.getBenefitValue()
						&& !benefitLineValues.getBenefitValue().equals("  ")) {

					if (benefitLineValues.getBenefitValue().equals("Y")
							|| benefitLineValues.getBenefitValue().equals("N")) {

						possibleBnftVal.add(new SelectItem("Y", "Y"));
						possibleBnftVal.add(new SelectItem("N", "N"));

					} else {
						possibleBnftVal.add(new SelectItem("YES", "YES"));
						possibleBnftVal.add(new SelectItem("NO", "NO"));
					}
				} else {
					if (null != benefitLineValues.getLineValue()
							&& !benefitLineValues.getLineValue().equals("  ")) {

						if (benefitLineValues.getLineValue().equals("Y")
								|| benefitLineValues.getLineValue().equals("N")) {

							possibleBnftVal.add(new SelectItem("Y", "Y"));
							possibleBnftVal.add(new SelectItem("N", "N"));

						} else {
							possibleBnftVal.add(new SelectItem("YES", "YES"));
							possibleBnftVal.add(new SelectItem("NO", "NO"));
						}
					}

					else {
						possibleBnftVal.add(new SelectItem("YES", "YES"));
						possibleBnftVal.add(new SelectItem("NO", "NO"));
					}

				}

				selectItems.setValue(possibleBnftVal);
				seloneMenuForBnftValue.getChildren().add(selectItems);
				if (null != benefitLineValues.getBenefitValue()
						&& !benefitLineValues.getBenefitValue().equals("  ")) {
					if (benefitLineValues.getBenefitValue().equals("YES")) {
						if (!(this.getBenefitComponentSessionObject().bcComponentType)
								.equals("MANDATE"))
							seloneMenuForBnftValue.setValue("YES");
						else
							lineBnftValueView.setValue("YES");

					} else if (benefitLineValues.getBenefitValue().equals("NO")) {
						if (!(this.getBenefitComponentSessionObject().bcComponentType)
								.equals("MANDATE"))
							seloneMenuForBnftValue.setValue("NO");
						else
							lineBnftValueView.setValue("NO");
					} else if (benefitLineValues.getBenefitValue().equals("Y")) {
						if (!(this.getBenefitComponentSessionObject().bcComponentType)
								.equals("MANDATE"))
							seloneMenuForBnftValue.setValue("Y");
						else
							lineBnftValueView.setValue("Y");
					} else if (benefitLineValues.getBenefitValue().equals("N")) {
						if (!(this.getBenefitComponentSessionObject().bcComponentType)
								.equals("MANDATE"))
							seloneMenuForBnftValue.setValue("N");
						else
							lineBnftValueView.setValue("N");
					} else {
						if (!(this.getBenefitComponentSessionObject().bcComponentType)
								.equals("MANDATE"))
							seloneMenuForBnftValue.setValue("  ");
						else
							lineBnftValueView.setValue("  ");
					}
				} else {
					if (!(this.getBenefitComponentSessionObject().bcComponentType)
							.equals("MANDATE"))
						seloneMenuForBnftValue.setValue("  ");
					else
						lineBnftValueView.setValue("");
				}
				seloneMenuForBnftValue.setDisabled(isHidden);
				object = (HtmlSelectOneMenu) seloneMenuForBnftValue;
				// set the value to the map
				ValueBinding bnftValueBinding = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{ComponentBenefitDefinitionsBackingBean.lineBenefitValueMap["
										+ benefitLineValues.getLineSysId()
										+ "]}");
				seloneMenuForBnftValue.setValueBinding("value",
						bnftValueBinding);
				seloneMenuForBnftValue.setId("lineBnftValue" + j + "_" + i);
			} else {
				lineBnftValue.setSize(10);
				lineBnftValue.setId("lineBnftValue" + j + "_" + i);
				lineBnftValue.setValue(benefitLineValues.getBenefitValue());
				ValueBinding bnftValueBinding = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{ComponentBenefitDefinitionsBackingBean.lineBenefitValueMap["
										+ benefitLineValues.getLineSysId()
										+ "]}");
				lineBnftValue.setValueBinding("value", bnftValueBinding);
				lineBnftValue.setStyleClass("formInputField");
				lineBnftValue.setStyle("width:75px;");
			}
		}
		// **End**        

		// lineEmptyString
		HtmlOutputText lineEmptyString = new HtmlOutputText();
		lineEmptyString.setValue(" ");

		HtmlOutputText lineEmptyString2 = new HtmlOutputText();
		lineEmptyString2.setValue(" ");

		// hidden field for storing the benefitLineSysId
		HtmlInputHidden hiddenLineId = new HtmlInputHidden();
		hiddenLineId.setId("hiddenLineId" + j + "_" + i);
		// **change        
		hiddenLineId.setValue(new Integer(benefitLineValues.getLineSysId())
				+ ":" + i + ":" + benefitLineValues.getDataTypeId() + ":"
				+ benefitLevelValues.getLevelDesc());
		// **end        
		// set the value to the map
		//Commented as part of stabilization release
		/*ValueBinding lineIdValBind = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{ComponentBenefitDefinitionsBackingBean.lineIdMap["
								+ benefitLineValues.getLineSysId() + "]}");
		hiddenLineId.setValueBinding("value", lineIdValBind);*/
		this.lineIdMap.put(new Long(benefitLineValues.getLineSysId()),new Integer(benefitLineValues.getLineSysId())
				+ ":" + i + ":" + benefitLineValues.getDataTypeId() + ":"
				+ benefitLevelValues.getLevelDesc());

		// combining the lineBenefitValue, DataType, hiddenLineIdField and EmptyString
		HtmlOutputLabel lblBnftValue = new HtmlOutputLabel();
		lblBnftValue.setId("lblBnftValue"+RandomStringUtils.randomAlphanumeric(15));
		//lblBnftValue.setId("lblBnftValue" + j + "_" + i);
		lblBnftValue.setFor("lineBnftValue" + j + "_" + i);
		// **Change**        

		if (null != sysDataType) {
			lblBnftValue.getChildren().add(hiddenBenefitValue);
			String mode = getBenefitComponentSessionObject()
					.getBenefitComponentMode();

			if (sysDataType.equals("DOLLAR")) {
				if (!(this.getBenefitComponentSessionObject().bcComponentType)
						.equals("MANDATE"))
					lblBnftValue.getChildren().add(lineBnftValue);
				else
					lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(lineEmptyString);
				lblBnftValue.getChildren().add(hiddenLineId);
			} else if (sysDataType.equals("PERCENTAGE")) {
				if (!(this.getBenefitComponentSessionObject().bcComponentType)
						.equals("MANDATE"))
					lblBnftValue.getChildren().add(lineBnftValue);
				else
					lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(lineEmptyString);
				lblBnftValue.getChildren().add(hiddenLineId);
			} else if (sysDataType.equals("STRING")) {
				if (!(this.getBenefitComponentSessionObject().bcComponentType)
						.equals("MANDATE"))
					lblBnftValue.getChildren().add(lineBnftValue);
				else
					lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(lineEmptyString);
				lblBnftValue.getChildren().add(hiddenLineId);
			} else if (sysDataType.equals("BOOLEAN")) {
				lblBnftValue.getChildren().add(lineEmptyString);
				if (!(this.getBenefitComponentSessionObject().bcComponentType)
						.equals("MANDATE"))
					lblBnftValue.getChildren().add(object);
				else
					lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(hiddenLineId);
			} else {
				if (!(this.getBenefitComponentSessionObject().bcComponentType)
						.equals("MANDATE"))
					lblBnftValue.getChildren().add(lineBnftValue);
				else
					lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(lineEmptyString);
				lblBnftValue.getChildren().add(hiddenLineId);
			}
		}
		// **End**      
		lineBnftValue.setDisabled(isHidden);
		// line reference
		HtmlOutputText lineReference = new HtmlOutputText();
		lineReference.setValue(benefitLineValues.getReferenceDesc());

		// line image
		HtmlOutputText lineImage = new HtmlOutputText();
		lineDesc.setValue(" ");

		// ******Change - Override BenefitLine Notes at benefitComponent
		HtmlOutputLabel lblRefAndNotes = new HtmlOutputLabel();
		lblRefAndNotes.setId("lblRefAndNotes"+RandomStringUtils.randomAlphanumeric(15));
		//lblBnftValue.setId("lblRefAndNotes" + j + "_" + i);
		lblBnftValue.setFor("lblRefAndNotes" + j + "_" + i);

		HtmlGraphicImage noteImage = new HtmlGraphicImage();
		noteImage.setUrl("../../images/notes_exist.gif");
		noteImage.setStyle("cursor:hand;");
		noteImage.setId("noteImage" + j + "_" + i);
		//      May 6 - Start
		HtmlInputHidden hiddenNotesStatus = new HtmlInputHidden();
		hiddenNotesStatus.setId("hiddenNotesStatus" + j + "_" + i);
		hiddenNotesStatus.setValue("");
		//May 6 - End	        
		HtmlCommandButton notesButton = new HtmlCommandButton();
		notesButton.setValue("NotesButton");
		if (benefitLineValues.getBnftLineNotesExist().equals("Y"))
			notesButton.setImage("../../images/notes_exist.gif");
		else
			notesButton.setImage("../../images/page.gif");
		notesButton.setTitle("Note");
		notesButton.setStyle("border:0;");
		notesButton.setAlt("Notes");
		notesButton.setId("notesButton" + j + "_" + i);
		notesButton.setOnclick("getUrlAssigned(" + benefitLineValues.getLineSysId()
				+ "," + j + "," + i + ");return false;");
		notesButton.setDisabled(isHidden);
		lblRefAndNotes.getChildren().add(lineReference);
		// Enhancement		
		// Display notesButton only if the componentType is STANDARD
		if (!(this.getBenefitComponentSessionObject().bcComponentType)
				.equals("MANDATE")) {
			lineImage.getChildren().add(notesButton);
			// May 6 - Start
			lineImage.getChildren().add(hiddenNotesStatus);
			// May 6 - End 			
		}

		// End - Enhancement	

		// lgnd data type
		HtmlOutputText lgndDataType = new HtmlOutputText();
		lgndDataType.setValue(benefitLineValues.getDataTypeLegend());
		lgndDataType.setId("lgndDataType" + j + "_" + i);

		// ***** End
		//Start - Term, Frequency & Qualifier Enhancement
		//line term
		HtmlOutputText lineTerm = new HtmlOutputText();
		lineTerm.setValue(" ");
		// line Qualifier
		HtmlOutputText lineQualifier = new HtmlOutputText();
		lineQualifier.setValue(" ");
		//End - Term, Frequency & Qualifier Enhancement
		
		lblBnftValue.getChildren().add(hiddenLineFlag);
		// adding to the panel
		panel.getChildren().add(lineDesc);
		panel.getChildren().add(lineTerm);
		panel.getChildren().add(lineQualifier);
		panel.getChildren().add(linePVA);
		panel.getChildren().add(lgndDataType);
		panel.getChildren().add(lblBnftValue);
		panel.getChildren().add(lblRefAndNotes);

		if (!(this.getBenefitComponentSessionObject().bcComponentType)
				.equals("MANDATE")) {
			panel.getChildren().add(lineImage);
			panel.getChildren().add(lineHideCheckBox);
		}

	}

	/**
	 * 
	 * @return
	 */
	public HtmlPanelGrid getPanel() {
		TimeHandler th = new TimeHandler();
    	Logger.logInfo(th.startPerfLogging("U23914_Benefit_Component_Coverage","ProductStructureBenefitDefenitionBackingBean","getPanel"));
    	
		int rowNumber = 0;
		int lineCount = 0;
		// Retaining the previous entered values, In case of application has any error message (Defect: 186432)
		// get the benefit defenitions list if list not null
		List benefitDefinitonsList = this.getBenefitDefinitionsList();
		if (validationMessages.isEmpty() && null != benefitDefinitonsList) {
			

			// this method gets the values from the benefit definiton List 
			//and sets it to the level list and line list
			getValuesFromDefinitonList(benefitDefinitonsList);

			StringBuffer sbf = new StringBuffer();
			sbf.append(",");
			panel = new HtmlPanelGrid();
			panel.setWidth("100%");
			if (!(this.getBenefitComponentSessionObject().bcComponentType)
					.equals("MANDATE"))
				panel.setColumns(9);
			else
				panel.setColumns(5);
			panel.setBorder(0);
			panel.setCellpadding("3");
			panel.setCellspacing("1");
			panel.setBgcolor("#cccccc");

			StringBuffer rows = new StringBuffer();//for setting row style in panel
			//setting values to benefit levels
			int size = benefitLevelList.size();
			//iterating to get the benefit levels
			for (int i = 0; i < size; i++) {
				rowNumber++;
				//a benefit level is selected
				BenefitLevel benefitLevelValues = (BenefitLevel) benefitLevelList
						.get(i);
				//Sets the hidden levels in grey color
				if (benefitLevelValues.isLevelHideStatus())
					rows.append("hiddenFieldLevelDisplay");
				else
					rows.append("dataTableEvenRow");
				//gets the benefit lines of a benefit level
				List benefitLines = benefitLevelValues.getBenefitLines();
				
				boolean levelHideStatus = false;
				for (int j = 0; j < benefitLines.size(); j++) {
					//selects an individual benefit line
					BenefitLine benefitLineValues = (BenefitLine) benefitLines
							.get(j);
					levelHideStatus = benefitLineValues.isLevelHideStatus();
					break;
				}
				//setting the benefit level values to the panel grid
				setBenefitLevelValuesToGrid(i, benefitLevelValues, benefitLines
						.size(), rowNumber, lineCount,levelHideStatus);

				if (benefitLines.size() != 0)
					rows.append(",");
				//iterating to get the individual benefit lines
				for (int j = 0; j < benefitLines.size(); j++) {
					rowNumber++;
					lineCount = lineCount + 1;
					//selects an individual benefit line
					BenefitLine benefitLineValues = (BenefitLine) benefitLines
							.get(j);
					//Sets the hidden lines in grey color
					if (benefitLineValues.isLineHideStatus())
						rows.append("hiddenFieldDisplay");
					else
						rows.append("dataTableOddRow");
					if (i < (size - 1))
						rows.append(",");
					else if (j < (benefitLines.size() - 1))
						rows.append(",");

					//sets the benefit lines of a benefit level to the panle grid
					setBenefitLineValuesToGrid(benefitLevelValues, j,
							benefitLineValues, i, benefitLines.size(),
							lineCount, benefitLineValues.isLineHideStatus());
				}
			}
			panel.setRowClasses(rows.toString());
		}
		getBenefitComponentSessionObject().setDataHiddenOutputValDescFromSession(dataHiddenOutputValDesc);
		getBenefitComponentSessionObject().setDataHiddenLowerLevelValDescFromSession(dataHiddenLowerLevelValDesc);
		getBenefitComponentSessionObject().setLevelIdMapFromSession(levelIdMap);
		getBenefitComponentSessionObject().setHiddenLevelFlagMapSession(hiddenLevelFlagMap);
		getBenefitComponentSessionObject().setDataHiddenValTermSession(dataHiddenValTerm);
		getBenefitComponentSessionObject().setDataHiddenValQualifierFromSession(dataHiddenValQualifier);
		getBenefitComponentSessionObject().setHiddenLineFreqValueMapFromSession(hiddenLineFreqValueMap);
		getBenefitComponentSessionObject().setHiddenLowerLevelFreqValueMapFromSession(hiddenLowerLevelFreqValueMap);
		getBenefitComponentSessionObject().setHiddenLineFlagMapFromSession(hiddenLineFlagMap);
		getBenefitComponentSessionObject().setLineIdMapFromSession(lineIdMap);
		
		Logger.logInfo(th.endPerfLogging());
		//added the messages to the request --Defect fix for WAS7 Migration
		addAllMessagesToRequest(informationMessageToDisplayOnPage);
		return panel;
	}

	/**
	 * 
	 * This method updates the benefit levels that are provided from the jsp
	 * page
	 */
	public String reloadPage() {
		getRequest().setAttribute("RETAIN_Value", "");
		return "componentBenefitDefinition";
	}

	/**
	 * Sets the panel
	 * @param panel.
	 */
	public void setPanel(HtmlPanelGrid panel) {
		this.panel = panel;
	}

	/**
	 * Returns the benefitDefinitionsList
	 * @return List benefitDefinitionsList.
	 */
	public List getBenefitDefinitionsList() {
		TimeHandler th = new TimeHandler();
    	Logger.logInfo(th.startPerfLogging("U23914_Benefit_Component_Coverage","ComponentBenefitDefinitionsBackingBean","getBenefitDefinitionsList"));
    	
		// create the request
		LocateComponentsBenefitDefinitionRequest request = (LocateComponentsBenefitDefinitionRequest) this
				.getServiceRequest(ServiceManager.LOCATE_COMPONENTS_BENEFIT_DEFINITION_REQUEST);
		// set the benefit component id in the request

		request.setBenefitId(Integer.parseInt(getSession().getAttribute(
				"SESSION_BNFT_ID").toString()));
		request.setBenefitComponentId(getBenefitComponentSessionObject()
				.getBenefitComponentId());

		//**Benefit level/line hide
		if (isShowHidden()) {
			request.setShowHidden(true);
		}
		//**End

		this.benefitComponentId = getBenefitComponentSessionObject()
				.getBenefitComponentId();
		this.benefitComponent = new Integer(benefitComponentId).toString();
		// get the response
		LocateComponentsBenefitDefinitionResponse response = (LocateComponentsBenefitDefinitionResponse) executeService(request);
		// get the list from the response
		if (null != response) {

			benefitDefinitionsList = response.getBenefitDefinitionsList();
			if (!benefitDefinitionsList.isEmpty()) {
				BenefitLine bline = (BenefitLine) benefitDefinitionsList.get(0);

				String benefitType = bline.getBenefitType();
				if ("STANDARD".equals(benefitType)) {
					this.setMandate(true);
				}			
				//for retrieving tier definitions associated with a benefit definition
				//benefitdefinition id is required,so set in session.	
				int benefitDefinitionId = bline.getBenefitDefinitionId();				
				getSession().setAttribute("SESSION_BNFTDEFINITION_ID",
				new Integer(benefitDefinitionId));
			}
			if(editFlag){
				if(null!=benefitDefinitionsList && benefitDefinitionsList.size()>0){
					headerDisplay = "display";
				}else{
					headerDisplay = "nodisplay";
				}
			}
		}

		//sunil
		//Commented as part of stabilization release.
		/*request.setShowHidden(false);
		if (editFlag) {
			request.setShowHidden(true);
			LocateComponentsBenefitDefinitionResponse responseHeader = (LocateComponentsBenefitDefinitionResponse) executeService(request);

			if (null != responseHeader) {

				hideHeaderPanelLevelsList = responseHeader
						.getBenefitDefinitionsList();
				if (null != hideHeaderPanelLevelsList
						&& hideHeaderPanelLevelsList.size() > 0) {
					headerDisplay = "display";
				} else {
					headerDisplay = "nodisplay";
				}
			}
			System.out.println("headerDisplay:"+headerDisplay);
		}*/
		// end sunil
		Logger.logInfo(th.endPerfLogging());
		// return the list
		return benefitDefinitionsList;
	}

	
	/**
	 * Sets the benefitDefinitionsList
	 * @param benefitDefinitionsList.
	 */
	public void setBenefitDefinitionsList(List benefitDefinitionsList) {
		this.benefitDefinitionsList = benefitDefinitionsList;
	}

	/**
	 * Returns the benefitLevelList
	 * @return List benefitLevelList.
	 */
	public List getBenefitLevelList() {
		return benefitLevelList;
	}

	/**
	 * Sets the benefitLevelList
	 * @param benefitLevelList.
	 */
	public void setBenefitLevelList(List benefitLevelList) {
		this.benefitLevelList = benefitLevelList;
	}

	/**
	 * Returns the benefitLineList
	 * @return List benefitLineList.
	 */
	public List getBenefitLineList() {
		return benefitLineList;
	}

	/**
	 * Sets the benefitLineList
	 * @param benefitLineList.
	 */
	public void setBenefitLineList(List benefitLineList) {
		this.benefitLineList = benefitLineList;
	}

	public HtmlPanelGrid getHeaderPanel() {

		headerPanel = new HtmlPanelGrid();
		headerPanel.setWidth("100%");
		if (!(this.getBenefitComponentSessionObject().bcComponentType)
				.equals("MANDATE"))
			headerPanel.setColumns(9);
		else
			headerPanel.setColumns(5);
		headerPanel.setBorder(0);
		headerPanel.setCellpadding("3");
		headerPanel.setCellspacing("1");
		headerPanel.setBgcolor("#cccccc");
		headerPanel.setStyleClass("dataTableHeader");

		HtmlOutputText headerText1 = new HtmlOutputText();
		HtmlOutputText headerText3 = new HtmlOutputText();
		HtmlOutputText headerText4 = new HtmlOutputText();
		HtmlOutputText headerText5 = new HtmlOutputText();
		HtmlOutputText headerText6 = new HtmlOutputText();
		HtmlOutputText headerText7 = new HtmlOutputText();

		//**Change level/Line hide
		HtmlSelectBooleanCheckbox showHiddenCheckBox = new HtmlSelectBooleanCheckbox();
		showHiddenCheckBox.setId("showHiddenCheckBox");
		showHiddenCheckBox.setValue(Boolean.valueOf(isShowHidden()));
		showHiddenCheckBox.setOnclick("unsavedDataFinder()");

		HtmlOutputText headerText8 = new HtmlOutputText();
		headerText8.setId("showHiddenText");
		headerText8.setValue("Show Hidden ");

		HtmlOutputLabel showHiddenLabel = new HtmlOutputLabel();
		showHiddenLabel.setId("showHiddenLabel"+RandomStringUtils.randomAlphanumeric(15));
		showHiddenLabel.getChildren().add(showHiddenCheckBox);
		showHiddenLabel.getChildren().add(headerText8);

		//**End

		headerText1.setValue("Description");
		headerText1.setId("desc");

		headerText3.setValue("Format");
		headerText3.setId("dataTypeLgnd");

		headerText4.setValue("PVA");
		headerText4.setId("pva");

		headerText5.setValue("Benefit Value");
		headerText5.setId("bnftValue");

		headerText6.setValue("Reference");
		headerText6.setId("ref");
		headerText7.setValue(" ");
		
		//**Start -- Display Term, Qualifier & Frequency
		HtmlOutputText headerText9 = new HtmlOutputText();
		HtmlOutputText headerText10 = new HtmlOutputText();
		
		headerText9.setValue("Term");
		headerText9.setId("term");
		
		headerText10.setValue("Frequency - Qualifier");
		headerText10.setId("freQual");
		
		//**End

		headerPanel.setStyleClass("dataTableHeader");
		headerPanel.getChildren().add(headerText1);
		headerPanel.getChildren().add(headerText9);
		headerPanel.getChildren().add(headerText10);
		headerPanel.getChildren().add(headerText4);
		headerPanel.getChildren().add(headerText3);
		headerPanel.getChildren().add(headerText5);
		headerPanel.getChildren().add(headerText6);
		if (!(this.getBenefitComponentSessionObject().bcComponentType)
				.equals("MANDATE")) {
			headerPanel.getChildren().add(headerText7);
			headerPanel.getChildren().add(showHiddenLabel); //Change level/line hide
		}
		return headerPanel;
	}

	/**
	 * Sets the headerPanel
	 * @param headerPanel.
	 */
	public void setHeaderPanel(HtmlPanelGrid headerPanel) {
		this.headerPanel = headerPanel;
	}

	/**
	 * Returns the selectedRow
	 * @return String selectedRow.
	 */
	public String getSelectedRow() {
		return selectedRow;
	}

	/**
	 * Sets the selectedRow
	 * @param selectedRow.
	 */
	public void setSelectedRow(String selectedRow) {
		this.selectedRow = selectedRow;
	}

	/**
	 * Returns the levelIdMap
	 * @return HashMap levelIdMap.
	 */
	public HashMap getLevelIdMap() {
		return levelIdMap;
	}

	/**
	 * Sets the levelIdMap
	 * @param levelIdMap.
	 */
	public void setLevelIdMap(HashMap levelIdMap) {
		this.levelIdMap = levelIdMap;
	}

	/**
	 * Returns the lineBenefitValueMap
	 * @return HashMap lineBenefitValueMap.
	 */
	public HashMap getLineBenefitValueMap() {
		return lineBenefitValueMap;
	}

	/**
	 * Sets the lineBenefitValueMap
	 * @param lineBenefitValueMap.
	 */
	public void setLineBenefitValueMap(HashMap lineBenefitValueMap) {
		this.lineBenefitValueMap = lineBenefitValueMap;
	}

	/**
	 * Returns the lineIdMap
	 * @return HashMap lineIdMap.
	 */
	public HashMap getLineIdMap() {
		return lineIdMap;
	}

	/**
	 * Sets the lineIdMap
	 * @param lineIdMap.
	 */
	public void setLineIdMap(HashMap lineIdMap) {
		this.lineIdMap = lineIdMap;
	}

	/**
	 * @return Returns the levelsToDeleteHidden.
	 */
	public String getLevelsToDelete() {
		return levelsToDelete;
	}

	/**
	 * @param levelsToDelete The levelsToDelete to set.
	 */
	public void setLevelsToDelete(String levelsToDelete) {
		this.levelsToDelete = levelsToDelete;
	}

	/**
	 * @return Returns the hideLevelsList.
	 */
	public List getHideLevelsList() {
		return hideLevelsList;
	}

	/**
	 * @param hideLevelsList The hideLevelsList to set.
	 */
	public void setHideLevelsList(List hideLevelsList) {
		this.hideLevelsList = hideLevelsList;
	}

	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}

	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}

	/**
	 * @return Returns the benefitComponent.
	 */
	public String getBenefitComponent() {
		return benefitComponent;
	}

	/**
	 * @param benefitComponent The benefitComponent to set.
	 */
	public void setBenefitComponent(String benefitComponent) {
		this.benefitComponent = benefitComponent;
	}

	/**
	 * @return Returns the mandate.
	 */
	public boolean isMandate() {
		return mandate;
	}

	/**
	 * @param mandate The mandate to set.
	 */
	public void setMandate(boolean mandate) {
		this.mandate = mandate;
	}

	/**
	 * Returns the validationMessages
	 * @return List validationMessages.
	 */

	public List getValidationMessages() {
		return validationMessages;
	}

	/**
	 * Sets the validationMessages
	 * @param validationMessages.
	 */

	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	/**
	 * @return Returns the showHidden.
	 */
	public boolean isShowHidden() {
		return showHidden;
	}

	/**
	 * @param showHidden The showHidden to set.
	 */
	public void setShowHidden(boolean showHidden) {
		this.showHidden = showHidden;
	}

	/**
	 * @return Returns the levelVisible.
	 */
	public boolean isLevelVisible() {
		return levelVisible;
	}

	/**
	 * @param levelVisible The levelVisible to set.
	 */
	public void setLevelVisible(boolean levelVisible) {
		this.levelVisible = levelVisible;
	}

	/**
	 * @return Returns the lineVisible.
	 */
	public boolean isLineVisible() {
		return lineVisible;
	}

	/**
	 * @param lineVisible The lineVisible to set.
	 */
	public void setLineVisible(boolean lineVisible) {
		this.lineVisible = lineVisible;
	}

	/**
	 * @return Returns the levelVisibleMap.
	 */
	public HashMap getLevelVisibleMap() {
		return levelVisibleMap;
	}

	/**
	 * @param levelVisibleMap The levelVisibleMap to set.
	 */
	public void setLevelVisibleMap(HashMap levelVisibleMap) {
		this.levelVisibleMap = levelVisibleMap;
	}

	/**
	 * @return Returns the lineVisibleMap.
	 */
	public HashMap getLineVisibleMap() {
		return lineVisibleMap;
	}

	/**
	 * @param lineVisibleMap The lineVisibleMap to set.
	 */
	public void setLineVisibleMap(HashMap lineVisibleMap) {
		this.lineVisibleMap = lineVisibleMap;
	}

	/**
	 * @return Returns the hideHeaderPanelLevelsList.
	 */
	public List getHideHeaderPanelLevelsList() {
		return hideHeaderPanelLevelsList;
	}

	/**
	 * @param hideHeaderPanelLevelsList The hideHeaderPanelLevelsList to set.
	 */
	public void setHideHeaderPanelLevelsList(List hideHeaderPanelLevelsList) {
		this.hideHeaderPanelLevelsList = hideHeaderPanelLevelsList;
	}

	/**
	 * @return Returns the headerDisplay.
	 */
	public String getHeaderDisplay() {
		return headerDisplay;
	}

	/**
	 * @param headerDisplay The headerDisplay to set.
	 */
	public void setHeaderDisplay(String headerDisplay) {
		this.headerDisplay = headerDisplay;
	}

	/**
	 * @return Returns the visibleLinesDisplay.
	 */
	public String getVisibleLinesDisplay() {
		return visibleLinesDisplay;
	}

	/**
	 * @param visibleLinesDisplay The visibleLinesDisplay to set.
	 */
	public void setVisibleLinesDisplay(String visibleLinesDisplay) {
		this.visibleLinesDisplay = visibleLinesDisplay;
	}
	/**
	 * @return Returns the dataHiddenValQualifier.
	 */
	public Map getDataHiddenValQualifier() {
		return dataHiddenValQualifier;
	}
	/**
	 * @param dataHiddenValQualifier The dataHiddenValQualifier to set.
	 */
	public void setDataHiddenValQualifier(Map dataHiddenValQualifier) {
		this.dataHiddenValQualifier = dataHiddenValQualifier;
	}
	/**
	 * @return Returns the dataHiddenValTerm.
	 */
	public Map getDataHiddenValTerm() {
		return dataHiddenValTerm;
	}
	/**
	 * @param dataHiddenValTerm The dataHiddenValTerm to set.
	 */
	public void setDataHiddenValTerm(Map dataHiddenValTerm) {
		this.dataHiddenValTerm = dataHiddenValTerm;
	}
	/**
	 * @return Returns the hiddenLineFreqValueMap.
	 */
	public HashMap getHiddenLineFreqValueMap() {
		return hiddenLineFreqValueMap;
	}
	/**
	 * @param hiddenLineFreqValueMap The hiddenLineFreqValueMap to set.
	 */
	public void setHiddenLineFreqValueMap(HashMap hiddenLineFreqValueMap) {
		this.hiddenLineFreqValueMap = hiddenLineFreqValueMap;
	}
	/**
	 * @return Returns the lineFreqValueMap.
	 */
	public HashMap getLineFreqValueMap() {
		return lineFreqValueMap;
	}
	/**
	 * @param lineFreqValueMap The lineFreqValueMap to set.
	 */
	public void setLineFreqValueMap(HashMap lineFreqValueMap) {
		this.lineFreqValueMap = lineFreqValueMap;
	}
	/**
	 * @return Returns the dataHiddenValDesc.
	 */
	public Map getDataHiddenValDesc() {
		return dataHiddenValDesc;
	}
	/**
	 * @param dataHiddenValDesc The dataHiddenValDesc to set.
	 */
	public void setDataHiddenValDesc(Map dataHiddenValDesc) {
		this.dataHiddenValDesc = dataHiddenValDesc;
	}
	/**
	 * @return Returns the dataHiddenOutputValDesc.
	 */
	public Map getDataHiddenOutputValDesc() {
		return dataHiddenOutputValDesc;
	}
	/**
	 * @param dataHiddenOutputValDesc The dataHiddenOutputValDesc to set.
	 */
	public void setDataHiddenOutputValDesc(Map dataHiddenOutputValDesc) {
		this.dataHiddenOutputValDesc = dataHiddenOutputValDesc;
	}
	/**
	 * @return Returns the hiddenLowerLevelFreqValueMap.
	 */
	public HashMap getHiddenLowerLevelFreqValueMap() {
		return hiddenLowerLevelFreqValueMap;
	}
	/**
	 * @param hiddenLowerLevelFreqValueMap The hiddenLowerLevelFreqValueMap to set.
	 */
	public void setHiddenLowerLevelFreqValueMap(
			HashMap hiddenLowerLevelFreqValueMap) {
		this.hiddenLowerLevelFreqValueMap = hiddenLowerLevelFreqValueMap;
	}
	/**
	 * @return Returns the dataHiddenLowerLevelValDesc.
	 */
	public HashMap getDataHiddenLowerLevelValDesc() {
		return dataHiddenLowerLevelValDesc;
	}
	/**
	 * @param dataHiddenLowerLevelValDesc The dataHiddenLowerLevelValDesc to set.
	 */
	public void setDataHiddenLowerLevelValDesc(
			HashMap dataHiddenLowerLevelValDesc) {
		this.dataHiddenLowerLevelValDesc = dataHiddenLowerLevelValDesc;
	}
	/**
	 * @return Returns the viewMode.
	 */
	public boolean isViewMode() {
		return viewMode;
	}
	/**
	 * @param viewMode The viewMode to set.
	 */
	public void setViewMode(boolean viewMode) {
		this.viewMode = viewMode;
	}
	/**
	 * @return Returns the dummyVar.
	 */
	public String getDummyVar() {
		this.setViewMode(true);
		return dummyVar;
	}
	/**
	 * @param dummyVar The dummyVar to set.
	 */
	public void setDummyVar(String dummyVar) {
		this.dummyVar = dummyVar;
	}
	/**
	 * @return Returns the errorFlag.
	 */
	public boolean isErrorFlag() {
		return errorFlag;
	}
	/**
	 * @param errorFlag The errorFlag to set.
	 */
	public void setErrorFlag(boolean errorFlag) {
		this.errorFlag = errorFlag;
	}
}