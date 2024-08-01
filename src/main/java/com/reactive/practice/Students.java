package com.reactive.practice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("students")
public class Students {

    private UUID uuid;
    private String first_name;
    private String last_name;
    private int age;
    private String email;
    private String address;
    private String gender;
    private Date enrollment_date;
    private String course;
    private double percentage;
    private String status;

}
