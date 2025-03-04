package hello.toy.todoapp.todo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.toy.todoapp.security.TestSecurityConfig;
import hello.toy.todoapp.todo.model.CreateTodoRequest;
import hello.toy.todoapp.todo.model.SelectTodoDto;
import hello.toy.todoapp.todo.model.UpdateTodoDto;
import hello.toy.todoapp.todo.model.UpdateTodoRequest;
import hello.toy.todoapp.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
@Import(TestSecurityConfig.class)
class TodoControllerTest {
    // test
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TodoService todoService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void 할일등록 () throws Exception {

        //given
        CreateTodoRequest request =
            new CreateTodoRequest(1L,
                "test",
                "description");

        // 요청 바디 json 변환
        String jsonStr = mapper.writeValueAsString(request);

        Long expectedData = 1L;

        // when
        when(todoService.create(any(CreateTodoRequest.class))).thenReturn(expectedData);

        // then
        mockMvc.perform(
            post("/api/todo")                   // post 요청
                .contentType(MediaType.APPLICATION_JSON)   // json type 타입
                .content(jsonStr))                         // 요청 바디 데이터
            .andExpect(status().isCreated())               // created 상태 리턴 기대
            .andExpect(jsonPath("$.data").value(expectedData)); // 응답 값 data에 1 있나요?

    }

    @Test
    void 할일조회_하나() throws Exception {
        // given
        Long id = 1L;
        SelectTodoDto expectedDto = new SelectTodoDto("unit", "unit test", null, null, null);
        // when
        when(todoService.findOne(id)).thenReturn(expectedDto);

        // then
        mockMvc.perform(
            get("/api/todo/{id}", id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.title").value(expectedDto.getTitle()))
            .andExpect(jsonPath("$.data.description").value(expectedDto.getDescription()));
    }

    @Test
    void 할일조회_하나_멤버 () throws Exception {
        // given
        Long id = 1L;
        SelectTodoDto expectedDto = new SelectTodoDto("unit", "unit test", null, null, "name");

        // when
        when(todoService.findOneWithMember(id)).thenReturn(expectedDto);

        // then
        mockMvc.perform(
            get("/api/todo/{id}/member", id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.title").value(expectedDto.getTitle()))
            .andExpect(jsonPath("$.data.description").value(expectedDto.getDescription()))
            .andExpect(jsonPath("$.data.name").value(expectedDto.getName()));
    }

    @Test
    void 할일조회_all () throws Exception {
        // given
        List<SelectTodoDto> todos = new ArrayList<>();
        todos.add(new SelectTodoDto("test1", "description1", null, null, null));
        todos.add(new SelectTodoDto("test2", "description2", null, null, null));

        // when
        when(todoService.findAll()).thenReturn(todos);

        // then
        mockMvc.perform(
            get("/api/todo"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.size()").value(todos.size()));

    }

    @Test
    void 할일수정 () throws Exception {
        // given
        Long id = 1L;
        String title = "update test";
        String description = "update description";
        UpdateTodoRequest request = new UpdateTodoRequest(title, description, null);
        UpdateTodoDto expectedDto = new UpdateTodoDto();
        expectedDto.setTitle(title);
        expectedDto.setDescription(description);

        String jsonStr = mapper.writeValueAsString(request);

        // when
        when(todoService.update(id, request)).thenReturn(expectedDto);

        //then
        mockMvc.perform(
            put("/api/todo/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.title").value(expectedDto.getTitle()))
            .andExpect(jsonPath("$.data.description").value(expectedDto.getDescription()));

    }

}