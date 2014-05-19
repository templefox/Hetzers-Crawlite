package com.hetzer.crawlite.job;

import java.util.Random;

import com.hetzer.crawlite.CrawlJobManager;
import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.CThread;
import com.hetzer.crawlite.framework.ProcesserChain;
import com.hetzer.crawlite.framework.UrlProvider;
import com.hetzer.crawlite.mock.MockProcesserChain;
import com.hetzer.crawlite.mock.MockResource;
import com.hetzer.crawlite.mock.MockURLChain;

public class CrawlJob {
	private CrawlJobManager crawlJobManager;
	private UrlProvider urlProvider;
	private ProcesserChain processerChain;
	private String name;
	private int THREAD_NUM;
	private CThread[] threads;
	
	public CrawlJob(CrawlJobManager cjm) {
		this(new MockURLChain(),new MockProcesserChain());
		this.crawlJobManager = cjm;
		name = "default-"+(new Random().nextInt()&0x7FFFFFFF);	
	}
	
	public CrawlJob(UrlProvider urlProvider,ProcesserChain processerChain){
		this.urlProvider = urlProvider;
		this.processerChain = processerChain;
	}
	
	public void initialize(){
		threads = crawlJobManager.apply(THREAD_NUM);
		for (int i = 0; i < threads.length; i++) {
			threads[i].insert(this);
		}
	}
	public void startCrawler(){
		for (int i = 0; i < threads.length; i++) {
			threads[i].jobStart();
		}
	}

	public String getName() {
		return name;
	}
	
	public void setSeeds(String[] seeds){
		urlProvider.insert(new MockResource("www.1.com"));
	}

	public UrlProvider getUrlProvider() {
		return urlProvider;
	}
	
	public ProcesserChain getProcesserChain(){
		return processerChain;
	}
}
