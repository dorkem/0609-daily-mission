package com.codesquad.jpaboard.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
  @NotBlank(message = "닉네임을 입력해야 합니다.")
  private String nickname;

  @NotBlank(message = "이메일을 입력해야 합니다.")
  private String email;

  @NotBlank(message = "아이디를 입력해야 합니다.")
  private String loginId;

  @NotBlank(message = "비밀번호를 입력해야 합니다.")
  private String password;
}
