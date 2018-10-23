package com.sunbin.springboot;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.sunbin.springboot.pojo.User;
import com.sunbin.springboot.repo.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSchedule {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testA_FindAll() throws Exception {
		List<User> users = userRepository.findAll();
		System.out.println("findAll:" + users);
		Assert.assertNotEquals(users.size(), 0);
	}

	@Test
	public void testB_FindById() throws Exception {
		Optional<User> userOptional = userRepository.findById(1);
		User user = userOptional.get();
		System.out.println("findById:" + user);
		Assert.assertNotNull(user);
	}

	@Test
	public void testC_QueryByNameLike() throws Exception {
		List<User> users = userRepository.queryByNameLike("a");
		System.out.println("queryByNameLike:" + users);
		Assert.assertNotEquals(users.size(), 0);
	}

}
