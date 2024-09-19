package com.reactive.practice.instructor;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

@WebFluxTest(InstructorController.class)
public class InstructorControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private InstructorService instructorService;

    private Instructors instructors;

    @BeforeEach
    public void setup() {
        instructors = new Instructors();
        instructors.setId(UUID.randomUUID());
        instructors.setFirstName("Richard");
        instructors.setLastName("Hendricks");
        instructors.setEmail("richardh@example.com");
        instructors.setDepartment("Computer");
        instructors.setStudentIds(null);
    }
}
