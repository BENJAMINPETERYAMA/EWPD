/*
 * Created on Jun 24, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.domain.bo;

import java.util.Date;

/**
 * @author U15427
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class QuestionnaireDomainInfo implements Comparable{
	 	private int domainId;
	    private int questionnaireHrchySysId;
	    private String createdUser;
	    private Date createdTimeStamp;
	    private String lastUpdatedUser;
	    private Date lastUpdatedTimeStamp;
	    private String lobDesc;
	    private String beDesc;
	    private String bgDesc;
	    private String lobName;
	    private String beName;
	    private String bgName;
	    private String buName;
	   
	    /**
	     * @see java.lang.Comparable#compareTo(java.lang.Object)
	     * @param obj
	     * @return
	     */
	    public int compareTo(Object obj) {
	        if (!(obj instanceof QuestionnaireDomainInfo))
	            throw new ClassCastException("Domain object Expected.");
	        
	        QuestionnaireDomainInfo otherDomain = (QuestionnaireDomainInfo) obj;
	        int compareValue = this.lobName.compareTo(otherDomain.lobName);
	        if(compareValue != 0)
	            return compareValue;
	        compareValue = this.beName.compareTo(otherDomain.beName);
	        if(compareValue != 0)
	            return compareValue;
	        compareValue = this.bgName.compareTo(otherDomain.bgName);
	        if(compareValue != 0)
	            return compareValue;
	        compareValue = this.buName.compareTo(otherDomain.buName);
	        if(compareValue != 0)
	            return compareValue;
	        
	        return 0;
	    }
	    
		/**
		 * @return Returns the createdTimeStamp.
		 */
		public Date getCreatedTimeStamp() {
			return createdTimeStamp;
		}
		/**
		 * @param createdTimeStamp The createdTimeStamp to set.
		 */
		public void setCreatedTimeStamp(Date createdTimeStamp) {
			this.createdTimeStamp = createdTimeStamp;
		}
		/**
		 * @return Returns the createdUser.
		 */
		public String getCreatedUser() {
			return createdUser;
		}
		/**
		 * @param createdUser The createdUser to set.
		 */
		public void setCreatedUser(String createdUser) {
			this.createdUser = createdUser;
		}
	/**
	 * @return Returns the domainId.
	 */
	public int getDomainId() {
		return domainId;
	}
	/**
	 * @param domainId The domainId to set.
	 */
	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}
					
		/**
		 * @return Returns the lastUpdatedTimeStamp.
		 */
		public Date getLastUpdatedTimeStamp() {
			return lastUpdatedTimeStamp;
		}
		/**
		 * @param lastUpdatedTimeStamp The lastUpdatedTimeStamp to set.
		 */
		public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
			this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
		}
		/**
		 * @return Returns the lastUpdatedUser.
		 */
		public String getLastUpdatedUser() {
			return lastUpdatedUser;
		}
		/**
		 * @param lastUpdatedUser The lastUpdatedUser to set.
		 */
		public void setLastUpdatedUser(String lastUpdatedUser) {
			this.lastUpdatedUser = lastUpdatedUser;
		}
		/**
		 * @return Returns the questionnaireHrchySysId.
		 */
		public int getQuestionnaireHrchySysId() {
			return questionnaireHrchySysId;
		}
		/**
		 * @param questionnaireHrchySysId The questionnaireHrchySysId to set.
		 */
		public void setQuestionnaireHrchySysId(int questionnaireHrchySysId) {
			this.questionnaireHrchySysId = questionnaireHrchySysId;
		}
		/**
		 * @return Returns the beDesc.
		 */
		public String getBeDesc() {
			return beDesc;
		}
		/**
		 * @param beDesc The beDesc to set.
		 */
		public void setBeDesc(String beDesc) {
			this.beDesc = beDesc;
		}
		/**
		 * @return Returns the bgDesc.
		 */
		public String getBgDesc() {
			return bgDesc;
		}
		/**
		 * @param bgDesc The bgDesc to set.
		 */
		public void setBgDesc(String bgDesc) {
			this.bgDesc = bgDesc;
		}
		/**
		 * @return Returns the lobDesc.
		 */
		public String getLobDesc() {
			return lobDesc;
		}
		/**
		 * @param lobDesc The lobDesc to set.
		 */
		public void setLobDesc(String lobDesc) {
			this.lobDesc = lobDesc;
		}
		/**
		 * @return Returns the beName.
		 */
		public String getBeName() {
			return beName;
		}
		/**
		 * @param beName The beName to set.
		 */
		public void setBeName(String beName) {
			this.beName = beName;
		}
		/**
		 * @return Returns the bgName.
		 */
		public String getBgName() {
			return bgName;
		}
		/**
		 * @param bgName The bgName to set.
		 */
		public void setBgName(String bgName) {
			this.bgName = bgName;
		}
		/**
		 * @return Returns the lobName.
		 */
		public String getLobName() {
			return lobName;
		}
		/**
		 * @param lobName The lobName to set.
		 */
		public void setLobName(String lobName) {
			this.lobName = lobName;
		}
		
		/**
		 * @return Returns the buName.
		 */
		public String getBuName() {
			return buName;
		}
		/**
		 * @param buName The buName to set.
		 */
		public void setBuName(String buName) {
			this.buName = buName;
		}
}
