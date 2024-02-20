/*
 * StateManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.business.framework.bo.MetaObjectFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.MetaObject;
import com.wellpoint.wpd.common.bo.Transition;
import com.wellpoint.wpd.common.framework.exception.MetadataParserException;
import com.wellpoint.wpd.common.framework.exception.SecurityException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.bo.InvalidStateTransitionException;
import com.wellpoint.wpd.common.framework.exception.bo.StatusChangeValidationException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.framework.util.ReflectionUtil;

/**
 * The StateManager checks whether the user has the privilege to do the next
 * transition on the BusinessObject.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: StateManager.java 41536 2008-01-10 04:26:51Z U15701 $
 */
public class StateManager {

    public void validate(Object object, User user, String newStatus)
            throws StatusChangeValidationException,SevereException,SecurityException {

        //Validate the parameters
        if (object == null || newStatus == null || user == null)
            throw new IllegalArgumentException("One of the parameters is null");

        //Retrieve the valid transitions from the meta object
        MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
                .getFactory(ObjectFactory.METAOBJECT);
        MetaObject metaObject = null;
        try {
            metaObject = metaObjectFactory.getMetaObject(object);
        } catch (MetadataParserException e) {
            throw new StatusChangeValidationException(
                    "Error in retrieving the MetaObject for the business object",
                    null, e);
        }
        Map transitions = metaObject.getTransitions();

        //Get the status attribute value
        String status = null;
        try {
            status = (String) ReflectionUtil
                    .getValueOfBusinessAttributeByReflection(object,
                            BusinessObject.BUSINESS_OBJECT_STATUS_FIELD_NAME);
            if (status == null)
                throw new StatusChangeValidationException(
                        "Status is not populated in the business object", null,
                        null);
        } catch (SevereException e) {
            throw new StatusChangeValidationException(
                    "Error in retrieving the value of the status attribute",
                    null, e);
        }

        //Check if the current status to new status is present in the list of
        // transitions
        String transitionKey = "TRANSITION_" + status + "_TO_" + newStatus;
        Transition transition = (Transition) transitions.get(transitionKey);
        // if there is a valid transition, check for the user authorization.
        if (transition != null) {
            boolean authorized=false;
            if(transition.getOverride()!=null&&!transition.getOverride().isEmpty()){
             List taskList=transition.getOverride();
               for(int i=0;i<taskList.size();i++){
                   String taskName=(String)taskList.get(i);
                   if(user.isAuthorized(metaObject.getModule(), taskName)){
                       authorized=true;
                   }
               }
                
            }else{
              authorized=user.isAuthorized(metaObject.getModule(), transition
                    .getTaskName());
            }
            if(!authorized){
                List params = new ArrayList();
                params.add(object);
                params.add(transition
                        .getTaskName());
                params.add(status);     
                SecurityException se = new SecurityException(
                        "User is not authorized for this task",
                        params, null);
                throw se;
            }
        }else{
            List params = new ArrayList();
            params.add(object);
            SevereException se = new InvalidStateTransitionException(
                    "Object can not be published. Invalid state transition",
                    params, null);
            Logger.logException(se);
            throw se;
        }
    }
}