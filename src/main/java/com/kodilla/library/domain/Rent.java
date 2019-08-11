package com.kodilla.library.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOK_COPY_ID")
    private BookCopy bookCopy;
    private LocalDate dateOfRental;
    private LocalDate dateOfReturn;

    public Rent() {}

    public Rent(User user, BookCopy bookCopy, LocalDate dateOfRental) {
        this.user = user;
        this.bookCopy = bookCopy;
        this.dateOfRental = dateOfRental;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public void setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LocalDate getDateOfRental() {
        return dateOfRental;
    }

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }
}
