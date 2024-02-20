/*
 * Created on Mar 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.vo;

import java.util.List;


/**
 * @author u13154
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitSearchVO{
	
    private String name;

    private List lobCodeList;

    private List businessEntityCodeList;

    private List businessGroupCodeList;
    
    private List marketBusinessUnitList;

    private List termCodeList;

    private List qualifierCodeList;

    private List providerArrangementCodeList;
    
    private List dataTypeCodeList;
    
    private int benefitKey;
    
    private int maxSearchResultSize;
    
    private String benefitTypeCode;
    
    private String benefitCategory;
    

	/**
	 * 
	 */
	public StandardBenefitSearchVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return Returns the businessEntityCodeList.
	 */
	public List getBusinessEntityCodeList() {
		return businessEntityCodeList;
	}
	/**
	 * @param businessEntityCodeList The businessEntityCodeList to set.
	 */
	public void setBusinessEntityCodeList(List businessEntityCodeList) {
		this.businessEntityCodeList = businessEntityCodeList;
	}
	/**
	 * @return Returns the businessGroupCodeList.
	 */
	public List getBusinessGroupCodeList() {
		return businessGroupCodeList;
	}
	/**
	 * @param businessGroupCodeList The businessGroupCodeList to set.
	 */
	public void setBusinessGroupCodeList(List businessGroupCodeList) {
		this.businessGroupCodeList = businessGroupCodeList;
	}
	/**
	 * @return Returns the dataTypeCodeList.
	 */
	public List getDataTypeCodeList() {
		return dataTypeCodeList;
	}
	/**
	 * @param dataTypeCodeList The dataTypeCodeList to set.
	 */
	public void setDataTypeCodeList(List dataTypeCodeList) {
		this.dataTypeCodeList = dataTypeCodeList;
	}
	/**
	 * @return Returns the lobCodeList.
	 */
	public List getLobCodeList() {
		return lobCodeList;
	}
	/**
	 * @param lobCodeList The lobCodeList to set.
	 */
	public void setLobCodeList(List lobCodeList) {
		this.lobCodeList = lobCodeList;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the providerArrangementCodeList.
	 */
	public List getProviderArrangementCodeList() {
		return providerArrangementCodeList;
	}
	/**
	 * @param providerArrangementCodeList The providerArrangementCodeList to set.
	 */
	public void setProviderArrangementCodeList(List providerArrangementCodeList) {
		this.providerArrangementCodeList = providerArrangementCodeList;
	}
	/**
	 * @return Returns the qualifierCodeList.
	 */
	public List getQualifierCodeList() {
		return qualifierCodeList;
	}
	/**
	 * @param qualifierCodeList The qualifierCodeList to set.
	 */
	public void setQualifierCodeList(List qualifierCodeList) {
		this.qualifierCodeList = qualifierCodeList;
	}
	/**
	 * @return Returns the termCodeList.
	 */
	public List getTermCodeList() {
		return termCodeList;
	}
	/**
	 * @param termCodeList The termCodeList to set.
	 */
	public void setTermCodeList(List termCodeList) {
		this.termCodeList = termCodeList;
	}
	/**
	 * Returns the benefitKey
	 * @return String benefitKey.
	 */
	public int getBenefitKey() {
		return benefitKey;
	}
	/**
	 * Sets the benefitKey
	 * @param benefitKey.
	 */
	public void setBenefitKey(int benefitKey) {
		this.benefitKey = benefitKey;
	}
	/**
	 * @return Returns the maxSearchResultSize.
	 */
	public int getMaxSearchResultSize() {
		return maxSearchResultSize;
	}
	/**
	 * @param maxSearchResultSize The maxSearchResultSize to set.
	 */
	public void setMaxSearchResultSize(int maxSearchResultSize) {
		this.maxSearchResultSize = maxSearchResultSize;
	}
	/**
	 * @return Returns the benefitTypeCode.
	 */
	public String getBenefitTypeCode() {
		return benefitTypeCode;
	}
	/**
	 * @param benefitTypeCode The benefitTypeCode to set.
	 */
	public void setBenefitTypeCode(String benefitTypeCode) {
		this.benefitTypeCode = benefitTypeCode;
	}
	/**
	 * @return Returns the benefitCategory.
	 */
	public String getBenefitCategory() {
		return benefitCategory;
	}
	/**
	 * @param benefitCategory The benefitCategory to set.
	 */
	public void setBenefitCategory(String benefitCategory) {
		this.benefitCategory = benefitCategory;
	}
	/**
	 * @return Returns the marketBusinessUnitList.
	 */
	public List getMarketBusinessUnitList() {
		return marketBusinessUnitList;
	}
	/**
	 * @param marketBusinessUnitList The marketBusinessUnitList to set.
	 */
	public void setMarketBusinessUnitList(List marketBusinessUnitList) {
		this.marketBusinessUnitList = marketBusinessUnitList;
	}
}
