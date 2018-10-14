package com.java.utils;

import java.util.Properties;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.java.exception.GeneralException;

public class MyDataSource extends BasicDataSource {

	private static MyDataSource ds = null;
	static Properties pt;
//	private static String url = "jdbc:oracle:thin:@localhost:1521:ORCL12C";
//	private static String username = "sys as sysdba";
//	private static String password = "oracle";
//	private static String driverName = "oracle.jdbc.driver.OracleDriver";

	private MyDataSource() {
		super();
	}

	public static MyDataSource getDataSource() throws GeneralException {
		if (ds == null) {
			synchronized (MyDataSource.class) {
				if (ds == null) {
					pt = PropertyUtil.readPropertyFile();
					if(pt == null)
						throw new GeneralException("No Properties available for data source.");
					ds = new MyDataSource();
					ds.setUrl(pt.getProperty("jdbc.url"));
					ds.setPassword(pt.getProperty("jdbc.password"));
					ds.setUsername(pt.getProperty("jdbc.username"));
					ds.setDriverClassName(pt.getProperty("jdbc.driverName"));
					ds.setMaxIdle(20);
					ds.setMaxConnLifetimeMillis(3000);
					ds.setMaxTotal(100);
					ds.setMaxWaitMillis(3000);
					ds.setDefaultAutoCommit(false);
				}
			}
		}

		return ds;
	}

}
