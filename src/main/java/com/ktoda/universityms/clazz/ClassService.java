package com.ktoda.universityms.clazz;

import com.ktoda.universityms.subject.Subject;
import com.ktoda.universityms.subject.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
@AllArgsConstructor
public class ClassService {
    private final ClassRepository classRepository;
    private final SubjectService subjectService;

    public List<Class> findAllClassesGroupBySubject() {
        return classRepository.findAllClassesGroupBySubject();
    }

    public void deleteClassById(int classId) {
        classRepository.deleteById(classId);
    }

    public Class createClass(ClassType classType, Time start, Time end, long subjectId) {
        Subject subject = subjectService.findById(subjectId);
        Class c = new Class(classType, start, end, subject);
        return classRepository.save(c);
    }

    public Class findById(int classId) {
        return classRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("HTTP 404: Class not found!"));
    }

    public Class updateClass(int classId, Time start, Time end, ClassType classType) {
        Class c = findById(classId);
        c.setClassType(classType);
        c.setStartClass(start);
        c.setEndClass(end);
        return classRepository.save(c);
    }

}
