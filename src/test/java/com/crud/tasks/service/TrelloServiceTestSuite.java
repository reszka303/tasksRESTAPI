package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Test
    public void fetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));
        //When
        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();
        //Then
        assertEquals("test", trelloBoardDtos.get(0).getName());
    }

    @Test
    public void createTrelloCard() {
        //Given
        TrelloCardDto trelloCard = new TrelloCardDto("testTrelloCardDtoName", "testTrelloCardDtoDescription",
                "testTrelloCardDtoPos","testTrelloCardDtoID");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("testId", "testName", "testUrl");
        //When
        when(trelloService.createTrelloCard(trelloCard)).thenReturn(createdTrelloCardDto);
        //Then
        assertEquals("testName", createdTrelloCardDto.getName());
    }
}