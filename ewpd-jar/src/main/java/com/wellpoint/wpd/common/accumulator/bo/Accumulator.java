package com.wellpoint.wpd.common.accumulator.bo;

import java.util.Date;

public interface Accumulator {
	
	/**
	 * @return svcCde
	 */
	public String getSvcCde();
	
	
	
	/**
	 * @return name
	 */
	public String getName();
	
	
	
	/**
	 * @return rootCaseFlg
	 */
	public String getRootCaseFlg();
	
	public String getBenefit();
	
	
	public String getQuestion();
	
	/**
	 * @return rootSubsCertFlg
	 */
	public String getRootSubsCertFlg();
	
	/**
	 * @return rootMbrCde
	 */
	public String getRootMbrCde();
	
	/**
	 * @return fromDtFlg
	 */
	public Date getFromDtFlg();
	
	/**
	 * @return thruDtFlg
	 */
	public Date getThruDtFlg();

	/**
	 * @return lookUpInd
	 */
	public String getLookUpInd();
	
	/**
	 * @return adltKey
	 */
	public String getAdltKey();
	
	/**
	 * @return occursFlg
	 */
	public String getOccursFlg();
	
	/**
	 * @return daysFlg
	 */
	public String getDaysFlg();
	
	/**
	 * @return moniesFlg
	 */
	public String getMoniesFlg();
	
	/**
	 * @return imageInd
	 */
	public String getImageInd();
	
	/**
	 * @return m204Ind
	 */
	public String getM204Ind();
	
	/**
	 * @return aionInd
	 */
	public String getAionInd();
	
	/**
	 * @return manuelInd
	 */
	public String getManuelInd();
	
	/**
	 * @return adjudSrtCde
	 */ 
	public String getAdjudSrtCde();
	
	/**
	 * @return grpSrtSeq
	 */
	public String getGrpSrtSeq();
	
	/**
	 * @return system
	 */
	public String getSystem();
	
	/**
	 * @return pva
	 */
	public String getPva();
	
	/**
	 * @return cstTyp
	 */
	public String getCstTyp();
	
	/**
	 * @return inClaimsFlg
	 */
	public String getInClaimsFlg();
	
	/**
	 * @return delFlg
	 */
	public String getDelFlg();
	
	public String getVariableId();
	

	public void setAdjudSrtCde(String adjudSrtCde);

	public void setAdltKey(String adltKey);

	public void setAionInd(String aionInd);

	public void setCstTyp(String cstTyp);

	public void setDaysFlg(String daysFlg);

	public void setDelFlg(String delFlg);

	public void setFromDtFlg(Date fromDtFlg);

	public void setGrpSrtSeq(String grpSrtSeq);

	public void setImageInd(String imageInd);

	public void setInClaimsFlg(String inClaimsFlg);

	public void setLookUpInd(String lookUpInd);

	public void setM204Ind(String ind);
	
	public void setQuestion(String question);

	public void setManuelInd(String manuelInd);
	
	public void setMoniesFlg(String moniesFlg);

	public void setName(String name);
	
	public void setBenefit(String benefit);

	public void setOccursFlg(String occursFlg);

	public void setPva(String pva);

	public void setRootCaseFlg(String rootCaseFlg);

	public void setRootMbrCde(String rootMbrCde) ;

	public void setRootSubsCertFlg(String rootSubsCertFlg);

	public void setSvcCde(String svcCde) ;

	public void setSystem(String system) ;

	public void setThruDtFlg(Date thruDtFlg);
	
	public void setVariableId(String variableId);
	
	public String getHiddenSvcCde();

	public void setHiddenSvcCde(String hiddenSvcCde);
	
	public String getHiddenSystem();

	public void setHiddenSystem(String hiddenSystem);

	public boolean isAccumMappedtoVar();

	public void setAccumMappedtoVar(boolean accumMappedtoVar) ;
}
