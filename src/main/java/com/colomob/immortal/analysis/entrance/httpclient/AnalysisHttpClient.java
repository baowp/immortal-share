/**
 * Project: analysis-entrance
 * 
 * File Created at May 13, 2013
 * $Id$Corporation
 * 
 * Copyright 2013-2015 Colomob.com Corporation Limited.
 * All rights reserved.
 */
package com.colomob.immortal.analysis.entrance.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;

/**
 * @author baowp
 * 
 */
@Component
public class AnalysisHttpClient {

	public void request(String url, Object sendDTO) {
		HttpClient httpclient = new DefaultHttpClient();

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("userid", "value1"));
		formparams.add(new BasicNameValuePair("username", "用户名"));
		HttpPost httppost = new HttpPost(url);
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
					while ((c = reader.read()) > -1)
						System.out.print((char) c);
				} finally {
					instream.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}
}
