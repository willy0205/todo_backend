package hello.toy.todoapp.member.api;

import hello.toy.todoapp.common.model.ResponseDto;
import hello.toy.todoapp.member.model.FriendRequest;
import hello.toy.todoapp.member.model.FriendResponse;
import hello.toy.todoapp.member.service.FriendService;
import hello.toy.todoapp.security.TokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;
    private final TokenProvider tokenProvider;

    // 친구 요청
    @PostMapping("request")
    @Operation(summary = "내가 친구 요청을 위한 요청 api")
    @ApiResponse(
        responseCode = "200",
        description = "성공",
        content = @Content(schema = @Schema(implementation = Long.class))
    )
    public ResponseEntity<ResponseDto<Long>> request(@RequestHeader("Authorization") String authorizationHeader, @RequestBody FriendRequest friendRequest) {
        // user id
        String userId = tokenProvider.getUserId(authorizationHeader);
        friendRequest.setMemberId(userId);
        ResponseDto<Long> responseDto = friendService.request(friendRequest);

        return ResponseEntity.ok(responseDto);
    }

    // 친구 요청 취소
    @PostMapping("cancel")
    @Operation(summary = "내가 한 친구 요청을 취소하기 위한 요청 api")
    @ApiResponse(
        responseCode = "200",
        description = "성공",
        content = @Content(schema = @Schema(implementation = Long.class))
    )
    public ResponseEntity<ResponseDto<Long>> cancel(@RequestHeader("Authorization") String authorizationHeader, @RequestBody FriendRequest friendRequest) {
        // user id
        // header에서 jwt를 추출해서 거기서 내 user id를 받아온다고 치고~
        String userId = tokenProvider.getUserId(authorizationHeader);
        friendRequest.setMemberId(userId);
        ResponseDto<Long> responseDto = friendService.cancel(friendRequest);

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
    public ResponseEntity<ResponseDto<Long>> allow(@RequestHeader("Authorization") String authorizationHeader, @RequestBody FriendRequest friendRequest) {
        // user id
        // header에서 jwt를 추출해서 거기서 내 user id를 받아온다고 치고~
        String userId = tokenProvider.getUserId(authorizationHeader);
        friendRequest.setMemberId(userId);
        ResponseDto<Long> responseDto = friendService.allow(friendRequest);

        return ResponseEntity.ok(responseDto);
    }

    // 친구 요청 조회(남이 나한테 한거)
    @GetMapping("request/list")
    @Operation(summary = "내게 들어온 친구요청 조회하기 위한 api")
    @ApiResponse(
        responseCode = "200",
        description = "성공",
        content = @Content(schema = @Schema(implementation = Long.class))
    )
    public ResponseEntity<ResponseDto<List<FriendResponse>>> requestList(@RequestHeader("Authorization") String authorizationHeader, @RequestBody FriendRequest friendRequest) {
        // user id
        // header에서 jwt를 추출해서 거기서 내 user id를 받아온다고 치고~
        String userId = tokenProvider.getUserId(authorizationHeader);
        friendRequest.setMemberId(userId);
        ResponseDto<List<FriendResponse>> responseDto = friendService.requestList(friendRequest);

        return ResponseEntity.ok(responseDto);
    }


}
