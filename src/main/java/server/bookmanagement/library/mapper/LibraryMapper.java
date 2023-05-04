package server.bookmanagement.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import server.bookmanagement.library.dto.LibraryDto;
import server.bookmanagement.library.entity.Library;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LibraryMapper {
    Library libraryPostToLibrary(LibraryDto.Post post);
    LibraryDto.Response libraryToLibararyResponse(Library library);
}
