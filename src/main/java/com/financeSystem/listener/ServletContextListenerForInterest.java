package com.financeSystem.listener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ServletContextListenerForInterest
 *
 */
@WebListener
public class ServletContextListenerForInterest implements ServletContextListener {
	private ScheduledExecutorService scheduler;
    /**
     * Default constructor. 
     */
    public ServletContextListenerForInterest() {
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	scheduler = Executors.newScheduledThreadPool(1);

        // Schedule your task with a fixed delay
        scheduler.scheduleAtFixedRate(new InterestCalculateScheduled(), 30, 30, TimeUnit.DAYS);
    }
	
}
