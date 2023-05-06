package server.bookmanagement.library.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import server.bookmanagement.book.dto.BookDto;
import server.bookmanagement.book.entity.Book;
import server.bookmanagement.library.inventory.entity.LibraryInventory;
import server.bookmanagement.library.library.dto.LibraryDto;
import server.bookmanagement.library.library.entity.Library;

public class LibraryInventoryDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private long bookId;
        private long libraryId;
        private int totalQuantity;
    }
    @Getter
    @AllArgsConstructor
    public static class Response{
        private long id;
        private BookDto.Response book;
        private LibraryDto.Response library;
        private int totalQuantity;
        private int loanQuantity;
        private LibraryInventory.LoanStatus loanStatus;
    }
}
