package server.bookmanagement.domain.library.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import server.bookmanagement.domain.library.inventory.dto.LibraryInventoryDto;
import server.bookmanagement.domain.library.inventory.entity.LibraryInventory;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LibraryInventoryMapper {
    LibraryInventory LibraryInventoryPostToLibraryInventory(LibraryInventoryDto.Post post);
    LibraryInventoryDto.LibraryResponse LibraryInventoryToLibraryResponse(LibraryInventory libraryInventory);
    LibraryInventoryDto.Response LibraryInventoryToResponse(LibraryInventory libraryInventory);
    List<LibraryInventoryDto.LibraryResponse> LibraryInventoriesToLibraryResponses(List<LibraryInventory> libraryInventories);
}
