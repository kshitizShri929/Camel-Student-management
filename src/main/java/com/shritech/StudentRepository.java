package com.shritech;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped

public class StudentRepository implements PanacheRepository<Student> {
    
}
