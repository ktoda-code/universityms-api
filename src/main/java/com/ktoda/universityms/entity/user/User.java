package com.ktoda.universityms.entity.user;

import com.ktoda.universityms.entity.subject.ForumResponse;
import com.ktoda.universityms.entity.user.staff.Ticket;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String password;
    private String email;
    @Column(name = "date_started")
    private Date dateStarted;
    @Column(name = "date_birth")
    private Date dateBirth;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Ticket> tickets;
    @OneToMany(mappedBy = "user")
    private List<Authority> authorities;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<ForumResponse> forumResponses;

    public User(String username, String firstName, String lastName, String password,
                String email, Date dateStarted, Date dateBirth) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.dateStarted = dateStarted;
        this.dateBirth = dateBirth;
        this.tickets = new ArrayList<>();
        this.authorities = new ArrayList<>();
        this.forumResponses = new ArrayList<>();
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void addAuthority(Authority authority) {
        authorities.add(authority);
    }

    public void addForumResponse(ForumResponse forumResponse) {
        forumResponses.add(forumResponse);
    }
}
