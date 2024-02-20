/*
 * Created on Jun 7, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.web.ajax.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.wellpoint.ets.bx.mapping.application.VariableMappingFacade;
import com.wellpoint.ets.bx.mapping.web.WebConstants;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AjaxViewAuditTrailInDetailController implements Controller{
    
    private VariableMappingFacade variableMappingFacade;
    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String variableId = request.getParameter("variableId");
        List auditTrailInDetailList = null;
        auditTrailInDetailList = variableMappingFacade.retreiveAuditTrailInDetail(variableId);
        Map map = new HashMap();
        
        if(auditTrailInDetailList== null ||(auditTrailInDetailList!=null && auditTrailInDetailList.isEmpty() )){
            List infoList = new ArrayList();
            infoList.add("No results to display");
            map.put(WebConstants.INFO_MESSAGES, infoList);
        }else{
            map.put("auditTrailInDetailList", auditTrailInDetailList);
        }
        return new ModelAndView("auditTrailInDetail",map);
    }

    /**
     * @return Returns the variableMappingFacade.
     */
    public VariableMappingFacade getVariableMappingFacade() {
        return variableMappingFacade;
    }
    /**
     * @param variableMappingFacade The variableMappingFacade to set.
     */
    public void setVariableMappingFacade(VariableMappingFacade variableMappingFacade) {
        this.variableMappingFacade = variableMappingFacade;
    }
}
