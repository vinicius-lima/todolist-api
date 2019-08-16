package com.lima.todolist.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lima.todolist.domain.Project;

public class ProjectDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String author;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date creationDate;
	
	public ProjectDTO() {
		
	}
	
	public ProjectDTO(Project obj) {
		id = obj.getId();
		name = obj.getName();
		author = obj.getAuthor();
		creationDate = obj.getCreationDate();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
}
