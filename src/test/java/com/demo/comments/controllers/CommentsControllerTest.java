package com.demo.comments.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import com.demo.comments.models.Comments;
import com.demo.comments.services.CommentsService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CommentsController.class, secure = false)

public class CommentsControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	CommentsService commentsService;
	RestTemplate restTemplate = new RestTemplate();

	Comments addcomments = new Comments("commentId","comment comment", "Anupama", System.currentTimeMillis(), true,
			false, "anupama@gmail.com");

	String exampleCommentsJson = "[{\"commentId\":\"commentId\",\"commentText\":\"comment comment\",\"commentBy\":\"Anupama\",\"like\":true,\"dislike\":false,\"emailId\":\"anupama@gmail.com\"}]";
	String exampleCommentsJsonSave = "{\"commentId\":\"commentId\",\"commentText\":\"comment for Devaraj\",\"commentBy\":\"Devaraj\",\"like\":true,\"dislike\":false,\"emailId\":\"devaraj@gmail.com\"}";

	@Test
	public void getComments_shouldReturnCommentsTest() throws Exception {

		Mockito.when(commentsService.viewAllComments()).thenReturn(Arrays.asList(addcomments));

		mockMvc.perform(get("/comments").contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().json(exampleCommentsJson));

		/*
		  mockMvc.perform(get("/comments/")).andExpect(status().isOk())
		  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(
		  print()) .andExpect(jsonPath("$[0].commentText", is("comment comment ")))
		  .andExpect(jsonPath("$[0].commentBy", is("Anupama")))
		  .andExpect(jsonPath("$[0].emailId", is("anupama@gmail.com")));
		 */
	}

	@Test
	public void toSaveComments_shouldSaveTest()throws Exception ,HttpMessageNotReadableException{
		
		Comments comment= new Comments();
		comment.setCommentText("comment for Devaraj");
		comment.setDislike(false);
		comment.setLike(true);
		comment.setCommentBy("Devaraj");
		comment.setEmailId("devaraj@gmail.com");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/comments")
				.accept(MediaType.APPLICATION_JSON).content(exampleCommentsJsonSave)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	//	assertEquals("http://localhost:8080/comments",response.getHeader(HttpHeaders.LOCATION));
	}
	/*@Test
	public void toSaveComments_webLayerTest() throws Exception {

		mockMvc.perform(post("/comments").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}*/

	@Test
	public void togetComments_webLayerTest() throws Exception {
		mockMvc.perform(get("/comments").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

	@Ignore
	@Test
	public void addCommentSuccess_Test() throws Exception {

		final String baseUrl = "http://localhost:8080" + "/comments";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, addcomments, String.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
	}

	 @Ignore
		@Test
		public void deleteCommentTest() throws Exception
		{
			mockMvc.perform(MockMvcRequestBuilders.delete("/comments/{id}","5c17da6a2f9e4809787311ba") )
		        .andExpect(status().isAccepted());
		}
	/*
	 *  @Ignore
	@Test
	public void deleteComment_Test() throws Exception {
		  
		 final String uri = "http://localhost:8080/comments/{id}";
		    Map<String, String> params = new HashMap<String, String>();
		    params.put("id", "5c17da6a2f9e4809787311ba");
		    restTemplate.delete(uri,params);
		
	}
	 */
}
