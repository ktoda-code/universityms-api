package com.ktoda.universityms.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "departments")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Department {
    @Id
    private String departmentName;
    private String description;
}
