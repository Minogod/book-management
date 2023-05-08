package server.bookmanagement.domain.library.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.bookmanagement.domain.library.library.entity.Library;
import server.bookmanagement.global.dto.SingleResponseDto;
import server.bookmanagement.domain.library.library.dto.LibraryDto;
import server.bookmanagement.domain.library.library.mapper.LibraryMapper;
import server.bookmanagement.domain.library.library.service.LibraryService;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;
    private final LibraryMapper libraryMapper;

    @PostMapping
    public ResponseEntity<?> createLibrary(@RequestBody LibraryDto.Post postDto) {
        Library library = libraryMapper.libraryPostToLibrary(postDto);
        Library createdLibrary = libraryService.createLibrary(library);
        LibraryDto.Response response = libraryMapper.libraryToLibararyResponse(createdLibrary);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
}
