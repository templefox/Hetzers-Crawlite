package com.hetzer.crawlite.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.UrlProvider;
import com.hetzer.crawlite.job.CrawlJob;

public class MockURLChain implements UrlProvider {
	ArrayList<CrawlableURL> list = new ArrayList<CrawlableURL>();
	int current = 0;
	final static int timeOut = 10;
	@Override
	public synchronized boolean hasNext() {
		if (current + 1 > list.size()) {
			try {
				Thread.sleep(timeOut);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (current + 1 > list.size()) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public CrawlableURL next(CrawlJob job) {
		return list.get(current++);
	}

	public boolean add(CrawlableURL e, CrawlJob job) {
		return false;
	}


	@Override
	public boolean add(CrawlableURL e, CrawlJob job, int pirority) {
		// TODO Auto-generated method stub
		return false;
	}

}
