package com.lima.todolist.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lima.todolist.domain.Project;
import com.lima.todolist.domain.Task;
import com.lima.todolist.dto.ProjectDTO;
import com.lima.todolist.dto.TaskDTO;
import com.lima.todolist.services.ProjectService;
import com.lima.todolist.services.TaskService;

@RestController
@RequestMapping(value="/projects")
public class ProjectResource {
	
	@Autowired
	private ProjectService service;
	
	@Autowired
	private TaskService taskService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ProjectDTO>> findAll() {
		List<Project> list = service.findAll();
		List<ProjectDTO> listDto = list.stream().map(obj -> new ProjectDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Project> find(@PathVariable Integer id) {
		Project obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{projectId}/tasks", method=RequestMethod.GET)
	public ResponseEntity<List<TaskDTO>> findTasks(@PathVariable Integer projectId) {
		List<Task> list = taskService.findByProject(projectId);
		List<TaskDTO> listDto = list.stream().map(obj -> new TaskDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody ProjectDTO objDto) {
		Project obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ProjectDTO objDto, @PathVariable Integer id) {
		Project obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
