package com.projeto.tarefa.data.dto.v1;

import com.projeto.tarefa.model.enums.Status;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

public class TasksDTO extends RepresentationModel<TasksDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String description;
    private Status status;

    public TasksDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TasksDTO tasksDTO = (TasksDTO) o;
        return Objects.equals(id, tasksDTO.id) && Objects.equals(title, tasksDTO.title) && Objects.equals(description, tasksDTO.description) && status == tasksDTO.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, title, description, status);
    }
}
