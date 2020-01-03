package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
//-------------------------------------------------------------------------------------------------

    @Test
    public void getAllTasksTest(){
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L,"A","AAA"));
        tasks.add(new Task(2L,"B","BBB"));
        tasks.add(new Task(3L,"C","CCC"));

        when(taskRepository.findAll()).thenReturn(tasks);

        //When
        List<Task> result = dbService.getAllTasks();

        //Then
        Assert.assertEquals(3,result.size());
    }

    @Test
    public void saveTaskTest(){
        //Given
        Task task = new Task(1L, "title", "content");

        when(taskRepository.save(task)).thenReturn(task);

        //When
        Task result = dbService.saveTask(task);

        //Then
        Assert.assertEquals(1L, result.getId(),0.001);
        Assert.assertEquals("title", result.getTitle());
        Assert.assertEquals("content", result.getContent());
    }


    //--------------------------------

    @Test
    public void testGetAllTasks() {
        //Given
        Task taskStub1 = new Task(1l, "test title", "test content1");
        Task taskStub2 = new Task(2l, "test title", "test content2");
        List<Task> tasksStub = new ArrayList<>();
        tasksStub.add(taskStub1);
        tasksStub.add(taskStub2);

        when(taskRepository.findAll()).thenReturn(tasksStub);

        //When
        List<Task> fetchedTaskList = dbService.getAllTasks();

        //Then
        assertEquals(2, fetchedTaskList.size());
    }

    @Test
    public void testGetAllTasksWithEmptyList() {
        //Given
        when(taskRepository.findAll()).thenReturn(new ArrayList<>());

        //When
        List<Task> fetchedTaskList = dbService.getAllTasks();

        //Then
        assertEquals(0, fetchedTaskList.size());
    }

    @Test
    public void testGetTaskById() {
        //Given
        Task taskStub = new Task(1L,"test title", "test content");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(taskStub));

        //When
        Task fetchedTaskById = dbService.getSingleTask(1L);

        //Then
        assertEquals(taskStub.getId(), fetchedTaskById.getId());
    }

    @Test
    public void testSaveTask() {
        //Given
        Task taskStub = new Task(1l, " test title", "test content");
        when(taskRepository.save(taskStub)).thenReturn(taskStub);

        //When
        Task testTask = dbService.saveTask(taskStub);

        //Then
        assertEquals(taskStub.getId(), testTask.getId());
        assertEquals(taskStub.getTitle(), testTask.getTitle());
        assertEquals(taskStub.getContent(), testTask.getContent());
    }

    @Test
    public void testGetTask() {
        //Given
        Task taskStub = new Task(1L, "test title", "test content");

        when(dbService.getTask(1L)).thenReturn(Optional.ofNullable(taskStub));

        //When
        Optional<Task> fetchedTaskById = dbService.getTask(1L);

        //Then
        assertTrue(fetchedTaskById.isPresent());
        assertEquals(taskStub.getId(), fetchedTaskById.get().getId());
    }
}
