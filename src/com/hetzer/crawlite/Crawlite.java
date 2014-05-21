package com.hetzer.crawlite;

public class Crawlite {
	private CrawlJobManager jobManager = null;

	public Crawlite(CrawlJobManager jobManager) {
		this.jobManager = jobManager;
	}

	

	public static void main(String[] args) {
		Crawlite crawlite = new Crawlite(CrawlJobManager.instance());
		crawlite.launch();

		// XXX for test
	}

	public CrawlJobManager getJobManager() {
		return jobManager;
	}

	/**
	 * launch the web. Initialize manager.
	 */
	public void launch() {
		System.out.println("crewlite�����������������������ҳ������");
		jobManager.initialize();
	}
}
