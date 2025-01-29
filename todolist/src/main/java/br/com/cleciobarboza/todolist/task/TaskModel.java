package br.com.cleciobarboza.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * ID
 * Usuário (IS_USUÁRIO)
 * Descrição
 * Título
 * Data de Início
 * Data de término
 * Prioridade
 * 
 */

 @ToString
@Setter
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
 @Entity(name = "tb_tasks")
public class TaskModel {
    
    @Id
    @GeneratedValue(generator ="UUID")
    private  UUID id;
    private String description;

    @Column(Length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitle(String title) throws Exception {
        if (title.length() > 50) {

            throw new Exception("O campo title deve conter no maximo 50 caracter");
                    
        }
        this.title = title;   

        
    }

   


}
