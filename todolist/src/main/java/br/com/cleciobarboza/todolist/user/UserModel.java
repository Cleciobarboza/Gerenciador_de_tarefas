package br.com.cleciobarboza.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity (name ="tb_users")


public class UserModel{
    @Id
    @GeneratedValue(generator ="UUID")
    private  UUID id;

    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    /** 

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setName(String name){
        this.name = name;


    }

    public String getName(){
        return name;

    }

    public void UserPassword(String password){
        this.password = password;

    }

    public String getPassword(){
        return password;

    }

    */
}