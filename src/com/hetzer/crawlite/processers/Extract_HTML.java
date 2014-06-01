package com.hetzer.crawlite.processers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.job.CrawlJob;
import com.hetzer.crawlite.mock.MockResource;

public class Extract_HTML extends AbstractExtractor {
	public boolean Extract(CrawlableURL source, CrawlJob crawlJob) {
		if (source.getWebsource() != null) {
			String regular_html = User_Regular_HTML.Regular_Fun();
			Pattern p_html;
			Matcher m_html;
			p_html = Pattern.compile(regular_html);
			m_html = p_html.matcher(source.getWebsource());
			int i_html = 0;
			while (m_html.find()) {
				crawlJob.getUrlProvider().add(
						new MockResource(m_html.group(1)), crawlJob);
				i_html++;
			}
			source.setUrlnum(i_html + source.getUrlnum());
		}
		return true;
	}

}
