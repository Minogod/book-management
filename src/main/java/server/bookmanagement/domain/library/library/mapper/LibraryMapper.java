package server.bookmanagement.domain.library.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import server.bookmanagement.domain.library.library.entity.Library;
import server.bookmanagement.domain.library.library.dto.LibraryDto;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LibraryMapper {
    Library libraryPostToLibrary(LibraryDto.Post post);
    Library libraryPatchToLibrary(LibraryDto.Patch patch);
    LibraryDto.Response libraryToLibararyResponse(Library library);
    LibraryDto.DetailResponse libraryToLibraryDetailResponse(Library library);
    List<LibraryDto.Response> librariesToLibraryResponses(List<Library> libraries);
}
