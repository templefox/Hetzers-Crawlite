package com.hetzer.crawlite.processers;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Analyze_Implementation extends AbstractPreFilter {
	public boolean analyzer(CrawlableURL source) {
		return true;
	}

}
