package com.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
