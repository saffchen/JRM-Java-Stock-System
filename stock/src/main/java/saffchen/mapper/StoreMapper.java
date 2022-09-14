package saffchen.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import saffchen.dto.StoreDto;
import saffchen.entities.StoreEntity;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)

public interface StoreMapper {

    StoreDto storeToStoreDto(StoreEntity store);

    List<StoreDto> toStoreDtoList(List<StoreEntity> storeEntities);

    StoreEntity storeDtoToStoreEntity(StoreDto storeDto);
}
