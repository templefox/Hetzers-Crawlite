package com.hetzer.crawlite.framework;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public interface Processor {
	public boolean process(CrawlableURL url);
}
