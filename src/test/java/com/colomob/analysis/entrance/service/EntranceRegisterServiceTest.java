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

import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.colomob.analysis.entrance.dto.RegisterDTO;

/**
 * @author baowp
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/analysisEntrance.xml")
public class EntranceRegisterServiceTest {

	@Autowired
	private EntranceRegisterService entranceRegisterService;

	@Test
	public void testSend() {
		RegisterDTO register = getRegister();
		JSONObject json = entranceRegisterService.send(register);
		System.out.println(json);
	}

	private RegisterDTO getRegister() {
		RegisterDTO register = new RegisterDTO();
		register.setUserid("userid");
		register.setUsername("username");
		register.setUdid("udid");
		register.setGuestId("guestId");
		register.setChannelAccountId("channelAccountId");
		register.setRegChannel("regChannel");
		register.setEmail("a@a.a");
		register.setMobile("18612345678");
		register.setRegClientType("regClientType");
		register.setRegisterType(1);
		register.setAccountType(2);
		register.setStatus(0);
		return register;
	}
}
