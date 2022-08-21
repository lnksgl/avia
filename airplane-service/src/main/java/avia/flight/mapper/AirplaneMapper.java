package avia.flight.mapper;

import avia.flight.entity.Airplane;
import avia.flight.record.AirplaneRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirplaneMapper {

    Airplane toAirplane(AirplaneRecord airplaneRecord);
    AirplaneRecord toRecord(Airplane airplane);
    List<AirplaneRecord> toList(List<Airplane> airplanes);
}
