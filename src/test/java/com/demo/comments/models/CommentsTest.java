package com.demo.comments.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Document(collection="Comments")
public class CommentsTest {
	
	//UUID submissionId = UUID.randomUUID();
	@Id
	private String submissionId ;
	private String commentText;
	private String personName ;
	@JsonProperty(access=Access.READ_ONLY)
	private Date commentDate;
	private boolean like;
	private boolean dislike;
	private String emailId;

	
	
	/*public UUID getSubmissionId() {
		return submissionId;
	}
	public void setSubmissionId(UUID submissionId) {
		this.submissionId = submissionId;
	}*/
	
	
	public String getCommentText() {
		return commentText;
	}
	public String getSubmissionId() {
		return submissionId;
	}
	public void setSubmissionId(String submissionId) {
		this.submissionId = submissionId;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	public boolean isDislike() {
		return dislike;
	}
	public void setDislike(boolean dislike) {
		this.dislike = dislike;
	}
	public String getEmail_Id() {
		return emailId;
	}
	public void setEmail_Id(String emailId) {
		this.emailId = emailId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	
}
