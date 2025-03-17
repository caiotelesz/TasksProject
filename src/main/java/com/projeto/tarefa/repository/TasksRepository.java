package com.projeto.tarefa.repository;

import com.projeto.tarefa.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
}
