/*
 * Created on Feb 15, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.benefitcomponent;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitHierarchy;
import com.wellpoint.wpd.web.framework.WPDBackingBean;

import java.util.ArrayList;
import java.util.List;


/**
 * @author u14617
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class BenefitBackingBean extends WPDBackingBean{
	
	List benefitList = null;
	BenefitHierarchy benefitFirst=null;
	BenefitHierarchy benefitSecond=null;
	BenefitHierarchy benefitThird=null;
	BenefitHierarchy benefitFourth=null;
		
/**
 * 
 *
 */
	public BenefitBackingBean(){
		benefitFirst=new BenefitHierarchy();
		benefitSecond=new BenefitHierarchy();
		benefitThird=new BenefitHierarchy();
		benefitFourth=new BenefitHierarchy();
	}
	/**
	 * Returns the benefitList
	 * 
	 * @return List benefitList
	 */
	public List getbenefitList() {
		
		benefitList = new ArrayList(4);
				
		benefitFirst.setBenefit("MM Deductible");		
		benefitSecond.setBenefit("General Basis of Payment");		
		benefitThird.setBenefit("Stop Loss");		
		benefitFourth.setBenefit("Lifetime Maximum");
		
		benefitList.add(benefitFirst);
		benefitList.add(benefitSecond);
		benefitList.add(benefitThird);
		benefitList.add(benefitFourth);
		
		return benefitList;
	}

	/**
	 * 
	 * @param List benefitList.
	 */
	public void setbenefitList(List benefitList) {
		this.benefitList = benefitList;
	}
}