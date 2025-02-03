package hello.toy.todoapp.todo.domain;

import com.google.common.annotations.VisibleForTesting;
import hello.toy.todoapp.member.domain.Member;
import hello.toy.todoapp.todo.model.CreateTodoRequest;
import hello.toy.todoapp.todo.model.UpdateTodoRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Todo {
    private Todo(String title, String description, LocalDateTime startTime, LocalDateTime endTime, Member member) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.member = member;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "TODO_ID")
    private Long id;

    private String title;

    private String description;

    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    // == 생성 메서드 == //
    public static Todo createTodo(CreateTodoRequest request, Member member) {
        // start time 없을 경우 현재 시간으로 설정
        if (request.getStartTime() == null) {
            request.setStartTime(LocalDateTime.now());
        }

        return new Todo(request.getTitle(), request.getDescription(), request.getStartTime(), request.getEndTime(), member);
    }

    public static Todo createTodo(String title, String description, LocalDateTime startTime, LocalDateTime endTime, Member member) {
        // start time 없을 경우 현재 시간으로 설정
        LocalDateTime now = startTime == null ? LocalDateTime.now() : startTime;

        return new Todo(title, description, now, endTime, member);
    }

    // == 비즈니스 메서드 == //

    /**
     * 업데이트
     */
    public void update(UpdateTodoRequest request) {
        if (request.getTitle() != null) {
            this.title = request.getTitle();
        }

        if (request.getDescription() != null) {
            this.description = request.getDescription();
        }

        if (request.getEndTime() != null) {
            this.endTime = request.getEndTime();
        }

    }

    // == 테스트 전용 메서드 == //
    @VisibleForTesting
    @Deprecated
    public void setIdForTest(Long id) {
        this.id = id;
    }

}
