package com.demo.comments.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.comments.models.Comments;
import com.demo.comments.services.CommentsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/comments")
@Api(value="CommentsControllerAPI",produces=MediaType.APPLICATION_JSON_VALUE)
public class CommentsController {

	@Autowired
	private CommentsService commentsService;

	// To view all the comments
	@ApiOperation("Gets the list of comments")
	@ApiResponses(value= {@ApiResponse(code=200,message="OK",response=Comments.class)})
	@RequestMapping(method = RequestMethod.GET)
	public List<Comments> viewAllComments() {

		return commentsService.viewAllComments();
	}

	// To add a new comment
	@RequestMapping(method = RequestMethod.POST)
	public Comments addComment(@Valid @RequestBody Comments comments) {
		commentsService.addComment(comments);
		return comments;
	}

	// To delete a comment given submission Id
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteComment(@PathVariable("id") String commentId) {
		System.out.println(commentId);
		commentsService.deleteComment(commentId);
	}

}
