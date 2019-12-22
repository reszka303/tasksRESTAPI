package com.crud.tasks.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskDtoTest {

    @Test
    public void testCreateTaskDto() {
        //Given
        TaskDto taskDto = new TaskDto();
        taskDto.setId(2L);
        taskDto.setContent("testTaskDtoContent");
        taskDto.setTitle("testTaskDtoTitle");

        long resultId = taskDto.getId();
        String resulTitle = taskDto.getTitle();
        String resultContent = taskDto.getContent();
        //Then
        assertEquals(2L, resultId);
        assertEquals("testTaskDtoTitle", resulTitle);
        assertEquals("testTaskDtoContent", resultContent);
    }
}
