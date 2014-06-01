package com.hetzer.crawlite.processers;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Analyze_Implementation extends AbstractAnalyzer {
	public boolean analyzer(CrawlableURL source) {
		source.setwebsource(null);
		source.setmap(null);
		return true;
	}

}
