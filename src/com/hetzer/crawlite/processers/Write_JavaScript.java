package com.hetzer.crawlite.processers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Write_JavaScript extends AbstractWrite {
	public void doit(CrawlableURL source) 
	{
		if(source.getURL().matches(".*?js"))
		{
		try{
		//int download_count_js=0;
		//Map<String, String> map_js_url = source.getMap_js_url_stockMap();
		Map<String, String> map_js = new HashMap<String,String>();
		//while(download_count_js<source.getJS_I())
		//{
			
			URL url_js = new URL(source.getURL());
            BufferedReader br_js = new BufferedReader(new InputStreamReader(url_js.openStream()));  
            String s_js = "";  
            StringBuffer sb_js = new StringBuffer("");  
            while ((s_js = br_js.readLine()) != null) {  
                sb_js.append(s_js);                       //保证其往下一行读
            }  
            br_js.close();  
            map_js.put("js1", sb_js.toString());                           
            //download_count_js++;
			//}
            source.setJS_I(1);
		source.setMap_js(map_js);
		System.out.println("Javascript Write Successfully, "+source.getJS_I()+" JS file");
		}
			catch(Exception e)
			{
				System.out.println("Javascript Write Fail");
			}
		}
	}

}
