package server.bookmanagement.domain.library.inventory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.bookmanagement.domain.library.inventory.repository.LibraryInventoryRepository;
import server.bookmanagement.global.error.exception.BusinessLogicException;
import server.bookmanagement.global.error.exception.ExceptionCode;
import server.bookmanagement.domain.library.inventory.entity.LibraryInventory;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryInventoryService {
    private final LibraryInventoryRepository libraryInventoryRepository;


    public LibraryInventory bookRegistrationInLibrary(LibraryInventory libraryInventory) {
        //LibraryInventory 전체 가지고오기
        //bookId, LibraryId 같은거 찾기
        //LibraryInventory 가 isDeleted true 이면 -> 새로만들기
        //false 이면 -> 예외처리
        //있으면 예외처리 없으면 save
        doubleCheck(libraryInventory);

        return libraryInventoryRepository.save(libraryInventory);
    }

    private void doubleCheck(LibraryInventory libraryInventory) {
        List<LibraryInventory> libraryInventories = findAll();
        boolean isDuplicate = libraryInventories.stream()
                .anyMatch(libraryInventory1 ->
                        libraryInventory1.getBook().getId().equals(libraryInventory.getBook().getId()) &&
                                libraryInventory1.getLibrary().getId().equals(libraryInventory.getLibrary().getId()) &&
                                !libraryInventory1.isDeleted()
                );

        if (isDuplicate) {
            throw new BusinessLogicException(ExceptionCode.LIBRARY_INVENTORY_ALREADY_EXISTS);
        }
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
            if( loanQuantity == updateTotalQuantity) {
                findLibraryInventory.setLoanStatus(LibraryInventory.LoanStatus.모두대여중);
            }
            return libraryInventoryRepository.save(findLibraryInventory);
        }
    }

    public List<LibraryInventory> findAll() {
        return libraryInventoryRepository.findAll();
    }

    public LibraryInventory bookDeleteInLibrary(LibraryInventory libraryInventory) {
        libraryInventory.setDeleted(true);
        return libraryInventoryRepository.save(libraryInventory);
        //Todo : 누가 책을 대여중인데 삭제시키려면 ? 반납가능 and
        // 삭제되게함. -> 그 member
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
