package hello.toy.todoapp.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTodoDto {
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
