package com.ktoda.universityms.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
public class StaffRestController {
    private final StaffService staffService;

    @Autowired
    public StaffRestController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public List<Staff> listStaff() {
        return staffService.findAll();
    }

    @GetMapping("/{staffId}")
    public Staff listStaffWithId(@PathVariable long staffId) {
        return staffService.findById(staffId);
    }

    @GetMapping("/tickets")
    public Staff listStaffWithLeastTickets() {
        return staffService.findStaffWithLeastTickets();
    }

}
