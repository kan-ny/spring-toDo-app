package com.kanny8.todoapp.Controller;

import com.kanny8.todoapp.Dto.TodoDto;
import com.kanny8.todoapp.Service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todo")
public class TodoController {

    private TodoService todoService;

    TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodo(){
        return ResponseEntity.ok(todoService.getTodo());
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @PostMapping()
    public ResponseEntity<TodoDto> createTodo( @RequestBody TodoDto createObj ){
        return new ResponseEntity<>(todoService.createTodo(createObj), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id,
                                              @RequestBody TodoDto createObj){
        return ResponseEntity.ok(todoService.updateTodo(createObj));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id ){
        return ResponseEntity.ok(todoService.deleteTodo(id));
    }

    @PatchMapping("complete/{id}")
    public  ResponseEntity<TodoDto> todoComplete(@PathVariable Long id){
        return ResponseEntity.ok(todoService.todoComplete(id));
    }

    @PatchMapping("incomplete/{id}")
    public  ResponseEntity<TodoDto> todoIncomplete(@PathVariable Long id){
        return ResponseEntity.ok(todoService.todoIncomplete(id));
    }



}
