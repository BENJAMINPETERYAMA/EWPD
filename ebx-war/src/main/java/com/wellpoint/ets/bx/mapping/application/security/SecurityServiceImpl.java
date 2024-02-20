/*
 * Created on Jun 1, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.application.security;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.wellpoint.ets.bx.mapping.repository.SecurityRepository;


/**
 * @author u20622
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SecurityServiceImpl implements SecurityService{

		protected static String USER_CONST = "user";
		private static Logger log = Logger.getLogger(SecurityServiceImpl.class);
		private SecurityRepository securityRepository;
		
		/**
		 * @return Returns the securityRepository.
		*/
		public SecurityRepository getSecurityRepository() {
			return securityRepository;
		}
		/**
		 * @param securityRepository
		 *            The securityRepository to set.
		 */
		public void setSecurityRepository(SecurityRepository securityRepository) {
			this.securityRepository = securityRepository;
		}
		
		public LoginUser getUser(String userId, String roleName) throws SecurityException{
			
			if (roleName != null) {
				roleName = roleName.toUpperCase();
			}
			if ((userId == null || userId.trim().length() == 0 || roleName == null || roleName
					.trim().length() == 0)
					&& (Environment.isUserAcceptanceTest() || Environment
							.isProduction())) {
				log.debug(ESAPI.encoder().encodeForHTML("User Id and or Role name not received from SiteMinder "+userId+" "+roleName));
				List params = new ArrayList();
				params.add(userId);
            	params.add(roleName);
            	throw new SecurityException("User Id and or Role name not received from SiteMinder "+userId+" "+roleName);
			}
			if (userId == null) {
				userId = SecurityConstants.USER;
				userId =userId.toUpperCase();
			}
			if (roleName == null) {
				roleName = SecurityConstants.DEFAULT_ROLE;
			}
			List roleDetails = getRoleDetails(userId, parseRoleNameString(roleName));
			LoginUserImpl userImpl = new LoginUserImpl();
			userImpl.setUserId(userId.toUpperCase());
			userImpl.setRoles(roleDetails);
			if ((roleDetails == null || roleDetails.isEmpty())
					&& (Environment.isUserAcceptanceTest() || Environment
							.isProduction())) {
				log
						.debug("No Role specified in eWPD for the name received from SiteMinder");
			}
			return userImpl;
	}
	
	 /**
	  * @param userId2
	  * @param list
	  * @return
	  */
	private List getRoleDetails(String userId, List roleNames) {
		
        List roleList = new ArrayList();
        if (roleNames != null) {
            for (int i = 0; i < roleNames.size(); i++) {
                RoleImpl roleImpl = new RoleImpl();
                roleImpl.setName(String.valueOf(roleNames.get(i)));
                roleImpl = retrieveModule(roleImpl);
                if (roleImpl.getModules() != null
                        && roleImpl.getModules().size() > 0) {
                    roleList.add(roleImpl);
                }
            }
        }
       
		// TODO Auto-generated method stub
		return roleList;
	}
		
		
		
	/*	protected HttpServletRequest getRequest() {
	        HttpServletRequest request = (HttpServletRequest) FacesContext
	                .getCurrentInstance().getExternalContext().getRequest();
	        return request;
	    }
		*/
		protected List parseRoleNameString(String roleName){
	    	List roleEmptyList = null;
	    	if(roleName != null){
	    		List roles = new ArrayList();
	    		StringTokenizer st = new StringTokenizer(roleName,SecurityConstants.SITEMINDER_ROLE_SEPARATOR);
	    		while(st.hasMoreTokens()){
	    			roles.add(st.nextToken());
	    		}
	    		return roles;
	    	}
	    	return roleEmptyList;
	    }
		
		 public RoleImpl retrieveModule(RoleImpl roleImpl)  {

	        RoleBO roleBO = new RoleBO();
	        roleBO.setRoleName(roleImpl.getName());
	        // Create an instance of the Adapter Manager    
	        roleBO = securityRepository.getRole(roleBO);
	        if(roleBO == null)return roleImpl;
	        roleImpl.setId(roleBO.getRoleId());
	        List moduleList, moduleIdList = null;
	        //Call the adapter getModuleBO()
	        moduleList = securityRepository.getModuleBO(roleImpl.getId());
	        moduleIdList=new ArrayList(moduleList==null?0:moduleList.size());
	        for (int i = 0; i < moduleList.size(); i++) {
	            ModuleBO moduleBO = (ModuleBO) moduleList.get(i);
	            moduleIdList.add(Integer.valueOf(moduleBO.getModuleId()));
	            ModuleImpl moduleImpl = new ModuleImpl();
	            moduleImpl.setId(moduleBO.getModuleId());
	            moduleImpl.setName(moduleBO.getModuleName());
	            getTasks(moduleImpl.getId(),roleImpl.getId(), moduleImpl);

	            roleImpl.addModule(moduleImpl);

	        }        
	        
	        return roleImpl;
	    
		 }
		/**
		 * @param id
		 * @param id2
		 * @param moduleImpl
		 */
		private void getTasks(long moduleId, long roleId, ModuleImpl moduleImpl) {
			List taskList = null;
			taskList = securityRepository.getTaskBO(moduleId, roleId);
			
            for (int i = 0; i < taskList.size(); i++) {
	            TaskBO taskBO = (TaskBO) taskList.get(i);
	            TaskImpl taskImpl = new TaskImpl();
	            taskImpl.setId(taskBO.getTaskId());
	            taskImpl.setName(taskBO.getTaskName());
	            moduleImpl.addTask(taskImpl);
	        }

			
		}
		
		public boolean isDataFeedRunning(){			
			boolean dataFeedRunningFlag = securityRepository.isDataFeedRunning();
			return dataFeedRunningFlag;
		}
}
