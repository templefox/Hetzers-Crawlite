package com.hetzer.crawlite.processers;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.Processor;
import com.hetzer.crawlite.job.CrawlJob;

public abstract class AbstractFetcher implements Processor {
	@Override
	public boolean process(CrawlableURL source, CrawlJob crawlJob) {
		fetch(source);
		if (source.getURL() != null) {
			return true;
		} else {
			return false;
		}
	}

	public abstract void fetch(CrawlableURL url);

}
