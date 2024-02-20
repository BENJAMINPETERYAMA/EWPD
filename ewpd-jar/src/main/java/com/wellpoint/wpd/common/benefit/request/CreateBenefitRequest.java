package com.wellpoint.wpd.common.benefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


public class CreateBenefitRequest extends WPDRequest
{
    private String headingName;
    private String description;
    
    public CreateBenefitRequest() {
    super();
    
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
    
    public void validate() throws ValidationException {
        
    }
}
