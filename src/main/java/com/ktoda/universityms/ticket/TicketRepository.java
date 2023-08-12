package com.ktoda.universityms.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    /**
     * Retrieves a list of open tickets that are currently
     * assigned to a specific staff member.
     *
     * @param staffId The unique identifier of the staff
     *                member.
     * @return A list of open tickets assigned to the
     * specified staff member.
     */
    List<Ticket> findAllByOpenIsTrueAndStaff_Id(long staffId);

    /**
     * Closes a ticket by its ID.
     *
     * @param ticketId The ID of the ticket to be closed.
     */
    @Modifying
    @Query("update Ticket t " +
            "set t.open = false " +
            "where t.id = :ticketId")
    void closeTicketById(@Param("ticketId") long ticketId);

    /**
     * Retrieves a list of tickets that are assigned to a specific staff member.
     *
     * @param staffId The unique identifier of the staff member.
     * @return A list of tickets assigned to the specified staff member.
     */
    List<Ticket> findAllByStaff_Id(long staffId);
}
