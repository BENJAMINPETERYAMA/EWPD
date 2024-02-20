package com.wellpoint.wpd.business.benefit.service;

import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.common.benefit.bo.Benefit;
import com.wellpoint.wpd.common.benefit.request.CreateBenefitRequest;
import com.wellpoint.wpd.common.benefit.response.CreateBenefitResponse;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.ArrayList;
import java.util.List;


public class BenefitService extends WPDService
{
    public BenefitService() {
    }
    
    public WPDResponse execute(WPDRequest request) throws ServiceException {
        if(request instanceof CreateBenefitRequest)
            return execute((CreateBenefitRequest)request);
        return null;
    }
    
    public WPDResponse execute(CreateBenefitRequest request) throws ServiceException 
    {
        //TODO get from factory
        CreateBenefitResponse createBenefitResponse = new CreateBenefitResponse();
        
        try
        {
        BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory)ObjectFactory.getFactory(ObjectFactory.BOM);
        BusinessObjectManager bom = bomf.getBusinessObjectManager();
      
        Benefit benefit = getBenefit(request);
        bom.persist(benefit, request.getUser(), true); //insert
        
         createBenefitResponse.setBenefit(benefit);
       
        } 
        catch (Exception ex) 
        {
        	Logger.logError(ex);
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage  = "Failed while processing CreateBenefitRequest" ;
            throw new ServiceException(logMessage, logParameters, ex);
        } 
        
        return createBenefitResponse;
    }
    
  
    private Benefit getBenefit(CreateBenefitRequest request)
    {
        Benefit benefit = new Benefit();
        benefit.setHeadingName(request.getHeadingName());
        benefit.setDescription(request.getDescription());
        return benefit;
    }
}
