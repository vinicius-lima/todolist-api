package com.lima.todolist.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lima.todolist.domain.Task;

public class TaskDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String title;
	private String description;
	private String author;
	private String status;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date creationDate;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date lastUpdate;
	
	private Integer projectId;
	
	public TaskDTO() {
		
	}
	
	public TaskDTO(Task obj) {
		id = obj.getId();
		title = obj.getTitle();
		description = obj.getDescription();
		author = obj.getAuthor();
		status = obj.getStatus().getDescription();
		creationDate = obj.getCreationDate();
		lastUpdate = obj.getLastUpdate();
		projectId = obj.getProject().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
}
