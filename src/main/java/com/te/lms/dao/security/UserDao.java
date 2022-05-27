package com.te.lms.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;
import com.te.lms.entity.security.User;

public interface UserDao extends JpaRepository<User, Integer> {

	public User findByUserName(String userName);
}
