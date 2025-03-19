package com.projeto.tarefa.controllers;

import com.projeto.tarefa.data.dto.v1.TasksDTO;
import com.projeto.tarefa.services.TasksServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {

    @Autowired
    private TasksServices tasksServices;

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public List<TasksDTO> findAll() {
        return tasksServices.findAll();
    }

    @GetMapping(
            value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public TasksDTO findById(@PathVariable("id") Long id) {
        return tasksServices.findById(id);
    }

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public TasksDTO create(@RequestBody TasksDTO tasks) {
        return tasksServices.create(tasks);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TasksDTO> update(@PathVariable("id") Long id, @RequestBody TasksDTO tasks) {
        TasksDTO updatedTask = tasksServices.update(id, tasks);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        tasksServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
