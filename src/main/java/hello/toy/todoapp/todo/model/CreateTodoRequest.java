package hello.toy.todoapp.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTodoRequest {
    private Long memberId;

    private String title;

    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    public CreateTodoRequest(Long memberId, String title, String description) {
        this.memberId = memberId;
        this.title = title;
        this.description = description;
    }
}
