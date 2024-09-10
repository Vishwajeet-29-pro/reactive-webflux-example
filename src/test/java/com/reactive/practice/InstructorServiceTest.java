package com.reactive.practice;

import com.reactive.practice.instructor.InstructorRepository;
import com.reactive.practice.instructor.InstructorServiceImpl;
import com.reactive.practice.instructor.Instructors;
import com.reactive.practice.student.Student;
import com.reactive.practice.student.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InstructorServiceTest {

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private InstructorServiceImpl instructorService;

    private Instructors instructors;
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

        instructors = new Instructors();
        instructors.setId(UUID.randomUUID());
        instructors.setFirstName("Tom");
        instructors.setLastName("Holland");
        instructors.setEmail("tomholland@example.com");
        instructors.setDepartment("Science");
        instructors.setStudentIds(Arrays.asList(student.getUuid())); // Associated student
    }

    @Test
    public void createInstructor_WithAssociatedStudents_ShouldReturnInstructor() {
        when(studentRepository.findById(student.getUuid())).thenReturn(Mono.just(student));
        when(instructorRepository.save(any(Instructors.class))).thenReturn(Mono.just(instructors));

        Mono<Instructors> createInstructor = instructorService.createInstructor(instructors);

        StepVerifier.create(createInstructor)
                .expectNext(instructors)
                .verifyComplete();

        verify(instructorRepository, times(1)).save(any(Instructors.class));

        verify(studentRepository, times(1)).findById(student.getUuid());
    }

    @Test
    public void getInstructorById_ShouldReturnsInstructor() {
        when(instructorRepository.findById(any(UUID.class))).thenReturn(Mono.just(instructors));

        Mono<Instructors> foundInstructor = instructorService.getInstructorsById(student.getUuid());

        StepVerifier.create(foundInstructor)
                .expectNext(instructors)
                .verifyComplete();

        verify(instructorRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    public void getAllInstructors_ShouldReturnListOfInstructors() {
        when(instructorRepository.findAll()).thenReturn(Flux.just(instructors));

        Flux<Instructors> instructorsFlux = instructorService.getAllInstructors();

        StepVerifier.create(instructorsFlux)
                .expectNext(instructors)
                .verifyComplete();

        verify(instructorRepository, times(1)).findAll();
    }

    @Test
    public void updateInstructorById_ShouldReturnsInstructor() {
        when(instructorRepository.findById(any(UUID.class))).thenReturn(Mono.just(instructors));
        when(instructorRepository.save(any(Instructors.class))).thenReturn(Mono.just(instructors));

        instructors.setFirstName("baba");

        Mono<Instructors> updatedInstructor = instructorService.updateInstructorById(instructors.getId(), instructors);

        StepVerifier.create(updatedInstructor)
                .expectNextMatches(s -> s.getFirstName().equals("baba"))
                .verifyComplete();

        verify(instructorRepository, times(1)).findById(any(UUID.class));
        verify(instructorRepository, times(1)).save(any(Instructors.class));
    }

    @Test
    public void deleteInstructor_ShouldDeleteInstructor() {
        when(instructorRepository.deleteById(any(UUID.class))).thenReturn(Mono.empty());

        Mono<Void> result = instructorService.deleteInstructorById(instructors.getId());

        StepVerifier.create(result)
                .verifyComplete();

        verify(instructorRepository, times(1)).deleteById(any(UUID.class));
    }
}
