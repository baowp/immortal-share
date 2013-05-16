/**
 * Project: analysis-entrance
 * 
 * File Created at May 11, 2013
 * $Id$Corporation
 * 
 * Copyright 2013-2015 Colomob.com Corporation Limited.
 * All rights reserved.
 */
package com.colomob.analysis.entrance.service.impl;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.colomob.analysis.entrance.annotations.RequestUri;
import com.colomob.analysis.entrance.dto.RegisterDTO;
import com.colomob.analysis.entrance.service.EntranceRegisterService;

/**
 * @author baowp
 * 
 */
@Service
public class EntranceRegisterServiceImpl implements EntranceRegisterService {

	@RequestUri("/immortal/register")
	public JSONObject send(RegisterDTO register) {
		System.out.println("self register");
		return null;
	}

}
