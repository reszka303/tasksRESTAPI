package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "testTaskDtoName01", "testTaskContentDto01");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals("testTaskDtoName01", task.getTitle());
    }


    //-------------------------------------------------------------


    @Test
    public void mapToTask() throws Exception {
        // Given
        TaskDto taskDtoStub = new TaskDto(1L, "test task title", "test task content");

        // When
        Task fetchedTask = taskMapper.mapToTask(taskDtoStub);
        Long expectedId = 1L;


        //Then
        assertEquals(expectedId , fetchedTask.getId());
        assertEquals("test task title", fetchedTask.getTitle());
        assertEquals("test task content", fetchedTask.getContent());
    }

    @Test
    public void mapToTaskDto() throws Exception {
        // Given
        Task taskStub = new Task(1L, "test task title", "test task content");

        // When
        TaskDto fetchedTaskDto = taskMapper.mapToTaskDto(taskStub);
        Long expectedId = 1L;

        //Then
        assertEquals(expectedId , fetchedTaskDto.getId());
        assertEquals("test task title", fetchedTaskDto.getTitle());
        assertEquals("test task content", fetchedTaskDto.getContent());
    }

    @Test
    public void mapToTaskDtoList() throws Exception {
        // Given
        Task taskStub = new Task(1L, "test task title", "test task content");
        List<Task> taskListStub = new ArrayList<>();
        taskListStub.add(taskStub);

        // When
        List<TaskDto> fetchedTaskDtoList = taskMapper.mapToTaskDtoList(taskListStub);
        Long expectedId = 1L;
        List<TaskDto> fetchedEmptyTaskDtoList = taskMapper.mapToTaskDtoList(new ArrayList<>());

        //Then
        assertEquals(1 , fetchedTaskDtoList.size());
        assertEquals(expectedId, fetchedTaskDtoList.get(0).getId());
        assertEquals("test task title", fetchedTaskDtoList.get(0).getTitle());
        assertEquals("test task content", fetchedTaskDtoList.get(0).getContent());
        assertEquals(0, fetchedEmptyTaskDtoList.size());
    }

}
