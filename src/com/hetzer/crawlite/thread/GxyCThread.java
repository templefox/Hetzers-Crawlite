package com.hetzer.crawlite.thread;

import java.util.Iterator;
import java.util.Random;

import com.hetzer.crawlite.Crawlite;
import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.CThread;
import com.hetzer.crawlite.framework.ProcesserChain;
import com.hetzer.crawlite.framework.Processor;
import com.hetzer.crawlite.framework.UrlProvider;
import com.hetzer.crawlite.job.CrawlJob;

public class GxyCThread extends Thread implements CThread {
	static int i = 0;
	private Object objInit = new Object();
	private boolean isInit = true;
	private String threadname;
	private Object objPause = new Object();
	private Object objAban = new Object();
	private boolean isRun = false;
	private boolean isWait = true;
	private boolean isGone = false;
	private boolean ischosen = false;
	private static final long SLEEP_TIME = 500;
	private long sleepTime = SLEEP_TIME;
	CrawlJob crawlJob;
	ProcesserChain processerChain;
	CrawlableURL current;

	public GxyCThread(ThreadGroup tg, String name) {
		super(tg, name);
		this.threadname = name;
	}

	public boolean isIschosen() {
		return ischosen;
	}

	public void setIschosen(boolean ischosen) {
		this.ischosen = ischosen;
	}

	@Override
	public void run() {
		checkIsinit();
		while (isRun) {
			doCrawlTask();

			checkIsWait();

			checkIsAbandon();
		}
	}

	private void checkIsinit() {
		synchronized (objInit) {
			while (isInit) {
				try {
					objInit.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	private void checkIsAbandon() {
		synchronized (objAban) {
			while (isGone) {

				try {
					objAban.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void checkIsWait() {
		synchronized (objPause) {
			while (isWait) {

				try {
					objPause.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void doCrawlTask() {
		System.out.println("do" + threadname);
		UrlProvider provider = crawlJob.getUrlProvider();
		processerChain = crawlJob.getProcesserChain();
		while (true) {
			current = (CrawlableURL) provider.next(crawlJob);
			if (current.getURL() == null) {
				try {
					sleep(sleepTime);
					sleepTime <<=1;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				sleepTime = SLEEP_TIME;
				System.out.println(current.getURL() + " " + crawlJob.getName()
						+ " " + threadname);
				
				process();

				current = null;
			}

		}

	}

	private void process() {
		for (Iterator iterator = processerChain.iterator(); iterator.hasNext();) {
			Processor processor = (Processor) iterator.next();
			boolean result = processor.process(current, crawlJob);
			if (!result) {
				break;
			}
		}
	}

	@Override
	public void insert(CrawlJob crawlJob) {
		this.crawlJob = crawlJob;
		System.out.println("insert " + this.crawlJob.getName() + " " + this);
	}

	@Override
	public thread_state getMyState() {
		if (this.isWait) {
			return thread_state.waiting;
		}
		return thread_state.working;
	}

	public boolean getTheState() {
		return this.isWait;
	}

	@Override
	public boolean getAbandon() {
		return this.isGone;
	}

	@Override
	public String getTaskName() {
		return this.threadname;
	}

	@Override
	public boolean getRunTime() {
		// TODO Auto-generated method stub
		return isRun;
	}

	@Override
	public void jobStart() {
		isWait = false;
		isRun = true;
		if (isGone == true) {
			jobRecycle();
		}
		if (isInit == true) {
			isInit = false;
			synchronized (objInit) {
				objInit.notifyAll();
			}
		}

	}

	@Override
	public void jobFinish() {
		// TODO Auto-generated method stub
		if (isWait == true) {
			isWait = false;
			synchronized (objPause) {
				objPause.notifyAll();
			}
		}
		ischosen = false;
		isGone = true;
	}

	@Override
	public void jobPause() {
		// TODO Auto-generated method stub
		isWait = true;

	}

	@Override
	public void jobResume() {
		// TODO Auto-generated method stub
		isWait = false;
		synchronized (objPause) {
			objPause.notifyAll();
		}
	}

	@Override
	public void jobRecycle() {
		isGone = false;
		synchronized (objAban) {
			objAban.notifyAll();
		}
	}

}
