package hello.toy.todoapp.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTodoRequest {
    private String title;
    private String description;
    private LocalDateTime endTime;
}
