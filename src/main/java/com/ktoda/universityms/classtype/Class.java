package com.ktoda.universityms.classtype;

import com.ktoda.universityms.subject.Subject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;

@Entity
@Table(name = "classes")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_number")
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "class_type")
    private ClassType classType;
    @Column(name = "class_start")
    private Time startClass;
    @Column(name = "class_end")
    private Time endClass;
    @OneToOne
    @JoinColumn(name = "subject")
    private Subject subject;

    public Class(ClassType classType, Time startClass, Time endClass, Subject subject) {
        this.classType = classType;
        this.startClass = startClass;
        this.endClass = endClass;
        this.subject = subject;
    }
}
