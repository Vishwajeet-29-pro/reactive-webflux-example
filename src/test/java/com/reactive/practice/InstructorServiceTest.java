package com.reactive.practice;

import com.reactive.practice.instructor.InstructorRepository;
import com.reactive.practice.instructor.Instructors;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class InstructorServiceTest {

    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorServiceImpl instructorService;

    private Instructors instructors;

}
