/*
 * JndiDataSourceLoader.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.spellcheck.dictionary.db;

import javax.naming.NamingException;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Class that starts a JNDI service and instantiates and binds wsb and
 * wsbContract data sources.
 * 
 * @author Wellpoint, Inc. - www.wellpoint.com <br />
 * @version $Id: $
 */
public class JndiDataSourceLoader {

	/**
	 * Default constructor
	 */
	static JndiDataSourceLoader jndiDataSourceLoader = null;
	public static void bindAndUseDataSources()throws NamingException
    {
                if(jndiDataSourceLoader == null)
                         jndiDataSourceLoader = new JndiDataSourceLoader();
    }
    public JndiDataSourceLoader() throws NamingException
    {
                //bindDataSources();
    }
    

	/**
	 * Static methods that the Contract DataFeed Controller method should call
	 * to load Datasources into the jndi repository. This permits the Adapter to
	 * use a jndi lookup to find the contract business objects.
	 * 
	 * @throws NamingException
	 */
	public javax.sql.DataSource getDataSources() throws NamingException {
		//Create an initial context and a "jdbc" subcontext

		ClassPathXmlApplicationContext clpApp = new ClassPathXmlApplicationContext("com/wellpoint/wpd/common/configfiles/spellcheckjndi.xml");
		javax.sql.DataSource wsbDataSource = (javax.sql.DataSource) clpApp.getBean( "wsbDataSource" );
		return wsbDataSource;
	}

}