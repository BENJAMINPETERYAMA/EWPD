/*
 * BusinessObjectBuilder.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo.manager;

import java.util.Map;

import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.security.domain.User;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: BusinessObjectBuilder.java 18367 2007-04-11 05:46:45Z u12046 $
 */
public interface BusinessObjectBuilder {

    /**
     * Retrieve the attributes of the complex Business Object with multiple
     * calls to the adapter and form the whole business object.
     */

    BusinessObject retrieve(BusinessObject businessObject) throws SevereException;
    
    /**
     * Retrieve the attributes of the complex Business Object with multiple
     * calls to the adapter and form the whole business object. The params map can be 
     * used to control the retrieval process, to partially retrieve the business object or
     * other special kind of retrievals.
     */
    BusinessObject retrieve(BusinessObject object, Map params) throws SevereException;

    /**
     * Retrieve the attributes of the latest version of complex Business Object
     * with multiple calls to the adapter and form the whole business object.
     * The latest version that is not in the MARKED_FOR_DELETION status to be returned
     */
    BusinessObject retrieveLatestVersion(BusinessObject businessObject) throws SevereException;
    
	/**
     * Retrieve the latest version number of Business Object even if the latest
     * version has MARKED_FOR_DELETION status
     */
	public int retrieveLatestVersionNumber(BusinessObject businessObject) throws SevereException;


    /**
     * Persist the complex Business Object with multiple calls to the adapter.
     * insertFlag is used to specify if the persist is an insert or update
     * operation.
     */
    boolean persist(BusinessObject BusinessObject, Audit audit, boolean insertFlag) throws SevereException;

    /**
     * Persist the sub Object under a main Business Object with multiple calls
     * to the adapter. insertFlag is used to specify if the persist is an insert
     * or update operation. Need to write different implementations based on the
     * different sub object types.
     */
    boolean persist(Object subObject, BusinessObject mainObject, Audit audit,
            boolean insertFlag) throws SevereException;

    /**
     * Delete the specified version of a complex Business Object with multiple
     * calls to the adapter to remove all the related data.
     */
    boolean delete(BusinessObject BusinessObject, Audit audit) throws SevereException;

    /**
     * Delete the subObject from the mainObject using calls to adapter. Need to
     * write different implementations based on the different sub object types.
     */
    boolean delete(Object subObject, BusinessObject mainObject, Audit audit) throws SevereException;

    /**
     * Delete the latest version of a complex Business Object with multiple
     * calls to the adapter to remove all the related data.
     */
    boolean deleteLatestVersion(BusinessObject BusinessObject, Audit audit) throws SevereException;

    /**
     * Copy a complex Business Object defined by srcBO to a Business Object with
     * keys as defined in trgtBO. The trgtBO should be expected to be present
     * already and should not create the trgtBO should not be created inside
     * this method. The Business Service can use parameters attribute to pass
     * processing parameters/data to the Builder.
     */
    boolean copy(BusinessObject srcBO, BusinessObject trgtBO, Audit audit) throws SevereException;
    /**
     * This copy method is called by the BOM while an object is being checked out.
     * A different copy method is preffered becuase there may be some business logic associated 
     * with the copy.
     */
    boolean copyForCheckOut(BusinessObject srcBO, BusinessObject trgtBO, Audit audit) throws SevereException;

    /**
     * Copy a complex Business Object defined by srcBO to a Business Object with
     * keys as defined in trgtBO. Persist the trgtBO to the Database. The
     * Business Service can use parameters attribute to pass processing
     * parameters/data to the Builder. The Framework does not use this object.
     */
    boolean copy(BusinessObject srcBO, BusinessObject trgtBO, Audit audit, Map parameters) throws SevereException;
    
    /**
     * To search for Business Objects for the given locate criteria. The builder
     * should return list of BusinessObjects or LocateResult or POJOs.
     * Overloaded methods have to be defined to locate different kinds of
     * results for each of the locate criteria defined in the metaobject
     * configuration. If the state objects corresponding to each of the entry in
     * the locate result is important, then the locateResults List in
     * LocateResults should be BusinessObjects or LocateResult(All the key
     * attributes as defined in the metaObject configuration have to be defined
     * for the corresponding LocateResult object).
     */
    LocateResults locate(LocateCriteria locateCriteria, User user) throws SevereException ;
    
    /**
     * To change the key value(s) (identity) of a Business Object. The old key
     * value(s) of the Business Object to be changed to the new key value(s).
     */
    boolean changeIdentity(BusinessObject oldBOKey, BusinessObject newBOKey, Audit audit) throws SevereException;
}