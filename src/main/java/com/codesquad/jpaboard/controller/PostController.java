package com.codesquad.jpaboard.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codesquad.jpaboard.common.AuthUtils;
import com.codesquad.jpaboard.dto.global.ResponseDto;
import com.codesquad.jpaboard.dto.request.CommentRequest;
import com.codesquad.jpaboard.dto.request.PostCreateRequest;
import com.codesquad.jpaboard.repository.PostRepository;
import com.codesquad.jpaboard.service.CommentService;
import com.codesquad.jpaboard.service.PostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {
  private final PostService postService;

  @GetMapping
  public ResponseDto<?> getPost() {
    return ResponseDto.ok(postService.getAllPosts());
  }

  @PostMapping
  public ResponseDto<?> createPost(
      @Valid @RequestBody PostCreateRequest postRequest
  ) {
    //임시 지정 나중엔 세션이나 뭐나 가져오기
    Long userId = 1L;
    return ResponseDto.created(postService.createPost(postRequest, userId));
  }

  @GetMapping("/{postId}")
  public ResponseDto<?> getPostById(@PathVariable Long postId) {
    return ResponseDto.ok(postService.getIssueById(postId));
  }

  @PutMapping("/{postId}")
  public ResponseDto<?> updatePost(
      @RequestBody PostCreateRequest postRequest,
      @PathVariable Long postId,
      HttpServletRequest httpRequest
  ){
    Long userId = AuthUtils.extractUserId(httpRequest);
    postService.updateById(postRequest, postId, userId);
    return ResponseDto.ok(null);
  }

  @DeleteMapping("/{postId}")
  public ResponseDto<?> deletePost(
      @PathVariable Long postId,
      HttpServletRequest httpRequest
  ) {
    Long userId = AuthUtils.extractUserId(httpRequest);
    postService.deleteById(userId, postId);
    return ResponseDto.ok(null);
  }

  @PostMapping("/{postId}/comment")
  public ResponseDto<?> addComment(
      @PathVariable Long postId,
      @RequestBody CommentRequest request
  ){
    //임시 지정 나중엔 세션이나 뭐나 가져오기
    Long userId = 1L;
    return ResponseDto.ok(postService.addComment(request, userId, postId));
  }
}
