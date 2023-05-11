package server.bookmanagement.domain.library.inventory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.bookmanagement.domain.library.inventory.repository.LibraryInventoryRepository;
import server.bookmanagement.global.error.exception.BusinessLogicException;
import server.bookmanagement.global.error.exception.ExceptionCode;
import server.bookmanagement.domain.library.inventory.entity.LibraryInventory;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryInventoryService {
    private final LibraryInventoryRepository libraryInventoryRepository;


    public LibraryInventory bookRegistrationInLibrary(LibraryInventory libraryInventory) {
        return libraryInventoryRepository.save(libraryInventory);
    }

    public LibraryInventory updateLibraryInventory(LibraryInventory libraryInventory) {
        LibraryInventory findLibraryInventory = findById(libraryInventory.getId());
        int loanQuantity = findLibraryInventory.getLoanQuantity();
        int updateTotalQuantity = libraryInventory.getTotalQuantity();

        //Todo: 만약에 대여중인 책이 있으면 대여중인 수량 이하로는 전체수량 변경 불가능하게 해야함
        if( loanQuantity > updateTotalQuantity) {
            throw new BusinessLogicException(ExceptionCode.QUANTITY_UPDATE_IMPOSSIBLE);
        } else {
            Optional.of(libraryInventory.getTotalQuantity()).ifPresent(findLibraryInventory::setTotalQuantity);
            return libraryInventoryRepository.save(findLibraryInventory);
        }
    }

    public LibraryInventory bookDeleteInLibrary(LibraryInventory libraryInventory) {
        libraryInventory.setDeleted(true);
        return libraryInventoryRepository.save(libraryInventory);
    }

    public void addLoanQuantity(LibraryInventory libraryInventory) {
        libraryInventory.setLoanQuantity(libraryInventory.getLoanQuantity() + 1);
        setLoanStatus(libraryInventory);
    }

    public void minusLoanQuantity(LibraryInventory libraryInventory) {
        libraryInventory.setLoanQuantity(libraryInventory.getLoanQuantity() - 1);
        setLoanStatus(libraryInventory);
    }


    private static void setLoanStatus(LibraryInventory libraryInventory) {
        if(libraryInventory.getLoanQuantity() == libraryInventory.getTotalQuantity()) {
            libraryInventory.setLoanStatus(LibraryInventory.LoanStatus.모두대여중);
        } else if (libraryInventory.getLoanQuantity() < libraryInventory.getTotalQuantity()) {
            libraryInventory.setLoanStatus(LibraryInventory.LoanStatus.대여가능);
        }
    }


    public LibraryInventory findById(long id) {
        Optional<LibraryInventory> optionalLibraryInventory = libraryInventoryRepository.findById(id);
        LibraryInventory libraryInventory = optionalLibraryInventory.orElseThrow(() -> new BusinessLogicException(ExceptionCode.LIBRARY_INVENTORY_NOT_FOUND));
        if(libraryInventory.isDeleted()) {
            throw new BusinessLogicException(ExceptionCode.LIBRARY_INVENTORY_NOT_FOUND);
        } else return libraryInventory;
    }

    public void validLoanStatus(LibraryInventory libraryInventory){
        LibraryInventory.LoanStatus loanStatus = libraryInventory.getLoanStatus();
        if(loanStatus.equals(LibraryInventory.LoanStatus.모두대여중)) {
            throw new BusinessLogicException(ExceptionCode.LOAN_NOT_ALLOW);
        }
    }
}
