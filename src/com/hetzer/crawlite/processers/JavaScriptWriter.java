package com.hetzer.crawlite.processers;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class JavaScriptWriter extends AbstractWriter {
	public boolean Download(CrawlableURL source, File dir) {
		if (source.getBoolean(CrawlableURL.JS)) {
			try {
				File file = new File(dir, "js_src");
				file.mkdir();
				String name = source.getURL().replaceAll("/|:|\\?", "_");
				System.out.println(name);
				File d = new File(file, name);
				d.mkdir();
				FileWriter fw = new FileWriter(new File(d, name + ".js"));
				fw.write(source.getString(CrawlableURL.JS));
				fw.close();
				System.out.println("Javascript Download Successfully, ");
			} catch (Exception e) {
				System.out.println("Javascript Write Fail");
			}
		}
		
		return true;
	}

}
