package com.ktoda.universityms.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Slf4j
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> findAll() {
        log.info("Retrieving all users");
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public UserDTO findUserById(@PathVariable long userId) {
        log.info("Retrieving user");
        return userService.findUserById(userId);
    }

    @GetMapping("/staff/{staffId}")
    public UserDTO findStaffUserById(@PathVariable long staffId) {
        log.info("Retrieving staff user");
        return userService.findStaffUserById(staffId);
    }

    @PostMapping("/staff")
    public UserDTO createStaffUser(@RequestBody UserRegisterRequest user) {
        log.info("Creating staff user " + user);
        return userService.createStaffUser(
                user.firstName(),
                user.lastName(),
                user.password(),
                new Date(System.currentTimeMillis()));
    }

    @PostMapping("/student")
    public UserDTO createStudentUser(@RequestBody UserRegisterRequest user) {
        log.info("Creating student user " + user);
        return userService.createStudentUser(
                user.firstName(),
                user.lastName(),
                user.password(),
                new Date(System.currentTimeMillis()));
    }

    @PostMapping("/teacher")
    public UserDTO createTeacherUser(@RequestBody UserRegisterRequest user) {
        log.info("Creating teacher user " + user);
        return userService.createTeacherUser(
                user.firstName(),
                user.lastName(),
                user.password(),
                new Date(System.currentTimeMillis()));
    }

}
