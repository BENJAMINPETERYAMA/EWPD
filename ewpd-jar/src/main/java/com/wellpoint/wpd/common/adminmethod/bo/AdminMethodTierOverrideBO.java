/*
 * Created on Oct 15, 2009
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.bo;
import java.util.Map;
import java.util.HashMap;
//import com.ibm.wsspi.sib.exitpoint.ra.HashMap;
import com.wellpoint.wpd.common.bo.BusinessObject;
/**
 * @author U20695
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodTierOverrideBO extends AdminMethodOverrideBO{
	
	
	private String termQuery ="";
	//private String productFamily;	//CARS:AM2|POS
    private Map termPVAMap = new HashMap(); //CARS:AM2|POS
    private String termName ="";
    
    private int random; 
    
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
//  CARS:AM2|POS|{
	public Map getTermPVAMap() {
		return termPVAMap;
	}
	public void setTermPVAMap(Map termPVAMap) {
		this.termPVAMap = termPVAMap;
	}
//  CARS:AM2|POS|}	
	
	public String getTermQuery() {
		return termQuery;
	}
	/**
	 * @param termQuery The termQuery to set.
	 */
	public void setTermQuery(String termQuery) {
		this.termQuery = termQuery;
	}
		
 
	public int getRandom() {
		return random;
	}
	public void setRandom(int random) {
		this.random = random;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	    /**
     * Returns the adminMethod
     * @return String adminMethod.
     */

   
}
