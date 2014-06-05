package com.hetzer.crawlite.processers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hetzer.crawlite.CrawlJobManager;
import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.job.CrawlJob;

public class CSSExtractr extends AbstractExtractor {
	public boolean Extract(CrawlableURL source, CrawlJob crawlJob) {
		String webSource = source.getString(CrawlableURL.WEB_SOURCE);
		if (webSource != null) {
			String regular_css = User_Regular_CSS.Regular_CSS_Fun();
			Pattern p_css;
			Matcher m_css;
			p_css = Pattern.compile(regular_css);
			m_css = p_css.matcher(webSource);
			while (m_css.find()) {
				String temp = m_css.group(1);
				if (temp.matches("/.*?")) {
					temp = source.getURL() + temp;
					CrawlableURL object = CrawlJobManager.makeUrlObject(temp);
					object.setDepth(source.getDepth() + 1);
					crawlJob.getUrlProvider().add(object, crawlJob,
							object.getDepth());
				} else {
					CrawlableURL object = CrawlJobManager.makeUrlObject(m_css
							.group(1));
					object.setDepth(source.getDepth() + 1);
					crawlJob.getUrlProvider().add(object, crawlJob,
							object.getDepth());
				}
			}

		}
		return true;
	}
}
