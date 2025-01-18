package hello.toy.todoapp.fluttercalltest.repository;

import hello.toy.todoapp.fluttercalltest.domain.Call;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRepository extends JpaRepository<Call, Long> {
}
