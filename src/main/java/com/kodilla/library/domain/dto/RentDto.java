package com.kodilla.library.domain.dto;

import java.time.LocalDate;

public class RentDto {
    private Long id;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private Long bookId;
    private String bookTitle;
    private LocalDate dateOfRental;
    private LocalDate dateOfReturn;

    public RentDto(Long id, Long userId, String userFirstName, String userLastName, Long bookId, String bookTitle, LocalDate dateOfRental, LocalDate dateOfReturn) {
        this.id = id;
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.dateOfRental = dateOfRental;
        this.dateOfReturn = dateOfReturn;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public LocalDate getDateOfRental() {
        return dateOfRental;
    }

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }
}
