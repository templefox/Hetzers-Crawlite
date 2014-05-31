package com.hetzer.crawlite.processers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Write_Image extends Write_Abstract {
	public void doit(CrawlableURL source) {
		if(source.getURL().matches(".*?gif")||source.getURL().matches(".*?png")||source.getURL().matches(".*?jpg")||source.getURL().matches(".*?gif"))
		{
		//int download_count = 0;
		Map<String, byte[]> map = new HashMap<String, byte[]>();
		//Map<String, String> map_img_url = source.getmap_img_url_stockMap();
		try {
			//while (download_count < source.getIMG_I()) {
				HttpClient client = new HttpClient();
				GetMethod get = new GetMethod(source.getURL());
				client.executeMethod(get);
				map.put("pc1", get.getResponseBody());
				//download_count++;
				source.setIMG_I(1);
			//}
			source.setmap(map);
			System.out.println("IMG Write Successfully, " + source.getURL());

		} catch (Exception e) {
			// e.printStackTrace();
//			System.out.println("Failure happens at"
//					+ source.IMGurlstock[download_count]);
			System.out.println("IMG Write Fail");
		}
		}
	}

}
