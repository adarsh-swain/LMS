package com.lms.config;

public class AppConstants {
	
	public static final Long ADMIN_ID = 101L;
	public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60;
	public static final String[] PUBLIC_URLS = { "/api/register/**", "/api/login/**" };
	public static final String[] ADMIN_URLS = { "/LMS/**" };

}
