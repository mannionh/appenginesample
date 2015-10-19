package com.howard.test.helpers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.Greeting;
import model.GuestBookStatistics;

import com.googlecode.objectify.ObjectifyService;

public class ObjectifyHelper implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ObjectifyService.register(Greeting.class);
		ObjectifyService.register(GuestBookStatistics.class);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
