package com.hetzer.crawlite.processers;

import java.io.File;
import java.io.FileWriter;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Download_HTML extends AbstractDownload {
	public boolean Download(CrawlableURL source,File dir) {
		if(source.getWebsource()!=null)
		{
		try {
			File file = new File(dir,"html_src");
            file.mkdir();
			FileWriter fw = new FileWriter("dir\\html_src\\index.html");
			fw.write(source.getWebsource());
			fw.close();
			System.out.println("Websource Download Successfully");
		} catch (Exception e) {
			System.out.println("Websource Download Fail");
		}
		}
		return true;

	}

}
