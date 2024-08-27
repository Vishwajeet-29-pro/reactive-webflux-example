package com.reactive.practice.instructor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("instructors")
public class Instructors {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;

    // one-to-many relationship with student
    private List<UUID> studentIds;
}
