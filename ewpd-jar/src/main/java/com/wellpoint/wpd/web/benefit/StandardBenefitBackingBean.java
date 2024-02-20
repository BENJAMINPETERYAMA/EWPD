/*
 * Created on Feb 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.wellpoint.wpd.web.benefit;

import com.wellpoint.wpd.common.benefit.request.CreateBenefitRequest;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;

import java.util.List;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm;

/**
 * StandardBenefitBackingBean contains the getters and setters of the 
 * variables and respective functions
 */
public class StandardBenefitBackingBean extends WPDBackingBean
{
  private String headingName;
  private String description;
 
  private HtmlForm form1;
  private HtmlCommandButton persistButton;
  
  public StandardBenefitBackingBean(){
      
  }

    public void setHeadingName(String headingName) {
        this.headingName = headingName;
    }

    public String getHeadingName() {
        return headingName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setForm1(HtmlForm form1) {
        this.form1 = form1;
    }

    public HtmlForm getForm1() {
        return form1;
    }


    public void setPersistButton(HtmlCommandButton persistButton) {
        this.persistButton = persistButton;
    }

    public HtmlCommandButton getPersistButton() {
        return persistButton;
    }
    
    public String createAction() 
    {
        CreateBenefitRequest createBenefitRequest = getCreateBenefitRequest();
        //out(createBenefitRequest.getDescription());
        //out(createBenefitRequest.getHeadingName());
       
        if(this.hasError()) {
                //showGenericError message
        }
        this.executeService(createBenefitRequest);
        if(this.hasError()) {
            //showGenericError message
        }  
        return "";
    }
    
    
    private CreateBenefitRequest getCreateBenefitRequest() 
    {
        CreateBenefitRequest createBenefitRequest = (CreateBenefitRequest) this.getServiceRequest(ServiceManager.CREATE_BENEFIT);      
        createBenefitRequest.setHeadingName(this.getHeadingName());
        createBenefitRequest.setDescription(this.getDescription());
        return createBenefitRequest;
    }
    
    
    private boolean hasError()
    {
        List messageList = this.getMessages();
        int size = messageList.size();
        if(messageList != null && size > 0)
        {
             for (int i = 0; i < size; i++)  {
                 Object messageObject = messageList.get(i);
                  if(messageObject != null && messageObject instanceof ErrorMessage ) {
                      return true;
                  }
                  
             }
        }
        return false;        
    }

//private void out(String message) {
//    
//}
 
}
