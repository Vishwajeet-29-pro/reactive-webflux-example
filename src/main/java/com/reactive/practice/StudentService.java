package com.reactive.practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface StudentService {

    Mono<Student> createStudent(Student student);
    Mono<Student> getStudentById(UUID uuid);
    Flux<Student> getAllStudents();
    Mono<Student> updateStudentDetailsById(UUID uuid, Student student);
    Mono<Void> deleteStudentById(UUID uuid);
}
