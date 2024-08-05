package com.reactive.practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface StudentService {

    Mono<Students> createStudent(Students students);
    Mono<Students> getStudentById(UUID uuid);
    Flux<Students> getAllStudents();
    Mono<Students> updateStudentDetailsById(UUID uuid, Students students);
    Mono<Void> deleteStudentById(UUID uuid);
}
