package com.reactive.practice.instructor;

import com.reactive.practice.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;


    @Override
    public Mono<Instructors> createInstructor(Instructors instructors) {
        List<UUID> studentIds = instructors.getStudentIds();

        // Use Flux to verify that all student IDs exist in the student repository
        return Flux.fromIterable(studentIds)
                .flatMap(studentRepository::findById)
                .collectList() // Collect all found students
                .flatMap(students -> {
                    // Check if the number of found students matches the number of student IDs
                    if (students.size() != studentIds.size()) {
                        return Mono.error(new IllegalArgumentException("One or more student IDs are invalid"));
                    }
                    // If all student IDs are valid, save the instructor
                    return instructorRepository.save(instructors);
                });
    }

    @Override
    public Mono<Instructors> getInstructorsById(UUID instructorId) {
        return instructorRepository.findById(instructorId);
    }

    @Override
    public Flux<Instructors> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public Mono<Instructors> updateInstructorById(UUID instructorId, Instructors instructors) {
        return instructorRepository.findById(instructorId)
                .flatMap(existingInstructor -> {
                    existingInstructor.setFirstName(instructors.getFirstName());
                    existingInstructor.setLastName(instructors.getLastName());
                    existingInstructor.setEmail(instructors.getEmail());
                    existingInstructor.setDepartment(instructors.getDepartment());
                    existingInstructor.setStudentIds(instructors.getStudentIds());
                    return instructorRepository.save(existingInstructor);
                });
    }

    @Override
    public Mono<Void> deleteInstructorById(UUID instructorId) {
        return instructorRepository.deleteById(instructorId);
    }
}
