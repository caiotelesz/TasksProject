package com.projeto.tarefa.services;

import com.projeto.tarefa.controllers.TasksController;
import com.projeto.tarefa.data.dto.v1.TasksDTO;
import com.projeto.tarefa.model.Tasks;
import com.projeto.tarefa.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.logging.Logger;

import static com.projeto.tarefa.mapper.ObjectMapper.parseListObjects;
import static com.projeto.tarefa.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class TasksServices {

    private Logger log = Logger.getLogger(TasksServices.class.getName());

    @Autowired
    TasksRepository tasksRepository;

    public List<TasksDTO> findAll() {
        log.info("Find all tasks");

        var task = parseListObjects(tasksRepository.findAll(), TasksDTO.class);
        task.forEach(this::addHateaoasLinks);
        return task;
    }

    public TasksDTO findById(Long id) {
        log.info("Find task by Id");

        var entity = tasksRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found for this id"));

        var dto = parseObject(entity, TasksDTO.class);
        addHateaoasLinks(dto);
        return dto;
    }

    public TasksDTO create(TasksDTO tasks) {

        Assert.isNull(tasks.getId(), "Tasks cannot be null");

        log.info("Creating one task");

        var entity = parseObject(tasks, Tasks.class);

        var dto = parseObject(tasksRepository.save(entity), TasksDTO.class);
        addHateaoasLinks(dto);
        return dto;
    }

    public TasksDTO update(Long id, TasksDTO tasks) {

        Tasks entity = tasksRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found for this id"));

        log.info("Updating one task");

        entity.setTitle(tasks.getTitle());
        entity.setDescription(tasks.getDescription());
        entity.setStatus(tasks.getStatus());

        var dto = parseObject(tasksRepository.save(entity), TasksDTO.class);
        addHateaoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        Tasks entity = tasksRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found for this id"));

        log.info("Deleting one task");

        tasksRepository.delete(entity);
    }

    private void addHateaoasLinks(TasksDTO dto) {
        dto.add(linkTo(methodOn(TasksController.class).findById(dto.getId())).withSelfRel().withType("GET")); // findById
        dto.add(linkTo(methodOn(TasksController.class).delete(dto.getId())).withRel("delete").withType("DELETE")); // delete
        dto.add(linkTo(methodOn(TasksController.class).findAll()).withRel("findAll").withType("GET")); // findAll
        dto.add(linkTo(methodOn(TasksController.class).create(dto)).withRel("create").withType("POST")); // create
        dto.add(linkTo(methodOn(TasksController.class).update(dto.getId(), dto)).withRel("update").withType("PUT")); // update
    }
}
