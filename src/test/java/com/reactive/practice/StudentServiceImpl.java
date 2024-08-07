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
        return studentRepository.findById(uuid);
    }

    @Override
    public Flux<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Mono<Student> updateStudentDetailsById(UUID uuid, Student student) {
        return studentRepository.findById(uuid).flatMap(
          existingStudent -> {
              existingStudent.setFirst_name(student.getFirst_name());
              existingStudent.setLast_name(student.getLast_name());
              existingStudent.setAge(student.getAge());
              existingStudent.setEmail(student.getEmail());
              existingStudent.setAddress(student.getAddress());
              existingStudent.setGender(student.getGender());
              existingStudent.setEnrollment_date(student.getEnrollment_date());
              existingStudent.setCourse(student.getCourse());
              existingStudent.setPercentage(student.getPercentage());
              existingStudent.setStatus(student.getStatus());
              return studentRepository.save(existingStudent);
          });
    }

    @Override
    public Mono<Void> deleteStudentById(UUID uuid) {
        return studentRepository.deleteById(uuid);
    }
}
