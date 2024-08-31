package com.lms.service;

import java.util.List;

import com.lms.entity.Loan;

public interface LoanService {

	Loan saveLoan(Loan loan);

	Loan getLoanById(Long id);

	 List<Object[]> getAllLoans();

	  public void deleteLoanById(Long loanId);

}
