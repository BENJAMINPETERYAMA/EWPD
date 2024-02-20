/*
 * Created on Feb 22, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.standardbenefit;

import com.wellpoint.wpd.common.standardbenefit.bo.BenefitTerm;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitTermImpl;
import com.wellpoint.wpd.web.framework.WPDBackingBean;

import java.util.ArrayList;
import java.util.List;


/**
 * @author u13259
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class BenefitTermBackingbean extends WPDBackingBean {
    private List benefitTermQualifierList = new ArrayList();

    private List benefitTermList = new ArrayList();

    private BenefitTerm benefitTerm1;

    private BenefitTerm benefitTerm2;

    private BenefitTerm benefitTerm3;

    private BenefitTerm benefitTerm4;

    private BenefitTerm benefitTerm5;

    private BenefitTerm benefitTerm6;

    public BenefitTermBackingbean() {
        benefitTerm1 = new BenefitTermImpl();
        benefitTerm2 = new BenefitTermImpl();
        benefitTerm3 = new BenefitTermImpl();
        benefitTerm4 = new BenefitTermImpl();
        benefitTerm5 = new BenefitTermImpl();
        benefitTerm6 = new BenefitTermImpl();
    }

    /**
     * @return Returns the benefitTermQualifierList.
     */
    public List getBenefitTermQualifierList() {
        benefitTermQualifierList = new ArrayList(6);
        benefitTerm1.setQualifier("Individual");
        benefitTerm2.setQualifier("Waiver");
        benefitTerm3.setQualifier("Last Quarter");
        benefitTerm4.setQualifier("Family");
        benefitTerm5.setQualifier("Number of Days");
        benefitTerm6.setQualifier("Dollar Amounts");

        benefitTermQualifierList.add(benefitTerm1);
        benefitTermQualifierList.add(benefitTerm2);
        benefitTermQualifierList.add(benefitTerm3);
        benefitTermQualifierList.add(benefitTerm4);
        benefitTermQualifierList.add(benefitTerm5);
        benefitTermQualifierList.add(benefitTerm6);

        return benefitTermQualifierList;
    }

    /**
     * @param benefitTermQualifierList The benefitTermQualifierList to set.
     */
    public void setBenefitTermQualifierList(List benefitTermQualifierList) {
        this.benefitTermQualifierList = benefitTermQualifierList;
    }

    /**
     * @return Returns the benefitTermList.
     */
    public List getBenefitTermList() {
        benefitTermList = new ArrayList(4);
        benefitTerm1.setTerm("Copay");
        benefitTerm2.setTerm("Deductable");
        benefitTerm3.setTerm("Payment Percent");
        benefitTerm4.setTerm("Stop Loss");

        benefitTermList.add(benefitTerm1);
        benefitTermList.add(benefitTerm2);
        benefitTermList.add(benefitTerm3);
        benefitTermList.add(benefitTerm4);

        return benefitTermList;
    }

    /**
     * @param benefitTermList The benefitTermList to set.
     */
    public void setBenefitTermList(List benefitTermList) {
        this.benefitTermList = benefitTermList;
    }
}
