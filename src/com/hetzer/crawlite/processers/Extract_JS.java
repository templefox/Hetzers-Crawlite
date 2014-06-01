package com.hetzer.crawlite.processers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.job.CrawlJob;
import com.hetzer.crawlite.mock.MockResource;

public class Extract_JS extends AbstractExtractor {
	public boolean Extract(CrawlableURL source, CrawlJob crawlJob) {
		String webSource = source.getString(CrawlableURL.WEB_SOURCE);
if (webSource != null) {
			String regular_js = User_Regular_JS.Regular_JS_Fun();
			
			Pattern p_js;
			Matcher m_js;
			p_js = Pattern.compile(regular_js);
			m_js = p_js.matcher(webSource);
			int i_js = 0;
			while (m_js.find()) {
				crawlJob.getUrlProvider().add(new MockResource(m_js.group(1)),
						crawlJob);
				i_js++;
			}
			source.setUrlnum(i_js + source.getUrlnum());
		}
		return true;
	}

}
