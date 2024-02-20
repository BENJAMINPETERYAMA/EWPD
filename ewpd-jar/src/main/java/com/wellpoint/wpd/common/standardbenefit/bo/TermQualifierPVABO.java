/*
 * Created on Apr 4, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

/**
 * @author u13259
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TermQualifierPVABO {
		private String termCode;
	    private String qualifierCode;
	    private String pvaCode;
	    private String codeDescText;
	    private int standardBenefitKey;
	    
		/**
		 * @return Returns the standardBenefitKey.
		 */
		public int getStandardBenefitKey() {
			return standardBenefitKey;
		}
		/**
		 * @param standardBenefitKey The standardBenefitKey to set.
		 */
		public void setStandardBenefitKey(int standardBenefitKey) {
			this.standardBenefitKey = standardBenefitKey;
		}
		/**
		 * @return Returns the pvaCode.
		 */
		public String getPvaCode() {
			return pvaCode;
		}
		/**
		 * @param pvaCode The pvaCode to set.
		 */
		public void setPvaCode(String pvaCode) {
			this.pvaCode = pvaCode;
		}
		/**
		 * @return Returns the qualifierCode.
		 */
		public String getQualifierCode() {
			return qualifierCode;
		}
		/**
		 * @param qualifierCode The qualifierCode to set.
		 */
		public void setQualifierCode(String qualifierCode) {
			this.qualifierCode = qualifierCode;
		}
		/**
		 * @return Returns the termCode.
		 */
		public String getTermCode() {
			return termCode;
		}
		/**
		 * @param termCode The termCode to set.
		 */
		public void setTermCode(String termCode) {
			this.termCode = termCode;
		}
		/**
		 * @return Returns the codeDescText.
		 */
		public String getCodeDescText() {
			return codeDescText;
		}
		/**
		 * @param codeDescText The codeDescText to set.
		 */
		public void setCodeDescText(String codeDescText) {
			this.codeDescText = codeDescText;
		}
}
