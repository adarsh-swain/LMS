package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.entity.Loan;
import com.lms.service.LoanService;

@RestController
@RequestMapping("/LMS")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/allloans")
    public List<Object[]> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PostMapping("/saveloan")
    public Loan addLoan(@RequestBody Loan loan) {
        return loanService.saveLoan(loan);
    }

    @GetMapping("/loanbyid/{id}")
    public Loan getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }

    @DeleteMapping("/deleteloan/{loanId}")
    public ResponseEntity<String> deleteLoanById(@PathVariable Long loanId) {
        loanService.deleteLoanById(loanId);
        return new ResponseEntity<>("Loan with ID " + loanId + " deleted successfully", HttpStatus.OK);
    }
}