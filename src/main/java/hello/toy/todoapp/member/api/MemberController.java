package hello.toy.todoapp.member.api;

import hello.toy.todoapp.common.model.ResponseDto;
import hello.toy.todoapp.member.model.FriendRequest;
import hello.toy.todoapp.member.model.LoginRequestDto;
import hello.toy.todoapp.member.model.MemberResponse;
import hello.toy.todoapp.member.model.UserSignUpRequestDto;
import hello.toy.todoapp.member.service.MemberService;
import hello.toy.todoapp.security.JwtFilter;
import hello.toy.todoapp.security.TokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/login")
    public ResponseDto<String> authorize(@Valid @RequestBody LoginRequestDto loginDto) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getLoginId(), loginDto.getPassword());

            // authenticate 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            // 해당 객체를 SecurityContextHolder에 저장하고
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
            String jwt = tokenProvider.createToken(authentication);

            HttpHeaders httpHeaders = new HttpHeaders();
            // response header에 jwt token에 넣어줌
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

            // tokenDto를 이용해 response body에도 넣어서 리턴
            return ResponseDto.<String>builder().data(jwt).message("request success").success(true).build();
        } catch (Exception e) {
            return ResponseDto.<String>builder().message("request failed").success(false).build();
        }

    }

    @PostMapping("/signup")
    public ResponseDto<Void> signup(@RequestBody UserSignUpRequestDto userDto) {

        return memberService.signup(userDto);
    }

    // 나의 친구 목록 조회
    @GetMapping("/friend/list")
    @Operation(summary = "나의 친구 목록 조회 api")
    @ApiResponse(
        responseCode = "200",
        description = "성공",
        content = @Content(schema = @Schema(implementation = MemberResponse.class))
    )
    public ResponseEntity<ResponseDto<List<MemberResponse>>> friendList(@RequestHeader("Authorization") String authorizationHeader) {
        // user id
        // header에서 jwt를 추출해서 거기서 내 user id를 받아온다고 치고~
        String userId = tokenProvider.getUserId(authorizationHeader);
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
    public ResponseEntity<ResponseDto<List<MemberResponse>>> myRequestFriendList(@RequestHeader("Authorization") String authorizationHeader) {
        // user id
        // header에서 jwt를 추출해서 거기서 내 user id를 받아온다고 치고~
        String userId = tokenProvider.getUserId(authorizationHeader);
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
    @GetMapping("/friend/blocked/list")
    @Operation(summary = "내가 차단한 친구목록 조회 api")
    @ApiResponse(
        responseCode = "200",
        description = "성공",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    public ResponseEntity<ResponseDto<List<MemberResponse>>> blockedFriendList(@RequestHeader("Authorization") String authorizationHeader) {
        // user id
        // header에서 jwt를 추출해서 거기서 내 user id를 받아온다고 치고~
        String userId = tokenProvider.getUserId(authorizationHeader);
        ResponseDto<List<MemberResponse>> result = memberService.blockedFriendList(userId);
        return ResponseEntity.ok(result);
    }

    // 친구 차단하는 api
    @PostMapping("/friend/block")
    @Operation(summary = "친구 차단하는 api")
    @ApiResponse(
        responseCode = "200",
        description = "성공",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    public ResponseEntity<ResponseDto<List<MemberResponse>>> blockFriend(@RequestHeader("Authorization") String authorizationHeader, @RequestBody FriendRequest friendRequest) {
        // user id
        // header에서 jwt를 추출해서 거기서 내 user id를 받아온다고 치고~
        String userId = tokenProvider.getUserId(authorizationHeader);
        friendRequest.setMemberId(userId);
        ResponseDto<List<MemberResponse>> result = memberService.blockFriend(friendRequest);
        return ResponseEntity.ok(result);
    }
}
