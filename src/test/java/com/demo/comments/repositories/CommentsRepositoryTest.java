package com.demo.comments.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.comments.models.Comments;

@Repository
public interface CommentsRepositoryTest extends MongoRepository<Comments, String> {
	public List<Comments> findAll() ;
	
	
}