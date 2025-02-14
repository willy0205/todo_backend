package hello.toy.todoapp.member.model;

import hello.toy.todoapp.member.domain.Friends;
import hello.toy.todoapp.member.domain.FriendsRequests;
import hello.toy.todoapp.member.domain.Member;
import hello.toy.todoapp.member.domain.MemberStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberResponse {

    @Schema(description = "friend id")
    private long friendId;
    @Schema(description = "member id")
    private long id;
    @Schema(description = "member name")
    private String name;
    @Schema(description = "member login id")
    private String loginId;

    public static MemberResponse of(Friends friends) {
        Member member = friends.getMember();
        return MemberResponse.builder()
            .friendId(friends.getId())
            .id(member.getId())
            .name(member.getName())
            .loginId(member.getLoginId())
            .build();
    }

    public static MemberResponse of(FriendsRequests friendsRequests) {
        Member member = friendsRequests.getReceiver();
        return MemberResponse.builder()
            .id(member.getId())
            .name(member.getName())
            .loginId(member.getLoginId())
            .build();
    }

    public static MemberResponse of(MemberStatus memberStatus) {
        Member member = memberStatus.getMember();
        return MemberResponse.builder()
            .id(member.getId())
            .name(member.getName())
            .loginId(member.getLoginId())
            .build();
    }
}
