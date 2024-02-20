/*
 * Created on Mar 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.mandate;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.mandate.request.CreateMandateRequest;
import com.wellpoint.wpd.common.mandate.response.CreateMandateResponse;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * @author u11442
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MandateBackingBean extends WPDBackingBean{
	
	
	private int mandateId;

    private int citationNumber;

    private String effectiveDate;

    private String expiryDate;

    private int mandateTypeId;

    private String mandateTypeDesc;

    private String jurisdictionId;

    private String jurisdictionDesc;

    private int groupSizeId;

    private String groupSizeDesc;

    private int fundingArrangementId;

    private String fundingArrangementDesc;

    private String description;

    private String mandateName;

    private String createdUser;

    private String createdTimestamp;

    private String lastUpdatedUser;

    private String lastUpdatedTimestamp;

    private int version;

    private String status;

    private List citationNumberList;

    private List jurisdictionList;
	
	ArrayList validationMessages ;
	
	
	

	/**
	 * @return
	 */
	public String create(){		
		validationMessages = new ArrayList();

        if (!validateRequiredFileds()) {
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        if (!validateDateFormat()) {
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        if (this.dateComparison(this.getEffectiveDate(), this
                .getExpiryDate())) {
            validationMessages.add(new ErrorMessage(WebConstants.EXPIRY_GREATER_EFFECTIVE_DATE));
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        if (!validateDescriptionLength()) {
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        
        CreateMandateRequest createMandateRequest = getCreateMandateRequest();
        CreateMandateResponse createMandateResponse = 
        	(CreateMandateResponse)executeService(createMandateRequest);
        
        if (null != createMandateResponse){
            if(null!= createMandateResponse.getMandateBO()){
                this.mandateId = createMandateResponse.getMandateBO().getMandateId();
            }
            return "afterCreateMandate";
        }

       	return "";
	}
	/**
	 * @return
	 */
	private CreateMandateRequest getCreateMandateRequest() {
//	    CreateMandateRequest createMandateRequest = 
//    		(CreateMandateRequest)this.getServiceRequest(ServiceManager.CREATE_MANDATE);
//	    MandateVO  mandateVO  = new MandateVO ();
//	    mandateVO.setCitationNumber(this.getCitationNumber());
//	    mandateVO.setEffectiveDate(this.getEffectiveDate());
//	    mandateVO.setExpiryDate(this.getExpiryDate());
//	    mandateVO.setMandateTypeDesc(this.getMandateTypeDesc());
//	    mandateVO.setMandateTypeId(this.getMandateTypeId());
//	    mandateVO.setFundingArrangementDesc(this.getFundingArrangementDesc());
//	    mandateVO.setFundingArrangementId(this.getFundingArrangementId());
//	    mandateVO.setGroupSizeDesc(this.getGroupSizeDesc());
//	    mandateVO.setGroupSizeId(this.getGroupSizeId());
//	    mandateVO.setJurisdictionDesc(this.getJurisdictionDesc());
//	    mandateVO.setJurisdictionId(this.getJurisdictionId());
//	    mandateVO.setJurisdictionList(this.getJurisdictionList());
//	    mandateVO.setDescription(this.getDescription());
//	    createMandateRequest.setMandateVO(mandateVO);
    	return null;		
	}
	public boolean validateDateFormat() {
	    validationMessages = new ArrayList();
	    boolean requiredDate = false;
	    if (!WPDStringUtil.isValidDate(effectiveDate)) {
            requiredDate = true;
            ErrorMessage errorMessage = new ErrorMessage(WebConstants.INPUT_FORMAT_INVALID);
            errorMessage.setParameters(new String[] {"Effective date" });
            validationMessages.add(errorMessage);
       }
       if (!WPDStringUtil.isValidDate(expiryDate)) {
           requiredDate = true;
           ErrorMessage errorMessage = new ErrorMessage(WebConstants.INPUT_FORMAT_INVALID);
           errorMessage.setParameters(new String[] {"Expiry date" });
           validationMessages.add(errorMessage);
       }
       

       if(requiredDate){
           return false;
       }
       return true;
	}
	
	public boolean dateComparison(String effectiveDate, String expiryDate) {
        int effectiveMonth = Integer.parseInt(effectiveDate.substring(0,
        		effectiveDate.indexOf("/")));
        int effectiveDay = Integer
                .parseInt(effectiveDate.substring(
                		effectiveDate.indexOf("/") + 1, effectiveDate
                                .lastIndexOf("/")));
        int effectiveYear = Integer.parseInt(effectiveDate.substring(
        		effectiveDate.lastIndexOf("/") + 1, effectiveDate.length()));

        int expiryMonth = Integer.parseInt(expiryDate.substring(0, expiryDate
                .indexOf("/")));
        int expiryDay = Integer.parseInt(expiryDate.substring(expiryDate
                .indexOf("/") + 1, expiryDate.lastIndexOf("/")));
        int expiryYear = Integer.parseInt(expiryDate.substring(expiryDate
                .lastIndexOf("/") + 1, expiryDate.length()));

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.set(effectiveYear, effectiveMonth, effectiveDay);
        cal2.set(expiryYear, expiryMonth, expiryDay);
        if (cal2.before(cal1)|| cal2.equals(cal1))
            return true;

        return false;

    }
	
	public boolean validateDescriptionLength() {
	    validationMessages = new ArrayList();
        int descLength = this.description.length();
        if(descLength<10||descLength>250){
            validationMessages.add(new ErrorMessage(WebConstants.INVALID_DESCRIPTION));
            return false;
        }
        return true;
    }
	/**
	 * @return
	 */
	private boolean validateRequiredFileds() {
		 validationMessages = new ArrayList();
		    boolean requiredField =true;
		    //Need clarification
		    if ("".equals(new Integer(citationNumber).toString())|| null == new Integer(citationNumber).toString()) {
	            requiredField = false;
	        }
		    if (new Integer(-1).equals(new Integer(this.mandateTypeId))) {
	            requiredField = false;
	        }
		    if ("".equals(this.jurisdictionId)|| null==this.jurisdictionId) {
	            requiredField = false;
	        }
		    if (new Integer(-1).equals(new Integer(this.groupSizeId))) {
	            requiredField = false;
	        }
		    if (new Integer(-1).equals(new Integer(this.fundingArrangementId))) {
	            requiredField = false;
	        }
		    if ("".equals(this.effectiveDate)|| null==this.effectiveDate) {
	            requiredField = false;
	        }
	        if ("".equals(this.expiryDate)|| null==this.expiryDate) {
	            requiredField = false;
	        }
	        if ("".equals(this.description)|| null==this.description) {
	            requiredField = false;
	        }
	        if (!requiredField) {
	            validationMessages.add(new ErrorMessage(
	                    WebConstants.MANDATORY_FIELDS_REQUIRED));
	        }
	        return requiredField;
	}
	

	
		/**
		 * @return Returns the citationNumber.
		 */
		public int getCitationNumber() {
			return citationNumber;
		}
		/**
		 * @param citationNumber The citationNumber to set.
		 */
		public void setCitationNumber(int citationNumber) {
			this.citationNumber = citationNumber;
		}
		/**
		 * @return Returns the citationNumberList.
		 */
		public List getCitationNumberList() {
			return citationNumberList;
		}
		/**
		 * @param citationNumberList The citationNumberList to set.
		 */
		public void setCitationNumberList(List citationNumberList) {
			this.citationNumberList = citationNumberList;
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
		 * @return Returns the description.
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * @param description The description to set.
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		
		/**
		 * @return Returns the fundingArrangementDesc.
		 */
		public String getFundingArrangementDesc() {
			return fundingArrangementDesc;
		}
		/**
		 * @param fundingArrangementDesc The fundingArrangementDesc to set.
		 */
		public void setFundingArrangementDesc(String fundingArrangementDesc) {
			this.fundingArrangementDesc = fundingArrangementDesc;
		}
		/**
		 * @return Returns the groupSizeDesc.
		 */
		public String getGroupSizeDesc() {
			return groupSizeDesc;
		}
		/**
		 * @param groupSizeDesc The groupSizeDesc to set.
		 */
		public void setGroupSizeDesc(String groupSizeDesc) {
			this.groupSizeDesc = groupSizeDesc;
		}
		
		/**
		 * @return Returns the jurisdictionDesc.
		 */
		public String getJurisdictionDesc() {
			return jurisdictionDesc;
		}
		/**
		 * @param jurisdictionDesc The jurisdictionDesc to set.
		 */
		public void setJurisdictionDesc(String jurisdictionDesc) {
			this.jurisdictionDesc = jurisdictionDesc;
		}
		
		/**
		 * @return Returns the jurisdictionList.
		 */
		public List getJurisdictionList() {
			return jurisdictionList;
		}
		/**
		 * @param jurisdictionList The jurisdictionList to set.
		 */
		public void setJurisdictionList(List jurisdictionList) {
			this.jurisdictionList = jurisdictionList;
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
		 * @return Returns the mandateId.
		 */
		public int getMandateId() {
			return mandateId;
		}
		/**
		 * @param mandateId The mandateId to set.
		 */
		public void setMandateId(int mandateId) {
			this.mandateId = mandateId;
		}
		/**
		 * @return Returns the mandateName.
		 */
		public String getMandateName() {
			return mandateName;
		}
		/**
		 * @param mandateName The mandateName to set.
		 */
		public void setMandateName(String mandateName) {
			this.mandateName = mandateName;
		}
		/**
		 * @return Returns the mandateTypeDesc.
		 */
		public String getMandateTypeDesc() {
			return mandateTypeDesc;
		}
		/**
		 * @param mandateTypeDesc The mandateTypeDesc to set.
		 */
		public void setMandateTypeDesc(String mandateTypeDesc) {
			this.mandateTypeDesc = mandateTypeDesc;
		}
		
		/**
		 * @return Returns the status.
		 */
		public String getStatus() {
			return status;
		}
		/**
		 * @param status The status to set.
		 */
		public void setStatus(String status) {
			this.status = status;
		}
	/**
	 * @return Returns the validationMessages.
	 */
	public ArrayList getValidationMessages() {
		return validationMessages;
	}
	/**
	 * @param validationMessages The validationMessages to set.
	 */
	public void setValidationMessages(ArrayList validationMessages) {
		this.validationMessages = validationMessages;
	}
		/**
		 * @return Returns the version.
		 */
		public int getVersion() {
			return version;
		}
		/**
		 * @param version The version to set.
		 */
		public void setVersion(int version) {
			this.version = version;
		}
	/**
	 * @param groupSizeId The groupSizeId to set.
	 */
	public void setGroupSizeId(int groupSizeId) {
		this.groupSizeId = groupSizeId;
	}
	
	/**
	 * @param mandateTypeId The mandateTypeId to set.
	 */
	public void setMandateTypeId(int mandateTypeId) {
		this.mandateTypeId = mandateTypeId;
	}
	/**
	 * @return Returns the groupSizeId.
	 */
	public int getGroupSizeId() {
		return groupSizeId;
	}
	
	/**
	 * @return Returns the mandateTypeId.
	 */
	public int getMandateTypeId() {
		return mandateTypeId;
	}
	/**
	 * @param fundingArrangementId The fundingArrangementId to set.
	 */
	public void setFundingArrangementId(int fundingArrangementId) {
		this.fundingArrangementId = fundingArrangementId;
	}
	/**
	 * @return Returns the fundingArrangementId.
	 */
	public int getFundingArrangementId() {
		return fundingArrangementId;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(String createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * @param expiryDate The expiryDate to set.
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(String lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * @return Returns the createdTimestamp.
	 */
	public String getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @return Returns the effectiveDate.
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @return Returns the expiryDate.
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public String getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param jurisdictionId The jurisdictionId to set.
	 */
	public void setJurisdictionId(String jurisdictionId) {
		this.jurisdictionId = jurisdictionId;
	}
	/**
	 * @return Returns the jurisdictionId.
	 */
	public String getJurisdictionId() {
		return jurisdictionId;
	}
}
