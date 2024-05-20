package com.example.toapi.repository.todo;


import lombok.Value;

@Value
public class TodoRecord {

    Long id;
    String title;
    String status;
    String details;


}
