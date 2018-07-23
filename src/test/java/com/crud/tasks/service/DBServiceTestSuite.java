package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DBServiceTestSuite {
    @InjectMocks
    DbService dbService;
    @Mock
    TaskRepository repository;

    @Test
    public void shouldGetAllTasks() {
        Task task = new Task();
        Task task1 = new Task();
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        tasks.add(task1);
        when(repository.findAll()).thenReturn(tasks);

        int size = dbService.getAllTasks().size();

        assertEquals(2, size);

    }

    @Test
    public void shouldSaveTask() {
        Task task = new Task(1L, "Test task", "Test content");
        when(repository.save(task)).thenReturn(task);
        String title = dbService.saveTask(task).getTitle();

        assertEquals("Test task", title);

    }



    @Test
    public void shouldDeleteById() {
        repository.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);

    }


}