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
}
