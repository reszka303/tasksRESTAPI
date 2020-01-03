package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
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
public class TrelloMapperTestSuite {

    @Autowired
    public TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest() {
        //Given
        List<TrelloListDto> testTrelloListDto = new ArrayList<>();
        List<TrelloBoardDto> testTrelloBoardDto = new ArrayList<>();
        testTrelloBoardDto.add(new TrelloBoardDto("testId01", "testName01", testTrelloListDto));
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(testTrelloBoardDto);
        //Then
        assertEquals(1, trelloBoards.size());
    }

    //======================

    @Test
    public void testMapToBoardDtos() throws Exception {
        // Given
        List<TrelloList> trelloListsStub = new ArrayList<>();
        trelloListsStub.add(new TrelloList("1", "test lists", false));
        List<TrelloBoard> trelloBoardsStub = new ArrayList<>();
        trelloBoardsStub.add(new TrelloBoard("1", "test boards", trelloListsStub));

        List<TrelloBoard> emptyTrelloBoardStub = new ArrayList<>();

        // When
        List<TrelloBoardDto> fetchedTrelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoardsStub);

        List<TrelloBoardDto> fetchedEmptyTrelloBoardDtos = trelloMapper.mapToBoardsDto(emptyTrelloBoardStub);

        //Then
        assertEquals(1,fetchedTrelloBoardDtos.size());
        assertEquals("test boards", fetchedTrelloBoardDtos.get(0).getName());
        assertEquals("test lists", fetchedTrelloBoardDtos.get(0).getLists().get(0).getName());

        assertEquals(0, fetchedEmptyTrelloBoardDtos.size());
    }

    @Test
    public void testMapToCardDto() throws Exception {
        // Given
        TrelloCard trelloCard = new TrelloCard("test card", "stub card", "pos", "testId");

        // When
        TrelloCardDto mappedTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        // Then
        assertEquals("testId", mappedTrelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() throws Exception {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test card", "stub card", "pos", "testId");

        // When
        TrelloCard mappedTrelloCard = trelloMapper.mapToCard(trelloCardDto);

        // Then
        assertEquals("testId", mappedTrelloCard.getListId());
    }

    @Test
    public void testMapToBoard() throws Exception {
        // Given
        List<TrelloListDto> trelloListDtosStub = new ArrayList<>();
        trelloListDtosStub.add(new TrelloListDto("1", "test list dtos", false));
        List<TrelloBoardDto> trelloBoardDtosStub = new ArrayList<>();
        trelloBoardDtosStub.add(new TrelloBoardDto("1", "test board dtos", trelloListDtosStub));

        List<TrelloBoardDto> emptyTrelloBoardDtoStub = new ArrayList<>();

        // When
        List<TrelloBoard> fetchedTrelloBoards = trelloMapper.mapToBoards(trelloBoardDtosStub);

        List<TrelloBoard> fetchedEmptyTrelloBoards = trelloMapper.mapToBoards(emptyTrelloBoardDtoStub);

        //Then
        assertEquals(1,fetchedTrelloBoards.size());
        assertEquals("test board dtos", fetchedTrelloBoards.get(0).getName());
        assertEquals("test list dtos", fetchedTrelloBoards.get(0).getLists().get(0).getName());

        assertEquals(0, fetchedEmptyTrelloBoards.size());
    }



}
