package com.demo.comments.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Document(collection = "Comments")
public class Comments {

	@Id
	private String submissionId;
	private String commentText;
	private String personName;

	@JsonProperty(access = Access.READ_ONLY)
	private long commentDate;
	private boolean like;
	private boolean dislike;
	private String emailId;

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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public long getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(long commentDate) {
		this.commentDate = commentDate;
	}

	public Comments(String submissionId, String commentText, String personName, long commentDate, boolean like,
			boolean dislike, String emailId) {
		super();
		this.submissionId = submissionId;
		this.commentText = commentText;
		this.personName = personName;
		this.commentDate = commentDate;
		this.like = like;
		this.dislike = dislike;
		this.emailId = emailId;
	}

	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}

}
