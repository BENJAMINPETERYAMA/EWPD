package com.wellpoint.wpd.common.accumulator.bo;

import java.util.Date;

public class AccumulatorImpl implements Accumulator {
	
	private String svcCde;
	
	private String benefit;
	
	private String question;
	
	private String name;
	
	private String rootCaseFlg;
	
	private String rootSubsCertFlg;
	
	private String variableId;
	
	private String rootMbrCde;
	
	private Date fromDtFlg;
	
	private Date thruDtFlg;
	
	private String lookUpInd;
	
	private String adltKey;
	
	private String occursFlg;
	
	private String daysFlg;
	
	private String moniesFlg;
	
	private String imageInd;
	
	private String m204Ind;
	
	private String aionInd;
	
	private String manuelInd;
	
	private String adjudSrtCde;
	
	private String grpSrtSeq;
	
	private String system;
	
	private String pva;
	
	private String cstTyp;
	
	private String inClaimsFlg;
	
	private String delFlg;
	
	private String hiddenSvcCde;
	
	private String hiddenSystem;
	
	private boolean accumMappedtoVar;
	

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getAdjudSrtCde()
	 */
	public String getAdjudSrtCde() {
		return adjudSrtCde;
	}

	/**
	 * @param adjudSrtCde
	 */
	public void setAdjudSrtCde(String adjudSrtCde) {
		this.adjudSrtCde = adjudSrtCde;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getAdltKey()
	 */
	public String getAdltKey() {
		return adltKey;
	}

	/**
	 * @param adltKey
	 */
	public void setAdltKey(String adltKey) {
		this.adltKey = adltKey;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getAionInd()
	 */
	public String getAionInd() {
		return aionInd;
	}

	/**
	 * @param aionInd
	 */
	public void setAionInd(String aionInd) {
		this.aionInd = aionInd;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getCstTyp()
	 */
	public String getCstTyp() {
		return cstTyp;
	}

	/**
	 * @param cstTyp
	 */
	public void setCstTyp(String cstTyp) {
		this.cstTyp = cstTyp;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getDaysFlg()
	 */
	public String getDaysFlg() {
		return daysFlg;
	}

	/**
	 * @param daysFlg
	 */
	public void setDaysFlg(String daysFlg) {
		this.daysFlg = daysFlg;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getDelFlg()
	 */
	public String getDelFlg() {
		return delFlg;
	}

	/**
	 * @param delFlg
	 */
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getFromDtFlg()
	 */
	public Date getFromDtFlg() {
		return fromDtFlg;
	}

	/**
	 * @param fromDtFlg
	 */
	public void setFromDtFlg(Date fromDtFlg) {
		this.fromDtFlg = fromDtFlg;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getGrpSrtSeq()
	 */
	public String getGrpSrtSeq() {
		return grpSrtSeq;
	}

	/**
	 * @param grpSrtSeq
	 */
	public void setGrpSrtSeq(String grpSrtSeq) {
		this.grpSrtSeq = grpSrtSeq;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getImageInd()
	 */
	public String getImageInd() {
		return imageInd;
	}

	/**
	 * @param imageInd
	 */
	public void setImageInd(String imageInd) {
		this.imageInd = imageInd;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getInClaimsFlg()
	 */
	public String getInClaimsFlg() {
		return inClaimsFlg;
	}

	/**
	 * @param inClaimsFlg
	 */
	public void setInClaimsFlg(String inClaimsFlg) {
		this.inClaimsFlg = inClaimsFlg;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getLookUpInd()
	 */
	public String getLookUpInd() {
		return lookUpInd;
	}

	/**
	 * @param lookUpInd
	 */
	public void setLookUpInd(String lookUpInd) {
		this.lookUpInd = lookUpInd;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getM204Ind()
	 */
	public String getM204Ind() {
		return m204Ind;
	}

	/**
	 * @param ind
	 */
	public void setM204Ind(String ind) {
		m204Ind = ind;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getManuelInd()
	 */
	public String getManuelInd() {
		return manuelInd;
	}

	/**
	 * @param manuelInd
	 */
	public void setManuelInd(String manuelInd) {
		this.manuelInd = manuelInd;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getMoniesFlg()
	 */
	public String getMoniesFlg() {
		return moniesFlg;
	}

	/**
	 * @param moniesFlg
	 */
	public void setMoniesFlg(String moniesFlg) {
		this.moniesFlg = moniesFlg;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		if(null != name)
		this.name = name.toUpperCase().trim();
		else
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getOccursFlg()
	 */
	public String getOccursFlg() {
		return occursFlg;
	}

	/**
	 * @param occursFlg
	 */
	public void setOccursFlg(String occursFlg) {
		this.occursFlg = occursFlg;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getPva()
	 */
	public String getPva() {
		return pva;
	}

	/**
	 * @param pva
	 */
	public void setPva(String pva) {
		this.pva = pva;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getRootCaseFlg()
	 */
	public String getRootCaseFlg() {
		return rootCaseFlg;
	}

	/**
	 * @param rootCaseFlg
	 */
	public void setRootCaseFlg(String rootCaseFlg) {
		this.rootCaseFlg = rootCaseFlg;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getRootMbrCde()
	 */
	public String getRootMbrCde() {
		return rootMbrCde;
	}

	/**
	 * @param rootMbrCde
	 */
	public void setRootMbrCde(String rootMbrCde) {
		this.rootMbrCde = rootMbrCde;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getRootSubsCertFlg()
	 */
	public String getRootSubsCertFlg() {
		return rootSubsCertFlg;
	}

	/**
	 * @param rootSubsCertFlg
	 */
	public void setRootSubsCertFlg(String rootSubsCertFlg) {
		this.rootSubsCertFlg = rootSubsCertFlg;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getSvcCde()
	 */
	public String getSvcCde() {
		return svcCde;
	}

	/**
	 * @param svcCde
	 */
	public void setSvcCde(String svcCde) {
		if(null != svcCde)
		this.svcCde = svcCde.toUpperCase().trim();
		else
		this.svcCde = svcCde;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getSystem()
	 */
	public String getSystem() {
		return system;
	}

	/**
	 * @param system
	 */
	public void setSystem(String system) {
		this.system = system;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.accumulatormaint.bo.lg.Accumulator#getThruDtFlg()
	 */
	public Date getThruDtFlg() {
		return thruDtFlg;
	}

	/**
	 * @param thruDtFlg
	 */
	public void setThruDtFlg(Date thruDtFlg) {
		this.thruDtFlg = thruDtFlg;
	}
	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}

	public String getVariableId() {
		return variableId;
	}

	public String getHiddenSvcCde() {
		return hiddenSvcCde;
	}

	public void setHiddenSvcCde(String hiddenSvcCde) {
		this.hiddenSvcCde = hiddenSvcCde;
	}

	public String getHiddenSystem() {
		return hiddenSystem;
	}

	public void setHiddenSystem(String hiddenSystem) {
		this.hiddenSystem = hiddenSystem;
	}

	public boolean isAccumMappedtoVar() { 
		return accumMappedtoVar;
	}

	public void setAccumMappedtoVar(boolean accumMappedtoVar) {
		this.accumMappedtoVar = accumMappedtoVar;
	}

	
	public String getBenefit() {
		return benefit;
	}

	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	
}
