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
public class LoginRequest {
  @NotBlank(message = "ID를 입력하세요.")
  private String loginId;

  @NotBlank(message = "PW를 입력하세요.")
  private String password;
}
