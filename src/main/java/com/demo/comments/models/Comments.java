package com.demo.comments.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Document(collection = "Comments")
public class Comments {

	@Id
	private String commentId;
	private String commentText;
	private String commentBy;

	@JsonProperty(access = Access.READ_ONLY)
	private long commentDate;
	private boolean like;
	private boolean dislike;
	private String emailId;


	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getCommentBy() {
		return commentBy;
	}

	public void setCommentBy(String commentBy) {
		this.commentBy = commentBy;
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

	public long getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(long commentDate) {
		this.commentDate = commentDate;
	}

	public Comments(String commentId, String commentText, String commentBy, long commentDate, boolean like,
			boolean dislike, String emailId) {
		super();
		this.commentId = commentId;
		this.commentText = commentText;
		this.commentBy = commentBy;
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
