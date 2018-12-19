package com.demo.comments.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.demo.comments.models.Comments;
import com.demo.comments.services.CommentsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CommentsController.class, secure = false)

public class CommentsControllerTest {
	@MockBean
	private CommentsService commentsService;

	@Autowired
	private MockMvc mockMvc;

	Comments addcomments = new Comments("","comment comment", "Anupama", System.currentTimeMillis(), true,
			false, "anupama@gmail.com");

	String exampleCommentsJson = "[{\"submissionId\":\"submissionId\",\"commentText\":\"comment comment\",\"personName\":\"Anupama\",\"like\":true,\"dislike\":false,\"emailId\":\"anupama@gmail.com\"}]";
	String exampleCommentsJsonSave = "[{\"submissionId\":\"submissionId\",\"commentText\":\"comment for Devaraj\",\"personName\":\"Devaraj\",\"like\":true,\"dislike\":false,\"emailId\":\"devaraj@gmail.com\"}]";

	@Test
	@Ignore
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
		  .andExpect(jsonPath("$[0].personName", is("Anupama")))
		  .andExpect(jsonPath("$[0].emailId", is("anupama@gmail.com")));
		 */
	}
	@Test
	@Ignore
	public void toSaveComments_shouldSaveTest()throws Exception ,HttpMessageNotReadableException{
		
		Comments comment= new Comments();
		comment.setCommentText("comment for Devaraj");
		comment.setDislike(false);
		comment.setLike(true);
		comment.setPersonName("Devaraj");
		comment.setEmailId("devaraj@gmail.com");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/comments/")
				.accept(MediaType.APPLICATION_JSON).content(exampleCommentsJsonSave)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println("===========>>"+result.getResponse());
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost:8080/comments",
				response.getHeader(HttpHeaders.LOCATION));
	}
	@Test
	@Ignore
	public void toSaveComments_webLayerTest()throws Exception{
		
		 mockMvc.perform(post("/comments").accept(MediaType.APPLICATION_JSON))
		 .andDo(print()).andExpect(status().isOk());
		

	}
	@Test
	
	public void togetComments_webLayerTest()throws Exception{
		 mockMvc.perform(get("/comments").accept(MediaType.APPLICATION_JSON))
		 .andDo(print()).andExpect(status().isOk());
	}
}
