package hello.toy.todoapp.member.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "Friend 관련 응답 DTO")
public class FriendResponse {

    @Schema(description = "friend request id")
    private long id;

    @Schema(description = "요청 대상 친구 아이디")
    private long friendId;

    @Schema(description = "요청 대상 친구 이름")
    private String friendName;

}