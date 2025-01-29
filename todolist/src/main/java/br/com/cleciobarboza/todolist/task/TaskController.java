package br.com.cleciobarboza.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import br.com.cleciobarboza.todolist.utils.Utils;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRespository taskRespository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        System.out.println("Chegou no controller"+);
        var request.getAuthType(name:"idUser");
        taskModel.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();
            //10/11/2024 - Current
            //10/10/2024 - starAt
        if (currentDate.isAfter(taskModel.getStartAt())  || currentDate.isAfter(taskModel.getEndAt())){
            resturn ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(body:"A data de inicio / data de término deve ser maior do que a data atual");

        }

        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            resturn ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(body:"A data de inicio deve ser menor do que a data de término");
        }
      
      
        var task = this.taskRespository.save(taskModel);
        return new ResponseEntity.status(HttpStatus.OK).body(task);

    }

    @GetMapping("/")
    public void list(HttpServletRequest request){
        var idUser = request.getAttribute(name:"idUser");
        var tasks = this.taskRespository.findByIdUser((UUID) idUser);
        return tasks;
    }

    //http://localhost:8080/tasks/892347823-cdfgcvb-832748234
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID ID){

        var task = this.taskRespository.findByiId(id).orElse(null);
        

        if (task == null){
            resturn ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Tarefa não encontrada");
        }

        var  idUser = request.getAttribute(neme:"idUser");

        if (!task.getIdUser().equals(idUser));{
            resturn ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Usuário não tem permição para alterar essa tarefa");

        }

        Utils.copyNonNullPropertyNames(taskModel, task);

        var taskUpdated = this.taskRespository.save(task);
        return ResponseEntity.ok().body(taskUpdated);

    }
    
}
