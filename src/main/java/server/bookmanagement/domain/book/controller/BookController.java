package server.bookmanagement.domain.book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.bookmanagement.domain.book.dto.BookDto;
import server.bookmanagement.domain.book.entity.Book;
import server.bookmanagement.domain.book.mapper.BookMapper;
import server.bookmanagement.domain.book.service.BookService;
import server.bookmanagement.global.dto.SingleResponseDto;

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
