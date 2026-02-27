package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.CommentCreateDto;
import kg.attractor.microgram.dto.CommentViewDto;

import java.util.List;

public interface CommentService {
    void create(CommentCreateDto commentCreateDto);

    CommentViewDto createAndReturnView(CommentCreateDto commentCreateDto);

    void delete(Integer commentId);

    List<CommentViewDto> getAllByPublication(Integer publicationId);
}
