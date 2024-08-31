package com.lms.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lms.entity.User;

public class UserInfoConfig implements UserDetails {

	private static final long serialVersionUID = 1L;

//	private int id;
//	private String uname;
//	private String role;
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;

	public UserInfoConfig(User user) {
		username = user.getUsername();
		password = user.getPassword();
		this.authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName()))
				.collect(Collectors.toList());
	}

//	public UserInfoConfig(Agency agency) {
//		 id = agency.getAgencyId();
//		 uname = agency.getAgencyName();
//		username = agency.getUsername();
//		password = agency.getPassword();
//		this.authorities = agency.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName()))
//				.collect(Collectors.toList());
//	}
//
//	public UserInfoConfig(Employee employee) {
//		id = employee.getEmpId();
//		uname = employee.getFirstName();
//		username = employee.getUsername();
//		password = employee.getPassword();
//		this.authorities = employee.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName()))
//				.collect(Collectors.toList());
//	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
