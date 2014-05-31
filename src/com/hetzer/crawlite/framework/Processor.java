package com.hetzer.crawlite.framework;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.job.CrawlJob;

public interface Processor {
	public boolean process(CrawlableURL url, CrawlJob crawlJob);
}
