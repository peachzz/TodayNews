package com.terry.todaynews.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class DataUtil {

	/**
	 * 返回链接地址的html数据
	 * 
	 * @return
	 * @throws CommonException
	 */

	public static String doGet(String uString) throws CommonException {

		StringBuffer sBuffer = new StringBuffer();
		try {
			URL url = new URL(uString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con.setDoInput(true);
			con.setDoOutput(true);

			if (con.getResponseCode() == 200) {
				InputStreamReader isr = new InputStreamReader(con.getInputStream(), "utf-8");
				int len = 0;
				char[] ch = new char[1024];
				while ((len = isr.read(ch)) != -1) {
					sBuffer.append(ch, 0, len);
				}
				isr.close();
			} else {
				throw new CommonException("访问网络失败！");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sBuffer.toString();
	}
}
