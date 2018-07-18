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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;

    @Test
    public void shouldGetTasks() throws Exception {
        //given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "test_title1", "test_content1"));
        tasks.add(new Task(2L, "test_title2", "test_content2"));

        List<TaskDto> tasksDto = new ArrayList<>();
        tasksDto.add(new TaskDto(1L, "test_title1", "test_content1"));
        tasksDto.add(new TaskDto(2L, "test_title2", "test_content2"));

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);

        //when & then
        mockMvc.perform(get("/v1/tasks/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("test_title1")))
                .andExpect(jsonPath("$[0].content", is("test_content1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("test_title2")))
                .andExpect(jsonPath("$[1].content", is("test_content2")));

        verify(dbService, times(1)).getAllTasks();
        verify(taskMapper, times(1)).mapToTaskDtoList(tasks);
    }

    @Test
    public void shouldGetTask() throws Exception {
        //given
        Task task = new Task(1L, "test_title", "test_content");
        Optional<Task> taskOptional = Optional.of(task);
        TaskDto taskDto = new TaskDto(1L, "test_title", "test_content");

        when(dbService.getTask(1L)).thenReturn(taskOptional);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //when & then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test_title")))
                .andExpect(jsonPath("$.content", is("test_content")));

        verify(dbService, times(1)).getTask(1L);
        verify(taskMapper, times(1)).mapToTaskDto(task);
    }


    @Test
    public void deleteTask() throws Exception {
        //when & then
        mockMvc.perform(delete("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(dbService, times(1)).deleteTask(1L);
    }

}