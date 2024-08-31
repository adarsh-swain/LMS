package com.lms.serviceImpl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.config.AppConstants;
import com.lms.entity.Role;
import com.lms.entity.User;
import com.lms.repo.RoleRepo;
import com.lms.repo.UserRepo;


@Service
public class UserService {

	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	public User registerUser(User user) {
		Role role = roleRepo.findById(AppConstants.ADMIN_ID).orElseThrow(() -> new RuntimeException("Admin role not found"));
	    if (user.getRoles() == null) {
	        user.setRoles(new HashSet<>());
	    }
	    user.getRoles().add(role);
	    User saveUser = userRepo.save(user);
	    return saveUser;
	}


}
