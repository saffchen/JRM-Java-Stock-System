package saffchen.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import saffchen.dto.ProductDto;
import saffchen.entities.ProductEntity;

import java.util.List;

/**
 * @author saffchen created on 06.07.2022
 * @project JRM-Java-Stock-System
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface ProductMapper {
    @Mapping(source = "store.name", target = "storeName")
    ProductDto productToProductDto(ProductEntity product);

    List<ProductDto> productToProductDtoList(List<ProductEntity> productEntities);
}