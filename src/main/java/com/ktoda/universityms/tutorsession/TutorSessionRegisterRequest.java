package com.ktoda.universityms.tutorsession;


import java.sql.Date;
import java.sql.Time;

public record TutorSessionRegisterRequest(
        Date date,
        Time startTime,
        Time endTime,
        long teacherId
) { }
