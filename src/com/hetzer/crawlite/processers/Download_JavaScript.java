package com.hetzer.crawlite.processers;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Download_JavaScript extends AbstractDownload {
	public boolean Download(CrawlableURL source, File dir) {
		if (source.isJS_Flag()) {
			try {
				File file = new File(dir, "js_src");
				file.mkdir();
				String name = source.getURL().replace('/', '_')
						.replace(':', '_').replace('?', '_').replace('"', '_')
						.replace('<', '_').replace('>', '_').replace('|', '_')
						.replace('*', '_').replace('"', '_');
				System.out.println(name);
				File d = new File(file, name);
				d.mkdir();
				FileWriter fw = new FileWriter(new File(d, name + ".js"));
				fw.write(source.getContent());
				fw.close();
				System.out.println("Javascript Download Successfully, ");
			} catch (Exception e) {
				System.out.println("Javascript Write Fail");
			}
		}
		source.setJS_Flag(false);
		return true;
	}

}
