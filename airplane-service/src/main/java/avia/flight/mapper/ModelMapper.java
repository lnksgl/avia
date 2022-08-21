package avia.flight.mapper;

import avia.flight.entity.Model;
import avia.flight.record.ModelRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    Model toModel(ModelRecord modelRecord);
    ModelRecord toRecord(Model model);
    List<ModelRecord> toList(List<Model> models);
}
