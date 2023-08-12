package com.ktoda.universityms.enrollment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    public List<Enrollment> findAllByStudentId(long studentId) {
        return enrollmentRepository.findAllByStudentId(studentId);
    }

}
