package com.hetzer.crawlite.processers;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.Processor;
import com.hetzer.crawlite.job.CrawlJob;

public abstract class AbstractWrite implements Processor {
	@Override
	public boolean process(CrawlableURL source, CrawlJob crawlJob) {
		doit(source);
		if (source.getURL() != null) {
			return true;
		} else {
			return false;
		}
	}

	public abstract void doit(CrawlableURL url);

}
