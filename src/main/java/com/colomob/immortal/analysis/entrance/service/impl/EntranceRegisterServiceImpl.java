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

import com.colomob.immortal.analysis.entrance.dto.RegisterDTO;
import com.colomob.immortal.analysis.entrance.service.EntranceRegisterService;

/**
 * @author baowp
 * 
 */
@Service
public class EntranceRegisterServiceImpl implements EntranceRegisterService {

	public int send(RegisterDTO register) {
		System.out.println("self register");
		return 0;
	}

}
