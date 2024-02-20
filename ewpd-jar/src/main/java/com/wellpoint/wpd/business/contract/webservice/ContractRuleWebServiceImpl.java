/*
 * Created on Jul 9, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.contract.webservice;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.xml.rpc.ServiceException;

import com.wellpoint.blaze.ruleservice.contractvalidation.WSUtil;
import com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIF;
import com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIFServiceLocator;
import com.wellpoint.wpd.common.contract.model.Contract;
import com.wellpoint.wpd.common.contract.model.Messages;



/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractRuleWebServiceImpl implements ContractRuleWebService {
	
	private static final String WEBSERVICE_RESOURCE_FILE = "webservice";
	private static final String WEBSERVICE_SERVER_PATH_ID = "webservice.path";

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.business.contract.webservice.ContractRuleWebService#getRuleSet()
	 */
	public List getRuleSet() throws RemoteException, ServiceException {
		// TODO Auto-generated method stub
		connect();
		java.util.Vector ruleSet= invokeRuleCategoryEntryPoint();
		return WSUtil.convertRuleSet(ruleSet);

	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.business.contract.webservice.ContractRuleWebService#validate(com.wellpoint.wpd.common.contract.model.Contract, java.util.List)
	 */
	public Messages validate(Contract contract, List ruleList) throws ServiceException, RemoteException {
		// TODO Auto-generated method stub
		Vector vector=new Vector();
		connect();
		com.wellpoint.wpd.common.contract.ws.model.Contract wscontract=WSUtil.convert(contract);
		for(int i=0;i<ruleList.size();i++)
			vector.add(ruleList.get(i));
		com.wellpoint.wpd.common.contract.ws.model.Messages messages=invokeRuleExecutionEntryPoint(wscontract,vector);
		
		return WSUtil.convert(messages);
	}
	
	
	
	/**
	 *	invokes a WellPointRuleServiceBean process for the entry point
	 *	"invokeRuleExecutionEntryPoint"
	 *
	 *	@param	arg0		==>Enter a description here	 *	@param	arg1		==>Enter a description here
	 *	@return	com.wellpoint.wpd.common.contract.model.ws.Messages	==>Enter a description of the return value
	 * @throws RemoteException
	 */
	public com.wellpoint.wpd.common.contract.ws.model.Messages invokeRuleExecutionEntryPoint(com.wellpoint.wpd.common.contract.ws.model.Contract arg0, java.util.Vector arg1) throws RemoteException
			
	{
		com.wellpoint.wpd.common.contract.ws.model.Messages retVal = (com.wellpoint.wpd.common.contract.ws.model.Messages)_service.invokeRuleExecutionEntryPoint(arg0, arg1);
		return retVal;

	}



	private WellPointRuleServiceIF _service;

	/**
	 *	Connect to the bean
	 * @throws ServiceException
	 */
	public void connect() throws ServiceException
			
	{
		
		WellPointRuleServiceIFServiceLocator locator = new WellPointRuleServiceIFServiceLocator(getWebServiceServerPath());
		_service = locator.getWellPointRuleServiceService();
	}

	/**
	 *	invokes a WellPointRuleServiceBean process for the entry point
	 *	"invokeRuleCategoryEntryPoint"
	 *

	 *	@return	java.util.Vector	==>Enter a description of the return value
	 */
	public java.util.Vector invokeRuleCategoryEntryPoint()
			throws RemoteException
	{
		Object object=_service.invokeRuleCategoryEntryPoint();
		java.util.Vector retVal = (java.util.Vector)object;
		return retVal;

	}
	
	public String getWebServiceServerPath(){
		ResourceBundle resourceBundle = ResourceBundle.getBundle(WEBSERVICE_RESOURCE_FILE,Locale.getDefault());
		String path = resourceBundle.getString(WEBSERVICE_SERVER_PATH_ID);
		return path;
	}

}
