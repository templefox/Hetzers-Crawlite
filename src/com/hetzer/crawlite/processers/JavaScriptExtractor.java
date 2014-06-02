package com.hetzer.crawlite.processers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hetzer.crawlite.CrawlJobManager;
import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.job.CrawlJob;
import com.hetzer.crawlite.mock.MockResource;

public class JavaScriptExtractor extends AbstractExtractor {
	public boolean Extract(CrawlableURL source, CrawlJob crawlJob) {
		String webSource = source.getString(CrawlableURL.WEB_SOURCE);
if (webSource != null) {
			String regular_js = User_Regular_JS.Regular_JS_Fun();
			
			Pattern p_js;
			Matcher m_js;
			p_js = Pattern.compile(regular_js);
			m_js = p_js.matcher(webSource);
			int i_js = 0;
			source.setDepth(i_js + source.getDepth());
			while (m_js.find()) {
				crawlJob.getUrlProvider().add(CrawlJobManager.makeUrlObject(m_js.group(1)),
						crawlJob,source.getDepth());
				i_js++;
			}
		}
		return true;
	}

}
