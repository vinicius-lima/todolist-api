package com.lima.todolist.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lima.todolist.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Task obj WHERE obj.project.id = :projectId ORDER BY obj.creationDate")
	public List<Task> findTasks(@Param("projectId") Integer projectID);
}
