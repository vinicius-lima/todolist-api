package com.lima.todolist.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.todolist.domain.Project;
import com.lima.todolist.domain.Task;
import com.lima.todolist.domain.enums.TaskStatus;
import com.lima.todolist.repositories.ProjectRepository;
import com.lima.todolist.repositories.TaskRepository;

@Service
public class DBService {
	
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private ProjectRepository projectRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Project pj1 = new Project(null, "EC2", "Vinícius", sdf.parse("14/08/2019"));
		
		Task tk1 = new Task(null, "TASK 1", "Doing a fisrt stuff", "Vinícius", TaskStatus.toEnum(1), sdf.parse("14/08/2019"), sdf.parse("14/08/2019"), pj1);
		Task tk2 = new Task(null, "TASK 2", "Doing a second stuff", "Antônio", TaskStatus.toEnum(2), sdf.parse("14/08/2019"), sdf.parse("15/08/2019"), pj1);
		
		pj1.getTasks().addAll(Arrays.asList(tk1, tk2));
		
		projectRepository.saveAll(Arrays.asList(pj1));
		taskRepository.saveAll(Arrays.asList(tk1, tk2));
	}
	
}
