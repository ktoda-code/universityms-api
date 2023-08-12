package com.ktoda.universityms.ticket;

public record TicketRegistrationRequest(String title,
                                        String description,
                                        Long userId) {
}
