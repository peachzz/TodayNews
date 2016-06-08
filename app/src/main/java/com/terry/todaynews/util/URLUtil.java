package com.terry.todaynews.util;


import com.terry.todaynews.contain.Constraint;

public class URLUtil {

	public static final String NEWS_LIST_URL_SHIZHENG = "http://www.jiemian.com/lists/34.html";
	public static final String NEWS_LIST_URL_MINGSHENG = "http://www.jiemian.com/lists/72.html";
	public static final String NEWS_LIST_URL_KEJI = "http://www.jiemian.com/lists/6.html";
	public static final String NEWS_LIST_URL_WANWU = "http://www.jiemian.com/lists/66.html";

	/**
	 * 
	 * @return
	 */
	public static String getNewsItemTypeUrl(int type) {

		String urlString = "";

		switch (type) {
		case Constraint.NEWS_TYPE_MINGSHENG:
			urlString = NEWS_LIST_URL_MINGSHENG;
			break;
		case Constraint.NEWS_TYPE_SHIZHENG:
			urlString = NEWS_LIST_URL_SHIZHENG;
			break;
		case Constraint.NEWS_TYPE_WANWU:
			urlString = NEWS_LIST_URL_WANWU;
			break;
		case Constraint.NEWS_TYPE_KEJI:
			urlString = NEWS_LIST_URL_KEJI;
			break;
			default :
			break;
		}
		return urlString;
	}
}