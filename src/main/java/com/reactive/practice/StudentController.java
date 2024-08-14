package com.reactive.practice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/test")
    public String index() {
        return "this is test endpoint.";
    }

    @PostMapping
    public Mono<ResponseEntity<Student>> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student)
                .map(createdStudent -> ResponseEntity.status(HttpStatus.CREATED).body(createdStudent))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @GetMapping
    public Flux<Student> getAllStudent() {
        return studentService.getAllStudents()
                .onErrorResume(e -> Flux.empty());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Student>> getStudentById(@PathVariable UUID id) {
        return studentService.getStudentById(id)
                .map(student -> ResponseEntity.status(HttpStatus.FOUND).body(student))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Student>> updateStudentById(
            @PathVariable UUID id,
            @RequestBody Student student
    ) {
        return studentService.updateStudentDetailsById(id, student)
                .map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteStudentById(@PathVariable UUID id) {
        return studentService.deleteStudentById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }
}
