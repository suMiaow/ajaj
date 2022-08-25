//package com.meme.mongo.service;
//
//import com.meme.mongo.entity.AdapterLogEntity;
//import com.meme.mongo.entity.Comment;
//import com.meme.mongo.entity.Noob;
//import com.meme.mongo.repository.CommentRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Optional;
//
//@Slf4j
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class CommentServiceTest {
//
//    @Autowired
//    private CommentService commentService;
//    @Autowired
//    private CommentRepository commentRepository;
////    @Autowired
////    private MongoTemplate mongoTemplate;
///*
//    @Test
//    public void find() {
////        List<Comment> commentList = commentService.findAll();
////        log.info("comments: {}", commentList);
////        Page<Comment> commentPage = commentRepository.findByParentId("", PageRequest.of(0, 1));
////        log.info("{}", commentPage);
//    }
//
//    @Test
//    public void save() {
//        AdapterLogEntity adapterLogEntity = new AdapterLogEntity();
//        adapterLogEntity.setBizType("aaaa4");
//        adapterLogEntity.setMainCode("bbbb3");
//        adapterLogEntity.setIsValid("1");
//        adapterLogEntity.setCreateTime(LocalDateTime.now());
//
//        mongoTemplate.save(adapterLogEntity);
//
//    }
//
//    @Test
//    public void find2() {
//
//        AdapterLogEntity adapterLogEntity = new AdapterLogEntity();
//        adapterLogEntity.setBizType("aaaa4");
//        adapterLogEntity.setMainCode("bbbb3");
//        adapterLogEntity.setIsValid("1");
//        AdapterLogEntity result = mongoTemplate.findOne(Query.query(Criteria.byExample(adapterLogEntity)).with(Sort.by(Sort.Order.asc("createTime"))), AdapterLogEntity.class);
//
//        log.info("result: {}", result);
//    }
//
//    @Test
//    public void save2() {
//
//        mongoTemplate.save(Arrays.asList(
//                new Noob().setName("n1").setHobby("h1"),
//                new Noob().setName("n2").setHobby("h2"),
//                new Noob().setName("n2").setHobby("h2"),
//                new Noob().setName("n2").setHobby("h2")
//        ), "noob");
//    }
//
//    */
//    @Test
//    public void testLog() {
//        log.error("error: ", new Exception());
//    }
//}
