package com.ktoda.universityms.entity.subject;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assignments")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private int id;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "subject")
    private Subject subject;
    @OneToMany(
            mappedBy = "assignment",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<AssignmentFile> assignmentFilesList;

    public Assignment(String title, String description, Subject subject) {
        this.title = title;
        this.description = description;
        this.subject = subject;
        this.assignmentFilesList = new ArrayList<>();
    }

    public void addFile(AssignmentFile assignmentFile) {
        assignmentFilesList.add(assignmentFile);
    }
}
