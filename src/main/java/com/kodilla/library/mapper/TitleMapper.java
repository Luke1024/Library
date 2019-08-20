package com.kodilla.library.mapper;

import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.dto.TitleCreationDto;
import com.kodilla.library.domain.dto.TitleDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TitleMapper {
    public List<TitleDto> mapToTitlesDtoList(final List<Title> titleList){
        return titleList
                .stream()
                .map(t -> new TitleDto(t.getId(), t.getTitle(), t.getAuthor(), t.getPublicationYear()))
                .collect(Collectors.toList());
    }

    public Title mapToTitleFromCreationDto(final TitleCreationDto titleCreationDto){
        return new Title(titleCreationDto.getTitle(),
                titleCreationDto.getAuthor(), titleCreationDto.getYearOfPublication());
    }

    public TitleDto mapToTitleDto(final Title title){
        return new TitleDto(title.getId(), title.getTitle(), title.getAuthor(), title.getPublicationYear());
    }
}
