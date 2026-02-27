package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.dto.CommentCreateDto;
import kg.attractor.microgram.dto.CommentViewDto;
import kg.attractor.microgram.exception.CommentNotFoundException;
import kg.attractor.microgram.exception.WrongActionException;
import kg.attractor.microgram.model.Comment;
import kg.attractor.microgram.model.Publication;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.repository.CommentRepository;
import kg.attractor.microgram.service.CommentService;
import kg.attractor.microgram.service.PublicationService;
import kg.attractor.microgram.util.ConvertHelper;
import kg.attractor.microgram.util.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final PublicationService publicationService;
    private final UserHelper userHelper;

    @Override
    public void create(CommentCreateDto commentCreateDto) {

        User authUser = userHelper.getAuthUser();
        Publication publication = publicationService.findById(commentCreateDto.getPublicationId());
        Comment comment = ConvertHelper.convertToComment(commentCreateDto, publication, authUser);

        repository.saveAndFlush(comment);

        publication.setComments(publication.getComments() + 1);
        publicationService.save(publication);
    }

    @Override
    public CommentViewDto createAndReturnView(CommentCreateDto commentCreateDto){
        User authUser = userHelper.getAuthUser();
        Publication publication = publicationService.findById(commentCreateDto.getPublicationId());
        Comment comment = ConvertHelper.convertToComment(commentCreateDto, publication, authUser);

        repository.saveAndFlush(comment);

        publication.setComments(publication.getComments() + 1);
        publicationService.save(publication);

        CommentViewDto view = ConvertHelper.convertToCommentViewDto(comment);
        view.setIsOwner(comment.getPublication().getOwner().getEmail().equals(authUser.getEmail()));

        return view;
    }

    @Override
    public void delete(Integer commentId) {

        User authUser = userHelper.getAuthUser();
        Comment comment = repository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        Publication publication = comment.getPublication();

        if (!publication.getOwner().getEmail().equals(authUser.getEmail())) {
            throw new WrongActionException("Удалять комментарии можно только автор публикации!");
        }

        publication.setComments(publication.getComments() - 1);
        publicationService.save(publication);

        repository.delete(comment);
    }

    @Override
    public List<CommentViewDto> getAllByPublication(Integer publicationId) {

        return repository
                .findAllByPublication_IdOrderByCreatedDateAsc(publicationId)
                .stream()
                .map(ConvertHelper::convertToCommentViewDto)
                .toList();
    }

}
