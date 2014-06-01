package com.hetzer.crawlite;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.hetzer.crawlite.exception.OverFlowException;
import com.hetzer.crawlite.framework.CThread;
import com.hetzer.crawlite.framework.CThreadPool;
import com.hetzer.crawlite.framework.Processor;
import com.hetzer.crawlite.job.CrawlJob;
import com.hetzer.crawlite.job.CrawlJobFactory;
import com.hetzer.crawlite.mock.MockProcessor;
import com.hetzer.crawlite.processers.Download_CSS;
import com.hetzer.crawlite.processers.Write_CSS;
import com.hetzer.crawlite.processers.Download_CSS;
import com.hetzer.crawlite.processers.Download_HTML;
import com.hetzer.crawlite.processers.Download_JavaScript;
import com.hetzer.crawlite.processers.Extractor_Implementation;
import com.hetzer.crawlite.processers.Write_CSS;
import com.hetzer.crawlite.processers.Write_HTML;
import com.hetzer.crawlite.processers.Write_JavaScript;
import com.hetzer.crawlite.thread.GxyCThreadPool;

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
		threadPool = new GxyCThreadPool("GXY");
		loadConfigs(properties);
		loadJobs();
		initThreadPool();

		CrawlJob job1 = makeTestJob("TestJob1");
		startCrawlers(new String[] {});
	}

	private CrawlJob makeTestJob(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("ThreadNum", 4);
		
		map.put("RetryTimes", 3);
		List<Class<? extends Processor>> list = new ArrayList<Class<? extends Processor>>();
		list.add(Write_CSS.class);
		list.add(Download_CSS.class);
		
		list.add(MockProcessor.class);
		list.add(Write_HTML.class);
		list.add(Download_HTML.class);
		list.add(Extractor_Implementation.class);
		map.put("processorList", list);
		CrawlJob job = makeNewJob(map);
		job.setSeeds(new String[]{"http://static.soku.com/v1.0.0753/soku/inthesky/giantstar/css/s_comm.css"});
		
		putJob(job.getName(), job);
		job.setSeeds(new String[] { "http://www.163.com" });
		return job;
	}

	private void loadConfigs(Properties properties) {
		jobPath = properties.getProperty("jobPath", "jobs");
		MAX_THREAD = new Integer(properties.getProperty("maxThread", "200"));
		System.out.println("loadConfigs");
	}

	/**
	 * Load exist jobs from directory.
	 */
	private void loadJobs() {
		System.out.println("loadJobs");

		File jobDirs = new File(jobPath);
		if (jobDirs.isDirectory()) {
			File[] dirs = jobDirs.listFiles();
			for (File ajobDir : dirs) {
				loadJob(ajobDir);
			}
		} else {
			// throw new IllegalStateException("jobDir is not a directory");
		}

		// jobMap.put("test", new CrawlJobFactory().makeJob(this));
	}

	/**
	 * Load an exist job from target directory. Make instance and put into map.
	 * 
	 * @param dir
	 *            represent a job, which contains config.xml.
	 */
	private void loadJob(File dir) {
		File jobConfig = new File(dir, "config.xml");
		CrawlJob job = new CrawlJobFactory(null).makeJob(this, jobConfig);
		putJob(job.getName(), job);
	}

	public void putJob(String name, CrawlJob job) {
		jobMap.put(name, job);
	}

	private void initThreadPool() {
		threadPool.initialize(MAX_THREAD);
		System.out.println("initThreadPool");
	}

	/**
	 * Make a new job. First make the directory and the config.xml according the
	 * "configs".
	 * 
	 * @param configs
	 *            configuration of job.
	 * @return
	 */
	public CrawlJob makeNewJob(Map<String, Object> configs) {
		CrawlJobFactory factory = new CrawlJobFactory(configs);
		File configFile = factory.makeConfigXml(factory.makedir(jobPath));
		CrawlJob job = factory.makeJob(this, configFile);
		return job;
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
			for (String name : names) {
				CrawlJob job = jobMap.get(name);
				job.startCrawler();
			}
		}
	}

	public CThread[] apply(int num) throws OverFlowException {
		return threadPool.apply(num);
	}

	public String getJobPath() {
		return jobPath;
	}
}
