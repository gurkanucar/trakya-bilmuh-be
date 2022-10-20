package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.model.Announcement;
import com.gucardev.trakyabilmuhbe.repository.AnnouncementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnnouncementServiceTest {

    @Mock
    private AnnouncementRepository announcementRepository;

    @InjectMocks
    private AnnouncementService announcementService;


    @Test
    void when_createBook_shouldReturn_savedBook() {

        Announcement expected = Announcement.builder()
                .title("title")
                .content("content")
                .link("link")
                .build();

        when(announcementRepository.save(expected)).thenReturn(expected);

        var actual = announcementService.create(expected);


        verify(announcementRepository,times(1)).save(expected);
        assertEquals(expected, actual);

    }


}