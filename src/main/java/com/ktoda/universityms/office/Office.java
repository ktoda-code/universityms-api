package com.ktoda.universityms.office;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "offices")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_number")
    private int id;
    private int floor;

    public Office(int floor) {
        this.floor = floor;
    }
}
