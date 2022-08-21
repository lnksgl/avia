package avia.flight.mapper;

import avia.flight.entity.RouteCities;
import avia.flight.record.RouteCitiesRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RouteCitiesMapper {

    RouteCities toRouteCities(RouteCitiesRecord routeCitiesRecord);

    RouteCitiesRecord toRecord(RouteCities route);

    List<RouteCitiesRecord> toList(List<RouteCities> routes);
}
