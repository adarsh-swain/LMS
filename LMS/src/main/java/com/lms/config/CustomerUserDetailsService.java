package com.lms.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.lms.entity.User;
import com.lms.repo.UserRepo;

@Component
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	

	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Optional<User> user = userRepo.findByUsername(username);
	        if (user.isPresent()) {
	            return new UserInfoConfig(user.get());
	        }

//	        Optional<Agency> agency = agencyRepo.findByUsername(username);
//	        if (agency.isPresent()) {
//	            return new UserInfoConfig(agency.get());
//	        }
//
//	        Optional<Employee> employee = employeeRepo.findByUsername(username);
//	        if (employee.isPresent()) {
//	            return new UserInfoConfig(employee.get());
//	        }

	        throw new UsernameNotFoundException("User not found with username: " + username);
	    }
		
}
