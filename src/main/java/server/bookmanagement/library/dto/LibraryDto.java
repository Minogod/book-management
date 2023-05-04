package server.bookmanagement.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class LibraryDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        private String name;
        private String img_url;

    }
    @Getter
    @AllArgsConstructor
    public static class Response {
        private String name;
        private String img_url;
        private String createdAt;
    }
}
