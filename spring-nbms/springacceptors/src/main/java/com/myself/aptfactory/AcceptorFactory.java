package com.myself.aptfactory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.myself.acceptors.system.IBookTypeAcceptor;
import com.myself.acceptors.system.IMenuAcceptor;
import com.myself.acceptors.system.IOrgAcceptor;
import com.myself.acceptors.system.IPermsAcceptor;
import com.myself.acceptors.system.IRoleAcceptor;
import com.myself.acceptors.system.IUserAcceptor;

public final class AcceptorFactory implements ApplicationContextAware {

	private static ApplicationContext applicationContext;  
	  
	private static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
	
	public static final IRoleAcceptor getRoleAcceptor() {
		return (IRoleAcceptor)getApplicationContext().getBean("roleAcceptor");
	}

	public static final IMenuAcceptor getMenuAcceptor() {
		System.out.println("acceptor getApplicationContext(): " + getApplicationContext());
		System.out.println("acceptor getApplicationContext(): " + getApplicationContext().getBean("menuAcceptor"));
		return (IMenuAcceptor)getApplicationContext().getBean("menuAcceptor");
	}

	public static final IOrgAcceptor getOrgAcceptor() {
		return (IOrgAcceptor)getApplicationContext().getBean("orgAcceptor");
	}

	public static final IPermsAcceptor getPermsAcceptor() {
		return (IPermsAcceptor)getApplicationContext().getBean("permsAcceptor");
	}

	public static final IBookTypeAcceptor getBookTypeAcceptor() {
		return (IBookTypeAcceptor)getApplicationContext().getBean("bookTypeAcceptor");
	}

	public static final IUserAcceptor getUserAcceptor() {
		return (IUserAcceptor)getApplicationContext().getBean("userAcceptor");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		AcceptorFactory.applicationContext = applicationContext;  
	}
	
}
