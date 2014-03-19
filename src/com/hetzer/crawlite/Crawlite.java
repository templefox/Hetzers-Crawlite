package com.hetzer.crawlite;

import com.hetzer.crawlite.job.CrawlJobManager;

public class Crawlite {
	private CrawlJobManager jobManager= null;
	
	
	public Crawlite(CrawlJobManager jobManager) {
		super();
		this.jobManager = jobManager;
	}

	public static void main(String[] args) {
		Crawlite crawlite = new Crawlite(new CrawlJobManager());
		crawlite.launch();
		
		/*
		 * Maybe start by some clicks on web.
		 */
		crawlite.getJobManager().startCrawler();
	}

	public CrawlJobManager getJobManager() {
		return jobManager;
	}

	/**
	 * launch the web.
	 */
	public void launch(){
		//launch the web.
	}
}
