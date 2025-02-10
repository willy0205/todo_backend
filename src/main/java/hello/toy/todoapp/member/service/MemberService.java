package hello.toy.todoapp.member.service;

import hello.toy.todoapp.common.model.ResponseDto;
import hello.toy.todoapp.member.domain.Friends;
import hello.toy.todoapp.member.domain.FriendsRequests;
import hello.toy.todoapp.member.domain.Member;
import hello.toy.todoapp.member.enums.FriendRequestStatus;
import hello.toy.todoapp.member.model.MemberResponse;
import hello.toy.todoapp.member.repository.FriendRequestRepository;
import hello.toy.todoapp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final FriendRequestRepository friendRequestRepository;

    // 나의 친구 목록 조회
    public ResponseDto<List<MemberResponse>> friendList(long userId) {

        ResponseDto responseDto = null;

        try {
            Member member = memberRepository.findById(userId).orElseThrow();
            List<Friends> firendList = member.getFriends();
            List<MemberResponse> returnList = firendList.stream().map(MemberResponse::of).toList();
            responseDto = ResponseDto.builder().data(returnList).message("request success").success(true).build();
        } catch (Exception e) {
            responseDto = ResponseDto.builder().message("request failed").success(false).build();
        }

        return responseDto;
    }

    // 내가 친구 요청한 목록 조회
    public ResponseDto<List<MemberResponse>> myRequestFriendList(long userId) {
        ResponseDto responseDto = null;

        try {
            Member member = memberRepository.findById(userId).orElseThrow();
            List<FriendsRequests> friendsRequestSendList = member.getFriendsRequestSendList();
            List<MemberResponse> returnList = friendsRequestSendList.stream().map(MemberResponse::of).toList();
            responseDto = ResponseDto.builder().data(returnList).message("request success").success(true).build();
        } catch (Exception e) {
            responseDto = ResponseDto.builder().message("request failed").success(false).build();
        }
        return responseDto;
    }

    // 친구 취소하는 api
    public ResponseDto<Void> friendCancel(long cancelFriendId) {
        ResponseDto responseDto = null;

        try {
            FriendsRequests myFriendsRequests = friendRequestRepository.findById(cancelFriendId).orElseThrow();
            myFriendsRequests.setStatus(FriendRequestStatus.CANCEL);
            Member sender = myFriendsRequests.getSender();
            Member receiver = myFriendsRequests.getReceiver();

            FriendsRequests friendFriendMember = friendRequestRepository.findBySenderAndReceiver(receiver, sender).orElseThrow();
            friendFriendMember.setStatus(FriendRequestStatus.CANCEL);

            responseDto = ResponseDto.builder().message("request success").success(true).build();
        } catch (Exception e) {
            responseDto = ResponseDto.builder().message("request failed").success(false).build();
        }
        return responseDto;
    }

    // 내가 차단한 친구목록 조회
    public ResponseDto<List<MemberResponse>> blockedFriendList(long userId) {
        ResponseDto responseDto = null;

        try {
            Member member = memberRepository.findById(userId).orElseThrow();
            List<MemberResponse> result = member.getBlockedList().stream().map(MemberResponse::of).toList();
            responseDto = ResponseDto.builder().data(result).message("request success").success(true).build();
        } catch (Exception e) {
            responseDto = ResponseDto.builder().message("request failed").success(false).build();
        }
        return responseDto;
    }

    // 친구 차단하는 api

}
