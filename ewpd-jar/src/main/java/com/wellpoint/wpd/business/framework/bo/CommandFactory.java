/*
 * CommandFactory.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo;

import java.util.List;

import com.wellpoint.wpd.business.search.command.Command;
import com.wellpoint.wpd.common.search.criteria.AdvancedSearchCriteria;
import com.wellpoint.wpd.common.search.criteria.BasicSearchCriteria;
import com.wellpoint.wpd.common.search.result.NotesIdentifier;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public interface CommandFactory {
	Command[] getCommands(BasicSearchCriteria basicSearchCriteria);
	Command[] getCommands(List objectIdentifiers);
    Command[] getCommands(AdvancedSearchCriteria advancedSearchCriteria);
    Command[] getCommands(NotesIdentifier identifier,List authModules);
}
