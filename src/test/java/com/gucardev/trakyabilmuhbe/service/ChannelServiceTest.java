package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.exception.GeneralException;
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

import java.util.List;
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
    void test_getChannels_then_shouldReturnChannels() {
        Channel channel1 = Channel.builder().build();
        Channel channel2 = Channel.builder().build();
        var expected = List.of(channel1, channel2);
        when(channelRepository.findAll()).thenReturn(expected);

        var actual = channelService.getAllChannels();

        verify(channelRepository, times(1))
                .findAll();
        assertEquals(expected, actual);
    }

    @Test
    void test_getMyChannels_then_shouldReturnMyOwnChannels() {
        User myUser = User.builder().id(1L).build();
        Channel channel1 = Channel.builder().user(myUser).build();
        Channel channel2 = Channel.builder().user(myUser).build();
        var expected = List.of(channel1, channel2);

        when(authService.getAuthenticatedUser()).thenReturn(myUser);

        when(channelRepository.findAllChannelsByUserOrCanSendOthers(myUser.getId()))
                .thenReturn(expected);

        var actual = channelService.getMyChannels();

        verify(authService, times(1)).getAuthenticatedUser();
        verify(channelRepository, times(1))
                .findAllChannelsByUserOrCanSendOthers(myUser.getId());
        assertEquals(expected, actual);
    }

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
                .channelName("update_channelName")
                .canSendOthers(true)
                .channelImageUrl("update_imageUrl")
                .user(User.builder().id(1L).username("user").build())
                .build();

        when(channelRepository.save(existing)).thenReturn(expected);
        when(channelRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(authService.checkForPermission(channelRequest.getUser().getId())).thenReturn(true);

        var actual = channelService.update(channelRequest);


        verify(channelRepository, times(1)).save(expected);
        verify(channelRepository, times(1)).findById(channelRequest.getId());
        verify(authService, times(1)).checkForPermission(existing.getUser().getId());

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
        verify(authService, times(1)).checkForPermission(existing.getUser().getId());

        assertEquals(PermissionError.class, error.getClass());

    }


    @Test
    void test_findChannel_whenExistsByGivenID_then_shouldReturnChannel() {
        Channel expected = Channel.builder()
                .id(1L)
                .canSendOthers(false)
                .channelName("channelName")
                .user(User.builder().id(1L).username("user").build())
                .channelImageUrl("imageUrl")
                .build();

        when(channelRepository.findById(expected.getId())).thenReturn(Optional.of(expected));

        var actual = channelService.getByID(expected.getId());

        verify(channelRepository, times(1)).findById(expected.getId());
        assertEquals(expected, actual);

    }


    @Test
    void test_findChannel_when_doesNotExistByGivenID_then_shouldThrwoException() {
        Channel channel = Channel.builder()
                .id(1L)
                .canSendOthers(false)
                .channelName("channelName")
                .user(User.builder().id(1L).username("user").build())
                .channelImageUrl("imageUrl")
                .build();

        when(channelRepository.findById(channel.getId())).thenReturn(Optional.empty());

        var actual = assertThrows(GeneralException.class, () -> channelService.getByID(channel.getId()));

        verify(channelRepository, times(1)).findById(channel.getId());
        assertEquals(GeneralException.class, actual.getClass());
    }

    @Test
    void test_deleteChannel_whenExistsByGivenIDAndPermissionGranted_then_shouldDelete() {
        Channel channel = Channel.builder()
                .id(1L)
                .canSendOthers(false)
                .channelName("channelName")
                .user(User.builder().id(1L).username("user").build())
                .channelImageUrl("imageUrl")
                .build();

        when(channelRepository.findById(channel.getId())).thenReturn(Optional.of(channel));
        when(authService.checkForPermission(channel.getUser().getId())).thenReturn(true);


        channelService.delete(channel.getId());

        verify(channelRepository, times(1)).findById(channel.getId());
        verify(channelRepository, times(1)).delete(channel);
        verify(authService, times(1)).checkForPermission(channel.getUser().getId());

    }

    @Test
    void test_deleteChannel_whenExistsByGivenIDAndPermissionNotGranted_then_shouldThrowException() {
        Channel channel = Channel.builder()
                .id(1L)
                .canSendOthers(false)
                .channelName("channelName")
                .user(User.builder().id(1L).username("user").build())
                .channelImageUrl("imageUrl")
                .build();

        when(channelRepository.findById(channel.getId())).thenReturn(Optional.of(channel));
        when(authService.checkForPermission(channel.getUser().getId())).thenReturn(false);


        var actual = assertThrows(PermissionError.class, () -> channelService.delete(channel.getId()));

        verify(channelRepository, times(1)).findById(channel.getId());
        verify(channelRepository, times(0)).delete(channel);
        verify(authService, times(1)).checkForPermission(channel.getUser().getId());

        assertEquals(PermissionError.class, actual.getClass());

    }

}