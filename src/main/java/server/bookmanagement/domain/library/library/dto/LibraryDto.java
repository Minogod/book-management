package server.bookmanagement.domain.library.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.bookmanagement.domain.library.inventory.dto.LibraryInventoryDto;

import java.util.List;

public class LibraryDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        private String name;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private String name;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private long id;
        private String name;
        private String createdAt;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class DetailResponse {
        private long id;
        private String name;
        private List<LibraryInventoryDto.LibraryResponse> libraryInventories;
    }
}
