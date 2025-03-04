package hello.toy.todoapp.member.domain;

import hello.toy.todoapp.todo.domain.Todo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "MEMBER_ID")
    private Long id;

    // 사용자가 사용할 이름
    @Column(nullable = false)
    private String name;

    // 계정 아이디
    @Column(nullable = false, unique = true)
    private String loginId;

    private String password;

    @JoinColumn(name = "authority_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Authority authority;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Friends> friends;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<FriendsRequests> friendsRequestSendList;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<FriendsRequests> friendsRequestReceiveList;

    @OneToMany(mappedBy = "blocked", cascade = CascadeType.ALL)
    private List<MemberStatus> blockedList;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Todo> todoList;

}
