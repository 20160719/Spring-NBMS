package com.myself.shiro;

import java.util.Set;

import org.apache.shiro.authz.SimpleAuthorizationInfo;

public class MySimpleAuthorizationInfo extends SimpleAuthorizationInfo {
	
	private static final long serialVersionUID = 3588382370085883481L;
	
	private Set<String> urls;

	protected Set<String> getUrls() {
		return urls;
	}

	protected void setUrls(Set<String> urls) {
		this.urls = urls;
	}
	

}
