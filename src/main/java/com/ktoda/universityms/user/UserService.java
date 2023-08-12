package com.ktoda.universityms.user;

import com.ktoda.universityms.authority.Authority;
import com.ktoda.universityms.authority.Role;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDTOMapper mapper;

    public UserService(UserRepository userRepository, UserDTOMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(mapper)
                .toList();
    }

    public UserDTO findUserById(long user) {
        return userRepository.findById(user)
                .map(mapper)
                .orElseThrow(() ->
                        new RuntimeException("HTTP 404: User not found!"));
    }

    public User findById(long user) {
        return userRepository.findById(user)
                .orElseThrow(() ->
                        new RuntimeException("HTTP 404: User not found!"));
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public UserDTO findStaffUserById(long id) {
        return mapper.apply(userRepository.findStaffUserById(id));
    }

    public UserDTO createStaffUser(String firstName, String lastName, String password, Date dateBirth) {
        String firstlastName = firstName.toLowerCase().charAt(0) + lastName.toLowerCase();
        String username = generateUsername(firstlastName);
        String email = firstlastName + "@admin.edu";
        User user = new User(username, firstName, lastName, password, email,
                new Date(System.currentTimeMillis()), dateBirth);
        Authority authority = new Authority(Role.ROLE_ADMIN, user);
        user.addAuthority(authority);
        return mapper.apply(userRepository.save(user));
    }

    public UserDTO createStudentUser(String firstName, String lastName, String password, Date dateBirth) {
        String firstlastName = firstName.toLowerCase().charAt(0) + lastName.toLowerCase();
        String username = generateUsername(firstlastName);
        String email = firstlastName + "@student.edu";
        User user = new User(username, firstName, lastName, password, email,
                new Date(System.currentTimeMillis()), dateBirth);
        Authority authority = new Authority(Role.ROLE_STUDENT, user);
        user.addAuthority(authority);
        return mapper.apply(userRepository.save(user));
    }

    public UserDTO createTeacherUser(String firstName, String lastName, String password, Date dateBirth) {
        String firstlastName = firstName.toLowerCase().charAt(0) + lastName.toLowerCase();
        String username = generateUsername(firstlastName);
        String email = firstlastName + "@teacher.edu";
        User user = new User(username, firstName, lastName, password, email,
                new Date(System.currentTimeMillis()), dateBirth);
        Authority authority = new Authority(Role.ROLE_TEACHER, user);
        user.addAuthority(authority);
        return mapper.apply(userRepository.save(user));
    }

    public String generateUsername(String username) {
        Random random = new Random();
        int randomId = random.nextInt(1000);
        String formattedId = String.format("%03d", randomId);
        return username + formattedId;
    }
    
}
