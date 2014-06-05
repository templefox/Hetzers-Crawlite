package com.hetzer.crawlite.processers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class HTMLFetcher extends AbstractFetcher {

	public void fetch(CrawlableURL source) {
		if (((source.getURL().matches("http.*?com.*?"))
				|| (source.getURL().matches(".*?net.*?"))
				|| (source.getURL().matches(".*?org.*?"))
				|| (source.getURL().matches(".*?edu.*?")) || (source.getURL()
				.matches(".*?gov.*?")))
				&& (!source.getURL().matches(".*?gif"))
				&& (!source.getURL().matches(".*?jpg"))
				&& (!source.getURL().matches(".*?png"))
				&& (!source.getURL().matches(".*?js"))
				&& (!source.getURL().matches(".*?css"))) {
			if (source.getCookie() != null) {
				// String s =
				// "http://user.goodjobs.cn/dispatcher.php/module/Resume/action/Preview";
				// 重新打开一个连接
				try {
					URL url = new URL(source.getURL());
					HttpURLConnection resumeConnection = (HttpURLConnection) url
							.openConnection();
					// 发送cookie信息上去，以表明自己的身份，否则会被认为没有权限
					resumeConnection.setRequestProperty("Cookie",
							source.getCookie());

					resumeConnection.connect();
					InputStream urlStream = resumeConnection.getInputStream();
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(urlStream));
					String ss = null;
					String total = "";
					while ((ss = bufferedReader.readLine()) != null) {
						total += ss;
					}
					source.putString(CrawlableURL.WEB_SOURCE, total.toString());
					System.out.println("Websource write Successfully");
					bufferedReader.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("error,Websource write fail");
				}

			} else {

				try {
					URL url = new URL(source.getURL());
					BufferedReader br = new BufferedReader(
							new InputStreamReader(url.openStream()));
					String s = "";
					StringBuffer sb = new StringBuffer("");
					while ((s = br.readLine()) != null) {
						sb.append(s);
					}
					br.close();
					// XXX source.setwebsource(sb.toString());
					source.putString(CrawlableURL.WEB_SOURCE, sb.toString());
					System.out.println("Websource write Successfully");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("error,Websource write fail");

				}
			}
		}
	}

}
