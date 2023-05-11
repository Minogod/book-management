package server.bookmanagement.domain.book.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.bookmanagement.domain.book.entity.Book;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    // 리펙토리시 QueryDSL 로 변경
    Page<Book> findByIsDeletedFalse(Pageable pageable);
}
