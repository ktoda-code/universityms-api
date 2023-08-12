package com.ktoda.universityms.tutorsession;

import com.ktoda.universityms.teacher.Teacher;
import com.ktoda.universityms.teacher.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TutorSessionService {
    private final TutorSessionRepository tutorSessionRepository;
    private final TeacherService teacherService;

    public List<TutorSession> findAll() {
        return tutorSessionRepository.findAll();
    }

    public TutorSession createTutorSession(long teacherId, Date date, Time start, Time end) {
        Teacher teacher = teacherService.findById(teacherId);

        TutorSession tutorSession = new TutorSession(date, start, end, teacher);
        teacher.addTutorSession(tutorSession);

        teacherService.save(teacher); // Update teacher
        return tutorSessionRepository.save(tutorSession);
    }
}
