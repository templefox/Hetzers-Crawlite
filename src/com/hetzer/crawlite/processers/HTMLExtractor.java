package com.hetzer.crawlite.processers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hetzer.crawlite.CrawlJobManager;
import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.job.CrawlJob;
import com.hetzer.crawlite.mock.MockResource;

public class HTMLExtractor extends AbstractExtractor {
	public boolean Extract(CrawlableURL source, CrawlJob crawlJob) {
		String webSource = source.getString(CrawlableURL.WEB_SOURCE);
		if (webSource != null) {
			String regular_html = User_Regular_HTML.Regular_Fun();
			Pattern p_html;
			Matcher m_html;
			p_html = Pattern.compile(regular_html);
			m_html = p_html.matcher(webSource);
			int i_html = 0;
			source.setDepth(i_html + source.getDepth());
			while (m_html.find()) {
				CrawlableURL object = CrawlJobManager.makeUrlObject(m_html.group(1));
				object.setDepth(source.getDepth()+1);
				crawlJob.getUrlProvider()
						.add(object,
								crawlJob,object.getDepth());
			}
		}
		return true;
	}

}
