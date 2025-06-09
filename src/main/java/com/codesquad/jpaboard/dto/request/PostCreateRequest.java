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
public class PostCreateRequest {
  @NotBlank(message = "제목을 작성해주세요.")
  private String title;

  @NotBlank(message = "댓글 내용을 넣어주세요.")
  private String content;

  private String imageUrl;
}
