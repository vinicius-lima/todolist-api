package com.lima.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lima.todolist.domain.Project;
import com.lima.todolist.dto.ProjectDTO;
import com.lima.todolist.repositories.ProjectRepository;
import com.lima.todolist.repositories.TaskRepository;
import com.lima.todolist.services.exceptions.DataIntegrityException;
import com.lima.todolist.services.exceptions.ObjectNotFoundException;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository repo;
	
	@Autowired
	private TaskRepository taskRepository;
	
	public Project find(Integer id) {
		Optional<Project> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Project.class.getName()));
	}
	
	public List<Project> findAll() {
		return repo.findAllByOrderById();
	}
	
	@Transactional
	public Project insert(Project obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public Project update(Project obj) {
		Project newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		Project obj = find(id);
		try {
			taskRepository.deleteAll(obj.getTasks());
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Could not delete due to related tasks");
		}
	}
	
	private void updateData(Project newObj, Project obj) {
		newObj.setName(obj.getName());
	}
	
	public Project fromDTO(ProjectDTO objDto) {
		return new Project(objDto.getId(), objDto.getName(), objDto.getAuthor(), objDto.getCreationDate());
	}
	
}
