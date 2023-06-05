package com.example.springmvcapp.service.impl;

import com.example.springmvcapp.dto.ClubDto;
import com.example.springmvcapp.dto.EventDto;
import com.example.springmvcapp.models.Club;
import com.example.springmvcapp.models.Event;
import com.example.springmvcapp.repository.ClubRepository;
import com.example.springmvcapp.repository.EventRepository;
import com.example.springmvcapp.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.springmvcapp.mapper.ClubMapper.mapToClub;
import static com.example.springmvcapp.mapper.EventMapper.mapToEventDto;
import static com.example.springmvcapp.mapper.EventMapper.maptoEvent;

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

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventDto findByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return mapToEventDto(event);
    }

    @Override
    public void delete(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = maptoEvent(eventDto);
            eventRepository.save(event);
    }

    @Override
    public EventDto findById(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return mapToEventDto(event);
    }
}
