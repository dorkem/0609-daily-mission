package com.codesquad.jpaboard.service;

import org.springframework.stereotype.Service;

import com.codesquad.jpaboard.common.JwtTokenProvider;
import com.codesquad.jpaboard.domain.User;
import com.codesquad.jpaboard.dto.request.LoginRequest;
import com.codesquad.jpaboard.dto.request.SignupRequest;
import com.codesquad.jpaboard.dto.response.LoginResponse;
import com.codesquad.jpaboard.exception.CommonException;
import com.codesquad.jpaboard.exception.ErrorCode;
import com.codesquad.jpaboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {
  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;

  //todo: 존재하는 아이디, 닉네임인지 검사하는 로직

  public void saveUser(SignupRequest request) {
    User user = new User(
        request.getLoginId(),
        request.getPassword(),
        request.getNickname(),
        request.getEmail()
    );

    userRepository.save(user);
  }

  public LoginResponse login(LoginRequest request) {
    User user = userRepository.findByLoginId(request.getLoginId())
        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

    if(!user.getPassword().equals(request.getPassword())) {
      throw new CommonException(ErrorCode.FAILURE_LOGIN);
    }

    String accessToken = jwtTokenProvider.createToken(user.getId(), user.getNickname());

    return new LoginResponse(accessToken);
  }
}
