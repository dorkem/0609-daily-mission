package com.codesquad.jpaboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.webmvc.core.service.RequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.codesquad.jpaboard.domain.Comment;
import com.codesquad.jpaboard.domain.Post;
import com.codesquad.jpaboard.domain.User;
import com.codesquad.jpaboard.dto.request.CommentRequest;
import com.codesquad.jpaboard.dto.request.PostCreateRequest;
import com.codesquad.jpaboard.dto.response.CommentResponse;
import com.codesquad.jpaboard.dto.response.PostPageResponse;
import com.codesquad.jpaboard.dto.response.PostResponse;
import com.codesquad.jpaboard.dto.response.PostSummaryResponse;
import com.codesquad.jpaboard.exception.CommonException;
import com.codesquad.jpaboard.exception.ErrorCode;
import com.codesquad.jpaboard.repository.CommentRepository;
import com.codesquad.jpaboard.repository.PostRepository;
import com.codesquad.jpaboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final CommentRepository commentRepository;
  private final RequestService requestService;

  public PostPageResponse getAllPosts() {
    List<Post> posts = postRepository.findAll();
    List<PostSummaryResponse> summaryPost = new ArrayList<>();
    for (Post post : posts) {
      summaryPost.add(new PostSummaryResponse(post.getId(), post.getTitle()));
    }

    //페이지 구현시 수정
    Long total = (long) posts.size();
    Long page = 1L;
    Long perPage = total;

    return new PostPageResponse(total, page, perPage, summaryPost);
  }

  @Transactional
  public Long createPost(PostCreateRequest request, Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

    Post post = new Post(
        request.getTitle(),
        request.getContent(),
        request.getImageUrl(),
        user.getNickname(),
        user
    );

    return postRepository.save(post).getId();
  }

  //todo: 처음엔 20개만 가져오고 그 뒤부터 요청이 오면 구현하는 식으로 변경(이슈 1개당 댓글 10만개 이상이면 for문으로 다 저장해서 가져온다.)
  public PostResponse getIssueById(Long postId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ROOM));

    List<Comment> comments = commentRepository.findByPostId(postId);
    List<CommentResponse> commentResponses = new ArrayList<>();
    for (Comment comment : comments) {
      commentResponses.add(new CommentResponse(
          comment.getId(),
          comment.getContent(),
          comment.getWriter()
      ));
    }
    return new PostResponse(
        post.getId(),
        post.getTitle(),
        post.getContent(),
        post.getImageUrl(),
        post.getWriter(),
        commentResponses
    );
  }

  public Long addComment(CommentRequest request, Long userId, Long postId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ROOM));

    Comment comment = new Comment(
        request.getContent(),
        user.getNickname(),
        user,
        post
    );

    return commentRepository.save(comment).getId();
  }

  @Transactional
  public void updateById(PostCreateRequest postRequest, Long postId, Long userId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ROOM));

    if (!post.getUser().getId().equals(userId)) {
      throw new CommonException(ErrorCode.CANNOT_UPDATE_ROOM);
    }

    post.update(postRequest.getTitle(), postRequest.getContent());
  }

  @Transactional
  public void deleteById(Long userId, Long postId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ROOM));

    if (!post.getUser().getId().equals(userId)) {
      throw new CommonException(ErrorCode.CANNOT_UPDATE_ROOM);
    }

    postRepository.delete(post);
  }
}
