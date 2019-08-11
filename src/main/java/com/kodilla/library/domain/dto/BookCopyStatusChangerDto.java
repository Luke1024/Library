package com.kodilla.library.domain.dto;

import com.kodilla.library.domain.Status;

public class BookCopyStatusChangerDto {
    private Long id;
    private Status status;

    public BookCopyStatusChangerDto() {}

    public BookCopyStatusChangerDto(Long id, Status status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }
}
