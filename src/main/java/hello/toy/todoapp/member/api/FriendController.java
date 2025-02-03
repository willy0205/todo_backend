package hello.toy.todoapp.member.api;

import hello.toy.todoapp.common.model.ResponseDto;
import hello.toy.todoapp.member.model.FriendRequest;
import hello.toy.todoapp.member.service.FriendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // 친구 요청
    @PostMapping("request")
    @Operation(summary = "친구요청을 위한 요청 api")
    @ApiResponse(
        responseCode = "200",
        description = "성공",
        content = @Content(schema = @Schema(implementation = Long.class))
    )
    public ResponseEntity<ResponseDto<Long>> request(@RequestBody FriendRequest friendRequest) {
        // user id
        // header에서 jwt를 추출해서 거기서 내 user id를 받아온다고 치고~
        long userId = 1;
        friendRequest.setMemberId(userId);
        ResponseDto<Long> responseDto = friendService.request(friendRequest);

        return ResponseEntity.ok(responseDto);
    }

    // 친구 요청 수락
    @PostMapping("allow")
    @Operation(summary = "친구요청을 수락하기 위한 요청 api")
    @ApiResponse(
        responseCode = "200",
        description = "성공",
        content = @Content(schema = @Schema(implementation = Long.class))
    )
    public ResponseEntity<ResponseDto<Long>> allow(@RequestBody FriendRequest friendRequest) {
        // user id
        // header에서 jwt를 추출해서 거기서 내 user id를 받아온다고 치고~
        long userId = 1;
        friendRequest.setMemberId(userId);
//        ResponseDto<Long> responseDto = friendService.request(friendRequest);

//        return ResponseEntity.ok(responseDto);
        return null;
    }

    // 친구 요청 취소

    // 친구 요청 조회(남이 나한테 한거)

    //

}
