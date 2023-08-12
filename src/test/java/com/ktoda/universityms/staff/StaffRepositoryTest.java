package com.ktoda.universityms.staff;

import com.ktoda.universityms.UniversityMsApplication;
import com.ktoda.universityms.user.User;
import com.ktoda.universityms.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = UniversityMsApplication.class) // Use Testing database
class StaffRepositoryTest {
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    UserRepository userRepository;

    List<User> userList = new ArrayList<>();

    private void deleteTestData() {
        userRepository.deleteAll(userList);
    }

    @Test
    void findAllShouldProduceAllStaff() {
        // Create users
        User jjay = new User("jjay",
                "John",
                "Jay",
                "jay123",
                "jjay@admin.edu",
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()));
        User obrain = new User("obrain",
                "Oni",
                "Brain",
                "brain123",
                "obrain@admin.edu",
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()));
        User lwheels = new User("lwheels",
                "Larry",
                "Wheels",
                "wheels123",
                "lwheels@admin.edu",
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()));

        userList.addAll(List.of(jjay, obrain, lwheels));

        userList = userRepository.saveAllAndFlush(List.of(
                jjay, obrain, lwheels
        ));

        System.out.println(userList);

        List<Staff> staff = staffRepository.findAll();
        Assertions.assertThat(staff).hasSize(9);

        deleteTestData();
        userList.clear();
    }

    @Test
    void findStaffByLeastTicketsShouldReturnStaff() {
        Staff s = staffRepository.findById(13L).get(); // Change
        System.out.println(s);
        Optional<List<Staff>> staff = staffRepository.findStaffByLeastTicketsOpen();
        System.out.println(staff);
        Assertions.assertThat(staff.orElseThrow().get(0).getId()).isEqualTo(s.getId());
    }

}