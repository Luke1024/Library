package com.kodilla.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Specified book not found.")
public class BookCopyNotFoundException extends Exception {
}
