package com.hetzer.crawlite.processers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Write_JavaScript extends AbstractWrite {
	public void doit(CrawlableURL source) {
		if (source.getURL().matches(".*?js")) {
			try {
				URL url_js = new URL(source.getURL());
				BufferedReader br_js = new BufferedReader(
						new InputStreamReader(url_js.openStream()));
				String s_js = "";
				StringBuffer sb_js = new StringBuffer("");
				while ((s_js = br_js.readLine()) != null) {
					sb_js.append(s_js); // ��֤������һ�ж�
				}
				br_js.close();
				source.setJS_Flag(true);
				;
				source.setContent(sb_js.toString());
				System.out.println("Javascript Write Successfully, ");
			} catch (Exception e) {
				System.out.println("Javascript Write Fail");
			}
		}
	}

}
