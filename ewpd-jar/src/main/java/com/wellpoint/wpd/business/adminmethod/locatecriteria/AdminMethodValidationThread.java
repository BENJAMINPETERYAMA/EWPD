/*
 * AdminMethodValidationThread.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.adminmethod.locatecriteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminmethod.builder.AdminMethodBusinessObjectBuilder;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodAnswerOverrideBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodOverrideBO;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodsPopupBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitAdministrationBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminMethodValidationThread implements Runnable {

	/**
	 * @see java.lang.Runnable#run()
	 *  
	 */

	private int benefitId;

	private String benefitName;

	private HashMap criteriaMap;

	private List messageList;

	private boolean result;

	private AdminMethodsPopupBO adminMethodsPopupBO;

	private List filterList;

	private int adminMethodId;

	private List generalBenefitAnsList;

	private List generalBenefitSPSList;

	/**
	 * 
	 * @param benefitId
	 * @param benefitName
	 * @param criteriaMap
	 * @param messageList
	 * @return
	 * @throws SevereException
	 */
	public boolean validateBenefitAdminMethod(int benefitId,
			String benefitName, HashMap criteriaMap, List messageList)
			throws SevereException {
		AdminMethodBusinessObjectBuilder adminMethodBusinessObjectBuilder = new AdminMethodBusinessObjectBuilder();
		List filterList = new ArrayList();

		boolean validation = true;

		if (criteriaMap.get(BusinessConstants.ENTITY_TYPE).equals(
				BusinessConstants.BENEFIT_CONSTANT)) {
			List benefitAdmin = null;
			try {
				//to get the benefit administration
				benefitAdmin = adminMethodBusinessObjectBuilder
						.getAdminstationId(benefitId);
			} catch (SevereException e1) {
				// TODO Auto-generated catch block
				Logger.logError(e1);
			} catch (AdapterException e1) {
				// TODO Auto-generated catch block
				Logger.logError(e1);
			}

			if (null != benefitAdmin && benefitAdmin.size() > 0) {
				for (int i = 0; i < benefitAdmin.size(); i++) {

					BenefitAdministrationBO benefitAdministrationBO = (BenefitAdministrationBO) benefitAdmin
							.get(i);
					int benefitAdminKey = benefitAdministrationBO
							.getBenefitAdministrationKey();
					String standardDefinition = benefitAdministrationBO
							.getEffectiveDateStandDef()
							+ "-"
							+ benefitAdministrationBO.getExpiryDateStandDef();
					int benefitDefinitionKey = benefitAdministrationBO
							.getBenefitDefinitionKey();
					String[] argMsg = new String[3];
					argMsg[0] = standardDefinition;
					List adminMethod = null;
					try {
						adminMethod = adminMethodBusinessObjectBuilder
								.getAssociatedAdminMethod(benefitAdminKey);
					} catch (SevereException e2) {
						// TODO Auto-generated catch block
						Logger.logError(e2);

					} catch (AdapterException e2) {
						// TODO Auto-generated catch block
						Logger.logError(e2);
					}
					criteriaMap.put(BusinessConstants.BEN_ADMIN_CONSTANT,
							new Integer(benefitAdminKey));
					// filteration logic for the admin method

					if (null != adminMethod && adminMethod.size() > 0) {
						AdminMethodBO adminMethodBO = new AdminMethodBO();
						adminMethodBO = (AdminMethodBO) adminMethod.get(0);
						Iterator iterator = adminMethod.iterator();
						int j = 0, k = 0, l = 0;
						Thread thread[] = new Thread[adminMethod.size()];
						AdminMethodValidationThread adminMethodValidationThread[] = new AdminMethodValidationThread[adminMethod
								.size()];
						// starting threads which does the filteration process
						// for each admin method
						while (iterator.hasNext()) {
							AdminMethodsPopupBO adminMethodsPopupBO = new AdminMethodsPopupBO();
							adminMethodBO = new AdminMethodBO();
							adminMethodBO = ((AdminMethodBO) iterator.next());
							int spsId = adminMethodBO.getSpsId();
							int adminMethodId = adminMethodBO
									.getAdminMethodSysId();
							adminMethodsPopupBO
									.setEntityType(BusinessConstants.BENEFIT_CONSTANT);
							adminMethodsPopupBO.setSpsId(spsId);
							adminMethodsPopupBO.setEntityId(benefitId);
							adminMethodsPopupBO
									.setStdDefId(benefitDefinitionKey);
							adminMethodsPopupBO.setAdminId(benefitAdminKey);

							adminMethodValidationThread[l] = new AdminMethodValidationThread();
							thread[j] = new Thread(
									adminMethodValidationThread[l]);
							startAdminMethodThread(adminMethodsPopupBO,
									thread[j], adminMethodValidationThread[l],
									adminMethodId);
							j++;
							l++;
						}
						// Joining threads which does the filteration process
						// for each admin method
						while (k < j) {
							try {
								thread[k].join();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								Logger.logError(e);
							}
							int spsId = 0;
							spsId = adminMethodValidationThread[k]
									.getAdminMethodsPopupBO().getSpsId();
							int adminMethodId = adminMethodValidationThread[k]
									.getAdminMethodId();
							messageList = adminMethodValidationThread[k]
									.getMessageList();

							filterList = adminMethodValidationThread[k++]
									.getFilterList();

							boolean filterValidation = false;
							if (null != filterList && filterList.size() > 0) {
								Iterator iter = filterList.iterator();
								while (iter.hasNext()) {
									AdminMethodsPopupBO adminMethodsFilterPopup = (AdminMethodsPopupBO) iter
											.next();
									if (adminMethodId == adminMethodsFilterPopup
											.getAdminMethodId()) {
										filterValidation = true;
									}
								}
								if (!filterValidation) {
									validation = false;
									ErrorMessage msg = new ErrorMessage(
											BusinessConstants.BEN_VALIDATION_CONSTANT);
									try {
										String spsName = new AdminMethodBusinessObjectBuilder()
												.getSpsName(spsId);
										argMsg[1] = spsName;
									} catch (SevereException e3) {
										// TODO Auto-generated catch block
										Logger.logError(e3);
									} catch (AdapterException e3) {
										// TODO Auto-generated catch block
										Logger.logError(e3);
									}
									msg.setParameters(argMsg);
									messageList.add(msg);
									break;
								}
							}
						}
					}
				}
			}
		} else {
			List benefitAdmin = null;
			try {
				// to get the benefit administration
				benefitAdmin = adminMethodBusinessObjectBuilder
						.getAdminstationIdFromBC(benefitId, Integer
								.parseInt(criteriaMap.get(
										BusinessConstants.BEN_COMP_CONSTANT)
										.toString()));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				Logger.logError(e1);
			} catch (SevereException e1) {
				// TODO Auto-generated catch block
				Logger.logError(e1);
			} catch (AdapterException e1) {
				// TODO Auto-generated catch block
				Logger.logError(e1);
			}
			// filteration logic for the admin method
			if (null != benefitAdmin && benefitAdmin.size() > 0) {
				for (int i = 0; i < benefitAdmin.size(); i++) {
					AdminMethodOverrideBO adminMethodOverride;
					BenefitAdministrationBO benefitAdministrationBO = (BenefitAdministrationBO) benefitAdmin
							.get(i);
					int benefitAdminKey = benefitAdministrationBO
							.getBenefitAdministrationKey();
					String standardDefinition = benefitAdministrationBO
							.getEffectiveDateStandDef()
							+ "-"
							+ benefitAdministrationBO.getExpiryDateStandDef();
					int benefitDefinitionKey = benefitAdministrationBO
							.getBenefitDefinitionKey();
							
					
					String benefitComponentName = criteriaMap.get(
							BusinessConstants.BEN_COMP_NAME).toString();
					String entityType = criteriaMap.get(
							BusinessConstants.ENTITY_TYPE).toString();
					//doing validation only for general benefits
//					if(benefitComponentName.equalsIgnoreCase("GENERAL BENEFITS") && (entityType.equals(BusinessConstants.PRODUCT_CONSTANT)
//					|| entityType
//					.equals(BusinessConstants.CONTRACT_CONSTANT)) ){
						//do the validation
					
					String[] argMsg = new String[6];
					argMsg[0] = benefitName;
					argMsg[1] = standardDefinition;
					argMsg[2] = benefitComponentName;
					
					    
					int entityId = Integer.parseInt(criteriaMap.get(
							BusinessConstants.EN_ID_CONSTANT).toString());
					AdminMethodOverrideBO adminMethodOverrideBO = new AdminMethodOverrideBO();
					int benefitComponent = Integer.parseInt(criteriaMap.get(
							BusinessConstants.BEN_COMP_CONSTANT).toString());
					adminMethodOverrideBO.setEntitySysId(entityId);
					adminMethodOverrideBO.setEntityType(entityType);
					adminMethodOverrideBO.setBenefitCompSysId(benefitComponent);
					adminMethodOverrideBO.setBnftAdmnId(benefitAdminKey);
					List countList = null;
					try {
						// admin method list which contains the admin method
						// values
						countList = adminMethodBusinessObjectBuilder
								.getSPSNamesForAdminMethodOverrideValidation(adminMethodOverrideBO);
					} catch (SevereException e2) {
						// TODO Auto-generated catch block
						Logger.logError(e2);
					} catch (AdapterException e2) {
						// TODO Auto-generated catch block
						Logger.logError(e2);
					}

					AdminMethodAnswerOverrideBO adminMethodOverrideBusinessObject = new AdminMethodAnswerOverrideBO();
					// if entity type in any one of the following
					// Contract
					// Product
					if (entityType.equalsIgnoreCase(BusinessConstants.PRODUCT_CONSTANT)
							|| entityType.equalsIgnoreCase(BusinessConstants.CONTRACT_CONSTANT) && 
							(benefitComponentName.equalsIgnoreCase(BusinessConstants.GEN_BENEFIT_CONSTANT))) {
						//if benefit component is general benefit
					//	if ((benefitComponentName
						//		.equalsIgnoreCase(BusinessConstants.GEN_BENEFIT_CONSTANT))) {

							//The countList denotes the no of admin methods
//							if (!entityType
//									.equals(BusinessConstants.PRODUCT_STUCT_CONSTANT)) {
								adminMethodOverrideBO = (AdminMethodOverrideBO) countList
										.get(0);
								// the validationId attribute denotes whether
								// all the adminmethods are selected or not
								if (adminMethodOverrideBO.getValidationId() == 0
										|| adminMethodOverrideBO
												.getValidationId() == -1) {
								    if (!entityType
											.equals(BusinessConstants.CONTRACT_CONSTANT)) {
										ErrorMessage msg = new ErrorMessage(
												BusinessConstants.GEN_BEN_VALIDATION_CONSTANT);
										msg.setParameters(argMsg);
										messageList.add(msg);
								    }else{
								        argMsg[4] = (String)criteriaMap.get("effectiveDateForContract");
								        ErrorMessage msg = new ErrorMessage(
												BusinessConstants.GEN_BEN_CONTRACT_VALIDATION_CONSTANT);
								        
										msg.setParameters(argMsg);
										messageList.add(msg);
								    }
									validation = false;
									break;
								}
//							}
							/*adminMethodOverrideBusinessObject
									.setEntitySysId(entityId);
							adminMethodOverrideBusinessObject
									.setEntityType(entityType);
							adminMethodOverrideBusinessObject
									.setBenefitCompSysId(benefitComponent);
							try {
								//The general benefit answer List
								this.generalBenefitAnsList = adminMethodBusinessObjectBuilder
										.getAnswerOverrideValue(adminMethodOverrideBusinessObject);
							} catch (SevereException e1) {
								// TODO Auto-generated catch block
								Logger.logError(e1);
								throw e1;
							} catch (AdapterException e1) {
								// TODO Auto-generated catch block
								Logger.logError(e1);
							}

							//The general benefit admin method list
							this.generalBenefitSPSList = countList;*/
//								//Setting the messageList for the 
//								//service to access
							this.messageList=messageList;
							return validation;
						//}
					}
					
					Iterator iterator = countList.iterator();
					boolean filterValidation = false;
					boolean generalBenefitfilterValidation = false;

					int j = 0, k = 0, l = 0;
					Thread thread[] = new Thread[countList.size()];
					AdminMethodValidationThread adminMethodValidationThread[] = new AdminMethodValidationThread[countList
							.size()];
					// starting threads which does the filteration process for
					// each admin method
					while (iterator.hasNext()) {
						adminMethodOverride = new AdminMethodOverrideBO();
						adminMethodOverride = ((AdminMethodOverrideBO) iterator
								.next());
						
						int sps = 0;
						sps = adminMethodOverride.getSpsId();
						
						int generalBenefitAdmin = 0;
						if ( null!=this.generalBenefitSPSList )
							for (int m = 0; m < this.generalBenefitSPSList
									.size(); m++) {
								if (sps == ((AdminMethodOverrideBO) this.generalBenefitSPSList
										.get(m)).getSpsId()) {
									generalBenefitAdmin = ((AdminMethodOverrideBO) this.generalBenefitSPSList
											.get(m)).getAdminMethodSysId();
									break;
								}
							}
						
						// Validating the Admin Methods in all the modules
						if(		 // If in benefit component, then there is no need to validate for unselected SPS
								(entityType.equals(BusinessConstants.BENEFIT_COMP_CONSTANT)
									&& adminMethodOverride.getAdminMethodSysId() != -1)
					  || // If in Product Structure, then no need to validate if no Admin Methods selected for General Benefits Benefit Component
								(entityType.equals(BusinessConstants.PRODUCT_STUCT_CONSTANT)
										&& adminMethodOverride.getAdminMethodSysId() != -1 
										&& benefitComponentName.equals(BusinessConstants.GEN_BENEFIT_CONSTANT))
					  || // If in Contract and Product, need to validate for unselected and selected SPS Admin Methods in all benefit components
					  			(entityType.equals(BusinessConstants.CONTRACT_CONSTANT)
					  					|| entityType.equals(BusinessConstants.PRODUCT_CONSTANT))
					  || // If in Product Structure, need to validate  SPS Admin Methods not selected in other benefit components but  selected  in GB  
					  			(entityType.equals(	BusinessConstants.PRODUCT_STUCT_CONSTANT)
					  					&& !benefitComponentName.equals(BusinessConstants.GEN_BENEFIT_CONSTANT) 
										&& generalBenefitAdmin != -1
										&& adminMethodOverride.getAdminMethodSysId() == -1 )
					  || // If in Product Structure, need to validate for unselected sps in GB but same SPS selected in other benefit components				
					  		(entityType.equals(	BusinessConstants.PRODUCT_STUCT_CONSTANT)
		  					&& !benefitComponentName.equals(BusinessConstants.GEN_BENEFIT_CONSTANT) 
							&& generalBenefitAdmin == -1
							&& adminMethodOverride.getAdminMethodSysId() != -1 )
					 || // If in Product Structure, need to validate for selected sps in GB and same SPS selected in other benefit components
				 		(entityType.equals(	BusinessConstants.PRODUCT_STUCT_CONSTANT)
			  					&& !benefitComponentName.equals(BusinessConstants.GEN_BENEFIT_CONSTANT) 
								&& generalBenefitAdmin != -1
								&& adminMethodOverride.getAdminMethodSysId() != -1 )
						){
						int spsId = adminMethodOverride.getSpsId();
						int adminMethodId = adminMethodOverride
								.getAdminMethodSysId();
						AdminMethodsPopupBO adminMethodsPopupBO = new AdminMethodsPopupBO();
						if (criteriaMap.get(BusinessConstants.ENTITY_TYPE)
								.equals(BusinessConstants.CONTRACT_CONSTANT)) {
							adminMethodsPopupBO.setContractId(Integer
									.parseInt(criteriaMap.get(
											BusinessConstants.EN_ID_CONSTANT)
											.toString()));
							adminMethodsPopupBO
									.setEntityId(Integer
											.parseInt(criteriaMap
													.get(
															BusinessConstants.CONTRACT_ID_CONSTANT)
													.toString()));
							adminMethodsPopupBO.setContractDateSgmntId(Integer
									.parseInt(criteriaMap.get(
											BusinessConstants.EN_ID_CONSTANT)
											.toString()));
							adminMethodsPopupBO
									.setProdId(Integer
											.parseInt(criteriaMap
													.get(
															BusinessConstants.PRODUCT_ID_CONSTANT)
													.toString()));
							adminMethodsPopupBO
									.setBenefitCompId(Integer
											.parseInt(criteriaMap
													.get(
															BusinessConstants.BEN_COMP_CONSTANT)
													.toString()));
						} else if (criteriaMap.get(
								BusinessConstants.ENTITY_TYPE).equals(
								BusinessConstants.PRODUCT_CONSTANT)) {
							adminMethodsPopupBO.setEntityId(Integer
									.parseInt(criteriaMap.get(
											BusinessConstants.EN_ID_CONSTANT)
											.toString()));
							adminMethodsPopupBO
									.setBenefitCompId(Integer
											.parseInt(criteriaMap
													.get(
															BusinessConstants.BEN_COMP_CONSTANT)
													.toString()));
						} else if (criteriaMap.get(
								BusinessConstants.ENTITY_TYPE).equals(
								BusinessConstants.PRODUCT_STUCT_CONSTANT)) {
							adminMethodsPopupBO.setEntityId(Integer
									.parseInt(criteriaMap.get(
											BusinessConstants.EN_ID_CONSTANT)
											.toString()));
							adminMethodsPopupBO
									.setBenefitCompId(Integer
											.parseInt(criteriaMap
													.get(
															BusinessConstants.BEN_COMP_CONSTANT)
													.toString()));
						} else if (criteriaMap.get(
								BusinessConstants.ENTITY_TYPE).equals(
								BusinessConstants.BENEFIT_COMP_CONSTANT)) {
							adminMethodsPopupBO.setEntityId(Integer
									.parseInt(criteriaMap.get(
											BusinessConstants.EN_ID_CONSTANT)
											.toString()));
						}
						adminMethodsPopupBO.setStdDefId(benefitDefinitionKey);
						adminMethodsPopupBO.setEntityType((String) criteriaMap
								.get(BusinessConstants.ENTITY_TYPE));
						adminMethodsPopupBO.setSpsId(spsId);
						adminMethodsPopupBO.setBenftId(benefitId);
						adminMethodsPopupBO
								.setBenefitComponentName(benefitComponentName);
						adminMethodsPopupBO.setAdminId(benefitAdminKey);

						adminMethodValidationThread[l] = new AdminMethodValidationThread();
						thread[j] = new Thread(adminMethodValidationThread[l]);
						startAdminMethodThread(adminMethodsPopupBO, thread[j],
								adminMethodValidationThread[l], adminMethodId);

						j++;
						l++;
						}
					}
					
					
					
					// Joining threads which does the filteration process for
					// each admin method
					while (k < j) {
						try {
							thread[k].join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							Logger.logError(e);
						}
						int spsId = 0;
						spsId = adminMethodValidationThread[k]
								.getAdminMethodsPopupBO().getSpsId();
						int adminMethodId = adminMethodValidationThread[k]
								.getAdminMethodId();
						int generalBenefitAdminId = 0;
						if ( null!=this.generalBenefitSPSList )
							for (int m = 0; m < this.generalBenefitSPSList
									.size(); m++) {
								if (spsId == ((AdminMethodOverrideBO) this.generalBenefitSPSList
										.get(m)).getSpsId()) {
									generalBenefitAdminId = ((AdminMethodOverrideBO) this.generalBenefitSPSList
											.get(m)).getAdminMethodSysId();
									break;
								}
							}
						generalBenefitfilterValidation = false;
						filterValidation = false;
						messageList = adminMethodValidationThread[k]
								.getMessageList();
						filterList = adminMethodValidationThread[k++]
								.getFilterList();
						if (null != filterList && filterList.size() > 0) {

							Iterator iter = filterList.iterator();
							while (iter.hasNext()) {
								AdminMethodsPopupBO adminMethodsFilterPopup = (AdminMethodsPopupBO) iter
										.next();

								if (adminMethodId != -1) {
									if (adminMethodId == adminMethodsFilterPopup
											.getAdminMethodId()) {
										filterValidation = true;
									}
									generalBenefitfilterValidation = true;
								} else {
									// if admin method is not selected
									if (entityType
											.equals(BusinessConstants.PRODUCT_CONSTANT)
											|| entityType
													.equals(BusinessConstants.CONTRACT_CONSTANT)
											|| entityType
													.equals(BusinessConstants.PRODUCT_STUCT_CONSTANT)) {
										if (!benefitComponentName
												.equals(BusinessConstants.GEN_BENEFIT_CONSTANT)
												&& generalBenefitAdminId != -1) {
											if (generalBenefitAdminId == adminMethodsFilterPopup
													.getAdminMethodId()) {
												generalBenefitfilterValidation = true;
												filterValidation = true;
												break;
											}
											filterValidation = true;
										} else {
											generalBenefitfilterValidation = true;
											filterValidation = true;
											break;
										}
									} else {
										// if the entity is not contract,
										// product or
										// product structure
										generalBenefitfilterValidation = true;
										filterValidation = true;
									}
								}
							}
							
							 
							 
							 
							if (!filterValidation) {
								validation = false;
								try {
									argMsg[3] = new AdminMethodBusinessObjectBuilder()
											.getSpsName(spsId);
								} catch (SevereException e3) {
									// TODO Auto-generated catch block
									Logger.logError(e3);
								} catch (AdapterException e3) {
									// TODO Auto-generated catch block
									Logger.logError(e3);
								}
								 if (!entityType
											.equals(BusinessConstants.CONTRACT_CONSTANT)) {
								     ErrorMessage msg = new ErrorMessage(
												BusinessConstants.ADMIN_METHOD_VALIDATION_CONSTANT);
								     msg.setParameters(argMsg);
										messageList.add(msg);
								 }else{
								     argMsg[4] = (String)criteriaMap.get("effectiveDateForContract");
								        ErrorMessage msg = new ErrorMessage(
												BusinessConstants.ADMIN_METHOD_VALIDATION_CONSTANT_CONTRACT);
								        msg.setParameters(argMsg);
										messageList.add(msg);
								 }
								break;
							}
							if (!generalBenefitfilterValidation) {
								validation = false;
								try {
									argMsg[3] = new AdminMethodBusinessObjectBuilder()
											.getSpsName(spsId);
								} catch (SevereException e3) {
									// TODO Auto-generated catch block
									Logger.logError(e3);
								} catch (AdapterException e3) {
									// TODO Auto-generated catch block
									Logger.logError(e3);
								}
								 if (!entityType
											.equals(BusinessConstants.CONTRACT_CONSTANT)) {
									ErrorMessage msg = new ErrorMessage(
											BusinessConstants.GEN_BEN_ADMIN_METHOD_VALID_CONST);
									msg.setParameters(argMsg);
									messageList.add(msg);
								 }else{
								     argMsg[4] = (String)criteriaMap.get("effectiveDateForContract");
								        ErrorMessage msg = new ErrorMessage(
												BusinessConstants.GEN_BEN_ADMIN_METHOD_VALID_CONST_CONTRACT);
								        msg.setParameters(argMsg);
										messageList.add(msg); 
								 }
								 
								break;
							}

						}
					}
				//}
				}
			}
		}
		this.messageList = messageList;
		return validation;
	}

	/**
	 * 
	 * @param adminMethodsPopupBO
	 * @param thread
	 * @param adminMethodValidationThread
	 * @param adminMethodId
	 */
	private void startAdminMethodThread(
			AdminMethodsPopupBO adminMethodsPopupBO, Thread thread,
			AdminMethodValidationThread adminMethodValidationThread,
			int adminMethodId) {
		adminMethodValidationThread.setAdminMethodsPopupBO(adminMethodsPopupBO);
		adminMethodValidationThread.setAdminMethodId(adminMethodId);
		thread.start();

	}

	/**
	 *  
	 */
	public void run() {

		try {
			this.messageList = new ArrayList();
			if ( null==adminMethodsPopupBO ) {
				// used for starting the benefit validation as a seperate thread
				result = validateBenefitAdminMethod(this.benefitId,
						this.benefitName, this.criteriaMap, this.messageList);
			} else {
				// used for getting the filter list of an admin method
				AdminMethodBusinessObjectBuilder adminMethodBusinessObjectBuilder = new AdminMethodBusinessObjectBuilder();
				try {
					filterList = adminMethodBusinessObjectBuilder
							.getAdminMethodsForPopup(adminMethodsPopupBO);
					filterList = adminMethodBusinessObjectBuilder
							.sortAdminMethodBasedOnRank(filterList);
				} catch (AdapterException e1) {
					// TODO Auto-generated catch block
					Logger.logError(e1);
				}
			}

		} catch (SevereException e) {
			// TODO Auto-generated catch block
			Logger.logError(e);
		}

	}

	/**
	 * Returns the benefitId
	 * 
	 * @return int benefitId.
	 */
	public int getBenefitId() {
		return benefitId;
	}

	/**
	 * Sets the benefitId
	 * 
	 * @param benefitId.
	 */
	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}

	/**
	 * Returns the benefitName
	 * 
	 * @return String benefitName.
	 */
	public String getBenefitName() {
		return benefitName;
	}

	/**
	 * Sets the benefitName
	 * 
	 * @param benefitName.
	 */
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}

	/**
	 * Returns the criteriaMap
	 * 
	 * @return HashMap criteriaMap.
	 */
	public HashMap getCriteriaMap() {
		return criteriaMap;
	}

	/**
	 * Sets the criteriaMap
	 * 
	 * @param criteriaMap.
	 */
	public void setCriteriaMap(HashMap criteriaMap) {
		this.criteriaMap = criteriaMap;
	}

	/**
	 * Returns the messageList
	 * 
	 * @return List messageList.
	 */
	public List getMessageList() {
		return messageList;
	}

	/**
	 * Sets the messageList
	 * 
	 * @param messageList.
	 */
	public void setMessageList(List messageList) {
		this.messageList = messageList;
	}

	/**
	 * Returns the result
	 * 
	 * @return boolean result.
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * Sets the result
	 * 
	 * @param result.
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * Returns the adminMethodsPopupBO
	 * 
	 * @return AdminMethodsPopupBO adminMethodsPopupBO.
	 */
	public AdminMethodsPopupBO getAdminMethodsPopupBO() {
		return adminMethodsPopupBO;
	}

	/**
	 * Sets the adminMethodsPopupBO
	 * 
	 * @param adminMethodsPopupBO.
	 */
	public void setAdminMethodsPopupBO(AdminMethodsPopupBO adminMethodsPopupBO) {
		this.adminMethodsPopupBO = adminMethodsPopupBO;
	}

	/**
	 * Returns the filterList
	 * 
	 * @return List filterList.
	 */
	public List getFilterList() {
		return filterList;
	}

	/**
	 * Sets the filterList
	 * 
	 * @param filterList.
	 */
	public void setFilterList(List filterList) {
		this.filterList = filterList;
	}

	/**
	 * Returns the adminMethodId
	 * 
	 * @return int adminMethodId.
	 */
	public int getAdminMethodId() {
		return adminMethodId;
	}

	/**
	 * Sets the adminMethodId
	 * 
	 * @param adminMethodId.
	 */
	public void setAdminMethodId(int adminMethodId) {
		this.adminMethodId = adminMethodId;
	}

	/**
	 * @return Returns the generalBenefitAnsList.
	 */
	public List getGeneralBenefitAnsList() {
		return generalBenefitAnsList;
	}

	/**
	 * @param generalBenefitAnsList
	 *            The generalBenefitAnsList to set.
	 */
	public void setGeneralBenefitAnsList(List generalBenefitAnsList) {
		this.generalBenefitAnsList = generalBenefitAnsList;
	}

	/**
	 * @return Returns the generalBenefitSPSList.
	 */
	public List getGeneralBenefitSPSList() {
		return generalBenefitSPSList;
	}

	/**
	 * @param generalBenefitSPSList
	 *            The generalBenefitSPSList to set.
	 */
	public void setGeneralBenefitSPSList(List generalBenefitSPSList) {
		this.generalBenefitSPSList = generalBenefitSPSList;
	}
}