package com.myself.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.myself.acceptors.system.IMenuAcceptor;
import com.myself.acceptors.system.IPermsAcceptor;
import com.myself.exception.CustomException;
import com.myself.persistences.entity.Tree;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.myself.acceptors.system.IUserAcceptor;
import com.myself.common.utils.StringUtils;
import com.myself.common.utils.SystemUtils;
import com.myself.persistences.entity.system.Permission;
import com.myself.persistences.entity.system.User;

import org.springframework.util.ObjectUtils;

@Service(value = "myRealm")
public class MyShiroRealm extends AuthorizingRealm {
	
	private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
	
	@Resource(name = "userAcceptor")
	private IUserAcceptor userAcceptor;

	@Resource(name = "permsAcceptor")
	private IPermsAcceptor permsAcceptor;

	@Resource(name = "menuAcceptor")
	private IMenuAcceptor menuAcceptor;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		try {
			User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
			List<String> roleIds = StringUtils.splitToList(user.getRoleIds());
			List<Permission> permsList = getPermsAcceptor().queryPermsByRoleIds(roleIds);

			List<String> menuIdList = SystemUtils.getMenuIds(permsList);

			List<Tree> menuList = getMenuAcceptor().queryTrees();
			Set<String> permsSet = menuList.stream().filter(m -> !"#".equals(m.getValue())).filter(m -> menuIdList.contains(m.getId())).map(m -> m.getValue()).collect(Collectors.toSet());

			Set<String> rolesSet = new HashSet<String>(roleIds);
			info.addRoles(rolesSet);
			info.addStringPermissions(permsSet);
		} catch (CustomException e) {
			logger.error(e.getMessage(), e);
		}
		return info;
	}

	// 鑾峰彇璁よ瘉淇℃伅
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
		AuthenticationInfo info = null;
		try {
			UsernamePasswordToken token = (UsernamePasswordToken) authToken;
			String account = token.getUsername();
			if (!StringUtils.isNotBlank(account)) {
				logger.error("error:account must not be empty...");
			}
			User user = SystemUtils.getUser();
			user.setAccount(account);
			user = getUserAcceptor().load(user);
			if (ObjectUtils.isEmpty(user)) {
				logger.error("error:the user not exist...");
			}
			SecurityUtils.getSubject().getSession().setAttribute("user", user);
			info = new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(), getName());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return info;
	}

	protected IUserAcceptor getUserAcceptor() {
		return userAcceptor;
	}

	protected IPermsAcceptor getPermsAcceptor() {
		return permsAcceptor;
	}

	public IMenuAcceptor getMenuAcceptor() {
		return menuAcceptor;
	}
}
