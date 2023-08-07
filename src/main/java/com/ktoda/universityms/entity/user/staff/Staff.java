package com.ktoda.universityms.entity.user.staff;

import com.ktoda.universityms.entity.user.Office;
import com.ktoda.universityms.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @Column(name = "user")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private User user;
    @Column(name = "office")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "office")
    private Office office;

    public Staff(User user, Office office) {
        this.user = user;
        this.office = office;
    }
}
