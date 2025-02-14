package hello.toy.todoapp.member.service;

import hello.toy.todoapp.common.model.ResponseDto;
import hello.toy.todoapp.member.domain.Friends;
import hello.toy.todoapp.member.domain.FriendsRequests;
import hello.toy.todoapp.member.domain.Member;
import hello.toy.todoapp.member.enums.FriendRequestStatus;
import hello.toy.todoapp.member.model.FriendRequest;
import hello.toy.todoapp.member.model.FriendResponse;
import hello.toy.todoapp.member.repository.FriendRepository;
import hello.toy.todoapp.member.repository.FriendRequestRepository;
import hello.toy.todoapp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final MemberRepository memberRepository;

    public ResponseDto<Long> request(FriendRequest friendRequest) {

        ResponseDto responseDto = null;

        try {

            // 블락된 사용자인지 확인하는 로직 추가하기

            FriendsRequests friendsRequests = new FriendsRequests();
            Optional<Member> sender = memberRepository.findById(friendRequest.getMemberId());
            Optional<Member> receiver = memberRepository.findById(friendRequest.getFriendId());

            friendsRequests.setReceiver(receiver.orElseThrow());
            friendsRequests.setSender(sender.orElseThrow());
            friendsRequests.setStatus(FriendRequestStatus.REQUEST);

            FriendsRequests returnEntity = friendRequestRepository.save(friendsRequests);
            responseDto = ResponseDto.builder().data(returnEntity.getId()).message("request success").success(true).build();
            // 알림 보내는 이벤트 발생 코드 삽입해야함??
        } catch (Exception e) {
            responseDto = ResponseDto.builder().message("request failed").success(false).build();
        }
        return responseDto;
    }

    public ResponseDto<Long> cancel(FriendRequest friendRequest) {

        ResponseDto responseDto = null;

        try {

            FriendsRequests friendsRequests = friendRequestRepository.findById(friendRequest.getId()).orElseThrow();
            friendsRequests.setStatus(FriendRequestStatus.CANCEL);
            responseDto = ResponseDto.builder().data(friendRequest.getId()).message("request success").success(true).build();
            // 알림 보내는 이벤트 발생 코드 삽입해야함??
        } catch (Exception e) {
            responseDto = ResponseDto.builder().message("request failed").success(false).build();
        }
        return responseDto;
    }

    public ResponseDto<Long> allow(FriendRequest friendRequest) {

        ResponseDto responseDto = null;

        try {
            // 리퀘스트 꺼내기
            FriendsRequests friendsRequests = friendRequestRepository.findById(friendRequest.getId()).orElseThrow();
            // 리퀘스트 상태를 allow로 변경
            friendsRequests.setStatus(FriendRequestStatus.ALLOW);

            // 신청자와 피신청자 꺼내기
            Member sender = friendsRequests.getSender();
            Member receiver = friendsRequests.getReceiver();

            // 신청자와 피신청자를 각각의 친구로 추가

            Friends senderFriends = new Friends();
            senderFriends.setMember(sender);
            senderFriends.setFriend((receiver));

            Friends receiverFriends = new Friends();
            receiverFriends.setMember(receiver);
            receiverFriends.setFriend((sender));

            List<Friends> list = List.of(senderFriends,receiverFriends);
            friendRepository.saveAll(list);

            responseDto = ResponseDto.builder().data(null).message("request success").success(true).build();
            // 알림 보내는 이벤트 발생 코드 삽입해야함??
        } catch (Exception e) {
            responseDto = ResponseDto.builder().message("request failed").success(false).build();
        }
        return responseDto;
    }

    public ResponseDto<List<FriendResponse>> requestList(FriendRequest friendRequest) {

        ResponseDto responseDto = null;

        try {
            Member me = memberRepository.findById(friendRequest.getMemberId()).orElseThrow();
            List<FriendResponse> returnList = friendRequestRepository.findAllByReceiverAndStatus(me, FriendRequestStatus.REQUEST)
                .orElseThrow()
                .stream().map(friendsRequests -> {
                    String friendRequesterName = friendsRequests.getSender().getName();
                    return FriendResponse.builder()
                        .id(friendsRequests.getId())
                        .friendId(friendsRequests.getSender().getId())
                        .friendName(friendRequesterName)
                        .build();
                }).toList();

            responseDto = ResponseDto.builder().data(returnList).message("request success").success(true).build();
        } catch (Exception e) {
            responseDto = ResponseDto.builder().message("request failed").success(false).build();
        }
        return responseDto;
    }

}
