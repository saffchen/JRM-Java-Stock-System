package saffchen.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import saffchen.dto.SatelliteDto;
import saffchen.entities.SatelliteEntity;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)

public interface SatelliteMapper {

    SatelliteDto satelliteToSatelliteDto(SatelliteEntity satellite);

    List<SatelliteDto> toSatellitesDtoList(List<SatelliteEntity> satelliteEntities);

    SatelliteEntity satelliteEntityToSatelliteDtoToSatelliteEntity(SatelliteDto satellite);
}
