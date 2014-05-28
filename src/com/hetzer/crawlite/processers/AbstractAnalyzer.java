package com.hetzer.crawlite.processers;

import java.io.File;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.Processor;
import com.hetzer.crawlite.job.CrawlJob;

public abstract class AbstractAnalyzer implements Processor {
	
	@Override
	public boolean process(CrawlableURL source,CrawlJob crawlJob){
		
		return analyzer(source);

	}
	public abstract boolean analyzer(CrawlableURL url);

}
