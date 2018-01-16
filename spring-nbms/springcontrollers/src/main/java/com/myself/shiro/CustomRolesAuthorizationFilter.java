package com.myself.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomRolesAuthorizationFilter extends AuthorizationFilter {

	private static final Logger logger = LoggerFactory.getLogger(CustomRolesAuthorizationFilter.class);
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		
		Subject subject = getSubject(request, response); 
        String[] rolesArray = (String[]) mappedValue; 
        logger.info("isAccessAllowed");
        if (rolesArray == null || rolesArray.length == 0) { 
            //no roles specified, so nothing to check - allow access. 
            return true; 
        } 

        for (int i = 0; i < rolesArray.length; i++) {  
            if (subject.hasRole(rolesArray[i])) { //若当前用户是rolesArray中的任何一个，则有权限访问  
                return true;  
            }  
        }  
        return false;  
	}

}
