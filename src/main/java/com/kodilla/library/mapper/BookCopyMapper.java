package com.kodilla.library.mapper;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.dto.BookCopyDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookCopyMapper {
    public List<BookCopyDto> mapToBookCopyDtoList(final List<BookCopy> bookCopyList){
        return bookCopyList.stream()
                .map(b -> new BookCopyDto(b.getTitleId(), b.getStatus())).collect(Collectors.toList());
    }

    public BookCopy mapToBookCopy(BookCopyDto bookCopyDto){
        return new BookCopy(bookCopyDto.getTitleId(), bookCopyDto.getStatus());
    }
}
