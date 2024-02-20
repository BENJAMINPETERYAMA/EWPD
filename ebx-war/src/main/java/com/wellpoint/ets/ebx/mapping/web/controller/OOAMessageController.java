package com.wellpoint.ets.ebx.mapping.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.mapping.web.view.OOAMsgExcelReport;
import com.wellpoint.ets.ebx.ooamessage.application.OOAMessageFacade;
import com.wellpoint.ets.ebx.ooamessage.util.MaintainOOAMessageRequest;
import com.wellpoint.ets.ebx.ooamessage.util.OOAMessageConstants;

public class OOAMessageController extends MultiActionController {
	
	private static Logger logger = Logger
			.getLogger(OOAMessageController.class);
	private OOAMessageFacade ooaMessageFacade;

	public OOAMessageFacade getOoaMessageFacade() {
		return ooaMessageFacade;
	}

	public void setOoaMessageFacade(OOAMessageFacade ooaMessageFacade) {
		this.ooaMessageFacade = ooaMessageFacade;
	}

	
	/**
	 * This method is used to change the status of ooa message
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */		
	public ModelAndView deleteOOAMessage(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String nextScreen = "";
		MaintainOOAMessageRequest maintainOOAMessageRequest = new MaintainOOAMessageRequest();
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			String networkOrContractCode = request.getParameter("networkOrContractCode");
			String messageId = request.getParameter("messageId");
			Integer msgId = new Integer(messageId);
			String search = request.getParameter("search");
			String exchangeIndicator=request.getParameter("excInd");
			List oOAMessageSearchDetailList = ooaMessageFacade.searchOOAMessageDetails(
					search,networkOrContractCode, "viewFunction",exchangeIndicator, messageId);
			MaintainOOAMessageRequest ooaMessageRequest = (MaintainOOAMessageRequest) oOAMessageSearchDetailList.get(0);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			StringBuffer auditcomments=new StringBuffer("[");
			String userComment=request.getParameter("userComment");
			if(null==userComment||userComment.isEmpty()){
				userComment="Message marked for Deleted";
				auditcomments=auditcomments.append(request.getAttribute(SecurityConstants.USER_NAME).toString()+","+
						simpleDateFormat.format(new Date())+"] "+userComment.toUpperCase());
			}else{
				auditcomments=auditcomments.append(request.getAttribute(SecurityConstants.USER_NAME).toString()+","+
						simpleDateFormat.format(new Date())+"] "+userComment.toUpperCase());
				
			}
		
			if(null == ooaMessageRequest.getComments()){
				maintainOOAMessageRequest.setComments(auditcomments.toString());
			}else{
				maintainOOAMessageRequest.setComments(auditcomments.toString()+"|"+ooaMessageRequest.getComments());
			}
			maintainOOAMessageRequest.setUserId(request.getAttribute(SecurityConstants.USER_NAME).toString());
			ooaMessageFacade.deleteOOAMessage(search, msgId, networkOrContractCode,maintainOOAMessageRequest);
			modelAndView.addObject("deleteStatus","SUCCESS");
		} catch (Exception ex) {
			logger.error("Error occurred in deleteOOAMessage Method");
			logger.error(ex.getMessage());
			modelAndView.addObject("deleteStatus","ERROR");
		}

		
		modelAndView.addObject(maintainOOAMessageRequest);
		return modelAndView;

	}
	
	
	/**
	 * This method is used to generate the Excel report
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */	
	public ModelAndView exportOOAMessageExcelFile(HttpServletRequest request,
			HttpServletResponse response) throws Exception  {
		
		String exportType= ESAPI.encoder().encodeForURL(request.getParameter("Search"));
		StringBuffer fileName=new StringBuffer(OOAMessageConstants.exportFileName).append(exportType);
		fileName.append(".xls");	
	
		ModelAndView mv = new ModelAndView();
		try {
			List<MaintainOOAMessageRequest> list = ooaMessageFacade.exportOOAMessageExcelFile(exportType);
			response.setHeader("Content-Type", "application/octet-stream");
			response.setHeader("content-disposition", "attachment; filename=" + fileName.toString());
		  
			mv.setView(new OOAMsgExcelReport(list,exportType));
					
				} catch (EBXException ex) {
					logger.error("EBXException occurred in exportOOAMessageExcelFile Method");
					logger.error(ex.getMessage());
				}
					catch (IOException ex) {
						logger.error("IOException occurred in exportOOAMessageExcelFile Method");
						logger.error(ex.getMessage());
					
				} catch (Exception ex) {
					logger.error("Exception occurred in exportOOAMessageExcelFile Method");
					logger.error(ex.getMessage());
				}
				return mv;

			}

}
