package com.ktoda.universityms.ticket;

import com.ktoda.universityms.user.User;
import com.ktoda.universityms.staff.Staff;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private Date dateCreated;
    private boolean open;
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "staff")
    private Staff staff;

    public Ticket(String title, String description, Date dateCreated, User user, Staff staff) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.user = user;
        this.staff = staff;
    }
}
