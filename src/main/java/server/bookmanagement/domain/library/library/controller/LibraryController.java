package server.bookmanagement.domain.library.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bookmanagement.domain.library.library.entity.Library;
import server.bookmanagement.global.dto.MultiResponseDto;
import server.bookmanagement.global.dto.SingleResponseDto;
import server.bookmanagement.domain.library.library.dto.LibraryDto;
import server.bookmanagement.domain.library.library.mapper.LibraryMapper;
import server.bookmanagement.domain.library.library.service.LibraryService;

import java.util.List;

@RestController
@RequestMapping("/libraries")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;
    private final LibraryMapper libraryMapper;

    @PostMapping
    public ResponseEntity<?> createLibrary(@RequestBody LibraryDto.Post post) {
        Library library = libraryMapper.libraryPostToLibrary(post);
        Library createdLibrary = libraryService.createLibrary(library);
        LibraryDto.Response response = libraryMapper.libraryToLibararyResponse(createdLibrary);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{library-id}")
    public ResponseEntity<?> pathchLibrary(@RequestParam("library-id") long libraryId,
                                           @RequestBody LibraryDto.Patch patch) {
        Library library = libraryMapper.libraryPatchToLibrary(patch);
        library.setId(libraryId);
        Library pathchedLibrary = libraryService.pathchLibrary(library);
        LibraryDto.Response response = libraryMapper.libraryToLibararyResponse(pathchedLibrary);
        return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }

    @GetMapping("/{library-id}")
    public ResponseEntity<?> getLibrary(@PathVariable("library-id") long libraryId) {
        Library library = libraryService.findById(libraryId);
        LibraryDto.DetailResponse response = libraryMapper.libraryToLibraryDetailResponse(library);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getLibraries(@RequestParam int page,
                                          @RequestParam int size) {
        Page<Library> librariesPage = libraryService.getLibraries(page - 1, size);
        List<Library> libraries = librariesPage.getContent();
        List<LibraryDto.Response> response = libraryMapper.librariesToLibraryResponses(libraries);

        return new ResponseEntity<>(new MultiResponseDto<>(response,librariesPage), HttpStatus.OK);

    }

    @DeleteMapping("{library-id}")
    public ResponseEntity<?> deleteLibrary(@PathVariable("library-id") long libraryId) {
        Library library = libraryService.findById(libraryId);
        Library deletedLibrary = libraryService.deleteLibrary(library);
        LibraryDto.Response response = libraryMapper.libraryToLibararyResponse(deletedLibrary);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
}
