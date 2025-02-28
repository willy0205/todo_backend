package hello.toy.todoapp.member.repository;

import hello.toy.todoapp.member.domain.Authority;
import hello.toy.todoapp.member.enums.AuthorityEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorityDataInitializer implements CommandLineRunner, Ordered {

    private final AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        if (authorityRepository.count() == 0) {
            Authority normal = Authority.builder()
                .authorityName(AuthorityEnum.NORMAL)
                .build();

            authorityRepository.save(normal);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
