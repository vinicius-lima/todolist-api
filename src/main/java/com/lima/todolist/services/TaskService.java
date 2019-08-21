package com.lima.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lima.todolist.domain.Project;
import com.lima.todolist.domain.Task;
import com.lima.todolist.domain.enums.TaskStatus;
import com.lima.todolist.dto.TaskDTO;
import com.lima.todolist.repositories.TaskRepository;
import com.lima.todolist.services.exceptions.DataIntegrityException;
import com.lima.todolist.services.exceptions.ObjectNotFoundException;

@Service
public class TaskService {

	@Autowired
	private TaskRepository repo;
	
	@Autowired
	private ProjectService projectService;
	
	public Task find(Integer id) {
		Optional<Task> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Task.class.getName()));
	}
	
	public List<Task> findByProject(Integer projectId) {
		return repo.findTasks(projectId);
	}
	
	@Transactional
	public Task insert(Task obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public Task update(Task obj) {
		Task newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Could not delete task");
		}
	}
	
	private void updateData(Task newObj, Task obj) {
		newObj.setTitle(obj.getTitle());
		newObj.setDescription(obj.getDescription());
		newObj.setUpdatedBy(obj.getUpdatedBy());
		newObj.setStatus(obj.getStatus());
		newObj.setLastUpdate(obj.getLastUpdate());
	}
	
	public Task fromDTO(TaskDTO objDto) {
		Project project = projectService.find(objDto.getProjectId());
		return new Task(objDto.getId(), objDto.getTitle(), objDto.getDescription(),
				objDto.getAuthor(), objDto.getUpdatedBy(), TaskStatus.toEnum(objDto.getStatus()),
				objDto.getCreationDate(), objDto.getLastUpdate(), project);
	}
	
}
