package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.exception.PermissionError;
import com.gucardev.trakyabilmuhbe.model.Channel;
import com.gucardev.trakyabilmuhbe.model.Message;
import com.gucardev.trakyabilmuhbe.model.User;
import com.gucardev.trakyabilmuhbe.repository.MessageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

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

    @Test
    void test_updateMessage_when_MessageExistsByGivenIDAndMessageAndValidPermission_then_shouldReturn_updatedMessage() {
        var stubUser = User.builder().id(1L).build();
        var stubChannel = Channel.builder().id(1L).build();

        var existing = Message.builder()
                .id(1L)
                .content("content")
                .user(stubUser)
                .channel(stubChannel)
                .build();

        var expected = Message.builder()
                .id(1L)
                .content("updated_content")
                .user(stubUser)
                .channel(stubChannel)
                .build();

        when(authService.checkForPermission(stubUser.getId())).thenReturn(true);
        when(messageRepository.save(expected)).thenReturn(expected);
        when(messageRepository.findById(expected.getId())).thenReturn(Optional.of(existing));
        when(channelService.getByID(anyLong())).thenReturn(Channel.builder().build());

        var actual = messageService.update(expected);

        verify(messageRepository, times(1)).save(expected);
        verify(messageRepository, times(1)).findById(expected.getId());
        assertEquals(expected.getContent(), actual.getContent());

    }


    @Test
    void test_updateMessage_when_MessageExistsByGivenIDAndMessageAndInvalidPermission_then_shouldThrow_exception() {
        var stubUser = User.builder().id(1L).build();
        var stubChannel = Channel.builder().id(1L).build();


        var expected = Message.builder()
                .id(1L)
                .content("updated_content")
                .user(stubUser)
                .channel(stubChannel)
                .build();

        when(messageRepository.findById(expected.getId())).thenReturn(Optional.of(expected));
        when(authService.checkForPermission(stubUser.getId())).thenReturn(false);

        Throwable actual = Assertions.assertThrows(PermissionError.class,
                () -> messageService.update(expected));

        verify(messageRepository, times(1)).findById(expected.getId());
        verify(messageRepository, times(0)).save(expected);

        assertEquals(PermissionError.class, actual.getClass());

    }

    @Test
    void test_deleteMessage_when_PermissionGranted_then_shouldDelete() {
        var stubUser = User.builder().id(1L).build();
        var stubMessage = Message.builder()
                .id(1L)
                .user(stubUser)
                .build();
        when(messageRepository.findById(stubMessage.getId())).thenReturn(Optional.of(stubMessage));
        when(authService.checkForPermission(stubUser.getId())).thenReturn(true);

        messageService.delete(stubMessage.getId());
        verify(messageRepository, times(1)).delete(stubMessage);
    }


    @Test
    void test_deleteMessage_when_PermissionNotGranted_then_should_throwException() {
        var stubUser = User.builder().id(1L).build();
        var stubMessage = Message.builder()
                .id(1L)
                .user(stubUser)
                .build();
        when(messageRepository.findById(stubMessage.getId())).thenReturn(Optional.of(stubMessage));
        when(authService.checkForPermission(stubUser.getId())).thenReturn(false);

        Throwable actual = assertThrows(PermissionError.class, () -> messageService.delete(stubMessage.getId()));

        verify(messageRepository, times(0)).delete(stubMessage);
        assertEquals(PermissionError.class, actual.getClass());
    }

    @Test
    void test_getMessages_when_channelIdIsNull_then_shouldReturnMessageList() {
        var stubChannel = Channel.builder().id(1L).build();
        var stubMessage1 = Message.builder().id(1L).build();
        var stubMessage2 = Message.builder().id(1L).build();
        var expected = List.of(stubMessage1, stubMessage2);

        when(messageRepository.findAllByOrderByCreatedDateTimeDesc()).thenReturn(expected);

        var actual = messageService.getMessages(null);

        assertEquals(expected,actual);
        verify(messageRepository,times(1)).findAllByOrderByCreatedDateTimeDesc();
        verify(messageRepository,times(0)).findAllByChannelOrderByCreatedDateTimeDesc(stubChannel);
    }

    @Test
    void test_getMessages_when_channelIdIsNotNull_then_shouldReturnMessageListByGivenChannel() {
        var stubChannel = Channel.builder().id(1L).build();
        var stubMessage1 = Message.builder().id(1L).build();
        var stubMessage2 = Message.builder().id(1L).build();
        var expected = List.of(stubMessage1, stubMessage2);

        when(messageRepository.findAllByChannelOrderByCreatedDateTimeDesc(stubChannel)).thenReturn(expected);
        when(channelService.getByID(anyLong())).thenReturn(Channel.builder().build());

        var actual = messageService.getMessages(stubChannel.getId());

        assertEquals(expected,actual);
        verify(messageRepository,times(0)).findAllByOrderByCreatedDateTimeDesc();
        verify(messageRepository,times(1)).findAllByChannelOrderByCreatedDateTimeDesc(stubChannel);
    }

}