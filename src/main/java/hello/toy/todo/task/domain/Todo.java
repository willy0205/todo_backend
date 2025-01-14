package hello.toy.todo.task.domain;

import jakarta.persistence.*;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TODO_ID")
    private Long id;
}
