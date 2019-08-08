package com.kodilla.library.domain.dto;

public class UserCreationDto {
    private String firstName;
    private String lastName;

    public UserCreationDto() {}

    public UserCreationDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
