package com.projeto.tarefa.services;

import com.projeto.tarefa.model.Tasks;
import com.projeto.tarefa.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.logging.Logger;

@Service
public class TasksServices {

    private Logger log = Logger.getLogger(TasksServices.class.getName());

    @Autowired
    TasksRepository tasksRepository;

    public List<Tasks> findAll() {
        log.info("Find all tasks");
        List<Tasks> list = tasksRepository.findAll().stream().toList();

        return list;
    }

    public Tasks findById(Long id) {
        log.info("Find task by Id");

        var entity = tasksRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found for this id"));

        return entity;
    }

    public Tasks create(Tasks tasks) {

        Assert.isNull(tasks.getId(), "Tasks cannot be null");

        log.info("Creating one task");

        return tasksRepository.save(tasks);
    }

    public Tasks update(Long id, Tasks tasks) {

        Tasks entity = tasksRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found for this id"));

        log.info("Updating one task");

        entity.setTitle(tasks.getTitle());
        entity.setDescription(tasks.getDescription());
        entity.setStatus(tasks.getStatus());

        return tasksRepository.save(entity);
    }

    public void delete(Long id) {
        Tasks entity = tasksRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found for this id"));

        log.info("Deleting one task");

        tasksRepository.delete(entity);
    }
}
