package server.bookmanagement.library.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import server.bookmanagement.library.library.dto.LibraryDto;
import server.bookmanagement.library.library.entity.Library;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LibraryMapper {
    Library libraryPostToLibrary(LibraryDto.Post post);
    LibraryDto.Response libraryToLibararyResponse(Library library);
}
