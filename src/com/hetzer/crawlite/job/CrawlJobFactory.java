package com.hetzer.crawlite.job;

import com.hetzer.crawlite.CrawlJobManager;

public class CrawlJobFactory {
	public CrawlJob makeJob(CrawlJobManager cjm){
		return new CrawlJob(cjm);
	}
}
