package com.reactive.practice.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("students")
public class Student {

    @Id
    private UUID uuid;
    private String first_name;
    private String last_name;
    private int age;
    private String email;
    private String address;
    private String gender;
    private LocalDateTime enrollment_date;
    private String course;
    private double percentage;
    private String status;

}
