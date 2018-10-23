package com.sunbin.springboot;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.sunbin.springboot.mapper.UserMapper;
import com.sunbin.springboot.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMybatis {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testA_FindAll() throws Exception {
		List<User> users = userMapper.findAll();
		System.out.println("findAll:" + users);
		Assert.assertNotEquals(users.size(), 0);
	}

	@Test
	public void testB_FindById() throws Exception {
		User user = userMapper.findById(1);
		System.out.println("findById:" + user);
		Assert.assertNotNull(user);
	}

	@Test
	public void testC_SaveUser() throws Exception {
		List<User> users = userMapper.findAll();
		int index = 1;
		if (!users.isEmpty()) {
			index = users.get(users.size() - 1).getId() + 1;
		}
		User user = new User(index, "" + (char) ('a' + index - 1));
		System.out.println("saveUser:" + user);
		System.out.println(userMapper.saveUser(user));
		Assert.assertNotNull(userMapper.findById(index));
	}

	@Test
	public void testD_UpdateUser() throws Exception {
		List<User> users = userMapper.findAll();
		Assert.assertNotEquals(users.size(), 0);
		User user = users.get(users.size() - 1);
		user.setName(user.getName() + user.getName());
		System.out.println("updateUser:" + user);
		System.out.println(userMapper.updateUser(user));
		Assert.assertNotNull(userMapper.findById(user.getId()));
	}

	@Test
	public void testE_DeleteById() throws Exception {
		List<User> users = userMapper.findAll();
		Assert.assertNotEquals(users.size(), 0);
		User user = users.get(users.size() - 1);
		System.out.println("deleteById:" + user);
		System.out.println(userMapper.deleteById(user.getId()));
		Assert.assertNull(userMapper.findById(user.getId()));
	}

	@Test
	public void testF_FindAllMap() throws Exception {
		List<Map> users = userMapper.findAllMap();
		System.out.println("findAllMap:" + users);
		Assert.assertNotEquals(users.size(), 0);
	}

	@Test
	public void testG_FindAllInXml() throws Exception {
		List<User> users = userMapper.findAllInXml();
		System.out.println("findAllInXml:" + users);
		Assert.assertNotEquals(users.size(), 0);
	}

}
