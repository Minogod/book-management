package server.bookmanagement.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.bookmanagement.library.entity.Library;
import server.bookmanagement.library.repository.LibraryRepository;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final LibraryRepository libraryRepository;
    public Library createLibrary(Library library) {

        return libraryRepository.save(library);
    }
}
