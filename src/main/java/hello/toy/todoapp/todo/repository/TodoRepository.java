package hello.toy.todoapp.todo.repository;

import hello.toy.todoapp.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query(value = "select t from Todo t join fetch t.member m where t.id = :id")
    Todo findOneWithMember(Long id);

}
