package com.hetzer.crawlite.framework;

public interface Processor {
	/**
	 * @return
	 * Is process be consumed, meaning to stop future process.
	 */
	public boolean process();
}
