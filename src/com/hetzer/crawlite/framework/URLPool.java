package com.hetzer.crawlite.framework;

import com.hetzer.crawlite.datamodel.CrawlableURL;

/**
 * <ul>
 *     <li>What URIs have been discovered
 *     <li>What URIs are being processed (fetched)
 *     <li>What URIs have been processed
 *     <li>In what order unprocessed URIs will be processed
 * </ul>
 */
public interface URLPool {
	public CrawlableURL next();
	public void insert(CrawlableURL url);
	public void initialize();
}
