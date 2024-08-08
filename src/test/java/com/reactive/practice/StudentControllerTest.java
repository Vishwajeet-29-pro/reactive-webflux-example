package com.reactive.practice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebFluxTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private StudentService studentService;

    private Student student;

    @BeforeEach
    public void setup() {
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
    public void createStudent_ShouldReturnCreatedStudent() {
        when(studentService.createStudent(any(Student.class))).thenReturn(Mono.just(student));

        webTestClient.post()
                .uri("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(student)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Student.class)
                .isEqualTo(student);
    }
}
