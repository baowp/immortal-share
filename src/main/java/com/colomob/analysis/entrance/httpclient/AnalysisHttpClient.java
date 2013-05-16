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

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;

import com.colomob.analysis.entrance.util.BeanUtil;

/**
 * @author baowp
 * 
 */
@Component
public class AnalysisHttpClient {

	private final org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(this.getClass());

	public JSONObject request(String url, Object sendDTO) {
		HttpClient httpclient = new DefaultHttpClient();

		Map<String, Object> param = BeanUtil.bean2map(sendDTO);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			if (entry.getValue() != null) {
				String key = entry.getKey();
				String value = entry.getValue().toString();
				formparams.add(new BasicNameValuePair(key, value));
			}
		}
		HttpPost httppost = new HttpPost(url);
		try {
			UrlEncodedFormEntity form = new UrlEncodedFormEntity(formparams,
					"UTF-8");
			httppost.setEntity(form);

			HttpResponse response = null;
			try {
				response = httpclient.execute(httppost);
			} catch (Exception e) {
				// TODO write dto to disk
				throw e;
			}
			HttpEntity entity = response.getEntity();

			int statusCode = response.getStatusLine().getStatusCode();

			if (entity != null && statusCode == HttpStatus.SC_OK) {
				InputStream instream = entity.getContent();
				InputStreamReader reader = new InputStreamReader(instream);
				StringBuilder sb = new StringBuilder();
				int c;
				try {
					while ((c = reader.read()) > -1)
						sb.append((char) c);
				} finally {
					reader.close();
				}
				JSONObject json = JSONObject.fromObject(sb.toString());
				return json;
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return null;
	}
}
