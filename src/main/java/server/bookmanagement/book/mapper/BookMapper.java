package server.bookmanagement.book.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import server.bookmanagement.book.dto.BookDto;
import server.bookmanagement.book.entity.Book;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    Book bookPostToBook(BookDto.Post post);
    BookDto.Response bookToBookResponse(Book book);
}
