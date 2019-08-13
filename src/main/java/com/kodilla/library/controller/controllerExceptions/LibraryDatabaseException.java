package com.kodilla.library.controller.controllerExceptions;

public class LibraryDatabaseException extends Exception {

    public static String TITLE_NOT_FOUND = "Title not found";
    public static String BOOK_COPY_NOT_FOUND = "Book copy not found";
    public static String RENT_NOT_FOUND = "Rent not found";
    public static String USER_NOT_FOUND = "User not found";
    public static String OBJECT_NOT_FOUND = "Object not found";

    public LibraryDatabaseException(String message){
        super(message);
    }
}
