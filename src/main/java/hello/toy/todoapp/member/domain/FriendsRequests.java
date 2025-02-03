package hello.toy.todoapp.member.domain;

import hello.toy.todoapp.member.enums.FriendRequestStatus;
import hello.toy.todoapp.member.model.FriendRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
public class FriendsRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "REQUEST_ID")
    private Long id;

    @JoinColumn(name = "SENDER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member sender;

    @JoinColumn(name = "RECEIVER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member receiver;

    private FriendRequestStatus status;

}
