package com.wellpoint.ets.bx.mapping.domain.vo;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;

/**
 * @author UST-GLOBAL
 * This is an POJO class for search criteria.
 * 
 */
public class SearchCriteria implements Cloneable {

	/**
	 * 
	 */

	private static Logger logger = Logger.getLogger(SearchCriteria.class);
	
	private String EB03;
	
	private String variableId;
	
	private String spsId;
	
	private String headerRuleId;
	
	private String messageText;
	
	private String EB01;
	
	private String III02;
	
	private String noteType;
	
	private String user;
	
	private String commaSeperatedUser;
	
	private boolean unMapped;
	
	private boolean mapped;
	
	private boolean notApplicable;
	
	private boolean inProgress;
	
	private boolean notFinalized;
	
	private boolean spsIdCriteria;
	
	private boolean ruleIdCriteria;
	
	private boolean msgCrteria;
	
	private boolean checkWPD;
	
	private String viewType;
	
	private String loggedUser;
	
	private String authorizedToApproveFlag;
	
	/********January Release********/
	private String contractId;

	private String contractSystem;
	
	private String contractStartDate;
	
	private String ISGContractRevisionDate;

	/********January Release********/
	
	private String variableDescription;
	
	private String majorHeading;
	
	private String minorHeading;
	
	// To display only Standard Messages--BXNI Change
	
	private boolean showOnlyStandardMessages;	
	
	// BXNI Change Ends
	
	public String getMajorHeading() {
		return majorHeading;
	}

	public String getVariableDescription() {
		return variableDescription;
	}

	public void setVariableDescription(String variableDescription) {
		this.variableDescription = variableDescription;
	}
	
	public void setMajorHeading(String majorHeading) {
		this.majorHeading = majorHeading;
	}

	public String getMinorHeading() {
		return minorHeading;
	}

	public void setMinorHeading(String minorHeading) {
		this.minorHeading = minorHeading;
	}

	public String getEB03() {
		return EB03;
	}

	public void setEB03(String eb03) {
		EB03 = eb03;
	}

	public String getHeaderRuleId() {
		return headerRuleId;
	}

	public void setHeaderRuleId(String headerRuleId) {
		this.headerRuleId = headerRuleId;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}


	public String getVariableId() {
		return variableId;
	}
	
	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}

	/**
	 * @return Returns the eB01.
	 */
	public String getEB01() {
		return EB01;
	}
	/**
	 * @param eb01 The eB01 to set.
	 */
	public void setEB01(String eb01) {
		EB01 = eb01;
	}
	/**
	 * @return Returns the unMapped.
	 */
	public boolean isUnMapped() {
		return unMapped;
	}
	/**
	 * @param unMapped The unMapped to set.
	 */
	public void setUnMapped(boolean unMapped) {
		this.unMapped = unMapped;
	}
	/**
	 * @return Returns the mapped.
	 */
	public boolean isMapped() {
		return mapped;
	}
	/**
	 * @param mapped The mapped to set.
	 */
	public void setMapped(boolean mapped) {
		this.mapped = mapped;
	}
	/**
	 * @return Returns the notApplicable.
	 */
	public boolean isNotApplicable() {
		return notApplicable;
	}
	/**
	 * @param notApplicable The notApplicable to set.
	 */
	public void setNotApplicable(boolean notApplicable) {
		this.notApplicable = notApplicable;
	}
	/**
	 * @return Returns the inProgress.
	 */
	public boolean isInProgress() {
		return inProgress;
	}
	/**
	 * @param inProgress The inProgress to set.
	 */
	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}
	/**
	 * @return Returns the notFinalized.
	 */
	public boolean isNotFinalized() {
		return notFinalized;
	}
	/**
	 * @param notFinalized The notFinalized to set.
	 */
	public void setNotFinalized(boolean notFinalized) {
		this.notFinalized = notFinalized;
	}
	/**
	 * @return Returns the spsId.
	 */
	public String getSpsId() {
		return spsId;
	}
	/**
	 * @param spsId The spsId to set.
	 */
	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}
	/**
	 * @return Returns the spsIdCriteria.
	 */
	public boolean isSpsIdCriteria() {
		return spsIdCriteria;
	}
	/**
	 * @param spsIdCriteria The spsIdCriteria to set.
	 */
	public void setSpsIdCriteria(boolean spsIdCriteria) {
		this.spsIdCriteria = spsIdCriteria;
	}
	/**
	 * @return Returns the ruleIdCriteria.
	 */
	public boolean isRuleIdCriteria() {
		return ruleIdCriteria;
	}
	/**
	 * @param ruleIdCriteria The ruleIdCriteria to set.
	 */
	public void setRuleIdCriteria(boolean ruleIdCriteria) {
		this.ruleIdCriteria = ruleIdCriteria;
	}
	/**
	 * @return Returns the msgCrteria.
	 */
	public boolean isMsgCrteria() {
		return msgCrteria;
	}
	/**
	 * @param msgCrteria The msgCrteria to set.
	 */
	public void setMsgCrteria(boolean msgCrteria) {
		this.msgCrteria = msgCrteria;
	}

	public void setIII02(String iII02) {
		III02 = iII02;
	}

	public String getIII02() {
		return III02;
	}

	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}

	public String getNoteType() {
		return noteType;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public void setCommaSeperatedUser(String comaSeperatedUser) {
		this.commaSeperatedUser = comaSeperatedUser;
	}

	public String getCommaSeperatedUser() {
		return commaSeperatedUser;
	}

	public void setCheckWPD(boolean checkWPD) {
		this.checkWPD = checkWPD;
	}

	public boolean isCheckWPD() {
		return checkWPD;
	}
	/**
	 * @return Returns the viewType.
	 */
	public String getViewType() {
		return viewType;
	}
	/**
	 * @param viewType The viewType to set.
	 */
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	/**
	 * @return Returns the loggedUser.
	 */
	public String getLoggedUser() {
		return loggedUser;
	}
	/**
	 * @param loggedUser The loggedUser to set.
	 */
	public void setLoggedUser(String loggedUser) {
		this.loggedUser = loggedUser;
	}
	/**
	 * @return Returns the authorizedToApproveFlag.
	 */
	public String getAuthorizedToApproveFlag() {
		return authorizedToApproveFlag;
	}
	/**
	 * @param authorizedToApproveFlag The authorizedToApproveFlag to set.
	 */
	public void setAuthorizedToApproveFlag(String authorizedToApproveFlag) {
		this.authorizedToApproveFlag = authorizedToApproveFlag;
	}
	public static Object clone(Object o) throws EBXException
    {
      Object clone = null;
    
      try
      {
         clone = o.getClass().newInstance();
      }
      catch (InstantiationException e)
      {
         logger.error("SearchCriteria class Instantiation failed", e);
         throw new EBXException("SearchCriteria class Instantiation failed : " + e.getMessage());
      }
      catch (IllegalAccessException e)
      {
            logger.error("SearchCriteria.class Illegal State", e);
               throw new EBXException("SearchCriteria class is in illegal state : " + e.getMessage());
      }
    
      // Walk up the superclass hierarchy
      for (Class obj = o.getClass();
        !obj.equals(Object.class);
        obj = obj.getSuperclass())
      {
        Field[] fields = obj.getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
        {
          fields[i].setAccessible(true);
          try
          {
            // for each class/suerclass, copy all fields
            // from this object to the clone
            fields[i].set(clone, fields[i].get(o));
          }
          catch (IllegalArgumentException e){
            logger.error("Illegal argumet recieved during object copy", e);
              throw new EBXException("Illegal argumet recieved during object copy : " + e.getMessage());
          }
          catch (IllegalAccessException e){
            logger.error("Illegal access during object copy", e);
              throw new EBXException("Illegal access during object copy : " + e.getMessage());
          }
        }
      }
      return clone;
    }

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public String getContractStartDate() {
		return contractStartDate;
	}

	public void setContractSystem(String contractSystem) {
		this.contractSystem = contractSystem;
	}

	public String getContractSystem() {
		return contractSystem;
	}

	public void setISGContractRevisionDate(String iSGContractRevisionDate) {
		ISGContractRevisionDate = iSGContractRevisionDate;
	}

	public String getISGContractRevisionDate() {
		return ISGContractRevisionDate;
	}

	public void setShowOnlyStandardMessages(boolean showOnlyStandardMessages){
		this.showOnlyStandardMessages = showOnlyStandardMessages;
	}
	
	public boolean getShowOnlyStandardMesages(){
		return showOnlyStandardMessages;
	}
}