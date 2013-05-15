/**
 * Project: analysis-entrance
 * 
 * File Created at May 11, 2013
 * $Id$Corporation
 * 
 * Copyright 2013-2015 Colomob.com Corporation Limited.
 * All rights reserved.
 */
package com.colomob.immortal.analysis.entrance.service.impl;

import org.springframework.stereotype.Service;

import com.colomob.immortal.analysis.entrance.annotations.RequestUri;
import com.colomob.immortal.analysis.entrance.dto.RegisterDTO;
import com.colomob.immortal.analysis.entrance.service.EntranceRegisterService;

/**
 * @author baowp
 * 
 */
@Service
public class EntranceRegisterServiceImpl implements EntranceRegisterService {

	@RequestUri("/user/register")
	public int send(RegisterDTO register) {
		System.out.println("self register");
		return 0;
	}

	public int setout(RegisterDTO register) {
		System.out.println("self setout");
		return 0;
	}

}
