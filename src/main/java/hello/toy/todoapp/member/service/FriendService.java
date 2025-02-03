package hello.toy.todoapp.member.service;

import hello.toy.todoapp.common.model.ResponseDto;
import hello.toy.todoapp.member.domain.FriendsRequests;
import hello.toy.todoapp.member.domain.Member;
import hello.toy.todoapp.member.model.FriendRequest;
import hello.toy.todoapp.member.repository.FriendRepository;
import hello.toy.todoapp.member.repository.FriendRequestRepository;
import hello.toy.todoapp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
            FriendsRequests friendsRequests = new FriendsRequests();
            Optional<Member> sender = memberRepository.findById(friendRequest.getMemberId());
            Optional<Member> receiver = memberRepository.findById(friendRequest.getFriendId());

            friendsRequests.setReceiver(receiver.orElseThrow());
            friendsRequests.setSender(sender.orElseThrow());

            FriendsRequests returnEntity = friendRequestRepository.save(friendsRequests);
            responseDto = ResponseDto.builder().data(returnEntity.getId()).message("request success").success(true).build();
            // 알림 보내는 이벤트 발생 코드 삽입해야함??
        } catch (Exception e) {
            responseDto = ResponseDto.builder().message("request failed").success(false).build();
        }
        return responseDto;
    }

    public ResponseDto<Long> allow(FriendRequest friendRequest) {

        ResponseDto responseDto = null;

        try {
            FriendsRequests friendsRequests = new FriendsRequests();
            Optional<Member> sender = memberRepository.findById(friendRequest.getMemberId());
            Optional<Member> receiver = memberRepository.findById(friendRequest.getFriendId());

            friendsRequests.setReceiver(receiver.orElseThrow());
            friendsRequests.setSender(sender.orElseThrow());

            // friend request에서 삭제

            // friend 리스트에 넣기
            FriendsRequests returnEntity = friendRequestRepository.save(friendsRequests);
            responseDto = ResponseDto.builder().data(returnEntity.getId()).message("request success").success(true).build();
            // 알림 보내는 이벤트 발생 코드 삽입해야함??
        } catch (Exception e) {
            responseDto = ResponseDto.builder().message("request failed").success(false).build();
        }
        return responseDto;
    }

}
