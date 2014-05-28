package com.hetzer.crawlite.processers;

import java.io.File;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.Processor;
import com.hetzer.crawlite.job.CrawlJob;

public abstract class AbstractDownload implements Processor{
	@Override
	public boolean process(CrawlableURL source,CrawlJob crawlJob){
		File dir = crawlJob.getDirectory();
		
		return Download(source,dir);

	}
	public abstract boolean Download(CrawlableURL url, File dir);

}
