package com.hetzer.crawlite.job;


import com.hetzer.crawlite.URLPool.MockURLPool;
import com.hetzer.crawlite.framework.URLPool;

/**
 * manage jobs.
 */
public class CrawlJobManager {
	private URLPool urlPool;
	
	public CrawlJobManager() {
		this(new MockURLPool());
		
	}
	
	public CrawlJobManager(URLPool urlPool){
		this.urlPool = urlPool;
	}
	
	private void initialize(){
		//TODO 1�����url 2�����߳� 3��������
	}
	
	public void startCrawler(){
		
	}
}
