package com.wellpoint.wpd.common.benefit.response;

import com.wellpoint.wpd.common.benefit.bo.Benefit;
import com.wellpoint.wpd.common.framework.response.WPDResponse;


public class CreateBenefitResponse extends WPDResponse
{

    Benefit benefit = new Benefit();
    
    public CreateBenefitResponse() {
    }


    public void setBenefit(Benefit benefit) {
        this.benefit = benefit;
    }

    public Benefit getBenefit() {
        return benefit;
    }
}
