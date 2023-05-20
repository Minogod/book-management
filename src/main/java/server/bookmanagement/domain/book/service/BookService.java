package server.bookmanagement.domain.book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import server.bookmanagement.domain.book.repository.BookRepository;
import server.bookmanagement.domain.book.entity.Book;
import server.bookmanagement.domain.library.inventory.entity.LibraryInventory;
import server.bookmanagement.global.error.exception.BusinessLogicException;
import server.bookmanagement.global.error.exception.ExceptionCode;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book patchBook(Book book) {
        Book findBook = findById(book.getId());
        Optional.ofNullable(book.getName()).ifPresent(findBook::setName);
        Optional.ofNullable(book.getPublisher()).ifPresent(findBook::setPublisher);
        Optional.ofNullable(book.getWriter()).ifPresent(findBook::setWriter);
        return bookRepository.save(findBook);
    }

    public Page<Book> findAllBooks(int page, int size) {
        return bookRepository.findByIsDeletedFalse(PageRequest.of(page, size));
    }

    public Book deleteBook(Book book) {
        book.setDeleted(true);
        return bookRepository.save(book);
    }

    @Cacheable(value = "book",key = "#id",unless = "#result == null", cacheManager = "testCacheManager")
    public Book findById(long id) {
        Book book = findBookById(id);
        validateBookNotDeleted(book);
        filterDeletedLibraryInventories(book);

        return book;
    }
    public Book findBookById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOOK_NOT_FOUND));
    }

    private void validateBookNotDeleted(Book book) {
        if (book.isDeleted()) {
            throw new BusinessLogicException(ExceptionCode.BOOK_IS_DELETED);
        }
    }

    private void filterDeletedLibraryInventories(Book book) {
        List<LibraryInventory> filteredLibraryInventories = book.getLibraryInventories()
                .stream()
                .filter(libraryInventory -> !libraryInventory.isDeleted())
                .collect(Collectors.toList());

        book.setLibraryInventories(filteredLibraryInventories);
    }


}
