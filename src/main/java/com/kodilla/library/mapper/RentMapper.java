package com.kodilla.library.mapper;

import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.dto.RentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentMapper {
    public List<RentDto> mapToDtoRentDtoList(List<Rent> rentList) {
        return rentList.stream().map(rent -> new RentDto(
                rent.getId(),
                rent.getUser().getId(),
                rent.getUser().getFirstName(),
                rent.getUser().getLastName(),
                rent.getBookCopy().getId(),
                rent.getBookCopy().getTitle().getTitle(),
                rent.getDateOfRental(),
                rent.getDateOfReturn())).collect(Collectors.toList());
    }

    public RentDto mapToRentDto(Rent rent){
        return new RentDto(rent.getId(),
                rent.getUser().getId(),
                rent.getUser().getFirstName(),
                rent.getUser().getLastName(),
                rent.getBookCopy().getId(),
                rent.getBookCopy().getTitle().getTitle(),
                rent.getDateOfRental(),
                rent.getDateOfReturn());
    }
}
