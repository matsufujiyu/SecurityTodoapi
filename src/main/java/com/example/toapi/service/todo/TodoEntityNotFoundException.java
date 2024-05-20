package com.example.toapi.service.todo;

public class TodoEntityNotFoundException extends RuntimeException{

    private long id;

    public TodoEntityNotFoundException(long id){
        super("TodoEntity(id = " +id +")is not found.");
        this.id = id;
    }
}
