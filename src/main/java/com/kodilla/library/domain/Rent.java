package com.kodilla.library.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long bookCopyId;

    @Column
    private LocalDate dateOfRental;

    @Column
    private LocalDate dateOfReturn;

    public Rent() {}

    public Rent(Long userId, Long bookCopyId, LocalDate dateOfRental, LocalDate dateOfReturn) {
        this.userId = userId;
        this.bookCopyId = bookCopyId;
        this.dateOfRental = dateOfRental;
        this.dateOfReturn = dateOfReturn;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBookCopyId() {
        return bookCopyId;
    }

    public LocalDate getDateOfRental() {
        return dateOfRental;
    }

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }
}
