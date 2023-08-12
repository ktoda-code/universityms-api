package com.ktoda.universityms.user;

import java.sql.Date;

public record UserRegisterRequest(String firstName,
                                  String lastName,
                                  String password,
                                  Date dateBirth) {
}
