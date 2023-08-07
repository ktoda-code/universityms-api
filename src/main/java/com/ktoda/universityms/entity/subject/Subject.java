package com.ktoda.universityms.entity.subject;

import com.ktoda.universityms.entity.user.Department;
import com.ktoda.universityms.entity.user.Student;
import com.ktoda.universityms.entity.user.teacher.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_code")
    private long subjectCode;
    @Column(name = "subject_name")
    private String name;
    private String description;
    private boolean active;
    @OneToOne
    @JoinColumn(name = "department")
    private Department department;
    @OneToOne(mappedBy = "subject", cascade = CascadeType.ALL)
    private Forum forum;
    @ManyToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;
    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private List<Assignment> assignments;
    @ManyToMany
    @JoinTable(
            name = "enrollment",
            joinColumns = @JoinColumn(name = "subject"),
            inverseJoinColumns = @JoinColumn(name = "student")
    )
    private List<Student> students;

    public Subject(String name, String description, Department department,
                   Forum forum, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.department = department;
        this.forum = forum;
        this.teacher = teacher;
        this.assignments = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public void addStudent(Student student) {
        students.add(student);
    }
}
