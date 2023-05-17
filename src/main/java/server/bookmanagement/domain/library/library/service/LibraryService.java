package server.bookmanagement.domain.library.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import server.bookmanagement.domain.library.inventory.entity.LibraryInventory;
import server.bookmanagement.domain.library.library.entity.Library;
import server.bookmanagement.domain.loan.service.LoanService;
import server.bookmanagement.global.error.exception.BusinessLogicException;
import server.bookmanagement.global.error.exception.ExceptionCode;
import server.bookmanagement.domain.library.library.repository.LibraryRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final LibraryRepository libraryRepository;
    public Library createLibrary(Library library) {
        return libraryRepository.save(library);
    }

    public Library pathchLibrary(Library library) {
        Library findLibrary = findById(library.getId());
        Optional.ofNullable(library.getName()).ifPresent(findLibrary::setName);
        return libraryRepository.save(findLibrary);
    }

    public Page<Library> getLibraries(int page, int size) {
        return libraryRepository.findByIsDeletedFalse(PageRequest.of(page,size));
    }

    public Library deleteLibrary(Library library) {
        library.setDeleted(true);
        List<LibraryInventory> libraryInventories = library.getLibraryInventories();
        for(LibraryInventory libraryInventory : libraryInventories) {
            libraryInventory.setDeleted(true);
        }

        return libraryRepository.save(library);
    }
    @Cacheable(value = "library",key = "#id",unless = "#result == null", cacheManager = "testCacheManager")
    public Library findById(long id){
        Optional<Library> optionalLibrary = libraryRepository.findById(id);
        Library library = optionalLibrary.orElseThrow(() -> new BusinessLogicException(ExceptionCode.LIBRARY_NOT_FOUND));
        // 컬렉션 필드 변환
        List<LibraryInventory> libraryInventories = new ArrayList<>(library.getLibraryInventories());

        for(LibraryInventory libraryInventory: libraryInventories) {
            libraryInventory.setBook(libraryInventory.getBook());
        }
        library.setLibraryInventories(libraryInventories);

        if(library.isDeleted()) {
            throw new BusinessLogicException(ExceptionCode.LIBRARY_IS_DELETED);
        } else return library;
    }

}
