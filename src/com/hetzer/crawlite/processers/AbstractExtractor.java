package com.hetzer.crawlite.processers;

import java.io.File;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.Processor;
import com.hetzer.crawlite.job.CrawlJob;

public abstract class AbstractExtractor implements Processor {
	@Override
	public boolean process(CrawlableURL source, CrawlJob crawlJob) {
		// crawlJob.getUrlProvider().add(new MockResource("url"));
		return Extract(source, crawlJob);

	}

	public abstract boolean Extract(CrawlableURL url, CrawlJob crawlJob);

}
