package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DbService service;
    @MockBean
    private TaskMapper taskMapper;

    TaskDto taskDto = new TaskDto(1L,"Test task 1","Testing1");

    Gson gson = new Gson();
    String jsonContent = gson.toJson(taskDto);


    @Test
    public void shouldCreateTask()throws Exception{

        Task task = new Task();
        when(service.saveTask(taskMapper.mapToTask(any(TaskDto.class)))).thenReturn(task);

        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isOk());
    }



    @Test
    public void shouldGetTask()throws Exception{

        Task testTask = new Task(1L,"Test task 1", "Testing1");
        Optional<Task> optionalTask = Optional.of(testTask);
        when(service.getTask(1L)).thenReturn(optionalTask);
        when(taskMapper.mapToTaskDto(testTask)).thenReturn(taskDto);

        mockMvc.perform(get("/v1/tasks/getTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.taskId", is(1)))
                .andExpect(jsonPath("$.title", is("Test task 1")))
                .andExpect(jsonPath("$.content", is("Testing1")));

    }


    @Test
    public void shouldUpdateTask()throws Exception{

        when(taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(taskDto);

        mockMvc.perform(put("/v1/tasks/updateTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test task 1")))
                .andExpect(jsonPath("$.content", is("Testing1")));

    }
}