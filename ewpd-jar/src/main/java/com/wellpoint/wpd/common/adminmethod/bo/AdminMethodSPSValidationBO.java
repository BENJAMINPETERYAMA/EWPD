/*
 * Created on Jan 14, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.bo;


import java.util.Map;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodSPSValidationBO extends BusinessObject {
	
	
	private int sprPrcssStpSysId;
	private String sprPrcssStpNm;
	private int admnMthdSysId;
	private String admnMthdDesc;
	private String admnMthdReqSps;
	private String referenceDesc;
	private String value;
	private String type;
	private String productFamily;
	
	private int entityId;
	private int prodId;
	private int benCompId;
	private int benId;
	private int benAdminId;
	private int keyAttr;
	private int admnMthdSpsGrpId;
	//CARS START
	private String methodType;
	private int tierSysId;
	//CARS END

	Map amRefParamMap;

	/**
	 * @return Returns the amRefParamMap.
	 */
	public Map getAmRefParamMap() {
		return amRefParamMap;
	}
	/**
	 * @param amRefParamMap The amRefParamMap to set.
	 */
	public void setAmRefParamMap(Map amParamMap) {
		this.amRefParamMap = amParamMap;
	}
	/**
	 * @return Returns the admnMthdSpsGrpId.
	 */
	public int getAdmnMthdSpsGrpId() {
		return admnMthdSpsGrpId;
	}
	/**
	 * @param admnMthdSpsGrpId The admnMthdSpsGrpId to set.
	 */
	public void setAdmnMthdSpsGrpId(int admnMthdSpsGrpId) {
		this.admnMthdSpsGrpId = admnMthdSpsGrpId;
	}
	/**
	 * @return Returns the keyAttr.
	 */
	public int getKeyAttr() {
		return keyAttr;
	}
	/**
	 * @param keyAttr The keyAttr to set.
	 */
	public void setKeyAttr(int keyAttr) {
		this.keyAttr = keyAttr;
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
	 * @return Returns the admnMthdReqSps.
	 */
	public String getAdmnMthdReqSps() {
		return admnMthdReqSps;
	}
	/**
	 * @param admnMthdReqSps The admnMthdReqSps to set.
	 */
	public void setAdmnMthdReqSps(String admnMthdReqSps) {
		this.admnMthdReqSps = admnMthdReqSps;
	}
	/**
	 * @return Returns the admnMthdSysId.
	 */
	public int getAdmnMthdSysId() {
		return admnMthdSysId;
	}
	/**
	 * @param admnMthdSysId The admnMthdSysId to set.
	 */
	public void setAdmnMthdSysId(int admnMthdSysId) {
		this.admnMthdSysId = admnMthdSysId;
	}
	/**
	 * @return Returns the admnMthdDesc.
	 */
	public String getAdmnMthdDesc() {
		return admnMthdDesc;
	}
	/**
	 * @param admnMthdDesc The admnMthdDesc to set.
	 */
	public void setAdmnMthdDesc(String dmnMthdDesc) {
		this.admnMthdDesc = dmnMthdDesc;
	}
	/**
	 * @return Returns the referenceDesc.
	 */
	public String getReferenceDesc() {
		return referenceDesc;
	}
	/**
	 * @param referenceDesc The referenceDesc to set.
	 */
	public void setReferenceDesc(String referenceDesc) {
		this.referenceDesc = referenceDesc;
	}
	/**
	 * @return Returns the sprPrcssStpNm.
	 */
	public String getSprPrcssStpNm() {
		return sprPrcssStpNm;
	}
	/**
	 * @param sprPrcssStpNm The sprPrcssStpNm to set.
	 */
	public void setSprPrcssStpNm(String sprPrcssStpNm) {
		this.sprPrcssStpNm = sprPrcssStpNm;
	}
	/**
	 * @return Returns the sprPrcssStpSysId.
	 */
	public int getSprPrcssStpSysId() {
		return sprPrcssStpSysId;
	}
	/**
	 * @param sprPrcssStpSysId The sprPrcssStpSysId to set.
	 */
	public void setSprPrcssStpSysId(int sprPrcssStpSysId) {
		this.sprPrcssStpSysId = sprPrcssStpSysId;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return Returns the benAdminId.
	 */
	public int getBenAdminId() {
		return benAdminId;
	}
	/**
	 * @param benAdminId The benAdminId to set.
	 */
	public void setBenAdminId(int benAdminId) {
		this.benAdminId = benAdminId;
	}
	/**
	 * @return Returns the benCompId.
	 */
	public int getBenCompId() {
		return benCompId;
	}
	/**
	 * @param benCompId The benCompId to set.
	 */
	public void setBenCompId(int benCompId) {
		this.benCompId = benCompId;
	}
	/**
	 * @return Returns the benId.
	 */
	public int getBenId() {
		return benId;
	}
	/**
	 * @param benId The benId to set.
	 */
	public void setBenId(int benId) {
		this.benId = benId;
	}
	/**
	 * @return Returns the entityId.
	 */
	public int getEntityId() {
		return entityId;
	}
	/**
	 * @param entityId The entityId to set.
	 */
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	/**
	 * @return Returns the prodId.
	 */
	public int getProdId() {
		return prodId;
	}
	/**
	 * @param prodId The prodId to set.
	 */
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	
	/**
	 * @return Returns the productFamily.
	 */
	public String getProductFamily() {
		return productFamily;
	}
	/**
	 * @param productFamily The productFamily to set.
	 */
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
//	CARS START
	/**
	 * @return Returns the methodType.
	 */
	public String getMethodType() {
		return methodType;
	}
	/**
	 * @param methodType The methodType to set.
	 */
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
//	CARS END
	/**
	 * @return Returns the tierSysId.
	 */
	public int getTierSysId() {
		return tierSysId;
	}
	/**
	 * @param tierSysId The tierSysId to set.
	 */
	public void setTierSysId(int tierSysId) {
		this.tierSysId = tierSysId;
	}
}
