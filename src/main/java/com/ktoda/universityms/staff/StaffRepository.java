package com.ktoda.universityms.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    /**
     * Retrieves a list of staff members ordered by the least number of assigned tickets.
     * Each staff member's associated tickets are eagerly fetched.
     *
     * @return An optional list of staff members with the least number of assigned tickets.
     * @implNote The list may be empty if no staff members are found.
     */
    @Query("select s " +
            "from Staff s " +
            "order by (select count(t) " +
            "           from Ticket t" +
            "           where t.open=true and t.staff.id=s.id)")
    Optional<List<Staff>> findStaffByLeastTicketsOpen();

}
