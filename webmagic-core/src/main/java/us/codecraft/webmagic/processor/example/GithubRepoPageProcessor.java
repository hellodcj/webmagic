package us.codecraft.webmagic.processor.example;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.3.2
 */
public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(0);

    @Override
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+\\-?\\w+/\\w+\\-?\\w+)").all());
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+\\-?\\w+)").all());
    	List list  =  new ArrayList();
    	list.add("https://github.com/code4craft/jsoup-learning");
    	page.addTargetRequests(list);
        
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft").addPipeline(new FilePipeline("d://")).thread(5).run();
    }
}



/**
 * 
 * [Request{url='https://github.com/code4craft/followers', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/stars/code4craft', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/code4craft/following', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/code4craft/webmagic', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/code4craft/netty-learning', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/code4craft/blackhole', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/code4craft/tiny-spring', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/code4craft/jsoup-learning', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/webmagic-io/docs', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/webmagic-io/webmagic-io', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/webmagic-io/jobhunter', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/jhy/jsoup', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/fuluchii/avatar-iBatis', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/code4craft/code4craft', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/site/terms', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/site/privacy', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/join', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/login', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/explore', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/features', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/blog', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/stars', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/webmagic-io', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/jhy', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/fuluchii', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/about', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/site', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/security', method='null', extras=null, priority=0}, 
	Request{url='https://github.com/contact', method='null', extras=null, priority=0}]
*/
