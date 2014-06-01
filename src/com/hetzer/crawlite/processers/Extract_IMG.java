package com.hetzer.crawlite.processers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.job.CrawlJob;
import com.hetzer.crawlite.mock.MockResource;

public class Extract_IMG extends AbstractExtractor {
	public boolean Extract(CrawlableURL source, CrawlJob crawlJob) {
		if (source.getWebsource() != null) {
			String temp;
			int i_img = 0;
			temp = source.getWebsource();
			String regular_img = User_Regular_IMG.Regular_Fun(); // Regular_FunÎª¾²Ì¬º¯Êý
			Pattern p_img;
			Matcher m_img;
			p_img = Pattern.compile(regular_img);
			m_img = p_img.matcher(temp);
			while (m_img.find()) {
				// urlstock[img_initial + i_img] = m_img.group(1);
				crawlJob.getUrlProvider().add(new MockResource(m_img.group(1)),
						crawlJob);
				i_img++;
			}
			source.setUrlnum(source.getUrlnum() + i_img);
		}
		return true;
	}

}
