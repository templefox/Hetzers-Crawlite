package com.hetzer.crawlite.processers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Write_CSS extends AbstractWrite {
	public void doit(CrawlableURL source) {
		if (source.getURL().matches(".*?css")) {
			try {
				URL url_css = new URL(source.getURL());
				BufferedReader br_css = new BufferedReader(
						new InputStreamReader(url_css.openStream()));
				String s_css = "";
				StringBuffer sb_css = new StringBuffer("");
				while ((s_css = br_css.readLine()) != null) {
					sb_css.append(s_css); // 保证其往下一行读

				}
				br_css.close();
				source.setCSS_Flag(true);
				source.setContent(sb_css.toString());
				System.out.println("CSS Write Successfully");
			} catch (Exception e) {
				System.out.println("CSS Write Fail");
			}
		}
	}

}
