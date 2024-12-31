package com.shritech;

import org.apache.camel.builder.RouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
       // restConfiguration().component("rest").host("localhost").port(8080); // Use "rest" instead of "quarkus"
       restConfiguration()
    .component("rest")
    .host("localhost")
    .port(8080)
    .apiContextPath("/students");
        rest("/students")
            .get("/{id}")
                .to("direct:getStudent")
            .post()
                .to("direct:createStudent")
            .put("/{id}")
                .to("direct:updateStudent")
            .delete("/{id}")
                .to("direct:deleteStudent");

        from("direct:getStudent")
            .bean(StudentService.class, "getStudent(${header.id})");

        from("direct:createStudent")
            .bean(StudentService.class, "createStudent(${body})");

        from("direct:updateStudent")
            .bean(StudentService.class, "updateStudent(${header.id}, ${body})");

        from("direct:deleteStudent")
            .bean(StudentService.class, "deleteStudent(${header.id})");
    }
}
