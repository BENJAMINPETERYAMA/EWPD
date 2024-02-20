/*
 * ObjectFactory.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ObjectFactory.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public abstract class ObjectFactory {

    public static final String SECURITY = "security";

    public static final String LOG = "log";

    public static final String BOM = "bomanager";

    public static final String STATE = "state";

    public static final String METAOBJECT = "metaobject";

    public static final String AUDIT = "audit";

    public static final String LOCK = "lock";

    public static final String REQUEST = "request";
    
    public static final String LOCATECHKOUTFACTORY = "locateChkOutFactory";
    
    public static final String COMMAND_FACTORY = "commandFactory";

    public static ApplicationContext applicationContext;
    
   

    static {
        applicationContext = new ClassPathXmlApplicationContext(
                "com/wellpoint/wpd/common/configfiles/object-context.xml");
    }

    /**
     *  
     */
    protected ObjectFactory() {
        super();
    }

    public static ObjectFactory getFactory(String type) {
        ObjectFactory of = (ObjectFactory) applicationContext.getBean(type);
        if (of == null) {
            throw new RuntimeException(
                    "Incorrect ObjectFactory type specified.  No type called "
                            + type);
        }
        return of;
    }

}