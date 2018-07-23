package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;
    @Mock
    private AdminConfig adminConfig;
    @Mock
    private SimpleEmailService emailService;

    @Test
    public void createTrelloCardTest() {

        TrelloCardDto trelloCardDto = new TrelloCardDto("First card", "Test card",
                "Top", "1");
        CreatedTrelloCardDto cardDtoStub = new CreatedTrelloCardDto("Test Id", "Test URL",
                "Testing", null);

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(cardDtoStub);
        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);

        assertEquals("Test URL", createdTrelloCardDto.getName());


    }

    @Test
    public void fetchTrelloBoardsTest(){
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto();
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto);
        trelloBoardDtos.add(trelloBoardDto1);

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);
        int size = trelloService.fetchTrelloBoards().size();

        assertEquals(2,size);

    }

}