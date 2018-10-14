package com.java.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.flywaydb.core.Flyway;

import com.java.exception.GeneralException;

@WebListener
public class MyFlyway implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {

			Flyway flyway = new Flyway();

			flyway.setBaselineOnMigrate(true);
			flyway.setValidateOnMigrate(true);
			flyway.setLocations("classpath:/com.db.migrate");
			flyway.setDataSource(MyDataSource.getDataSource());

			flyway.migrate();

		} catch (GeneralException e) {
			event.getServletContext().setAttribute("migration", "Error occured during migration: "+e.getMessage());
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

}
