package com.example.springmvcapp.service;

import com.example.springmvcapp.dto.EventDto;

import java.util.List;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
    List<EventDto> findAllEvents();

    EventDto findByEventId(Long eventId);

    void delete(Long eventId);

    void updateEvent(EventDto eventDto);

    EventDto findById(Long eventId);
}
