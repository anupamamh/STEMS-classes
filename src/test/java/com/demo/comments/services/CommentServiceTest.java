package com.demo.comments.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.demo.comments.models.Comments;
import com.demo.comments.repositories.CommentsRepository;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {
	@Mock
	private CommentsRepository commentsRepository;

	private CommentsService commentsService;
	private String commentId ="5c17da6a2f9e4809787311ba";

	Comments addComments = new Comments("", "comment comment", "Anupama", System.currentTimeMillis(), true, false,
			"anupama@gmail.com");

	@Test
	public void getComments_returnsCommentsInfo() {
		when(commentsRepository.findAll()).thenReturn((List<Comments>) addComments);
	
		//comments = (List<Comments>) commentsService.viewAllComments();
		assertThat(addComments.getCommentText()).isEqualTo("comment comment");
		assertThat(addComments.getCommentBy()).isEqualTo("Anupama");
		assertThat(addComments.isLike()).isEqualTo(true);
		assertThat(addComments.isDislike()).isEqualTo(false);
		assertThat(addComments.getEmailId()).isEqualTo("anupama@gmail.com");

	}
	@Test
	@Ignore
	public void saveComments_Test() {
		assertThat(commentsService.addComment(addComments),is(notNullValue()));
	}
	
	@Test
	@Ignore
	public void deleteComments_Test() {
		// Assertions.assertThat(commentsService.deleteComment(commentId)).(nullValue());
	}
}
