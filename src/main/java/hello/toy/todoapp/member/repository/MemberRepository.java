package hello.toy.todoapp.member.repository;

import hello.toy.todoapp.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByLoginId(String loginId);

    boolean existsMemberByLoginId(String loginId);
}
