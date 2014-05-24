package com.hetzer.crawlite.framework;

import java.util.Iterator;
import java.util.List;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.job.CrawlJob;

/**
 * <ul>
 *     <li>What URIs have been discovered
 *     <li>What URIs are being processed (fetched)
 *     <li>What URIs have been processed
 *     <li>In what order unprocessed URIs will be processed
 * </ul>
 */
public interface UrlProvider extends Iterator<CrawlableURL>,List<CrawlableURL>{
	public void initialize();
	public CrawlableURL next(CrawlJob job);
	public boolean add(CrawlableURL e, CrawlJob job);
}
