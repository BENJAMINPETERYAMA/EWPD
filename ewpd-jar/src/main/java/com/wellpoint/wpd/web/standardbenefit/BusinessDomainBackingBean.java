/*
 * Created on Feb 22, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.standardbenefit;

import com.wellpoint.wpd.web.framework.WPDBackingBean;

import java.util.ArrayList;
import java.util.List;


/**
 * @author u13274
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BusinessDomainBackingBean extends WPDBackingBean{
	
	private String lob = null;
	private String businessEntity = null;
	private String businessGroup = null;
	
	private List lobList = null;
	private List busEntityList = null;
	private List busGroupList = null;
	
	private BusinessDomainBO businessDomainBo1 = null;
	private BusinessDomainBO businessDomainBo2 = null;
	private BusinessDomainBO businessDomainBo3 = null;
	private BusinessDomainBO businessDomainBo4 = null;
	private BusinessDomainBO businessDomainBo5 = null;
	
	/*
	 * Constructor
	 */
	public BusinessDomainBackingBean() {
		
		lobList = new ArrayList();
		busEntityList = new ArrayList();
		busGroupList = new ArrayList();
		
		businessDomainBo1 = new BusinessDomainBOImpl();
		businessDomainBo2 = new BusinessDomainBOImpl();
		businessDomainBo3 = new BusinessDomainBOImpl();
		businessDomainBo4 = new BusinessDomainBOImpl();
		businessDomainBo5 = new BusinessDomainBOImpl();
	}
	
	/**
	 * @return Returns the busEntityList.
	 */
	public List getBusEntityList() {
		businessDomainBo1.setBusEntityCode(0);
		businessDomainBo1.setBusEntityDescription("BLUE CROSS OF CALIFORNIA");
		
		businessDomainBo2.setBusEntityCode(1);
		businessDomainBo2.setBusEntityDescription("UNICARE");
		
		businessDomainBo3.setBusEntityCode(2);
		businessDomainBo3.setBusEntityDescription("ISG");
		
		busEntityList.add(businessDomainBo1);
		busEntityList.add(businessDomainBo2);
		busEntityList.add(businessDomainBo3);
		
		
		return busEntityList;
	}
	/**
	 * @param busEntityList The busEntityList to set.  
	 */  
	public void setBusEntityList(List busEntityList) {
		this.busEntityList = busEntityList;
	}
	/**
	 * @return Returns the busGroupList.
	 */
	public List getBusGroupList() {
		businessDomainBo1.setBusGroupCode(0);
		businessDomainBo1.setBusGroupDescription("INDIVIDUAL");
		
		businessDomainBo2.setBusGroupCode(1);
		businessDomainBo2.setBusGroupDescription("SMALL GROUP");
		
		businessDomainBo3.setBusGroupCode(2);
		businessDomainBo3.setBusGroupDescription("LARGE GROUP");
		
		businessDomainBo4.setBusGroupCode(3);
		businessDomainBo4.setBusGroupDescription("STATE");
		
		businessDomainBo5.setBusGroupCode(4);
		businessDomainBo5.setBusGroupDescription("SENIOR");
		
		busGroupList.add(businessDomainBo1);
		busGroupList.add(businessDomainBo2);
		busGroupList.add(businessDomainBo3);
		busGroupList.add(businessDomainBo4);
		busGroupList.add(businessDomainBo5);
		
		return busGroupList;
	}
	/**
	 * @param busGroupList The busGroupList to set.
	 */
	public void setBusGroupList(List busGroupList) {
		this.busGroupList = busGroupList;
	}
	/**
	 * @return Returns the businessEntity.
	 */
	public String getBusinessEntity() {
		return businessEntity;
	}
	/**
	 * @param businessEntity The businessEntity to set.
	 */
	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}
	/**
	 * @return Returns the businessGroup.
	 */
	public String getBusinessGroup() {
		return businessGroup;
	}
	/**
	 * @param businessGroup The businessGroup to set.
	 */
	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}
	/**
	 * @return Returns the lob.
	 */
	public String getLob() {
		return lob;
	}
	/**
	 * @param lob The lob to set.
	 */
	public void setLob(String lob) {
		this.lob = lob;
	}
	/**
	 * @return Returns the lobList.
	 */
	public List getLobList() {
		lobList = new ArrayList(5);
		
		businessDomainBo1.setLobCode(0) ;
		businessDomainBo1.setLobDescription("Medical");
		
		businessDomainBo2.setLobCode(1) ;
		businessDomainBo2.setLobDescription("Dental");
		
		businessDomainBo3.setLobCode(2) ;
		businessDomainBo3.setLobDescription("Pharmacy");
		
		businessDomainBo4.setLobCode(3) ;
		businessDomainBo4.setLobDescription("Vision");
		
		businessDomainBo5.setLobCode(4) ;
		businessDomainBo5.setLobDescription("Life");
		
		lobList.add(businessDomainBo1);
		lobList.add(businessDomainBo2);
		lobList.add(businessDomainBo3);
		lobList.add(businessDomainBo4);
		lobList.add(businessDomainBo5);
		
		return lobList;
	}
	/**
	 * @param lobList The lobList to set.
	 */
	public void setLobList(List lobList) {
		this.lobList = lobList;
	}
}
