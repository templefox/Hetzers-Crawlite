package com.hetzer.crawlite.job;

import java.io.File;
import java.util.Random;

import com.hetzer.crawlite.CrawlJobManager;
import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.exception.OverFlowException;
import com.hetzer.crawlite.framework.CThread;
import com.hetzer.crawlite.framework.ProcesserChain;
import com.hetzer.crawlite.framework.Processor;
import com.hetzer.crawlite.framework.UrlProvider;
import com.hetzer.crawlite.framework.urlProvider.H2UrlProvider;
import com.hetzer.crawlite.mock.MockProcesserChain;
import com.hetzer.crawlite.mock.MockProcessor;
import com.hetzer.crawlite.mock.MockResource;
import com.hetzer.crawlite.mock.MockURLChain;
import com.hetzer.crawlite.thread.GxyCThread;

public class CrawlJob {
	private CrawlJobManager crawlJobManager;
	private UrlProvider urlProvider;
	private ProcesserChain processerChain;
	private String name;
	private int THREAD_NUM;
	private int RETRY_TIMES;
	private int retry = 0;
	private CThread[] threads;
	private File directory;
	
	public CrawlJob(CrawlJobManager cjm) {
		this(H2UrlProvider.instance(),new MockProcesserChain());
		this.crawlJobManager = cjm;
	}
	
	public CrawlJob(UrlProvider urlProvider,ProcesserChain processerChain){
		this.urlProvider = urlProvider;
		this.processerChain = processerChain;
	}
	
	public void initialize(){
		try {
			threads = crawlJobManager.apply(THREAD_NUM);
		} catch (OverFlowException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].insert(this);
		}		
	}
	public void startCrawler(){
		System.out.println("start "+this.getName());
		retry = 0;
		for (int i = 0; i < threads.length; i++) {
			threads[i].jobStart();
		}
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setSeeds(String[] seeds){
		for (String seed : seeds) {
			urlProvider.add(new MockResource(seed),this);			
		}
	}

	public UrlProvider getUrlProvider() {
		return urlProvider;
	}
	
	public ProcesserChain getProcesserChain(){
		return processerChain;
	}
	
	public void addProcessor(Processor processor){
		processerChain.add(processor);
	}

	public void setThreadNum(Integer integer) {
		this.THREAD_NUM = integer;
	}

	public void stop() {
		for (CThread thread : threads) {
			thread.jobFinish();
		}
	}

	public int getRetryTimes() {
		return RETRY_TIMES;
	}
	
	public boolean checkCanRetry(){
		return retry++<=getRetryTimes();
	}

	public void setRetryTimes(int retryTimes) {
		RETRY_TIMES = retryTimes;
	}

	public File getDirectory() {
		return directory;
	}

	public void setDirectory(File directory) {
		this.directory = directory;
	}
}
