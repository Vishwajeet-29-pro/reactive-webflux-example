package com.reactive.practice.instructor;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface InstructorRepository extends ReactiveCrudRepository<Instructors, UUID> {
}
