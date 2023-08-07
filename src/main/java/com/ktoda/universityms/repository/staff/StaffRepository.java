package com.ktoda.universityms.repository.staff;

import com.ktoda.universityms.entity.user.staff.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {}
