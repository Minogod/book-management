package server.bookmanagement.book.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.bookmanagement.book.entity.Book;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
