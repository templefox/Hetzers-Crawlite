package com.hetzer.crawlite.processers;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Download_CSS extends AbstractDownload{
	public boolean Download(CrawlableURL source,File dir)
	{
		if(source.getCSS_I()!=0)
		{
		Map<String, String> map_css = source.getMap_css();
		try
		{
			String a=source.getURL();
			a=a.replace("//", "");
			a=a.replace("/", "");
			a=a.replace(".", "");
			a=a.replace(":", "");
		//int download_count_css=0;
		//while(download_count_css<source.getCSS_I())
		//{
			//FileWriter fw_js = new FileWriter("js\\"+download_count_js+".js");   
            File file = new File(dir,"css");
            file.mkdir();
            FileWriter fw_css = new FileWriter("dir\\css\\"+a+".css");      
            fw_css.write(map_css.get("CSS1"));
            fw_css.close();                              
            //download_count_css++;
		//}
		System.out.println("CSS Download Successfully, "+source.getCSS_I()+" CSS file");
		}
		catch (Exception e)
		{System.out.println("CSS Download Fail");
		
		e.printStackTrace();}
		
		}
		return true;
	}

}
