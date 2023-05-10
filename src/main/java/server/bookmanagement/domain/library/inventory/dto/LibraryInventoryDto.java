package server.bookmanagement.domain.library.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.bookmanagement.domain.book.dto.BookDto;
import server.bookmanagement.domain.library.inventory.entity.LibraryInventory;
import server.bookmanagement.domain.library.library.dto.LibraryDto;

public class LibraryInventoryDto {
    @Getter
    @NoArgsConstructor
    public static class Post{
        private long bookId;
        private long libraryId;
        private int totalQuantity;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class LibraryResponse {
        private long id;
        private BookDto.Response book;
        private int totalQuantity;
        private int loanQuantity;
        private LibraryInventory.LoanStatus loanStatus;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response{
        private long id;
        private BookDto.Response book;
        private LibraryDto.Response library;
        private int totalQuantity;
        private int loanQuantity;
        private LibraryInventory.LoanStatus loanStatus;
    }
}
