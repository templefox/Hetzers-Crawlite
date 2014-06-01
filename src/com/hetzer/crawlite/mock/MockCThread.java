package com.hetzer.crawlite.mock;

import java.util.Iterator;
import java.util.Random;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.CThread;
import com.hetzer.crawlite.framework.ProcesserChain;
import com.hetzer.crawlite.framework.Processor;
import com.hetzer.crawlite.framework.UrlProvider;
import com.hetzer.crawlite.job.CrawlJob;

public class MockCThread implements CThread {
	Thread thread;
	CrawlJob crawlJob;
	ProcesserChain processerChain;
	CrawlableURL current;
	static int i = 0;

	public MockCThread() {
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				a: while (true) {
					UrlProvider provider = crawlJob.getUrlProvider();
					processerChain = crawlJob.getProcesserChain();
					while (true) {
						current = (CrawlableURL) provider.next(crawlJob);
						if (current.getURL() == null) {
							break a;
						}
						System.out.println(current.getURL()
								+ crawlJob.getName());

						process();

						current = null;
					}
				}
			}

		});
	}

	private void process() {
		for (Iterator iterator = processerChain.iterator(); iterator.hasNext();) {
			Processor processor = (Processor) iterator.next();
			processor.process(current, crawlJob);

		}
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

	public thread_state getState() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public thread_state getMyState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTaskName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getAbandon() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void jobRecycle() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getRunTime() {
		// TODO Auto-generated method stub
		return false;
	}

}
