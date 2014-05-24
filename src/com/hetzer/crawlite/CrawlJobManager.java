package com.hetzer.crawlite;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.omg.PortableServer.POA;

import com.hetzer.crawlite.framework.CThread;
import com.hetzer.crawlite.framework.CThreadPool;
import com.hetzer.crawlite.job.CrawlJob;
import com.hetzer.crawlite.job.CrawlJobFactory;
import com.hetzer.crawlite.mock.MockCThreadPool;

/**
 * manage jobs.
 */
public class CrawlJobManager {
	private Map<String, CrawlJob> jobMap;
	private int MAX_THREAD;
	private CThreadPool threadPool;
	private String jobPath;
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

	public void initialize(Properties properties) {
		System.out.println("CrawlJobManager initialize");
		threadPool = new MockCThreadPool();
		loadConfigs(properties);
		loadJobs();
		initThreadPool();

		startCrawlers(new String[] {});
	}

	private void loadConfigs(Properties properties) {
		jobPath = properties.getProperty("jobPath", "jobs");
		MAX_THREAD = 2;
		System.out.println("loadConfigs");
	}

	private void loadJobs() {
		System.out.println("loadJobs");

		File jobDir = new File(jobPath);
		if (jobDir.isDirectory()) {
			File[] dirs = jobDir.listFiles();
			for (File dir : dirs) {
				File jobConfig = new File(dir, "config.xml");
				jobMap.put("loaded",
						new CrawlJobFactory().makeJob(this, jobConfig));
			}
		} else {
			throw new IllegalStateException("jobDir is not a directory");
		}

		jobMap.put("test", new CrawlJobFactory().makeJob(this));
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
			}
			for (CrawlJob job : jobMap.values()) {
				job.startCrawler();
			}
		} else {
			for (String name : names) {
				CrawlJob job = jobMap.get(name);
				job.initialize();
			}
			for (CrawlJob job : jobMap.values()) {
				job.startCrawler();
			}
		}
	}

	public CThread[] apply(int num) {
		return threadPool.apply(num);
	}

	public String getJobPath() {
		return jobPath;
	}
}
