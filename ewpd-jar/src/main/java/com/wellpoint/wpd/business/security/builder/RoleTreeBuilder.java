/*
 * RoleTreeBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.security.builder;

import java.util.List;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.security.adapter.RoleAdapterManager;
import com.wellpoint.wpd.business.security.locatecriteria.RoleLocateCriteria;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.security.bo.ModuleBO;
import com.wellpoint.wpd.common.security.bo.ModuleSrdaBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RoleTreeBuilder {

    /**
     * Method for locating the associated modules
     * 
     * @param moduleBO
     * @return List
     * @throws SevereException
     */
    public List locateModules(ModuleBO moduleBO) throws SevereException,AdapterException {
        RoleAdapterManager adapterManager = new RoleAdapterManager();
        return adapterManager.retrieveRoleModuleAssociation(moduleBO);
    }
    /**
     * Method for locating the associated modules
     * 
     * @param moduleBO
     * @return List
     * @throws SevereException
     */
    public List locateModules(ModuleSrdaBO moduleSrdaBO) throws SevereException,AdapterException {
        RoleAdapterManager adapterManager = new RoleAdapterManager();
        return adapterManager.retrieveRoleModuleAssociation(moduleSrdaBO);
    }

    /**
     * Method for locating the associated tasks
     * 
     * @param taskBO
     * @return List
     * @throws SevereException
     */
    public List locateAssociatedTasks(RoleLocateCriteria locateCriteria)
            throws SevereException,AdapterException {
        RoleAdapterManager adapterManager = new RoleAdapterManager();
        return adapterManager.locateRoleAssociations(locateCriteria);
    }



}