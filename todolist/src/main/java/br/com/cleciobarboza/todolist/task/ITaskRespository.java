package br.com.cleciobarboza.todolist.task;

import java.util.List;
import java.util.UUID;

import org.springframework.scheduling.config.Task;



public interface  ITaskRespository extends JpaRepository<TaskModel, UUID>{
    List<TaskModel> findByiId(UUID idUser);
    
    
}
