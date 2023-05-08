package server.bookmanagement.domain.library.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import server.bookmanagement.domain.library.library.entity.Library;
import server.bookmanagement.domain.library.library.dto.LibraryDto;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LibraryMapper {
    Library libraryPostToLibrary(LibraryDto.Post post);
    LibraryDto.Response libraryToLibararyResponse(Library library);
}
