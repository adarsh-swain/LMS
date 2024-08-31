package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

	void deleteByBookId(Long bookId);

	@Query(value = "SELECT loans.*, students.*, books.*, library_branches.* " + "FROM loans "
			+ "JOIN students ON loans.student_id = students.id " + "JOIN books ON loans.book_id = books.id "
			+ "JOIN library_branches ON loans.library_branch_id = library_branches.id", nativeQuery = true)
	List<Object[]> findAllLoansWithDetails();

	@Modifying
	@Query(value = "DELETE FROM loans WHERE id = :loanId", nativeQuery = true)
	void deleteLoanById(@Param("loanId") Long loanId);

}
