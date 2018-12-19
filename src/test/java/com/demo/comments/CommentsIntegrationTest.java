package com.demo.comments;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.comments.models.Comments;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CommentsIntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
		@Test
	public void getCommentsTest() throws Exception {
		ResponseEntity<Comments> response= restTemplate.getForEntity("/comments/", Comments.class);
		
	assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	assertThat(response.getBody().getPersonName()).isEqualTo("Reliable Soft");
	assertThat(response.getBody().getCommentText()).isEqualTo("comments text");
	assertThat(response.getBody().getEmailId()).isEqualTo("reliablesoft@gmail.com");
	assertThat(response.getBody().isLike()).isEqualTo(true);
	assertThat(response.getBody().isDislike()).isEqualTo(false);
	
	}
}
