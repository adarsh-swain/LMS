package com.lms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lms.config.AppConstants;
import com.lms.entity.Role;
import com.lms.repo.RoleRepo;

@SpringBootApplication
public class LmsApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}
		
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public void run(String... args) throws Exception {
		try {
			Role adminRole = new Role();
			adminRole.setRoleId(AppConstants.ADMIN_ID);
			adminRole.setRoleName("ADMIN");
			List<Role> roles = List.of(adminRole);
			List<Role> savedRoles = roleRepo.saveAll(roles);
			savedRoles.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
