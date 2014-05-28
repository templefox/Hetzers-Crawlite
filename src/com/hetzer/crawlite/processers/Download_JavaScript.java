package com.hetzer.crawlite.processers;

import java.io.FileWriter;
import java.util.Map;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Download_JavaScript extends Download_Abstract {
	public void doit(CrawlableURL source)
	{
		if(source.getJS_I()!=0)
		{
	try{
	//int download_count_js=0;
		String a=source.getURL();
		a=a.replace("//", "");
		a=a.replace("/", "");
		a=a.replace(".", "");
		a=a.replace(":", "");
		
	Map<String, String> map_js = source.getMap_js();
	//while(download_count_js<source.getJS_I()-1)
	//{
        FileWriter fw_js = new FileWriter("js\\"+a+".js");      
        fw_js.write(map_js.get("js1"));
        fw_js.close();                              
    //    download_count_js++;
        //System.out.println(source.JSurlstock[download_count_js]);
        //System.out.println(source.JSurlstock[download_count_js].matches("http.*?"));
	//	}
	System.out.println("Javascript Download Successfully, "+source.getJS_I()+" JS file");
	}
		catch(Exception e)
		{
			System.out.println("Javascript Write Fail");
		}
		}
	}

}
