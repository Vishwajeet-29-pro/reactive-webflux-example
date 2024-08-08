package com.reactive.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Mono<ResponseEntity<Student>> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student)
                .map(createdStudent -> ResponseEntity.status(HttpStatus.CREATED).body(createdStudent));
    }

    @GetMapping
    public Flux<Student> getAllStudent() {
        return studentService.getAllStudents();
    }
}
