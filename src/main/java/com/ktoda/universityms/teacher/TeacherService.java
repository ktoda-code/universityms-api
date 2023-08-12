package com.ktoda.universityms.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Teacher findById(long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() ->
                        new RuntimeException("Not found!"));
    }

    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }
}
