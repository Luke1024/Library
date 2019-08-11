package com.kodilla.library.domain.dto;

import com.kodilla.library.domain.Status;

public class BookCopyDto {
    private Long titleId;
    private String title;
    private Status status;

    public BookCopyDto() {}

    public BookCopyDto(Long titleId, String title, Status status) {
        this.titleId = titleId;
        this.title = title;
        this.status = status;
    }

    public Long getTitleId() {
        return titleId;
    }

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }
}
