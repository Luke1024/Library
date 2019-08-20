package com.kodilla.library.domain;

import javax.persistence.*;

@Entity(name = "copies")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TITLE_ID")
    private Title title;
    private Status status;

    public BookCopy() {}

    public BookCopy(Title title, Status status) {
        this.title = title;
        this.status = status;
    }

    public BookCopy(Long id, Title title, Status status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
