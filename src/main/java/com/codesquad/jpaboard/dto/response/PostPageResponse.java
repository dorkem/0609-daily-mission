package com.codesquad.jpaboard.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostPageResponse {
  private Long total;
  private Long page;
  private Long perPage;
  private List<PostSummaryResponse> posts;
}
