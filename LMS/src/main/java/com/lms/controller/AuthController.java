package com.lms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.config.UserInfoConfig;
import com.lms.entity.AuthRequest;
import com.lms.entity.User;
import com.lms.filter.JwtUtil;
import com.lms.serviceImpl.UserService;


@RestController
@RequestMapping("/api")
public class AuthController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

//	@Autowired
//	private AgencyService agencyService;
//
//	@Autowired
//	private EmployeeService employeeService;

	@PostMapping("/register")
	public ResponseEntity<User> registerHandler(@RequestBody User user) throws UsernameNotFoundException {
		String encodedPass = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPass);
		User registerUser = userService.registerUser(user);
		return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> loginHandler(@RequestBody AuthRequest credentials) {
		UsernamePasswordAuthenticationToken authCredentials = new UsernamePasswordAuthenticationToken(
				credentials.getUsername(), credentials.getPassword());
		Authentication authentication = authenticationManager.authenticate(authCredentials);
		UserInfoConfig userDetails = (UserInfoConfig) authentication.getPrincipal();
		String token = jwtUtil.generateToken(userDetails);
		Map<String, Object> response = new HashMap<>();
		response.put("accessToken", token);
		return ResponseEntity.ok(response);
	}

}
