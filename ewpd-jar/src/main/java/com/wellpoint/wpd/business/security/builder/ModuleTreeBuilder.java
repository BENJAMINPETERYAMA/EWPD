/*
 * ModuleTreeBuilder.java
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
import com.wellpoint.wpd.business.security.adapter.ModuleAdapterManager;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.security.bo.TaskBO;
import com.wellpoint.wpd.common.security.bo.TaskSrdaBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ModuleTreeBuilder {

    public List locateAssociatedTasks(TaskBO taskBO) throws SevereException ,AdapterException{

        // Create an instance of the adapter manager
        ModuleAdapterManager adapterManager = new ModuleAdapterManager();

        // Call the locateAssociatedTasks() to get the associated tasks 
        return adapterManager.locateModuleAssociaion(taskBO);
    }
    
    public List locateAssociatedTasks(TaskSrdaBO taskBO) throws SevereException ,AdapterException{

        // Create an instance of the adapter manager
        ModuleAdapterManager adapterManager = new ModuleAdapterManager();

        // Call the locateAssociatedTasks() to get the associated tasks 
        return adapterManager.locateModuleAssociaion(taskBO);
    }

}