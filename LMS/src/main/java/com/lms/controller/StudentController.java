package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.entity.Student;
import com.lms.service.StudentService;

@RestController
@RequestMapping("/LMS")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/allstudent")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/savestudent")
    public Student addStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/studentbyid/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("updatestudent/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Student student = studentService.getStudentById(id);
        student.setName(updatedStudent.getName());
        student.setRollNo(updatedStudent.getRollNo());
        student.setBatch(updatedStudent.getBatch());
        student.setMobile(updatedStudent.getMobile());
        student.setEmail(updatedStudent.getEmail());
        return studentService.saveStudent(student);
    }

    @DeleteMapping("/deletestudent/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}