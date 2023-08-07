package com.ktoda.universityms.entity.enrollment;

import com.ktoda.universityms.entity.subject.Subject;
import com.ktoda.universityms.entity.user.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "enrollment")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Enrollment {
    @EmbeddedId
    private EnrollmentId id;
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student")
    private Student student;
    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subject")
    private Subject subject;
    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private List<Grade> grades;

    public Enrollment(Student student, Subject subject) {
        this.student = student;
        this.subject = subject;
        this.grades = new ArrayList<>();
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

}
