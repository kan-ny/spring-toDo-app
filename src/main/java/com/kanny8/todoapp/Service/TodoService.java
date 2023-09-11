package com.kanny8.todoapp.Service;

import com.kanny8.todoapp.Dto.TodoDto;
import com.kanny8.todoapp.Entity.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TodoService {
    TodoDto createTodo(TodoDto todoDto);
    TodoDto getTodoById(Long id);
    TodoDto updateTodo(TodoDto todoDto);
    List<TodoDto> getTodo();
    String deleteTodo(Long id);

    TodoDto todoComplete(Long id);
    TodoDto todoIncomplete(Long id);

}
