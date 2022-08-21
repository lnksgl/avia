package avia.flight.mapper;

import avia.flight.entity.Route;
import avia.flight.record.RouteRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RouteMapper {

    Route toRoute(RouteRecord routeRecord);
    RouteRecord toRecord(Route route);
    List<RouteRecord> toList(List<Route> routes);
}
