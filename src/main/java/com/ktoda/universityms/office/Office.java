package com.ktoda.universityms.office;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "offices")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Office {
    @Id
    @Column(name = "office_number")
    private int id;
    private int floor;

    public Office(int floor) {
        this.floor = floor;
    }

}
