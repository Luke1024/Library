package com.kodilla.library.domain.dto;

import com.kodilla.library.domain.Status;

public class BookCopyDto {
    private Long titleId;
    private Status status;

    public BookCopyDto() {}

    public BookCopyDto(Long titleId, Status status) {
        this.titleId = titleId;
        this.status = status;
    }

    public Long getTitleId() {
        return titleId;
    }

    public Status getStatus() {
        return status;
    }
}
