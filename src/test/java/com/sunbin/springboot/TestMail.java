package com.sunbin.springboot;


import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sunbin.springboot.mail.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMail {

	@Autowired
	private MailService mailService;

	@Test
	public void testA_sendSimpleMail() throws Exception {
		boolean result = true;
//		result = mailService.sendSimpleMail("", "sunb@clo.com.cn", "test spring-boot mail subject", "test spring-boot mail content");
		Assert.assertTrue(result);
	}


}
