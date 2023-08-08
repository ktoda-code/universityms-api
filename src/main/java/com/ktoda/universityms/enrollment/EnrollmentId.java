package com.ktoda.universityms.enrollment;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class EnrollmentId implements Serializable {
    private long studentId;
    private long subjectId;

    public EnrollmentId(long studentId, long subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    public EnrollmentId() {

    }
}
