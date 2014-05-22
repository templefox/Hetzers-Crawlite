package com.hetzer.crawlite;

import java.io.FileInputStream;
import java.util.Properties;

public class Crawlite {
	private CrawlJobManager jobManager = null;
	public static final String version = "crawlite.version";
	private Properties properties;
	
	public Crawlite(CrawlJobManager jobManager) {
		this.jobManager = jobManager;
	}

	public static void main(String[] args) {
		Crawlite crawlite = new Crawlite(CrawlJobManager.instance());
		crawlite.loadProperties();
		// crawlite.launch();

		// XXX for test

	}
	
	public Properties getProperties() {
		return properties;
	}

	private void loadProperties() {
		try {
			properties = new Properties();
			FileInputStream fis = new FileInputStream("crawlite.properties");
			properties.load(fis);
		} catch (Exception e) {
			// TODO: handle exception
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
		jobManager.initialize();
	}
}
