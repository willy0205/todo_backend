package hello.toy.todoapp.member.repository;

import hello.toy.todoapp.member.domain.Authority;
import hello.toy.todoapp.member.enums.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findAuthorityByAuthorityName(AuthorityEnum authorityName);
}
