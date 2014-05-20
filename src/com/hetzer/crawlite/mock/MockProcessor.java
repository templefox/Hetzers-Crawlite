package com.hetzer.crawlite.mock;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.Processor;

public class MockProcessor implements Processor {

	@Override
	public boolean process(CrawlableURL url) {
		System.out.println("Processor:" + this + " process " + url.getURL());
		return true;
	}
}
