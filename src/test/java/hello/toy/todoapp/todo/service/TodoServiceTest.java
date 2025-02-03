package hello.toy.todoapp.todo.service;

import hello.toy.todoapp.member.repository.MemberRepository;
import hello.toy.todoapp.member.domain.Member;
import hello.toy.todoapp.todo.domain.Todo;
import hello.toy.todoapp.todo.model.CreateTodoRequest;
import hello.toy.todoapp.todo.model.SelectTodoDto;
import hello.toy.todoapp.todo.model.UpdateTodoDto;
import hello.toy.todoapp.todo.model.UpdateTodoRequest;
import hello.toy.todoapp.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void 할일생성 () throws Exception {
        // given
        Long expected = 1L;
        CreateTodoRequest request = new CreateTodoRequest(1L, "title", "description");
        Todo mockTodo = Todo.createTodo(request, null);
        mockTodo.setIdForTest(expected);
        when(todoRepository.save(any(Todo.class))).thenReturn(mockTodo);
        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(new Member()));


        // when
        Long createdId = todoService.create(request);

        //then
        assertThat(createdId).isEqualTo(expected);
        verify(todoRepository, times(1)).save(any(Todo.class));
        verify(memberRepository, times(1)).findById(anyLong());

    }

    @Test
    void 할일조회_하나 () throws Exception {
        // given
        Long id = 1L;
        SelectTodoDto expected = new SelectTodoDto("title", "description", null, null);
        Optional<Todo> mockTodo = Optional.of(Todo.createTodo("title", "description", null, null, null));
        when(todoRepository.findById(anyLong())).thenReturn(mockTodo);

        // when
        SelectTodoDto result = todoService.findOne(id);

        //then
        assertThat(result.getTitle()).isEqualTo(expected.getTitle());
        assertThat(result.getDescription()).isEqualTo(expected.getDescription());
        verify(todoRepository, times(1)).findById(id);

    }

    @Test
    void 할일조회_하나_멤버와같이 () throws Exception {
        // given
        Long id = 1L;
        Member mockMember = new Member();
        Todo mockTodo = Todo.createTodo("title", "description", null, null, mockMember);
        when(todoRepository.findOneWithMember(anyLong())).thenReturn(mockTodo);

        // when
        SelectTodoDto result = todoService.findOneWithMember(id);

        //then
        assertThat(result.getTitle()).isEqualTo(mockTodo.getTitle());
        assertThat(result.getDescription()).isEqualTo(mockTodo.getDescription());
        assertThat(result.getName()).isEqualTo(null);
        verify(todoRepository, times(1)).findOneWithMember(anyLong());

    }

    @Test
    void 할일조회_all () throws Exception {
        // given
        List<Todo> mockTodos = new ArrayList<>();
        mockTodos.add(Todo.createTodo("title1", "description1", null, null, null));
        mockTodos.add(Todo.createTodo("title2", "description2", null, null, null));

        when(todoRepository.findAll()).thenReturn(mockTodos);

        // when
        List<SelectTodoDto> result = todoService.findAll();

        //then
        assertThat(result.size()).isEqualTo(mockTodos.size());
        verify(todoRepository, times(1)).findAll();
    }

    @Test
    void 할일수정 () throws Exception {
        // given
        Long id = 1L;
        UpdateTodoRequest request = new UpdateTodoRequest("update", "updateDescription", null);
        Optional<Todo> mockTodo = Optional.of(Todo.createTodo("title", "description", null, null, null));
        when(todoRepository.findById(anyLong())).thenReturn(mockTodo);

        // when
        UpdateTodoDto result = todoService.update(id, request);

        //then
        assertThat(result.getTitle()).isEqualTo(request.getTitle());
        assertThat(result.getDescription()).isEqualTo(request.getDescription());
        verify(todoRepository, times(1)).findById(anyLong());

    }

}