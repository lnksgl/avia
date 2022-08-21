package avia.flight.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ControllerContract<R> {

    @PostMapping
    void create(@RequestBody R record);

    @GetMapping("/{id}")
    R getById(@PathVariable Long id);

    @GetMapping
    List<R> getAll();

    @PutMapping
    void update(@RequestBody R record);

    @DeleteMapping
    void delete(@RequestBody R record);
}
