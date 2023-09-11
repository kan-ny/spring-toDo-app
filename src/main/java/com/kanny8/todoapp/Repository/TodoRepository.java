package com.kanny8.todoapp.Repository;

import com.kanny8.todoapp.Entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
