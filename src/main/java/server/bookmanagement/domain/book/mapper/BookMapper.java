package server.bookmanagement.domain.book.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import server.bookmanagement.domain.book.dto.BookDto;
import server.bookmanagement.domain.book.entity.Book;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    Book bookPostToBook(BookDto.Post post);
    BookDto.Response bookToBookResponse(Book book);
}
