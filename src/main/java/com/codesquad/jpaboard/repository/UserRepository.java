package com.codesquad.jpaboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codesquad.jpaboard.domain.User;

import jakarta.validation.constraints.NotBlank;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByLoginId(@NotBlank(message = "ID를 입력하세요.") String loginId);
}
