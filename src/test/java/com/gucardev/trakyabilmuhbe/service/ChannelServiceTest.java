package com.gucardev.trakyabilmuhbe.service;

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

import static org.junit.jupiter.api.Assertions.*;
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

}