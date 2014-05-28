package com.hetzer.crawlite.processers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Write_HTML extends AbstractWrite {
	public void doit(CrawlableURL source) {
		if((source.getURL().matches(".*?com"))||(source.getURL().matches(".*?net"))||(source.getURL().matches(".*?org"))||(source.getURL().matches(".*?edu"))||(source.getURL().matches(".*?gov")))
		{
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
			source.setwebsource(sb.toString());
			System.out.println("Websource write Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error,Websource write fail");
			
		}
		}
	}

}
