package com.hetzer.crawlite.mock;

import java.util.Iterator;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.CThread;
import com.hetzer.crawlite.framework.ProcesserChain;
import com.hetzer.crawlite.framework.Processor;
import com.hetzer.crawlite.framework.UrlProvider;
import com.hetzer.crawlite.job.CrawlJob;

public class MockCThread implements CThread {
	Thread thread;
	CrawlJob crawlJob;

	public MockCThread() {
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				UrlProvider provider = crawlJob.getUrlProvider();
				ProcesserChain processerChain = crawlJob.getProcesserChain();
				while (provider.hasNext()) {
					CrawlableURL url = (CrawlableURL) provider.next();
					System.out.println(url.getURL());

					for (Iterator iterator = processerChain.iterator(); iterator
							.hasNext();) {
						Processor processor = (Processor) iterator.next();
						processor.process(url);

					}

				}

			}
		});
	}

	@Override
	public void insert(CrawlJob crawlJob) {
		System.out.println("insert" + this);
		this.crawlJob = crawlJob;
	}

	@Override
	public void jobStart() {
		System.out.println("start");
		thread.start();
	}

	@Override
	public void jobFinish() {
		// TODO Auto-generated method stub

	}

	@Override
	public void jobPause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void jobResume() {
		// TODO Auto-generated method stub

	}

	@Override
	public thread_state getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
