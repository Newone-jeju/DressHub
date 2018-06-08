package com.newoneplus.dresshub.Model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity(name = "user")
public class User {
    // User VO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
//    @Size(min=4, max=16, message="사용자 아이디는 4자리 이상 16자 이하만 가능 합니다.")
    private String uid;
    @Column
//    @Pattern(regexp="([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9]){10,16}" ,message="숫자 영문자 특수 문자를 포함한 10 ~ 16 자를 입력하세요. ")
    private String password;
    @Column
    private String name;
    @Column
//    @Pattern(regexp="^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$" ,message="올바른 이메일을 입력하세요.")
    private String email;
    @Column
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
