package hello.toy.todoapp.member.api;

import hello.toy.todoapp.common.model.ResponseDto;
import hello.toy.todoapp.member.model.MemberResponse;
import hello.toy.todoapp.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 나의 친구 목록 조회
    @GetMapping("/friend/list")
    @Operation(summary = "나의 친구 목록 조회 api")
    @ApiResponse(
        responseCode = "200",
        description = "성공",
        content = @Content(schema = @Schema(implementation = MemberResponse.class))
    )
    public ResponseEntity<ResponseDto<List<MemberResponse>>> friendList() {
        // user id
        // header에서 jwt를 추출해서 거기서 내 user id를 받아온다고 치고~
        long userId = 1;
        ResponseDto<List<MemberResponse>> result = memberService.friendList(userId);
        return ResponseEntity.ok(result);
    }

    // 내가 친구 요청한 목록 조회
    @GetMapping("/friend/request/list")
    @Operation(summary = "내가 친구 요청한 목록 조회 api")
    @ApiResponse(
        responseCode = "200",
        description = "성공",
        content = @Content(schema = @Schema(implementation = MemberResponse.class))
    )
    public ResponseEntity<ResponseDto<List<MemberResponse>>> myRequestFriendList() {
        // user id
        // header에서 jwt를 추출해서 거기서 내 user id를 받아온다고 치고~
        long userId = 1;
        ResponseDto<List<MemberResponse>> result = memberService.myRequestFriendList(userId);
        return ResponseEntity.ok(result);
    }

    // 친구 취소하는 api
    @PutMapping("/friend/cancel")
    @Operation(summary = "친구 취소하는 api")
    @ApiResponse(
        responseCode = "200",
        description = "성공",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    public ResponseEntity<ResponseDto<Void>> friendCancel(@RequestParam long cancelFriendId) {
        memberService.friendCancel(cancelFriendId);
        return ResponseEntity.ok(null);
    }

    // 내가 차단한 친구목록 조회
    @PutMapping("/friend/blocked/list")
    @Operation(summary = "내가 차단한 친구목록 조회 api")
    @ApiResponse(
        responseCode = "200",
        description = "성공",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    public ResponseEntity<ResponseDto<List<MemberResponse>>> blockedFriendList() {
        // user id
        // header에서 jwt를 추출해서 거기서 내 user id를 받아온다고 치고~
        long userId = 1;
        ResponseDto<List<MemberResponse>> result = memberService.blockedFriendList(userId);
        return ResponseEntity.ok(result);
    }

    // 친구 차단하는 api

}
