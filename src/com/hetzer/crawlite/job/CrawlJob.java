package com.hetzer.crawlite.job;

import java.util.Random;

import com.hetzer.crawlite.CrawlJobManager;
import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.CThread;
import com.hetzer.crawlite.framework.ProcesserChain;
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
	private int THREAD_NUM;
	private CThread[] threads;
	
	public CrawlJob(CrawlJobManager cjm) {
		this(new H2UrlProvider(),new MockProcesserChain());
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
		
		setSeeds(new String[]{"a","b","c","d"});

		processerChain.add(new MockProcessor());
		processerChain.add(new MockProcessor());
		processerChain.add(new MockProcessor());
		processerChain.add(new MockProcessor());
		processerChain.add(new MockProcessor());
		
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
		for (String seed : seeds) {
			urlProvider.add(new MockResource(seed));			
		}
	}

	public UrlProvider getUrlProvider() {
		return urlProvider;
	}
	
	public ProcesserChain getProcesserChain(){
		return processerChain;
	}
}
