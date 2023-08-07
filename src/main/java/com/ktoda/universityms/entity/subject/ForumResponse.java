package com.ktoda.universityms.entity.subject;

import com.ktoda.universityms.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "forum_responses")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ForumResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "response_id")
    private int id;
    private String title;
    private String description;
    @Column(name = "date_respond")
    private Date dateRespond;
    @ManyToOne
    @JoinColumn(name = "forum")
    private Forum forum;
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    public ForumResponse(String title, String description, Date dateRespond, Forum forum, User user) {
        this.title = title;
        this.description = description;
        this.dateRespond = dateRespond;
        this.forum = forum;
        this.user = user;
    }
}
