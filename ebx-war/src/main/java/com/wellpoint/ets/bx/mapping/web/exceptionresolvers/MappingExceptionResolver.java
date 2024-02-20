/*
 * Created on Jun 28, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.web.exceptionresolvers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;

/**
 * @author u22093
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MappingExceptionResolver extends SimpleMappingExceptionResolver{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	protected ModelAndView doResolveException(HttpServletRequest request,  HttpServletResponse response, 
			Object handler, Exception ex) {
			log.error("An Exception has occured in the application "+ ex, ex);
			StackTraceElement stack[] = ex.getStackTrace();
			StringBuffer stackTrace = new StringBuffer();
			for(int i=0;i<stack.length;i++){
				
				stackTrace.append(stack[i]);
			}
			ModelAndView modelAndView =  super.doResolveException(request, response, handler, ex);
			if(ex instanceof EBXException) {
				modelAndView.addObject("message", (((EBXException) ex).getDisplayDescription()));
			} else {
				modelAndView.addObject("message", (ex.getMessage()));
			}
			modelAndView.addObject("stackTrace", stackTrace);
			return modelAndView;
	  }

}
