package com.hetzer.crawlite.processers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class ImageFetcher extends AbstractFetcher {
	public void fetch(CrawlableURL source) {
		if (source.getURL().matches(".*?gif")
				|| source.getURL().matches(".*?png")
				|| source.getURL().matches(".*?jpg")) {
			try {
				HttpClient client = new HttpClient();
				GetMethod get = new GetMethod(source.getURL());
				client.executeMethod(get);
				// XXX map.put("pc1", get.getResponseBody());
				source.putBytes(CrawlableURL.IMG, get.getResponseBody());
				source.putBoolean(CrawlableURL.IMG, true);
				System.out
						.println("IMG Write Successfully, " + source.getURL());

			} catch (Exception e) {
				System.out.println("IMG Write Fail");
				source.putBoolean(CrawlableURL.IMG, false);
			}
		} else {
			source.putBoolean(CrawlableURL.IMG, false);

		}
	}

}
