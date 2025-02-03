package hello.toy.todoapp.member.repository;

import hello.toy.todoapp.member.domain.FriendsRequests;
import hello.toy.todoapp.member.domain.Member;
import hello.toy.todoapp.member.enums.FriendRequestStatus;
import hello.toy.todoapp.member.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendsRequests, Long> {

    Optional<List<FriendsRequests>> findAllByReceiverAndStatus(Member receiver, FriendRequestStatus status);

}
