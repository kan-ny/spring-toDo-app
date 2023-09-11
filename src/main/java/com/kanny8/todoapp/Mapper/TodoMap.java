package com.kanny8.todoapp.Mapper;

import com.kanny8.todoapp.Dto.TodoDto;
import com.kanny8.todoapp.Entity.Todo;

public class TodoMap {
    public static TodoDto convertToDto(Todo todo){
        return new TodoDto(
                todo.getId(),
                todo.getName(),
                todo.getDescription(),
                todo.isComplete()
        );
    }


    public static Todo convertToEntity(TodoDto todoDto){
        Todo todo = new Todo();
        todo.setId(todoDto.getId());
        todo.setName(todoDto.getName());
        todo.setDescription(todoDto.getDescription());
        todo.setComplete(todoDto.isComplete());
        return todo;
    }


}
