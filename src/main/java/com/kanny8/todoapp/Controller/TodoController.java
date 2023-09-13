package com.kanny8.todoapp.Controller;

import com.kanny8.todoapp.Dto.TodoDto;
import com.kanny8.todoapp.Service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todo")
public class TodoController {

    private TodoService todoService;

    TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodo(){
        return ResponseEntity.ok(todoService.getTodo());
    }

    @PreAuthorize("permitAll()")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<TodoDto> createTodo( @RequestBody TodoDto createObj ){
        return new ResponseEntity<>(todoService.createTodo(createObj), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id,
                                              @RequestBody TodoDto createObj){
        return ResponseEntity.ok(todoService.updateTodo(createObj));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id ){
        return ResponseEntity.ok(todoService.deleteTodo(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("complete/{id}")
    public  ResponseEntity<TodoDto> todoComplete(@PathVariable Long id){
        return ResponseEntity.ok(todoService.todoComplete(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("incomplete/{id}")
    public  ResponseEntity<TodoDto> todoIncomplete(@PathVariable Long id){
        return ResponseEntity.ok(todoService.todoIncomplete(id));
    }



}
