package com.codesquad.jpaboard.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
  private Long id;
  private String title;
  private String content;
  private String imageUrl;
  private String writer;
  private List<CommentResponse> comments;
}
