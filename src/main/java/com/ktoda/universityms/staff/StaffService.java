package com.ktoda.universityms.staff;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StaffService {
    private final Logger log = LoggerFactory.getLogger(StaffService.class);
    private final StaffRepository staffRepository;

    public List<Staff> findAll() {
        log.info("Listing staff members");
        return staffRepository.findAll();
    }

    public Staff findById(long id) {
        log.info("Staff member by id " + id);
        return staffRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No staff member found!");
                    return new RuntimeException("HTTP 404: No staff member");
                });
    }

    public Staff findStaffWithLeastTickets() {
        return staffRepository.findStaffByLeastTicketsOpen()
                .orElseThrow(() ->
                {
                    log.error("No staff members found!");
                    return new RuntimeException("HTTP 404: No staff members");
                }).get(0);
    }

    public void save(Staff staff) {
        staffRepository.save(staff);
    }
}
