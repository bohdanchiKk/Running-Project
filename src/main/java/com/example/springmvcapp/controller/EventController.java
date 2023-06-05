package com.example.springmvcapp.controller;

import com.example.springmvcapp.dto.ClubDto;
import com.example.springmvcapp.dto.EventDto;
import com.example.springmvcapp.models.Club;
import com.example.springmvcapp.models.Event;
import com.example.springmvcapp.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private EventService eventService;
    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model){
        Event event = new Event();
        model.addAttribute("clubId",clubId);
        model.addAttribute("event",event);
        return "events-create";
    }
    @GetMapping("/events")
    public String eventList(Model model){
        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("events",events);
        return "events-list";
    }
    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model){
        EventDto eventDto = eventService.findByEventId(eventId);
        model.addAttribute("event", eventDto);
        return "events-detail";
    }
    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId){
        eventService.delete(eventId);
        return "redirect:/events";
    }
    @GetMapping("/events/{eventId}/edit")
    public String editClubForm(@PathVariable("eventId") Long eventId,Model model){
        EventDto event = eventService.findByEventId(eventId);
        model.addAttribute("event",event);
        return "event-edit";
    }
    @PostMapping("/events/{eventId}/edit")
    public String updateClub(@PathVariable("eventId") Long eventId,
                             @Valid @ModelAttribute("event") EventDto event,Model model,
                             BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("event",event);
            return "event-edit";
        }
        EventDto eventDto = eventService.findByEventId(eventId);
        event.setId(eventId);
        event.setClub(eventDto.getClub());
        eventService.updateEvent(event);
        return "redirect:/events";
    }
    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("event")EventDto eventDto, Model model){
        eventService.createEvent(clubId,eventDto);
        return "redirect:/clubs"+clubId;
    }
}
