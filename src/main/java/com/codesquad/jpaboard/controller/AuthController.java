package com.codesquad.jpaboard.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.codesquad.jpaboard.dto.global.ResponseDto;
import com.codesquad.jpaboard.dto.request.LoginRequest;
import com.codesquad.jpaboard.dto.request.SignupRequest;
import com.codesquad.jpaboard.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {
  private final AuthService authService;
  @PostMapping("/api/signup")
  public ResponseDto<?> signup(@RequestBody SignupRequest request) {
    authService.saveUser(request);
    return ResponseDto.ok(null);
  }

  @PostMapping("/api/login")
  public ResponseDto<?> login(@RequestBody LoginRequest request) {
    return ResponseDto.ok(authService.login(request));
  }
}
