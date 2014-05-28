package com.hetzer.crawlite.processers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Write_CSS extends Write_Abstract {
	public void doit(CrawlableURL source)
	{
		if(source.getURL().matches(".*?css"))
		{
		try
		{
		//int download_count_css=0;
		//Map<String,String> map_css_url = source.getMap_css_url_stockMap();
		Map<String, String> map_css_result = new HashMap<String,String>();
		//while(download_count_css<source.getCSS_I())
		//{
			URL url_css = new URL(source.getURL());
            BufferedReader br_css = new BufferedReader(new InputStreamReader(url_css.openStream()));  
            String s_css = "";  
            StringBuffer sb_css = new StringBuffer("");  
            while ((s_css = br_css.readLine()) != null) {  
                sb_css.append(s_css);                       //保证其往下一行读
                
            }  
            map_css_result.put("CSS1", sb_css.toString());
            br_css.close();         
            source.setCSS_I(1);
           // download_count_css++;
		//}
		source.setMap_css(map_css_result);
		System.out.println("CSS Write Successfully, "+source.getCSS_I()+" CSS file");
		}
		catch (Exception e)
		{System.out.println("CSS Write Fail");}
	}
	}

}
