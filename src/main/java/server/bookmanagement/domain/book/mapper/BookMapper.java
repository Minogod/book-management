package server.bookmanagement.domain.book.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import server.bookmanagement.domain.book.dto.BookDto;
import server.bookmanagement.domain.book.entity.Book;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    Book bookPostToBook(BookDto.Post post);
    Book bookPatchToBook(BookDto.Patch patch);
    BookDto.Response bookToBookResponse(Book book);
    List<BookDto.Response> booksToBookResponses(List<Book> books);
}
