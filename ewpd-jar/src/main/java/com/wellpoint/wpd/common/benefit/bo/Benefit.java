package com.wellpoint.wpd.common.benefit.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;


public class Benefit extends BusinessObject
{
    private String headingName;
    private String description;
    
    public Benefit() {
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
    
    public boolean equals(BusinessObject object) {
        return true;
    }

    public  int hashCode() {
        return 1;
    }

    public  String toString() {
       return "" ;
    }
}
