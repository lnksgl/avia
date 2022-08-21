package avia.flight.mapper;

import avia.flight.entity.Flight;
import avia.flight.record.FlightRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    Flight toFlight(FlightRecord flightRecord);
    FlightRecord toRecord(Flight flight);
    List<FlightRecord> toList(List<Flight> flights);
}
