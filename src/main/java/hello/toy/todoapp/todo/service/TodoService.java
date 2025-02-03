package hello.toy.todoapp.todo.service;

import hello.toy.todoapp.member.domain.Member;
import hello.toy.todoapp.todo.domain.Todo;
import hello.toy.todoapp.todo.model.CreateTodoRequest;
import hello.toy.todoapp.todo.model.SelectTodoDto;
import hello.toy.todoapp.todo.model.UpdateTodoDto;
import hello.toy.todoapp.todo.model.UpdateTodoRequest;
import hello.toy.todoapp.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {
    private final TodoRepository todoRepository;
//    private final MemberRepository memberRepository;

    @Transactional(readOnly = false)
    public Long create(CreateTodoRequest createTodoRequest) throws Exception {
        // 맴버 찾기
        Optional<Member> member = memberRepository.findById(createTodoRequest.getMemberId());

        Todo todo = Todo.createTodo(createTodoRequest, member.orElseThrow());
        Todo saved = todoRepository.save(todo);


        return saved.getId();

    }

    public SelectTodoDto findOne(Long id) throws Exception {
        // 투두 조회
        Optional<Todo> findTodo = todoRepository.findById(id);
        Todo todo = findTodo.orElseThrow();
        return new SelectTodoDto(todo.getTitle(), todo.getDescription(), todo.getStartTime(), todo.getEndTime());
    }

    public SelectTodoDto findOneWithMember(Long id) throws Exception {
        // jpql로 조회
        Todo findTodo = todoRepository.findOneWithMember(id);
        return new SelectTodoDto(findTodo.getTitle(), findTodo.getDescription(), findTodo.getStartTime(), findTodo.getEndTime(), findTodo.getMember().getName());

    }

    public List<SelectTodoDto> findAll() throws Exception {
        // 조회
        List<Todo> todos = todoRepository.findAll();
        // 엔티티 dto 전환하면서 return
        return todos.stream().map(
            todo -> new SelectTodoDto(todo.getTitle(), todo.getDescription(), todo.getStartTime(), todo.getEndTime())
        ).toList();
    }

    @Transactional(readOnly = false)
    public UpdateTodoDto update(Long id, UpdateTodoRequest request) throws Exception {
        // id로 투두 찾기
        Optional<Todo> findTodo = todoRepository.findById(id);
        Todo todo = findTodo.orElseThrow();
        // 업데이트 수행
        todo.update(request);
        return new UpdateTodoDto(todo.getTitle(), todo.getDescription(), todo.getStartTime(), todo.getEndTime());
    }

}
