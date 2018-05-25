package com.newoneplus.dresshub.Model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
//@Table(name = "member")
@EqualsAndHashCode(of = "uid")
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 20)
    private String uid;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, unique = true, length = 255)
    private String phoneNumber;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = true, length = 255)
    private String introduce;

    @CreationTimestamp
    private Date resisterDate;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="uid")
    private List<MemberRole> roles;

}
