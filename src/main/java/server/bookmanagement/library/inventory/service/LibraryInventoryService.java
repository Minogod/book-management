package server.bookmanagement.library.inventory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.bookmanagement.global.error.exception.BusinessLogicException;
import server.bookmanagement.global.error.exception.ExceptionCode;
import server.bookmanagement.library.inventory.entity.LibraryInventory;
import server.bookmanagement.library.inventory.repository.LibraryInventoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryInventoryService {
    private final LibraryInventoryRepository libraryInventoryRepository;


    public LibraryInventory registrationInLibrary(LibraryInventory libraryInventory){
        return libraryInventoryRepository.save(libraryInventory);
    }

    public void setLoanQuantity(LibraryInventory libraryInventory){
        libraryInventory.setLoanQuantity(libraryInventory.getLoanQuantity() + 1);
    }
    public LibraryInventory findById(long id){
        Optional<LibraryInventory> optionalLibraryInventory = libraryInventoryRepository.findById(id);
        return optionalLibraryInventory.orElseThrow(()->new BusinessLogicException(ExceptionCode.LIBRARY_INVENTORY_NOT_FOUND));
    }
}
