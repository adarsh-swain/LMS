package com.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {
	
	private String batch;
	private String mobile;
	private String name;
	private String rollNo;
	private String title;
	private String branchName;
	
}
