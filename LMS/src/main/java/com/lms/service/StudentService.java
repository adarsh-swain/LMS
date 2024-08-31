package com.lms.service;

import java.util.List;

import com.lms.entity.Student;

public interface StudentService {

	Student saveStudent(Student student);

	Student getStudentById(Long id);

	List<Student> getAllStudents();

	void deleteStudent(Long id);
}
