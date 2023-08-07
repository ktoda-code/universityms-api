package com.ktoda.universityms.entity.enrollment;

import com.ktoda.universityms.entity.enrollment.Enrollment;
import com.ktoda.universityms.entity.subject.Assignment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "grades")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private int id;
    @Basic
    private Double grade;
    @OneToOne
    @JoinColumn(name = "assignment")
    private Assignment assignment;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "student", referencedColumnName = "student"),
            @JoinColumn(name = "subject", referencedColumnName = "subject")
    })
    private Enrollment enrollment;

    public Grade(Double grade, Assignment assignment) {
        this.grade = grade;
        this.assignment = assignment;
    }
}
