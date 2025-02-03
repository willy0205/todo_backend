package hello.toy.todoapp.member.domain;

import hello.toy.todoapp.member.enums.FirendRequestStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "FRIENDS_ID")
    private Long id;

    @JoinColumn(name = "MEMBER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "FRIEND_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member friend;

    private FirendRequestStatus status;


}
