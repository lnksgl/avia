package avia.flight.controller;

import avia.flight.record.AirplaneRecord;
import avia.flight.mapper.AirplaneMapper;
import avia.flight.service.AirplaneService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/airplanes")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AirplaneController implements ControllerContract<AirplaneRecord>{

    AirplaneService airplaneService;
    AirplaneMapper mapper;

    @Override
    public void create(AirplaneRecord record) {
        airplaneService.save(mapper.toAirplane(record));
    }

    @Override
    public AirplaneRecord getById(Long id) {
        return mapper.toRecord(airplaneService.getById(id));
    }

    @Override
    public List<AirplaneRecord> getAll() {
        return mapper.toList(airplaneService.getAll());
    }

    @Override
    public void update(AirplaneRecord record) {
        airplaneService.update(mapper.toAirplane(record));
    }

    @Override
    public void delete(AirplaneRecord record) {
        airplaneService.delete(mapper.toAirplane(record));
    }
}
