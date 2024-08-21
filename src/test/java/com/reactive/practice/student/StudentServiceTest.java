package com.reactive.practice.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        student = new Student();
        student.setUuid(UUID.randomUUID());
        student.setFirst_name("John");
        student.setLast_name("Wick");
        student.setAge(17);
        student.setEmail("johnwick22@example.com");
        student.setGender("Male");
        student.setAddress("123 Lane Xyz city");
        student.setEnrollment_date(LocalDateTime.now());
        student.setCourse("Computer science");
        student.setStatus("Active");
        student.setPercentage(89.5);
    }

    @Test
    public void createStudent_ShouldReturnsStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(Mono.just(student));

        Mono<Student> createdStudent = studentService.createStudent(student);

        StepVerifier.create(createdStudent)
                .expectNext(student)
                .verifyComplete();

        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    public void getStudentById_ShouldReturnsStudent() {
        when(studentRepository.findById(any(UUID.class))).thenReturn(Mono.just(student));

        Mono<Student> foundStudent = studentService.getStudentById(student.getUuid());

        StepVerifier.create(foundStudent)
                .expectNext(student)
                .verifyComplete();

        verify(studentRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    public void getAllStudents_ShouldReturnListOfStudents() {
        when(studentRepository.findAll()).thenReturn(Flux.just(student));

        Flux<Student> studentFlux = studentService.getAllStudents();

        StepVerifier.create(studentFlux)
                .expectNext(student)
                .verifyComplete();

        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void updateStudentById_ShouldReturnsStudent() {
        when(studentRepository.findById(any(UUID.class))).thenReturn(Mono.just(student));
        when(studentRepository.save(any(Student.class))).thenReturn(Mono.just(student));

        student.setFirst_name("Jane");

        Mono<Student> updatedStudent = studentService.updateStudentDetailsById(student.getUuid(), student);

        StepVerifier.create(updatedStudent)
                .expectNextMatches(s -> s.getFirst_name().equals("Jane"))
                .verifyComplete();

        verify(studentRepository, times(1)).findById(any(UUID.class));
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    public void deleteStudent_ShouldDeleteStudent() {
        when(studentRepository.deleteById(any(UUID.class))).thenReturn(Mono.empty());

        Mono<Void> result = studentService.deleteStudentById(student.getUuid());

        StepVerifier.create(result)
                .verifyComplete();

        verify(studentRepository, times(1)).deleteById(any(UUID.class));
    }
}
