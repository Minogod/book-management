package server.bookmanagement.library.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.bookmanagement.library.inventory.entity.LibraryInventory;

@Repository
public interface LibraryInventoryRepository extends JpaRepository<LibraryInventory,Long> {
}
