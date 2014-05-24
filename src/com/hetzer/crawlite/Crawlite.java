package com.hetzer.crawlite;

import java.io.FileInputStream;
import java.util.Properties;

public class Crawlite {
	private CrawlJobManager jobManager = null;
	public static final String VERSION = "crawlite.version";
	public static final String PROPERTIES_FILE = "crawlite.properties";
	private Properties properties;
	
	public Crawlite(CrawlJobManager jobManager) {
		this.jobManager = jobManager;
	}

	public static void main(String[] args) {
		Crawlite crawlite = new Crawlite(CrawlJobManager.instance());
		crawlite.loadProperties();
		crawlite.launch();

		// XXX for test

	}
	
	public Properties getProperties() {
		return properties;
	}

	private void loadProperties() {
		try {
			properties = new Properties();
			FileInputStream fis = new FileInputStream(PROPERTIES_FILE);
			properties.load(fis);
		} catch (Exception e) {
			throw new RuntimeException("No properties file");
		}
	}

	public CrawlJobManager getJobManager() {
		return jobManager;
	}

	/**
	 * launch the web. Initialize manager.
	 */
	public void launch() {
		System.out.println("crewlite启动（任务管理器启动，网页启动）");
		jobManager.initialize(properties);
	}
}
