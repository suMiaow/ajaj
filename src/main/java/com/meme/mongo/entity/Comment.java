//package com.meme.mongo.entity;
//
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.index.Indexed;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Data
//@Document
//public class Comment {
//
//    @Id
//    private String id;
//
//    private String content;
//
//    private LocalDate publishDate;
//
//    @Indexed
//    private String userid;
//
//    private String nickname;
//
//    private LocalDateTime createDateTime;
//
//    private Integer likeNum;
//
//    private Integer replyNum;
//
//    private String state;
//
//    private String parentId;
//
//    private String articleId;
//}
//
