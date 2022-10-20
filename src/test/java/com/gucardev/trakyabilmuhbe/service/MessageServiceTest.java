package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.model.Channel;
import com.gucardev.trakyabilmuhbe.model.Message;
import com.gucardev.trakyabilmuhbe.model.User;
import com.gucardev.trakyabilmuhbe.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;
    @Mock
    private UserService userService;
    @Mock
    private AuthService authService;
    @Mock
    private ChannelService channelService;

    @InjectMocks
    private MessageService messageService;

    @Test
    void when_createMessage_then_shouldReturn_createdMessage() {
       var stubUser = User.builder().id(1L).build();
        var stubChannel = Channel.builder().id(1L).build();
        var expected = Message.builder()
                .id(1L)
                .user(stubUser)
                .channel(stubChannel)
                .build();


        when(userService.doesUserExistByID(stubUser.getId())).thenReturn(true);
        when(messageRepository.save(expected)).thenReturn(expected);
        when(channelService.getByID(anyLong())).thenReturn(Channel.builder().build());


        var actual = messageService.create(expected);

        verify(messageRepository, times(1)).save(expected);
        assertEquals(expected, actual);

    }

}