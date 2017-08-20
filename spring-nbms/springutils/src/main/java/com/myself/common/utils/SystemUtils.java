package com.myself.common.utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

import com.myself.persistences.entity.system.Permission;
import com.myself.persistences.entity.system.Role;
import com.myself.persistences.entity.system.User;

public class SystemUtils {
	
	private SystemUtils() {
		
	}
	
	public static Set<String> getRoleSet(List<Role> roleSet) {
		Assert.notEmpty(roleSet, "Role set must not be empty...");
		return roleSet.stream().map(r -> r.getId()).collect(Collectors.toSet());
	}
	
	public static Set<String> getPermSet(List<Permission> permsSet) {
		Assert.notEmpty(permsSet, "Perm set must not be empty...");
		return permsSet.stream().map(p -> p.getPermId()).collect(Collectors.toSet());
	}

	public static List<String> getMenuIds(List<Permission> permsSet) {
		Assert.notEmpty(permsSet, "Perm set must not be empty...");
		return permsSet.stream().map(p -> p.getMenuId()).collect(Collectors.toList());
	}

	public static User getUser() {
		return new User();
	}

}
