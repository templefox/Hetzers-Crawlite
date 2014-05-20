package com.hetzer.crawlite.framework;

import java.util.Iterator;
import java.util.List;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public interface ProcesserChain extends Iterator<Processor>,List<Processor> {

}
