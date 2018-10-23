package com.sunbin.springboot.service;

import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sunbin.springboot.mapper.UserMapper;
import com.sunbin.springboot.pojo.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public List<User> findAll() {
		return userMapper.findAll();
	}

	/**
	 * @Cacheable 应用到读取数据的方法上，先从缓存中读取，如果没有再从DB获取数据，然后把数据添加到缓存中 unless
	 *            表示条件表达式成立的话不放入缓存
	 * @param id
	 * @return
	 */
	@Cacheable(value = "user", key = "#id", unless = "#result eq null")
	public User findById(int id) {
		return userMapper.findById(id);
	}

	/**
	 * @CachePut 应用到写数据的方法上，如新增/修改方法，调用方法时会自动把相应的数据放入缓存
	 * @param user
	 * @return
	 */
	@CachePut(value = "user", key = "#user.id", unless = "#result eq null")
	public User saveUser(User user) {
		if(userMapper.saveUser(user)>0){
			return user;
		}
		return null;
	}

	@CachePut(value = "user", key = "#user.id", unless = "#result eq null")
	public User updateUser(User user) {
		if(userMapper.updateUser(user)>0){
			return user;
		}
		return null;
	}

	/**
	 * @CacheEvict 应用到删除数据的方法上，调用方法时会从缓存中删除对应key的数据
	 * @param username
	 * @return
	 */
	@CacheEvict(value = "user", key = "#id", condition = "#result eq 1")
	public int deleteById(int id) {
		return userMapper.deleteById(id);
	}
}
