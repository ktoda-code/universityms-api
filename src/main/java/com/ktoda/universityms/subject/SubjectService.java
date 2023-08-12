package com.ktoda.universityms.subject;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject findById(long subject) {
        return subjectRepository.findById(subject)
                .orElseThrow(() ->
                        new RuntimeException("HTTP 404: Subject not found!"));
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }
}
