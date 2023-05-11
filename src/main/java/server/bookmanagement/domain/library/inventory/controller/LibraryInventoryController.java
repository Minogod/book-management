package server.bookmanagement.domain.library.inventory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bookmanagement.domain.book.entity.Book;
import server.bookmanagement.domain.book.service.BookService;
import server.bookmanagement.domain.library.inventory.dto.LibraryInventoryDto;
import server.bookmanagement.domain.library.inventory.entity.LibraryInventory;
import server.bookmanagement.domain.library.inventory.mapper.LibraryInventoryMapper;
import server.bookmanagement.domain.library.inventory.service.LibraryInventoryService;
import server.bookmanagement.domain.library.library.entity.Library;
import server.bookmanagement.domain.library.library.service.LibraryService;
import server.bookmanagement.global.dto.SingleResponseDto;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/library/inventory")
@RequiredArgsConstructor
public class LibraryInventoryController {
    private final LibraryInventoryService libraryInventoryService;
    private final LibraryService libraryService;
    private final BookService bookService;
    private final LibraryInventoryMapper libraryInventoryMapper;
    @PostMapping
    public ResponseEntity<?> bookRegistrationInLibrary(@RequestBody LibraryInventoryDto.Post post) {
        //Todo : 같은 도서관에 이미 등록된 책이면 예외처리 해야함.
        Library library = libraryService.findById(post.getLibraryId());
        Book book = bookService.findById(post.getBookId());
        LibraryInventory libraryInventory = libraryInventoryMapper.LibraryInventoryPostToLibraryInventory(post);
        libraryInventory.setLibrary(library);
        libraryInventory.setBook(book);

        LibraryInventory registration = libraryInventoryService.bookRegistrationInLibrary(libraryInventory);

        LibraryInventoryDto.Response response = libraryInventoryMapper.LibraryInventoryToResponse(registration);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @Transactional
    @PatchMapping("/{library-inventory-id}")
    public ResponseEntity<?> patchLibraryInventory(@PathVariable("library-inventory-id") long libraryInventoryId,
                                                   @RequestBody LibraryInventoryDto.Patch patch) {
        LibraryInventory libraryInventory = libraryInventoryMapper.LibraryInventoryPatchToLibraryInventory(patch);
        libraryInventory.setId(libraryInventoryId);
        LibraryInventory updatedLibraryInventory = libraryInventoryService.updateLibraryInventory(libraryInventory);
        LibraryInventoryDto.Response response = libraryInventoryMapper.LibraryInventoryToResponse(updatedLibraryInventory);
        return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }

    @DeleteMapping("/{library-inventory-id}")
    public ResponseEntity<?> bookDeleteInLibrary(@PathVariable("library-inventory-id") long libraryInventoryId) {
        LibraryInventory libraryInventory = libraryInventoryService.findById(libraryInventoryId);
        LibraryInventory deletedLibraryInventory = libraryInventoryService.bookDeleteInLibrary(libraryInventory);

        LibraryInventoryDto.Response response = libraryInventoryMapper.LibraryInventoryToResponse(deletedLibraryInventory);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
}
