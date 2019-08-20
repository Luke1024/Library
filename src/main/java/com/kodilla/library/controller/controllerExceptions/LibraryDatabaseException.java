package com.kodilla.library.controller.controllerExceptions;

public class LibraryDatabaseException extends Exception {

    public static final String TITLE_NOT_FOUND = "Title not found";
    public static final String BOOK_COPY_NOT_FOUND = "Book copy not found";
    public static final String RENT_NOT_FOUND = "Rent not found";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String OBJECT_NOT_FOUND = "Object not found";

    public LibraryDatabaseException(String message){
        super(message);
    }
}
