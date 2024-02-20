
package com.wellpoint.wpd.web.adminmethod;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodsPopupBO;
import com.wellpoint.wpd.common.adminmethod.request.RetrieveAdminMethodsRequest;
import com.wellpoint.wpd.common.adminmethod.response.RetrieveAdminMethodsResponse;
import com.wellpoint.wpd.common.adminmethod.vo.AdminMethodsPopupVO;
import com.wellpoint.wpd.web.benefitcomponent.BenefitComponentSessionObject;
import com.wellpoint.wpd.web.contract.ContractSession;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.product.ProductSessionObject;
import com.wellpoint.wpd.web.productstructure.ProductStructureSessionObject;
import com.wellpoint.wpd.web.standardbenefit.StandardBenefitSessionObject;
import com.wellpoint.wpd.web.util.WebConstants;
import com.wellpoint.wpd.common.framework.util.StringUtil;

/**
 * @author u13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodPopupBackingBean extends WPDBackingBean{
	
	private String loadAdminMethodPopup;
	
	private String validation;
	
	private List adminMethods;
	
	private int count;
			
	public static String SPSID = BusinessConstants.SPSID;    
		
	public static String ENTITY_TYPE = BusinessConstants.ENTITY_TYPE;
		
	public static String STD_BEN_ID = BusinessConstants.STD_BEN_ID;
	
	private String spsName;	
	
	private List messages;
	
	private String loadAdminMethodViewPopup;
	
	private int adminId;
	
	private int entityId;
	
	private String entityType;
	
	private int adminMethodSysId;
	
	private String adminMethodSysIdValue;
	
	private List adminMethodConfig;
	/**
	* Method to retrieve Admin Methods List
	* 
	* @return String
	*/		
	public String getAdminMethodList(){

        AdminMethodsPopupVO adminMethodsPopupVO = new AdminMethodsPopupVO();
        HttpServletRequest request = getRequest();
        RetrieveAdminMethodsRequest adminMethodsRequest =  (RetrieveAdminMethodsRequest)this.getServiceRequest(ServiceManager.ADMIN_METHOD_POPUP_REQUEST);
        String spsId = request.getParameter(BusinessConstants.SPSID);	
        String entityId = request.getParameter(BusinessConstants.STD_BEN_ID);
        String entityType = request.getParameter(BusinessConstants.ENTITY_TYPE);
        String adminId = request.getParameter(BusinessConstants.ADMINID);
        String validation = request.getParameter("validation");
        //CARS AM1 START
        String benefitTierSysId = request.getParameter(BusinessConstants.BEN_TIER_SYS_ID);
        //CARS AM1 END        
        adminMethodsRequest.setEntityTypeForValidation(validation);
        adminMethodsRequest.setSpsId(Integer.parseInt(spsId));
        adminMethodsRequest.setAdminMethodsPopupVO(adminMethodsPopupVO);
        getValueInRequestFromSession(adminMethodsRequest, entityType);
        adminMethodsRequest.setEntityType(entityType);
        adminMethodsRequest.setAdminId(Integer.parseInt(adminId));
        //CARS AM1 START         
        if(null != benefitTierSysId){
        	adminMethodsRequest.setBenefitTierSysId(Integer.parseInt(benefitTierSysId));
        }else {
        	adminMethodsRequest.setBenefitTierSysId(-1);
        }
        //CARS AM1 END
        RetrieveAdminMethodsResponse adminMethodsResponse = (RetrieveAdminMethodsResponse) executeService(adminMethodsRequest);
        
        List adminMethodsList = adminMethodsResponse.getAdminMethods();
        if(null != adminMethodsList && !adminMethodsList.isEmpty()){
        	AdminMethodsPopupBO adminMethodsPopupBO;
        	for(Iterator adminMethodsListItr = adminMethodsList.iterator();
        			adminMethodsListItr.hasNext();){
        		adminMethodsPopupBO = (AdminMethodsPopupBO)adminMethodsListItr.next();
        		adminMethodsPopupBO
					.setActualAdminMthdDesc(
	        				adminMethodsPopupBO
							.getAdminMethodDesc()
							.replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
        	}
        }
        
        if(null != validation){
        	this.setValidation(validation);
        }
        
        if(null != adminMethodsResponse.getAdminMethods() && adminMethodsResponse.getAdminMethods().size() != 0){
	        this.setAdminMethods(adminMethodsResponse.getAdminMethods());
	        this.setCount(adminMethodsResponse.getAdminMethods().size());
	        this.setAdminId(Integer.parseInt(adminId));
	        this.setEntityId(Integer.parseInt(entityId));
	        this.setEntityType(entityType);
        } else {
            this.setMessages(adminMethodsResponse.getMessages());
        }
        return "";           
	}




	

    /**
     * Method sets values from session to request 
	 * @param adminMethodsRequest
	 */
	private void getValueInRequestFromSession(RetrieveAdminMethodsRequest adminMethodsRequest, String type) {
		if(WebConstants.BENEFIT_COMP.equalsIgnoreCase(type)){ //FIX ME Indenation, Remove hard coded
		    if((null != getSession().getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY) && !getSession().getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY).equals("")&&
		        null != getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID) && !getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID).equals(""))){
		        BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject)getSession().getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);			
		        adminMethodsRequest.setEntityId(benefitComponentSessionObject.getBenefitComponentId());
		        adminMethodsRequest.setStdBenftId(Integer.parseInt(getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID).toString()));
		   	 	adminMethodsRequest.setBcName(getSession().getAttribute(WebConstants.BENEFIT_COMP_NAME).toString());
		   	    adminMethodsRequest.setBenefitCompId(benefitComponentSessionObject.getBenefitComponentId());
		    }
		}else if(WebConstants.BENEFIT_TYPE.equalsIgnoreCase(type)){
		    if((null != getSession().getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY) && !getSession().getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY).equals("")&&
		           null != getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID) && !getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID).equals(""))){
				StandardBenefitSessionObject benefitSessionObject =(StandardBenefitSessionObject) getSession()
			    .getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);
				adminMethodsRequest.setEntityId(benefitSessionObject.getStandardBenefitKey());
				adminMethodsRequest.setStdDefid(Integer.parseInt(getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID).toString()));
		    }
		}else if(WebConstants.PROD_STRUCT.equalsIgnoreCase(type)){
			
			if("validation".equals(adminMethodsRequest.getEntityTypeForValidation())){
				
				    
				    
				adminMethodsRequest.setEntityId(Integer.parseInt(getSession().getAttribute("AM_ENTITY_KEY").toString()));
				 adminMethodsRequest.setBenefitCompId(Integer.parseInt(getSession().getAttribute("AM_BC_KEY").toString()));
				 adminMethodsRequest.setStdBenftId(Integer.parseInt(getSession().getAttribute("AM_BENEFIT").toString()));
				 adminMethodsRequest.setBcName(getSession().getAttribute("AM_BENEFIT_COMP_NAME").toString());
				
			}else{
		    if((null != getSession().getAttribute(WebConstants.PRODUCT_STRUCTURE_SESSION_KEY) && !getSession().getAttribute(WebConstants.PRODUCT_STRUCTURE_SESSION_KEY).equals("")&&
			           null != getSession().getAttribute(WebConstants.BNFT_CMPNT_KEY) && !getSession().getAttribute(WebConstants.BNFT_CMPNT_KEY).equals("")&&
			           null != getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID) && !getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID).equals(""))){
		        ProductStructureSessionObject productStructureSessionObject = (ProductStructureSessionObject) getSession()
	            .getAttribute(WebConstants.PRODUCT_STRUCTURE_SESSION_KEY);
		        adminMethodsRequest.setBenefitCompId(Integer.parseInt(getSession().getAttribute(WebConstants.BNFT_CMPNT_KEY).toString()));
		        String stdBenId = getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID).toString();
		        StringTokenizer stringTokenizer = new StringTokenizer(stdBenId,"~");
		        String standardBenId = stringTokenizer.nextToken();
		        adminMethodsRequest.setStdBenftId(Integer.parseInt(standardBenId));
				adminMethodsRequest.setEntityId(productStructureSessionObject.getId());
				adminMethodsRequest.setBcName(getSession().getAttribute(WebConstants.BENEFIT_COMP_NAME).toString());
		    }
			}
		}else if(WebConstants.PROD_TYPE.equalsIgnoreCase(type)){
			
			if("validation".equals(adminMethodsRequest.getEntityTypeForValidation())){
				
				    
				    
				adminMethodsRequest.setEntityId(Integer.parseInt(getSession().getAttribute("AM_ENTITY_KEY").toString()));
				 adminMethodsRequest.setBenefitCompId(Integer.parseInt(getSession().getAttribute("AM_BC_KEY").toString()));
				 adminMethodsRequest.setStdBenftId(Integer.parseInt(getSession().getAttribute("AM_BENEFIT").toString()));
				 adminMethodsRequest.setBcName(getSession().getAttribute("AM_BENEFIT_COMP_NAME").toString());
				
			}else{
			    if((null != getSession().getAttribute(WebConstants.PROD_TYPE) && !getSession().getAttribute(WebConstants.PROD_TYPE).equals("")&&
				           null != getSession().getAttribute(WebConstants.BNFT_CMPNT_KEY) && !getSession().getAttribute(WebConstants.BNFT_CMPNT_KEY).equals("")&&
				           null != getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID) && !getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID).equals(""))){
				    ProductSessionObject productSessionObject = (ProductSessionObject)getSession()
				    .getAttribute(WebConstants.PROD_TYPE);
					adminMethodsRequest.setEntityId(productSessionObject.getProductKeyObject().getProductId());
					 adminMethodsRequest.setBenefitCompId(Integer.parseInt(getSession().getAttribute(WebConstants.BNFT_CMPNT_KEY).toString()));
					 adminMethodsRequest.setStdBenftId(Integer.parseInt(getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID).toString()));
					 adminMethodsRequest.setBcName(getSession().getAttribute(WebConstants.BENEFIT_COMP_NAME).toString());
			    }
			}
		}else if(WebConstants.CONTRACT.equalsIgnoreCase(type)){
			if("validation".equals(adminMethodsRequest.getEntityTypeForValidation())){
				
				    
				ContractSession contractSessionObject = (ContractSession)getSession().getAttribute(WebConstants.CONTRACT);    
				adminMethodsRequest.setEntityId(Integer.parseInt(getSession().getAttribute("AM_ENTITY_KEY").toString()));
				adminMethodsRequest.setContractDateSgmntId(Integer.parseInt(getSession().getAttribute("AM_ENTITY_KEY").toString()));
				 adminMethodsRequest.setBenefitCompId(Integer.parseInt(getSession().getAttribute("AM_BC_KEY").toString()));
				 adminMethodsRequest.setStdBenftId(Integer.parseInt(getSession().getAttribute("AM_BENEFIT").toString()));
				 adminMethodsRequest.setBcName(getSession().getAttribute("AM_BENEFIT_COMP_NAME").toString());
				 adminMethodsRequest.setProdId(contractSessionObject.getContractKeyObject().getProductId());
				
			}else{
		    if((null != getSession().getAttribute(WebConstants.CONTRACT) && !getSession().getAttribute(WebConstants.CONTRACT).equals("")&&
			           null != getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID) && !getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID).equals(""))){
				ContractSession contractSessionObject = (ContractSession)getSession().getAttribute(WebConstants.CONTRACT);
				adminMethodsRequest.setContractDateSgmntId(contractSessionObject.getContractKeyObject().getDateSegmentId());
				adminMethodsRequest.setEntityId(contractSessionObject.getContractKeyObject(). getContractSysId());
				adminMethodsRequest.setProdId(contractSessionObject.getContractKeyObject().getProductId());
				if(contractSessionObject.getBenefitComponentId() != 0)
				 adminMethodsRequest.setBenefitCompId(contractSessionObject.getBenefitComponentId());
				else
				 adminMethodsRequest.setBenefitCompId(Integer.parseInt(getSession().getAttribute("AM_BC_KEY").toString()));	
				
				adminMethodsRequest.setStdBenftId(Integer.parseInt(getSession().getAttribute(WebConstants.SESSION_BNFT_DEFN_ID).toString()));
				adminMethodsRequest.setBcName(getSession().getAttribute(WebConstants.BENEFIT_COMP_NAME).toString());
		    }
			}
		}
		
	}



	/**
     * @return Returns the adminMethod.
     */
    public List getAdminMethods() {
        return adminMethods;
    }
    /**
     * @param adminMethod The adminMethod to set.
     */
    public void setAdminMethods(List adminMethods) {
        this.adminMethods = adminMethods;
    }
    /**
     * @return Returns the loadAdminMethodPopup.
     */
    public String getLoadAdminMethodPopup() {
        getAdminMethodList();
        return "";
    }
    /**
     * @param loadAdminMethodPopup The loadAdminMethodPopup to set.
     */
    public void setLoadAdminMethodPopup(String loadAdminMethodPopup) {
        this.loadAdminMethodPopup = loadAdminMethodPopup;
    }
    /**
     * Returns the count
     * @return int count.
     */

    public int getCount() {
        return count;
    }
    /**
     * Sets the count
     * @param count.
     */

    public void setCount(int count) {
        this.count = count;
    }
	/**
	 * @return Returns the spsName.
	 */
	public String getSpsName() {
		HttpServletRequest request = getRequest();
		return request.getParameter("spsName");
	}
	/**
	 * @param spsName The spsName to set.
	 */
	public void setSpsName(String spsName) {
		this.spsName = spsName;
	}
    /**
     * Returns the messages
     * @return List messages.
     */

    public List getMessages() {
        return messages;
    }
    /**
     * Sets the messages
     * @param messages.
     */

    public void setMessages(List messages) {
        this.messages = messages;
    }
	/**
	 * @return Returns the loadAdminMethodViewPopup.
	 */
	public String getLoadAdminMethodViewPopup() {
		String entityType=getRequest().getParameter(WebConstants.ENTITY_TYPE);
		if(StringUtil.regExPatterValidation(entityType)){
			getSession().setAttribute(WebConstants.ENTITY_TYPE,entityType);
			}else{
				entityType=null;
			}
		String adminId=getRequest().getParameter(WebConstants.ADMIN_ID);
			if(StringUtil.regExPatterValidation(adminId)){
			getSession().setAttribute(WebConstants.ADMIN_ID,adminId);
			}else{
				adminId=null;
			}
		String entityId=getRequest().getParameter(WebConstants.ENTITY_ID);
		if(StringUtil.regExPatterValidation(entityId)){
			getSession().setAttribute(WebConstants.ENTITY_ID,entityId);
			}else{
				entityId=null;
			}
		String adminMethodSysId=getRequest().getParameter(WebConstants.ADMIN_METHOD_SYSID);
		if(StringUtil.regExPatterValidation(adminMethodSysId)){
			getSession().setAttribute(WebConstants.ADMIN_METHOD_SYSID,adminMethodSysId);
			}else{
				adminMethodSysId=null;
			}
		getAdminMethodViewPopup();
		return "";
	}
	
	/**
	 * @return Returns the loadAdminMethodViewPopup.
	 */
	public String getLoadAdminMethodPrintPopup() {
		getAdminMethodViewPopup();
		return "";
	}

	/**
	 * Method to get available AdminMethods to populate in the Popup
	 * @return
	 */
	private void getAdminMethodViewPopup() {
        RetrieveAdminMethodsRequest adminMethodsRequest =  (RetrieveAdminMethodsRequest)this.getServiceRequest(ServiceManager.ADMIN_METHOD_POPUP_REQUEST);
        AdminMethodsPopupVO adminMethodsPopupVO = new AdminMethodsPopupVO();
        String validation = getRequest().getParameter("validation");
        int adminId = 0;
        int entityId = 0;
        int adminMethodSysId = 0;
        if(null != getRequest().getParameter(WebConstants.ADMIN_ID))
        	adminId = Integer.valueOf(getRequest().getParameter(WebConstants.ADMIN_ID)).intValue();
        else
        	adminId = Integer.valueOf((String)getSession().getAttribute(WebConstants.ADMIN_ID)).intValue();
        
        if(null != getRequest().getParameter(WebConstants.ENTITY_ID))
        	entityId = Integer.valueOf(getRequest().getParameter(WebConstants.ENTITY_ID)).intValue();
        else
        	entityId = Integer.valueOf((String)getSession().getAttribute(WebConstants.ENTITY_ID)).intValue();

        adminMethodsRequest.setAdminId(adminId);
        adminMethodsRequest.setEntityId(entityId);
        String entityType = "";
        if(null != getRequest().getParameter(WebConstants.ENTITY_TYPE))
        	entityType = getRequest().getParameter(WebConstants.ENTITY_TYPE);
        else
        	entityType = (String) getSession().getAttribute(WebConstants.ENTITY_TYPE);
        if(null != getRequest().getParameter(WebConstants.ADMIN_METHOD_SYSID))
        	adminMethodSysId = Integer.valueOf(getRequest().getParameter(WebConstants.ADMIN_METHOD_SYSID)).intValue();
        else
        	adminMethodSysId = Integer.valueOf((String)getSession().getAttribute(WebConstants.ADMIN_METHOD_SYSID)).intValue();

        //        String adminMethodId[] = new String[2];
        //adminMethodId = adminMethodSysId.split("~");
        adminMethodsPopupVO.setAdminMethodId(adminMethodSysId);
        adminMethodsRequest.setAdminMethodsPopupVO(adminMethodsPopupVO);
        adminMethodsRequest.setEntityType(entityType);
        if((null != validation && validation.equals("validation") && !validation.equals("")) || (null != this.validation && this.validation.equals("validation"))){
        	adminMethodsRequest.setEntityTypeForValidation("validation");
        }else{
        	
        }
        getValueInRequestFromSession(adminMethodsRequest,entityType);
        adminMethodsRequest.setAction(WebConstants.ADMIN_METHOD_VIEW_POPUP);
        RetrieveAdminMethodsResponse adminMethodsResponse = (RetrieveAdminMethodsResponse) executeService(adminMethodsRequest);
        if(null != adminMethodsResponse){
        		if(null != adminMethodsResponse.getAdminMethods() && !adminMethodsResponse.getAdminMethods().isEmpty()){
            		if(adminMethodsResponse.getAdminMethods().size() > 0){
                		List newList = adminMethodsResponse.getAdminMethods(); 
                		this.adminMethodConfig = new ArrayList();
                		for(int i = 0 ; i < newList.size() ; i++){
                			AdminMethodsPopupBO popupBO = (AdminMethodsPopupBO) newList.get(i);
                			if(null != popupBO.getAnswerDesc() && popupBO.getAnswerDesc().equalsIgnoreCase("NOT ANSWERED")){
                				popupBO.setAnswerDesc("");
                			}
                			this.adminMethodConfig.add(popupBO);
                		}
                		 
            		}else{
            			this.adminMethodConfig = null;
            		}
            	}else{
        			this.adminMethodConfig = null;
        		}
        	}else{
    			this.adminMethodConfig = null;
        	}
        }


	/**
	 * @param loadAdminMethodViewPopup The loadAdminMethodViewPopup to set.
	 */
	public void setLoadAdminMethodViewPopup(String loadAdminMethodViewPopup) {
		this.loadAdminMethodViewPopup = loadAdminMethodViewPopup;
	}
	/**
	 * @return Returns the adminId.
	 */
	public int getAdminId() {
		return adminId;
	}
	/**
	 * @param adminId The adminId to set.
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	/**
	 * @return Returns the entityId.
	 */
	public int getEntityId() {
		return entityId;
	}
	/**
	 * @param entityId The entityId to set.
	 */
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	/**
	 * @return Returns the adminMethodSysId.
	 */
	public int getAdminMethodSysId() {
		return adminMethodSysId;
	}
	/**
	 * @param adminMethodSysId The adminMethodSysId to set.
	 */
	public void setAdminMethodSysId(int adminMethodSysId) {
		this.adminMethodSysId = adminMethodSysId;
	}
	/**
	 * @return Returns the adminMethodSysIdValue.
	 */
	public String getAdminMethodSysIdValue() {
		return adminMethodSysIdValue;
	}
	/**
	 * @param adminMethodSysIdValue The adminMethodSysIdValue to set.
	 */
	public void setAdminMethodSysIdValue(String adminMethodSysIdValue) {
		this.adminMethodSysIdValue = adminMethodSysIdValue;
	}
	/**
	 * @return Returns the adminMethodConfig.
	 */
	public List getAdminMethodConfig() {
		return adminMethodConfig;
	}
	/**
	 * @param adminMethodConfig The adminMethodConfig to set.
	 */
	public void setAdminMethodConfig(List adminMethodConfig) {
		this.adminMethodConfig = adminMethodConfig;
	}
	/**
	 * @return Returns the validation.
	 */
	public String getValidation() {
		return validation;
	}
	/**
	 * @param validation The validation to set.
	 */
	public void setValidation(String validation) {
		this.validation = validation;
	}
}
