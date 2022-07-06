package saffchen.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import saffchen.dto.ProductDto;
import saffchen.entities.ProductEntity;

import java.util.List;

/**
 * @author saffchen created on 06.07.2022
 * @project JRM-Java-Stock-System
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface ProductMapper {

    ProductDto productToProductDto(ProductEntity product);
    List<ProductDto> toProductsDtoList(List<ProductEntity> productEntities);
    ProductEntity productDtoToProduct(ProductDto productDto);
}
