/**
 * Project: analysis-entrance
 * 
 * File Created at May 13, 2013
 * $Id$Corporation
 * 
 * Copyright 2013-2015 Colomob.com Corporation Limited.
 * All rights reserved.
 */
package com.colomob.analysis.entrance.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

/**
 * @author baowp
 * 
 */
public class SampleHttpClientTest {

	@Test
	public void testGet() throws ClientProtocolException, IOException,
			URISyntaxException {
		HttpClient httpclient = new DefaultHttpClient();
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("localhost").setPort(8080)
				.setPath("/immortal/register");
		builder.setParameter("userid", "value1")
				.setParameter("username", "用户名");
		URI uri = builder.build();

		HttpGet httpget = new HttpGet(uri);

		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		for (Header header : response.getAllHeaders()) {
			System.out.println(header);
		}
		System.out.println(response.getStatusLine());
		System.out.println(HttpStatus.SC_OK);

		if (entity != null) {
			InputStream instream = entity.getContent();
			InputStreamReader reader = new InputStreamReader(instream);
			try {
				while (instream.available() > 0) {
					System.out.print((char) instream.read());
				}
				int c;
				StringBuilder sb = new StringBuilder();
				while ((c = reader.read()) > -1)
					sb.append((char) c);
				System.out.println(sb);
			} finally {
				reader.close();
			}
		}
	}

	@Test
	public void testPost() {
		HttpClient httpclient = new DefaultHttpClient();

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("userid", "value1"));
		formparams.add(new BasicNameValuePair("username", "用户名"));
		HttpPost httppost = new HttpPost(
				"http://localhost:8080/immortal/register");
		try {
			UrlEncodedFormEntity form = new UrlEncodedFormEntity(formparams,
					"UTF-8");
			httppost.setEntity(form);

			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			for (Header header : response.getAllHeaders()) {
				System.out.println(header);
			}
			System.out.println(response.getStatusLine());

			if (entity != null) {
				InputStream instream = entity.getContent();
				InputStreamReader reader = new InputStreamReader(instream);
				try {
					while (instream.available() > 0) {
						System.out.print((char) instream.read());
					}
					int c;
					StringBuilder sb = new StringBuilder();
					while ((c = reader.read()) > -1)
						sb.append((char) c);
					System.out.println(sb);
				} finally {
					reader.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}
}
