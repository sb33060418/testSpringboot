package com.sunbin.springboot.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sunbin.springboot.pojo.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * 自定义简单查询.
	 *
	 * @param name
	 *            the name
	 * @return the user
	 */
	User findByName(String name);

	/**
	 * 自定义复杂查询.
	 *
	 * @param name
	 *            the name
	 * @return the list
	 */
	List<User> findByNameLike(String name);

	/**
	 * 自定义分页查询.
	 *
	 * @param name
	 *            the name
	 * @return the list
	 */
	Page<User> findByNameLike(String name, Pageable pageable);

	/**
	 * 自定义SQL查询.
	 *
	 * @param name
	 *            the name
	 * @return the list
	 */
	@Query("select u from User u where u.name like ?1%")
	List<User> queryByNameLike(String name);

}
