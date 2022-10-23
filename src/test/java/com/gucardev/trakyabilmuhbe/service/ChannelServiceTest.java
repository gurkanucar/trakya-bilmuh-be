package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.exception.PermissionError;
import com.gucardev.trakyabilmuhbe.model.Channel;
import com.gucardev.trakyabilmuhbe.model.User;
import com.gucardev.trakyabilmuhbe.repository.ChannelRepository;
import com.gucardev.trakyabilmuhbe.request.ChannelRequest;
import com.gucardev.trakyabilmuhbe.util.CodeGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChannelServiceTest {

    @Mock
    private ChannelRepository channelRepository;
    @Mock
    private AuthService authService;
    @Mock
    private CodeGenerator codeGenerator;

    @InjectMocks
    private ChannelService channelService;


    @Test
    void test_createChannel_shouldReturn_savedChannel() {
        ChannelRequest channelRequest = ChannelRequest.builder()
                .user(User.builder().id(1L).username("user").build())
                .build();
        Channel expected = Channel.builder()
                .channelTopic("RANDOM_STRING")
                .canSendOthers(channelRequest.isCanSendOthers())
                .channelName(channelRequest.getChannelName())
                .user(channelRequest.getUser())
                .channelImageUrl(channelRequest.getChannelImageUrl())
                .build();

        when(channelRepository.save(expected)).thenReturn(expected);
        when(codeGenerator.generateRandomString()).thenReturn("RANDOM_STRING");

        var actual = channelService.create(channelRequest);

        verify(codeGenerator, times(1)).generateRandomString();
        verify(channelRepository, times(1)).save(expected);
        assertEquals(expected, actual);


    }

    @Test
    void test_updateChannel_whenChannelExistsAndPermissionGranted_thenReturn_updatedChannel() {
        ChannelRequest channelRequest = ChannelRequest.builder()
                .id(1L)
                .channelName("update_channelName")
                .canSendOthers(true)
                .channelImageUrl("update_imageUrl")
                .user(User.builder().id(1L).username("user").build())
                .build();
        Channel existing = Channel.builder()
                .id(1L)
                .canSendOthers(false)
                .channelName("channelName")
                .user(User.builder().id(1L).username("user").build())
                .channelImageUrl("imageUrl")
                .build();

        Channel expected = Channel.builder()
                .id(1L)
                .canSendOthers(false)
                .channelName("channelName")
                .user(User.builder().id(1L).username("user").build())
                .channelImageUrl(channelRequest.getChannelImageUrl())
                .build();

        when(channelRepository.save(existing)).thenReturn(expected);
        when(channelRepository.findById(1L)).thenReturn(Optional.ofNullable(existing));
        when(authService.checkForPermission(channelRequest.getUser().getId())).thenReturn(true);

        var actual = channelService.update(channelRequest);


        verify(channelRepository, times(1)).save(expected);
        verify(channelRepository, times(1)).findById(channelRequest.getId());

        assertEquals(expected, actual);

    }

    @Test
    void test_updateChannel_whenChannelExistsAndPermissionNotGranted_then_throwException() {
        ChannelRequest channelRequest = ChannelRequest.builder()
                .id(1L)
                .channelName("update_channelName")
                .canSendOthers(true)
                .channelImageUrl("update_imageUrl")
                .user(User.builder().id(1L).username("user").build())
                .build();
        Channel existing = Channel.builder()
                .id(1L)
                .canSendOthers(false)
                .channelName("channelName")
                .user(User.builder().id(1L).username("user").build())
                .channelImageUrl("imageUrl")
                .build();

        when(channelRepository.findById(1L)).thenReturn(Optional.ofNullable(existing));
        when(authService.checkForPermission(channelRequest.getUser().getId())).thenReturn(false);

        var error = assertThrows(PermissionError.class, () -> channelService.update(channelRequest));


        assert existing != null;
        verify(channelRepository, times(0)).save(existing);
        verify(channelRepository, times(1)).findById(channelRequest.getId());

        assertEquals(PermissionError.class, error.getClass());

    }

}