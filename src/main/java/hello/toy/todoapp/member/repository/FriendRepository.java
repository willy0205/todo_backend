package hello.toy.todoapp.member.repository;

import hello.toy.todoapp.member.domain.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friends, Long> {

}
