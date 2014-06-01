package com.hetzer.crawlite.processers;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Download_CSS extends AbstractDownload {
	public boolean Download(CrawlableURL source, File dir) {
		if (source.isCSS_Flag()) {
			// Map<String, String> map_css = source.getMap_css();
			try {

				File file = new File(dir, "css_src");
				file.mkdir();
				String name = source.getURL().replace('/', '_')
						.replace(':', '_').replace('?', '_').replace('"', '_')
						.replace('<', '_').replace('>', '_').replace('|', '_')
						.replace('*', '_').replace('"', '_');
				System.out.println(name);
				File d = new File(file, name);
				d.mkdir();
				FileWriter fw = new FileWriter(new File(d, name + ".css"));
				fw.write(source.getContent());
				fw.close();
				// download_count_css++;
				// }
				System.out.println("CSS Download Successfully");
			} catch (Exception e) {
				System.out.println("CSS Download Fail");

				e.printStackTrace();
			}

		}
		source.setCSS_Flag(false);
		return true;
	}

}
