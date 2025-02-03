package hello.toy.todoapp.member.repository;

import hello.toy.todoapp.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
