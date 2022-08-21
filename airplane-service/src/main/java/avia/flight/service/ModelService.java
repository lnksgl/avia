package avia.flight.service;

import avia.flight.exception.NotFoundException;
import avia.flight.entity.Model;
import avia.flight.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ModelService implements ServiceContract<Model>{

    ModelRepository modelRepository;

    @Override
    public void save(Model o) {
        modelRepository.save(o);
    }

    @Override
    public Model getById(Long id) {
        return modelRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Model not found. ModelId: " + id));
    }

    @Override
    public List<Model> getAll() {
        return modelRepository.findAll();
    }

    @Override
    public void update(Model o) {
        save(o);
    }

    @Override
    public void delete(Model o) {
        modelRepository.delete(o);
    }
}
