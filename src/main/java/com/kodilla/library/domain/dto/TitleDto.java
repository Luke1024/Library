package com.kodilla.library.domain.dto;


public class TitleDto {
    private Long titleId;
    private String title;
    private String author;
    private int publicationYear;

    public TitleDto() {}

    public TitleDto(Long titleId, String title, String author, int publicationYear) {
        this.titleId = titleId;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public Long getTitleId() {
        return titleId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }
}
