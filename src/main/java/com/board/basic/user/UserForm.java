package com.board.basic.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
    @NotEmpty(message = "아이디를 입력하세요.")
    @Size(min = 4, max = 15)
    private String username;
    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password1;
    @NotEmpty(message = "비밀번호확인을 입력하세요.")
    private String password2;
    @NotEmpty(message = "닉네임을 입력하세요.")
    private String nickname;
}
