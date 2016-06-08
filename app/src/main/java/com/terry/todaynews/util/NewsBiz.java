package com.terry.todaynews.util;

import com.terry.todaynews.bean.News;
import com.terry.todaynews.bean.NewsDto;
import com.terry.todaynews.bean.NewsItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class NewsBiz {

    public static NewsBiz mNewsBiz ;

    /**
     * 解析NewsItem的数据 返回List<NewsItem>
     */

     public static NewsBiz getNewsBizInstance() {

        if (mNewsBiz == null) {
            mNewsBiz = new NewsBiz();
        }
        return mNewsBiz;
    }

    public List<NewsItem> getNewsItem(int newsType) throws CommonException {

        List<NewsItem> newsItems = new ArrayList<>();

        NewsItem newsItem = null;

        String urlString = URLUtil.getNewsItemTypeUrl(newsType);

        String htmlString = DataUtil.doGet(urlString);

        Document doc = Jsoup.parse(htmlString);

        Elements items = doc.select(".news-view").select(".left");

        for (int i = 0; i < items.size(); i++) {
            try {
                newsItem = new NewsItem();
                newsItem.setType(newsType);

                Element item_ele = items.get(i);

                Element header_ele = item_ele.getElementsByClass("news-header").get(0);
                Element h3_title_ele = header_ele.getElementsByTag("h3").get(0);
                Element a_h3_title_ele = h3_title_ele.child(0);

                String link = a_h3_title_ele.attr("href");
                newsItem.setLink(link);// link
                newsItem.setTitle(a_h3_title_ele.text());// title

                // 标题没有图片
                Element img_ele = item_ele.getElementsByClass("news-img").get(0);
                Element a_img_ele = img_ele.child(0);
                Element src_ele = a_img_ele.getElementsByTag("img").get(0);
                newsItem.setImageLink(src_ele.attr("src"));

//				newsItem.setSummary(getSummary(link).getSummary());
//				newsItem.setDate(getSummary(link).getDate());

                Elements footer_ele = item_ele.getElementsByClass("news-footer");
                footer_ele.select("span.comment").remove();
                footer_ele.select("a").remove();
//                    Element date_ele = p_footer_ele.select("span.date").get(0);// update
                if (footer_ele.size() > 0) {
                    newsItem.setUpDate(footer_ele.text());
                }

            } catch (IndexOutOfBoundsException exception) {

            }
            newsItems.add(newsItem);
        }

        return newsItems;
    }

    private News getSummary(String url) {
        News news = new News();
        try {
            String htmlString = DataUtil.doGet(url);
            Document doc = Jsoup.parse(htmlString);

            Element view_ele = doc.select(".article-view").get(0);
            Element header_ele = view_ele.select("div.article-header").get(0);
            Element h4_header_ele = header_ele.child(1);
            news.setSummary(h4_header_ele.text()); // summary

            Element p_ele = view_ele.select("p.info-s").get(0);
            Element date_ele = p_ele.getElementsByClass("date").get(0);
            news.setDate(date_ele.text());

        } catch (CommonException e) {
            e.printStackTrace();
        }

        return news;
    }

    public NewsDto getNews(String url) throws CommonException {

        NewsDto newsDto = new NewsDto();
        List<News> newsList = new ArrayList<>();

        String htmlString = DataUtil.doGet(url);

        Document doc = Jsoup.parse(htmlString);

        Element view_ele = doc.select(".article-view").get(0);

        Element header_ele = view_ele.select("div.article-header").get(0);

        Element h1_header_ele = header_ele.child(0);
        News news = new News();
        news.setTitle(h1_header_ele.text());
        news.setType(News.NewsType.TITLE);
        newsList.add(news);

        Element h4_header_ele = header_ele.child(1);
        news = new News();
        news.setSummary(h4_header_ele.text()); // summary
        newsList.add(news);

        Element p_ele = view_ele.select("p.info-s").get(0);
        Element date_ele = p_ele.getElementsByClass("date").get(0);
        news = new News();
        news.setDate(date_ele.text());
        newsList.add(news);

        Element con_ele = view_ele.select("div.article-content").get(0);
        Elements childrenEle = con_ele.children();

        for (Element element : childrenEle) {
            Elements imgs_ele = element.getElementsByTag("img");

            if (imgs_ele.size() > 0) {
                for (Element img_ele : imgs_ele) {
                    if (img_ele.equals("")) {
                        continue;
                    }
                    news = new News();
                    news.setImageLink(img_ele.attr("src"));
                    newsList.add(news);
                }
            }
            imgs_ele.remove();

            if (element.text().equals("")) {
                continue;
            }

            news = new News();
            news.setType(News.NewsType.CONTENT);

            if (element.select("div.article-source").size() > 0) {
                continue;
            }

            news.setContent(element.outerHtml());
            newsList.add(news);
        }
        newsDto.setNewses(newsList);
        return newsDto;
    }
}
