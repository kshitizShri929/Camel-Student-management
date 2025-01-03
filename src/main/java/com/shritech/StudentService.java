package com.shritech;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
@ApplicationScoped
public class StudentService {
    
    @Inject
    StudentRepository studentRepository;

    // Retrieve a student by id
    public Student getStudent(Long id) {
        return studentRepository.findById(id);  // Assuming findById is defined in StudentRepository
    }
    // Create a new student
    @Transactional
    public Student createStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student object cannot be null");
        }
        studentRepository.persist(student);  // Persist the student to the database
        return student;
    }
// Update an existing student
    @Transactional
    public Student updateStudent(Long id, Student studentData) {
        if (studentData == null) {
            throw new IllegalArgumentException("Student data cannot be null");
        }

        // Find the existing student by ID
        Student student = studentRepository.findById(id);
        if (student != null) {
            student.setName(studentData.getName());
            student.setAge(studentData.getAge());
            student.setEmail(studentData.getEmail());
            studentRepository.persist(student);  // Persist updated student object
        } else {
            throw new IllegalArgumentException("Student with ID " + id + " not found");
        }
        return student;
    }

    // Delete a student by id
    @Transactional
    public void deleteStudent(Long id) {
        boolean deleted = studentRepository.deleteById(id);  // Delete student by id
        if (!deleted) {
            throw new IllegalArgumentException("Student with ID " + id + " not found");
        }
    }
}
