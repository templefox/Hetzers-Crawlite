package com.hetzer.crawlite.framework;

import com.hetzer.crawlite.job.CrawlJob;

public interface CThread {
	public enum thread_state {
		waiting, working
	}
	
	public void insert(CrawlJob crawlJob);
	
	public void jobStart();

	public void jobFinish();

	public void jobPause();

	public void jobResume();

	public thread_state getMyState();

	public String getTaskName();
	
    public boolean getAbandon();
	
	public void jobRecycle();

	public boolean getRunTime();

}
