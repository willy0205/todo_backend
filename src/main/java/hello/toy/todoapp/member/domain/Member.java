package hello.toy.todoapp.member.domain;

import hello.toy.todoapp.todo.domain.Todo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    //  @Column(name = "LOGIN_ID")
    private String loginId;

    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Friends> friends;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<FriendsRequests> friendsRequestSendList;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<FriendsRequests> friendsRequestReceiveList;

    @OneToMany(mappedBy = "blocked", cascade = CascadeType.ALL)
    private List<MemberStatus> blockedList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Todo> todoList;

}
