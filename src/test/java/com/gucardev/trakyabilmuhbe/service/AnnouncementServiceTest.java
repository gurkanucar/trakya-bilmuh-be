package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.model.Announcement;
import com.gucardev.trakyabilmuhbe.repository.AnnouncementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnnouncementServiceTest {

    @Mock
    private AnnouncementRepository announcementRepository;

    @InjectMocks
    private AnnouncementService announcementService;

    @Test
    void when_getAll_then_shouldReturn_AnnouncementList() {

        Announcement stub1 = Announcement.builder().build();
        Announcement stub2 = Announcement.builder().build();
        var expected = List.of(stub1, stub2);

        when(announcementRepository.findAll()).thenReturn(expected);

        var actual = announcementService.getAll();

        verify(announcementRepository, times(1)).findAll();
        assertEquals(expected, actual);

    }

    @Test
    void when_createAnnouncement_then_shouldReturn_savedAnnouncement() {

        Announcement expected = Announcement.builder()
                .title("title")
                .content("content")
                .link("link")
                .build();

        when(announcementRepository.save(expected)).thenReturn(expected);

        var actual = announcementService.create(expected);

        verify(announcementRepository, times(1)).save(expected);
        assertEquals(expected, actual);

    }

    @Test
    void when_updateAnnouncementWithExistingId_then_shouldReturnUpdatedAnnouncement() {
        Announcement existing = Announcement.builder()
                .id(1L)
                .title("title")
                .content("content")
                .link("link")
                .build();
        Announcement expected = Announcement.builder()
                .id(1L)
                .title("title_updated")
                .content("content_updated")
                .link("link_updated")
                .build();
        when(announcementRepository.findById(1L)).thenReturn(Optional.ofNullable(existing));
        when(announcementRepository.save(expected)).thenReturn(expected);

        var actual = announcementService.update(expected);

        verify(announcementRepository, times(1)).findById(1L);
        verify(announcementRepository, times(1)).save(expected);
        assertEquals(expected, actual);

    }

    @Test
    void when_updateAnnouncementWithoutExistingId_then_shouldThrowException() {
        Announcement stub = Announcement.builder()
                .id(1L)
                .title("title")
                .content("content")
                .link("link")
                .build();

        when(announcementRepository.findById(1L)).thenReturn(Optional.empty());

        Throwable actual = Assertions.assertThrows(RuntimeException.class,
                () -> announcementService.update(stub));

        verify(announcementRepository, times(1)).findById(1L);
        verify(announcementRepository, times(0)).save(stub);

        assertEquals(RuntimeException.class, actual.getClass());

    }

    @Test
    void when_deleteAnnouncementWithExistingId_then_shouldDelete() {

        Announcement stub1 = Announcement.builder().build();

        when(announcementRepository.findById(1L)).thenReturn(Optional.ofNullable(stub1));
        announcementService.delete(1L);
        assert stub1 != null;
        verify(announcementRepository, times(1)).delete(stub1);

    }

    @Test
    void when_deleteAnnouncementWithoutExistingId_then_shouldThrowException() {

        Announcement stub1 = Announcement.builder().build();

        when(announcementRepository.findById(1L)).thenReturn(Optional.empty());

        Throwable actual = Assertions.assertThrows(RuntimeException.class,
                () -> announcementService.delete(1L));

        assert stub1 != null;
        verify(announcementRepository, times(0)).delete(stub1);
        assertEquals(RuntimeException.class, actual.getClass());

    }

}