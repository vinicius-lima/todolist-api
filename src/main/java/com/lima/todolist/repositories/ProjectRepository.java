package com.lima.todolist.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lima.todolist.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

	@Transactional(readOnly=true)
	public List<Project> findAllByOrderByName();
}
