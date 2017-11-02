package com.kenny.model;

import java.util.Date;

import javax.validation.constraints.Size;

/**
 * A model represents a fake tweet.
 * @author Kenny
 */
public class Tweet {
	private int id;				// id of this tweet

	private String user;		// poster's name

	@Size(min = 5, message = "Enter atleast 5 Characters.")
	private String content;		// content of this tweet

	private Date targetDate;	// posting date

	public Tweet() {
		super();
	}

	public Tweet(int id, String user, String content, Date targetDate) {
		super();
		this.id = id;
		this.user = user;
		this.content = content;
		this.targetDate = targetDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tweet other = (Tweet) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"Tweet [id=%d, user=%s, desc=%s, targetDate=%s]", id,
				user, content, targetDate);
	}

}