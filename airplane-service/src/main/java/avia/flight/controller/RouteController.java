package avia.flight.controller;

import avia.flight.mapper.RouteMapper;
import avia.flight.record.RouteRecord;
import avia.flight.service.RouteService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RouteController implements ControllerContract<RouteRecord> {

    RouteService routeService;
    RouteMapper mapper;

    @Override
    public void create(RouteRecord record) {
        routeService.save(mapper.toRoute(record));
    }

    @Override
    public RouteRecord getById(Long id) {
        return mapper.toRecord(routeService.getById(id));
    }

    @Override
    public List<RouteRecord> getAll() {
        return mapper.toList(routeService.getAll());
    }

    @Override
    public void update(RouteRecord record) {
        routeService.update(mapper.toRoute(record));
    }

    @Override
    public void delete(RouteRecord record) {
        routeService.delete(mapper.toRoute(record));
    }
}
