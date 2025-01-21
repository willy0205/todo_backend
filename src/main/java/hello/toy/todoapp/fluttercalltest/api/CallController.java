package hello.toy.todoapp.fluttercalltest.api;

import hello.toy.todoapp.fluttercalltest.domain.Call;
import hello.toy.todoapp.fluttercalltest.repository.CallRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CallController {
    private final CallRepository callRepository;
    private int num = 0;


    @GetMapping("/call")
    @Operation(summary = "테스트 용 get requst", description = "문찬호씨가 테스트 용으로 만든 get request입니다. 많은 관심 부탁드립니다.")
    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = Call.class))
    )
    public Optional<Call> findCall(
        @Parameter(description = "조회할 id를 입력합니다.") @RequestParam("id") Long id) {
        System.out.println("CallController.findCall");
        return callRepository.findById(id);
    }


    @PostMapping("/call")
    @Operation(summary = "테스트 용으로 id를 입력합니다. request만 날리면 알아서 1씩 증가시키면서 id 가 들어갑니다.")
    @ApiResponse(
        responseCode = "200",
        description = "성공",
        content = @Content(schema = @Schema(implementation = String.class))
    )
    public String  save() {
        System.out.println("CallController.save");
        Call call = new Call();
        num++;
        call.setDescription("callTestDescription" + num);
        call.setTitle("callTestTitle" + num);
        callRepository.save(call);
        return call.getDescription();
    }

}
