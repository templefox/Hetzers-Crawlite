package com.hetzer.crawlite.processers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Download_HTML extends AbstractDownload {
	public boolean Download(CrawlableURL source,File dir) {
		if(source.getWebsource()!=null)
		{
		try {
			File file = new File(dir,"html_src");
            file.mkdir();
            String name = source.getURL().replace('/', '_').replace(':', '_');
            System.out.println(name);
            File d = new File(file,name);
            d.mkdir();
			FileWriter fw = new FileWriter(new File(d,name+".html"));
			fw.write(source.getWebsource());
			fw.close();
			System.out.println("Websource Download Successfully");
		} catch (IOException e) {
			System.out.println("Websource Download Fail");
			e.printStackTrace();
		}
		}
		return true;

	}

}
