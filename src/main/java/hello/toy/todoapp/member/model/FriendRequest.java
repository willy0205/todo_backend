package hello.toy.todoapp.member.model;

import hello.toy.todoapp.member.enums.FriendRequestStatus;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Friend 관련 요청 DTO")
public class FriendRequest {

    @Schema(description = "friend request id")
    private long id;

    @Schema(description = "멤버 아이디")
    @Hidden
    private String memberId;

    @Schema(description = "요청 대상 친구 아이디")
    private String friendId;

    @Schema(description = "요청 상태")
    @Hidden
    private FriendRequestStatus status;


}
