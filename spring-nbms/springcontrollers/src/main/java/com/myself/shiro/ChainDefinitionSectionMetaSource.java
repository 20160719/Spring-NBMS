package com.myself.shiro;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.myself.acceptors.system.IMenuAcceptor;
import com.myself.acceptors.system.IPermsAcceptor;
import com.myself.common.utils.StringUtils;
import com.myself.persistences.entity.Tree;
import com.myself.persistences.entity.system.Permission;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;

import javax.annotation.Resource;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Section> {

	private String filterChainDefinitions;
	
	private static final String ROLE_STRING = "authc, roles[\"{0}\"]";

	@Resource(name = "permsAcceptor")
	private IPermsAcceptor permsAcceptor;

	@Resource(name = "menuAcceptor")
	private IMenuAcceptor menuAcceptor;
	
	@Override
	public Section getObject() throws Exception {
		Ini ini = new Ini();
		ini.load(filterChainDefinitions);
		Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);

		List<Permission> permsList = getPermsAcceptor().queryPermissions();
		Map<String, List<Permission>> permMap = permsList.stream().collect(Collectors.groupingBy(Permission::getMenuId));
		Set<Map.Entry<String, List<Permission>>> setEntry = permMap.entrySet();
		List<String> roleIdList = null;
		String roleIds = null;

		List<Tree> menuList = getMenuAcceptor().queryTrees();
		Map<String, List<Tree>> menuMap = menuList.stream().collect(Collectors.groupingBy(Tree::getId));

		for(Map.Entry<String, List<Permission>> entry : setEntry) {
			System.out.println(entry.getKey() + "->" + Arrays.toString(entry.getValue().toArray()));
			roleIdList = entry.getValue().stream().map(t -> t.getRoleId()).collect(Collectors.toList());
			roleIds = StringUtils.mergeToStr(roleIdList);
			//section.put(menuMap.get(entry.getKey()).get(0).getValue(), MessageFormat.format(ROLE_STRING, roleIds));
			//System.out.println(menuMap.get(entry.getKey()).get(0).getValue()+ " -> "+ MessageFormat.format(ROLE_STRING, roleIds));
		}
		return section;
	}

	@Override
	public Class<?> getObjectType() {
		return getClass();
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	public IPermsAcceptor getPermsAcceptor() {
		return permsAcceptor;
	}

	public IMenuAcceptor getMenuAcceptor() {
		return menuAcceptor;
	}

}
