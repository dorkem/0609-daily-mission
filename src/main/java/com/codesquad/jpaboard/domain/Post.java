package com.codesquad.jpaboard.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "posts")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_id", nullable = false)
  private Long id;

  @Column(length = 500, nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String content;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "writer", nullable = false)
  private String writer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  public Post(String title, String content, String imageUrl, String writer, User user) {
    this.title = title;
    this.content = content;
    this.imageUrl = imageUrl;
    this.writer = writer;
    this.user = user;
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
