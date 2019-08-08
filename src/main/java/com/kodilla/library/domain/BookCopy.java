package com.kodilla.library.domain;

import javax.persistence.*;

@Entity(name = "copies")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long titleId;

    @Column
    private Status status;

    public BookCopy() {}

    public BookCopy(Long titleId, Status status) {
        this.titleId = titleId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getTitleId() {
        return titleId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
