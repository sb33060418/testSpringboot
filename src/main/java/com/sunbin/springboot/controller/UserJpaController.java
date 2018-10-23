package com.sunbin.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbin.springboot.pojo.User;
import com.sunbin.springboot.repo.UserRepository;

@RestController
public class UserJpaController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/jpa/users", method = { RequestMethod.GET })
	public List<User> usersGet() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@RequestMapping(value = "/jpa/users", method = { RequestMethod.POST })
	public String usersPost(User user) {
		List<User> users = userRepository.findAll();
		int index = 1;
		if (!users.isEmpty()) {
			index = users.get(users.size() - 1).getId() + 1;
		}
		user.setId(index);
		userRepository.save(user);
		return "{\"status\": \"y\"}";
	}

	@RequestMapping(value = "/jpa/user/{id}", method = { RequestMethod.GET })
	public User userGet(@PathVariable("id") Integer id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			return user;
		}
		return null;
	}

	@RequestMapping(value = "/jpa/user/{id}", method = { RequestMethod.PUT })
	public String userPut(User user) {
		userRepository.save(user);
		return "{\"status\": \"y\"}";
	}

	@RequestMapping(value = "/jpa/user/{id}", method = { RequestMethod.DELETE })
	public String userDelete(@PathVariable("id") Integer id) {
		userRepository.deleteById(id);
		return "{\"status\": \"y\"}";
	}

	@RequestMapping(value = "/jpa/users/name/{name}", method = { RequestMethod.GET })
	public User userGetByName(@PathVariable("name") String name) {
		User user = userRepository.findByName(name);
		return user;
	}

	@RequestMapping(value = "/jpa/users/nameLike/{name}", method = { RequestMethod.GET })
	public List<User> usersGetByNameLike(@PathVariable("name") String name) {
		List<User> users = userRepository.findByNameLike(name + "%");
		return users;
	}

	@RequestMapping(value = "/jpa/users/page", method = { RequestMethod.GET })
	public Page<User> usersPage(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {
		Sort sort = new Sort(Direction.ASC, "id");
		if (page == null) {
			page = 0;
		}
		if (size == null) {
			size = 3;
		}
		Pageable pageable = new PageRequest(page, size, sort);
		Page<User> pageUser = userRepository.findAll(pageable);
		return pageUser;
	}

	@RequestMapping(value = "/jpa/users/nameLike/{name}/page", method = { RequestMethod.GET })
	public Page<User> usersGetByNameLikePage(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,@PathVariable("name") String name) {
		Sort sort = new Sort(Direction.ASC, "id");
		if (page == null) {
			page = 0;
		}
		if (size == null) {
			size = 3;
		}
		Pageable pageable = new PageRequest(page, size, sort);
		Page<User> pageUser = userRepository.findByNameLike(name + "%",pageable);
		return pageUser;
	}
}
