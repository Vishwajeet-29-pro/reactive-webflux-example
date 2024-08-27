package com.reactive.practice.instructor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface InstructorService {

    Mono<Instructors> createInstructor(Instructors instructors);
    Mono<Instructors> getInstructorsById(UUID instructorId);
    Flux<Instructors> getAllInstructors();
    Mono<Instructors> updateInstructorById(UUID instructorId, Instructors instructors);
    Mono<Void> deleteInstructorById(UUID instructorId);
}
