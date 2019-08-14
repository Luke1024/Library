package com.kodilla.library.domain.dto;

public class BorrowReturnDto {
    private Long userId;
    private Long bookCopyId;

    public BorrowReturnDto() {}

    public BorrowReturnDto(Long userId, Long bookCopyId) {
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
