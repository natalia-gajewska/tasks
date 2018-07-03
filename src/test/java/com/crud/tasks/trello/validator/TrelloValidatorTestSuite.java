package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTestSuite {

    @Mock
    TrelloValidator trelloValidator;

    @Test
    public void testValidator() {
        TrelloList trelloList = new TrelloList("1","Test list", false);
        TrelloList trelloList2 = new TrelloList("2","Test list2", false);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList);
        lists.add(trelloList2);
        TrelloBoard trelloBoard = new TrelloBoard("1","New board",lists);
        List<TrelloBoard>trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        when(trelloValidator.validateTrelloBoards(trelloBoards)).thenReturn(trelloBoards);

        int numberOfBoards = trelloValidator.validateTrelloBoards(trelloBoards).size();

        assertEquals(1,numberOfBoards);


    }

}