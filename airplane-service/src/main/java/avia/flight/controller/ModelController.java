package avia.flight.controller;

import avia.flight.record.ModelRecord;
import avia.flight.service.ModelService;
import avia.flight.mapper.ModelMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/models")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ModelController implements ControllerContract<ModelRecord> {

    ModelService modelService;
    ModelMapper mapper;

    @Override
    public void create(@RequestBody ModelRecord record) {
        modelService.save(mapper.toModel(record));
    }

    @Override
    public ModelRecord getById(@PathVariable Long id) {
        return mapper.toRecord(modelService.getById(id));
    }

    @Override
    public List<ModelRecord> getAll() {
        return mapper.toList(modelService.getAll());
    }

    @Override
    public void update(@RequestBody ModelRecord record) {
        modelService.update(mapper.toModel(record));
    }

    @Override
    public void delete(@RequestBody ModelRecord record) {
        modelService.delete(mapper.toModel(record));
    }
}
