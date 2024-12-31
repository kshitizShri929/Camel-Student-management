package com.shritech;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class StudentService {
    public Student getStudent(Long id) {
        return Student.findById(id);
    }

    @Transactional
    public Student createStudent(Student student) {
        student.persist();
        return student;
    }

    @Transactional
    public Student updateStudent(Long id, Student studentData) {
        Student student = Student.findById(id);
        if (student != null) {
            student.name = studentData.name;
            student.age = studentData.age;
            student.email = studentData.email;
        }
        return student;
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student.deleteById(id);
    }
}

    

