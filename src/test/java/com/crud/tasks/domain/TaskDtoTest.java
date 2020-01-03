package com.crud.tasks.domain;

import org.junit.Assert;
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
    //--------------------------
    @Test
    public void testAtachmentsByType() {
        AttachmentsByType attachmentsByType = new AttachmentsByType();

        attachmentsByType.getTrello();

        Assert.assertEquals(attachmentsByType,attachmentsByType);
    }

    @Test
    public void testBadgesDtoType() {
        BadgesDto badgesDto = new BadgesDto();


        badgesDto.getAttachmentsByType();

        Assert.assertEquals(badgesDto, badgesDto);

    }

    @Test
    public void testBadgesDtoVotes() {
        BadgesDto badgesDtoVotes = new BadgesDto();

        badgesDtoVotes.getVotes();

        Assert.assertEquals(badgesDtoVotes, badgesDtoVotes);
    }

    @Test
    public void testTrelloBoard() {
        Trello trelloBoard = new Trello();

        trelloBoard.getBoard();

        Assert.assertEquals(trelloBoard, trelloBoard);
    }

    @Test
    public void testTrelloCard() {
        Trello trelloBoard = new Trello();

        trelloBoard.getCard();

        Assert.assertEquals(trelloBoard, trelloBoard);
    }

    @Test
    public void testTaskCanEqual() {
        Task task = new Task();

        task.canEqual(true);

        Assert.assertEquals(task,task);
    }
}
