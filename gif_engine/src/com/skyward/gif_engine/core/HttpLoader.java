package com.skyward.gif_engine.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * һ����URLת��Ϊ�������Ĺ�����
 * 
 * @author skyward
 *
 */
public class HttpLoader {
	public static InputStream getInputSteamFromUrl(String url) throws MalformedURLException, IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		return conn.getInputStream();
	}
}
