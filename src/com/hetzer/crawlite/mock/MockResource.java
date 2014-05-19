package com.hetzer.crawlite.mock;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class MockResource implements CrawlableURL {
	String url;
	
	public MockResource(String s) {
		url = s;
	}
	@Override
	public String getURL() {
		return url;
	}
	
}
