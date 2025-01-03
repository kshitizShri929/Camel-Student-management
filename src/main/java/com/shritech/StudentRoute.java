package com.shritech;

import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.model.dataformat.JsonLibrary;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
    
        // Define RESTful endpoints
        rest("/students")
            .get("/{id}")
                .to("direct:getStudent") 
            .post()
                .type(Student.class)
                .to("direct:createStudent") 
            .put("/{id}")
                .to("direct:updateStudent") 
            .delete("/{id}")
                .to("direct:deleteStudent"); 

        // Direct route for GET student by id
        from("direct:getStudent")
            .bean(StudentService.class, "getStudent(${header.id})"); 

        // Direct route for POST create student
        from("direct:createStudent")
            .log("${body}")
            .unmarshal().json(Student.class)
            .bean(StudentService.class, "createStudent"); 

        

        // Direct route for PUT update student
        from("direct:updateStudent")
            .bean(StudentService.class, "updateStudent(${header.id}, ${body})"); 

        // Direct route for DELETE delete student
        from("direct:deleteStudent")
            .bean(StudentService.class, "deleteStudent(${header.id})");
    }
}
