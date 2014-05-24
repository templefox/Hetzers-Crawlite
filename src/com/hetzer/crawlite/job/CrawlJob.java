package com.hetzer.crawlite.job;

import java.util.Random;

import com.hetzer.crawlite.CrawlJobManager;
import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.CThread;
import com.hetzer.crawlite.framework.ProcesserChain;
import com.hetzer.crawlite.framework.Processor;
import com.hetzer.crawlite.framework.UrlProvider;
import com.hetzer.crawlite.framework.urlProvider.H2UrlProvider;
import com.hetzer.crawlite.mock.MockProcesserChain;
import com.hetzer.crawlite.mock.MockProcessor;
import com.hetzer.crawlite.mock.MockResource;
import com.hetzer.crawlite.mock.MockURLChain;

public class CrawlJob {
	private CrawlJobManager crawlJobManager;
	private UrlProvider urlProvider;
	private ProcesserChain processerChain;
	private String name;
	private int THREAD_NUM = 1;
	private CThread[] threads;
	
	public CrawlJob(CrawlJobManager cjm) {
		this(H2UrlProvider.instance(),new MockProcesserChain());
		this.crawlJobManager = cjm;
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
		System.out.println("start "+this.getName());
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
}
