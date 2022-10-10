package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.exception.GeneralException;
import com.gucardev.trakyabilmuhbe.exception.PermissionError;
import com.gucardev.trakyabilmuhbe.model.Channel;
import com.gucardev.trakyabilmuhbe.repository.ChannelRepository;
import com.gucardev.trakyabilmuhbe.request.ChannelRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final AuthService authService;


    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    public List<Channel> getMyChannels() {
        return channelRepository.findAllChannelsByUserOrCanSendOthers(authService.getAuthenticatedUser().getId());
    }

    public Channel create(ChannelRequest channelRequest) {

        String channelTopic = "";

        Channel channel = Channel.builder()
                .channelTopic(channelTopic)
                .canSendOthers(channelRequest.isCanSendOthers())
                .channelName(channelRequest.getChannelName())
                .user(channelRequest.getUser())
                .channelImageUrl(channelRequest.getChannelImageUrl())
                .build();
        return channelRepository.save(channel);
    }

    public Channel update(ChannelRequest channelRequest) {
        Channel channel = getByID(channelRequest.getId());
        channel.setChannelName(channel.getChannelName());
        channel.setChannelImageUrl(channel.getChannelImageUrl());
        return channelRepository.save(channel);
    }


    public void delete(Long id) {
        Channel existing = getByID(id);
        if (!authService.checkForPermission(existing.getUser().getId())) {
            throw new PermissionError("Permission not granted!");
        }
        channelRepository.delete(existing);
    }


    public Channel getByID(Long id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new GeneralException("channel not found!", HttpStatus.NOT_FOUND));
    }
}