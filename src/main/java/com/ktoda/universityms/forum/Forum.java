package com.ktoda.universityms.forum;

import com.ktoda.universityms.forumresponse.ForumResponse;
import com.ktoda.universityms.subject.Subject;
import com.ktoda.universityms.teacher.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "forums")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_id")
    private int id;
    private String title;
    private String description;
    @OneToOne
    @JoinColumn(name = "subject")
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;
    @OneToMany(mappedBy = "forum", fetch = FetchType.EAGER)
    private List<ForumResponse> forumResponses;

    public Forum(String title, String description, Subject subject, Teacher teacher) {
        this.title = title;
        this.description = description;
        this.subject = subject;
        this.teacher = teacher;
        this.forumResponses = new ArrayList<>();
    }

    public void addForumResponse(ForumResponse forumResponse) {
        forumResponses.add(forumResponse);
    }
}
