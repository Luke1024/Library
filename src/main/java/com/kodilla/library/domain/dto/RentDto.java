package com.kodilla.library.domain.dto;

public class RentDto {
    private Long userId;
    private Long bookCopyId;

    public RentDto(Long userId, Long bookCopyId) {
        this.userId = userId;
        this.bookCopyId = bookCopyId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBookCopyId() {
        return bookCopyId;
    }
}
