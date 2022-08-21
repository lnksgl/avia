package avia.flight.controller;

import avia.flight.record.FlightRecord;
import avia.flight.service.FlightService;
import avia.flight.mapper.FlightMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class FlightController implements ControllerContract<FlightRecord> {

    FlightService flightService;
    FlightMapper mapper;

    @Override
    public void create(FlightRecord record) {
        flightService.save(mapper.toFlight(record));
    }

    @Override
    public FlightRecord getById(Long id) {
        return mapper.toRecord(flightService.getById(id));
    }

    @Override
    public List<FlightRecord> getAll() {
        return mapper.toList(flightService.getAll());
    }

    @Override
    public void update(FlightRecord record) {
        flightService.update(mapper.toFlight(record));
    }

    @Override
    public void delete(FlightRecord record) {
        flightService.delete(mapper.toFlight(record));
    }
}
