/**
 * Project: analysis-entrance
 * 
 * File Created at May 11, 2013
 * $Id$Corporation
 * 
 * Copyright 2013-2015 Colomob.com Corporation Limited.
 * All rights reserved.
 */
package com.colomob.analysis.entrance.service;

import com.colomob.analysis.entrance.dto.RegisterDTO;

/**
 * @author baowp
 * 
 */
public interface EntranceRegisterService {

	int send(RegisterDTO register);

	int setout(RegisterDTO register);
}
