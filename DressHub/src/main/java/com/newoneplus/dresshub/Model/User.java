package com.newoneplus.dresshub.Model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class User {
    @NotNull
    @Size(min=4, max=16, message="사용자 아이디는 4자리 이상 16자 이하만 가능 합니다.")
    private String id;
    @NotNull
    @Pattern(regexp="([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9]){10,16}" ,message="숫자 영문자 특수 문자를 포함한 10 ~ 16 자를 입력하세요. ")
    private String password;
    @NotNull
    private String name;
    @NotNull
    @Pattern(regexp="^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$" ,message="올바른 이메일을 입력하세요.")
    private String email;
    private int userType;
    private String address;
    @NotNull
    private String phoneNumber;
    private String nickname;
    private String introduce;
    private boolean openPrivateInfo;
    private boolean certification;
    private Date resisterDate;


}
