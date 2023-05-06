package server.bookmanagement.library.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import server.bookmanagement.library.inventory.dto.LibraryInventoryDto;
import server.bookmanagement.library.inventory.entity.LibraryInventory;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LibraryInventoryMapper {
    LibraryInventory LibraryInventoryPostToLibraryInventory(LibraryInventoryDto.Post post);
    LibraryInventoryDto.Response LibraryInventoryToLibraryInventoryResponse(LibraryInventory libraryInventory);
}
