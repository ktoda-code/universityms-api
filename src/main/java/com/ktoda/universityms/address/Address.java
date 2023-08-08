package com.ktoda.universityms.address;

import com.ktoda.universityms.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@Getter
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long id;
    private String city;
    private String street;
    private String state;
    private int number;
    @Column(name = "zip_code")
    private int zipCode;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private User user;

    public Address(String city, String street, String state, int number, int zipCode, User user) {
        this.city = city;
        this.street = street;
        this.state = state;
        this.number = number;
        this.zipCode = zipCode;
        this.user = user;
    }
}
