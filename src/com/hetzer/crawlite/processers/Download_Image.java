package com.hetzer.crawlite.processers;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Download_Image extends Download_Abstract{
	public void doit(CrawlableURL source) {
		if(source.getIMG_I()!=0)
		{

		//int download_count = 0;
		//Map<String, String> map_img_url = source.getmap_img_url_stockMap();
		Map<String, byte[]> map = source.getmap();
		try {
			String a=source.getURL();
			a=a.replace("//", "");
			a=a.replace("/", "");
			a=a.replace(".", "");
			a=a.replace(":", "");
			//while (download_count < source.getIMG_I()-1) {

				if (source.getURL().matches("http.*?")) {
					File storeFile = new File("img\\" + a + ".jpg");
					FileOutputStream fileOutputStream = new FileOutputStream(
							storeFile);
					fileOutputStream.write(map.get("pc1"));
					fileOutputStream.close();
				} else {
//					System.out.println("Í¼Æ¬ "
//							+ source.IMGurlstock[download_count] + "ÏÂÔØÊ§°Ü£¬Ìø¹ý");
				}
				//download_count++;
			//}
			System.out.println("IMG Download Successfully, " + source.getIMG_I()
					+ " Images file");

		} catch (Exception e) {
			// e.printStackTrace();
//			System.out.println("Failure happens at"
//					+ source.IMGurlstock[download_count]);
			System.out.println("IMG Download Fail");
		}
		}
	}

}
