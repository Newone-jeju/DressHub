package com.newoneplus.dresshub.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Member {
    // User VO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    @Column
//    @Size(min=4, max=16, message="사용자 아이디는 4자리 이상 16자 이하만 가능 합니다.")
    private String uid;
    @Column
    @JsonIgnore
//    @Pattern(regexp="([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9]){10,16}" ,message="숫자 영문자 특수 문자를 포함한 10 ~ 16 자를 입력하세요. ")

    private String password;
    @Column
    private String name;
    @Column
//    @Pattern(regexp="^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$" ,message="올바른 이메일을 입력하세요.")
    private String email;

    @Column
    @JsonIgnore
    private int userType;
    @Column
    private String address;
    @Column
    private String phoneNumber;
    @Column
    private String nickname;
    @Column
    private String introduce;
    @CreationTimestamp
    private Date resisterDate;

}
