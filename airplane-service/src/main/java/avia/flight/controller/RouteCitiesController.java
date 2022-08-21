package avia.flight.controller;

import avia.flight.record.RouteCitiesRecord;
import avia.flight.service.RouteCitiesService;
import avia.flight.mapper.RouteCitiesMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/route-cities")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RouteCitiesController implements ControllerContract<RouteCitiesRecord> {

    RouteCitiesService routeCitiesService;
    RouteCitiesMapper mapper;

    @Override
    public void create(RouteCitiesRecord record) {
        routeCitiesService.save(mapper.toRouteCities(record));
    }

    @Override
    public RouteCitiesRecord getById(Long id) {
        return mapper.toRecord(routeCitiesService.getById(id));
    }

    @Override
    public List<RouteCitiesRecord> getAll() {
        return mapper.toList(routeCitiesService.getAll());
    }

    @Override
    public void update(RouteCitiesRecord record) {
        routeCitiesService.update(mapper.toRouteCities(record));
    }

    @Override
    public void delete(RouteCitiesRecord record) {
        routeCitiesService.delete(mapper.toRouteCities(record));
    }
}
