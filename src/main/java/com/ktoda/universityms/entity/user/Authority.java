package com.ktoda.universityms.entity.user;

import com.ktoda.universityms.entity.user.Role;
import com.ktoda.universityms.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "authorities")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    public Authority(Role role, User user) {
        this.role = role;
        this.user = user;
    }
}
