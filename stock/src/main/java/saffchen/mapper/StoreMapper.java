package saffchen.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import saffchen.dto.StoreDto;
import saffchen.entities.StoreEntity;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)

public interface StoreMapper {
    
    @Mapping(target = "count", ignore = true)
    StoreDto storeToStoreDto(StoreEntity store);

    List<StoreDto> toStoreDtoList(List<StoreEntity> storeEntities);

    @Mapping(target = "products", ignore = true)
    StoreEntity storeDtoToStoreEntity(StoreDto storeDto);
}
