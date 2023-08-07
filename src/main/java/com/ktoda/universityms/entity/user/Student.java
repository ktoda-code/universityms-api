package com.ktoda.universityms.entity.user;

import com.ktoda.universityms.entity.subject.Subject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private long id;
    private boolean studying;
    private Date dateGraduated;
    @OneToOne
    @JoinColumn(name = "user")
    private User user;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "enrollment",
            joinColumns = @JoinColumn(name = "student"),
            inverseJoinColumns = @JoinColumn(name = "subject")
    )
    private List<Subject> subjects;

    public Student(User user) {
        this.user = user;
        this.dateGraduated = null;
        this.subjects = new ArrayList<>();
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }
}
