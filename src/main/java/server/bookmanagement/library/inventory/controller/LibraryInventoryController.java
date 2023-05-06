package server.bookmanagement.library.inventory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.bookmanagement.book.entity.Book;
import server.bookmanagement.book.service.BookService;
import server.bookmanagement.library.inventory.dto.LibraryInventoryDto;
import server.bookmanagement.library.inventory.entity.LibraryInventory;
import server.bookmanagement.library.inventory.mapper.LibraryInventoryMapper;
import server.bookmanagement.library.inventory.service.LibraryInventoryService;
import server.bookmanagement.library.library.entity.Library;
import server.bookmanagement.library.library.service.LibraryService;

@RestController
@RequestMapping("/library/inventory")
@RequiredArgsConstructor
public class LibraryInventoryController {
    private final LibraryInventoryService libraryInventoryService;
    private final LibraryService libraryService;
    private final BookService bookService;
    private final LibraryInventoryMapper libraryInventoryMapper;
    @PostMapping
    public ResponseEntity<?> RegistrationInLibrary(@RequestBody LibraryInventoryDto.Post post) {
        Library library = libraryService.findById(post.getLibraryId());
        Book book = bookService.findById(post.getBookId());
        LibraryInventory libraryInventory = libraryInventoryMapper.LibraryInventoryPostToLibraryInventory(post);

        libraryInventory.setLibrary(library);
        libraryInventory.setBook(book);
        LibraryInventory registration = libraryInventoryService.registrationInLibrary(libraryInventory);

        LibraryInventoryDto.Response response = libraryInventoryMapper.LibraryInventoryToLibraryInventoryResponse(registration);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
