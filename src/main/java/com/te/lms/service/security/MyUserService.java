package com.te.lms.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.lms.dao.security.UserDao;
import com.te.lms.dto.security.MyUserDetails;
import com.te.lms.entity.security.User;

@Service
public class MyUserService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUserName(username);
		if (user != null) {
			return new MyUserDetails(user);
		} else {
			throw new UsernameNotFoundException("Incorrect Username");
		}
	}

}
