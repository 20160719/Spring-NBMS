package com.myself.controllers.system;

import com.myself.common.utils.CommonUtils;
import com.myself.common.utils.StringUtils;
import com.myself.common.utils.SystemUtils;
import com.myself.controllers.AbstractSystemController;
import com.myself.exception.CustomException;
import com.myself.persistences.entity.Tree;
import com.myself.persistences.entity.system.Account;
import com.myself.persistences.entity.system.Permission;
import com.myself.persistences.entity.system.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/system/loginOut")
public class LoginOutController extends AbstractSystemController<User> {
	
	@Resource(name = "securityManager")
	private SecurityManager securityManager;
	
	@RequestMapping(value = "toLogin" + SUFFIX, method = RequestMethod.GET)
	public String toLogin() {
		return getDirectory() + "login";
	}
	
	@RequestMapping(value = "login" + SUFFIX, method = RequestMethod.POST)
	public String login(@ModelAttribute("account") Account account) {
		SecurityUtils.setSecurityManager(securityManager);
		UsernamePasswordToken token = new UsernamePasswordToken(account.getAccount(), account.getPassword());
		setAccount(account.getAccount());
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			subject.getSession().setAttribute("account", account.getAccount());
			return "redirect:/system/loginOut/content.action";
		} catch (Exception e) {
			return "redirect:/system/loginOut/toLogin.action";
		}
	}
	
	@RequestMapping(value = "content" + SUFFIX, method = RequestMethod.GET)
	public String content() {
		return "content";
	}
	
	@RequestMapping(value = "navigation" + SUFFIX, produces = "text/html;charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String navigation() {
		String account = SecurityUtils.getSubject().getSession().getAttribute("account").toString();
		User user = SystemUtils.getUser();
		user.setAccount(account);
		try {
			user = getUserAcceptor().load(user);
			//鑾峰彇roleIds闆嗗悎
			List<String> roleIds = StringUtils.splitToList(user.getRoleIds());
			//鏍规嵁roleIds闆嗗悎鑾峰彇permsSet
			List<Permission> permsSet = getPermsAcceptor().queryPermsByRoleIds(roleIds);
			//鑾峰彇menuIdList
			List<String> menuIdList = SystemUtils.getMenuIds(permsSet);
			//鑾峰彇鑿滃崟闆嗗悎menuList
			//杩囨护menuList menuId瀛樺湪menuIdList涓殑璁板綍
			List<Tree> menuList =  getMenuAcceptor().queryTrees();
			menuList = CommonUtils.filterTreeListById(menuList, menuIdList);
			
			List<String>filterList = com.myself.common.utils.CollectionUtils.getList();
			filterList.add("0");
			//获取显示权限的菜单
			menuList = CommonUtils.filterTreeListByType(menuList, filterList);
			filterList.clear();
			filterList.add("1");
			//获取有效的菜单
			menuList = CommonUtils.filterTreeListByValid(menuList, filterList);
			//获取可见的菜单
			menuList = CommonUtils.filterTreeListByVisible(menuList, filterList);
			//涓篢ree璁剧疆children灞炴��
			menuList = CommonUtils.setChildrenListForTree(menuList);

			List<String> parentIdList = com.myself.common.utils.CollectionUtils.getList();
			parentIdList.add("M1000000000");

			menuList = CommonUtils.filterTreeListByParentId(menuList, parentIdList).get(0).getChildren();

			StringBuffer buffer = new StringBuffer();
			buffer.append("<div id=\"menu\" style=\"z-index:20;position: absolute;\">");
			getMenuStr(menuList, buffer, 0, 0);
			buffer.append("</div>");
			return buffer.toString();
		} catch (CustomException e) {
		}
		return "navigation";
	}

	private void getMenuStr(List<Tree> list, StringBuffer buffer, int i, int j) {
		List<Tree> childrenList = null;
		if (!CollectionUtils.isEmpty(list)) {
			if (0 == i) {
				buffer.append("<ul id=\"nav\">");
			} else {
				buffer.append("<ul>");
			}
			for(Tree t : list) {
				childrenList = t.getChildren();
				if(0 == j) {
					buffer.append("<li class=\"current\">");
				} else {
					buffer.append("<li>");
				}
				buffer.append("<a href=\"javascript:void(0)\" onclick=\"createTab('"+t.getId()+"','"+t.getName()+"','"+t.getValue()+"','"+t.getOpCode()+"')\">").append(t.getName()).append("</a>");
				getMenuStr(childrenList, buffer, 1, 1);
				buffer.append("</li>");
			}
			buffer.append("</ul>");
		}
	}

	@RequestMapping(value = "logout" + SUFFIX, method = RequestMethod.GET)
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/system/loginOut/toLogin.action";
	}
	
	@Override
	protected String getDirectory() {
		return "system/loginOut/";
	}

}
