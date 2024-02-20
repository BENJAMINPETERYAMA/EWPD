/*
 * SecurityService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.service;

import java.util.ArrayList;
import java.util.List;
import com.wellpoint.wpd.business.security.builder.RoleBusinessObjectBuilder;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.request.RetrieveUserRequest;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.RetrieveUserResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.security.domain.RoleImpl;
import com.wellpoint.wpd.common.framework.security.domain.UserImpl;

/**
 * The service for retrieving security.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SecurityService extends WPDService {

    public WPDResponse execute(WPDRequest request) throws ServiceException {
        return null;
    }

    /**
     * Sets security to response
     * 
     * @param RetrieveUserRequest
     *            request
     * @throws ServiceException
     */
    public WPDResponse execute(RetrieveUserRequest retrieveUserRequest)
            throws ServiceException {
        if (retrieveUserRequest == null) {
            throw new ServiceException(
                    "Error occurred in Retrieve User Service", new ArrayList(),
                    null);
        }
        UserImpl user = new UserImpl();
        user.setUserId(retrieveUserRequest.getUserId());
        try {
            List roleList = new ArrayList();
            if (retrieveUserRequest.getRoleNames() != null) {
                RoleBusinessObjectBuilder businessObjectBuilder = new RoleBusinessObjectBuilder();
                for (int i = 0; i < retrieveUserRequest.getRoleNames().size(); i++) {
                    RoleImpl roleImpl = new RoleImpl();
                    roleImpl.setName(String.valueOf(retrieveUserRequest
                            .getRoleNames().get(i)));
                    roleImpl = businessObjectBuilder.retrieveModule(roleImpl);
                    if (roleImpl.getModules() != null
                            && roleImpl.getModules().size() > 0) {
                        roleList.add(roleImpl);
                    }
                }
            }
            if (roleList != null) {
                user.setRoles(roleList);
            } else {
                user.setRoles(null);
            }
            RetrieveUserResponse retrieveUserResponse = new RetrieveUserResponse();
            retrieveUserResponse.setUser(user);
            return retrieveUserResponse;

        } catch (Exception e) {
            throw new ServiceException(
                    "Error occurred in Retrieve User Service", new ArrayList(),
                    null);
        }
    }

}