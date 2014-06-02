package com.hetzer.crawlite.processers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Map;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class ImageWriter extends AbstractWriter {
	public boolean Download(CrawlableURL source, File dir) {
		if (source.getBoolean(CrawlableURL.IMG)) {
			try {
				if (source.getURL().matches("http.*?")) {
					File file = new File(dir, "img_src");
					file.mkdir();
					String name = source.getURL().replaceAll("/|:|\\?", "_");
					System.out.println(name);
					File d = new File(file, name);
					d.mkdir();
					File storeFile = new File(d, name + ".jpg");
					FileOutputStream fileOutputStream = new FileOutputStream(
							storeFile);
					fileOutputStream.write(source.getBytes(CrawlableURL.IMG));
					fileOutputStream.close();
				} else {
					System.out.println("Í¼Æ¬ ÏÂÔØÊ§°Ü£¬Ìø¹ý");
				}
				System.out.println("IMG Download Successfully, ");

			} catch (Exception e) {
				System.out.println("IMG Download Fail");
			}
		}
		source.putBoolean(CrawlableURL.IMG, false);
		return true;
	}

}
