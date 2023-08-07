package com.ktoda.universityms.entity.user.teacher;

import com.ktoda.universityms.entity.user.teacher.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "tutor_sessions")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class TutorSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private int id;
    private Date date;
    @Column(name = "start_time")
    private Time startTime;
    @Column(name = "end_time")
    private Time endTime;
    @ManyToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;

    public TutorSession(Date date, Time startTime, Time endTime, Teacher teacher) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.teacher = teacher;
    }
}
