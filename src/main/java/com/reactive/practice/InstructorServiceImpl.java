package com.reactive.practice;

import com.reactive.practice.instructor.InstructorRepository;
import com.reactive.practice.instructor.InstructorService;
import com.reactive.practice.instructor.Instructors;
import com.reactive.practice.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;


    @Override
    public Mono<Instructors> createInstructor(Instructors instructors) {
        return null;
    }

    @Override
    public Mono<Instructors> getInstructorsById(UUID instructorId) {
        return null;
    }

    @Override
    public Flux<Instructors> getAllInstructors() {
        return null;
    }

    @Override
    public Mono<Instructors> updateInstructorById(UUID instructorId, Instructors instructors) {
        return null;
    }

    @Override
    public Mono<Void> deleteInstructorById(UUID instructorId) {
        return null;
    }
}
