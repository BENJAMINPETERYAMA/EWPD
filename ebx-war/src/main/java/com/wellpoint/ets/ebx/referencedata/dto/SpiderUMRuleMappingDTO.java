package com.wellpoint.ets.ebx.referencedata.dto;



import java.util.ArrayList;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData;
import com.wellpoint.ets.ebx.referencedata.vo.SpiderUMRuleMappingVO;



/**
* @author UST-GLOBAL ErrorCodeVO
*
*/
public class SpiderUMRuleMappingDTO {



/**
* Comment for <code>errorCode</code>
*/


	
	private String umRuleId;
	private String umRuleDesc;
	private String comment;
	
	

    private List<SpiderUMRuleMappingDTO> umMappingList = new ArrayList<SpiderUMRuleMappingDTO>();


	
	public String getUmRuleId() {
	return umRuleId;
	}
	
	
	
	public void setUmRuleId(String umRuleId) {
	this.umRuleId = umRuleId;
	}
	
	
	
	public String getUmRuleDesc() {
	return umRuleDesc;
	}
	
	
	
	public void setUmRuleDesc(String umRuleDesc) {
	this.umRuleDesc = umRuleDesc;
	}
	
	
	
	public String getComment() {
	return comment;
	}
	
	
	
	public void setComment(String comment) {
	this.comment = comment;
	}
	
	
	
	public List<SpiderUMRuleMappingDTO> getUmMappingList() {
	return umMappingList;
	}
	
	
	
	public void setUmMappingList(List<SpiderUMRuleMappingDTO> umMappingList) {
	this.umMappingList = umMappingList;
	}
	


}
