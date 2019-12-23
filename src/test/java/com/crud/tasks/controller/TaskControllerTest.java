package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void getEmptyTasks() throws Exception {
        //Given
        List<Task> taskListTest = new ArrayList<>();

        when(dbService.getAllTasks()).thenReturn(taskListTest);
        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }

    @Test
    public void getTasks() throws Exception {
        //Given
        Task taskTest = new Task(1L, "testTitle", "testContent");
        List<Task> taskListTest = new ArrayList<>();
        taskListTest.add(taskTest);

        TaskDto taskTestDto = new TaskDto(1L, "testTitle", "testContent");
        List<TaskDto> taskListTestDto = new ArrayList<>();
        taskListTestDto.add(taskTestDto);

        when(dbService.getAllTasks()).thenReturn(taskListTest);
        when(taskMapper.mapToTaskDtoList(taskListTest)).thenReturn(taskListTestDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("testTitle")))
                .andExpect(jsonPath("$[0].content", is("testContent")));
    }

    @Test
    public void getTask() throws Exception {
        //Given
        Task taskTest = new Task(1L, "testTitle", "testContent");
        long idTestTask = taskTest.getId();
        TaskDto taskTestDto = new TaskDto(1L, "testTitle", "testContent");

        when(dbService.getTask(idTestTask)).thenReturn(Optional.of(taskTest));
        when(taskMapper.mapToTaskDto(taskTest)).thenReturn(taskTestDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON).param("taskId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("testTitle")))
                .andExpect(jsonPath("$.content", is("testContent")));
    }

    @Test
    public void deleteTask() throws Exception {
        //Given
        Task taskTest = new Task(1L, "testTitle", "testContent");
        long idTestTask = taskTest.getId();

        when(dbService.getTask(idTestTask)).thenReturn(Optional.of(taskTest));

        //When & Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON).param("taskId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTask() throws Exception {
        //Given
        Task taskTest = new Task(1L, "testTitle", "testContent");
        TaskDto taskTestDto = new TaskDto(1L, "testTitle", "testContent");

        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(taskTest);
        when(dbService.saveTask(taskTest)).thenReturn(taskTest);
        when(taskMapper.mapToTaskDto(taskTest)).thenReturn(taskTestDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskTestDto);

        //When & Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("testTitle")))
                .andExpect(jsonPath("$.content", is("testContent")));
    }

    @Test
    public void createTask() throws Exception {
        //Given
        Task taskTest = new Task(1L, "testTitle", "testContent");
        TaskDto taskTestDto = new TaskDto(1L, "testTitle", "testContent");

        when(taskMapper.mapToTask(taskTestDto)).thenReturn(taskTest);
        when(dbService.saveTask(taskTest)).thenReturn(taskTest);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskTestDto);

        //When & Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}
