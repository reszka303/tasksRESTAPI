package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;


    @Test
    public void getAllTasks() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L,"titleTest01","contentTest01"));
        taskList.add(new Task(2L,"titleTest02","contentTest02"));
        taskList.add(new Task(3L,"titleTest03","contentTest03"));
        //When
        when(dbService.getAllTasks()).thenReturn(taskList);
        //Then
        assertEquals(3, taskList.size());
    }

    @Test
    public void getSingleTask() {
        //Given
        Task taskTest = new Task(1L,"titleTest01","contentTest01");
        Long taskIdTest = taskTest.getId();
        Optional<Task> optionalTaskTest = Optional.of(taskTest);
        String taskTitletest = taskTest.getTitle();
        //When
        when(taskRepository.findById(taskIdTest)).thenReturn(optionalTaskTest);
        //Then
        assertEquals("titleTest01", taskTitletest);
    }

    @Test
    public void saveTask() {
        //Given
        Task taskTest = new Task(1L,"titleTest01","contentTest01");
        //When
        when(taskRepository.save(taskTest)).thenReturn(taskTest);
        dbService.saveTask(taskTest);
        //Then
        verify(taskRepository,times(1)).save(taskTest);
    }

    @Test
    public void getTask() {
        //Given
        Task taskTest = new Task(1L,"titleTest01","contentTest01");
        Long taskIdTest = taskTest.getId();
        String taskTitletest = taskTest.getTitle();
        //When
        when(dbService.getTask(taskIdTest)).thenReturn(java.util.Optional.of(taskTest));
        //Then
        assertEquals("titleTest01", taskTitletest);
    }

    @Test
    public void deleteTaskByID() {
        //Given
        Task taskTest = new Task(1L,"titleTest01","contentTest01");
        Long taskTestId = taskTest.getId();
        //When
        doNothing().when(taskRepository).deleteById(taskTestId);
        dbService.deleteTaskById(taskTestId);
        //Then
        verify(taskRepository, times(1)).deleteById(taskTestId);
    }
}
