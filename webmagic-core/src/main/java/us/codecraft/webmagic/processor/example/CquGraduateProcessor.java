package us.codecraft.webmagic.processor.example;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class CquGraduateProcessor implements PageProcessor{
	public static final String URL_LIST = "http://graduate\\.cqu\\.edu\\.cn/fourthIndex\\.do\\?method=main&classId=0&viewPage=\\d+&action=next";
	
	public static final String URL_POST = "newsDetail\\.do\\?newsinfoid=\\w+";
	
	private Site site = Site
            .me()
            .setDomain("graduate.cqu.edu.cn")
            .setSleepTime(3000);
	
	
	@Override
	public void process(Page page) {
		 //列表页
        if (page.getUrl().regex(URL_LIST).match()) {
            //page.addTargetRequests(page.getHtml().xpath("//div[@class=\"articleList\"]").links().regex(URL_POST).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
           // page.putField("url", page.getHtml().links().regex(URL_POST).toString());
            List<Selectable> list = page.getHtml().xpath("//td[@class=\"bodybg\"]//ul").nodes();
            List<Map<String,Object>> file = new  ArrayList<Map<String,Object>>();
            for (Selectable s :list){
            		Map map = new HashMap();
                	map.put("url", site.getDomain()+"/"+s.links().regex(URL_POST).toString());
                	map.put("title", s.xpath("//li//a/text()"));
                	file.add(map);
            }
            
            page.putField("file", file);
        }
		
	}

	@Override
	public Site getSite() {
		return site;
	}
	
	public static void main(String[] args) {
        Spider.create(new CquGraduateProcessor()).addUrl("http://graduate.cqu.edu.cn/fourthIndex.do?method=main&classId=0&viewPage=0&action=next")
                .addPipeline(new FilePipeline("d://")).run();
    }
}
