package com.sunbin.springboot;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.sunbin.springboot.pojo.User;
import com.sunbin.springboot.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMybatisRedis {

	@Autowired
	private UserService userService;

	@Test
	public void testA_FindAll() throws Exception {
		List<User> users = userService.findAll();
		System.out.println("findAll:" + users);
		Assert.assertNotEquals(users.size(), 0);
	}

	@Test
	public void testB_FindById() throws Exception {
		User user = userService.findById(1);
		System.out.println("findById:" + user);
		Assert.assertNotNull(user);
	}

	@Test
	public void testC_SaveUser() throws Exception {
		List<User> users = userService.findAll();
		int index = 1;
		if (!users.isEmpty()) {
			index = users.get(users.size() - 1).getId() + 1;
		}
		User user = new User(index, "" + (char) ('a' + index - 1));
		System.out.println("saveUser:" + user);
		System.out.println(userService.saveUser(user));
		user = userService.findById(index);
		Assert.assertNotNull(user);
	}

	@Test
	public void testD_UpdateUser() throws Exception {
		List<User> users = userService.findAll();
		Assert.assertNotEquals(users.size(), 0);
		User user = users.get(users.size() - 1);
		user.setName(user.getName() + user.getName());
		System.out.println("updateUser:" + user);
		System.out.println(userService.updateUser(user));
		Assert.assertNotNull(userService.findById(user.getId()));
	}

	@Test
	public void testE_DeleteById() throws Exception {
		List<User> users = userService.findAll();
		Assert.assertNotEquals(users.size(), 0);
		User user = users.get(users.size() - 1);
		System.out.println("deleteById:" + user);
		System.out.println(userService.deleteById(user.getId()));
		Assert.assertNull(userService.findById(user.getId()));
	}

}
