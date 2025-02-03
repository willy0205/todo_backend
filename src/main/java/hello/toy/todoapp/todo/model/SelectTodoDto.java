package hello.toy.todoapp.todo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class SelectTodoDto {

    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String name;


    public SelectTodoDto(String title, String description, LocalDateTime startTime, LocalDateTime endTime, String name) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
    }

    public SelectTodoDto(String title, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
