package com.hetzer.crawlite.processers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.job.CrawlJob;
import com.hetzer.crawlite.mock.MockResource;

public class Extract_CSS extends AbstractExtractor {
	public boolean Extract(CrawlableURL source, CrawlJob crawlJob) {
		String webSource = source.getString(CrawlableURL.WEB_SOURCE);
		if (webSource != null) {
			String regular_css = User_Regular_CSS.Regular_CSS_Fun();
			Pattern p_css;
			Matcher m_css;
			p_css = Pattern.compile(regular_css);
			m_css = p_css.matcher(webSource);
			int i_css = 0;
			while (m_css.find()) {
				crawlJob.getUrlProvider().add(new MockResource(m_css.group(1)),
						crawlJob);
				i_css++;
			}
			source.setUrlnum(source.getUrlnum() + i_css);

		}
		return true;
	}
}
