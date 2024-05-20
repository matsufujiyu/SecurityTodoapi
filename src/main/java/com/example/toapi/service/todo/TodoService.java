package com.example.toapi.service.todo;

import com.example.toapi.repository.todo.TodoRecord;
import com.example.toapi.repository.todo.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    //get
    public TodoEntity find(long id) {
        return todoRepository.select(id)
                .map(record -> new TodoEntity(record.getId(),record.getTitle(), record.getStatus(), record.getDetails()))
                .orElseThrow(() ->new TodoEntityNotFoundException(id));

    }

    //gets
    public List<TodoEntity> find(int limit, long offset) {
       return todoRepository.selectList(limit, offset)
               .stream()
               .map(record -> new TodoEntity(record.getId(), record.getTitle(), record.getStatus(), record.getDetails()))
               .collect(Collectors.toList());

    }

    //Post
    public TodoEntity create(String title,String status,String details) {
        var record = new TodoRecord(null,title,status,details);
        todoRepository.insert(record);
        return new TodoEntity(record.getId(),  record.getTitle(), record.getStatus(), record.getDetails());
    }

    //put
    public TodoEntity update(Long id, String title, String status, String details) {
        todoRepository.select(id)
                        .orElseThrow(() ->new TodoEntityNotFoundException(id));
        todoRepository.update(new TodoRecord(id,title, status, details));
        return find(id);
    }

    //delete
    public void delete(Long id) {
        todoRepository.select(id)
                        .orElseThrow(() -> new TodoEntityNotFoundException(id));
        todoRepository.delete(id);
    }
}
