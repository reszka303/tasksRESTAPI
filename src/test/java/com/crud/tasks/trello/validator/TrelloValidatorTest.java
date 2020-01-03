package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
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
public class TrelloValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoards() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("testId01", "testName01", true));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("testId02", "testName02", trelloLists));
        //When
        List<TrelloBoard> validateTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertEquals(1, validateTrelloBoards.size());
    }

    @Test
    public void testValidateTrelloBoards() {
        //Given
        TrelloBoard firstBoardStub = new TrelloBoard("0", "test", new ArrayList<>());
        TrelloBoard secondBoardStub = new TrelloBoard("1", "board name (shall pass the validator)", new ArrayList<>());
        List<TrelloBoard> trelloBoardsStub = new ArrayList<>();
        trelloBoardsStub.add(firstBoardStub);
        trelloBoardsStub.add(secondBoardStub);

        //When
        List<TrelloBoard> validatedTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoardsStub);

        List<TrelloBoard> validatedEmptyTrelloBoards = trelloValidator.validateTrelloBoards(new ArrayList<>());

        //Then
        assertEquals(1, validatedTrelloBoards.size());
        assertEquals("board name (shall pass the validator)", validatedTrelloBoards.get(0).getName());
        assertEquals(0, validatedEmptyTrelloBoards.size());
    }


}
