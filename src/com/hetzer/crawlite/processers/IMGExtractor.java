package com.hetzer.crawlite.processers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hetzer.crawlite.CrawlJobManager;
import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.job.CrawlJob;
import com.hetzer.crawlite.mock.MockResource;

public class IMGExtractor extends AbstractExtractor {
	public boolean Extract(CrawlableURL source, CrawlJob crawlJob) {
		String webSource = source.getString(CrawlableURL.WEB_SOURCE);
		if (webSource != null) {
			int i_img = 0;
			String regular_img = User_Regular_IMG.Regular_Fun(); // Regular_FunÎª¾²Ì¬º¯Êý
			Pattern p_img;
			Matcher m_img;
			p_img = Pattern.compile(regular_img);
			m_img = p_img.matcher(webSource);
			source.setDepth(source.getDepth() + i_img);
			while (m_img.find()) {
				String temp = m_img.group(1);
				if (temp.matches("/.*?")) {
					temp = source.getURL() + temp;
					CrawlableURL object = CrawlJobManager.makeUrlObject(temp);
					object.setDepth(source.getDepth() + 1);
					crawlJob.getUrlProvider().add(object, crawlJob,
							object.getDepth());
				} else {

					CrawlableURL object = CrawlJobManager.makeUrlObject(m_img
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
