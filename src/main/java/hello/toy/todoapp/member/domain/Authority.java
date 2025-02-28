package hello.toy.todoapp.member.domain;

import hello.toy.todoapp.member.enums.AuthorityEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "authority_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority_name", length = 50, unique = true)
    private AuthorityEnum authorityName;

    @OneToMany(mappedBy = "authority",fetch = FetchType.LAZY)
    private List<Member> members;

}