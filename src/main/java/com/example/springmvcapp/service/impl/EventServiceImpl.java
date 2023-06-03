package com.example.springmvcapp.service.impl;

import com.example.springmvcapp.dto.EventDto;
import com.example.springmvcapp.models.Club;
import com.example.springmvcapp.models.Event;
import com.example.springmvcapp.repository.ClubRepository;
import com.example.springmvcapp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
   private EventRepository eventRepository;
   private ClubRepository clubRepository;
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }
    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = maptoEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    private Event maptoEvent(EventDto eventDto) {
        return Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .photoUrl(eventDto.getPhotoUrl())
                .type(eventDto.getType())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .build();

    }
}
