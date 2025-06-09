package com.codesquad.jpaboard.common;

import com.codesquad.jpaboard.exception.CommonException;
import com.codesquad.jpaboard.exception.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;

public class AuthUtils {

  public static Long extractUserId(HttpServletRequest request) {
    Object userId = request.getAttribute("jwt.userId");
    if (userId == null) {
      throw new CommonException(ErrorCode.ACCESS_DENIED);
    }
    return (Long) userId;
  }
}
