package com.example.toapi.controller.todo;

import com.example.toapi.service.todo.TodoEntity;
import com.example.toapi.service.todo.TodoService;
import com.example.todoapi.controller.TodosApi;
import com.example.todoapi.model.PageDTO;
import com.example.todoapi.model.Todo;
import com.example.todoapi.model.TodoListDTO;
import com.example.todoapi.model.TodoWithoutId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TodoController implements TodosApi {

    private final TodoService todoService;

    //一つのTODO取得（GET）
    @Override
    public ResponseEntity<Todo> showTodo(Long id) {
        var entity = todoService.find(id);
        var dto = toTodoDTO(entity);
        return ResponseEntity.ok(dto);
    }

    //201を返す（POST）
    @Override
    public ResponseEntity<Todo> createTodo(TodoWithoutId todo) {
        var entity = todoService.create(todo.getTitle(), todo.getStatus(),todo.getDetails());
        var dto = toTodoDTO(entity);
        return ResponseEntity
                .created(URI.create("/todos"+ dto.getId()))
                .body(dto);
    }

    //GET 一覧
    @Override
    public ResponseEntity<TodoListDTO> listTodos(Integer limit, Long offset)  {
        var entityList = todoService.find(limit,offset);
        var dtoList = entityList.stream()
                .map(TodoController::toTodoDTO)
                .collect(Collectors.toList());

        var pageDTO =new PageDTO();
        pageDTO.setLimit(limit);
        pageDTO.setOffset(offset);
        pageDTO.setSize(dtoList.size());

        var dto = new TodoListDTO();
        dto.setPage(pageDTO);
        dto.setResults(dtoList);

        return ResponseEntity.ok(dto);
    }

    //PUT
    @Override
    public ResponseEntity<Todo> editTodo(Long id, TodoWithoutId todoWith)  {
        var entity = todoService.update(id,todoWith.getTitle(),todoWith.getStatus(),todoWith.getDetails());
        var dto = toTodoDTO(entity);
        return ResponseEntity.ok(dto);
    }

    private static Todo toTodoDTO(TodoEntity todoEntity) {
        var todo = new Todo();
        todo.setId(todoEntity.getId());
        todo.setTitle(todoEntity.getTitle());
        todo.setStatus(todoEntity.getStatus());
        todo.setDetails(todoEntity.getDetails());
        return todo;
    }

    //Delete
    @Override
    public ResponseEntity<Void> deleteTodo(Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}