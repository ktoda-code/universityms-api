package com.ktoda.universityms.tutorsession;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tutorsessions")
@AllArgsConstructor
public class TutorSessionRestController {
    private final TutorSessionService tutorSessionService;

    @GetMapping
    public List<TutorSession> findAll() {
        return tutorSessionService.findAll();
    }

    @PostMapping
    public TutorSession createTutorSession(@RequestBody TutorSessionRegisterRequest tsRegisterRequest) {
        return tutorSessionService.createTutorSession(
                tsRegisterRequest.teacherId(),
                tsRegisterRequest.date(),
                tsRegisterRequest.startTime(),
                tsRegisterRequest.endTime());
    }
}
