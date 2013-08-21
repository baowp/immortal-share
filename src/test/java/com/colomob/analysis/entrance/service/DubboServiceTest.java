/**
 * Project: immortal-share
 * 
 * File Created at Aug 12, 2013
 * $Id$Corporation
 * 
 * Copyright 2013-2015 Colomob.com Corporation Limited.
 * All rights reserved.
 */
package com.colomob.analysis.entrance.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author baowp
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/analysisEntrance.xml")
public class DubboServiceTest {

	@Autowired
	private DubboService dubboService;

	@Test
	public void testHello() {
		// String name = dubboService.hello("jack");
		System.out.println("");
	}
}
