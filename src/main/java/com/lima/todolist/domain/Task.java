package com.lima.todolist.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lima.todolist.domain.enums.TaskStatus;

@Entity
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String description;
	private String author;
	private Integer status;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date creationDate;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date lastUpdate;
	
	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;
	
	public Task () {
		
	}

	public Task(Integer id, String title, String description, String author, TaskStatus status, Date creationDate,
			Date lastUpdate, Project project) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.author = author;
		this.status = (status == null) ? null : status.getCode();
		this.creationDate = creationDate;
		this.lastUpdate = lastUpdate;
		this.project = project;
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

	public TaskStatus getStatus() {
		return TaskStatus.toEnum(status);
	}

	public void setStatus(TaskStatus status) {
		this.status = status.getCode();
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Task other = (Task) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
