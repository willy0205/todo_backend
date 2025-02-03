package hello.toy.todoapp.todo.api;

import hello.toy.todoapp.common.model.ResponseDto;
import hello.toy.todoapp.todo.model.CreateTodoRequest;
import hello.toy.todoapp.todo.model.SelectTodoDto;
import hello.toy.todoapp.todo.model.UpdateTodoDto;
import hello.toy.todoapp.todo.model.UpdateTodoRequest;
import hello.toy.todoapp.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    /**
     * 할 일 생성
     */
    @PostMapping("/todo")
    public ResponseEntity<ResponseDto<Long>> create(@RequestBody CreateTodoRequest request) {

        ResponseDto<Long> res = null;

        try {
            Long todoId = todoService.create(request);
            res = ResponseDto.of(true, "success create todo", todoId);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch (Exception e) {
            res = ResponseDto.of(false, "fail create todo", -1L, e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

    /**
     * 할 일 수정
     */
    @PutMapping("/todo/{id}")
    public ResponseEntity<ResponseDto<UpdateTodoDto>> update(@RequestBody UpdateTodoRequest updateTodoRequest,
                                                             @PathVariable("id") Long id) {
        ResponseDto<UpdateTodoDto> res = null;

        try {
            UpdateTodoDto updateDto = todoService.update(id, updateTodoRequest);
            res = ResponseDto.of(true, "success update todo", updateDto);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res = ResponseDto.of(false, "fail to update todo", null, e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }

    }

    /**
     * 할 일 조회 (하나)
     */

    @GetMapping("/todo/{id}")
    public ResponseEntity<ResponseDto<SelectTodoDto>> findOne(@PathVariable("id") Long id) {

        ResponseDto<SelectTodoDto> res = null;

        try {
            // path variable로 투두 조회
            SelectTodoDto todoDto = todoService.findOne(id);
            res = ResponseDto.of(true, "success find one todo", todoDto);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res = ResponseDto.of(false, "fail to find one todo", null, e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

    /**
     * 할 일 하나 조회 (멤버도 같이)
     *  계층구조로 설계해야하나 vs query parameter 로 넘겨야하나 ex) /todo/{id}?userId=loginId
     *  쿼리 파라미터로 보내면 유저가 임의 값을 넣어서 하면 안될 듯 한데 잘 모르겠음ㅋㅋ
     */
    @GetMapping("/todo/{id}/member")
    public ResponseEntity<ResponseDto<SelectTodoDto>> findOneWithMember(@PathVariable("id") Long id) {
        ResponseDto<SelectTodoDto> res = null;
        try {
            SelectTodoDto todoDto = todoService.findOneWithMember(id);
            res = ResponseDto.of(true, "success find one todo with member", todoDto);
            return ResponseEntity.ok(res);

        } catch (Exception e) {
            res = ResponseDto.of(false, "fail to find one todo with member", null, e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

    /**
     * 할 일 조회 (전체)
     */
    @GetMapping("/todo")
    public ResponseEntity<ResponseDto<List<SelectTodoDto>>> findAll() {
        ResponseDto<List<SelectTodoDto>> res = null;

        try {
            List<SelectTodoDto> todos = todoService.findAll();
            res = ResponseDto.of(true, "success find all", todos);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res = ResponseDto.of(false, "fail to find all", null, e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

    /**
     * 전체 조회에 멤버도 필요 ?
     */

}
