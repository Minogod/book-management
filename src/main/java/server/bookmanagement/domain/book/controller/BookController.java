package server.bookmanagement.domain.book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.bookmanagement.domain.book.dto.BookDto;
import server.bookmanagement.domain.book.entity.Book;
import server.bookmanagement.domain.book.mapper.BookMapper;
import server.bookmanagement.domain.book.service.BookService;
import server.bookmanagement.global.dto.MultiResponseDto;
import server.bookmanagement.global.dto.SingleResponseDto;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookDto.Post post) {
        Book book = bookMapper.bookPostToBook(post);
        Book createdBook = bookService.createBook(book);
        BookDto.Response response = bookMapper.bookToBookResponse(createdBook);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{book-id}")
    public ResponseEntity<?> patchBook(@PathVariable("book-id") long bookId,
                                       @RequestBody BookDto.Patch patch) {
        Book book = bookMapper.bookPatchToBook(patch);
        book.setId(bookId);
        Book patchedBook = bookService.patchBook(book);
        BookDto.Response response = bookMapper.bookToBookResponse(patchedBook);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<?> getBook(@PathVariable("book-id") long bookId) {
        Book book = bookService.findById(bookId);
        BookDto.Response response = bookMapper.bookToBookResponse(book);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getBooks(@RequestParam int page,
                                      @RequestParam int size) {
        Page<Book> bookPage = bookService.findAllBooks(page - 1, size);
        List<Book> books = bookPage.getContent();
        List<BookDto.Response> response = bookMapper.booksToBookResponses(books);

        return new ResponseEntity<>(new MultiResponseDto<>(response,bookPage), HttpStatus.OK);
    }

    @DeleteMapping("/{book-id}")
    public ResponseEntity<?> deleteBook(@PathVariable("book-id") long bookId) {
        Book book = bookService.findById(bookId);
        Book deletedBook = bookService.deleteBook(book);
        BookDto.Response response = bookMapper.bookToBookResponse(deletedBook);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Todo: 책 목록 조회 ( 책, 도서관, 도서관에 등록된 책 )
    // 사용자가 빌릴 책 검색하기위해 필요
    // 책만가지고 어디 도서관에 있는지 찾냐
    // 도서관안에서 책을 검색하냐
    // 책 디테일 조회 -> 책 ID,책 이름, 저자, 출판사, 어디 도서관에 총재고, 대여중인재고, 남은재고
    // 책 리스트 조회 -> 책 ID,책 이름, 저자, 출판사
    // 도서관 리스트 조회 -> 도서관 ID, 도서관 이름
    // 도서관 디테일 조회 -> 도서관 ID, 도서관 이름, 도서관에 있는 책들(책Id,총 재고,대여중재고,남은재고)
    // 도서관에 등록된 책 리스트 조회 -> 도서관 디테일 조회와 동일 ( 필요없음 )
    // 도서관에 등록된 책 디테일 조회 -> 책 디테일 조회와 동일 ( 필요없음 )
}
