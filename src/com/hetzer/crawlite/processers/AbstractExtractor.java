package com.hetzer.crawlite.processers;

import java.io.File;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.Processor;
import com.hetzer.crawlite.job.CrawlJob;
import com.hetzer.crawlite.mock.MockResource;

public abstract class AbstractExtractor implements Processor {
	@Override
	public boolean process(CrawlableURL source,CrawlJob crawlJob){
		//crawlJob.getUrlProvider().add(new MockResource("url"));
		return Extract(source);

	}
	public abstract boolean Extract(CrawlableURL url);
	

}
