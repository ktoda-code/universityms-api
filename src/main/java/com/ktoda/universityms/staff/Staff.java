package com.ktoda.universityms.staff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ktoda.universityms.office.Office;
import com.ktoda.universityms.ticket.Ticket;
import com.ktoda.universityms.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "staff")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    @JsonIgnore
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "office")
    private Office office;
    @OneToMany(mappedBy = "staff", fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    public Staff(User user, Office office) {
        this.user = user;
        this.office = office;
        this.tickets = new ArrayList<>();
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
