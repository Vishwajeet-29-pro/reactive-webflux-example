package com.reactive.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Mono<Student> createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Mono<Student> getStudentById(UUID uuid) {
        return null;
    }

    @Override
    public Flux<Student> getAllStudents() {
        return null;
    }

    @Override
    public Mono<Student> updateStudentDetailsById(UUID uuid, Student student) {
        return null;
    }

    @Override
    public Mono<Void> deleteStudentById(UUID uuid) {
        return null;
    }
}
