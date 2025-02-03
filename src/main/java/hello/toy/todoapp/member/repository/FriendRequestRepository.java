package hello.toy.todoapp.member.repository;

import hello.toy.todoapp.member.domain.FriendsRequests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRequestRepository extends JpaRepository<FriendsRequests, Long> {
}
