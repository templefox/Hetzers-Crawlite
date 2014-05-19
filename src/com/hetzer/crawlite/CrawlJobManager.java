package com.hetzer.crawlite;

import java.util.HashMap;
import java.util.Map;

import com.hetzer.crawlite.framework.CThread;
import com.hetzer.crawlite.framework.CThreadPool;
import com.hetzer.crawlite.job.CrawlJob;
import com.hetzer.crawlite.mock.MockCThreadPool;

/**
 * manage jobs.
 */
public class CrawlJobManager {
	private Map<String, CrawlJob> jobMap;
	private int MAX_THREAD;
	private CThreadPool threadPool;
	private static CrawlJobManager crawlJobManager = new CrawlJobManager();

	private CrawlJobManager() {
		this(new HashMap<String, CrawlJob>());
	}

	private CrawlJobManager(Map<String, CrawlJob> jobMap) {
		this.jobMap = jobMap;
	}

	public static CrawlJobManager instance() {
		return crawlJobManager;
	}

	public void initialize() {
		System.out.println("CrawlJobManager initialize");
		threadPool = new MockCThreadPool();
		loadConfigs();
		loadJobs();
		initThreadPool();

		startCrawlers(new String[] {});
	}

	private void loadConfigs() {
		MAX_THREAD = 2;
		System.out.println("loadConfigs");
	}

	private void loadJobs() {
		jobMap.put("test", new CrawlJob(this));
		System.out.println("loadJobs");
	}

	private void initThreadPool() {
		threadPool.initialize(MAX_THREAD);
		System.out.println("initThreadPool");
	}

	/**
	 * @param name
	 *            if null than start all.
	 */
	public void startCrawlers(String... names) {
		System.out.println("startCrawlers");
		if (names == null || names.length == 0) {
			for (CrawlJob job : jobMap.values()) {
				job.initialize();
				job.startCrawler();
			}
		} else {
			for (String name : names) {
				CrawlJob job = jobMap.get(name);
				job.initialize();
				job.startCrawler();
			}
		}
	}

	public CThread[] apply(int num) {
		return threadPool.apply(num);
	}
}
