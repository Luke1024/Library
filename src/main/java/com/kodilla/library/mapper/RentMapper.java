package com.kodilla.library.mapper;

import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.dto.RentDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RentMapper {
    public Rent mapBorrow(RentDto rentDto){
        return new Rent(rentDto.getUserId(), rentDto.getBookCopyId(), LocalDate.now(), null);
    }

}
