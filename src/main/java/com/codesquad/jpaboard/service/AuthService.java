package com.codesquad.jpaboard.service;

import org.springframework.stereotype.Service;

import com.codesquad.jpaboard.domain.User;
import com.codesquad.jpaboard.dto.request.LoginRequest;
import com.codesquad.jpaboard.dto.request.SignupRequest;
import com.codesquad.jpaboard.exception.CommonException;
import com.codesquad.jpaboard.exception.ErrorCode;
import com.codesquad.jpaboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {
  private final UserRepository userRepository;

  public void saveUser(SignupRequest request) {
    User user = new User(
        request.getLoginId(),
        request.getPassword(),
        request.getNickname(),
        request.getEmail()
    );

    userRepository.save(user);
  }

  public String login(LoginRequest request) {
    String accessTocken = "abcd1234568";

    User user = userRepository.findByLoginId(request.getLoginId())
        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

    if(!user.getPassword().equals(request.getPassword())) {
      throw new CommonException(ErrorCode.FAILURE_LOGIN);
    }

    return accessTocken;
  }
}
