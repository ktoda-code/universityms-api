package com.ktoda.universityms.ticket;

import com.ktoda.universityms.staff.Staff;
import com.ktoda.universityms.staff.StaffService;
import com.ktoda.universityms.user.User;
import com.ktoda.universityms.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final StaffService staffService;

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> findAllTicketsAssignedToStaff(long staff) {
        return ticketRepository.findAllByStaff_Id(staff);
    }

    public List<Ticket> findAllOpenTicketsAssignedToStaff(long staff) {
        return ticketRepository.findAllByOpenIsTrueAndStaff_Id(staff);
    }

    @Transactional
    public void closeTicket(long ticket) {
        ticketRepository.closeTicketById(ticket);
    }

    @Transactional
    public Ticket createTicket(String title, String description, long userId) {
        Staff staff = staffService.findStaffWithLeastTickets();
        User user = userService.findById(userId);

        Ticket ticket = new Ticket(title, description,
                new Date(System.currentTimeMillis()), user, staff);
        ticket.setOpen(true);
        user.addTicket(ticket);
        staff.addTicket(ticket);

        staffService.save(staff);
        userService.save(user);

        return ticketRepository.save(ticket);
    }

    @Transactional
    public void deleteTicketById(long ticket) {
        ticketRepository.deleteById(ticket);
    }
}
