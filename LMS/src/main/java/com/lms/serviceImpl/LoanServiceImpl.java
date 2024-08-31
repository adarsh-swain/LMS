package com.lms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.entity.Loan;
import com.lms.repo.LoanRepository;
import com.lms.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found with id: " + id));
    }

    @Override
    public List<Object[]> getAllLoans() {
        return loanRepository.findAllLoansWithDetails();
    }

    public void deleteLoanById(Long loanId) {
        loanRepository.deleteLoanById(loanId);
    }
}
