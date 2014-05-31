package com.hetzer.crawlite.processers;

import java.io.FileWriter;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Download_HTML extends Download_Abstract {
	public void doit(CrawlableURL source) {
		if(source.getWebsource()!=null)
		{
		try {

			FileWriter fw = new FileWriter("html_src\\index.html");
			fw.write(source.getWebsource());
			fw.close();
			System.out.println("Websource Download Successfully");
		} catch (Exception e) {
			System.out.println("Websource Download Fail");
		}
		}

	}

}
