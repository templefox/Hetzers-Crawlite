package com.hetzer.crawlite.processers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Write_HTML extends AbstractWrite {

	public void doit(CrawlableURL source) {
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
			try {
				URL url = new URL(source.getURL());
				BufferedReader br = new BufferedReader(new InputStreamReader(
						url.openStream()));
				String s = "";
				StringBuffer sb = new StringBuffer("");
				while ((s = br.readLine()) != null) {
					sb.append(s);
				}
				br.close();
				//XXX source.setwebsource(sb.toString());
				source.putString(CrawlableURL.WEB_SOURCE, sb.toString());
				System.out.println("Websource write Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("error,Websource write fail");

			}
		}
	}

}
