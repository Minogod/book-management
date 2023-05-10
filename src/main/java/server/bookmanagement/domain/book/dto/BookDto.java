package server.bookmanagement.domain.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.bookmanagement.domain.library.inventory.dto.LibraryInventoryDto;

import java.util.List;

public class BookDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String name;
        private String writer;
        private String publisher;
    }
    @Getter
    @AllArgsConstructor
    public static class Patch{
        private String name;
        private String writer;
        private String publisher;
    }
    @Setter
    @Getter
    @NoArgsConstructor
    public static class DetailResponse{
        private long id;
        private String name;
        private String writer;
        private String publisher;
        private List<LibraryInventoryDto.BookResponse> libraryInventories;
    }
    @Setter
    @Getter
    @NoArgsConstructor
    public static class Response{
        private long id;
        private String name;
        private String writer;
        private String publisher;
    }
}
