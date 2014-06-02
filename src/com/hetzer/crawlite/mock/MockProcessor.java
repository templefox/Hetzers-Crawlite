package com.hetzer.crawlite.mock;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.Processor;
import com.hetzer.crawlite.job.CrawlJob;

public class MockProcessor implements Processor {

	@Override
	public boolean process(CrawlableURL url,CrawlJob crawlJob) {
		System.out.println("Processor:"+ url.getURL()+" D:"+url.getDepth());
		return true;
	}
}
