package com.ktoda.universityms.enrollment;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/enrollments")
@AllArgsConstructor
public class EnrollmentRestController {
    private final EnrollmentService enrollmentService;

    @GetMapping
    public List<Enrollment> findAll() {
        return enrollmentService.findAll();
    }

    @GetMapping("/student/{studentId}")
    public List<Enrollment> findAllByStudentId(@PathVariable long studentId) {
        return enrollmentService.findAllByStudentId(studentId);
    }
}
