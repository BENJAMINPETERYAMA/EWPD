/*
 * <AjaxViewAllRuleIdDetailController.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */

package com.wellpoint.ets.ebx.mapping.web.ajax.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.owasp.esapi.ESAPI;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentMappingVO;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.ebx.mapping.application.LocateFacade;
/**
 * @author UST-GLOBAL This is a controller class for rendering in 
 * detail for a published mapping of a rule 
 * 
 */
public class AjaxViewAllRuleIdDetailController  implements Controller{

	private LocateFacade locateRuleIdFacade;
	private Logger log = Logger.getLogger(this.getClass());
	/**
	 * Retrieve the Published mapping's audit trail record
	 * and display the changed values of a mapping
	 * rule id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) 
	    throws EBXException {

			Map map = new HashMap();
			Mapping mapping = new Mapping();
			Rule rule = new Rule();	
			rule.setHeaderRuleId(request.getParameter("ruleId"));
			mapping.setRule(rule);	
			
	        List mappings = locateRuleIdFacade.getRecords(mapping, null, null, -1, -1, null);
	      	
	        if((null != mappings) && (!mappings.isEmpty())){
	        	
	        	mapping = (Mapping) mappings.get(0);
	        	
	        	 if(null == mapping.getAuditTrails() || mapping.getAuditTrails().isEmpty()){
	 	            List infoList = new ArrayList();
	 	            infoList.add("No results to display");
	 	            map.put(WebConstants.INFO_MESSAGES, infoList);
	 	        }else{
	 	        	
	 	        XStream stream = new XStream();
	 	       stream.allowTypes(new Class[] {HippaSegmentMappingVO.class});
	 	   		stream.alias("mapping", HippaSegmentMappingVO.class);
	 	   		List hippaSegmentMappingList = new ArrayList();
	 	   		for (Iterator iterator = mapping.getAuditTrails().iterator(); iterator.hasNext();) {
	 	   			AuditTrail auditTrail = (AuditTrail) iterator.next();
	 	   			String systemCommentXml = auditTrail.getSystemComments();
	 	   			if(systemCommentXml != null && systemCommentXml.trim().length() != 0 && 
	 	   					auditTrail.getMappingStatus().equals(DomainConstants.STATUS_PUBLISHED)) {
	 	   			try {
	 	   				HippaSegmentMappingVO hippaSegmentMappingVO = 
	 	   					(HippaSegmentMappingVO)stream.fromXML(systemCommentXml);
	 	   				auditTrail.setHippaSegmentMappingVO(hippaSegmentMappingVO);
	 	   				hippaSegmentMappingList.add(auditTrail);
	 	   		} catch (Exception e) {
					log.info(ESAPI.encoder().encodeForHTML("Invalid system comment for RULE ID "
									+ mapping.getRule().getHeaderRuleId()
									+ ". The system comment should be a valid xml."));
				}
	 	   			}
	 	   		}	 	        	
	 	         map.put("auditTrailInDetailList", hippaSegmentMappingList);
	 	        }
	        } 
	       
	        return new ModelAndView("auditTrailInDetailRule",map);
	    }

		public LocateFacade getLocateRuleIdFacade() {
			return locateRuleIdFacade;
		}

		public void setLocateRuleIdFacade(LocateFacade locateRuleIdFacade) {
			this.locateRuleIdFacade = locateRuleIdFacade;
		}

}
