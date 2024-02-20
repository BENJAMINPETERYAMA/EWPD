/*
 * Created on Jul 1, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.contract.webservice;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import com.wellpoint.wpd.common.contract.model.Contract;
import com.wellpoint.wpd.common.contract.model.Messages;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ContractRuleWebService {
	
	public List getRuleSet()throws RemoteException, ServiceException ;
	public Messages validate(Contract contract,List ruleList )throws ServiceException, RemoteException;
	
}
