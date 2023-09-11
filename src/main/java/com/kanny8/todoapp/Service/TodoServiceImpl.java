package com.kanny8.todoapp.Service;

import com.kanny8.todoapp.Dto.TodoDto;
import com.kanny8.todoapp.Entity.Todo;
import com.kanny8.todoapp.Exception.ResourceNotFoundException;
import com.kanny8.todoapp.Mapper.TodoMap;
import com.kanny8.todoapp.Repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    @Override
    public TodoDto createTodo(TodoDto todoDto) {

        Todo todo = TodoMap.convertToEntity(todoDto);
        Todo saved = todoRepository.save(todo);
        return TodoMap.convertToDto(saved);
    }

    @Override
    public TodoDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Id "+id+" does not exist")
        );
        return TodoMap.convertToDto(todo);
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto) {
        Todo currentTodo = todoRepository.findById(todoDto.getId()).get();
        currentTodo.setComplete(todoDto.isComplete());
        currentTodo.setName(todoDto.getName());
        currentTodo.setDescription(todoDto.getDescription());

        Todo updated = todoRepository.save(currentTodo);
        return TodoMap.convertToDto(updated);
    }

    @Override
    public List<TodoDto> getTodo() {
        List<Todo> list = todoRepository.findAll();
        return list.stream().map( TodoMap::convertToDto ).collect(Collectors.toList());
    }

    @Override
    public String deleteTodo(Long id) {

        boolean exists = todoRepository.existsById(id);
        if(exists){
            todoRepository.deleteById(id);
            return "Todo with ID: "+id+" is deleted.";
        }
        return "Todo with ID: "+id+" does not exists.";

    }

    @Override
    public TodoDto todoComplete(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> {
                throw new ResourceNotFoundException("ID "+id+" not exist");
                });
        todo.setComplete(Boolean.TRUE);
        return TodoMap.convertToDto(todoRepository.save(todo));
    }

    @Override
    public TodoDto todoIncomplete(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("ID "+id+" not exist");
                });
        todo.setComplete(Boolean.FALSE);
        return TodoMap.convertToDto(todoRepository.save(todo));
    }


}
