package com.hetzer.crawlite.processers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Write_Image extends AbstractWrite {
	public void doit(CrawlableURL source) {
		if (source.getURL().matches(".*?gif")
				|| source.getURL().matches(".*?png")
				|| source.getURL().matches(".*?jpg")) {
			Map<String, byte[]> map = new HashMap<String, byte[]>();
			try {
				HttpClient client = new HttpClient();
				GetMethod get = new GetMethod(source.getURL());
				client.executeMethod(get);
				map.put("pc1", get.getResponseBody());
				source.setIMG_Flag(true);
				source.setmap(map);
				System.out
						.println("IMG Write Successfully, " + source.getURL());

			} catch (Exception e) {
				System.out.println("IMG Write Fail");
			}
		}
	}

}
