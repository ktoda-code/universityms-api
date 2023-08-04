create schema if not exists 'university-ms';

create table if not exists departments
(
    department_name varchar(8) not null
    primary key,
    description     text       not null
    );

create table if not exists offices
(
    office_number int not null
    primary key,
    floor         int not null
);

create table if not exists users
(
    user_id      bigint auto_increment
    primary key,
    username     varchar(255) not null,
    first_name   varchar(255) not null,
    last_name    varchar(255) not null,
    password     varchar(80)  not null,
    email        varchar(255) not null,
    date_started date         not null,
    date_birth   date         not null,
    constraint email_ak
    unique (email),
    constraint username_ak
    unique (username)
    );

create table if not exists addresses
(
    address_id bigint auto_increment
    primary key,
    city       varchar(255) not null,
    street     varchar(255) not null,
    state      varchar(255) not null,
    number     int          null,
    zip_code   int          null,
    user       bigint       not null,
    constraint addresses_user_ak
    unique (user),
    constraint addrs_users_fk
    foreign key (user) references users (user_id)
    on update cascade on delete cascade
    );

create table if not exists authorities
(
    role_id   int auto_increment
    primary key,
    role_name varchar(100) not null,
    user      bigint       not null,
    constraint auth_users_fk
    foreign key (user) references users (user_id)
    on update cascade on delete cascade
    )
    comment 'The authorities table is responsible to track information about the permission of each user';

create table if not exists staff
(
    staff_id bigint auto_increment
    primary key,
    user     bigint not null,
    office   int    null,
    constraint staff_office_ak
    unique (office),
    constraint staff_office__fk
    foreign key (office) references offices (office_number)
    on update cascade on delete cascade,
    constraint staff_users___fk
    foreign key (user) references users (user_id)
    on update cascade on delete cascade
    );

create definer = `unimsapi-admin`@localhost trigger trg_check_office_in_teachers
    before insert
    on staff
    for each row
BEGIN
  DECLARE office_assigned_to_teacher INT;
SELECT COUNT(*) INTO office_assigned_to_teacher FROM teachers WHERE office = NEW.office;

IF office_assigned_to_teacher > 0 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Office is already assigned to a teacher member.';
END IF;
END;

create table if not exists students
(
    student_id     bigint auto_increment
    primary key,
    user           bigint               not null,
    date_graduated datetime             null,
    studying       tinyint(1) default 1 not null,
    constraint students_user_ak
    unique (user),
    constraint students_users___fk
    foreign key (user) references users (user_id)
    on update cascade on delete cascade
    );

create table if not exists teachers
(
    teacher_id bigint auto_increment
    primary key,
    user       bigint               not null,
    active     tinyint(1) default 1 not null,
    office     int                  null,
    department varchar(8)           null,
    constraint teachers_office_ak
    unique (office),
    constraint teachers_user_ak
    unique (user),
    constraint teachers_departmentd_department_name_fk
    foreign key (department) references departments (department_name)
    on update cascade on delete cascade,
    constraint teachers_offices_office_number_fk
    foreign key (office) references offices (office_number)
    on update cascade on delete cascade,
    constraint teachers_users_user_id_fk
    foreign key (user) references users (user_id)
    on update cascade on delete cascade
    );

create table if not exists subjects
(
    subject_code bigint auto_increment
    primary key,
    subject_name varchar(255)         not null,
    description  text                 not null,
    active       tinyint(1) default 1 not null,
    department   varchar(8)           not null,
    teacher      bigint               null,
    constraint subjects_name_ak
    unique (subject_name),
    constraint subjects_departments_department_name_fk
    foreign key (department) references departments (department_name)
    on update cascade on delete cascade,
    constraint subjects_teachers_teacher_id_fk
    foreign key (teacher) references teachers (teacher_id)
    on update cascade on delete cascade
    );

create table if not exists assignments
(
    assignment_id int auto_increment
    primary key,
    title         varchar(255) not null,
    description   text         not null,
    subject       bigint       not null,
    constraint assignments_subjects_subject_code_fk
    foreign key (subject) references subjects (subject_code)
    on delete cascade
    );

create table if not exists assignment_files
(
    afile_id   int auto_increment
    primary key,
    file_name  varchar(255) not null,
    file_type  varchar(50)  not null,
    file_data  blob         not null comment 'The actual file data to be stored',
    assignment int          not null,
    constraint assignment_files_assignments_assignment_id_fk
    foreign key (assignment) references assignments (assignment_id)
    on update cascade on delete cascade
    );

create table if not exists classes
(
    class_number int auto_increment
    primary key,
    class_type   varchar(50) not null,
    subject      bigint      not null,
    class_start  time        not null,
    class_end    time        null,
    constraint classes_subjects_subject_code_fk
    foreign key (subject) references subjects (subject_code)
    );

create table if not exists enrollment
(
    student   bigint       not null,
    subject   bigint       not null,
    subj_name varchar(255) not null,
    primary key (student, subject),
    constraint enrollment_student___fk
    foreign key (student) references students (student_id)
    on update cascade on delete cascade,
    constraint enrollment_subject__fk
    foreign key (subject) references subjects (subject_code)
    on update cascade on delete cascade
    );

create table if not exists forums
(
    forum_id    int auto_increment
    primary key,
    title       varchar(255) not null,
    description text         null,
    subject     bigint       not null,
    teacher     bigint       not null,
    constraint forums_subject_ak
    unique (subject),
    constraint forums_subjects_subject_code_fk
    foreign key (subject) references subjects (subject_code),
    constraint forums_subjects_teacher_fk
    foreign key (teacher) references subjects (teacher)
    );

create table if not exists forum_responses
(
    response_id  int auto_increment
    primary key,
    title        varchar(255) not null,
    description  text         not null,
    date_respond datetime     not null,
    forum        int          not null,
    user         bigint       not null,
    constraint forum_responses_forums_forum_id_fk
    foreign key (forum) references forums (forum_id)
    on update cascade on delete cascade,
    constraint forum_responses_users_user_id_fk
    foreign key (user) references users (user_id)
    on update cascade
    );

create table if not exists grades
(
    grade_id int auto_increment
    primary key,
    grade    double not null,
    subject  bigint not null,
    student  bigint not null,
    constraint grades_enrollment_student_subject_fk
    foreign key (student, subject) references enrollment (student, subject)
    on update cascade on delete cascade
    );

create definer = `unimsapi-admin`@localhost trigger trg_check_teacher_is_active
    before insert
    on subjects
    for each row
BEGIN
  DECLARE teacher_active INT;
SELECT active INTO teacher_active FROM teachers WHERE teacher_id = NEW.teacher;
IF teacher_active <> 1 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Teacher must be active (active=1) to be associated with a subject.';
END IF;
END;

create definer = `unimsapi-admin`@localhost trigger trg_check_office_in_staff
    before insert
    on teachers
    for each row
BEGIN
  DECLARE office_assigned_to_staff INT;
SELECT COUNT(*) INTO office_assigned_to_staff FROM staff WHERE office = NEW.office;

IF office_assigned_to_staff > 0 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Office is already assigned to a staff member.';
END IF;
END;

create table if not exists tickets
(
    ticket_id    bigint auto_increment
    primary key,
    title        varchar(255)         not null,
    description  text                 not null,
    date_created datetime             not null,
    open         tinyint(1) default 1 not null,
    user         bigint               not null,
    staff        bigint               not null,
    constraint tickets_staff_fk
    foreign key (staff) references staff (staff_id)
    on update cascade,
    constraint tickets_users_fk
    foreign key (user) references users (user_id)
    on update cascade
    );

create table if not exists tutor_sessions
(
    session_id int auto_increment
    primary key,
    date       datetime not null,
    start_time time     not null,
    end_time   time     not null,
    teacher    bigint   not null,
    constraint tutor_sessions_teachers_teacher_id_fk
    foreign key (teacher) references teachers (teacher_id)
    on update cascade on delete cascade
    );

create definer = `unimsapi-admin`@localhost trigger after_insert_user
    after insert
    on users
    for each row
BEGIN
    DECLARE email_suffix VARCHAR(20);

    -- Extract the email suffix using SUBSTRING_INDEX function
    SET email_suffix = SUBSTRING_INDEX(NEW.email, '@', -1);

    -- Check the email suffix and insert into the appropriate table
    IF email_suffix = 'admin.edu' THEN
        INSERT INTO staff (user)
        VALUES (NEW.user_id);
    ELSEIF email_suffix = 'student.edu' THEN
        INSERT INTO students (user)
        VALUES (NEW.user_id);
    ELSEIF email_suffix = 'teacher.edu' THEN
        INSERT INTO teachers (user)
        VALUES (NEW.user_id);
END IF;
END;

create definer = `unimsapi-admin`@localhost trigger after_insert_user_auth
    after insert
    on users
    for each row
BEGIN
    DECLARE email_suffix VARCHAR(20);

    -- Extract the email suffix using SUBSTRING_INDEX function
    SET email_suffix = SUBSTRING_INDEX(NEW.email, '@', -1);

    -- Check the email suffix and insert into the appropriate table
    IF email_suffix = 'admin.edu' THEN
        INSERT INTO authorities (role_name, user)
        VALUES ('ROLE_ADMIN',NEW.user_id);
    ELSEIF email_suffix = 'student.edu' THEN
        INSERT INTO authorities (role_name, user)
        VALUES ('ROLE_STUDENT',NEW.user_id);
    ELSEIF email_suffix = 'teacher.edu' THEN
        INSERT INTO authorities (role_name, user)
        VALUES ('ROLE_TEACHER',NEW.user_id);
END IF;
END;

