package com.myself.provider.impl;

import com.myself.api.DemoService;

public class DemoServiceImpl implements DemoService {

	public void sayHello(String name) {
		System.out.println("hello, " + name);
	}

}
