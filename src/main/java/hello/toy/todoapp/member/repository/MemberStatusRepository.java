package hello.toy.todoapp.member.repository;

import hello.toy.todoapp.member.domain.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStatusRepository extends JpaRepository<MemberStatus, Long> {

}
