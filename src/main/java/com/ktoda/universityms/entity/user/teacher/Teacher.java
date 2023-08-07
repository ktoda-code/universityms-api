package com.ktoda.universityms.entity.user.teacher;

import com.ktoda.universityms.entity.subject.Forum;
import com.ktoda.universityms.entity.subject.Subject;
import com.ktoda.universityms.entity.user.Department;
import com.ktoda.universityms.entity.user.Office;
import com.ktoda.universityms.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "teachers")
@NoArgsConstructor
@Getter
@ToString
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private long id;
    private boolean active;
    @OneToOne
    @JoinColumn(name = "user")
    private User user;
    @OneToOne
    @JoinColumn(name = "office")
    private Office office;
    @OneToOne
    @JoinColumn(name = "department")
    private Department department;
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<TutorSession> tutorSessions;
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Subject> subjects;
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Forum> forums;

    public Teacher(User user, Office office, Department department) {
        this.user = user;
        this.office = office;
        this.department = department;
        this.tutorSessions = new ArrayList<>();
        this.subjects = new ArrayList<>();
        this.forums = new ArrayList<>();
    }

    public void addTutorSession(TutorSession tutorSession) {
        tutorSessions.add(tutorSession);
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void addForum(Forum forum) {
        forums.add(forum);
    }
}
