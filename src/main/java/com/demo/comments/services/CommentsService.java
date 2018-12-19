package com.demo.comments.services;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.comments.models.Comments;
import com.demo.comments.repositories.CommentsRepository;

@Service
public class CommentsService {

	@Autowired
	private CommentsRepository commentsRepository;
	Comments tempComment;

	public List<Comments> viewAllComments() {
		//List<Comments> tempComments =  commentsRepository.findAll().;
		return commentsRepository.findAll();
	}

	
	public Comments addComment(@Valid @RequestBody Comments comments) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		//comments.setCommentDate(Long.parseLong(dateFormat.format(System.currentTimeMillis()).toString()));
		System.out.println("++++++++++++"+dateFormat.format(System.currentTimeMillis()));
		comments.setCommentDate(System.currentTimeMillis());
		commentsRepository.save(comments);
		return comments;
	}

		
	public void deleteComment(String submissionId) {
		//commentsRepository.delete(commentId);
		commentsRepository.deleteById(submissionId);
	}
}
