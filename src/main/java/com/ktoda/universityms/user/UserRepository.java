package com.ktoda.universityms.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Retrieves the associated User entity for a specific Staff entity.
     *
     * @param staffId The ID of the Staff entity for which to retrieve the associated User.
     * @return The User entity associated with the specified Staff entity.
     */
    @Query("select u " +
            "from Staff s " +
            "join s.user u " +
            "where s.id = :staffId")
    User findStaffUserById(@Param("staffId") long staffId);
}
