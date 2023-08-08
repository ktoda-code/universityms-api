package com.ktoda.universityms.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/staff")
public class StaffRestController {
    private final StaffService staffService;

    @Autowired
    public StaffRestController(StaffService staffService) {
        this.staffService = staffService;
    }


}
