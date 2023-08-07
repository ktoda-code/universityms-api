package com.ktoda.universityms.entity.subject;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "assignment_files")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AssignmentFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "afile_id")
    private int id;
    @Column(name = "file_name")
    private String name;
    @Column(name = "file_type")
    private String type;
    @Column(name = "file_data")
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "assignment")
    private Assignment assignment;

    public AssignmentFile(String name, String type, byte[] data, Assignment assignment) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.assignment = assignment;
    }
}
